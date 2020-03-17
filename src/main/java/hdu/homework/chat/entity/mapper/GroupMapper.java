package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.database.Group;

public class GroupMapper extends AbstractMapper<Group> {
    @Override
    public Class<Group> getClazz() {
        return Group.class;
    }
}
