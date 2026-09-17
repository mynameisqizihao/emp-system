package com.qi.empsystem.dao;

import com.qi.empsystem.pojo.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DepartmentMapper {
    public List<Department> selectAllDepartments();
}
