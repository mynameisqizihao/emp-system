package com.qi.empsystem.dao;

import com.qi.empsystem.pojo.Salary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工资数据访问接口。
 *
 * 方法名必须和 SalaryMapper.xml 里的 statement id 完全一致。
 */
@Mapper
public interface SalaryMapper {

    /** 新增工资记录 */
    void insertSalary(Salary salary);

    /** 查询某员工的全部工资记录 */
    List<Salary> listByEmployeeId(Integer employeeId);

    /** 查询某员工某个月的工资记录 */
    Salary getByEmployeeAndMonth(@Param("employeeId") Integer employeeId,
                                 @Param("salaryMonth") String salaryMonth);
}
