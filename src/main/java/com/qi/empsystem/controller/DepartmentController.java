package com.qi.empsystem.controller;

import com.qi.empsystem.pojo.Department;
import com.qi.empsystem.service.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 部门管理接口。
 *
 * 注意：接口路径保持不变，只规范了 Java 方法名。
 */
@Tag(name = "部门管理", description = "部门的增删改查")
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Operation(summary = "查询所有部门")
    @GetMapping("/selectAll")
    public List<Department> listDepartments() {
        return departmentService.listDepartments();
    }

    // 和员工那几个接口保持一致：收表单参数，删除用 POST

    @Operation(summary = "新增部门")
    @PostMapping("/add")
    public Map<String, Object> saveDepartment(
            @Parameter(description = "部门名") @RequestParam("departmentName") String departmentName) {
        return departmentService.saveDepartment(departmentName);
    }

    @Operation(summary = "修改部门")
    @PostMapping("/update")
    public Map<String, Object> updateDepartment(
            @Parameter(description = "部门id") @RequestParam("id") Integer id,
            @Parameter(description = "部门名") @RequestParam("departmentName") String departmentName) {
        return departmentService.updateDepartment(id, departmentName);
    }

    @Operation(summary = "删除部门（部门下还有员工时不允许删）")
    @PostMapping("/delete/{id}")
    public Map<String, Object> deleteDepartment(@Parameter(description = "部门id") @PathVariable Integer id) {
        return departmentService.deleteDepartment(id);
    }
}
