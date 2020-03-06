package hdu.homework.chat.entity.bean.response.swagger;

import hdu.homework.chat.entity.bean.response.Friend;
import hdu.homework.chat.entity.bean.response.Msg;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel
public class FriendResponse extends Msg<List<Friend>> {
    public FriendResponse(Integer code) {
        super(code);
    }
}
