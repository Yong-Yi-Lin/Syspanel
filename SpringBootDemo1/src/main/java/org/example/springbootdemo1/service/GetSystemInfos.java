package org.example.springbootdemo1.service;

import org.example.springbootdemo1.pojo.SystemInfo;
import org.json.JSONException;
import redis.clients.jedis.Jedis;

import org.json.JSONObject;

public class GetSystemInfos {

    public SystemInfo getSystemInfo() throws JSONException {
        Jedis jedis = new Jedis("localhost", 6379);
        SystemInfo systemInfo = new SystemInfo();

        try {
            // 获取存储在Redis中的系统信息
            String systemInfoJson = jedis.get("system_info");

            // 如果找到了系统信息，则解析它
            if (systemInfoJson != null) {
                JSONObject systemInfoJsonObj = new JSONObject(systemInfoJson);
                systemInfo.setCpuUtilization(systemInfoJsonObj.getDouble("cpu_utilization"));
                systemInfo.setTotalMemory(systemInfoJsonObj.getDouble("total_memory"));
                systemInfo.setUsedMemory(systemInfoJsonObj.getDouble("used_memory"));
                systemInfo.setFreeMemory(systemInfoJsonObj.getDouble("free_memory"));
                systemInfo.setMemoryUtilization(systemInfoJsonObj.getDouble("memory_utilization"));
                systemInfo.setTotalDiskSpace(systemInfoJsonObj.getDouble("total_disk_space"));
                systemInfo.setUsedDiskSpace(systemInfoJsonObj.getDouble("used_disk_space"));
                systemInfo.setDiskUtilization(systemInfoJsonObj.getDouble("disk_utilization"));
                systemInfo.setTotalProcesses(systemInfoJsonObj.getInt("total_processes"));
                systemInfo.setNetworkDataReceived(systemInfoJsonObj.getDouble("network_data_received"));
                systemInfo.setNetworkDataSent(systemInfoJsonObj.getDouble("network_data_sent"));
                systemInfo.setCurrentTime(systemInfoJsonObj.getString("current_time"));
//                SystemInfoo systemInfo = parseSystemInfo(systemInfoJson);
                // 打印获取到的SystemInfo对象
//                System.out.println(systemInfo);

            } else {
                System.out.println("Redis中没有找到系统信息。");
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            // 关闭Jedis连接
            jedis.close();
        }
        return systemInfo;
    }
}
