package com.qi.empsystem.controller;

import com.qi.empsystem.pojo.Employee;
import com.qi.empsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/selectAll")
    public List<Employee> selectAllEmployees(){
        return employeeService.selectAllEmployees();
    }
//  Restful 风格
    @GetMapping("/selectAllById/{id}")
    public Employee selectAllEmployeesById(@PathVariable Integer id){
        return employeeService.selectAllEmployeesById(id);
    }
//    @GetMapping("/selectAllById")
//    public List<Employee> selectAllEmployeesById(@RequestParam("empId") Integer id){
//        return employeeService.selectAllEmployeesById(id);
//    }

    //增删改得用post，查询可以get
    @PostMapping("/add")
    public void addEmployee(Employee employee){
         employeeService.addEmployee(employee);
    }

    @PostMapping("/update")
    public void updateEmployee(Employee employee){
        employeeService.updateEmployee(employee);
    }

    @PostMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable Integer id){
        employeeService.deleteEmployee(id);
    }
}
