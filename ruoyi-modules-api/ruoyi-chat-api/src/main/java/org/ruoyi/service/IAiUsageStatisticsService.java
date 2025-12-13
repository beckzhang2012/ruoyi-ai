package org.ruoyi.service;

import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.domain.AiUsageStatistics;

import java.util.Collection;
import java.util.List;
import java.util.Date;

/**
 * AI使用统计表Service接口
 *
 * @author ageerle
 * @date 2025-11-11
 */
public interface IAiUsageStatisticsService {

    /**
     * 查询AI使用统计表
     *
     * @param id AI使用统计表主键
     * @return AI使用统计表
     */
    AiUsageStatisticsVo queryById(Long id);

    /**
     * 查询AI使用统计表列表
     *
     * @param bo AI使用统计表业务对象
     * @return AI使用统计表集合
     */
    List<AiUsageStatisticsVo> queryList(AiUsageStatisticsBo bo);

    /**
     * 查询AI使用统计表分页列表
     *
     * @param bo AI使用统计表业务对象
     * @param pageQuery 分页查询对象
     * @return AI使用统计表分页列表
     */
    TableDataInfo<AiUsageStatisticsVo> queryPageList(AiUsageStatisticsBo bo, PageQuery pageQuery);

    /**
     * 新增AI使用统计表
     *
     * @param bo AI使用统计表业务对象
     * @return 结果
     */
    Boolean insertByBo(AiUsageStatisticsBo bo);

    /**
     * 修改AI使用统计表
     *
     * @param bo AI使用统计表业务对象
     * @return 结果
     */
    Boolean updateByBo(AiUsageStatisticsBo bo);

    /**
     * 批量删除AI使用统计表
     *
     * @param ids 需要删除的AI使用统计表主键集合
     * @param isValid 是否进行有效性校验
     * @return 结果
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 按用户统计使用情况
     *
     * @param bo 查询条件
     * @return 统计结果
     */
    List<AiUsageStatisticsVo> getUserUsageStatistics(AiUsageStatisticsBo bo);

    /**
     * 按用户统计使用情况分页列表
     *
     * @param bo 查询条件
     * @param pageQuery 分页查询对象
     * @return 统计结果分页列表
     */
    TableDataInfo<AiUsageStatisticsVo> getUserUsageStatisticsPage(AiUsageStatisticsBo bo, PageQuery pageQuery);

    /**
     * 按模型统计使用情况
     *
     * @param bo 查询条件
     * @return 统计结果
     */
    List<AiUsageStatisticsVo> getModelUsageStatistics(AiUsageStatisticsBo bo);

    /**
     * 按模型统计使用情况分页列表
     *
     * @param bo 查询条件
     * @param pageQuery 分页查询对象
     * @return 统计结果分页列表
     */
    TableDataInfo<AiUsageStatisticsVo> getModelUsageStatisticsPage(AiUsageStatisticsBo bo, PageQuery pageQuery);

    /**
     * 统计用户使用趋势
     *
     * @param userId 用户ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 趋势数据
     */
    List<AiUsageStatisticsVo> getUserUsageTrend(Long userId, Date startDate, Date endDate);

    /**
     * 统计模型使用趋势
     *
     * @param modelName 模型名称
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 趋势数据
     */
    List<AiUsageStatisticsVo> getModelUsageTrend(String modelName, Date startDate, Date endDate);

    /**
     * 生成每日统计数据
     *
     * @param statDate 统计日期
     * @return 结果
     */
    Boolean generateDailyStatistics(Date statDate);

}