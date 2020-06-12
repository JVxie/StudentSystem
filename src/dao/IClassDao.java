package dao;

import pojo.Class;

import java.util.List;

public interface IClassDao {
    Class selectByClassId(Integer classId);

    List<Class> selectAll();
}
