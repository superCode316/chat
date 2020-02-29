package hdu.homework.chat.database;

import hdu.homework.chat.database.entity.TestEntity;
import hdu.homework.chat.entity.bean.User;
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

    private JdbcTemplate template;
    private ApplicationContext context;
    @Autowired
    public DatabaseTests(JdbcTemplate template, ApplicationContext context) {
        this.template = template;
        this.context = context;
        this.template.execute("use chat");
    }

    @Test
    void selectSome() {
        List<Map<String, Object>> expresses = template.queryForList("select * from user where u_phone = ?;", "15868818382");
        for (Map<String, Object> map : expresses) {
            for (String s : map.keySet()) {
                System.out.println(s + ":" + map.get(s));
            }
            System.out.println("=======================");
        }
    }

    @Test
    void getUsers() {
        User list = template.queryForObject("select * from user where u_phone = ? limit 1", new UserMapper(), "15868818382");
        System.out.println(list);
    }

    @Test
    void insertTest() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        template.update("insert into user(u_phone, u_pwd, creaTime) values (?,?,?)", "15868818382", "Ubuntu16.04", format.format(new Date()));
    }

    @Test
    void insertRegisterTest() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = format.format(new Date());
        template.update("insert into rigister(u_id, loginTime, lastUpdateTime) values (?,?,?)", "1", now, now);
        template.update("insert into rigister(u_id, loginTime, lastUpdateTime) values (?,?,?)", "1", now, now);
    }

    @Test
    void selectCount() {
        Object result = template.queryForObject("select count(*) as c from user where u_phone=?", Long.class, "15868818382");
        System.out.println(result);
    }

    @Test
    void updateTest() {
        List<TestEntity> list = getList();
        List<Object[]> objects = new ArrayList<>();
        list.forEach(o->{
            objects.add(new Object[]{o.getId(), o.getData(), o.getTime()});
        });
        template.batchUpdate("insert into test values (?,?,?)", objects);
    }

    List<TestEntity> getList() {
        List<TestEntity> list = new ArrayList<>();
        for (int i=10; i < 15; i++) {
            list.add(new TestEntity(i, getRandomString(), "2020/02/28 12:29:00"));
        }
        return list;
    }

    String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    String getRandomString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < 20; i++) {
            sb.append(base.charAt((int)(Math.random() * 62)));
        }
        return sb.toString();
    }

}
