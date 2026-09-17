package com.qi.empsystem.service;

import com.qi.empsystem.dao.DepartmentMapper;
import com.qi.empsystem.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public List<Department> selectAllDepartments(){
        return departmentMapper.selectAllDepartments();
    }
}
