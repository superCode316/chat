package hdu.homework.chat.entity.bean.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Getter
@ApiModel("新增群组实体")
public class AddGroup {
    @ApiModelProperty(value = "群组名称", required = true, dataType = "string")
    private String name;
    @ApiModelProperty(value = "群组介绍", required = false, dataType = "string")
    private String description;
    @ApiModelProperty(value = "群组头像", required = false, dataType = "string")
    private String thumbnail_url;
}
