package hdu.homework.chat.entity.bean.database;

import hdu.homework.chat.modules.user.controller.UserController;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Data
@Entity
public class User {
    @Id
    private int uId;
    private String account;
    private String password;
    private String createTime;
    private String name;
    private String profileUrl;
    private String birth;
    private String province;
    private String city;
    private String email;
    private String sign;
    public User(){}

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public static String REFUSE_LOGIN_USERNAME = "nologin";
    public static String REFUSE_LOGIN_PASSWORD = "nologin";
    public static User DEFAULT = new User(REFUSE_LOGIN_USERNAME, REFUSE_LOGIN_PASSWORD);


}
