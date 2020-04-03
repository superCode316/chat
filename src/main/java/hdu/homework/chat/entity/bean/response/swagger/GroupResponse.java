package hdu.homework.chat.entity.bean.response.swagger;

import hdu.homework.chat.entity.bean.database.GroupInfo;
import hdu.homework.chat.entity.bean.response.Msg;
import io.swagger.annotations.ApiModel;

@ApiModel()
public class GroupResponse extends Msg<GroupInfo> {
    public GroupResponse(Integer code) {
        super(code);
    }
}
