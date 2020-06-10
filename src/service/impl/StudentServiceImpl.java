package service.impl;

import dao.IStudentDao;
import dao.impl.StudentDaoImpl;
import enums.ResponseEnum;
import pojo.Student;
import service.IStudentService;
import vo.ResponseVo;

public class StudentServiceImpl implements IStudentService {
    private IStudentDao studentDao = new StudentDaoImpl();

    @Override
    public ResponseVo create(Student student) {
        Student studentByDb = studentDao.selectByStudentId(student.getStudentId());
        if (studentByDb != null) {
            return ResponseVo.error(ResponseEnum.STUDENT_EXISTS);
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
            return ResponseVo.error(ResponseEnum.STUDENT_NOTFOUND);
        }
        return ResponseVo.success(student);
    }

    @Override
    public ResponseVo update(String studentId, Student student) {
        Student studentByDb = studentDao.selectByStudentId(studentId);
        if (studentByDb != null) {
            return ResponseVo.error(ResponseEnum.STUDENT_EXISTS);
        }
        boolean result = studentDao.updateByStudentId(studentId, student);
        if (!result) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success(student);
    }

    @Override
    public ResponseVo delete(String studentId) {
        Student student = studentDao.selectByStudentId(studentId);
        if (student != null) {
            return ResponseVo.error(ResponseEnum.STUDENT_EXISTS);
        }
        boolean result = studentDao.deleteByStudentId(studentId);
        if (!result) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }
}
