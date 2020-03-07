package hdu.homework.chat.entity.bean.response.swagger;

import hdu.homework.chat.entity.bean.Message;
import hdu.homework.chat.entity.bean.response.Msg;

import java.util.List;

public class MessagesResponse extends Msg<List<Message>> {
    public MessagesResponse(Integer code) {
        super(code);
    }
}
