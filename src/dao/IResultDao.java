package dao;

import pojo.Result;

import java.util.List;
import java.util.Set;

public interface IResultDao {
    List<Result> selectByStudentIdSet(Set<String> studentIdSet);

    boolean insert(Result result);

    boolean updateByStudentId(String studentId, Result result);

    boolean deleteByStudentId(String studentId);
}
