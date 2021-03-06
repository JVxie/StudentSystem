package servlet;


import utils.JsonUtil;
import enums.ResponseEnum;
import form.StudentUpdateForm;
import pojo.Student;
import service.IStudentService;
import service.impl.StudentServiceImpl;
import vo.ResponseVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
    private IStudentService studentService = new StudentServiceImpl();

    private JsonUtil jsonUtil = new JsonUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = jsonUtil.readJson(request, Student.class);
        if (student.getStudentId() == null || student.getStudentId().trim().equals("")) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_ID_NOT_NULL));
            return ;
        }
        if (student.getClassId() == null) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.CLASS_ID_NOT_NULL));
            return ;
        }
        if (student.getStudentName() == null || student.getStudentName().trim().equals("")) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_NAME_NOT_NULL));
            return ;
        }
        if (student.getStudentSex() != null && (student.getStudentSex() < 1 || student.getStudentSex() > 3)) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_SEX_ERROR));
            return ;
        }
        ResponseVo responseVo = studentService.create(student);
        jsonUtil.writeJson(response, responseVo);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Student student = jsonUtil.readJson(request, Student.class);
        String studentId = request.getParameter("studentId");
        if (studentId == null || studentId.trim().equals("")) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_ID_NOT_NULL));
            return ;
        }
        ResponseVo responseVo = studentService.search(studentId);
        jsonUtil.writeJson(response, responseVo);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentUpdateForm studentUpdateForm = jsonUtil.readJson(request, StudentUpdateForm.class);
        Student student = new Student();
        student.setStudentId(studentUpdateForm.getStudentId());
        student.setClassId(studentUpdateForm.getClassId());
        student.setStudentName(studentUpdateForm.getStudentName());
        student.setStudentSex(studentUpdateForm.getStudentSex());
        student.setStudentAddress(studentUpdateForm.getStudentAddress());
        student.setStudentPhone(studentUpdateForm.getStudentPhone());
        if (student.getStudentId().trim().equals("")) {
//            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_ID_NOT_NULL));
            student.setStudentId(null);
        }
        if (student.getStudentName().trim().equals("")) {
//            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_NAME_NOT_NULL));
            student.setStudentName(null);
        }
        if (student.getStudentSex() != null && (student.getStudentSex() < 1 || student.getStudentSex() > 3)) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_SEX_ERROR));
            return ;
        }
        if (student.getStudentAddress().trim().equals("")) {
//            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_NAME_NOT_NULL));
            student.setStudentAddress(null);
        }
        if (student.getStudentPhone().trim().equals("")) {
//            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_NAME_NOT_NULL));
            student.setStudentPhone(null);
        }
        ResponseVo responseVo = studentService.update(studentUpdateForm.getStudentIdLast(), student);
        jsonUtil.writeJson(response, responseVo);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = jsonUtil.readJson(request, Student.class);
        if (student.getStudentId() == null || student.getStudentId().trim().equals("")) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_ID_NOT_NULL));
            return ;
        }
        ResponseVo responseVo = studentService.delete(student.getStudentId());
        jsonUtil.writeJson(response, responseVo);
    }
}
