//package org.example.springbootdemo1.utils;
//
//import redis.clients.jedis.Jedis;
//
//import java.util.Map;
//
//public class test01 {
//    public static void main(String[] args) {
//        // 连接到Redis服务器
//        Jedis jedis = new Jedis("localhost", 6379);
//        System.out.println("连接成功");
//
//        // 获取存储在Redis中的系统信息
//        String systemInfoKey = "system_info";
//
//        // 使用哈希映射来保存每一部分的数据
//        Map<String, String> systemInfo = jedis.hgetAll(systemInfoKey);
//
//        // 从哈希映射中取出数据
//        double cpuPercent = Double.parseDouble(systemInfo.get("cpu_percent"));
//        double memoryTotal = Double.parseDouble(systemInfo.get("memory_total"));
//        double memoryUsed = Double.parseDouble(systemInfo.get("memory_used"));
//        double memoryFree = Double.parseDouble(systemInfo.get("memory_free"));
//        double memoryPercent = Double.parseDouble(systemInfo.get("memory_percent"));
//        double diskTotal = Double.parseDouble(systemInfo.get("disk_total"));
//        double diskUsed = Double.parseDouble(systemInfo.get("disk_used"));
//        double diskPercent = Double.parseDouble(systemInfo.get("disk_percent"));
//        int totalProcesses = Integer.parseInt(systemInfo.get("total_processes"));
//        double networkRecv = Double.parseDouble(systemInfo.get("network_recv"));
//        double networkSent = Double.parseDouble(systemInfo.get("network_sent"));
//        long timestamp = Long.parseLong(systemInfo.get("timestamp"));
//
//        // 打印获取到的数据
//        System.out.println("CPU百分比： " + cpuPercent);
//        System.out.println("内存总量： " + memoryTotal);
//        System.out.println("内存使用量： " + memoryUsed);
//        System.out.println("内存剩余量： " + memoryFree);
//        System.out.println("内存使用百分比： " + memoryPercent);
//        System.out.println("磁盘总量： " + diskTotal);
//        System.out.println("磁盘使用量： " + diskUsed);
//        System.out.println("磁盘使用百分比： " + diskPercent);
//        System.out.println("总进程数： " + totalProcesses);
//        System.out.println("网络接收量： " + networkRecv);
//        System.out.println("网络发送量： " + networkSent);
//        System.out.println("时间戳： " + timestamp);
//
//        // 关闭连接
//        jedis.close();
//    }
//}
