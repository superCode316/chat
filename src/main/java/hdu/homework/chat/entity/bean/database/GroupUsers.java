package hdu.homework.chat.entity.bean.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * created by 钱曹宇@supercode on 2020/4/3
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupUsers {
    @Id
    @Column(name = "g_id")
    private int gId;
    private int uId;
    private String createTime;
}
