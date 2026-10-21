package com.qi.empsystem.service;

import com.qi.empsystem.dao.AttendanceMapper;
import com.qi.empsystem.dao.DepartmentMapper;
import com.qi.empsystem.dao.EmployeeMapper;
import com.qi.empsystem.dao.StatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 首页统计业务逻辑。
 */
@Service
public class StatisticsService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private AttendanceMapper attendanceMapper;

    @Autowired
    private StatisticsMapper statisticsMapper;

    /**
     * 首页概览统计。
     *
     * @return 包含四个字段的 Map：
     *         totalEmployees  总员工数
     *         totalDepartments 总部门数
     *         todayAttendance  今日出勤人数
     *         monthLeave       本月请假条数
     */
    public Map<String, Object> getOverview() {
        Map<String, Object> result = new HashMap<>();

        // 总员工数
        int totalEmployees = employeeMapper.countEmployees();
        result.put("totalEmployees", totalEmployees);

        // 总部门数
        int totalDepartments = departmentMapper.listDepartments().size();
        result.put("totalDepartments", totalDepartments);

        // 今日出勤
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        int todayAttendance = attendanceMapper.countToday(today);
        result.put("todayAttendance", todayAttendance);

        // 本月请假
        result.put("monthLeave", statisticsMapper.countMonthLeave());

        return result;
    }

    /**
     * 各部门人数统计（用于 ECharts 柱状图）。
     *
     * @return { names: [部门名...], counts: [人数...] }
     */
    public Map<String, Object> getDeptStats() {
        List<Map<String, Object>> list = statisticsMapper.listDeptEmployeeCount();

        List<String> names = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();

        for (Map<String, Object> map : list) {
            names.add(String.valueOf(map.get("name")));
            counts.add(((Number) map.get("value")).intValue());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("names", names);
        result.put("counts", counts);
        return result;
    }
}
