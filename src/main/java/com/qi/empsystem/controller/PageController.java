package com.qi.empsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Thymeleaf 页面路由：把 .html 请求映射到 templates 下的模板。
 *
 * 这是旧版管理页面使用的入口，Vue 前端不经过这里。
 */
@Controller
public class PageController {
    @GetMapping("/login.html")
    public String loginPage(){
        return "login";
    }
    @GetMapping("/register.html")
    public String registerPage(){
        return "register";
    }
    @GetMapping("/index.html")
    public String indexPage(){
        return "index";
    }
    @GetMapping("/dashboard.html")
    public String dashboardPage(){
        return "dashboard";
    }
    @GetMapping("/add.html")
    public String addPage(){
        return "add";
    }
    @GetMapping("/update.html")
    public String updatePage(){
        return "update";
    }


}
