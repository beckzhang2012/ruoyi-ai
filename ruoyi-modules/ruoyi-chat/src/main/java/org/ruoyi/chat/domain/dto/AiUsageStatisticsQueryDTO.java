package org.ruoyi.chat.domain.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * AI使用统计查询DTO
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Data
public class AiUsageStatisticsQueryDTO {

    /**
     * 统计类型（1-日统计，2-周统计，3-月统计）
     */
    private String statType;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 开始日期
     */
    private LocalDate startTime;

    /**
     * 结束日期
     */
    private LocalDate endTime;

    /**
     * 分页页码
     */
    private Integer pageNum = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 10;
}