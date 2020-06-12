package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "LoginFilter", urlPatterns = {"/", "/*", "/**"})
public class LoginFilter implements Filter {
    public void destroy() {
    }

    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/login", "/login.html")));

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        System.out.println(path);
        if (allowedPath) {
            System.out.println("这里是不需要处理的url进入的方法");
            HttpSession session = request.getSession();
            if (session.getAttribute("adminId") != null) {
                response.sendRedirect("index.html");
            }
            chain.doFilter(req, resp);
        }
        else {
            System.out.println("这里是需要处理的url进入的方法");
            HttpSession session = request.getSession();
            if (session.getAttribute("adminId") != null) {
                chain.doFilter(req, resp);
            } else {
                response.sendRedirect("login.html");
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
