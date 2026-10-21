package com.qi.empsystem.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 统计相关的数据访问接口。
 *
 * 方法名必须和 StatisticsMapper.xml 里的 statement id 完全一致。
 */
@Mapper
public interface StatisticsMapper {

    /** 查询各部门人数（用于 ECharts 柱状图） */
    List<Map<String, Object>> listDeptEmployeeCount();

    /** 查询本月请假条数（按请假的开始日期落在本月算） */
    int countMonthLeave();
}
