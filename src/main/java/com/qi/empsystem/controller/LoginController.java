package com.qi.empsystem.controller;

import com.qi.empsystem.dao.UserMapper;
import com.qi.empsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")



public class LoginController {

    @Autowired
    private UserService userService;
//登录是一个改变服务器状态的操作，还要创建session,以及不能让用户的用户名密码显示在url地址栏里
    @PostMapping("/login")
    @ResponseBody
    public String login(String name,String password){
        return userService.login(name,password);
    }

    @PostMapping("/checkName")
    @ResponseBody
    public String checkName(String name){
        return userService.checkName(name);
    }

    @PostMapping("/checkPassword")
    @ResponseBody
    public String checkPassword(String name,String password){
        return userService.checkPassword(name,password);
    }
}
