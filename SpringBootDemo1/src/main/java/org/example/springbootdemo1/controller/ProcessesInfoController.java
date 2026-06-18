package org.example.springbootdemo1.controller;

import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.service.GetProcessesInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequestMapping("/process")
public class ProcessesInfoController {
    GetProcessesInfo getProcessesInfo = new GetProcessesInfo();


    //获取进程到前端展示
    @RequestMapping("/getProcessedInfo")
    @ResponseBody
    public Results getProcessedInfo() {
//        System.out.println("收到请求");
//        List<JSONObject> processInfo = getProcessesInfo.getProcessInfoFromRedis();
        String processInfoFromRedis = getProcessesInfo.getProcessInfoFromRedis();
//        System.out.println(processInfoFromRedis);
        Results results = null;
        if (processInfoFromRedis != null) {
            results = Results.success("获取成功", processInfoFromRedis);
        } else {
            results = Results.fail("获取失败");
        }
        return results;
    }

    //杀死进程
    @RequestMapping("/killProcess")
    @ResponseBody
    public Results killProcess(@RequestParam int pid) {
        // 执行杀死进程的命令
        // 在Windows上，使用"taskkill /PID pid /F"命令
        // 在Linux上，使用"kill -9 pid"命令
        System.out.println("pid"+pid);
        Results results = null;
        String os = System.getProperty("os.name").toLowerCase();
        String command = "";
        // 获取当前运行的Java虚拟机
        Runtime runtime = Runtime.getRuntime();
        try {
            if (os.contains("win")) {
                command = "taskkill /PID " + pid + " /F";
            } else if (os.contains("nix") || os.contains("nux")) {
                command = "kill -15 " + pid;
            } else {
                System.out.println("不支持的操作系统");
                results = Results.fail("不支持的操作系统");
                return results;
            }
            Process exec = runtime.exec(command);
            System.out.println(exec);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(exec.getInputStream(), StandardCharsets.UTF_8));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
            int exitValue = exec.waitFor();
            System.out.println("exitValue"+exitValue);


            if (exitValue == 0) {
                System.out.println("进程被成功杀死。");
                results = Results.success("进程被成功杀死");
            } else {
                System.out.println("进程没有被杀死，或者发生了错误。");
                results = Results.success("进程没有被杀死，或者发生了错误");
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return results;
    }
}
