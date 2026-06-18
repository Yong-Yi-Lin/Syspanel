package org.example.springbootdemo1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String toIndex(){
        System.out.println("indexController index method is called");
        return "index";//index.html
    }
    @RequestMapping("/user")
    public String toUser(){
        System.out.println("跳转到用户界面");
        return "user";//user.html
    }
    @RequestMapping("/register")
    public String toRegister(){
        System.out.println("跳转到注册页面");
        return "register";//register.html
    }
    @RequestMapping("/login")
    public String toLogin(){
        System.out.println("跳转到登录页面");
        return "login";
    }
    @RequestMapping("/adminPage")
    public String toAdminPage(){
        System.out.println("跳转到管理员页面");
        return "adminPage";//register.html

    }
    @RequestMapping("/userPage")
    public String toUserPage(){
        System.out.println("跳转到登录页面");
        return "userPage";//register.html

    }
    @RequestMapping("/find")
    public String toFind(){
        System.out.println("跳转到查找密码页面");
        return "find";
    }

    @RequestMapping("/memory")
    public String toMemory(){
        System.out.println("跳转到用户界面");
        return "memory";//memory1.html
    }

    @RequestMapping("/cpu")
    public String toCpu(){
        System.out.println("跳转到用户界面");
        return "cpu";//memory1.html
    }

    @RequestMapping("/SystemInfo")
    public String toSystemInfo(){
        System.out.println("跳转到用户界面");
        return "SystemInfo";//memory1.html
    }

    @RequestMapping("/allSys")
    public String toAllSys(){
        System.out.println("跳转到用户界面");
        return "allSys1";
    }

    @RequestMapping("/process")
    public String toProcessInfo(){
        System.out.println("跳转到用户界面");
        return "process";
    }
}
