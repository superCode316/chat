package hdu.homework.chat.database;

import hdu.homework.chat.entity.bean.Message;
import hdu.homework.chat.entity.factory.MessageFactory;
import hdu.homework.chat.modules.message.model.MessageModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MessageTest {
    @Autowired
    private MessageModel messageModel;
    @Test
    public void insertMessage() {
        Message message = MessageFactory.instance()
                .from(1)
                .to(1, 0)
                .content("foobar")
                .build();
        messageModel.insertMessage(message);
    }
    @Test
    public void getMessage() {
        List<Message> messages = messageModel.getMessage(1);
        System.out.println(messages);
        assert messages.size() == 1;
    }
}
