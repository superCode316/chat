package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.database.Messages;

public class MessageMapper extends AbstractMapper<Messages> {
    @Override
    public Class<Messages> getClazz() {
        return Messages.class;
    }
}
