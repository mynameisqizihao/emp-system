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
@Schema(description = "考勤实体类")
public class Attendance {
    @Schema(description = "考勤id", example = "1")
    private Integer id;
    @Schema(description = "员工id", example = "1")
    private Integer employeeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "工作日期", example = "2026-10-04")
    private Date workDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "上班打卡时间", example = "2026-10-04 08:50:00")
    private Date checkInTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "下班打卡时间", example = "2026-10-04 18:10:00")
    private Date checkOutTime;
    @Schema(description = "考勤状态（0=正常，1=迟到，2=早退，3=迟到+早退）", example = "0")
    private Integer status;
}
