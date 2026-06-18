package org.example.springbootdemo1.controller;

import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.pojo.VerificationCode;
import org.example.springbootdemo1.repository.VerificationCodeRepository;
import org.example.springbootdemo1.service.SimpleCodeManager;
import org.example.springbootdemo1.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/verification-code")
public class VerificationCodeController {

    @Autowired
    private VerificationCodeService verificationCodeService;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @PostMapping("/sendmail")
    public Results sendVerificationCode(@RequestParam String email) {
        Results results = new Results();
//        results = scm.sendMail(email);
        try {
            // 调用服务层发送验证码
            String code = generateRandomCode();
            results =  verificationCodeService.sendVerificationCode(email, code);

//            results = Results.success("发送成功！！！！!");
        } catch (Exception e) {
            // 处理异常，返回表示错误的Results对象
            results = Results.fail("发送失败");
        }
        return results;

    }

    // 辅助方法，生成随机验证码
    private String generateRandomCode() {
        // 实现生成随机验证码的逻辑...
        // 获取当前时间
        Date currentTime = new Date();
        // 将当前时间转换为long类型，作为种子
        long seed = currentTime.getTime();
        // 创建Random对象，并设置种子
        Random random = new Random(seed);
        // 生成一个100000到999999之间的随机数
        int randomNumber = random.nextInt(900000) + 100000;

        return String.valueOf(randomNumber);
    }

    @PostMapping("/checkIsExpired")
    @ResponseBody
    public Results checkIsExpired(@RequestParam String email) {
        Results results = null;
        System.out.println(email);
        if(email==null){
            results = Results.fail("邮箱为空");
            return results;
        }
        VerificationCode storedVerificationCode = verificationCodeRepository.findByEmail(email);
        if (storedVerificationCode == null || storedVerificationCode.isExpired() || storedVerificationCode.isUsed()) {
            // 验证码不存在或已过期
//            assert storedVerificationCode != null;
//            System.out.println(storedVerificationCode.isExpired());
//            System.out.println(storedVerificationCode.isUsed());
            results = Results.success("验证码不存在或已过期");
        }else{
            results = Results.fail("验证码还在有效期");
        }
        return results;
    }
}