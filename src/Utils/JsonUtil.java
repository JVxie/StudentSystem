package Utils;

import com.google.gson.Gson;
import pojo.Admin;
import vo.ResponseVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class JsonUtil {
    private Gson gson = new Gson();

    public <T> T readJson(HttpServletRequest request, Class<T> classOfT) {
        StringBuilder strbu;
        T result = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            strbu = new StringBuilder();
            String str = "";
            while ((str = reader.readLine()) != null) {
                strbu.append(str);
            }
            result = gson.fromJson(strbu.toString(), classOfT);
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void writeJson(HttpServletResponse response, ResponseVo responseVo) throws IOException {
        response.setContentType("text/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String jsonString = gson.toJson(responseVo);
        out.println(jsonString);
        out.flush();
        out.close();
    }
}
