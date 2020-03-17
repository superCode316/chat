package hdu.homework.chat.entity.bean.response.swagger;

import hdu.homework.chat.entity.bean.response.FriendName;
import hdu.homework.chat.entity.bean.response.Msg;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel
public class FriendResponse extends Msg<List<FriendName>> {
    public FriendResponse(Integer code) {
        super(code);
    }
}
