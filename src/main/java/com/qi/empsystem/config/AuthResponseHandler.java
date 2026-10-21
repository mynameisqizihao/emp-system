package com.qi.empsystem.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 统一处理「没登录」和「用户名密码不对」两种情况，回应方式按请求方区分：
 *
 *   浏览器直接访问页面（Accept 里带 text/html）-> 跳 /login.html，保持 Thymeleaf 那套行为不变。
 *   前端 SPA 的 XHR / fetch（Accept 里没有 text/html）-> 直接返回 401 + JSON。
 *
 * 为什么要区分：
 * 1. SPA 登录用的是 fetch + redirect:'manual'，如果这里回 302，它拿到的是一个不透明响应
 *    （status 为 0、读不到 Location），和登录成功的 302 长得一模一样，根本分不出来。
 * 2. 前端 request.js 里是「401 才清 token 并跳登录页」，302 会被浏览器跟到一个 HTML 页面，
 *    那个分支永远不会触发。
 */
@Component
public class AuthResponseHandler implements AuthenticationEntryPoint, AuthenticationFailureHandler {

    /** 未登录就访问了受保护资源（session 过期，或压根没登录）走这里 */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        if (wantsHtml(request)) {
            response.sendRedirect("/login.html");
            return;
        }
        writeJson(response, HttpServletResponse.SC_UNAUTHORIZED, "未登录或登录已过期");
    }

    /** 用户名或密码不对走这里 */
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {
        if (wantsHtml(request)) {
            // 和 Spring Security 默认失败行为一致，Thymeleaf 登录页不受影响
            response.sendRedirect("/login.html?error");
            return;
        }
        writeJson(response, HttpServletResponse.SC_UNAUTHORIZED, "用户名或密码错误");
    }

    /** 浏览器地址栏访问页面会带 text/html；jQuery / axios / fetch 发出的 XHR 不会 */
    private boolean wantsHtml(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        return accept != null && accept.contains("text/html");
    }

    private void writeJson(HttpServletResponse response, int status, String message) throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write("{\"message\":\"" + message + "\"}");
    }
}
