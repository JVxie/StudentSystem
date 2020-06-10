package pojo;

import lombok.Data;

@Data
public class Class {
    private Integer classId;

    private String className;

    public Class(Integer classId, String className) {
        this.classId = classId;
        this.className = className;
    }
}
