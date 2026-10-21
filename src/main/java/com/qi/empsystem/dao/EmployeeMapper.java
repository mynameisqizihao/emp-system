package com.qi.empsystem.dao;

import com.qi.empsystem.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 员工数据访问接口。
 *
 * 方法名必须和 EmployeeMapper.xml 里的 statement id 完全一致。
 */
@Mapper
public interface EmployeeMapper {

    /** 查询所有员工（含部门信息） */
    List<Employee> listEmployees();

    /** 根据 id 查询员工 */
    Employee getEmployeeById(Integer id);

    /** 新增员工 */
    void insertEmployee(Employee employee);

    /** 修改员工 */
    void updateEmployee(Employee employee);

    /** 删除员工 */
    void deleteEmployee(Integer id);

    /** 统计员工总数（不带搜索条件） */
    int countEmployees();

    /** 分页查询员工，keyword 为空时查全部 */
    List<Employee> listEmployeesByPage(@Param("keyword") String keyword,
                                       @Param("offset") int offset,
                                       @Param("size") int size);

    /** 分页查询的总记录数，搜索条件必须和 listEmployeesByPage 保持一致 */
    int countEmployeesByKeyword(@Param("keyword") String keyword);
}
