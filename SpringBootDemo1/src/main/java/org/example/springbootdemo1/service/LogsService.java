package org.example.springbootdemo1.service;

import com.mysql.cj.log.Log;
import org.example.springbootdemo1.pojo.Logs;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.pojo.userLogs;

import java.util.List;

public interface LogsService {
    public boolean addLog(Logs logs);
    public boolean deleteLog(String time,String type,String title);
    public boolean deleteLogByName(String name,String time,String type,String title);

    public List<Logs> getAllLog();
    public List<Logs> getAllLogByTimeAndNumber(String time,String number);

    public boolean addUserLog(userLogs userLogs);
    public List<userLogs> getAllUserLog();
    public List<userLogs> getAllUserLogByTimeAndNumber(String time,String type);

    public List<Logs> getLogByTimeAndType(String time,String type,String username);
    public List<Logs> getLog(String username);

}

