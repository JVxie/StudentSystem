package service;


import pojo.Result;
import vo.ResponseVo;

public interface IResultService {
    ResponseVo create(Result result);

    ResponseVo search(String studentId);

    ResponseVo update(String studentId, Result result);

    ResponseVo delete(String studentId);
}
