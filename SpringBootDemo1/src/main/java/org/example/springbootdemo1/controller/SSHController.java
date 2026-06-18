package org.example.springbootdemo1.controller;

import com.jcraft.jsch.Session;
import jakarta.servlet.http.HttpSession;
import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.SSHClient;
import org.example.springbootdemo1.service.SSHClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/ssh")
public class SSHController {
    SSHClientService sshClientService;

    @Autowired
    public void setService(SSHClientService service) {
        this.sshClientService = service;
    }

    @PostMapping("/getLogin")
    @ResponseBody
    public Results getLogin(@ModelAttribute SSHClient sshClientForm,HttpSession httpSession){
        System.out.println(sshClientForm);
        Results results;

        String host = sshClientForm.getHost();
        Integer port = sshClientForm.getPort();
        String username = sshClientForm.getUsername();
        String password = sshClientForm.getPassword();
//        String cmd = sshClientForm.getCmd();

        if (host==null && port==null && username==null && password == null){
            results = Results.fail("请填写完整的配置！");
        }
        Session session = sshClientService.login(sshClientForm);

        System.out.println("1:"+session);

        httpSession.setAttribute("远程连接会话",session);
        
        if (session.isConnected()){
            results = Results.success("连接成功");
            System.out.println("连接成功");
        }else {
            results = Results.fail("连接失败，请检查配置");
        }
        return results;
    }

    @PostMapping("/getSSH")
    @ResponseBody
    public Results getSSH(@ModelAttribute SSHClient sshClientForm){
        //查看接收请求的表单数据
        System.out.println(sshClientForm);
        Results results;

        String host = sshClientForm.getHost();
        Integer port = sshClientForm.getPort();
        String username = sshClientForm.getUsername();
        String password = sshClientForm.getPassword();
//        String cmd = sshClientForm.getCmd();

        if (host==null && port==null && username==null && password == null){
            results = Results.fail("请填写完整的配置！");
        }else {
            String backCmd = sshClientService.execCmd(sshClientForm);
            System.out.println(backCmd);
            if (!backCmd.equals("远程连接失败")){
                results = Results.success("执行命令",backCmd);
            }else {
                results = Results.fail(backCmd);
            }
        }
        return results;
    }

    @PostMapping("/disconnectSSH")
    @ResponseBody
    public Results disconnectSSH(@ModelAttribute SSHClient sshClientForm,HttpSession httpSession){
        System.out.println(sshClientForm);
        Results results;

//        String host = sshClientForm.getHost();
//        Integer port = sshClientForm.getPort();
//        String username = sshClientForm.getUsername();
//        String password = sshClientForm.getPassword();
//        String cmd = sshClientForm.getCmd();

//        if (host==null && port==null && username==null && password == null){
//            results = Results.fail("请填写完整的配置！");
//        }


//        Session session = sshClientService.login(sshClientForm);

        Session session = (Session) httpSession.getAttribute("远程连接会话");
        System.out.println("2:"+session);

        Boolean b = sshClientService.JschDisconnect(null, session);
        if (b){
            results = Results.success("断开连接成功");
            System.out.println("断开连接成功");
        }else {
            results = Results.fail("断开连接失败");
        }
        return results;
    }

}
