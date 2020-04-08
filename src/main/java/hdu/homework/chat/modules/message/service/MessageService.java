package hdu.homework.chat.modules.message.service;

import hdu.homework.chat.entity.bean.database.Messages;
import hdu.homework.chat.modules.groups.model.GroupUserModel;
import hdu.homework.chat.modules.message.model.MessageRepository;
import hdu.homework.chat.modules.message.utils.WebSocket;
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
    private GroupUserModel guModel;
    private UserRepository userRepository;
    private MessageRepository messageRepository;

    public MessageService(GroupUserModel guModel, UserRepository userRepository, MessageRepository messageRepository) {
        this.guModel = guModel;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public void addMessage(Messages messages) {
        messageRepository.save(messages);
    }

    public List<Messages> getGroupMessages(Integer receiver) {
        if (receiver % 10 != 2)
            return null;
        return messageRepository.getAllByToId(receiver);
    }

    public void sendMessage(Integer sender, Integer receiver, String content) {
        Messages messages1 = new Messages(sender, content, receiver);
        messages1.setTime(DateUtils.getNowDateString());
        addMessage(messages1);

//        Set<Object> users = redisUtil.getGroupMember(String.valueOf(receiver));
        List<Integer> users = guModel.getUsers(receiver);
        users.forEach(user->
            WebSocket.send(String.valueOf(user), content, sender, receiver)
        );
    }
}
