package com.qi.empsystem.dao;

import com.qi.empsystem.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    //查询所有员工（dashboard.html展示内容）
    List<Employee> selectAllEmployees();

    //根据id查询所有员工
    Employee selectAllEmployeesById(Integer id);

    //添加员工
    void addEmployee(Employee employee);

    //修改员工
    void updateEmployee(Employee employee);

    //删除员工
    void deleteEmployee(Integer id);
}
