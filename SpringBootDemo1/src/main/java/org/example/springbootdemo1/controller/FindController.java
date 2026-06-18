package org.example.springbootdemo1.controller;


import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.pojo.VerificationCode;
import org.example.springbootdemo1.repository.VerificationCodeRepository;
import org.example.springbootdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/resetPwd")
public class FindController {
    //依赖注入
    UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @RequestMapping("/checkUser")
    @ResponseBody
    public Results checkUser(
            @RequestParam("email") String email,
            @RequestParam("verificationCode") String verificationCode,
            @RequestParam("password") String password)
    {
        Results results = null;
        VerificationCode storedVerificationCode = verificationCodeRepository.findByEmail(email);
        if (storedVerificationCode == null || storedVerificationCode.isExpired()||storedVerificationCode.isUsed()
        ) {
            // 验证码不存在或已过期
            results = Results.fail("验证码不存在或已过期,请重新发送");
            return results;
        }
        // 比较验证码是否匹配
        boolean isSimilar = storedVerificationCode.getCode().equals(verificationCode);
        if(!isSimilar){
            results = Results.fail("验证码不正确");
            return results;
        }

        boolean b = userService.updatepwd(email,password);
        if (b){
            results = Results.success("修改成功");
            storedVerificationCode.setUsed(true);
            verificationCodeRepository.save(storedVerificationCode);
//            VerificationCode storedVerificationCode2 = verificationCodeRepository.findByEmail(email);
//
//            System.out.println(storedVerificationCode2);
        }else{
            results = Results.fail("修改失败");
        }
        return results;
    }


}
