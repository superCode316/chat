package hdu.homework.chat.modules.groups.model;

import hdu.homework.chat.entity.bean.database.GroupInfo;
import hdu.homework.chat.entity.mapper.GroupMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Deprecated
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

    public GroupInfo getGroupById(Integer id) {
        return template.queryForObject(getGroup, new GroupMapper(), id);
    }

    public GroupInfo getGroupByName(String name) {
        return template.queryForObject(getGroupByName, new GroupMapper(), name);
    }

    public List<GroupInfo> getGroupsByUserId(Integer userid) {
        return template.query(getGroupByUserID, new GroupMapper(), userid);
    }

    public void addGroup(GroupInfo groupInfo) {
        template.update(addGroup, groupInfo.getName(), groupInfo.getCreateTime(), groupInfo.getDescription(), groupInfo.getAdminId(), groupInfo.getProfileUrl());
    }

    public List<GroupInfo> searchGroupsByName(String name) {
        return template.query(getGetGroupByName, new GroupMapper(), name+"%", "%"+name, "%"+name+"%");
    }
}
