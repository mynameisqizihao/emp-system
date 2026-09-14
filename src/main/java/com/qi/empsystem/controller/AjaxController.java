//package com.qi.empsystem.controller;
//
//import com.qi.empsystem.pojo.User;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/ajax")
//public class AjaxController {
//    @RequestMapping("/a1")
//    public void ajax(String name, HttpServletResponse response) throws IOException {
//        if (name != null && name.equals("admin")){
//            response.getWriter().print("true");
//        }else{
//            response.getWriter().print("false");
//        }
//    }
//    @RequestMapping("/a2")
//    @ResponseBody
//    public List<User> ajax2() {
//        List<User> list = new ArrayList<>();
//        User user1 = new User("a",1,"男");
//        User user2 = new User("b",2,"女");
//        User user3 = new User("c",2,"女");
//        User user4 = new User("d",2,"女");
//        list.add(user1);
//        list.add(user2);
//        list.add(user3);
//        list.add(user4);
//
//        return list;
//    }
//
//    @RequestMapping("/a4")
//    @ResponseBody
//    public String ajax4(String name) {
//        if(name == null || name.equals("")){
//            return "用户名不能为空";
//        }else if(!name.equals("admin")){
//            return "用户不存在";
//        }else{
//            return "OK";
//        }
//    }
//
//    @RequestMapping("/a5")
//    @ResponseBody
//    public String ajax5(String pwd) {
//        if(pwd == null || pwd.equals("")){
//            return "密码不能为空";
//        }else if(!pwd.equals("123456")){
//            return "密码错误";
//        }else{
//            return "OK";
//        }
//    }
//}
