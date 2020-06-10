package dao.impl;

import Utils.DbUtil;
import dao.IAdminDao;
import pojo.Admin;

import java.util.Map;

public class AdminDaoImpl implements IAdminDao {
    private DbUtil dbUtil = new DbUtil();

    @Override
    public Admin selectByAdminId(String adminId) {
        if (adminId == null || adminId.trim().equals(""))
            return null;
        String sql = "select * from admin where admin_id = ?";
        Map<String, Object> resultMap = dbUtil.queryForMap(sql, adminId);
        if (resultMap == null) return null;
        Admin admin = new Admin(resultMap.get("admin_id").toString(), resultMap.get("admin_psw").toString());
        return admin;
    }
}
