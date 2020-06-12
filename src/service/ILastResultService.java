package service;

import vo.ResponseVo;

public interface ILastResultService {
    ResponseVo search(String studentId, Integer classId);
}
