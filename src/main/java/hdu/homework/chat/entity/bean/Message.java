package hdu.homework.chat.entity.bean;

import hdu.homework.chat.utils.DateUtils;
import lombok.ToString;

@ToString
public class Message {
    private Integer m_id;
    private String content;
    private String time;
    private Integer senderIDl;
    private Integer recieverID;
    private Integer groupID;
    public Message(){}
    public Message (String content, int sender, int receive, int type) {
        this.content = content;
        if (type == 0)
            this.recieverID = receive;
        else
            this.groupID = receive;
        this.senderIDl = sender;
        this.time = DateUtils.getNowDateString();
    }

    public Integer getM_id() {
        return m_id;
    }

    public void setM_id(Integer m_id) {
        this.m_id = m_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getSenderIDl() {
        return senderIDl;
    }

    public void setSenderIDl(Integer senderIDl) {
        this.senderIDl = senderIDl;
    }

    public Integer getRecieverID() {
        return recieverID;
    }

    public void setRecieverID(Integer recieverID) {
        this.recieverID = recieverID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }
}
