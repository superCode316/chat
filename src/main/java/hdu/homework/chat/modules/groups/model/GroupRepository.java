package hdu.homework.chat.modules.groups.model;

import hdu.homework.chat.entity.bean.database.GroupInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * created by 钱曹宇@supercode on 2020/4/3
 */
public interface GroupRepository extends JpaRepository<GroupInfo, String> {
    GroupInfo getGroupInfoBygId(Integer gid);
    List<GroupInfo> getGroupInfosByNameLike(String pattern);

    @Query(value = "select new hdu.homework.chat.entity.bean.database.GroupInfo(g.gId, g.name, g.createTime, g.description, g.adminId, g.profileUrl, g.notice) from GroupInfo g where g.gId in (select gu.gId from GroupUsers gu where gu.uId = ?1)")
    List<GroupInfo> groupInfosByUserId(Integer uid);
    @Query(value = "select new hdu.homework.chat.entity.bean.database.GroupInfo(g.gId, g.name, g.createTime, g.description, g.adminId, g.profileUrl, g.notice) from GroupInfo g where g.gId in (select gu.gId from VGroupUsers gu where gu.account = ?1)")
    List<GroupInfo> groupInfosByAccount(String account);
}
