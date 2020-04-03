package hdu.homework.chat.modules.user.model;

import hdu.homework.chat.entity.bean.database.User;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@DynamicUpdate
@DynamicInsert
public interface UserRepository extends JpaRepository<User, String> {
    User getUserByAccount(String account);
    User findByAccount(String account);
    @Query("select new hdu.homework.chat.entity.bean.database.User(u.uId, u.account, u.profileUrl) from User u where u.uId = ?1")
    User limitUserInfoById(Integer id);
    @Query("select new hdu.homework.chat.entity.bean.database.User(u.uId, u.account, u.profileUrl) from User u where u.account = ?1")
    User limitUserInfoByAccount(String account);
    @Query("select u.uId from User u where u.account = ?1")
    Integer getUidByAccount(String account);
    @Query("select new hdu.homework.chat.entity.bean.database.User(u.account, u.password) from User u where u.account = ?1")
    User authInfo(String account);
}
