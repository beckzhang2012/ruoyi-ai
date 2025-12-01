package org.ruoyi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ruoyi.core.mapper.BaseMapperPlus;
import org.ruoyi.domain.AiUsageStatistics;
import org.ruoyi.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.domain.bo.AiUsageStatisticsBo;

import java.util.List;
import java.util.Date;

/**
 * AI使用统计表Mapper接口
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Mapper
public interface AiUsageStatisticsMapper extends BaseMapperPlus<AiUsageStatistics, AiUsageStatisticsVo> {

    /**
     * 按用户统计使用情况
     *
     * @param bo 查询条件
     * @return 统计结果
     */
    List<AiUsageStatisticsVo> selectUserUsageStatistics(AiUsageStatisticsBo bo);

    /**
     * 按模型统计使用情况
     *
     * @param bo 查询条件
     * @return 统计结果
     */
    List<AiUsageStatisticsVo> selectModelUsageStatistics(AiUsageStatisticsBo bo);

    /**
     * 统计用户使用趋势
     *
     * @param userId 用户ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 趋势数据
     */
    List<AiUsageStatisticsVo> selectUserUsageTrend(@Param("userId") Long userId, 
                                                       @Param("startDate") Date startDate, 
                                                       @Param("endDate") Date endDate);

    /**
     * 统计模型使用趋势
     *
     * @param modelName 模型名称
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 趋势数据
     */
    List<AiUsageStatisticsVo> selectModelUsageTrend(@Param("modelName") String modelName, 
                                                        @Param("startDate") Date startDate, 
                                                        @Param("endDate") Date endDate);

    /**
     * 检查是否已存在该统计数据
     *
     * @param statDate 统计日期
     * @param statType 统计类型
     * @param userId 用户ID
     * @param modelName 模型名称
     * @return 结果
     */
    int checkExists(@Param("statDate") Date statDate, 
                     @Param("statType") String statType, 
                     @Param("userId") Long userId, 
                     @Param("modelName") String modelName);

}