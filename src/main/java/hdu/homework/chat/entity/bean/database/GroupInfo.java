package hdu.homework.chat.entity.bean.database;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@Data
@AllArgsConstructor
@ToString
@ApiModel
@Entity
@Table()
public class GroupInfo {
    @Id
    @ApiModelProperty(name = "群组id", dataType = "number")
    @Column(name = "gId")
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
    public GroupInfo(){}

    public GroupInfo(Integer gId, String name, String description, Integer adminId, String profileUrl, String notice) {
        this.gId = gId;
        this.name = name;
        this.description = description;
        this.adminId = adminId;
        this.profileUrl = profileUrl;
        this.notice = notice;
    }

    public GroupInfo(String name, String description, String profileUrl) {
        this.name = name;
        this.description = description;
        this.profileUrl = profileUrl;
    }
}
