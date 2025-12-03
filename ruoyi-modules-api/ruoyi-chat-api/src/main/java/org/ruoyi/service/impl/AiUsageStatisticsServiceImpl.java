package org.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ruoyi.common.core.utils.DateUtils;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.domain.AiUsageStatistics;
import org.ruoyi.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.mapper.AiUsageStatisticsMapper;
import org.ruoyi.service.IAiUsageStatisticsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * AI使用统计Service业务层处理
 * 实现IAiUsageStatisticsService接口中定义的业务逻辑
 */
@RequiredArgsConstructor
@Service
public class AiUsageStatisticsServiceImpl implements IAiUsageStatisticsService {

    private final AiUsageStatisticsMapper baseMapper;

    /**
     * 查询AI使用统计列表
     */
    @Override
    public TableDataInfo<AiUsageStatisticsVo> queryPageList(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        Page<AiUsageStatisticsVo> page = new Page<>(pageQuery.getPageNum(), pageQuery.getPageSize());
        IPage<AiUsageStatisticsVo> result = baseMapper.queryPageList(page, bo);
        return TableDataInfo.build(result);
    }

    /**
     * 查询AI使用统计列表
     */
    @Override
    public List<AiUsageStatisticsVo> queryList(AiUsageStatisticsBo bo) {
        return baseMapper.queryList(bo);
    }

    /**
     * 按用户统计使用情况
     */
    @Override
    public List<AiUsageStatisticsVo> statisticByUser(AiUsageStatisticsBo bo) {
        return baseMapper.statisticByUser(bo);
    }

    /**
     * 按模型统计使用情况
     */
    @Override
    public List<AiUsageStatisticsVo> statisticByModel(AiUsageStatisticsBo bo) {
        return baseMapper.statisticByModel(bo);
    }

    /**
     * 按时间趋势统计
     */
    @Override
    public List<AiUsageStatisticsVo> statisticByTimeTrend(AiUsageStatisticsBo bo) {
        return baseMapper.statisticByTimeTrend(bo);
    }

    /**
     * 同步统计数据
     */
    @Override
    public Boolean syncStatistics(Date statDate, String statType) {
        return baseMapper.syncFromChatMessage(statDate, statType) > 0;
    }

    /**
     * 日统计数据同步
     */
    @Override
    public Boolean syncDailyStatistics(Date statDate) {
        return baseMapper.syncFromChatMessage(statDate, "1") > 0;
    }

    /**
     * 周统计数据同步
     */
    @Override
    public Boolean syncWeeklyStatistics(Date statDate) {
        return baseMapper.syncFromChatMessage(statDate, "2") > 0;
    }

    /**
     * 月统计数据同步
     */
    @Override
    public Boolean syncMonthlyStatistics(Date statDate) {
        return baseMapper.syncFromChatMessage(statDate, "3") > 0;
    }
}