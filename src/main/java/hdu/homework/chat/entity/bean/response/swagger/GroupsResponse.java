package hdu.homework.chat.entity.bean.response.swagger;

import hdu.homework.chat.entity.bean.Group;
import hdu.homework.chat.entity.bean.response.Msg;
import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel
public class GroupsResponse extends Msg<List<Group>> {
    public GroupsResponse(Integer code) {
        super(code);
    }
}
