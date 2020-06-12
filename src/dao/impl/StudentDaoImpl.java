package dao.impl;

import pojo.Result;
import utils.DbUtil;
import dao.IStudentDao;
import pojo.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentDaoImpl implements IStudentDao {
    private DbUtil dbUtil = new DbUtil();

    private Student createStudent(Map<String, Object> studentMap) {
        Student student = new Student();
        student.setStudentId((String) studentMap.get("student_id"));
        student.setClassId((Integer) studentMap.get("class_id"));
        student.setStudentName((String) studentMap.get("student_name"));
        student.setStudentSex((Integer) studentMap.get("student_sex"));
        student.setStudentAddress((String) studentMap.get("student_address"));
        student.setStudentPhone((String) studentMap.get("student_phone"));
        return student;
    }

    @Override
    public Student selectByStudentId(String studentId) {
        String sql = "select * from student where student_id = ?";
        Map<String, Object> studentMap = dbUtil.queryForMap(sql, studentId);
        if (studentMap == null) {
            return null;
        }
        return createStudent(studentMap);
    }

    @Override
    public List<Student> selectByClassIdSet(Set<Integer> classIdSet) {
        StringBuilder sql;
        List<Map<String, Object>> studentMapList;
        List<Student> studentList = null;
        if (classIdSet == null || classIdSet.size() <= 0) {
            sql = new StringBuilder("select * from student");
            studentMapList = dbUtil.queryForList(sql.toString());
        } else {
            sql = new StringBuilder("select * from student where class_id in (?");
            for (int i = 1; i < classIdSet.size(); ++i) {
                sql.append(", ?");
            }
            sql.append(")");
            Object[] objects = classIdSet.toArray();
            studentMapList = dbUtil.queryForList(sql.toString(), objects);
        }
        if (!studentMapList.isEmpty()) {
            studentList = new ArrayList<>();
            for (Map<String, Object> studentMap : studentMapList) {
                Student student = createStudent(studentMap);
                studentList.add(student);
            }
        }
        return studentList;
    }

    @Override
    public boolean insert(Student student) {
        String sql = "insert into student " +
                "(student_id, class_id, student_name, student_sex, student_address, student_phone)" +
                "values(?, ?, ?, ?, ?, ?)";
        boolean result = dbUtil.execute(
                sql,
                student.getStudentId(),
                student.getClassId(),
                student.getStudentName(),
                student.getStudentSex(),
                student.getStudentAddress(),
                student.getStudentPhone()
        );
        return result;
    }

    @Override
    public boolean updateByStudentId(String studentId, Student student) {
        String sql = "update student set ";
        boolean flag = false;
        List list = new ArrayList();
        if (student.getStudentId() != null) {
            sql += "student_id = ?";
            flag = true;
            list.add(student.getStudentId());
        }
        if (student.getClassId() != null) {
            if (!flag) {
                sql += "class_id = ?";
                flag = true;
            } else {
                sql += ", class_id = ?";
            }
            list.add(student.getClassId());
        }
        if (student.getStudentName() != null) {
            if (!flag) {
                sql += "student_name = ?";
                flag = true;
            } else {
                sql += ", student_name = ?";
            }
            list.add(student.getStudentName());
        }
        if (student.getStudentSex() != null) {
            if (!flag) {
                sql += "student_sex = ?";
                flag = true;
            } else {
                sql += ", student_sex = ?";
            }
            list.add(student.getStudentSex());
        }
        if (student.getStudentAddress() != null) {
            if (!flag) {
                sql += "student_address = ?";
                flag = true;
            } else {
                sql += ", student_address = ?";
            }
            list.add(student.getStudentAddress());
        }
        if (student.getStudentPhone() != null) {
            if (!flag) {
                sql += "student_phone = ?";
            } else {
                sql += ", student_phone = ?";
            }
            list.add(student.getStudentPhone());
        }
        if (!flag) {
            return true;
        }
        sql += " where student_id = ?";
        list.add(studentId);
        Object[] objects = list.toArray();
        boolean result = dbUtil.execute(sql, objects);
        return result;
    }

    @Override
    public boolean deleteByStudentId(String studentId) {
        String sql = "delete from student where student_id = ?";
        boolean result = dbUtil.execute(sql, studentId);
        return result;
    }
}
