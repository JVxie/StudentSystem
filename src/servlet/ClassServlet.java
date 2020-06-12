package servlet;

import pojo.Class;
import service.IClassService;
import service.impl.ClassServiceImpl;
import utils.JsonUtil;
import vo.ResponseVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ClassServlet", value = "/class")
public class ClassServlet extends HttpServlet {
    private IClassService classService = new ClassServiceImpl();

    private JsonUtil jsonUtil = new JsonUtil();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Class aClass = jsonUtil.readJson(request, Class.class);
        ResponseVo responseVo;
        String classIdString = request.getParameter("classId");
        if (classIdString == null || classIdString.trim().equals("")) {
            responseVo = classService.search(null);
        }
        else {
            Integer classId = Integer.parseInt(classIdString);
            responseVo = classService.search(classId);
        }
        jsonUtil.writeJson(response, responseVo);
    }
}
