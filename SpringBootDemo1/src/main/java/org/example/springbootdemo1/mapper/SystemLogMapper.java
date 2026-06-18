package org.example.springbootdemo1.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.example.springbootdemo1.pojo.SystemLog;

import java.util.List;

@Mapper
public interface SystemLogMapper {
    @SelectProvider(type = SystemLogSql.class,method = "getSystemLogs")
    List<SystemLog> getSystemLogs();
    @InsertProvider(type = SystemLogSql.class,method = "addLog")
    boolean addLog(SystemLog systemLog);
    @SelectProvider(type = SystemLogSql.class,method = "getSystemLogsByTimeAndNumber")
    List<SystemLog> getSystemLogsByTimeAndNumber(String time,String type);

    @SelectProvider(type = SystemLogSql.class,method = "selectNumber")
    int selectNumber();

    @DeleteProvider(type = SystemLogSql.class,method = "deleteLogs")
    boolean deleteLogs();

    @SelectProvider(type = SystemLogSql.class,method = "selectWarningNumber")
    int selectWarningNumber();

    @SelectProvider(type = SystemLogSql.class,method = "selectDangerNumber")
    int selectDangerNumber();
}
