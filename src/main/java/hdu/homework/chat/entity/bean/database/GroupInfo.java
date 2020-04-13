package hdu.homework.chat.entity.bean.database;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * created by 钱曹宇@supercode on 3/8/2020
 */
@ToString
@ApiModel
@Entity
@Table()
public class GroupInfo {
    public Integer getGId() {
        return gId;
    }

    public void setgId(Integer gId) {
        this.gId = gId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public GroupInfo(Integer gId, String name, String createTime, String description, Integer adminId, String profileUrl, String notice) {
        this.gId = gId;
        this.name = name;
        this.createTime = createTime;
        this.description = description;
        this.adminId = adminId;
        this.profileUrl = profileUrl;
        this.notice = notice;
    }

    @Id
    @GeneratedValue
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
