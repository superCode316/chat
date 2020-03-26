package hdu.homework.chat.modules.groups.model;

import hdu.homework.chat.entity.bean.database.Group;
import hdu.homework.chat.entity.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Repository
public class GroupModel {
    private final String getGroup = "select * from group_info where g_id = ?";
    private final String getGroupByName = "select * from group_info where name = ?";
    private final String addGroup = "insert into group_info(name, create_time, description, admin_id, profile_url) value (?,?,?,?,?)";
    private final String getGroupByUserID = "select * from group_info where g_id in (select g_id from group_users where u_id = ?)";
    private final String getGetGroupByName = "select * from group_info where name like ? or name like ? or name like ?";
    private JdbcTemplate template;

    public GroupModel(JdbcTemplate template) {
        this.template = template;
    }

    public Group getGroupById(Integer id) {
        List<Group> result = template.query(getGroup, new GroupMapper(), id);
        if (result.size() == 0)
            return null;
        return result.get(0);
    }

    public Group getGroupByName(String name) {
        List<Group> groups = template.query(getGroupByName, new GroupMapper(), name);
        if (groups.size() == 0)
            return null;
        return groups.get(0);
    }

    public List<Group> getGroupsByUserId(Integer userid) {
        return template.query(getGroupByUserID, new GroupMapper(), userid);
    }

    public void addGroup(Group group) {
        template.update(addGroup, group.getName(), group.getCreateTime(), group.getDescription(), group.getAdminId(), group.getProfileUrl());
    }

    public List<Group> searchGroupsByName(String name) {
        return template.query(getGetGroupByName, new GroupMapper(), name+"%", "%"+name, "%"+name+"%");
    }
}
