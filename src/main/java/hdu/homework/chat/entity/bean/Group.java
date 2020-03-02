package hdu.homework.chat.entity.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Group {
    private Integer g_id;
    private String name;
    private String creaTime;
    private String description;
    private Integer adminID;
    private String thumbnail_url;
    private String notic;
    public Group(){}
}
