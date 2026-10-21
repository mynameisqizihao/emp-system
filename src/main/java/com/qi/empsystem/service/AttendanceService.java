package com.qi.empsystem.service;

import com.qi.empsystem.dao.AttendanceMapper;
import com.qi.empsystem.pojo.Attendance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 考勤业务逻辑。
 *
 * 考勤状态 status 的含义：
 * 0 = 正常，1 = 迟到，2 = 早退，3 = 迟到 + 早退。
 */
@Service
public class AttendanceService {

    @Autowired
    private AttendanceMapper attendanceMapper;

    /**
     * 上班打卡。
     *
     * 业务规则：
     * 1. 同一天只能打一次上班卡；
     * 2. 9:00 之后打卡算迟到，status = 1，否则 status = 0。
     *
     * @param employeeId 员工 id
     * @return { success, message, attendance }
     */
    public Map<String, Object> checkIn(Integer employeeId) {
        Map<String, Object> result = new HashMap<>();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        // 检查今天是否已经打过卡
        Attendance existing = attendanceMapper.getToday(employeeId, today);
        if (existing != null) {
            result.put("success", false);
            result.put("message", "今天已经打过卡了");
            return result;
        }

        Date now = new Date();
        Attendance attendance = new Attendance();
        attendance.setEmployeeId(employeeId);
        attendance.setCheckInTime(now);

        try {
            attendance.setWorkDate(new SimpleDateFormat("yyyy-MM-dd").parse(today));
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "日期解析失败");
            return result;
        }

        // 判断是否迟到：9 点后算迟到
        try {
            Date nineOClock = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(today + " 09:00:00");
            if (now.after(nineOClock)) {
                attendance.setStatus(1); // 迟到
            } else {
                attendance.setStatus(0); // 正常
            }
        } catch (Exception e) {
            attendance.setStatus(0);
        }

        attendanceMapper.insertAttendance(attendance);

        result.put("success", true);
        result.put("message", attendance.getStatus() == 1 ? "打卡成功，但已迟到" : "打卡成功");
        result.put("attendance", attendance);
        return result;
    }

    /**
     * 下班打卡。
     *
     * 业务规则：
     * 1. 必须先打上班卡；
     * 2. 同一天只能打一次下班卡；
     * 3. 18:00 之前打卡算早退，在原状态上加 2：
     *    正常(0) -> 早退(2)，迟到(1) -> 迟到+早退(3)。
     *
     * @param employeeId 员工 id
     * @return { success, message, attendance }
     */
    public Map<String, Object> checkOut(Integer employeeId) {
        Map<String, Object> result = new HashMap<>();
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        Attendance attendance = attendanceMapper.getToday(employeeId, today);
        if (attendance == null) {
            result.put("success", false);
            result.put("message", "今天还未上班打卡");
            return result;
        }
        if (attendance.getCheckOutTime() != null) {
            result.put("success", false);
            result.put("message", "今天已经打过下班卡了");
            return result;
        }

        Date now = new Date();
        attendance.setCheckOutTime(now);

        // 判断是否早退：18 点前算早退
        try {
            Date sixOClock = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(today + " 18:00:00");
            if (now.before(sixOClock)) {
                // 早退：在原有状态上 +2（迟到=1 则变 3，正常=0 则变 2）
                attendance.setStatus(attendance.getStatus() + 2);
            }
        } catch (Exception e) {
            // 解析异常不影响打卡
        }

        attendanceMapper.updateCheckOut(attendance);

        String message = "下班打卡成功";
        if (attendance.getStatus() == 2) {
            message = "下班打卡成功，但已早退";
        } else if (attendance.getStatus() == 3) {
            message = "下班打卡成功，但迟到且早退";
        }

        result.put("success", true);
        result.put("message", message);
        result.put("attendance", attendance);
        return result;
    }

    /**
     * 管理端分页查询考勤记录。
     *
     * 返回结构和前端约定一致：total / list / page / size。
     *
     * @param page 当前页，从 1 开始
     * @param size 每页条数
     * @return 分页结果
     */
    public Map<String, Object> pageAttendances(int page, int size) {
        int offset = (page - 1) * size;
        int total = attendanceMapper.countAttendances();
        List<Attendance> list = attendanceMapper.listAttendancesByPage(offset, size);

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("list", list);
        result.put("page", page);
        result.put("size", size);
        return result;
    }

    /**
     * 查询某个员工的全部考勤记录。
     *
     * @param employeeId 员工 id
     * @return 考勤列表
     */
    public List<Attendance> listByEmployeeId(Integer employeeId) {
        return attendanceMapper.listByEmployeeId(employeeId);
    }

    /**
     * 查询某个员工今天的打卡记录。
     *
     * @param employeeId 员工 id
     * @return 今天的考勤记录，没打过卡时返回 null
     */
    public Attendance getToday(Integer employeeId) {
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        return attendanceMapper.getToday(employeeId, today);
    }
}
