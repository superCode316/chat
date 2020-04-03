package hdu.homework.chat.modules.message.model;

import hdu.homework.chat.entity.bean.database.Messages;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Messages, String> {
    List<Messages> getAllByToId(Integer to);
}
