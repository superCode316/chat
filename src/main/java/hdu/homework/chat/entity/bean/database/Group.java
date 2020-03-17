package hdu.homework.chat.entity.bean.database;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Data
@AllArgsConstructor
@ToString
@ApiModel
public class Group {
    @ApiModelProperty(name = "群组id", dataType = "number")
    private Integer gId;
    @ApiModelProperty(name = "群组名称", dataType = "string")
    private String name;
    @ApiModelProperty(name = "创建时间", dataType = "string", value = "2020-02-02 20:02")
    private String createTime;
    @ApiModelProperty(value = "群组介绍", dataType = "string")
    private String description;
    @ApiModelProperty(value = "群主id", dataType = "number")
    private Integer adminId;
    @ApiModelProperty(value = "群组头像", dataType = "string")
    private String profileUrl;
    @ApiModelProperty(value = "群消息", dataType = "string")
    private String notice;
    public Group(){}

    public Group(String name, String description, String profileUrl) {
        this.name = name;
        this.description = description;
        this.profileUrl = profileUrl;
    }
}
