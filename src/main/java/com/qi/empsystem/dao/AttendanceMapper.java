package com.qi.empsystem.dao;

import com.qi.empsystem.pojo.Attendance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 考勤数据访问接口。
 *
 * 方法名必须和 AttendanceMapper.xml 里的 statement id 完全一致。
 */
@Mapper
public interface AttendanceMapper {

    /** 新增一条打卡记录 */
    void insertAttendance(Attendance attendance);

    /** 更新下班打卡时间和状态 */
    void updateCheckOut(Attendance attendance);

    /** 单独更新考勤状态 */
    void updateStatus(@Param("id") Integer id, @Param("status") Integer status);

    /** 查询某员工某天的打卡记录 */
    Attendance getToday(@Param("employeeId") Integer employeeId, @Param("workDate") String workDate);

    /** 统计考勤记录总数 */
    int countAttendances();

    /** 分页查询考勤记录 */
    List<Attendance> listAttendancesByPage(@Param("offset") int offset, @Param("size") int size);

    /** 查询某员工的全部考勤记录 */
    List<Attendance> listByEmployeeId(Integer employeeId);

    /** 统计某一天的打卡人数（首页「今日出勤」用） */
    int countToday(@Param("workDate") String workDate);
}
