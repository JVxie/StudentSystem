package dao.impl;

import Utils.DbUtil;
import dao.IResultDao;
import pojo.Class;
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
                result.setResultMoral((Float) resultMap.get("result_moral"));
                resultList.add(result);
            }
        }
        return resultList;
    }

    @Override
    public boolean insert(Result result) {
        String sql = "insert into result " +
                "() " +
                "";
        return false;
    }

    @Override
    public boolean updateByStudentId(String studentId, Result result) {
        return false;
    }

    @Override
    public boolean deleteByStudentId(String studentId) {

        return false;
    }
}
