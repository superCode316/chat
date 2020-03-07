package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.response.Friend;

public class FriendMapper extends AbstractMapper<Friend> {
    @Override
    public Class<Friend> getClazz() {
        return Friend.class;
    }
}
