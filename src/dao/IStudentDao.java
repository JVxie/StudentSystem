package dao;

import pojo.Student;

import java.util.List;
import java.util.Set;

public interface IStudentDao {
    Student selectByStudentId(String studentId);

    List<Student> selectByClassIdSet(Set<Integer> classIdSet);

    boolean insert(Student student);

    boolean updateByStudentId(String studentId, Student student);

    boolean deleteByStudentId(String studentId);
}
