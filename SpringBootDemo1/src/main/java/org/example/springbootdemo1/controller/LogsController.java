package org.example.springbootdemo1.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.Logs;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.pojo.VerificationCode;
import org.example.springbootdemo1.pojo.userLogs;
import org.example.springbootdemo1.repository.VerificationCodeRepository;
import org.example.springbootdemo1.service.LogsService;
import org.example.springbootdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;
@CrossOrigin
@Controller
@RequestMapping("/logs")
@SessionAttributes(value = "user")
public class LogsController {
    LogsService logsService;

    @Autowired
    public void setLogsService(LogsService logsService){
        this.logsService = logsService;
    }

    @PostMapping("/addLog")
    @ResponseBody
    public Results addLog(@RequestBody Logs logsForm,HttpSession session) {
        System.out.println(logsForm);

        Results results = new Results();
        String username = (String) session.getAttribute("username");
        System.out.println("用户名为："+username);

        if(username!=null){
            logsForm.setUserName(username);
//            System.out.println(logsForm);
            boolean b = logsService.addLog(logsForm);
            if (b){
                results = Results.success("登记成功!");
            }else {
                results = Results.fail("登记失败");
            }
        }else if(username==null){
            results = Results.fail("登录信息超时，请重新登录");

        }

        return results;
    }

    @GetMapping("/deleteLog")
    @ResponseBody
    public Results deleteLog(@RequestParam(value = "type", required = false) String type,
                                 @RequestParam(value = "time", required = false) String time,
                             @RequestParam(value = "title",required = false) String title) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        System.out.println(time);
        if (time != null && !time.isEmpty() || type != null && !type.isEmpty()||title!=null&&!title.isEmpty()) {
            boolean result = logsService.deleteLog(time, type,title);
            return Results.success("删除成功"); // 封装结果并返回
        }
//        System.out.println(logs);
        return Results.fail("删除失败"); // 封装结果并返回

    }
    @GetMapping("/deleteLogByName")
    @ResponseBody
    public Results deleteLogByName(@RequestParam(value = "number", required = false) String name,
                            @RequestParam(value = "type", required = false) String type,
                             @RequestParam(value = "time", required = false) String time,
                             @RequestParam(value = "title",required = false) String title) {
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        System.out.println(time);
        if (name!= null&&time != null && !time.isEmpty() || type != null && !type.isEmpty()||title!=null&&!title.isEmpty()) {
            boolean result = logsService.deleteLogByName(name,time, type,title);
            return Results.success("删除成功"); // 封装结果并返回
        }
//        System.out.println(logs);
        return Results.fail("删除失败"); // 封装结果并返回

    }

    @GetMapping("/getAllLog")
    @ResponseBody
    public Results getAllLog(@RequestParam(value = "time", required = false) String time,
                             @RequestParam(value = "number", required = false) String number) {
        List<Logs> logs;
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        if (time != null && !time.isEmpty() || number != null && !number.isEmpty()) {
            logs = logsService.getAllLogByTimeAndNumber(time, number);
        } else {
            logs = logsService.getAllLog(); // 如果没有提供查询参数，则调用原有的方法
        }
        System.out.println(logs);
        return Results.success("查询成功", logs); // 封装结果并返回
    }

    @PostMapping("/addUserLog")
    @ResponseBody
    public Results addUserLog(@RequestBody userLogs userLogsForm, HttpServletRequest request, HttpSession session) {
        System.out.println(userLogsForm);

        Results results = new Results();

        String username = (String) session.getAttribute("username");
        System.out.println("用户名为：" + username);

        if (username != null) {
            userLogsForm.setUsername(username);
            boolean b = logsService.addUserLog(userLogsForm);
            if (b) {
                results = Results.success("登记成功!");
            } else {
                results = Results.fail("登记失败");
            }
        } else if (username == null) {
            results = Results.fail("登录信息超时，请重新登录");

        }
        return results;

    }
    @GetMapping("/getAllUserLog")
    @ResponseBody
    public Results getAllUserLog(@RequestParam(value = "time", required = false) String time,
                                 @RequestParam(value = "type", required = false) String type) {
        List<userLogs> logs;
        // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
        if (time != null && !time.isEmpty() || type != null && !type.isEmpty()) {
            logs = logsService.getAllUserLogByTimeAndNumber(time, type);
        } else {
            logs = logsService.getAllUserLog(); // 如果没有提供查询参数，则调用原有的方法
        }
        System.out.println(logs);
        return Results.success("查询成功", logs); // 封装结果并返回
    }

    @GetMapping("/getLogByName")
    @ResponseBody
    public Results getLogByName(@RequestParam(value = "time" ,required = false) String time,
                                @RequestParam(value = "type", required = false) String type,HttpSession httpSession) {
        if(httpSession!=null) {
            User user = (User)httpSession.getAttribute("user");
            String username = (String) httpSession.getAttribute("username");
            System.out.println(username);
            List<Logs> logs ;
            // 检查time和number是否为空，然后调用服务层的方法（这里假设服务层方法已经修改以接受这些参数）
            if (time != null && !time.isEmpty() || type != null && !type.isEmpty()) {
                logs = logsService.getLogByTimeAndType(time, type,username);
            } else {
                logs = logsService.getLog(username); // 如果没有提供查询参数，则调用原有的方法
            }
            System.out.println(logs);
            return Results.success("查询成功", logs); // 封装结果并返回

        }else {
            return Results.fail("当前没有用户登录");
        }


    }

}
