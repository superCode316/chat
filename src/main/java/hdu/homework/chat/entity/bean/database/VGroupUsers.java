package hdu.homework.chat.entity.bean.database;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * created by 钱曹宇@supercode on 2020/4/3
 */
@Entity
@Table(name = "v_group_users")
@Data
public class VGroupUsers {
    @Id
    private String name;
    private int gId;
    private int uId;
    private String account;
}
