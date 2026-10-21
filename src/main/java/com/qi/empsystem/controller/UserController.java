package com.qi.empsystem.controller;

import com.qi.empsystem.dao.UserMapper;
import com.qi.empsystem.pojo.User;
import com.qi.empsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "当前用户", description = "获取当前登录账号的信息")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    /** 当前登录账号的用户名，未登录时返回 null */
    private String currentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null ? null : authentication.getName();
    }

    /**
     * 取当前登录账号。
     *
     * 登录接口本身是 Spring Security 的表单登录，成功只返回 302、没有响应体，
     * 所以前端登录成功后必须再调这个接口才知道「我是谁、对应哪个员工」。
     */
    @Operation(summary = "获取当前登录用户信息")
    @GetMapping("/current")
    public Map<String, Object> getCurrent() {
        String name = currentUsername();
        User user = name == null ? null : userMapper.getUserByName(name);

        Map<String, Object> result = new HashMap<>();
        if (user == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }

        // 这里手工拼字段而直接返回 User 实体，是为了不把密码哈希带出去
        result.put("success", true);
        result.put("id", user.getId());
        result.put("name", user.getName());
        // 可能为 null：这个账号还没绑定员工
        result.put("employeeId", user.getEmployeeId());
        return result;
    }

    /**
     * 修改当前登录账号的密码。
     * 用户名从 SecurityContext 取，前端不用（也不能）传是谁在改密码。
     */
    @Operation(summary = "修改密码")
    @PostMapping("/changePassword")
    public Map<String, Object> changePassword(
            @Parameter(description = "原密码") @RequestParam("oldPassword") String oldPassword,
            @Parameter(description = "新密码") @RequestParam("newPassword") String newPassword) {

        Map<String, Object> result = new HashMap<>();

        String name = currentUsername();
        if (name == null) {
            result.put("success", false);
            result.put("message", "未登录");
            return result;
        }

        String outcome = userService.changePassword(name, oldPassword, newPassword);
        boolean ok = "OK".equals(outcome);

        result.put("success", ok);
        result.put("message", ok ? "密码修改成功" : outcome);
        return result;
    }
}
