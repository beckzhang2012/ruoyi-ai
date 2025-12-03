package org.ruoyi.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.core.domain.BaseEntity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * AI使用统计业务对象
 * 用于接收前端传递的查询参数和统计数据
 */
@Data
public class AiUsageStatisticsBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 统计日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date statDate;

    /**
     * 统计类型（1-日统计，2-周统计，3-月统计）
     */
    @NotBlank(message = "统计类型不能为空", groups = {AddGroup.class, EditGroup.class})
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

    // 新增时间范围查询字段
    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
}