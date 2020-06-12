package service.impl;

import dao.IAdminDao;
import dao.impl.AdminDaoImpl;
import enums.ResponseEnum;
import pojo.Admin;
import service.IAdminService;
import vo.ResponseVo;

public class AdminServiceImpl implements IAdminService {
    private IAdminDao adminDao = new AdminDaoImpl();
    
    @Override
    public ResponseVo login(String adminId, String adminPsw) {
        if (adminId == null || adminPsw == null) {
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        Admin admin = adminDao.selectByAdminId(adminId);
        if (!admin.getAdminPsw().equals(adminPsw)) {
            return ResponseVo.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR);
        }
        admin.setAdminPsw("");
        return ResponseVo.success(admin);
    }
}
