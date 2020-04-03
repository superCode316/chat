package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.database.GroupInfo;

public class GroupMapper extends AbstractMapper<GroupInfo> {
    @Override
    public Class<GroupInfo> getClazz() {
        return GroupInfo.class;
    }
}
