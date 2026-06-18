package org.example.springbootdemo1.service;

import org.example.springbootdemo1.mapper.UserMapper;
import org.example.springbootdemo1.pojo.Logs;
import org.example.springbootdemo1.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User getUserByName(String username) {
        User u = userMapper.getUserByName(username);
        return u;
    }
    @Transactional
    @Override
    public List<User> getAllUser(){
        return userMapper.getAllUser();
    }

    @Transactional
    @Override
    public boolean addUser(User user){
        return userMapper.addUser(user);
    }

    @Transactional
    @Override
    public boolean deleteUserByName(String username){
        return userMapper.deleteUserByName(username);
    }
    @Transactional
    @Override
    public User isExistByName(String username){
        return userMapper.isExistByName(username);
    }

    @Transactional
    @Override
    public User isExistByEmail(String email){
        return userMapper.isExistByEmail(email);
    }
    @Transactional
    @Override
    public List<User> getAllUserByRole(String role,String number){
        return userMapper.getAllUserByRole(role,number);
    }

    @Transactional
    @Override
    public boolean updatepwd(String email,String password){return userMapper.updatepwd(email,password);}
    @Transactional
    @Override
    public boolean updateUser(User user,String oldName){
        List<Logs> logs = userMapper.getLogByName(oldName); // 假设您想要检查旧名称的日志
        if (!logs.isEmpty()&&user.getUserName()!=null) {
            boolean logUpdated = userMapper.updateLog(user, oldName);
        }
        boolean userUpdated = userMapper.updateUser(user,oldName);


        return userUpdated;

    }

    @Transactional
    @Override
    public boolean logUserLogin(String username){return userMapper.logUserLogin(username);}
    @Transactional
    @Override
    public boolean recordUserLogout(String username){return userMapper.recordUserLogout(username);}
    @Transactional
    @Override
    public List<User> getAllLoginUser(){return userMapper.getAllLoginUser();}
    @Transactional
    @Override
    public List<User> getAllUserByTimeAndNumber(String time,String number){return userMapper.getAllUserByTimeAndNumber(time,number);}

    @Transactional
    @Override
    public boolean updateUsername(String username,String oldUserName){
        return userMapper.updateUsername(username,oldUserName);
    }
    @Transactional
    @Override
    public boolean updatePassword(String password,String oldUserName){
        return userMapper.updatePassword(password,oldUserName);
    }
    //    public boolean updatePassword(String password,String oldUserName);
    @Transactional
    @Override
    public boolean updateemail(String email,String oldUserName){
        return userMapper.updateemail(email,oldUserName);
    }
    @Transactional
    @Override
    public boolean updateTimeout(String timeout,String oldUserName){
        return userMapper.updateTimeout(timeout,oldUserName);
    }
}
