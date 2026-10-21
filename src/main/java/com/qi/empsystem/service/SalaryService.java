package com.qi.empsystem.service;

import com.qi.empsystem.dao.SalaryMapper;
import com.qi.empsystem.pojo.Salary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工资业务逻辑。
 */
@Service
public class SalaryService {

    @Autowired
    private SalaryMapper salaryMapper;

    /**
     * 新增工资记录。
     *
     * 业务规则：
     * 1. 同一员工同一月份只能有一条工资记录；
     * 2. 实发工资由服务端计算：基本工资 + 奖金 - 扣款，
     *    三个字段为 null 时按 0 处理，不接受前端传入的 realSalary。
     *
     * @param salary 工资信息
     * @return { success, message, salary }
     */
    public Map<String, Object> saveSalary(Salary salary) {
        Map<String, Object> result = new HashMap<>();

        // 检查该员工该月份是否已有工资记录
        Salary existing = salaryMapper.getByEmployeeAndMonth(salary.getEmployeeId(), salary.getSalaryMonth());
        if (existing != null) {
            result.put("success", false);
            result.put("message", "该员工该月份已有工资记录");
            return result;
        }

        // 自动计算实发工资
        BigDecimal base = salary.getBaseSalary() != null ? salary.getBaseSalary() : BigDecimal.ZERO;
        BigDecimal bonus = salary.getBonus() != null ? salary.getBonus() : BigDecimal.ZERO;
        BigDecimal deduction = salary.getDeduction() != null ? salary.getDeduction() : BigDecimal.ZERO;
        salary.setRealSalary(base.add(bonus).subtract(deduction));

        salaryMapper.insertSalary(salary);

        result.put("success", true);
        result.put("message", "工资记录添加成功");
        result.put("salary", salary);
        return result;
    }

    /**
     * 查询某个员工的全部工资记录。
     *
     * @param employeeId 员工 id
     * @return 工资列表
     */
    public List<Salary> listByEmployeeId(Integer employeeId) {
        return salaryMapper.listByEmployeeId(employeeId);
    }

    /**
     * 查询某个员工某个月的工资详情。
     *
     * @param employeeId  员工 id
     * @param salaryMonth 工资月份，格式 yyyy-MM，如 2026-10
     * @return { success, message, salary }
     */
    public Map<String, Object> getByMonth(Integer employeeId, String salaryMonth) {
        Map<String, Object> result = new HashMap<>();
        Salary salary = salaryMapper.getByEmployeeAndMonth(employeeId, salaryMonth);

        if (salary == null) {
            result.put("success", false);
            result.put("message", "未找到该员工该月份的工资记录");
            return result;
        }

        result.put("success", true);
        result.put("salary", salary);
        return result;
    }
}
