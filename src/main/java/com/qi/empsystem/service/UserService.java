package com.qi.empsystem.service;

import com.qi.empsystem.dao.UserMapper;
import com.qi.empsystem.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户账号业务逻辑。
 *
 * 说明：register / login / checkName / checkPassword 返回 "OK" 或错误提示字符串，
 * 是早期 Thymeleaf 页面使用的调用方式。实际登录已由 Spring Security 接管，
 * 这些方法保留是为了不影响已有页面。
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 注册新用户。
     *
     * @param name     用户名
     * @param password 已经过 BCrypt 加密的密码
     * @return "OK" 表示成功，否则返回错误提示
     */
    public String register(String name, String password) {
        User existuser = userMapper.getUserByName(name);
        if (existuser != null) {
            return "用户名已存在";
        }
        User newUser = new User(name, password);
        userMapper.insertUser(newUser);
        return "OK";
    }

    /**
     * 校验用户名和密码。
     *
     * @param name     用户名
     * @param password 明文密码
     * @return "OK" 表示通过，否则返回错误提示
     */
    public String login(String name, String password) {
        User user = userMapper.getUserByName(name);
        if (user == null) {
            return "用户不存在";
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "密码错误";
        }
        return "OK";
    }

    /**
     * 校验用户名是否存在。
     *
     * @param name 用户名
     * @return "OK" 表示存在，否则返回错误提示
     */
    public String checkName(String name) {
        if (name == null || name.equals("")) {
            return "用户名不能为空";
        }
        User user = userMapper.getUserByName(name);
        if (user == null) {
            return "用户不存在";
        }
        return "OK";
    }

    /**
     * 校验用户名和密码是否匹配（注册页面的实时校验用）。
     *
     * @param name     用户名
     * @param password 明文密码
     * @return "OK" 表示匹配，否则返回错误提示
     */
    public String checkPassword(String name, String password) {
        if (password == null || password.equals("")) {
            return "密码不能为空";
        }

        User user = userMapper.getUserByName(name);

        if (user == null) {
            return "用户不存在";
        }
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "密码错误";
        }
        return "OK";
    }

    /**
     * 按用户名查询用户。
     *
     * @param name 用户名
     * @return 用户信息，不存在时返回 null
     */
    public User getUserByName(String name) {
        return userMapper.getUserByName(name);
    }

    /**
     * 修改密码。
     *
     * 业务规则：
     * 1. 原密码必须正确；
     * 2. 新密码不能少于 6 位；
     * 3. 新密码不能和原密码相同。
     *
     * @param name        用户名
     * @param oldPassword 原密码
     * @param newPassword 新密码
     * @return "OK" 表示修改成功，否则返回错误提示
     */
    public String changePassword(String name, String oldPassword, String newPassword) {
        User user = userMapper.getUserByName(name);
        if (user == null) {
            return "用户不存在";
        }
        if (oldPassword == null || !passwordEncoder.matches(oldPassword, user.getPassword())) {
            return "原密码不正确";
        }
        if (newPassword == null || newPassword.trim().length() < 6) {
            return "新密码不能少于 6 位";
        }
        if (newPassword.trim().equals(oldPassword)) {
            return "新密码不能和原密码相同";
        }

        userMapper.updatePassword(name, passwordEncoder.encode(newPassword));
        return "OK";
    }
}
