package org.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.ruoyi.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * AI使用统计实体类
 * 对应数据库表 ai_usage_statistics
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_usage_statistics")
public class AiUsageStatistics extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 统计日期
     */
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}