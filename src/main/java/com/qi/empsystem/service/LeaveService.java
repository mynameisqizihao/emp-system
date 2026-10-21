package com.qi.empsystem.service;

import com.qi.empsystem.dao.LeaveMapper;
import com.qi.empsystem.pojo.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 请假业务逻辑。
 *
 * 请假状态 status 的含义：
 * 0 = 待审批，1 = 已通过，2 = 已驳回。
 */
@Service
public class LeaveService {

    @Autowired
    private LeaveMapper leaveMapper;

    /**
     * 员工提交请假申请。
     *
     * 业务规则：
     * 1. 申请时间由服务端设置为当前时间，不用前端传；
     * 2. 新提交的申请状态固定为 0（待审批）。
     *
     * @param leave 请假信息（员工、类型、起止日期、原因）
     * @return { success, message, leave }
     */
    public Map<String, Object> apply(Leave leave) {
        Map<String, Object> result = new HashMap<>();

        // 设置申请时间为当前时间，状态为待审批
        leave.setApplyTime(new Date());
        leave.setStatus(0);

        leaveMapper.insertLeave(leave);

        result.put("success", true);
        result.put("message", "请假申请已提交");
        result.put("leave", leave);
        return result;
    }

    /**
     * 审批请假（通过/驳回）。
     *
     * @param id     请假记录 id
     * @param status 审批状态：1=通过，2=驳回
     * @return { success, message }
     */
    public Map<String, Object> approve(Integer id, Integer status) {
        Map<String, Object> result = new HashMap<>();

        if (status != 1 && status != 2) {
            result.put("success", false);
            result.put("message", "审批状态无效，1=通过，2=驳回");
            return result;
        }

        String approveTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        leaveMapper.updateApprove(id, status, approveTime);

        String message = status == 1 ? "审批通过" : "审批驳回";
        result.put("success", true);
        result.put("message", message);
        return result;
    }

    /**
     * 管理端分页查询请假记录。
     *
     * 返回结构和前端约定一致：total / list / page / size。
     *
     * @param page 当前页，从 1 开始
     * @param size 每页条数
     * @return 分页结果
     */
    public Map<String, Object> pageLeaves(int page, int size) {
        int offset = (page - 1) * size;
        int total = leaveMapper.countLeaves();
        List<Leave> list = leaveMapper.listLeavesByPage(offset, size);

        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("list", list);
        result.put("page", page);
        result.put("size", size);
        return result;
    }

    /**
     * 查询某个员工的全部请假记录。
     *
     * @param employeeId 员工 id
     * @return 请假列表，按申请时间倒序
     */
    public List<Leave> listByEmployeeId(Integer employeeId) {
        return leaveMapper.listByEmployeeId(employeeId);
    }
}
