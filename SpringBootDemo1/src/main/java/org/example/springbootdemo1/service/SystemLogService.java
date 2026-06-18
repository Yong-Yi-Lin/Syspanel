package org.example.springbootdemo1.service;

import org.example.springbootdemo1.pojo.SystemLog;

import java.util.List;

public interface SystemLogService {
    public List<SystemLog> getSystemLogs();
    public boolean addLog(SystemLog systemLog);
    public List<SystemLog> getSystemLogsByTimeAndNumber(String time,String type);

    public int selectNumber();

    public boolean deleteLogs();

    public int selectWarningNumber();

    public int selectDangerNumber();


}
