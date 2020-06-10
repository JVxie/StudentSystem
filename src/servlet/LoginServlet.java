package servlet;

import Utils.JsonUtil;
import com.google.gson.Gson;
import constants.StudentSystemConstants;
import enums.ResponseEnum;
import pojo.Admin;
import service.IAdminService;
import service.impl.AdminServiceImpl;
import vo.ResponseVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private IAdminService adminService = new AdminServiceImpl();
    
    private JsonUtil jsonUtil = new JsonUtil();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Admin admin = jsonUtil.readJson(request, Admin.class);
        ResponseVo result = adminService.login(admin.getAdminId(), admin.getAdminPsw());
        HttpSession session = request.getSession();
        if (session.getAttribute("adminId") != null) {
            jsonUtil.writeJson(response, ResponseVo.error(ResponseEnum.LOGIN_EXISTS));
            return ;
        }
        if (result.getStatus().equals(ResponseEnum.SUCCESS.getCode())) {
            session.setAttribute("adminId", admin.getAdminId());
        }
        jsonUtil.writeJson(response, result);
//        System.out.println(1);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
