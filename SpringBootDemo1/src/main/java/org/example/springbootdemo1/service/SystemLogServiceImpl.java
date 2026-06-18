package org.example.springbootdemo1.service;

import org.example.springbootdemo1.mapper.SystemLogMapper;
import org.example.springbootdemo1.pojo.SystemLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemLogServiceImpl implements SystemLogService{
    SystemLogMapper systemLogMapper;

    @Autowired
    public void setSystemLogMapper(SystemLogMapper systemLogMapper) {
        this.systemLogMapper = systemLogMapper;
    }

    @Override
    public List<SystemLog> getSystemLogs() {
        return systemLogMapper.getSystemLogs();
    }
    @Override
    public List<SystemLog> getSystemLogsByTimeAndNumber(String time,String type){
        return systemLogMapper.getSystemLogsByTimeAndNumber(time,type);
    }

    @Override
    public int selectNumber() {
        return systemLogMapper.selectNumber();
    }

    @Override
    public boolean deleteLogs() {
        return systemLogMapper.deleteLogs();
    }

    @Override
    public int selectWarningNumber() {
        return systemLogMapper.selectWarningNumber();
    }

    @Override
    public int selectDangerNumber() {
        return systemLogMapper.selectDangerNumber();
    }

    @Override
    public boolean addLog(SystemLog systemLog) {
        return systemLogMapper.addLog(systemLog);
    }
}
