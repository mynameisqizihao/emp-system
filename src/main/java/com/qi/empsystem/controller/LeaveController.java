package com.qi.empsystem.controller;

import com.qi.empsystem.pojo.Leave;
import com.qi.empsystem.service.LeaveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 请假管理接口：申请与审批。
 *
 * 注意：接口路径保持不变，只规范了 Java 方法名。
 */
@Tag(name = "请假管理", description = "请假申请与审批接口")
@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @Operation(summary = "提交请假申请")
    @PostMapping("/apply")
    public Map<String, Object> apply(@Parameter(description = "请假信息") @RequestBody Leave leave) {
        return leaveService.apply(leave);
    }

    @Operation(summary = "审批请假")
    @PostMapping("/approve")
    public Map<String, Object> approve(
            @Parameter(description = "请假记录id") @RequestParam Integer id,
            @Parameter(description = "审批状态：1=通过，2=驳回") @RequestParam Integer status) {
        return leaveService.approve(id, status);
    }

    @Operation(summary = "分页查询请假记录")
    @GetMapping("/page")
    public Map<String, Object> pageLeaves(
            @Parameter(description = "当前第几页") @RequestParam(value = "page", defaultValue = "1") int page,
            @Parameter(description = "每页多少行数据") @RequestParam(value = "size", defaultValue = "5") int size) {
        return leaveService.pageLeaves(page, size);
    }

    @Operation(summary = "查某员工所有请假记录")
    @GetMapping("/myList")
    public List<Leave> listByEmployeeId(@Parameter(description = "员工id") @RequestParam Integer employeeId) {
        return leaveService.listByEmployeeId(employeeId);
    }
}
