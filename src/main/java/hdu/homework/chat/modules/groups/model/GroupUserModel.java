package hdu.homework.chat.modules.groups.model;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Repository
public class GroupUserModel {
    private JdbcTemplate template;

    private final String join = "insert into group_users(g_id, u_id, create_time) value (?,?,?)";
    private final String gusers = "select u_id from group_users where g_id = ?";

    public GroupUserModel(JdbcTemplate template) {
        this.template = template;
    }

    public void join(Integer gid, Integer uid, String time) {
        template.update(join, gid, uid, time);
    }

    public List<Integer> getUsers(Integer gid) {
        return template.queryForList(gusers, Integer.class, gid);
    }
}
