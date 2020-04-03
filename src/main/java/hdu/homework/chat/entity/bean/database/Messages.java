package hdu.homework.chat.entity.bean.database;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@ToString
@Data
@Entity
public class Messages {
    @Id
    @GeneratedValue
    private Integer mId;
    private String content;
    private String time;
    private Integer senderId;
    private Integer toId;
    public Messages(){}

    public Messages(Integer senderId, String content, Integer toId) {
        this.senderId = senderId;
        this.content = content;
        this.toId = toId;
    }
}
