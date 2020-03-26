package hdu.homework.chat.modules.message.service;

import hdu.homework.chat.entity.bean.database.Message;
import hdu.homework.chat.modules.message.model.MessageModel;
import hdu.homework.chat.modules.user.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class MessageService {
    private MessageModel messageModel;
    private UserModel userModel;

    public MessageService(MessageModel messageModel, UserModel userModel) {
        this.messageModel = messageModel;
        this.userModel = userModel;
    }

    public void addMessage(String username, Message message) {
        message.setSenderId(userModel.getUidByAccount(username));
        messageModel.insertMessage(message);
    }

    public List<Message> getMessages(Integer offset, Integer receiver, String username) {
        return messageModel.getUserByTargetAndId(receiver, offset, userModel.getUidByAccount(username));
    }

    public List<Message> getGroupMessages(Integer receiver) {
        if (receiver % 10 != 2)
            return null;
        return messageModel.getGroupMessages(receiver);
    }
}
