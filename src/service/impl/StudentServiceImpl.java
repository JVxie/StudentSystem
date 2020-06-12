package service.impl;

import dao.IClassDao;
import dao.IStudentDao;
import dao.impl.ClassDaoImpl;
import dao.impl.StudentDaoImpl;
import enums.ResponseEnum;
import pojo.Class;
import pojo.Student;
import service.IStudentService;
import vo.ResponseVo;

public class StudentServiceImpl implements IStudentService {
    private IStudentDao studentDao = new StudentDaoImpl();

    private IClassDao classDao = new ClassDaoImpl();

    @Override
    public ResponseVo create(Student student) {
        Student studentByDb = studentDao.selectByStudentId(student.getStudentId());
        if (studentByDb != null) {
            return ResponseVo.error(ResponseEnum.STUDENT_EXISTS);
        }
        Class aClass = classDao.selectByClassId(student.getClassId());
        if (aClass == null) {
            return ResponseVo.error(ResponseEnum.CLASS_ERROR);
        }
        boolean result = studentDao.insert(student);
        if (!result) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success(student);
    }

    @Override
    public ResponseVo search(String studentId) {
        Student student = studentDao.selectByStudentId(studentId);
        if (student == null) {
            return ResponseVo.error(ResponseEnum.STUDENT_NOT_FOUND);
        }
        return ResponseVo.success(student);
    }

    @Override
    public ResponseVo update(String studentId, Student student) {
        if (student.getStudentId() != null) {
            Student studentByDb = studentDao.selectByStudentId(student.getStudentId());
            if (studentByDb != null) {
                return ResponseVo.error(ResponseEnum.STUDENT_EXISTS);
            }
        }
        if (student.getClassId() != null) {
            Class aClass = classDao.selectByClassId(student.getClassId());
            if (aClass == null) {
                return ResponseVo.error(ResponseEnum.CLASS_ERROR);
            }
        }
        boolean result = studentDao.updateByStudentId(studentId, student);
        if (!result) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        if (student.getStudentId() == null) {
            student = studentDao.selectByStudentId(studentId);
        } else {
            student = studentDao.selectByStudentId(student.getStudentId());
        }
        return ResponseVo.success(student);
    }

    @Override
    public ResponseVo delete(String studentId) {
        Student student = studentDao.selectByStudentId(studentId);
        if (student == null) {
            return ResponseVo.error(ResponseEnum.STUDENT_NOT_FOUND);
        }
        boolean result = studentDao.deleteByStudentId(studentId);
        if (!result) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }
}
