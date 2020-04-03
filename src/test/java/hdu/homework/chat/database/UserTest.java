package hdu.homework.chat.database;

import hdu.homework.chat.ChatApplication;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.user.model.UserModel;
import hdu.homework.chat.modules.user.repo.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * created by 钱曹宇@supercode on 3/30/2020
 */
@SpringBootTest(classes = ChatApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveUser() {
        User user = new User("username", "password");
        userRepository.save(user);
    }

    @Test
    public void getUser() {
        System.out.println(userRepository.getUserByAccount("username"));
            }
}
