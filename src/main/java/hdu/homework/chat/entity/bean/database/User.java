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
}