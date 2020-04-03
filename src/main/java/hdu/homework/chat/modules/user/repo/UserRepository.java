package hdu.homework.chat.modules.user.repo;

import hdu.homework.chat.entity.bean.database.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User getUserByAccount(String account);
}
