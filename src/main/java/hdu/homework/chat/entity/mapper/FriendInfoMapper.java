package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.response.FriendInfo;

/**
 * created by 钱曹宇@supercode on 3/17/2020
 */
public class FriendInfoMapper extends AbstractMapper<FriendInfo> {
    @Override
    public Class<FriendInfo> getClazz() {
        return FriendInfo.class;
    }
}
