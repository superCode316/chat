package hdu.homework.chat.modules.message.service;

import hdu.homework.chat.entity.bean.Message;
import hdu.homework.chat.entity.factory.MessageFactory;
import hdu.homework.chat.modules.message.model.MessageModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageModel messageModel;

    public MessageService(MessageModel messageModel) {
        this.messageModel = messageModel;
    }

    public void addMessage(String content, Integer from, Integer to, Integer type) {
        Message message = MessageFactory.instance().from(from).to(to, type).content(content).build();
        messageModel.insertMessage(message);
    }

    public List<Message> getMessage(Integer from) {
        return messageModel.getMessage(from);
    }
}
