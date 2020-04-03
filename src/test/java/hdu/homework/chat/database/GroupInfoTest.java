package hdu.homework.chat.database;

import hdu.homework.chat.ChatApplication;
import hdu.homework.chat.entity.bean.database.GroupInfo;
import hdu.homework.chat.modules.groups.model.GroupModel;
import hdu.homework.chat.modules.groups.model.GroupRepository;
import hdu.homework.chat.modules.groups.model.GroupUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;

@SpringBootTest(classes = ChatApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GroupInfoTest {
    @Autowired
    private GroupModel groupModel;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private GroupUserRepository gr;
    @Test
    public void addGroup() {
        GroupInfo groupInfo = new GroupInfo(null, "test group", "2020-02-02 00:00:00", "description", 1, "", null);
        groupModel.addGroup(groupInfo);
    }

    @Test
    public void getUserGroups() {
            System.out.println(groupRepository.groupInfosByAccount("15868818382"));
    }

    @Test
    public void getUserInGroup() {
        System.out.println(gr.usersInGroup(12));
    }

//    @Test
//    public void getGroup() {
//        Optional<Group> group = groupModel.getGroupById(1);
//        assert group.get().equals(new Group(1, "test group", "2020-02-02 00:00:00", "description", 1, "", null));
//    }
}
