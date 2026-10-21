package com.qi.empsystem.service;

import com.qi.empsystem.dao.EmployeeMapper;
import com.qi.empsystem.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工业务逻辑。
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询所有员工（含所属部门）。
     *
     * @return 员工列表，按 id 升序
     */
    public List<Employee> listEmployees() {
        return employeeMapper.listEmployees();
    }

    /**
     * 根据 id 查询单个员工。
     *
     * @param id 员工 id
     * @return 员工信息，不存在时返回 null
     */
    public Employee getEmployeeById(Integer id) {
        return employeeMapper.getEmployeeById(id);
    }

    /**
     * 新增员工。
     *
     * @param employee 员工信息，其中 department.id 必须有值
     */
    public void saveEmployee(Employee employee) {
        employeeMapper.insertEmployee(employee);
    }

    /**
     * 修改员工。
     *
     * @param employee 员工信息，id 不能为空
     */
    public void updateEmployee(Employee employee) {
        employeeMapper.updateEmployee(employee);
    }

    /**
     * 删除员工。
     *
     * @param id 员工 id
     */
    public void deleteEmployee(Integer id) {
        employeeMapper.deleteEmployee(id);
    }

    /**
     * 分页查询员工，支持按姓名或邮箱模糊搜索。
     *
     * 返回结构和前端约定一致：total / list / page / size。
     * 注意 total 必须使用和列表相同的搜索条件，否则分页页码会算错。
     *
     * @param page    当前页，从 1 开始
     * @param size    每页条数
     * @param keyword 搜索关键字，可为 null 表示查全部
     * @return 分页结果
     */
    public Map<String, Object> pageEmployees(int page, int size, String keyword) {
        int offset = (page - 1) * size;
        int total = employeeMapper.countEmployeesByKeyword(keyword);
        List<Employee> list = employeeMapper.listEmployeesByPage(keyword, offset, size);

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("list", list);
        result.put("page", page);
        result.put("size", size);
        return result;
    }
}
