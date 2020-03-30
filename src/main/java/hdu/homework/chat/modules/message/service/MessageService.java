package hdu.homework.chat.modules.message.service;

import hdu.homework.chat.entity.bean.database.Message;
import hdu.homework.chat.modules.groups.model.GroupUserModel;
import hdu.homework.chat.modules.groups.service.GroupService;
import hdu.homework.chat.modules.message.model.MessageModel;
import hdu.homework.chat.modules.message.socket.WebSocket;
import hdu.homework.chat.modules.user.model.UserModel;
import hdu.homework.chat.utils.DateUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Service
public class MessageService {
    private MessageModel messageModel;
    private UserModel userModel;
    private GroupUserModel guModel;

    public MessageService(MessageModel messageModel, UserModel userModel, GroupUserModel guModel) {
        this.messageModel = messageModel;
        this.userModel = userModel;
        this.guModel = guModel;
    }

    public void addMessage(Integer userid, Message message) {
        message.setSenderId(userid);
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

    public void sendMessage(Integer receiver, String content) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        sendMessage(userModel.getUidByAccount(username), receiver, content);
    }

    public void sendMessage(Integer sender, Integer receiver, String content) {
        Message message1 = new Message(content, receiver);
        message1.setTime(DateUtils.getNowDateString());
        addMessage(sender, message1);

//        Set<Object> users = redisUtil.getGroupMember(String.valueOf(receiver));
        List<Integer> users = guModel.getUsers(receiver);
        users.forEach(user->
            WebSocket.send(String.valueOf(user), content, sender)
        );
    }
}
