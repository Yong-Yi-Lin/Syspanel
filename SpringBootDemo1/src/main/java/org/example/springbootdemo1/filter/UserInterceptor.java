package org.example.springbootdemo1.filter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class UserInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        System.out.println(uri);
        HttpSession session = request.getSession(false);
        // 假设用户信息存储在session中的属性名为"user"
        String username = (session != null) ? (String) session.getAttribute("username") : null;
        System.out.println("lhw是大傻逼");

        if (uri.startsWith("/userPage")) {
            // 用户访问用户页面，如果未登录，则重定向到登录页面
            if (username == null) {
                System.out.println("lhw是大傻逼");

//                request.setAttribute("error", "您未登录，请先登录！");
                Cookie errorCookie = new Cookie("error", "lhw是大傻逼请先登录，请先登录！");
                errorCookie.setPath("/"); // 设置Cookie的路径为根路径
                response.addCookie(errorCookie);
//                response.sendRedirect("/"); // 重定向到登录页面
                return false;
            }
        } else if (uri.startsWith("/adminPage")) {
            // 用户访问管理员页面，如果未登录或登录身份是guest，则重定向到登录页面或错误页面
//            System.out.println(session.getAttribute("role"));
            if (username != null&&"guest".equals(session.getAttribute("role"))) {
                System.out.println("lhw是大傻逼");

//                request.setAttribute("error", "您没有权限访问该页面！");
                Cookie errorCookie = new Cookie("error", "您没有权限访问该页面!");
                errorCookie.setPath("/"); // 设置Cookie的路径为根路径
                response.addCookie(errorCookie);
                System.out.println(errorCookie);
                response.sendRedirect("/userPage"); // 重定向到登录页面

                return false;
            }else if(session == null){
                System.out.println("lhw是大傻逼");
                Cookie errorCookie = new Cookie("error", "lhw是大傻逼请先登录！");
                errorCookie.setPath("/"); // 设置Cookie的路径为根路径
                response.addCookie(errorCookie);
                System.out.println(errorCookie);


            }
        }
        return true;
    }

}
