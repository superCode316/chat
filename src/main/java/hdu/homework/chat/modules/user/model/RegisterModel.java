package hdu.homework.chat.modules.user.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RegisterModel {
    private JdbcTemplate template;
    public String insert = "insert into rigister value (?,?,?)";

    public RegisterModel(JdbcTemplate template) {
        this.template = template;
    }

    public void insertRecord(int username, String d1, String d2) {
        template.update(insert, username, d1, d2);
    }
}
