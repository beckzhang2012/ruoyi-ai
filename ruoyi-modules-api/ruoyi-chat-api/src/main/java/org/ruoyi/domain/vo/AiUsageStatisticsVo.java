package org.ruoyi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AI使用统计视图对象
 * 用于返回前端需要的统计数据
 */
@Data
public class AiUsageStatisticsVo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 统计日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date statDate;

    /**
     * 统计类型（1-日统计，2-周统计，3-月统计）
     */
    private String statType;

    /**
     * 用户ID（NULL表示全部用户）
     */
    private Long userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 模型名称（NULL表示全部模型）
     */
    private String modelName;

    /**
     * 对话次数
     */
    private Integer conversationCount;

    /**
     * 消息数量
     */
    private Integer messageCount;

    /**
     * 总token数
     */
    private Long totalTokens;

    /**
     * 总费用
     */
    private BigDecimal totalCost;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    // 新增汇总字段
    /**
     * 平均每次对话消耗token数
     */
    private BigDecimal avgTokensPerConversation;

    /**
     * 平均每次对话费用
     */
    private BigDecimal avgCostPerConversation;
}