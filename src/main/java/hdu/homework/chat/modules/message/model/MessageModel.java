package hdu.homework.chat.modules.message.model;

import hdu.homework.chat.entity.bean.Message;
import hdu.homework.chat.entity.mapper.MessageMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Repository
public class MessageModel {
    private JdbcTemplate template;
    private final String insertMessage = "insert into messages(content, time, senderid, recieverid, groupid) value (?,?,?,?,?)";
    private final String getMessage = "select * from messages where senderID = ?";
    private final String getMessageByTarget = "select * from " +
            "( select * from messages where m_id > ?) as `m*`" +
            " where recieverID = ?  and senderID = ? or senderID = ? and recieverID = ? ";
    private final String getGroupMessageByTarget = "select * from messages where groupID = ? and m_id > ?";
    public MessageModel(JdbcTemplate template) {
        this.template = template;
    }
    public void insertMessage(Message message) {
        template.update(insertMessage, message.getContent(), message.getTime(), message.getSenderIDl(), message.getRecieverID(), message.getGroupID());
    }
    public List<Message> getMessage(Integer sender) {
        return template.query(getMessage, new MessageMapper(), sender);
    }

    public List<Message> getUserMessageByTargetAndId(Integer target, Integer offset, Integer sender) {
        return template.query(getMessageByTarget, new MessageMapper(), target, sender, sender, target, offset);
    }

    public List<Message> getGroupMessageByTargetAndId(Integer target, Integer offset) {
        return template.query(getGroupMessageByTarget, new MessageMapper(), target, offset);
    }
}
