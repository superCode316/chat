package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.database.Message;

public class MessageMapper extends AbstractMapper<Message> {
    @Override
    public Class<Message> getClazz() {
        return Message.class;
    }
}
