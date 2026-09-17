package com.qi.empsystem.controller;

import com.qi.empsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class RegisterController {
    @Autowired
    private UserService userService;
    //接受前端传来的name和password
    @PostMapping("/register")
    @ResponseBody
    public String register(String name,String password){
        return userService.register(name,password);
    }
}
