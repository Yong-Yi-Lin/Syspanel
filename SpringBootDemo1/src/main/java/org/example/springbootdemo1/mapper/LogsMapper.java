package org.example.springbootdemo1.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.example.springbootdemo1.pojo.Logs;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.pojo.userLogs;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface LogsMapper {
    @InsertProvider(type = LogsSql.class,method = "addLogs")
    boolean addLogs(Logs logs);
    @SelectProvider(type = LogsSql.class,method = "getAllLog")
    List<Logs> getAllLog();
    @SelectProvider(type = LogsSql.class,method = "getAllLogByTimeAndNumber")
    List<Logs> getAllLogByTimeAndNumber(String time,String number);
    @InsertProvider(type = LogsSql.class,method = "addUserLog")
    boolean addUserLog(userLogs userLogs);
    @SelectProvider(type = LogsSql.class,method = "getAllUserLog")
    List<userLogs> getAllUserLog();
    @SelectProvider(type = LogsSql.class,method = "getAllUserLogByTimeAndNumber")
    List<userLogs> getAllUserLogByTimeAndNumber(String time,String number);
    @SelectProvider(type = LogsSql.class,method = "getLogByTimeAndType")
    List<Logs> getLogByTimeAndType(String time,String type,String username);
    @SelectProvider(type = LogsSql.class,method = "getLog")
    List<Logs> getLog(String username);
    @DeleteProvider(type = LogsSql.class,method = "deleteLog")
    boolean deleteLog(String time,String type,String title);
    @DeleteProvider(type = LogsSql.class,method = "deleteLogByName")
    boolean deleteLogByName(String name,String time,String type,String title);

}
