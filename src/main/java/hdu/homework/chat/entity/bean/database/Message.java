package hdu.homework.chat.entity.bean.database;

import lombok.Data;
import lombok.ToString;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@ToString
@Data
public class Message {
    private Integer mId;
    private String content;
    private String time;
    private Integer senderId;
    private Integer toId;
    public Message(){}

    public Message(String content, Integer toId) {
        this.content = content;
        this.toId = toId;
    }
}
