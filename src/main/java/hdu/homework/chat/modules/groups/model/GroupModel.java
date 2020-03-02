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
    private final String addGroup = "insert into groupInfo(name, creaTime, description, adminID, thumbnail_url) value (?,?,?,?,?)";
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

    public void addGroup(Group group) {
        template.update(addGroup, group.getName(), group.getCreaTime(), group.getDescription(), group.getAdminID(), group.getThumbnail_url());
    }
}
