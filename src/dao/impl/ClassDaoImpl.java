package dao.impl;

import utils.DbUtil;
import dao.IClassDao;
import pojo.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassDaoImpl implements IClassDao {
    private DbUtil dbUtil = new DbUtil();

    @Override
    public Class selectByClassId(Integer classId) {
        String sql = "select * from class where class_id = ?";
        Map<String, Object> classMap = dbUtil.queryForMap(sql, classId);
        if (classMap == null) return null;
        return new Class((Integer) classMap.get("class_id"), (String) classMap.get("class_name"));
    }

    @Override
    public List<Class> selectAll() {
        String sql = "select * from class";
        List<Map<String, Object>> classMapList = dbUtil.queryForList(sql);
        List<Class> classList = new ArrayList<>();
        for (Map<String, Object> classMap : classMapList) {
            Class aClass = new Class((Integer) classMap.get("class_id"), (String) classMap.get("class_name"));
            classList.add(aClass);
        }
        return classList;
    }
}
