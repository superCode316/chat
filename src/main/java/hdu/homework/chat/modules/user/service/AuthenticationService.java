package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.User;
import hdu.homework.chat.entity.bean.request.UserPost;
import hdu.homework.chat.modules.user.model.RegisterModel;
import hdu.homework.chat.modules.user.model.UserModel;
import hdu.homework.chat.utils.JSONTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final RegisterModel registerModel;
    private final UserDetailsService userDetailsService;
    private final JSONTokenUtil jsonTokenUtil;

    private List<Character> symbols = List.of('.',',','!','@','#','$','%','+','-','*','/');
    private SimpleDateFormat format;
    private final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public AuthenticationService(UserModel User, RegisterModel registerModel, @Qualifier("userdetails") UserDetailsService userDetailsService, JSONTokenUtil jsonTokenUtil) {
        this.User = User;
        this.registerModel = registerModel;
        this.userDetailsService = userDetailsService;
        this.jsonTokenUtil = jsonTokenUtil;
        this.format = new SimpleDateFormat(dateFormat);
    }

    public hdu.homework.chat.entity.bean.User getUserByPhone(String phone) {
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
        UserDetails details = userDetailsService.loadUserByUsername(username);
        return jsonTokenUtil.generateToken(details);
    }

    public int getExpire() {
        return jsonTokenUtil.getExpiration();
    }

    private boolean validate(User user, String password) {
        return user.getPassword().equals(password);
    }

    public void logUser(String username) {
        Integer uid = User.getUidByPhone(username);
        registerModel.insertRecord(uid, format.format(new Date()), format.format(new Date()));
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
        String now = this.format.format(new Date());
        User.addUser(user.getUsername(), user.getPassword(), now);
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
