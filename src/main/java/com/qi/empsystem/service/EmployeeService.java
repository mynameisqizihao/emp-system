package com.qi.empsystem.service;

import com.qi.empsystem.dao.EmployeeMapper;
import com.qi.empsystem.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> selectAllEmployees(){
        return employeeMapper.selectAllEmployees();
    }

    public Employee selectAllEmployeesById(Integer id){
        return employeeMapper.selectAllEmployeesById(id);
    }

    public void addEmployee(Employee employee){
         employeeMapper.addEmployee(employee);
    }

    public void updateEmployee(Employee employee){
         employeeMapper.updateEmployee(employee);
    }

    public void deleteEmployee(Integer id){
         employeeMapper.deleteEmployee(id);
    }
}
