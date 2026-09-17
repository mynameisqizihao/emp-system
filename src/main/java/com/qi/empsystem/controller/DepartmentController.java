package com.qi.empsystem.controller;

import com.qi.empsystem.pojo.Department;
import com.qi.empsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/selectAll")
    public List<Department> selectAllDepartments(){
        return departmentService.selectAllDepartments();
    }
}
