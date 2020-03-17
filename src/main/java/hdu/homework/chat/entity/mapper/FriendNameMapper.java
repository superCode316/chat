package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.response.FriendName;

/**
 * created by 钱曹宇@supercode on 3/17/2020
 */
public class FriendNameMapper extends AbstractMapper<FriendName> {
    @Override
    public Class<FriendName> getClazz() {
        return FriendName.class;
    }
}
