package com.qi.empsystem.dao;

import com.qi.empsystem.pojo.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门数据访问接口。
 *
 * 方法名必须和 DepartmentMapper.xml 里的 statement id 完全一致。
 */
@Mapper
public interface DepartmentMapper {

    /** 查询所有部门 */
    List<Department> listDepartments();

    /** 按部门名精确查询（用于重名校验） */
    Department getByName(@Param("departmentName") String departmentName);

    /** 新增部门 */
    void insertDepartment(Department department);

    /** 修改部门 */
    void updateDepartment(Department department);

    /** 删除部门 */
    void deleteDepartment(@Param("id") Integer id);

    /** 统计该部门下的员工数：employee.department_id 上有外键，删除前必须先查 */
    int countEmployeesByDept(@Param("id") Integer id);
}
