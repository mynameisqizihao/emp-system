package com.qi.empsystem.controller;

import com.qi.empsystem.dao.UserMapper;
import com.qi.empsystem.pojo.User;
import com.qi.empsystem.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String login(String name, String password, HttpServletRequest request){
        String result = userService.login(name,password);
        if ("OK".equals(result)){
            User user = userService.getUserByName(name);
            request.getSession().setAttribute("userSession",user);
        }
        return result;
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

    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();//清空session
        return "redirect:/login.html";
    }
}
