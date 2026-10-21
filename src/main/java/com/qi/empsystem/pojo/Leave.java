package com.qi.empsystem.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "请假实体类")
public class Leave {
    @Schema(description = "请假id", example = "1")
    private Integer id;
    @Schema(description = "员工id", example = "1")
    private Integer employeeId;
    @Schema(description = "请假类型（1=事假，2=病假，3=年假）", example = "1")
    private Integer leaveType;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "开始日期", example = "2026-10-05")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "结束日期", example = "2026-10-07")
    private Date endDate;
    @Schema(description = "请假原因", example = "身体不适")
    private String reason;
    @Schema(description = "审批状态（0=待审批，1=已通过，2=已驳回）", example = "0")
    private Integer status;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "申请时间", example = "2026-10-04 10:00:00")
    private Date applyTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "审批时间", example = "2026-10-04 14:00:00")
    private Date approveTime;
}
