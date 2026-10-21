package com.qi.empsystem.dao;

import com.qi.empsystem.pojo.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 请假数据访问接口。
 *
 * 方法名必须和 LeaveMapper.xml 里的 statement id 完全一致。
 */
@Mapper
public interface LeaveMapper {

    /** 新增请假申请 */
    void insertLeave(Leave leave);

    /** 更新审批状态和审批时间 */
    void updateApprove(@Param("id") Integer id,
                       @Param("status") Integer status,
                       @Param("approveTime") String approveTime);

    /** 请假记录总数 */
    int countLeaves();

    /** 分页查询请假记录 */
    List<Leave> listLeavesByPage(@Param("offset") int offset, @Param("size") int size);

    /** 查询某员工的全部请假记录 */
    List<Leave> listByEmployeeId(Integer employeeId);
}
