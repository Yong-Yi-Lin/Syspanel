package org.example.springbootdemo1.controller;
import org.springframework.ui.Model;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Controller
@CrossOrigin
@RequestMapping("/login")
//@SessionAttributes(value = {"user"})
public class LoginController {
    UserService userService;
    //    @Autowired
    private HttpSession httpSession; // 注入HttpSession
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @PostMapping("/checkUser")
    @ResponseBody
    public Results login(@ModelAttribute User userForm, HttpServletResponse response,HttpSession session) {
        Results results = new Results();

        if (userForm == null || userForm.getUserName() == null || userForm.getPassword() == null) {
            results = Results.fail("用户名或密码为空！");
            return results;
        }
        User user = userService.getUserByName(userForm.getUserName());

//        model.addAttribute("user",user);

        if (user == null || !user.getPassword().equals(userForm.getPassword())) {
            results = Results.fail("用户名或密码错误！");
            return results;
        }
//        HttpSession httpSession = request.getSession();
        session.setAttribute("username", user.getUserName());
        session.setAttribute("email",user.getEmail());
        session.setAttribute("role",user.getRole());
        String role = user.getRole();
//        httpSession.setAttribute("role", role);
//        System.out.println(session.getId());
        logger.info("登录成功，用户名 '{}' 已放入会话", user.getUserName());
        System.out.println(session.getAttribute("username"));
        boolean result = userService.logUserLogin(userForm.getUserName());

        results = Results.success("验证成功！！！！!", role);
        return results;
    }
    @GetMapping("/exit")
    @ResponseBody
    public Results toExit(HttpServletRequest request,HttpSession session) {
        System.out.println("用户退出");
//        HttpSession httpSession = request.getSession(false);
        // 不创建新的session，如果当前没有session则返回null
        if (session != null) {
            // 清除用户会话中的认证信息
            String username = (String) session.getAttribute("username");
            session.removeAttribute("username"); // 移除用户对象
            session.removeAttribute("role"); // 移除角色信息
            session.removeAttribute("email");
            userService.recordUserLogout(username);
            System.out.println(session.getAttribute("username"));
        }
        return Results.success("退出成功");
        // 重定向到登录页面或主页
//            return "redirect:/"; // 或者使用SimpleUrlLogoutSuccessHandler来重定向
    }

}
