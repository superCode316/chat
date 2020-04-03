package hdu.homework.chat.entity.bean.response.swagger;

import hdu.homework.chat.entity.bean.database.Messages;
import hdu.homework.chat.entity.bean.response.Msg;

import java.util.List;

public class MessagesResponse extends Msg<List<Messages>> {
    public MessagesResponse(Integer code) {
        super(code);
    }
}
