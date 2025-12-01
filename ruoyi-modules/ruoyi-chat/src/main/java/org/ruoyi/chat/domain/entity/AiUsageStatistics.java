package org.ruoyi.chat.domain.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * AI使用统计实体类
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ai_usage_statistics")
public class AiUsageStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 统计日期
     */
    private LocalDate statDate;

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
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}