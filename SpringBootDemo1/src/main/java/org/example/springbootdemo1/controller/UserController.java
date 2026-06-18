package org.example.springbootdemo1.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.Logs;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    //依赖注入
    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/getUser/{username}")
    @ResponseBody
    public Results getUserByName(@PathVariable("username") String username){
        System.out.println(username);
        User u = userService.getUserByName(username);
        Results results = null;
        if (u != null){
            results = Results.success("查找成功",u);
        }else{
            results = Results.fail("未查找到该用户");
        }
        return results;
    }
    @GetMapping("/getAllUser")
    @ResponseBody
    public Results getAllUser(@RequestParam(value = "role" ,required = false) String role,
                              @RequestParam(value = "number", required = false) String number) {
        List<User> users ;
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        if ( number != null && !number.isEmpty() || role != null && !role.isEmpty()) {
            users = userService.getAllUserByRole(role,number); // 如果没有提供查询参数，则调用原有的方法
            System.out.println(users);
            return Results.success("查询成功", users); // 封装结果并返回

        }
        else {
            users = userService.getAllUser(); // 如果没有提供查询参数，则调用原有的方法
            System.out.println(users);

            return Results.success("查询成功", users); // 封装结果并返回

        }
    }
    @GetMapping("/deleteUserByName")
    @ResponseBody
    public Results deleteUserByName(@RequestParam(value = "username" ,required = false) String username) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        if(username==null){
            System.out.println("用户名为空");
            return Results.fail("用户名为空");
        }
        System.out.println(username);
        boolean result = userService.deleteUserByName(username);
        if(result){
            return Results.success("成功删除！");
        }else{
            return Results.fail("删除失败");
        }
    }
    @GetMapping("/isExistByName")
    @ResponseBody
    public Results isExistByName(@RequestParam(value = "username" ,required = false) String username) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        if(username==null){
            System.out.println("用户名为空");
            return Results.fail("用户名为空");
        }
        System.out.println(username);
        User user = userService.isExistByName(username);
        if(user!=null){
            return Results.success("用户名存在");
        }else{
            return Results.fail("用户名不存在");
        }
    }
    @GetMapping("/isExistByEmail")
    @ResponseBody
    public Results isExistByEmail(@RequestParam(value = "email" ,required = false) String email) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        if(email==null){
            System.out.println("邮箱为空");
            return Results.fail("邮箱为空");
        }
        System.out.println(email);
        User user = userService.isExistByEmail(email);
        if(user!=null){
            return Results.success("邮箱已存在");
        }else{
            return Results.fail("邮箱不存在");
        }
    }
    @PostMapping("/updateUser")
    @ResponseBody
    public Results updateUser(@RequestBody Map<String, String> formData) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        User u = new User();
        String username = formData.get("number");
        String password = formData.get("pwd");
        String email = formData.get("email");
        String name = formData.get("name");
        if(formData.get("area")!=null&& !formData.get("area").isEmpty()){
            String role = formData.get("area").equals("管理员")?"admin":"guest";
            u.setRole(role);
        }
        if(username!=null&& !username.isEmpty()){
            u.setUserName(username);
        }
        if(password!=null&& !password.isEmpty()){
            u.setPassword(password);
        }
        if(email!=null&& !email.isEmpty()){
            u.setEmail(email);
        }
//        System.out.println(u);
        boolean result = userService.updateUser(u,name);

//        updateUser()
        return Results.success("查询成功");

    }
    @PostMapping("/addUser")
    @ResponseBody
    public Results addUser(@RequestBody Map<String, String> formData) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        User u = new User();
        String username = formData.get("number");
        String password = formData.get("pwd");
        String email = formData.get("email");
        String role = formData.get("area").equals("管理员")?"admin":"guest";
        u.setRole(role);
        if(username!=null&& !username.isEmpty()){
            u.setUserName(username);
        }
        if(password!=null&& !password.isEmpty()){
            u.setPassword(password);
        }
        if(email!=null&& !email.isEmpty()){
            u.setEmail(email);
        }
