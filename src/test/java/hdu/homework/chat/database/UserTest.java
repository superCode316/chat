package hdu.homework.chat.database;

import hdu.homework.chat.ChatApplication;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.modules.user.model.UserRepository;
import hdu.homework.chat.modules.user.service.UserService;
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
    @Autowired
    private UserService userService;
    @Test
    public void saveUser() {
        User user = new User("username", "password");
        userRepository.save(user);
    }

    @Test
    public void getUser() {
        System.out.println(userRepository.getUserByAccount("username"));
    }

    @Test
    public void delUser() {
        userRepository.deleteById("51");
    }

    @Test
    public void limitInfo() {
//        User user = new User("username", "password");
//        user.setProvince("浙江");
//        user.setBirth("2000-03-16");
//        user.setName("qcy0");
//        userRepository.save(user);
        System.out.println(userService.getLimitUserInfo("username"));
    }

    @Test
    public void getUid() {
        System.out.println(userRepository.getUidByAccount("username"));
    }
}
