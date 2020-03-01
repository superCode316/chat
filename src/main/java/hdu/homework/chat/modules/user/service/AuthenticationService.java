package hdu.homework.chat.modules.user.service;

import hdu.homework.chat.entity.bean.User;
import hdu.homework.chat.entity.bean.request.UserPost;
import hdu.homework.chat.modules.user.model.RegisterModel;
import hdu.homework.chat.modules.user.model.UserModel;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AuthenticationService {

    private final UserModel User;
    private final RegisterModel registerModel;
    private List<Character> symbols = List.of('.',',','!','@','#','$','%','+','-','*','/');
    private SimpleDateFormat format;
    private final String dateFormat = "yyyy-MM-dd HH:mm:ss";

    public AuthenticationService(UserModel User, RegisterModel registerModel) {
        this.User = User;
        this.registerModel = registerModel;
        this.format = new SimpleDateFormat(dateFormat);
    }

    public hdu.homework.chat.entity.bean.User getUserByPhone(String phone) {
        return User.getUserByPhone(phone);
    }

    public boolean validUser(String username, String password) {
        try {
            User user = User.getUserByPhone(username);
            return user.getPassword().equals(password);
        } catch (EmptyResultDataAccessException | NullPointerException e) {
            return false;
        }
    }

    public void logUser(String username) {
        Integer uid = User.getUidByPhone(username);
        registerModel.insertRecord(uid, format.format(new Date()), format.format(new Date()));
    }

    public String checkRegister(UserPost user) {
        if (checkUserExist(user.getUsername()))
            return "yijingyouzhegeyonghumingle";
        if (checkPasswordComplexity(user.getPassword())) {
            return "yonghumimataijiandan";
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
