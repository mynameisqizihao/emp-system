package com.qi.empsystem.controller;

import com.qi.empsystem.pojo.Attendance;
import com.qi.empsystem.service.AttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 考勤管理接口：打卡与查询。
 *
 * 注意：接口路径保持不变，只规范了 Java 方法名。
 */
@Tag(name = "考勤管理", description = "考勤打卡与查询接口")
@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @Operation(summary = "上班打卡")
    @PostMapping("/checkIn")
    public Map<String, Object> checkIn(@Parameter(description = "员工id") @RequestParam Integer employeeId) {
        return attendanceService.checkIn(employeeId);
    }

    @Operation(summary = "下班打卡")
    @PostMapping("/checkOut")
    public Map<String, Object> checkOut(@Parameter(description = "员工id") @RequestParam Integer employeeId) {
        return attendanceService.checkOut(employeeId);
    }

    @Operation(summary = "分页查询考勤")
    @GetMapping("/page")
    public Map<String, Object> pageAttendances(
            @Parameter(description = "当前第几页") @RequestParam(value = "page", defaultValue = "1") int page,
            @Parameter(description = "每页多少行数据") @RequestParam(value = "size", defaultValue = "5") int size) {
        return attendanceService.pageAttendances(page, size);
    }

    @Operation(summary = "查某员工所有考勤记录")
    @GetMapping("/myList")
    public List<Attendance> listByEmployeeId(@Parameter(description = "员工id") @RequestParam Integer employeeId) {
        return attendanceService.listByEmployeeId(employeeId);
    }

    @Operation(summary = "查今天打卡状态")
    @GetMapping("/today")
    public Attendance getToday(@Parameter(description = "员工id") @RequestParam Integer employeeId) {
        return attendanceService.getToday(employeeId);
    }
}
