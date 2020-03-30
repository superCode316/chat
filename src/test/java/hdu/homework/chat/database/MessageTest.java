package hdu.homework.chat.database;

import hdu.homework.chat.entity.bean.database.Message;
import hdu.homework.chat.modules.message.model.MessageModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MessageTest {
    @Autowired
    private MessageModel messageModel;

}
