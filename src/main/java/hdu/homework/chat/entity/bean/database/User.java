package hdu.homework.chat.entity.bean.database;

import lombok.Data;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Data
public class User {
    private int uid;
    private String account;
    private String password;
    private String createTime;
    private String name;
    private String profileURL;
    public User(){}

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public static String REFUSE_LOGIN_USERNAME = "nologin";
    public static String REFUSE_LOGIN_PASSWORD = "nologin";
    public static User DEFAULT = new User(REFUSE_LOGIN_USERNAME, REFUSE_LOGIN_PASSWORD);


}
