package hdu.homework.chat.modules.message.service;

import hdu.homework.chat.entity.bean.Group;
import hdu.homework.chat.entity.bean.Message;
import hdu.homework.chat.entity.bean.request.MessageBody;
import hdu.homework.chat.entity.bean.request.RequestMessage;
import hdu.homework.chat.entity.factory.MessageFactory;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.message.model.MessageModel;
import hdu.homework.chat.modules.user.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageModel messageModel;
    private UserModel userModel;

    public MessageService(MessageModel messageModel, UserModel userModel) {
        this.messageModel = messageModel;
        this.userModel = userModel;
    }

    public void addMessage(String username, Message message) {
        message.setSenderIDl(userModel.getUidByPhone(username));
        messageModel.insertMessage(message);
    }

    public List<Message> getSentMessage(String from) {
        return messageModel.getMessage(userModel.getUidByPhone(from));
    }

    public List<Message> getMessages(RequestMessage body, String username) {
        if (body.getType() == 0) {
            return userMessage(body.getReceiver(), body.getOffsetId(), username);
        } else {
            return groupMessage(body.getReceiver(), body.getOffsetId());
        }
    }

    private List<Message> groupMessage(Integer to, Integer offset) {
        return messageModel.getGroupMessageByTargetAndId(to, offset);
    }

    private List<Message> userMessage(Integer to, Integer offset, String username) {
        return messageModel.getUserMessageByTargetAndId(to, offset, userModel.getUidByPhone(username));
    }
}
