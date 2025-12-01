package org.ruoyi.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.core.domain.BaseEntity;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * AI使用统计表业务对象 ai_usage_statistics
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AiUsageStatisticsBo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 统计日期
     */
    @NotNull(message = "统计日期不能为空", groups = {AddGroup.class, EditGroup.class})
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

    /**
     * 开始日期（查询条件）
     */
    private Date startDate;

    /**
     * 结束日期（查询条件）
     */
    private Date endDate;

    /**
     * 映射转换
     */
    public interface AiUsageStatisticsBoMapper {
        AiUsageStatisticsBo toBo(AiUsageStatistics entity);
        AiUsageStatistics toEntity(AiUsageStatisticsBo bo);
    }

}