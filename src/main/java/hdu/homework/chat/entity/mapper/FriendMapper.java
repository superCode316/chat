package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.database.Friend;

/**
 * created by 钱曹宇@supercode on 3/17/2020
 */
public class FriendMapper extends AbstractMapper<Friend> {
    @Override
    public Class<Friend> getClazz() {
        return Friend.class;
    }

    public FriendMapper(String... args) {
        super(args);
    }

    public FriendMapper() {
    }
}
