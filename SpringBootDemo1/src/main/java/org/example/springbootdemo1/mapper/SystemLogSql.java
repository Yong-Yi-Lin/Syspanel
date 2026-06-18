package org.example.springbootdemo1.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.example.springbootdemo1.pojo.SystemLog;
import org.example.springbootdemo1.pojo.User;

public class SystemLogSql {
    public String getSystemLogs() {
        return new SQL() {
            {
                SELECT("*");
                FROM("SystemLog");
            }
        }.toString();
    }
    public String getSystemLogsByTimeAndNumber(String time,String type) {
//        return new SQL() {
//            {
//                SELECT("*");
//                FROM("SystemLog");
//            }
//        }.toString();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM SystemLog");
        boolean hasWhere = false;
//        System.out.println(time+number);
        if (time != null && !time.equals("null")) {
            if(!time.isEmpty()){
                if (hasWhere) {
                    sql.append(" AND ");
                } else {
                    sql.append(" WHERE ");
                    hasWhere = true;
                }
                sql.append("logTime LIKE '%").append(time).append("%'");
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
                sql.append("warnType = '").append(type).append("'");
            }
        }
        System.out.println(sql);
        return sql.toString();
    }


    public String addLog(final SystemLog systemLog) {
        return new SQL() {
            {
                INSERT_INTO("SystemLog");
                if (systemLog.getWarnType() != null) {
                    VALUES("warnType", "#{warnType}");
                }
                if (systemLog.getWarnTitle() != null) {
                    VALUES("warnTitle", "#{warnTitle}");
                }
                if (systemLog.getWarnContact() != null) {
                    VALUES("warnContact", "#{warnContact}");
                }
            }
        }.toString();
    }

    public String selectWarningNumber(final String warnType) {
        return new SQL() {
            {
                SELECT("COUNT(*)");
                FROM("SystemLog");
                if (warnType != null){
                    WHERE("warnType = #{warnType}");
                }
            }
        }.toString();
    }

    public String selectDangerNumber(final String warnType) {
        return new SQL() {
            {
                SELECT("COUNT(*)");
                FROM("SystemLog");
                if (warnType != null){
                    WHERE("warnType = #{warnType}");
                }
            }
        }.toString();
    }

    public String selectNumber() {
        return new SQL() {
            {
                SELECT("COUNT(*)");
                FROM("SystemLog");
            }
        }.toString();
    }

    public String deleteLogs() {
        return new SQL() {
            {
                DELETE_FROM("SystemLog");
            }
        }.toString();
    }
}
