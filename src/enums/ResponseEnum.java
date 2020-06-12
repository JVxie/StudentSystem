package enums;

import lombok.Getter;

@Getter
public enum ResponseEnum {
    ERROR(-1, "服务端异常"),
    SUCCESS(0, "成功"),
    USERNAME_OR_PASSWORD_ERROR(1, "用户名或密码错误"),
    NEED_LOGIN(2, "您尚未登录，请先登录"),
    LOGIN_EXISTS(3, "用户已登录"),
    STUDENT_NOT_FOUND(4, "学生信息不存在"),
    STUDENT_EXISTS(5, "学生学号已存在"),
    STUDENT_ID_NOT_NULL(6, "学生学号不能为空"),
    STUDENT_NAME_NOT_NULL(7, "学生姓名不能为空"),
    CLASS_ID_NOT_NULL(8, "班级不能为空"),
    STUDENT_SEX_ERROR(9, "性别选择错误"),
    RESULT_EXISTS(10, "该学生已录入成绩"),
    RESULT_NOT_FOUND(11, "该学生尚未录入成绩"),
    CLASS_ERROR(12, "班级选择错误"),
    RESULT_AVERAGE_NOT_NULL(13, "考试平均成绩不能为空"),
    RESULT_MUTUAL_NOT_NULL(14, "同学互评成绩不能为空"),
    RESULT_MORAL_NOT_NULL(15, "品德成绩不能为空"),
    RESULT_TEACHER_NOT_NULL(16, "任课老师评分成绩不能为空"),
    LAST_RESULT_NOT_FOUND(17, "找不到相关成绩记录"),
    RESULT_NOT_NULL(18, "成绩信息填写不完整或填写格式错误"),
    ;

    Integer code;
    String desc;

    ResponseEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
