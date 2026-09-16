package com.qi.empsystem.service;

import com.qi.empsystem.dao.UserMapper;
import com.qi.empsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserMapper userMapper;

    public String register(String name,String password){

        User existuser = userMapper.getUserByName(name);

        if(existuser!=null){
            return "用户名已存在";
        }

        User newUser = new User(name,password);

        userMapper.addUser(newUser);

        return "OK";
    }


    public String login(String name,String password){
        User user = userMapper.getUserByName(name);
        if(user==null){
            return "用户不存在";
        }
        if(!user.getPassword().equals(password)){
            return "密码错误";
        }
        return "OK";
    }

    public String checkName(String name){
        if(name==null || name.equals("")){
            return "用户名不能为空";
        }
        User user = userMapper.getUserByName(name);
        if(user==null){
            return "用户不存在";
        }
            return "OK";
    }

    public String checkPassword(String name,String password){

        if(password==null || password.equals("")){
            return "密码不能为空";
        }

        User user = userMapper.getUserByName(name);

        if(user==null){
            return "用户不存在";
        }
        if(!user.getPassword().equals(password)){
            return "密码错误";
        }
        return "OK";
    }
}
