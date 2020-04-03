package hdu.homework.chat.modules.message.service;

import hdu.homework.chat.entity.bean.database.Message;
import hdu.homework.chat.modules.groups.model.GroupUserModel;
import hdu.homework.chat.modules.message.model.MessageModel;
import hdu.homework.chat.modules.message.socket.WebSocket;
import hdu.homework.chat.modules.user.model.UserRepository;
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
    private GroupUserModel guModel;
    private UserRepository userRepository;

    public MessageService(MessageModel messageModel, GroupUserModel guModel, UserRepository userRepository) {
        this.messageModel = messageModel;
        this.guModel = guModel;
        this.userRepository = userRepository;
    }

    public void addMessage(Integer userid, Message message) {
        message.setSenderId(userid);
        messageModel.insertMessage(message);
    }

    public List<Message> getMessages(Integer offset, Integer receiver, String account) {
        return messageModel.getUserByTargetAndId(receiver, offset, userRepository.getUidByAccount(account));
    }

    public List<Message> getGroupMessages(Integer receiver) {
        if (receiver % 10 != 2)
            return null;
        return messageModel.getGroupMessages(receiver);
    }

    public void sendMessage(Integer receiver, String content) {
        String account = SecurityContextHolder.getContext().getAuthentication().getName();
        sendMessage(userRepository.getUidByAccount(account), receiver, content);
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
