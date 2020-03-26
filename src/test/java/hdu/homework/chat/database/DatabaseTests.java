package hdu.homework.chat.database;

import hdu.homework.chat.database.entity.TestEntity;
import hdu.homework.chat.entity.bean.database.User;
import hdu.homework.chat.entity.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.text.SimpleDateFormat;
import java.util.*;

@ActiveProfiles("dev")
@SpringBootTest
public class DatabaseTests {

    @Autowired
    private JdbcTemplate template;

    @Test
    public void query() {
        List<User> users = template.queryForList("select * from user;", User.class);
        printUser(users);
    }

    private void printUser(List<User> users) {
        users.forEach(System.out::println);
    }


}
