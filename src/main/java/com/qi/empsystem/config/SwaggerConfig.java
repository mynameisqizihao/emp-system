package com.qi.empsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("元知HRM人力资源管理系统API文档")
                        .description("企业资源计划第一子系统")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("齐子豪")
                                .email("208824161@qq.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }

    @Bean
    public GroupedOpenApi employeeApi(){
        return GroupedOpenApi.builder()
                .group("A-员工管理")
                .pathsToMatch("/employee/**")
                .build();
    }

    @Bean
    public GroupedOpenApi departmentApi(){
        return GroupedOpenApi.builder()
                .group("B-部门管理")
                .pathsToMatch("/department/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userApi(){
        return GroupedOpenApi.builder()
                .group("C-用户管理")
                .pathsToMatch("/user/**","/ajax/**")
                .build();
    }

    // ============ 新增 4 个分组 ============

    @Bean
    public GroupedOpenApi statisticsApi(){
        return GroupedOpenApi.builder()
                .group("D-数据统计")
                .pathsToMatch("/statistics/**")
                .build();
    }

    @Bean
    public GroupedOpenApi attendanceApi(){
        return GroupedOpenApi.builder()
                .group("E-考勤管理")
                .pathsToMatch("/attendance/**")
                .build();
    }

    @Bean
    public GroupedOpenApi leaveApi(){
        return GroupedOpenApi.builder()
                .group("F-请假管理")
                .pathsToMatch("/leave/**")
                .build();
    }

    @Bean
    public GroupedOpenApi salaryApi(){
        return GroupedOpenApi.builder()
                .group("G-工资管理")
                .pathsToMatch("/salary/**")
                .build();
    }
}