package servlet;

import service.ILastResultService;
import service.impl.LastResultServiceImpl;
import utils.JsonUtil;
import vo.ResponseVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.StudentSystemConstants.ALL_CLASS;

@WebServlet(name = "LastResultServlet", value = "/lastResult")
public class LastResultServlet extends HttpServlet {
    private JsonUtil jsonUtil = new JsonUtil();

    private ILastResultService lastResultService = new LastResultServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        LastResultForm lastResultForm = jsonUtil.readJson(request, LastResultForm.class);
        String studentId = request.getParameter("studentId");
        String classIdString = request.getParameter("classId");
        Integer classId = null;
        if (classIdString != null && classIdString.trim().equals("")) {
            classId = Integer.parseInt(classIdString);
            if (classId.equals(ALL_CLASS)) classId = null;
        }
        if (studentId != null && studentId.trim().equals(""))
            studentId = null;
        ResponseVo responseVo = lastResultService.search(studentId, classId);
        jsonUtil.writeJson(response, responseVo);
    }
}
