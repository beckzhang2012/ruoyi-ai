package org.ruoyi.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.core.domain.BaseEntity;
import org.ruoyi.domain.AiUsageStatistics;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * AI使用统计表视图对象 ai_usage_statistics
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AiUsageStatisticsVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
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
     * 用户名
     */
    private String userName;

    /**
     * 统计类型名称
     */
    private String statTypeName;

    /**
     * 映射转换
     */
    @Mapper
    public interface AiUsageStatisticsVoMapper {
        AiUsageStatisticsVo toVo(AiUsageStatistics entity);
        AiUsageStatistics toEntity(AiUsageStatisticsVo vo);
    }

    public static final AiUsageStatisticsVoMapper INSTANCE = Mappers.getMapper(AiUsageStatisticsVoMapper.class);

}