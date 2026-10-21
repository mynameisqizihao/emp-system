package com.qi.empsystem.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "工资实体类")
public class Salary {
    @Schema(description = "工资id", example = "1")
    private Integer id;
    @Schema(description = "员工id", example = "1")
    private Integer employeeId;
    @Schema(description = "工资月份", example = "2026-10")
    private String salaryMonth;
    @Schema(description = "基本工资", example = "5000.00")
    private BigDecimal baseSalary;
    @Schema(description = "奖金", example = "1000.00")
    private BigDecimal bonus;
    @Schema(description = "扣款", example = "200.00")
    private BigDecimal deduction;
    @Schema(description = "实发工资", example = "5800.00")
    private BigDecimal realSalary;
}
