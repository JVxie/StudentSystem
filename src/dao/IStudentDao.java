package dao;

import pojo.Student;

import java.util.List;

public interface IStudentDao {
    Student selectByStudentId(String studentId);

    boolean insert(Student student);

    boolean updateByStudentId(String studentId, Student student);

    boolean deleteByStudentId(String studentId);
}
