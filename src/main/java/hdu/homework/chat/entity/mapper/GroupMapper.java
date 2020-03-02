package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.Group;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupMapper extends AbstractMapper<Group> {
    @Override
    public Class<Group> getClazz() {
        return Group.class;
    }
}
