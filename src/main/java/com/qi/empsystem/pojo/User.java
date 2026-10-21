package com.qi.empsystem.pojo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "用户实体类")
public class User {
    @Schema(description = "用户id",example = "1")
    private Integer id; //int默认为0，Integer默认可以是null，可以区分没赋值和赋值为0
    @Schema(description = "用户名",example = "admin")
    private String name;
    @Schema(description = "用户密码",example = "123456")
    private String password;
    @Schema(description = "关联的员工id，可为空（该账号不是员工）",example = "1")
    private Integer employeeId;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
