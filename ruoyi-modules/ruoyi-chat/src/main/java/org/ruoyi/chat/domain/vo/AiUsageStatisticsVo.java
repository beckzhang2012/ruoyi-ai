package org.ruoyi.chat.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * AI使用统计视图对象
 *
 * @author ruoyi
 * @date 2025-10-26
 */
@Data
public class AiUsageStatisticsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 统计日期
     */
    @ExcelProperty(value = "统计日期")
    private Date statDate;

    /**
     * 统计类型（1-日统计，2-周统计，3-月统计）
     */
    @ExcelProperty(value = "统计类型")
    private String statType;

    /**
     * 用户ID（NULL表示全部用户）
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 模型名称（NULL表示全部模型）
     */
    @ExcelProperty(value = "模型名称")
    private String modelName;

    /**
     * 对话次数
     */
    @ExcelProperty(value = "对话次数")
    private Integer conversationCount;

    /**
     * 消息数量
     */
    @ExcelProperty(value = "消息数量")
    private Integer messageCount;

    /**
     * 总token数
     */
    @ExcelProperty(value = "总token数")
    private Long totalTokens;

    /**
     * 总费用
     */
    @ExcelProperty(value = "总费用")
    private BigDecimal totalCost;

}
