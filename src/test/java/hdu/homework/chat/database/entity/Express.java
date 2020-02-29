package hdu.homework.chat.database.entity;

import lombok.Data;

@Data
public class Express {
    private Integer id;

    private String info;

    private String trackingNo;

    private Boolean isTracking;

    private String ownerId;

    private String updateTime;

    private String createTime;

}
