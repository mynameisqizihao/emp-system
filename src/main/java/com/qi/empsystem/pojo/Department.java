package com.qi.empsystem.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "部门实体类")
public class Department {
    @Schema(description = "部门id",example = "101")
    private Integer id;
    @Schema(description = "部门名",example = "市场部")
    private String departmentName;
}
