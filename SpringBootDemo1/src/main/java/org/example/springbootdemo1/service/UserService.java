package org.example.springbootdemo1.service;

import org.example.springbootdemo1.pojo.User;

import java.util.List;

public interface UserService {
    public User getUserByName(String username);
    public boolean addUser(User user);
    //    public String cheackUser(User user);
    public boolean updatepwd(String email,String password);
    public boolean updateUser(User user,String name);
    public List<User> getAllUser();
    public List<User> getAllUserByRole(String role,String number);
    public boolean deleteUserByName(String username);
    public User isExistByName(String username);
    public User isExistByEmail(String email);
    public boolean updateUsername(String username,String oldUserName);
    public boolean updatePassword(String password,String oldUserName);
    public boolean updateemail(String email,String oldUserName);
    public boolean updateTimeout(String timeout,String oldUserName);
    public boolean logUserLogin(String username);
    public boolean recordUserLogout(String username);
    public List<User> getAllLoginUser();
    public List<User> getAllUserByTimeAndNumber(String time,String number);
}
