package com.qi.empsystem.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userSession = request.getSession().getAttribute("userSession");
        if(userSession==null){
            response.sendRedirect("/login.html");
            return false;
        }else{
            return true;
        }
    }
}
