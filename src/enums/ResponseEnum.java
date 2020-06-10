package enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    ERROR(-1, "服务端异常"),
    SUCCESS(0, "成功"),
    USERNAME_OR_PASSWORD_ERROR(1, "用户名或密码错误"),
    NEED_LOGIN(2, "您尚未登录，请先登录"),
    LOGIN_EXISTS(3, "用户已登录"),
    STUDENT_NOTFOUND(4, "学生信息不存在"),
    STUDENT_EXISTS(5, "学生学号已存在"),
    STUDENT_ID_NOT_NULL(6, "学生学号不能为空"),
    STUDENT_NAME_NOT_NULL(7, "学生姓名不能为空"),
    CLASS_ID_NOT_NULL(8, "班级不能为空"),
    STUDENT_SEX_ERROR(9, "性别选择错误"),
    ;

    Integer code;
    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
