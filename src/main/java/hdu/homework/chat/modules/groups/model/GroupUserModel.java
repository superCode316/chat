package hdu.homework.chat.modules.groups.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Repository
public class GroupUserModel {
    private JdbcTemplate template;

    private final String join = "insert into groupusers value (?,?,?)";

    public GroupUserModel(JdbcTemplate template) {
        this.template = template;
    }

    public void join(Integer gid, Integer uid, String time) {
        template.update(join, gid, uid, time);
    }
}
