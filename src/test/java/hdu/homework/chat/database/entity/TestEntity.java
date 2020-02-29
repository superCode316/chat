package hdu.homework.chat.database.entity;

import lombok.Data;

@Data
public class TestEntity {
    private int id;
    private String data;
    private String time;

    public TestEntity(int id, String data, String time) {
        this.id = id;
        this.data = data;
        this.time = time;
    }
}
