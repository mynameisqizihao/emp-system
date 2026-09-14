package com.qi.empsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qi.empsystem.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@RequestMapping("/ajax")
@Controller
public class UserController {
    @RequestMapping("/a1")
    @ResponseBody
    public String ajax1(String name){
        if(name==null || name.equals("")){
            return "用户名不能为空";
        }else if(!name.equals("admin")){
            return "用户不存在";
        }else{
            return "OK";
        }
    }
    @RequestMapping("/a2")
    @ResponseBody
    public String ajax2(String password){
        if(password==null || password.equals("")){
            return "密码不能为空";
        }else if(!password.equals("123456")){
            return "密码错误";
        }else {
            return "OK";
        }
    }
}