//        System.out.println(u);
        boolean result = userService.addUser(u);
        return Results.success("查询成功");

    }
    @GetMapping("/getAllLoginUser")
    @ResponseBody
    public Results getAllLoginUser(@RequestParam(value = "time" ,required = false) String time,
                                   @RequestParam(value = "number", required = false) String number) {
        List<User> users ;
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        if ( number != null && !number.isEmpty() || time != null && !time.isEmpty()) {
            users = userService.getAllUserByTimeAndNumber(time,number); // 如果没有提供查询参数，则调用原有的方法
            System.out.println(users);
            return Results.success("查询成功", users); // 封装结果并返回

        }
        else {
            users = userService.getAllLoginUser(); // 如果没有提供查询参数，则调用原有的方法
            System.out.println(users);

            return Results.success("查询成功", users); // 封装结果并返回

        }
    }
    @PostMapping("/updatename")
    @ResponseBody
    public Results updatename(@RequestBody Map<String, String> formData,HttpSession httpSession) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
//        User u = new User();
        String username = formData.get("username");
        System.out.println(username);
        String oldUserName = (String) httpSession.getAttribute("username");
//        System.out.println(oldUserName);
        if(oldUserName==null){
            return Results.fail("当前没有用户登录");
        }
        boolean result = userService.updateUsername(username,oldUserName);
        if(result){
            httpSession.removeAttribute("username");
            httpSession.setAttribute("username",username);
            return Results.success("更新成功");
        }
//        updateUser()
        return Results.fail("更新失败");

    }
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public Results getUserInfo(HttpSession session){
//        System.out.println(username);
        String username = (String)session.getAttribute("username");
        User u = userService.getUserByName(username);
        Results results = null;
        if (u != null){
            results = Results.success("查找成功",u);
        }else{
            results = Results.fail("未查找到该用户");
        }
        return results;
    }
    @PostMapping("/updatepassword")
    @ResponseBody
    public Results updatepassword(@RequestBody Map<String, String> formData,HttpSession httpSession) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
//        User u = new User();
        String password = formData.get("password");
        System.out.println(password);
        String oldUserName = (String) httpSession.getAttribute("username");
        System.out.println(oldUserName);
        if(oldUserName==null){
            return Results.fail("当前没有用户登录");
        }
        boolean result = userService.updatePassword(password,oldUserName);
        if(result){
            return Results.success("更新成功");

        }
//        updateUser()
        return Results.fail("更新失败");

    }
    @PostMapping("/updateemail")
    @ResponseBody
    public Results updateemail(@RequestBody Map<String, String> formData,HttpSession httpSession) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
//        User u = new User();
        String email = formData.get("email");
        System.out.println(email);
        String oldUserName = (String) httpSession.getAttribute("username");
        System.out.println(oldUserName);
        if(oldUserName==null){
            return Results.fail("当前没有用户登录");
        }
        boolean result = userService.updateemail(email,oldUserName);
        if(result){
            httpSession.removeAttribute("email");
            httpSession.setAttribute("email",email);
            return Results.success("更新成功");

        }
//        updateUser()
        return Results.fail("更新失败");

    }
    @RequestMapping("/getOutTime")
    @ResponseBody
    public Results getOutTime(HttpSession session){
//        System.out.println(username);
        Results results = null;
        String username = (String)session.getAttribute("username");
        User u = userService.getUserByName(username);
        if(u==null){
            results = Results.fail("当前没有用户信息");
        }
        if (u != null){
            results = Results.success("查找成功",u.getOutTime());
        }else{
            results = Results.fail("未查找到该用户");
        }
        return results;
    }
    @RequestMapping("/isUserLogin")
    @ResponseBody
    public Results isUserLogin(HttpSession session){
//        System.out.println(username);
        String username = (String)session.getAttribute("username");
        String role = (String) session.getAttribute("role");
        System.out.println(role);
        System.out.println("判断用户是否登录");
        System.out.println(username);
        if (username != null && role!=null){
            return Results.success("查找成功",role);
        }
        else{
            return Results.fail("未查找到该用户");
        }
    }

    @PostMapping("/updatetimeout")
    @ResponseBody
    public Results updatetimeout(@RequestBody Map<String, String> formData,HttpSession httpSession) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
//        User u = new User();
        String timeout = formData.get("passtime");
        System.out.println(timeout);
        String oldUserName = (String) httpSession.getAttribute("username");
        System.out.println(oldUserName);
        if(oldUserName==null){
            return Results.fail("当前没有用户登录");
        }
        boolean result = userService.updateTimeout(timeout,oldUserName);
        if(result){
            return Results.success("更新成功");

        }
        return Results.fail("更新失败");

    }

}