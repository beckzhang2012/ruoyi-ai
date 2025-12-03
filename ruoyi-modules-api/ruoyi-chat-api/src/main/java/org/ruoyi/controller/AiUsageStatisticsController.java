package org.ruoyi.controller;

import lombok.RequiredArgsConstructor;
import org.ruoyi.common.annotation.Log;
import org.ruoyi.common.enums.BusinessType;
import org.ruoyi.common.utils.poi.ExcelUtil;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.service.IAiUsageStatisticsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * AI使用统计Controller
 * 用于处理HTTP请求，提供用户使用统计和模型使用统计的查询、导出等功能
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/chat/usage/statistics")
public class AiUsageStatisticsController {

    private final IAiUsageStatisticsService aiUsageStatisticsService;

    /**
     * 查询AI使用统计列表
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:list')")
    @GetMapping("/list")
    public TableDataInfo<AiUsageStatisticsVo> list(AiUsageStatisticsBo bo) {
        return aiUsageStatisticsService.queryPageList(bo, bo);
    }

    /**
     * 按用户统计使用情况
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:user')")
    @GetMapping("/user")
    public TableDataInfo<AiUsageStatisticsVo> statisticByUser(AiUsageStatisticsBo bo) {
        List<AiUsageStatisticsVo> list = aiUsageStatisticsService.statisticByUser(bo);
        return TableDataInfo.build(list);
    }

    /**
     * 按模型统计使用情况
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:model')")
    @GetMapping("/model")
    public TableDataInfo<AiUsageStatisticsVo> statisticByModel(AiUsageStatisticsBo bo) {
        List<AiUsageStatisticsVo> list = aiUsageStatisticsService.statisticByModel(bo);
        return TableDataInfo.build(list);
    }

    /**
     * 按时间趋势统计
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:trend')")
    @GetMapping("/trend")
    public TableDataInfo<AiUsageStatisticsVo> statisticByTimeTrend(AiUsageStatisticsBo bo) {
        List<AiUsageStatisticsVo> list = aiUsageStatisticsService.statisticByTimeTrend(bo);
        return TableDataInfo.build(list);
    }

    /**
     * 导出AI使用统计列表
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:export')")
    @Log(title = "AI使用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiUsageStatisticsBo bo) {
        List<AiUsageStatisticsVo> list = aiUsageStatisticsService.queryList(bo);
        ExcelUtil.exportExcel(list, "AI使用统计", AiUsageStatisticsVo.class, response);
    }

    /**
     * 导出用户使用统计列表
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:export')")
    @Log(title = "用户使用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/user/export")
    public void exportUserStatistics(HttpServletResponse response, AiUsageStatisticsBo bo) {
        List<AiUsageStatisticsVo> list = aiUsageStatisticsService.statisticByUser(bo);
        ExcelUtil.exportExcel(list, "用户使用统计", AiUsageStatisticsVo.class, response);
    }

    /**
     * 导出模型使用统计列表
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:export')")
    @Log(title = "模型使用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/model/export")
    public void exportModelStatistics(HttpServletResponse response, AiUsageStatisticsBo bo) {
        List<AiUsageStatisticsVo> list = aiUsageStatisticsService.statisticByModel(bo);
        ExcelUtil.exportExcel(list, "模型使用统计", AiUsageStatisticsVo.class, response);
    }

    /**
     * 同步统计数据
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:sync')")
    @Log(title = "AI使用统计", businessType = BusinessType.IMPORT)
    @PostMapping("/sync")
    public String syncStatistics(@RequestParam Date statDate, @RequestParam String statType) {
        Boolean result = aiUsageStatisticsService.syncStatistics(statDate, statType);
        return result ? "同步成功" : "同步失败";
    }

    /**
     * 同步日统计数据
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:sync')")
    @Log(title = "AI使用统计", businessType = BusinessType.IMPORT)
    @PostMapping("/sync/daily")
    public String syncDailyStatistics(@RequestParam Date statDate) {
        Boolean result = aiUsageStatisticsService.syncDailyStatistics(statDate);
        return result ? "日统计数据同步成功" : "日统计数据同步失败";
    }

    /**
     * 同步周统计数据
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:sync')")
    @Log(title = "AI使用统计", businessType = BusinessType.IMPORT)
    @PostMapping("/sync/weekly")
    public String syncWeeklyStatistics(@RequestParam Date statDate) {
        Boolean result = aiUsageStatisticsService.syncWeeklyStatistics(statDate);
        return result ? "周统计数据同步成功" : "周统计数据同步失败";
    }

    /**
     * 同步月统计数据
     */
    @PreAuthorize("@ss.hasPermi('chat:usage:statistics:sync')")
    @Log(title = "AI使用统计", businessType = BusinessType.IMPORT)
    @PostMapping("/sync/monthly")
    public String syncMonthlyStatistics(@RequestParam Date statDate) {
        Boolean result = aiUsageStatisticsService.syncMonthlyStatistics(statDate);
        return result ? "月统计数据同步成功" : "月统计数据同步失败";
    }
}