package form;

import lombok.Data;

@Data
public class StudentUpdateForm {
    private String studentIdLast;

    private String studentId;

    private Integer classId;

    private String studentName;

    private Integer studentSex;

    private String studentAddress;

    private String studentPhone;
}
