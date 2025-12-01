package org.ruoyi.chat.domain.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 用户使用统计VO
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Data
public class UserUsageStatisticsVO {

    @ExcelProperty(value = "用户ID")
    private Long userId;

    @ExcelProperty(value = "对话次数")
    private Long conversationCount;

    @ExcelProperty(value = "消息数量")
    private Long messageCount;

    @ExcelProperty(value = "总Token消耗")
    private Long totalTokens;

    @ExcelProperty(value = "总费用")
    private BigDecimal totalCost;
}