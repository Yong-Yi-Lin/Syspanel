package org.example.springbootdemo1.controller;

import org.example.springbootdemo1.http.Results;
import org.example.springbootdemo1.pojo.HostInfo;
import org.example.springbootdemo1.service.GetHostInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hostInfo")
public class HostInfoController {
    GetHostInfoService getHostInfoService;

    @Autowired
    public void setGetHostInfoService(GetHostInfoService getHostInfoService) {
        this.getHostInfoService = getHostInfoService;
    }

    @RequestMapping("/getHostInfo")
    @ResponseBody
    public Results getHostInfo(){
        HostInfo hostInfo = getHostInfoService.getHostInfo();
        System.out.println(hostInfo);
        Results results = null;
        if (hostInfo != null){
            results = Results.success("获取主机信息成功",hostInfo);
        }else {
            results = Results.fail("获取主机信息失败");
        }
        return results;
    }
}
