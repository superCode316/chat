package hdu.homework.chat.modules.message.model;

import hdu.homework.chat.entity.bean.Message;
import hdu.homework.chat.entity.mapper.MessageMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MessageModel {
    private JdbcTemplate template;
    private final String insertMessage = "insert into messages(content, time, senderid, recieverid, groupid) value (?,?,?,?,?)";
    private final String getMessage = "select * from messages where senderID = ?";
    public MessageModel(JdbcTemplate template) {
        this.template = template;
    }
    public void insertMessage(Message message) {
        template.update(insertMessage, message.getContent(), message.getTime(), message.getSenderIDl(), message.getRecieverID(), message.getGroupID());
    }
    public List<Message> getMessage(Integer sender) {
        return template.query(getMessage, new MessageMapper(), sender);
    }
}
