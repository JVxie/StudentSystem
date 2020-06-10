package dao.impl;

import utils.DbUtil;
import dao.IClassDao;
import pojo.Class;

import java.util.Map;

public class ClassDaoImpl implements IClassDao {
    private DbUtil dbUtil = new DbUtil();

    @Override
    public Class selectByClassId(Integer classId) {
        String sql = "select * from class where class_id = ?";
        Map<String, Object> classMap = dbUtil.queryForMap(sql, classId);
        if (classMap == null) return null;
        return new Class((Integer) classMap.get("class_id"), (String) classMap.get("class_name"));
    }
}
