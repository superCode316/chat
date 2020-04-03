package hdu.homework.chat.modules.groups.model;

import hdu.homework.chat.entity.bean.database.GroupUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 2020/4/3
 */
public interface GroupUserRepository extends JpaRepository<GroupUsers, String> {
    @Query(nativeQuery = true, value = "select u_id from group_users where g_id = ?")
    List<Integer> usersInGroup(Integer gid);
}
