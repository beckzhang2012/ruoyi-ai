package org.ruoyi.chat.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.core.domain.BaseEntity;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * AI使用统计表实体类
 *
 * @author ruoyi
 * @date 2025-10-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ai_usage_statistics")
public class AiUsageStatistics extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 统计日期
     */
    @TableField("stat_date")
    private Date statDate;

    /**
     * 统计类型（1-日统计，2-周统计，3-月统计）
     */
    @TableField("stat_type")
    private String statType;

    /**
     * 用户ID（NULL表示全部用户）
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 模型名称（NULL表示全部模型）
     */
    @TableField("model_name")
    private String modelName;

    /**
     * 对话次数
     */
    @TableField("conversation_count")
    private Integer conversationCount;

    /**
     * 消息数量
     */
    @TableField("message_count")
    private Integer messageCount;

    /**
     * 总token数
     */
    @TableField("total_tokens")
    private Long totalTokens;

    /**
     * 总费用
     */
    @TableField("total_cost")
    private BigDecimal totalCost;

}
