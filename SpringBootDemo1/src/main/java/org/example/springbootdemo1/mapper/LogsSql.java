package org.example.springbootdemo1.mapper;

import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.example.springbootdemo1.pojo.Logs;
import org.example.springbootdemo1.pojo.User;
import org.example.springbootdemo1.pojo.userLogs;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogsSql {
    public String addLogs(final Logs logs) {
        return new SQL() {
            {
                INSERT_INTO("logs");
                if (logs.getTitle() != null) {
                    VALUES("title", "#{title}");
                }
                if(logs.getCreatedAt()!=null){
                    VALUES("createdAt","#{createdAt}");
                }
                if(logs.getLogInfo()!=null){
                    VALUES("logInfo","#{logInfo}");
                }
                if (logs.getContent() != null) {
                    VALUES("content", "#{content}");
                }
                if(!logs.isPublic()){
                    VALUES("isPublic","#{isPublic}");
                }
                if (logs.getUserName()!=null){
                    VALUES("userName","#{userName}");
                }
            }
        }.toString();
    }
    public String getAllLog(){
        return new SQL() {
            {
                SELECT("userName", "createdAt", "content", "logInfo");
                FROM("logs");
            }
        }.toString();
    }
    public String getAllLogByTimeAndNumber(String time,String number){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT userName, createdAt, content, logInfo FROM logs");

        boolean hasWhere = false;
        System.out.println(time+number);
        if (time != null && !time.equals("null")) {
            if(!time.isEmpty()){
                if (hasWhere) {
                    sql.append(" AND ");
                } else {
                    sql.append(" WHERE ");
                    hasWhere = true;
                }
                sql.append("createdAt LIKE '%").append(time).append("%'");
            }
        }

        if (number != null && !number.equals("null")) {
            if(!number.isEmpty()){
                if (hasWhere) {
                    sql.append(" AND ");
                } else {
                    sql.append(" WHERE ");
                    hasWhere = true;
                }
                sql.append("userName = '").append(number).append("'");
            }
        }
        System.out.println(sql);
        return sql.toString();
    }
    public String addUserLog(final userLogs logs) {
        return new SQL() {
            {
                INSERT_INTO("userLogs");
                if (logs.getUsername() != null) {
                    VALUES("username", "#{username}");
                }
                if(logs.getTime()!=null){
                    VALUES("time","#{time}");
                }
                if(logs.getType()!=null){
                    VALUES("type","#{type}");
                }
                if (logs.getSatisfaction() != null) {
                    VALUES("satisfaction", "#{satisfaction}");
                }
                if(logs.getInfo()!= null){
                    VALUES("info","#{info}");
                }
            }
        }.toString();
    }
    public String getLogByName(String name){
        return new SQL() {
            {
                SELECT("*");
                FROM("logs");
                if(name!=null){
                    WHERE("username = #{name}");
                }
            }
        }.toString();
    }

    public String updateLog(final User user,final String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE logs SET ");
        boolean isFirstSet = true;

        if (user.getUserName() != null) {
            sb.append("username = '").append(user.getUserName()).append("'");
            isFirstSet = false;
        }
        // 确保至少有一个 SET 语句被执行
        sb.append(" WHERE username = '").append(name).append("'");
        System.out.println(sb);

        return sb.toString();
    }
    public String getAllUserLog(){
        return new SQL() {
            {
                SELECT("type", "time", "info");
                FROM("userlogs");
            }
        }.toString();
    }
    public String getAllUserLogByTimeAndNumber(String time,String number){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT type,time, info FROM userlogs");

        boolean hasWhere = false;
        System.out.println(time+number);
        if (time != null && !time.equals("null")) {
            if(!time.isEmpty()){
                if (hasWhere) {
                    sql.append(" AND ");
                } else {
                    sql.append(" WHERE ");
                    hasWhere = true;
                }
                sql.append("time LIKE '%").append(time).append("%'");
            }
        }

        if (number != null && !number.equals("null")) {
            if(!number.isEmpty()){
                if (hasWhere) {
                    sql.append(" AND ");
                } else {
                    sql.append(" WHERE ");
                    hasWhere = true;
                }
                sql.append("type = '").append(number).append("'");
            }
        }
        System.out.println(sql);
        return sql.toString();
    }

    public String getLogByTimeAndType(String time,String type,String username){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT createdAt, content, logInfo FROM logs");

        boolean hasWhere = false;
        System.out.println(time+type);
        if (time != null && !time.equals("null")) {
            if(!time.isEmpty()){
                if (hasWhere) {
                    sql.append(" AND ");
                } else {
                    sql.append(" WHERE ");
                    hasWhere = true;
                }
                sql.append("createdAt LIKE '%").append(time).append("%'");
            }
        }

        if (type != null && !type.equals("null")) {
            if(!type.isEmpty()){
                if (hasWhere) {
                    sql.append(" AND ");
                } else {
                    sql.append(" WHERE ");
                    hasWhere = true;
                }
                sql.append("content = '").append(type).append("'");
            }
        }
        if (hasWhere) {
            sql.append(" AND ");
        } else {
            sql.append(" WHERE ");
        }
        sql.append("userName = '").append(username).append("'");
        System.out.println(sql);
        return sql.toString();
    }
    public String getLog(String username){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT createdAt, content, logInfo FROM logs");
        sql.append(" WHERE userName = '").append(username).append("'");
        System.out.println(sql);
        return sql.toString();
    }

    public String deleteLog(final String time, final String type, final String title) {
        // 使用 StringBuilder 来构建 SQL 语句，这样更容易添加条件
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("DELETE FROM logs WHERE ");

        // 使用一个标志来检查是否需要添加 WHERE 子句
        boolean needsWhere = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 使用LocalDate和DateTimeFormatter来解析字符串为LocalDate对象
        LocalDate date = LocalDate.parse(time, formatter);

        // 获取下一天的日期
        LocalDate nextDay = date.plusDays(1);

        // 将下一天的日期格式化回字符串
        String lastday = nextDay.format(formatter);
        if (type != null&&title != null&&time != null) {
            sqlBuilder.append("content = '").append(type).append("' ");
            sqlBuilder.append("AND logInfo = '").append(title).append("' ");
            sqlBuilder.append("AND createdAt >= '").append(time).append(" 00:00:00'");
//            AND createdAt >= '2024-08-03 00:00:00'
            sqlBuilder.append("AND createdAt < '").append(lastday).append(" 00:00:00'");
//            AND createdAt < '2024-08-04 00:00:00';
            needsWhere = true;
        }
        System.out.println(sqlBuilder.toString());
        return sqlBuilder.toString();

    }
    public String deleteLogByName(final String name,final String time, final String type, final String title) {
        // 使用 StringBuilder 来构建 SQL 语句，这样更容易添加条件
        StringBuilder sqlBuilder = new StringBuilder();

        sqlBuilder.append("DELETE FROM logs WHERE ");

        // 使用一个标志来检查是否需要添加 WHERE 子句
        boolean needsWhere = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // 使用LocalDate和DateTimeFormatter来解析字符串为LocalDate对象
        LocalDate date = LocalDate.parse(time, formatter);

        // 获取下一天的日期
        LocalDate nextDay = date.plusDays(1);

        // 将下一天的日期格式化回字符串
        String lastday = nextDay.format(formatter);
        if (type != null&&title != null&&time != null&&name!=null) {
            sqlBuilder.append("userName = '").append(name).append("' ");
            sqlBuilder.append("AND content = '").append(type).append("' ");
            sqlBuilder.append("AND logInfo = '").append(title).append("' ");
            sqlBuilder.append("AND createdAt >= '").append(time).append(" 00:00:00'");
//            AND createdAt >= '2024-08-03 00:00:00'
            sqlBuilder.append("AND createdAt < '").append(lastday).append(" 00:00:00'");
//            AND createdAt < '2024-08-04 00:00:00';
        }
        System.out.println(sqlBuilder.toString());
        return sqlBuilder.toString();

    }

}
