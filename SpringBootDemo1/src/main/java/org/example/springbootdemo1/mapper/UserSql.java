package org.example.springbootdemo1.mapper;

import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.example.springbootdemo1.pojo.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UserSql {
    public String getUserByName(final String userName) {
        return new SQL() {
            {
                SELECT("*");
                FROM("users");
                if (userName != null) {
                    WHERE("userName = #{userName}");
                }
            }
        }.toString();
    }
    public String getAllUserByRole(String role,String number){
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT username, createdAt, role FROM users");

        boolean hasWhere = false;
        System.out.println(role+number);
        if (role != null && !role.equals("null")) {
            if(!role.isEmpty()){
                if (hasWhere) {
                    sql.append(" AND ");
                } else {
                    sql.append(" WHERE ");
                    hasWhere = true;
                }
                sql.append("role LIKE '%").append(role).append("%'");
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
                sql.append("username = '").append(number).append("'");
            }
        }
        System.out.println(sql);
        return sql.toString();
    }
    public String getAllUser(){
        return new SQL() {
            {
                SELECT("username, createdAt, role");
                FROM("users");
            }
        }.toString();
    }
    public String addUser(final User user) {
        return new SQL() {
            {
                INSERT_INTO("users");
                if (user.getUserName() != null) {
                    VALUES("username", "#{userName}");
                }
                if (user.getPassword() != null) {
                    VALUES("password", "#{password}");
                }
                if(user.getRole()!=null){
                    VALUES("role","#{role}");
                }
                if(user.getEmail()!=null){
                    VALUES("email","#{email}");
                }

            }
        }.toString();
    }
    public String updatepwd(final String email, final String password) {
        return new SQL() {
            {
                UPDATE("users");
                if (password != null) {
                    SET("password = #{password}");
                }
                WHERE("email = #{email}");
            }
        }.toString();
    }
    public String updateTimeout(final String timeout,final String oldUserName) {
        return new SQL() {
            {
                UPDATE("users");
                if (timeout != null) {
                    SET("outTime = #{timeout}");
                }
                WHERE("username = #{oldUserName}");
            }
        }.toString();
    }
    public String updateUser(final User user,final String name) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE users SET ");
        boolean isFirstSet = true;

        if (user.getPassword() != null) {
            if (!isFirstSet) sb.append(", ");
            sb.append("password = '").append(user.getPassword()).append("'");
            isFirstSet = false;
        }

        if (user.getUserName() != null) {
            if (!isFirstSet) sb.append(", ");
            sb.append("username = '").append(user.getUserName()).append("'");
            isFirstSet = false;
        }

        if (user.getEmail() != null) {
            if (!isFirstSet) sb.append(", ");
            sb.append("email = '").append(user.getEmail()).append("'");
            isFirstSet = false;
        }

        if (user.getRole() != null) {
            if (!isFirstSet) sb.append(", ");
            sb.append("role = '").append(user.getRole()).append("'");
        }

        // 确保至少有一个 SET 语句被执行
        sb.append(" WHERE username = '").append(name).append("'");
        System.out.println(sb);

        return sb.toString();
    }
    public String updateUsername(final String username,final String oldUserName) {
        return new SQL() {
            {
                UPDATE("users");
                if (username != null) {
                    SET("username = #{username}");
                }
                WHERE("username = #{oldUserName}");
            }
        }.toString();
    }
    public String updatePassword(final String password,final String oldUserName) {
        return new SQL() {
            {
                UPDATE("users");
                if (password != null) {
                    SET("password = #{password}");
                }
                WHERE("username = #{oldUserName}");
            }
        }.toString();
    }
    public String updateemail(final String email,final String oldUserName) {
        return new SQL() {
            {
                UPDATE("users");
                if (email != null) {
                    SET("email = #{email}");
                }
                WHERE("username = #{oldUserName}");
            }
        }.toString();
    }

    public String deleteUserByName(final String username){
        return new SQL() {
            {
                DELETE_FROM("users");
                if (username != null) {
                    WHERE( "username = #{username}");
                }
            }
        }.toString();
    }
    public String isExistByName(final String username){
        return new SQL() {
            {
                SELECT("*");
                FROM("users");
                if (username != null) {
                    WHERE( "username = #{username}");
                }
            }
        }.toString();
    }
    public String isExistByEmail(final String email){
        return new SQL() {
            {
                SELECT("*");
                FROM("users");
                if (email != null) {
                    WHERE( "email = #{email}");
                }
            }
        }.toString();
    }

    public String logUserLogin(final String username){
        return new SQL(){
            {
                UPDATE("users");
                SET("loginTime = NOW(),isLogin =1");
                WHERE("username = #{username}");
            }
        }.toString();

    }


    public String recordUserLogout(String username){
        return new SQL(){
            {
                UPDATE("users");
                SET("logoutTime = NOW()");
                WHERE("username = #{username}");
            }
        }.toString();

    }

    public String getAllLoginUser(){
        return new SQL() {
            {
                SELECT("username, loginTime, logoutTime");
                FROM("users");
                WHERE("isLogin = 1");
            }
        }.toString();
    }
    public String getAllUserByTimeAndNumber(String time, String number) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT username, loginTime, logoutTime FROM users WHERE isLogin = 1");

        List<String> conditions = new ArrayList<>();
        if (time != null && !time.equals("null")) {
            // 定义日期格式
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // 将字符串解析为LocalDate对象
            LocalDate dateObj = LocalDate.parse(time, formatter);

            // 在日期对象上加上一天
            LocalDate nextDay = dateObj.plusDays(1);

            // 将LocalDate对象转换回字符串
            String nextDayStr = nextDay.format(formatter);

            System.out.println(nextDayStr); // 输出: 2024-07-07
            // 使用括号将两个时间条件括起来，并保留占位符的可能性（但这里仍用字符串拼接）
            conditions.add("(loginTime >= '" + time + " 00:00:00' AND loginTime < '" + nextDayStr + " 00:00:00' OR logoutTime >= '" + time + " 00:00:00' AND logoutTime < '" + nextDayStr + " 00:00:00')");
        }
        // 如果之前已经构建了条件，我们需要将它们添加到whereClause中
        String whereClause = "";
        if (!conditions.isEmpty()) {
            whereClause = " AND " + String.join(" ", conditions); // 注意：这里我们不再使用OR，因为条件已经在括号内了
        }

        // 处理username（或number）条件，应该始终添加到最终的whereClause中
        if (number != null) {
//            if (!whereClause.isEmpty()) {
            whereClause += " AND ";

            whereClause += "username = '" + number + "'"; // 这里同样有SQL注入风险
        }

        // 如果构建了whereClause，则将其添加到SQL语句中
        if (!whereClause.isEmpty()) {
            sql.append(whereClause);
        }

        String finalSql = sql.toString();
        System.out.println(finalSql); // 打印构建的SQL语句
        return finalSql;
    }



}
