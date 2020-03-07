package hdu.homework.chat.entity.bean.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel
public class JoinGroup {
    @ApiModelProperty(name = "群组id", required = true, dataType = "number")
    private Integer gid;
}
