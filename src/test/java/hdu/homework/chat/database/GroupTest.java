package hdu.homework.chat.database;

import hdu.homework.chat.entity.bean.Group;
import hdu.homework.chat.modules.groups.model.GroupModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class GroupTest {
    @Autowired
    private GroupModel groupModel;
    @Test
    public void addGroup() {
        Group group = new Group(null, "test group", "2020-02-02 00:00:00", "description", 1, "", null);
        groupModel.addGroup(group);
    }

    @Test
    public void getGroup() {
        Group group = groupModel.getGroupById(1);
        assert group.equals(new Group(1, "test group", "2020-02-02 00:00:00", "description", 1, "", null));
    }
}