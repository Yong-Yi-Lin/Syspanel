package org.example.springbootdemo1.controller;

import jakarta.servlet.http.HttpSession;
import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.MonitorSettings;
import org.example.springbootdemo1.pojo.SystemInfo;
import org.example.springbootdemo1.pojo.SystemLog;
import org.example.springbootdemo1.service.SystemLogService;
import org.example.springbootdemo1.service.GetSystemInfos;
import org.example.springbootdemo1.service.VerificationCodeService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/warn")
public class WarnController {
//    SystemGetInfo systemGetInfo = new SystemGetInfo();
    GetSystemInfos systemInfos = new GetSystemInfos();

    SystemLog systemLog_cpu,systemLog_memory;

    @Autowired
    public void setSystemLog(SystemLog systemLog) {
        this.systemLog_cpu = systemLog;
        this.systemLog_memory = systemLog;
    }

    SystemLogService systemLogService;

    int i = 0;
    double allCpuUsage = 0, allMemoryUsage = 0;
    double averageCpuUsage = 0 ,averageMemoryUsage = 0;
    boolean a = false,b = false,sendEmail = false;
    @Autowired
    private VerificationCodeService verificationCodeService;

    @Autowired
    public void setSystemLogService(SystemLogService systemLogService) {
        this.systemLogService = systemLogService;
    }

    int number,cpuWarningValue,cpuDangerValue,memoryWarningValue,memoryDangerValue;

    @PostMapping("/warning_handling")
    @ResponseBody
    public Results warning_handle(@RequestBody MonitorSettings monitorSettings, HttpSession httpSession) throws JSONException {
//        System.out.println("接收表单数据为："+monitorSettings);
        System.out.println(i);

        Results results = null;

        number = monitorSettings.getNumber();
        cpuWarningValue = monitorSettings.getCpuWarningValue();
        cpuDangerValue = monitorSettings.getCpuDangerValue();
        memoryWarningValue = monitorSettings.getMemoryWarningValue();
        memoryDangerValue = monitorSettings.getMemoryDangerValue();
        String email = (String) httpSession.getAttribute("email");
        System.out.println(email);
        //解析表单数据
        if (cpuWarningValue!=0 && cpuDangerValue!=0 && memoryWarningValue !=0 && memoryDangerValue !=0 ) {
            monitorSettings.setCpuWarningValue(cpuWarningValue);
            monitorSettings.setCpuDangerValue(cpuDangerValue);
            monitorSettings.setMemoryWarningValue(memoryWarningValue);
            monitorSettings.setMemoryDangerValue(memoryDangerValue);

            SystemInfo systemInfo = systemInfos.getSystemInfo();
            double cpuUtilization = systemInfo.getCpuUtilization();
            double memoryUtilization = systemInfo.getMemoryUtilization();

            if (i < 10) {
                i++;
                allCpuUsage = allCpuUsage + cpuUtilization;
                allMemoryUsage = allMemoryUsage + memoryUtilization;
            } else {
                averageCpuUsage = allCpuUsage/i;
                averageMemoryUsage = allMemoryUsage/i;
                System.out.println("平均cpu利用率："+averageCpuUsage+"平均内存利用率："+averageMemoryUsage);
                i = 0;
                allCpuUsage = 0;
                allMemoryUsage = 0;

                if (averageCpuUsage >= cpuWarningValue && averageCpuUsage < cpuDangerValue) {
                    systemLog_cpu.setWarnType("警告");
                    systemLog_cpu.setWarnTitle("cpu利用率警告");
                    systemLog_cpu.setWarnContact("cpu利用率超过警告值");
                    a = systemLogService.addLog(systemLog_cpu);

                    Results results1 = verificationCodeService.sendVerificationCodetoWarn(email,"警告","cpu利用率超过警告值");
                    System.out.println(results1.getMessage());
                    if (results1.getMessage().equals("发送成功！")){
                        sendEmail = true;
                    }

                } else if (averageCpuUsage >= cpuDangerValue){
                    systemLog_cpu.setWarnType("危险");
                    systemLog_cpu.setWarnTitle("cpu利用率危险");
                    systemLog_cpu.setWarnContact("cpu利用率超过危险值");
                    a = systemLogService.addLog(systemLog_cpu);
                    Results results1 = verificationCodeService.sendVerificationCodetoWarn(email,"危险","cpu利用率超过危险值");
                    System.out.println(results1.getMessage());
                    if (results1.getMessage().equals("发送成功！")){
                        sendEmail = true;
                    }
                }

                if (averageMemoryUsage >= memoryWarningValue && averageMemoryUsage < memoryDangerValue) {
                    systemLog_memory.setWarnType("警告");
                    systemLog_memory.setWarnTitle("内存利用率警告");
                    systemLog_memory.setWarnContact("内存利用率超过警告值");
                    b = systemLogService.addLog(systemLog_memory);
                    Results results1 = verificationCodeService.sendVerificationCodetoWarn(email,"警告","内存利用率超过警告值");
                    System.out.println(results1.getMessage());
                    if (results1.getMessage().equals("发送成功！")){
                        sendEmail = true;
                    }

                } else if (averageMemoryUsage>=memoryDangerValue){
                    systemLog_memory.setWarnType("危险");
                    systemLog_memory.setWarnTitle("内存利用率危险");
                    systemLog_memory.setWarnContact("内存利用率超过危险值");
                    b = systemLogService.addLog(systemLog_memory);
                    Results results1 = verificationCodeService.sendVerificationCodetoWarn(email,"危险","内存利用率超过危险值");
                    System.out.println(results1.getMessage());
                    if (results1.getMessage().equals("发送成功！")){
                        sendEmail = true;
                    }
                }

            }
            if (a || b){
                String emailResult = null;
                if (sendEmail){
                    emailResult = "已发送预警通知";
                }else {
                    emailResult = "预警通知失败";
                }
                results = Results.success("添加日志成功,"+emailResult);
                averageCpuUsage = 0;
                averageMemoryUsage = 0;
                a = false;
                b=false;
                sendEmail=false;
                deleteNum(number);
//            }else if (a){
//                results = Results.fail("添加成功cpu日志"+i);
//                averageCpuUsage = 0;
//                deleteNum(number);
//            }else if(b){
//                results = Results.fail("添加成功内存日志"+i);
//                averageMemoryUsage = 0;
//                deleteNum(number);
            }else{
                if(i >= 10){
                    results = Results.fail("请求次数"+i+"预警正常");
                }else {
                    results = Results.fail("请求次数" + i);
                }
            }
        }else {
            results = Results.fail("请求失败，非法阈值");
        }
        return results;
    }

