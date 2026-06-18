package org.example.springbootdemo1.service;

import org.example.springbootdemo1.mapper.LogsMapper;
import org.example.springbootdemo1.mapper.UserMapper;
import org.example.springbootdemo1.pojo.Logs;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.pojo.userLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LogsServiceImpl implements LogsService{
    LogsMapper logsMapper;
    @Autowired
    public void setLogsMapper(LogsMapper logsMapper) {
        this.logsMapper = logsMapper;
    }
    @Transactional
    @Override
    public boolean addLog(Logs logs){
        return logsMapper.addLogs(logs);
    }
    @Transactional
    @Override
    public boolean deleteLog(String time,String type,String title){
        return logsMapper.deleteLog(time,type,title);
    }
    @Transactional
    @Override
    public boolean deleteLogByName(String name,String time,String type,String title){
        return logsMapper.deleteLogByName(name,time,type,title);
    }

    @Transactional
    @Override
    public List<Logs> getAllLog(){
        return logsMapper.getAllLog();
    }
    @Transactional
    @Override
    public List<Logs> getAllLogByTimeAndNumber(String time,String number){
        return logsMapper.getAllLogByTimeAndNumber(time,number);
    }
    @Transactional
    @Override
    public boolean addUserLog(userLogs userLogs){return logsMapper.addUserLog(userLogs);}
    @Transactional
    @Override
    public List<userLogs> getAllUserLog(){return logsMapper.getAllUserLog();}
    @Transactional
    @Override
    public List<userLogs> getAllUserLogByTimeAndNumber(String time,String number){
        return logsMapper.getAllUserLogByTimeAndNumber(time,number);
    }
    @Transactional
    @Override
    public List<Logs> getLogByTimeAndType(String time,String type,String username){
        return logsMapper.getLogByTimeAndType(time,type,username);
    }
    @Transactional
    @Override
    public List<Logs> getLog(String username){
        return logsMapper.getLog(username);
    }


}
