package servlet;

import enums.ResponseEnum;
import pojo.Result;
import service.IResultService;
import service.impl.ResultServiceImpl;
import utils.JsonUtil;
import vo.ResponseVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ResultServlet", value = "/result")
public class ResultServlet extends HttpServlet {
    private IResultService resultService = new ResultServiceImpl();

    private JsonUtil jsonUtil = new JsonUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result = jsonUtil.readJson(request, Result.class);
        if (result == null) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.RESULT_NOT_NULL));
            return ;
        }
        if (result.getStudentId() == null || result.getStudentId().trim().equals("")) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_ID_NOT_NULL));
            return ;
        }
        ResponseVo responseVo = resultService.create(result);
        jsonUtil.writeJson(response, responseVo);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        Result result = jsonUtil.readJson(request, Result.class);
        String studentId = request.getParameter("studentId");
        if (studentId == null || studentId.trim().equals("")) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_ID_NOT_NULL));
            return ;
        }
        ResponseVo responseVo = resultService.search(studentId);
        jsonUtil.writeJson(response, responseVo);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result = jsonUtil.readJson(request, Result.class);
        if (result == null) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.RESULT_NOT_NULL));
            return ;
        }
        if (result.getStudentId() == null || result.getStudentId().trim().equals("")) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_ID_NOT_NULL));
            return ;
        }
        ResponseVo responseVo = resultService.update(result.getStudentId(), result);
        jsonUtil.writeJson(response, responseVo);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Result result = jsonUtil.readJson(request, Result.class);
        if (result.getStudentId() == null || result.getStudentId().trim().equals("")) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.STUDENT_ID_NOT_NULL));
            return ;
        }
        ResponseVo responseVo = resultService.delete(result.getStudentId());
        jsonUtil.writeJson(response, responseVo);
    }
}
