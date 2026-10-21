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
@Schema(description = "员工实体类")
public class Employee {
    @Schema(description = "员工id",example = "1")
    private Integer id;
    @Schema(description = "员工姓名",example = "张三")
    private String lastName;
    @Schema(description = "员工邮箱",example = "123@qq.com")
    private String email;
    @Schema(description = "员工性别（0=女，1=男）",example = "0")
    private Integer gender;
    @Schema(description = "员工所属部门")
    private Department department;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "员工生日",example = "2020-01-01")
    private Date birth;
}
