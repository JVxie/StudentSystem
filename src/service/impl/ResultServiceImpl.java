package service.impl;

import dao.IResultDao;
import dao.IStudentDao;
import dao.impl.ResultDaoImpl;
import dao.impl.StudentDaoImpl;
import enums.ResponseEnum;
import pojo.Result;
import pojo.Student;
import service.IResultService;
import vo.ResponseVo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResultServiceImpl implements IResultService {
    private IResultDao resultDao = new ResultDaoImpl();

    private IStudentDao studentDao = new StudentDaoImpl();

    @Override
    public ResponseVo create(Result result) {
        Student student = studentDao.selectByStudentId(result.getStudentId());
        if (student == null) {
            return ResponseVo.error(ResponseEnum.STUDENT_NOT_FOUND);
        }
        Set<String> studentIdSet = new HashSet<>();
        studentIdSet.add(result.getStudentId());
        List<Result> resultList = resultDao.selectByStudentIdSet(studentIdSet);
        if (resultList != null && !resultList.isEmpty()) {
            return ResponseVo.error(ResponseEnum.RESULT_EXISTS);
        }
        boolean row = resultDao.insert(result);
        if (!row) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        resultList = resultDao.selectByStudentIdSet(studentIdSet);
        return ResponseVo.success(resultList.get(0));
    }

    @Override
    public ResponseVo search(String studentId) {
        Set<String> studentIdSet = new HashSet<>();
        studentIdSet.add(studentId);
        List<Result> resultList = resultDao.selectByStudentIdSet(studentIdSet);
        if (resultList == null || resultList.isEmpty()) {
            return ResponseVo.error(ResponseEnum.RESULT_NOT_FOUND);
        }
        return ResponseVo.success(resultList.get(0));
    }

    @Override
    public ResponseVo update(String studentId, Result result) {
        Set<String> studentIdSet = new HashSet<>();
        studentIdSet.add(studentId);
        List<Result> resultList = resultDao.selectByStudentIdSet(studentIdSet);
        if (resultList == null || resultList.isEmpty()) {
            return ResponseVo.error(ResponseEnum.RESULT_NOT_FOUND);
        }
        boolean row = resultDao.updateByStudentId(studentId, result);
        if (!row) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success(result);
    }

    @Override
    public ResponseVo delete(String studentId) {
        Set<String> studentIdSet = new HashSet<>();
        studentIdSet.add(studentId);
        List<Result> resultList = resultDao.selectByStudentIdSet(studentIdSet);
        if (resultList == null || resultList.isEmpty()) {
            return ResponseVo.error(ResponseEnum.RESULT_NOT_FOUND);
        }
        boolean row = resultDao.deleteByStudentId(studentId);
        if (!row) {
            return ResponseVo.error(ResponseEnum.ERROR);
        }
        return ResponseVo.success();
    }
}
