package com.qi.empsystem.controller;

import com.qi.empsystem.pojo.Salary;
import com.qi.empsystem.service.SalaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 工资管理接口。
 *
 * 注意：接口路径保持不变，只规范了 Java 方法名。
 */
@Tag(name = "工资管理", description = "工资记录管理接口")
@RestController
@RequestMapping("/salary")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @Operation(summary = "新增工资记录")
    @PostMapping("/add")
    public Map<String, Object> saveSalary(@Parameter(description = "工资信息") @RequestBody Salary salary) {
        return salaryService.saveSalary(salary);
    }

    @Operation(summary = "查某员工所有工资记录")
    @GetMapping("/myList")
    public List<Salary> listByEmployeeId(@Parameter(description = "员工id") @RequestParam Integer employeeId) {
        return salaryService.listByEmployeeId(employeeId);
    }

    @Operation(summary = "查某员工某月工资详情")
    @GetMapping("/detail")
    public Map<String, Object> getByMonth(
            @Parameter(description = "员工id") @RequestParam Integer employeeId,
            @Parameter(description = "工资月份，如2026-10") @RequestParam String salaryMonth) {
        return salaryService.getByMonth(employeeId, salaryMonth);
    }
}
