package org.ruoyi.chat.domain.dto;

import lombok.Data;
import java.time.LocalDate;

/**
 * AI使用统计查询DTO
 * 
 * @author ruoyi
 * @date 2025-12-01
 */
@Data
public class AiUsageStatisticsQuery {

    /** 开始日期 */
    private LocalDate beginDate;

    /** 结束日期 */
    private LocalDate endDate;

    /** 用户ID */
    private Long userId;

    /** 用户名称 */
    private String userName;

    /** 模型名称 */
    private String modelName;

    /** 统计类型（1-日统计，2-周统计，3-月统计） */
    private String statType;
}
