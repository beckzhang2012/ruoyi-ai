package org.ruoyi.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ruoyi.chat.domain.AiUsageStatistics;
import org.ruoyi.chat.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.chat.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * AI使用统计Mapper接口
 *
 * @author ruoyi
 * @date 2025-10-26
 */
@Mapper
public interface AiUsageStatisticsMapper extends BaseMapperPlus<AiUsageStatistics, AiUsageStatisticsVo> {

    /**
     * 查询用户使用统计数据
     *
     * @param bo 查询条件
     * @return 用户使用统计数据列表
     */
    List<AiUsageStatisticsVo> queryUserUsageStatistics(@Param("bo") AiUsageStatisticsBo bo);

    /**
     * 查询模型使用统计数据
     *
     * @param bo 查询条件
     * @return 模型使用统计数据列表
     */
    List<AiUsageStatisticsVo> queryModelUsageStatistics(@Param("bo") AiUsageStatisticsBo bo);

    /**
     * 查询模型使用趋势数据
     *
     * @param bo 查询条件
     * @return 模型使用趋势数据列表
     */
    List<AiUsageStatisticsVo> queryModelUsageTrend(@Param("bo") AiUsageStatisticsBo bo);

}
