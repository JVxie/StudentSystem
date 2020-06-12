package service.impl;

import dao.IClassDao;
import dao.impl.ClassDaoImpl;
import pojo.Class;
import service.IClassService;
import vo.ResponseVo;

import java.util.List;

public class ClassServiceImpl implements IClassService {
    private IClassDao classDao = new ClassDaoImpl();

    @Override
    public ResponseVo search(Integer classId) {
        if (classId != null) {
            Class aClass = classDao.selectByClassId(classId);
            return ResponseVo.success(aClass);
        }
        List<Class> classList = classDao.selectAll();
        return ResponseVo.success(classList);
    }
}
