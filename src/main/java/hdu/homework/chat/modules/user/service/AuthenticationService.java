package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.security.JWToken;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.user.model.UserRepository;
import hdu.homework.chat.utils.JSONTokenUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class AuthenticationService{

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final JSONTokenUtil jsonTokenUtil;
    private final RedisTemplate<String, Object> redis;

    private List<Character> symbols = List.of('.',',','!','@','#','$','%','+','-','*','/');

    public AuthenticationService(UserRepository userRepository, @Qualifier("userdetails") UserDetailsService userDetailsService, JSONTokenUtil jsonTokenUtil, RedisTemplate<String, Object> redis) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.jsonTokenUtil = jsonTokenUtil;
        this.redis = redis;
    }

    public UsernamePasswordAuthenticationToken verifyCookie(String cookie) {
        UsernamePasswordAuthenticationToken token = null;
        String account = jsonTokenUtil.getUsernameFromToken(cookie);
        if (account != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(account);
            if (jsonTokenUtil.validateToken(cookie, userDetails)) {
                token = new JWToken(userDetails, null, userDetails.getAuthorities());
            }
        }
        return token;
    }

    public String login(String account, String password) {
        User user;
        try {
            user = userRepository.authInfo(account);
            if (!validate(user, password)) return null;
        } catch (EmptyResultDataAccessException | NullPointerException e) {
            return null;
        }
        addToRedis(account);
        UserDetails details = userDetailsService.loadUserByUsername(account);
        return jsonTokenUtil.generateToken(details);
    }

    private void addToRedis(String account) {
        redis.opsForValue().set(account, true, 1800L);
    }

    public int getExpire() {
        return jsonTokenUtil.getExpiration();
    }

    private boolean validate(User user, String password) {
        return user != null && user.getPassword().equals(password);
    }

    public Optional<String> register(String username, String password) {
        if (checkUserExist(username))
            return Optional.of("已经有这个用户名了");
        if (!checkPasswordComplexity(password)) {
            return Optional.of("用户名或密码太简单");
        }
        if (username.length() != 11) {
            return Optional.of("用户名必须为电话号码");
        }
        userRepository.save(new User(username, password));
        return Optional.empty();
    }

    private boolean checkUserExist(String username) {
        return userRepository.getUserByAccount(username) != null;
    }

    private boolean checkPasswordComplexity(String password) {
        int symbol=0, number=0, alpha=0;
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) number++;
            else if (Character.isLetter(c)) alpha++;
            else if (symbols.contains(c)) symbol++;
        }
        return (symbol>0&&number>3&&alpha>7);
    }
}
