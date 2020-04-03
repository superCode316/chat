package hdu.homework.chat.modules.message.model;

import hdu.homework.chat.entity.bean.database.Messages;
import hdu.homework.chat.entity.mapper.MessageMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Repository
@Deprecated
public class MessageModel {
    private JdbcTemplate template;
    private final String insertMessage = "insert into messages(content, time, sender_id, to_id) value (?,?,?,?)";
    private final String getMessageByTarget = "select * from " +
            "( select * from messages where m_id > ?) as `m*`" +
            " where to_id = ?  and sender_id = ? or sender_id = ? and to_id = ? ";
    public MessageModel(JdbcTemplate template) {
        this.template = template;
    }
    private final String getGroupMessages = "select * from messages where to_id = ?";

    public void insertMessage(Messages messages) {
        template.update(insertMessage, messages.getContent(), messages.getTime(), messages.getSenderId(), messages.getToId());
    }

    public List<Messages> getUserByTargetAndId(Integer target, Integer offset, Integer sender) {
        return template.query(getMessageByTarget, new MessageMapper(), offset, target, sender, sender, target);
    }

    public List<Messages> getGroupMessages(Integer target) {
        return template.query(getGroupMessages, new MessageMapper(), target);
    }
}
