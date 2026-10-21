package com.qi.empsystem.dao;

import com.qi.empsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户账号数据访问接口。
 *
 * 方法名必须和 UserMapper.xml 里的 statement id 完全一致。
 */
@Mapper
public interface UserMapper {

    /** 根据 id 查询用户 */
    User getUserById(Integer id);

    /** 根据用户名查询用户 */
    User getUserByName(String name);

    /** 新增用户 */
    void insertUser(User user);

    /** 修改密码，传进来的 password 必须是已经加密过的 */
    void updatePassword(@Param("name") String name, @Param("password") String password);
}
