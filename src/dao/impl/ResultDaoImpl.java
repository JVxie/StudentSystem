package dao.impl;

import utils.DbUtil;
import dao.IResultDao;
import pojo.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultDaoImpl implements IResultDao {
    private DbUtil dbUtil = new DbUtil();

    @Override
    public List<Result> selectByStudentIdSet(Set<String> studentIdSet) {
        StringBuilder sql;
        List<Map<String, Object>> resultMapList;
        List<Result> resultList = null;
        if (studentIdSet == null || studentIdSet.size() <= 0) {
            sql = new StringBuilder("select * from result");
            resultMapList = dbUtil.queryForList(sql.toString());

        } else {
            sql = new StringBuilder("select * from result where student_id in (?");
            for (int i = 1; i < studentIdSet.size(); ++i) {
                sql.append(", ?");
            }
            sql.append(")");
            Object[] objects = studentIdSet.toArray();
            resultMapList = dbUtil.queryForList(sql.toString(), objects);
        }
        if (!resultMapList.isEmpty()) {
            resultList = new ArrayList<>();
            for (Map<String, Object> resultMap : resultMapList) {
                Result result = new Result();
                result.setResultId((Integer) resultMap.get("result_id"));
                result.setStudentId((String) resultMap.get("student_id"));
                result.setResultAverage((Float) resultMap.get("result_average"));
                result.setResultMutual((Float) resultMap.get("result_mutual"));
                result.setResultMoral((Float) resultMap.get("result_moral"));
                result.setResultTeacher((Float) resultMap.get("result_teacher"));
                resultList.add(result);
            }
        }
        return resultList;
    }

    @Override
    public boolean insert(Result result) {
        String sql = "insert into result " +
                "(result_id, student_id, result_average, result_mutual, result_moral, result_teacher) " +
                "values (?, ?, ?, ?, ?, ?)";
        boolean row = dbUtil.execute(
                sql,
                result.getResultId(),
                result.getStudentId(),
                result.getResultAverage(),
                result.getResultMutual(),
                result.getResultMoral(),
                result.getResultTeacher()
        );
        return row;
    }

    @Override
    public boolean updateByStudentId(String studentId, Result result) {
        String sql = "update result set ";
        boolean flag = false;
        List list = new ArrayList();
        if (result.getResultAverage() != null) {
            sql += "result_average = ?";
            flag = true;
            list.add(result.getResultAverage());
        }
        if (result.getResultMutual() != null) {
            if (!flag) {
                sql += "result_mutual = ?";
                flag = true;
            } else {
                sql += ", result_mutual = ?";
            }
            list.add(result.getResultMutual());
        }
        if (result.getResultMoral() != null) {
            if (!flag) {
                sql += "result_moral = ?";
                flag = true;
            } else {
                sql += ", result_moral = ?";
            }
            list.add(result.getResultMoral());
        }
        if (result.getResultTeacher() != null) {
            if (!flag) {
                sql += "result_teacher = ?";
                flag = true;
            } else {
                sql += ", result_teacher = ?";
            }
            list.add(result.getResultTeacher());
        }
        if (!flag) {
            return true;
        }
        sql += " where student_id = ?";
        list.add(studentId);
        Object[] objects = list.toArray();
        boolean row = dbUtil.execute(sql, objects);
        return row;
    }

    @Override
    public boolean deleteByStudentId(String studentId) {
        String sql = "delete from result where student_id = ?";
        boolean row = dbUtil.execute(sql, studentId);
        return row;
    }
}
