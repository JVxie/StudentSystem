package service;

import pojo.Admin;
import vo.ResponseVo;

/**
 * 管理员业务块
 */
public interface IAdminService {
    ResponseVo login(String adminId, String adminPsw);
}
