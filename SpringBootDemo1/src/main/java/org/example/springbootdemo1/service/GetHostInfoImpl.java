package org.example.springbootdemo1.service;

import org.example.springbootdemo1.pojo.HostInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Service
public class GetHostInfoImpl implements GetHostInfoService{
    HostInfo hostInfo;

    @Autowired
    public void setHostInfo(HostInfo hostInfo) {
        this.hostInfo = hostInfo;
    }

    @Override
    public HostInfo getHostInfo() {
        String hostName = null;
        String ipAddress = null;

        try {
            InetAddress address = InetAddress.getLocalHost();
            hostName = address.getHostName();
            ipAddress = address.getHostAddress();
//            System.out.println("主机名： " + address.getHostName());
//            System.out.println("IP地址： " + address.getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String osName = System.getProperty("os.name");
        String osVersion = System.getProperty("os.version");
        String osArch = System.getProperty("os.arch");

        int numberOfCores = Runtime.getRuntime().availableProcessors();
//        System.out.println("Number of CPU cores: " + numberOfCores);

        hostInfo.setHostName(hostName);
        hostInfo.setIpAddress(ipAddress);
        hostInfo.setOsName(osName);
        hostInfo.setOsVersion(osVersion);
        hostInfo.setOsArch(osArch);
        hostInfo.setCoresNumber(numberOfCores);

        return hostInfo;
    }
}
