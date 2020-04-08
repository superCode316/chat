package hdu.homework.chat.entity.bean.response;

import hdu.homework.chat.utils.DateUtils;
import lombok.Data;

/**
 * created by 钱曹宇@supercode on 2020/4/4
 */
public class BaseMsg {
    private String time;
    public BaseMsg() {
        this.time = DateUtils.getNowDateString();
    }
}
