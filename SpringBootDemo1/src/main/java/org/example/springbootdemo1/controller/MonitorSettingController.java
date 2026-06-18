package org.example.springbootdemo1.controller;

import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.MonitorSettings;
import org.example.springbootdemo1.service.MonitorSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/MonitorSetting")
public class MonitorSettingController {
   MonitorSettingService monitorSettingService;

   @Autowired
    public void setMonitorSettingService(MonitorSettingService monitorSettingService) {
        this.monitorSettingService = monitorSettingService;
    }

    @RequestMapping("/saveSetting")
    @ResponseBody
    public Results saveSetting(@RequestBody MonitorSettings monitorSettings){
       System.out.println(monitorSettings);

        boolean b = false;
        MonitorSettings setting = monitorSettingService.getSetting();
        if (setting != null){
            monitorSettingService.deleteSetting();
        }
        b = monitorSettingService.addSetting(monitorSettings);



//        SystemInfo systemInfo = systemGetInfo.getSystemInfo();
//        List<Double> cpu_usage = cpu.getCpuUsage("cpu信息","cpu利用率");
//        double cpuUsage = cpu.getCpu_usage();
        Results results = null;

        if (b){
            results = Results.success("保存监控设置成功");
        }else {
            results = Results.fail("保存监控设置失败");
        }
        return results;
    }

    @RequestMapping("/selectSetting")
    @ResponseBody
    public Results selectSetting(){
        MonitorSettings setting = monitorSettingService.getSetting();
        System.out.println(setting);
        Results results = null;

        if (setting != null){
            results = Results.success("查找设置成功",setting);
        }else {
            results = Results.fail("查找设置成功");
        }
        return results;
    }


}
