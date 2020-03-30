package hdu.homework.chat.database;

import hdu.homework.chat.ChatApplication;
import hdu.homework.chat.modules.user.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * created by 钱曹宇@supercode on 3/30/2020
 */
@SpringBootTest(classes = ChatApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserTest {
    @Autowired
    private UserModel userModel;

}
