package org.example.springbootdemo1.mapper;

import org.apache.ibatis.annotations.*;
import org.example.springbootdemo1.pojo.Logs;
import org.example.springbootdemo1.pojo.User;

import java.util.List;

@Mapper
public interface UserMapper {
    @SelectProvider(type = UserSql.class,method = "getUserByName")
    User getUserByName(String userName);
    @InsertProvider(type = UserSql.class,method = "addUser")
    boolean addUser(User user);
    @UpdateProvider(type = UserSql.class,method = "updatepwd")
    boolean updatepwd(String email,String password);

    @SelectProvider(type = UserSql.class,method = "getAllUser")
    List<User> getAllUser();
    @SelectProvider(type = UserSql.class,method = "getAllUserByRole")
    List<User> getAllUserByRole(String role,String number);
    @DeleteProvider(type = UserSql.class,method = "deleteUserByName")
    boolean deleteUserByName(String username);

    @UpdateProvider(type = UserSql.class,method = "logUserLogin")
    public boolean logUserLogin(String username);
    @UpdateProvider(type = UserSql.class,method = "updateUser")
    public boolean updateUser(User user,String name);
    @UpdateProvider(type = LogsSql.class,method = "updateLog")
    public boolean updateLog(User user,String name);
    @UpdateProvider(type = UserSql.class,method = "recordUserLogout")
    public boolean recordUserLogout(String username);
    @SelectProvider(type = UserSql.class,method = "getAllLoginUser")
    public List<User> getAllLoginUser();
    @SelectProvider(type = LogsSql.class,method = "getLogByName")
    public List<Logs> getLogByName(String name);
    @SelectProvider(type = UserSql.class,method = "getAllUserByTimeAndNumber")
    public List<User> getAllUserByTimeAndNumber(String time,String number);

    @SelectProvider(type = UserSql.class,method = "isExistByName")
    public User isExistByName(String username);

    @SelectProvider(type = UserSql.class,method = "isExistByEmail")
    public User isExistByEmail(String email);
    @UpdateProvider(type = UserSql.class,method = "updateUsername")
    public boolean updateUsername(String username,String oldUserName);
    @UpdateProvider(type = UserSql.class,method = "updatePassword")
    public boolean updatePassword(String password,String oldUserName);
    @UpdateProvider(type = UserSql.class,method = "updateemail")
    public boolean updateemail(String email,String oldUserName);
    @UpdateProvider(type = UserSql.class,method = "updateTimeout")
    public boolean updateTimeout(String timeout,String oldUserName);
}
