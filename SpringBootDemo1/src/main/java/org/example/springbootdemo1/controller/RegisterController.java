package org.example.springbootdemo1.controller;

import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.pojo.VerificationCode;
import org.example.springbootdemo1.repository.VerificationCodeRepository;
import org.example.springbootdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;
    private final VerificationCodeRepository verificationCodeRepository;
    private final ResourceLoader resourceLoader;

    @Autowired
    public RegisterController(UserService userService, VerificationCodeRepository verificationCodeRepository, ResourceLoader resourceLoader) {
        this.userService = userService;
        this.verificationCodeRepository = verificationCodeRepository;
        this.resourceLoader = resourceLoader;
    }

    @PostMapping("/addUser")
    @ResponseBody
    public Results addUser(@ModelAttribute User userForm, @RequestParam("verificationCode") String verificationCode) {
        System.out.println(userForm);
        System.out.println(verificationCode);
        Results results = new Results();
        System.out.println(userService.getUserByName(userForm.getUserName()));
        if(userService.getUserByName(userForm.getUserName())!=null){
            System.out.println("用户名已存在！");
            results = Results.fail("用户名已存在，请重新输入！");
            return results;
        }

        VerificationCode existingCode = verificationCodeRepository.findByEmail(userForm.getEmail());
        System.out.println(existingCode);
        if(existingCode==null||existingCode.isExpired()||existingCode.isUsed()){
            System.out.println("验证码不存在或者已过期！");
            results = Results.fail("验证码不存在或者已过期！");
            return results;
        }
        if(!existingCode.getCode().equals(verificationCode)){
            System.out.println("验证码不正确！");
            results = Results.fail("验证码不正确!");
            return results;
        }

        boolean b = userService.addUser(userForm);


        if (b){
            results = Results.success("注册成功!");
            existingCode.setUsed(true);
            verificationCodeRepository.save(existingCode);

            System.out.println(existingCode);

            System.out.println("验证码已使用");
        }else {
            results = Results.fail("注册失败");
        }
        return results;
    }
}
