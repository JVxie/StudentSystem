package service;

import pojo.Student;
import vo.ResponseVo;

public interface IStudentService {
    ResponseVo create(Student student);

    ResponseVo search(String studentId);

    ResponseVo update(String studentId, Student student);

    ResponseVo delete(String studentId);
}
