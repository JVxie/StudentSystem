package dao;

import pojo.Admin;

public interface IAdminDao {
    Admin selectByAdminId(String adminId);
}
