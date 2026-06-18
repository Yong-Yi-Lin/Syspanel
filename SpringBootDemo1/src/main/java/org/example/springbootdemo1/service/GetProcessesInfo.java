package org.example.springbootdemo1.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import java.util.Iterator;


@Service
public class GetProcessesInfo {
//    private Jedis jedis;
//    Jedis jedis = new Jedis("localhost", 6379);
    private static final String HOST = "localhost"; // Redis服务器地址
    private static final int PORT = 6379; // Redis服务器端口
    private static final String ALL_PROCESSES_KEY = "all_processes"; // Redis中存储进程信息的键

    public String getProcessInfoFromRedis() {
        Jedis jedis = new Jedis(HOST, PORT);
        try {
            // 获取存储的所有进程信息的JSON字符串
            String processInfoJson = jedis.hget(ALL_PROCESSES_KEY, "data");

            // 将JSON字符串转换为JSONArray
            JSONArray processInfoArray = new JSONArray(processInfoJson);

            // 遍历JSONArray并打印每个进程的信息
            for (int i = 0; i < processInfoArray.length(); i++) {
                JSONObject processInfoObject = processInfoArray.getJSONObject(i);
//                System.out.println("PID: " + processInfoObject.getString("pid"));
//                System.out.println("Name: " + processInfoObject.getString("name"));
//                System.out.println("Username: " + processInfoObject.getString("username"));
//                System.out.println("CPU Usage: " + processInfoObject.getDouble("cpu") + "%");
//                System.out.println("Memory Usage: " + processInfoObject.getDouble("memory") + " MB");
//                System.out.println("statue"+processInfoObject.getString("statue"));
//                System.out.println();
            }

            if (processInfoJson!=null){
                return processInfoJson;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭Jedis连接
            if (jedis != null) {
                jedis.close();
            }
        }

        return null;
    }
}
