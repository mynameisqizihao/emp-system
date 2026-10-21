package com.qi.empsystem.service;

import com.qi.empsystem.dao.DepartmentMapper;
import com.qi.empsystem.pojo.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门业务逻辑。
 *
 * 增删改方法统一返回 { success, message } 结构，方便前端直接提示。
 */
@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 查询所有部门。
     *
     * @return 部门列表，按 id 升序
     */
    public List<Department> listDepartments() {
        return departmentMapper.listDepartments();
    }

    /**
     * 新增部门。
     *
     * 业务规则：
     * 1. 部门名不能为空；
     * 2. 部门名不能和已有部门重复。
     *
     * @param departmentName 部门名
     * @return { success, message }
     */
    public Map<String, Object> saveDepartment(String departmentName) {
        Map<String, Object> result = new HashMap<>();

        String name = departmentName == null ? "" : departmentName.trim();
        if (name.isEmpty()) {
            result.put("success", false);
            result.put("message", "部门名不能为空");
            return result;
        }

        if (departmentMapper.getByName(name) != null) {
            result.put("success", false);
            result.put("message", "部门「" + name + "」已存在");
            return result;
        }

        Department department = new Department();
        department.setDepartmentName(name);
        departmentMapper.insertDepartment(department);

        result.put("success", true);
        result.put("message", "新增成功");
        return result;
    }

    /**
     * 修改部门。
     *
     * 业务规则：
     * 1. id 不能为空；
     * 2. 部门名不能为空；
     * 3. 不能重名，但允许改成自己原来的名字。
     *
     * @param id             部门 id
     * @param departmentName 新的部门名
     * @return { success, message }
     */
    public Map<String, Object> updateDepartment(Integer id, String departmentName) {
        Map<String, Object> result = new HashMap<>();

        if (id == null) {
            result.put("success", false);
            result.put("message", "缺少部门id");
            return result;
        }

        String name = departmentName == null ? "" : departmentName.trim();
        if (name.isEmpty()) {
            result.put("success", false);
            result.put("message", "部门名不能为空");
            return result;
        }

        // 重名校验要排除自己，否则「把名字改成原名」会被自己挡住
        Department exist = departmentMapper.getByName(name);
        if (exist != null && !exist.getId().equals(id)) {
            result.put("success", false);
            result.put("message", "部门「" + name + "」已存在");
            return result;
        }

        departmentMapper.updateDepartment(new Department(id, name));

        result.put("success", true);
        result.put("message", "修改成功");
        return result;
    }

    /**
     * 删除部门。
     *
     * 业务规则：部门下还有员工时不允许删除。
     * 原因：employee.department_id 上有外键 fk_department_id，
     * 直接删会抛数据库异常，所以先查一次员工数量。
     *
     * @param id 部门 id
     * @return { success, message }
     */
    public Map<String, Object> deleteDepartment(Integer id) {
        Map<String, Object> result = new HashMap<>();

        if (id == null) {
            result.put("success", false);
            result.put("message", "缺少部门id");
            return result;
        }

        int count = departmentMapper.countEmployeesByDept(id);
        if (count > 0) {
            result.put("success", false);
            result.put("message", "该部门下还有 " + count + " 名员工，不能删除");
            return result;
        }

        departmentMapper.deleteDepartment(id);

        result.put("success", true);
        result.put("message", "删除成功");
        return result;
    }
}
