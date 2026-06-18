package org.example.springbootdemo1.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.VerificationCode;
import org.example.springbootdemo1.repository.VerificationCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class VerificationCodeService {
    private static final Logger logger = LoggerFactory.getLogger(VerificationCodeService.class);

    @Autowired
    private VerificationCodeRepository verificationCodeRepository;
    @Autowired
    private SimpleCodeManager simpleCodeManager;
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional
//    @Retryable(value = {DuplicateKeyException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public Results sendVerificationCode(String email, String code) {
        // ... 省略其他代码，如发送验证码到邮箱 ...
        Results results = new Results();
        results = simpleCodeManager.sendMail(email,code);
        if(results.getMessage().equals("发送失败")){
            return results;
        }

        // 保存验证码到数据库
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setEmail(email);
        verificationCode.setCode(code);
        // 设置验证码过期时间（例如，3分钟后）
        verificationCode.setUsed(false);
        verificationCode.setExpirationTime(LocalDateTime.now().plusMinutes(3));
        // 设置验证码创建时间（通常是当前时间）
        verificationCode.setCreatedAt(LocalDateTime.now());
        // 保存验证码到数据库

        try {
            VerificationCode existingCode = verificationCodeRepository.findByEmail(email);
            if (existingCode != null) {
                // 根据你的业务逻辑，选择更新或删除现有的验证码
                // 例如，删除旧的验证码
                System.out.println("删除之前的验证码！！！");
                logger.info("Deleting old verification code for email: {}", email);
                verificationCodeRepository.delete(existingCode);
                logger.info("Old verification code deleted successfully for email: {}", email);
            }
            // 现在可以安全地插入新的验证码
            verificationCodeRepository.save(verificationCode);
            return Results.success("验证码发送成功，请查看您的邮箱！");
        } catch (Exception e) {
            // 处理保存过程中可能发生的异常
            e.printStackTrace();
            // 在这里可以抛出自定义异常或返回错误状态等
//             throw new SomeCustomException("保存验证码失败");
            return Results.fail("保存验证码失败");
        }
//        verificationCodeRepository.save(verificationCode);
    }

    public Results sendVerificationCodetoWarn(String email,String type,String contact){
        Results results = new Results();
        return results = simpleCodeManager.sendMailtoSend(email,type,contact);
    }

}
