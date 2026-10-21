package com.qi.empsystem.controller;

import com.qi.empsystem.pojo.Employee;
import com.qi.empsystem.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 员工管理接口：员工的增删改查。
 *
 * 注意：接口路径保持不变，只规范了 Java 方法名。
 */
@Tag(name = "员工管理", description = "员工的增删改查接口")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Operation(summary = "查询所有员工")
    @GetMapping("/selectAll")
    public List<Employee> listEmployees() {
        return employeeService.listEmployees();
    }

    @Operation(summary = "根据id查询员工")
    @GetMapping("/selectAllById/{id}")
    public Employee getEmployeeById(@Parameter(description = "员工id") @PathVariable Integer id) {
        return employeeService.getEmployeeById(id);
    }

    // 增删改得用 post，查询可以 get
    @Operation(summary = "添加员工")
    @PostMapping("/add")
    public void saveEmployee(@Parameter(description = "员工") Employee employee) {
        employeeService.saveEmployee(employee);
    }

    @Operation(summary = "修改员工")
    @PostMapping("/update")
    public void updateEmployee(@Parameter(description = "员工") Employee employee) {
        employeeService.updateEmployee(employee);
    }

    @Operation(summary = "删除员工")
    @PostMapping("/delete/{id}")
    public void deleteEmployee(@Parameter(description = "员工id") @PathVariable Integer id) {
        employeeService.deleteEmployee(id);
    }

    @Operation(summary = "分页查询员工")
    @GetMapping("/page")
    public Map<String, Object> pageEmployees(
            @Parameter(description = "当前第几页") @RequestParam(value = "page", defaultValue = "1") int page,
            @Parameter(description = "每页多少行数据") @RequestParam(value = "size", defaultValue = "5") int size,
            @Parameter(description = "搜索关键字，按姓名或邮箱模糊匹配") @RequestParam(value = "keyword", required = false) String keyword) {
        return employeeService.pageEmployees(page, size, keyword);
    }
}
