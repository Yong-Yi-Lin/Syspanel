package org.example.springbootdemo1.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.example.springbootdemo1.http.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Random;

@Service
public class SimpleCodeManager {

    @Autowired
    JavaMailSender mailSender;
    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public Results sendMail(String email,String code) {
        Results results = new Results();
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(email));
                mimeMessage.setFrom(new InternetAddress("18923677686@163.com"));
                mimeMessage.setText("您的验证码是"+code+"，请尽快验证！");
            }
        };
        try {
            this.mailSender.send(preparator);
            System.out.println("邮件发送成功！");
            results = Results.success("发送成功！！！！!");
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
            results = Results.fail("发送失败");

        }

        return results;
    }
    public Results sendMailtoSend(String email,String type,String contact) {
        Results results = new Results();

        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(email));
                mimeMessage.setFrom(new InternetAddress("18923677686@163.com"));
                mimeMessage.setText("类型："+type+"\n内容："+contact);
            }
        };
        try {
            this.mailSender.send(preparator);
            System.out.println("邮件发送成功！");
            results = Results.success("发送成功！");
        }
        catch (MailException ex) {
            System.err.println(ex.getMessage());
            results = Results.fail("发送失败!");
        }

        return results;
    }

}