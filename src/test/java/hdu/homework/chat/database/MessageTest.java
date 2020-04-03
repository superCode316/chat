package hdu.homework.chat.database;

import hdu.homework.chat.modules.message.model.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MessageTest {
    @Autowired
    private MessageModel messageModel;

}
