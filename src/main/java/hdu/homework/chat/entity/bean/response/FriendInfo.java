package hdu.homework.chat.entity.bean.response;

import lombok.Data;

/**
 * created by 钱曹宇@supercode on 3/17/2020
 */
@Data
public class FriendInfo {
    private Integer uId;
    private String account;
    private String name;
    private String profileUrl;
    private String birth;
    private String province;
    private String city;
    private String email;
    private String sign;
}
