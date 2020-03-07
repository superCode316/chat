package hdu.homework.chat.modules.groups.model;

import hdu.homework.chat.entity.bean.Group;
import hdu.homework.chat.entity.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GroupModel {
    private final String getGroup = "select * from groupInfo where g_id = ?";
    private final String getGroupByName = "select * from groupInfo where name = ?";
    private final String addGroup = "insert into groupInfo(name, creaTime, description, adminID, thumbnail_url) value (?,?,?,?,?)";
    private final String getGroupByUserName = "select * from groupInfo where g_id in (select g_id from groupusers where u_id = (select u_id from `user` where account = ?))";
    private final String getGroupByUser = "select * from groupInfo where g_id in (select g_id from groupusers where u_id = ?)";

    private JdbcTemplate template;

    @Autowired
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

    public List<Group> getGroupsByUserName(String username) {
        return template.query(getGroupByUserName, new GroupMapper(), username);
    }

    public List<Group> getGroupsByUser(Integer id) {
        return template.query(getGroupByUser, new GroupMapper(), id);
    }

    public void addGroup(Group group) {
        template.update(addGroup, group.getName(), group.getCreaTime(), group.getDescription(), group.getAdminID(), group.getThumbnail_url());
    }
}
