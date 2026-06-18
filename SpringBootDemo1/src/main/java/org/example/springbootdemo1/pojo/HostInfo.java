package org.example.springbootdemo1.pojo;

import org.springframework.stereotype.Component;

@Component
public class HostInfo {
    private String hostName; //主机名
    private String osName; //操作系统名称
    private String osVersion; //操作系统版本
    private String osArch;//操作系统架构
    private String ipAddress;//IP地址
    private int coresNumber;//cpu核心数量


    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getCoresNumber() {
        return coresNumber;
    }

    public void setCoresNumber(int coresNumber) {
        this.coresNumber = coresNumber;
    }

    @Override
    public String toString() {
        return "HostInfo{" +
                "hostName='" + hostName + '\'' +
                ", osName='" + osName + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", osArch='" + osArch + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", coresNumber=" + coresNumber +
                '}';
    }
}
