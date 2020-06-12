package service.impl;

import dao.IClassDao;
import dao.IResultDao;
import dao.IStudentDao;
import dao.impl.ClassDaoImpl;
import dao.impl.ResultDaoImpl;
import dao.impl.StudentDaoImpl;
import enums.ResponseEnum;
import pojo.Class;
import pojo.Result;
import pojo.Student;
import service.ILastResultService;
import vo.LastResultVo;
import vo.ResponseVo;

import java.util.*;
import java.util.stream.Collectors;

public class LastResultServiceImpl implements ILastResultService {
    private IStudentDao studentDao = new StudentDaoImpl();

    private IResultDao resultDao = new ResultDaoImpl();

    private IClassDao classDao = new ClassDaoImpl();

    @Override
    public ResponseVo search(String studentId, Integer classId) {
        Set<String> studentIdSet = new HashSet<>();
        // 是否有输入学号
        if (studentId == null || studentId.trim().equals("")) {
            // 是否有选择班级
            if (classId == null) {
                List<Student> studentList = studentDao.selectByClassIdSet(null);
                studentIdSet = studentList.stream().map(Student::getStudentId).collect(Collectors.toSet());
            } else {
                Class aClass = classDao.selectByClassId(classId);
                if (aClass == null) {
                    return ResponseVo.error(ResponseEnum.CLASS_ERROR);
                }
                Set<Integer> classIdSet = new HashSet<>();
                classIdSet.add(classId);
                List<Student> studentList = studentDao.selectByClassIdSet(classIdSet);
                studentIdSet = studentList.stream().map(Student::getStudentId).collect(Collectors.toSet());
            }
        } else {
            if (classId == null) {
                Student student = studentDao.selectByStudentId(studentId);
                if (student == null) {
                    return ResponseVo.error(ResponseEnum.STUDENT_NOT_FOUND);
                }
                studentIdSet.add(studentId);
            } else {
                Class aClass = classDao.selectByClassId(classId);
                if (aClass == null) {
                    return ResponseVo.error(ResponseEnum.CLASS_ERROR);
                }
                Set<Integer> classIdSet = new HashSet<>();
                classIdSet.add(classId);
                List<Student> studentList = studentDao.selectByClassIdSet(classIdSet);
                if (studentList == null || studentList.isEmpty()) {
                    return ResponseVo.error(ResponseEnum.STUDENT_NOT_FOUND);
                }
                studentIdSet.add(studentId);
            }
        }
        List<Result> resultList = resultDao.selectByStudentIdSet(studentIdSet);
        if (resultList == null || resultList.isEmpty()) {
            return ResponseVo.error(ResponseEnum.LAST_RESULT_NOT_FOUND);
        }
        List<LastResultVo> lastResultVoList = new ArrayList<>();
        for (Result result : resultList) {
            Student student = studentDao.selectByStudentId(result.getStudentId());
            LastResultVo lastResultVo = new LastResultVo();
            lastResultVo.setStudentId(student.getStudentId());
            lastResultVo.setClassId(student.getClassId());
            lastResultVo.setStudentName(student.getStudentName());
            lastResultVo.setLastResult(
                    result.getResultAverage() * (float) 0.6
                    + result.getResultMutual() * (float) 0.1
                    + result.getResultMoral() * (float) 0.1
                    + result.getResultTeacher() * (float) 0.2
            );
            lastResultVoList.add(lastResultVo);
        }
        lastResultVoList.sort((o1, o2) -> {
            int r = 0;
            if (o2.getLastResult() - o1.getLastResult() > 0) {
                r = 1;
            } else if (o2.getLastResult() - o1.getLastResult() < 0) {
                r = -1;
            } else {
                r = o1.getStudentId().compareTo(o2.getStudentId());
            }
            return r;
        });
        return ResponseVo.success(lastResultVoList);
    }
}
