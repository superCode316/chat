package hdu.homework.chat.modules.auth;

import hdu.homework.chat.entity.bean.database.JWToken;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.entity.bean.request.UserPost;
import hdu.homework.chat.modules.user.model.UserModel;
import hdu.homework.chat.utils.JSONTokenUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class AuthenticationService{

    private final UserModel User;
    private final UserDetailsService userDetailsService;
    private final JSONTokenUtil jsonTokenUtil;
    private final RedisTemplate<String, Object> redis;

    private List<Character> symbols = List.of('.',',','!','@','#','$','%','+','-','*','/');
    private SimpleDateFormat format;
    private final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public AuthenticationService(UserModel User, @Qualifier("userdetails") UserDetailsService userDetailsService, JSONTokenUtil jsonTokenUtil, RedisTemplate<String, Object> redis) {
        this.User = User;
        this.userDetailsService = userDetailsService;
        this.jsonTokenUtil = jsonTokenUtil;
        this.redis = redis;
        this.format = new SimpleDateFormat(dateFormat);
    }

    public UsernamePasswordAuthenticationToken verifyCookie(String cookie) {
        UsernamePasswordAuthenticationToken token = null;
        String username = jsonTokenUtil.getUsernameFromToken(cookie);
        if (username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jsonTokenUtil.validateToken(cookie, userDetails)) {
                token = new JWToken(userDetails, null, userDetails.getAuthorities());
            }
        }
        return token;
    }

    public hdu.homework.chat.entity.bean.database.User getUserByPhone(String phone) {
        return User.getUserByPhone(phone);
    }

    public String login(String username, String password) {
        User user;
        try {
            user = User.getUserByPhone(username);
            if (!validate(user, password)) return null;
        } catch (EmptyResultDataAccessException | NullPointerException e) {
            return null;
        }
        addToRedis(username);
        UserDetails details = userDetailsService.loadUserByUsername(username);
        return jsonTokenUtil.generateToken(details);
    }

    private void addToRedis(String username) {
        redis.opsForValue().set(username, true, 1800L);
    }

    public int getExpire() {
        return jsonTokenUtil.getExpiration();
    }

    private boolean validate(User user, String password) {
        return user.getPassword().equals(password);
    }

    public String checkRegister(UserPost user) {
        if (checkUserExist(user.getUsername()))
            return "已经有这个用户名了";
        if (!checkPasswordComplexity(user.getPassword())) {
            return "用户名或密码太简单";
        }
        return null;
    }

    public void addUser(UserPost user) {
        User.addUser(user.getUsername(), user.getPassword());
    }

    private boolean checkUserExist(String username) {
        return User.getUserCount(username) == 1;
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
