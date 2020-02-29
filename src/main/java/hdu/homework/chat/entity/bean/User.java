package hdu.homework.chat.entity.bean;

import lombok.Data;

@Data
public class User {
    private int uid;
    private String phone;
    private String password;
    private String createTime;
}
