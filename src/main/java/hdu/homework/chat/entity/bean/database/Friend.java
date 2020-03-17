package hdu.homework.chat.entity.bean.database;

import lombok.Data;

/**
 * created by 钱曹宇@supercode on 3/17/2020
 */
@Data
public class Friend {
    private Integer userId;
    private Integer friendId;
    private String addTime;
    private Integer isAgree;
}
