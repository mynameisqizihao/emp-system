package com.qi.empsystem.dao;


import com.qi.empsystem.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUserById(Integer id);

    User getUserByName(String name);

    void addUser(User user);
}
