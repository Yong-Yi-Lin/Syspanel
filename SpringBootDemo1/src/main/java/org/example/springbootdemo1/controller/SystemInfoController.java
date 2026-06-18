package org.example.springbootdemo1.controller;

import org.apache.ibatis.jdbc.SQL;
import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.SystemInfo;
import org.example.springbootdemo1.service.GetSystemInfos;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/SystemInfo")
public class SystemInfoController {
    GetSystemInfos sys_info = new GetSystemInfos();

    @RequestMapping("/getSystemInfo")
    @ResponseBody
    public Results getCpuInfo() throws JSONException {
        SystemInfo systemInfos = sys_info.getSystemInfo();

//        SystemInfo systemInfo = systemGetInfo.getSystemInfo();
//        List<Double> cpu_usage = cpu.getCpuUsage("cpu信息","cpu利用率");
//        double cpuUsage = cpu.getCpu_usage();
        Results results = null;

        if (systemInfos != null){
            results = Results.success("获取成功",systemInfos);
        }else {
            results = Results.fail("获取失败");
        }
        return results;
    }

    public String getSetting() {
        return new SQL() {
            {
                SELECT("*");
                FROM("monitorSetting");
            }
        }.toString();
    }
}