    @RequestMapping("/warning_Info")
    @ResponseBody
    public Results getWarningInfo(@RequestParam(value = "time", required = false) String time,
                                  @RequestParam(value = "type", required = false) String type){

        List<SystemLog> systemLogs;
        if (time != null && !time.isEmpty() || type != null && !type.isEmpty()) {
            systemLogs = systemLogService.getSystemLogsByTimeAndNumber(time, type);
        } else {
            systemLogs = systemLogService.getSystemLogs(); // 如果没有提供查询参数，则调用原有的方法
        }

        Results results = null;
        if (systemLogs!=null){
            results = Results.success("警告日志获取成功！",systemLogs);
        }else {
            results = Results.fail("警告日志获取失败");
        }
        return results;
    }

    //写一个查询数量的方法
    public boolean deleteNum(int number){
        boolean isDelete = false;
        int num = systemLogService.selectNumber();
        if (num>=number){
            //清空表的数据
            isDelete = systemLogService.deleteLogs();
        }
        return isDelete;
    }

    @RequestMapping("/getWarn_Number")
    @ResponseBody
    public Results getWarn_Number(){
        int i = systemLogService.selectWarningNumber();
        Results results = null;

        if (i>=0){
            results = Results.success("警告数量获取成功",i);
        }else {
            results = Results.fail("警告数量获取失败");
        }
        return results;
    }

    @RequestMapping("/getDanger_Number")
    @ResponseBody
    public Results getDanger_Number(){
        int i = systemLogService.selectDangerNumber();
        Results results = null;

        if (i>=0){
            results = Results.success("危险数量获取成功",i);
        }else {
            results = Results.fail("危险数量获取失败");
        }
        return results;
    }
}
