package hdu.homework.chat.entity.bean;

import hdu.homework.chat.utils.DateUtils;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class Message {
    private Integer m_id;
    private String content;
    private String time;
    private Integer senderIDl;
    private Integer recieverID;
    private Integer groupID;
    public Message(){}
}
