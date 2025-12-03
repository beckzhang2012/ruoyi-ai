package org.ruoyi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.ruoyi.domain.AiUsageStatistics;
import org.ruoyi.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.domain.vo.AiUsageStatisticsVo;

import java.util.Date;
import java.util.List;

/**
 * AI使用统计Mapper接口
 * 用于操作数据库表 ai_usage_statistics
 */
public interface AiUsageStatisticsMapper extends BaseMapper<AiUsageStatistics> {

    /**
     * 查询AI使用统计列表
     *
     * @param bo 业务对象
     * @return 统计列表
     */
    List<AiUsageStatisticsVo> queryList(@Param("bo") AiUsageStatisticsBo bo);

    /**
     * 查询AI使用统计分页列表
     *
     * @param page 分页对象
     * @param bo 业务对象
     * @return 分页统计列表
     */
    IPage<AiUsageStatisticsVo> queryPageList(Page<?> page, @Param("bo") AiUsageStatisticsBo bo);

    /**
     * 按用户统计使用情况
     *
     * @param bo 业务对象
     * @return 用户统计列表
     */
    List<AiUsageStatisticsVo> statisticByUser(@Param("bo") AiUsageStatisticsBo bo);

    /**
     * 按模型统计使用情况
     *
     * @param bo 业务对象
     * @return 模型统计列表
     */
    List<AiUsageStatisticsVo> statisticByModel(@Param("bo") AiUsageStatisticsBo bo);

    /**
     * 按时间趋势统计
     *
     * @param bo 业务对象
     * @return 时间趋势统计列表
     */
    List<AiUsageStatisticsVo> statisticByTimeTrend(@Param("bo") AiUsageStatisticsBo bo);

    /**
     * 从chat_message表同步统计数据
     *
     * @param statDate 统计日期
     * @param statType 统计类型
     * @return 影响行数
     */
    int syncFromChatMessage(@Param("statDate") Date statDate, @Param("statType") String statType);
}