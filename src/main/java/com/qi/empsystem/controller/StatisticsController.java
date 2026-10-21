package com.qi.empsystem.controller;

import com.qi.empsystem.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 首页统计接口：概览数字和部门人数分布。
 *
 * 注意：接口路径保持不变，只规范了 Java 方法名。
 */
@Tag(name = "统计管理", description = "首页统计数据接口")
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @Operation(summary = "首页概览统计（总员工数、总部门数、今日出勤）")
    @GetMapping("/overview")
    public Map<String, Object> getOverview() {
        return statisticsService.getOverview();
    }

    @Operation(summary = "各部门人数统计（ECharts柱状图）")
    @GetMapping("/dept")
    public Map<String, Object> getDeptStats() {
        return statisticsService.getDeptStats();
    }
}
