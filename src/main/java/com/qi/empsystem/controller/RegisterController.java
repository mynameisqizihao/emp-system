package com.qi.empsystem.controller;

import com.qi.empsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 注册接口。
 *
 * 注意：接口路径保持不变，只规范了 Java 方法名。
 */
@Tag(name = "注册校验", description = "注册校验")
@Controller
@RequestMapping("/user")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 注册新用户。
     *
     * 密码在这里先用 BCrypt 加密，再交给 UserService 保存。
     *
     * @param name     用户名
     * @param password 明文密码
     * @return "OK" 表示注册成功，否则返回错误提示
     */
    @Operation(summary = "注册校验")
    @PostMapping("/register")
    @ResponseBody
    public String register(@Parameter(description = "用户名") String name,@Parameter(description = "密码") String password){
        String bcryptPassword = passwordEncoder.encode(password);
        return userService.register(name,bcryptPassword);
    }
}
