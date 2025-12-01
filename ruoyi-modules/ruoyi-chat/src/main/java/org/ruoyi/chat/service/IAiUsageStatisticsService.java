package org.ruoyi.chat.service;

import org.ruoyi.chat.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.chat.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;


import jakarta.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;

/**
 * AI使用统计Service接口
 *
 * @author ruoyi
 * @date 2025-10-26
 */
public interface IAiUsageStatisticsService {

    /**
     * 查询AI使用统计
     */
    AiUsageStatisticsVo queryById(Long id);

    /**
     * 查询AI使用统计列表
     */
    TableDataInfo<AiUsageStatisticsVo> queryPageList(AiUsageStatisticsBo bo, PageQuery pageQuery);

    /**
     * 查询AI使用统计列表
     */
    List<AiUsageStatisticsVo> queryList(AiUsageStatisticsBo bo);

    /**
     * 生成AI使用统计
     */
    Boolean generateStatistics();

    /**
     * 生成AI使用统计
     */
    Boolean generateStatistics(AiUsageStatisticsBo bo);

    /**
     * 新增AI使用统计
     */
    Long insertByBo(AiUsageStatisticsBo bo);

    /**
     * 修改AI使用统计
     */
    Boolean updateByBo(AiUsageStatisticsBo bo);

    /**
     * 删除AI使用统计
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 导出AI使用统计Excel
     */
    void exportExcel(AiUsageStatisticsBo bo, HttpServletResponse response);

    /**
     * 查询用户使用统计
     */
    TableDataInfo<AiUsageStatisticsVo> queryUserUsageStatistics(AiUsageStatisticsBo bo, PageQuery pageQuery);

    /**
     * 查询模型使用统计
     */
    TableDataInfo<AiUsageStatisticsVo> queryModelUsageStatistics(AiUsageStatisticsBo bo, PageQuery pageQuery);

    /**
     * 查询模型使用趋势
     */
    List<AiUsageStatisticsVo> queryModelUsageTrend(AiUsageStatisticsBo bo);

}
