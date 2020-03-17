package hdu.homework.chat.entity.mapper;

import hdu.homework.chat.entity.bean.database.User;

public class UserMapper extends AbstractMapper<User> {
    @Override
    public Class<User> getClazz() {
        return User.class;
    }

    public UserMapper(String... args) {
        super(args);
    }

    public UserMapper() {
    }
}
