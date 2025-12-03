package org.ruoyi.service;

import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.domain.vo.AiUsageStatisticsVo;

import java.util.Date;
import java.util.List;

/**
 * AI使用统计Service接口
 * 定义统计模块的业务逻辑
 */
public interface IAiUsageStatisticsService {

    /**
     * 查询AI使用统计列表
     *
     * @param bo 业务对象
     * @param pageQuery 分页查询
     * @return 分页统计列表
     */
    TableDataInfo<AiUsageStatisticsVo> queryPageList(AiUsageStatisticsBo bo, PageQuery pageQuery);

    /**
     * 查询AI使用统计列表
     *
     * @param bo 业务对象
     * @return 统计列表
     */
    List<AiUsageStatisticsVo> queryList(AiUsageStatisticsBo bo);

    /**
     * 按用户统计使用情况
     *
     * @param bo 业务对象
     * @return 用户统计列表
     */
    List<AiUsageStatisticsVo> statisticByUser(AiUsageStatisticsBo bo);

    /**
     * 按模型统计使用情况
     *
     * @param bo 业务对象
     * @return 模型统计列表
     */
    List<AiUsageStatisticsVo> statisticByModel(AiUsageStatisticsBo bo);

    /**
     * 按时间趋势统计
     *
     * @param bo 业务对象
     * @return 时间趋势统计列表
     */
    List<AiUsageStatisticsVo> statisticByTimeTrend(AiUsageStatisticsBo bo);

    /**
     * 同步统计数据
     *
     * @param statDate 统计日期
     * @param statType 统计类型
     * @return 同步结果
     */
    Boolean syncStatistics(Date statDate, String statType);

    /**
     * 日统计数据同步
     *
     * @param statDate 统计日期
     * @return 同步结果
     */
    Boolean syncDailyStatistics(Date statDate);

    /**
     * 周统计数据同步
     *
     * @param statDate 统计日期
     * @return 同步结果
     */
    Boolean syncWeeklyStatistics(Date statDate);

    /**
     * 月统计数据同步
     *
     * @param statDate 统计日期
     * @return 同步结果
     */
    Boolean syncMonthlyStatistics(Date statDate);
}