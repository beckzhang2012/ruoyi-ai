package org.ruoyi.chat.controller.chat;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.chat.domain.entity.AiUsageStatistics;
import org.ruoyi.chat.domain.dto.AiUsageStatisticsQueryDTO;
import org.ruoyi.chat.domain.vo.UserUsageStatisticsVO;
import org.ruoyi.chat.domain.vo.ModelUsageStatisticsVO;
import org.ruoyi.chat.service.chat.IAiUsageStatisticsService;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.excel.utils.ExcelUtil;
import org.ruoyi.common.log.annotation.Log;
import org.ruoyi.common.log.enums.BusinessType;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * AI使用统计控制器
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/statistics")
public class AiUsageStatisticsController extends BaseController {

    private final IAiUsageStatisticsService aiUsageStatisticsService;

    /**
     * 查询AI使用统计列表
     */
    @GetMapping("/list")
    public TableDataInfo<AiUsageStatistics> list(AiUsageStatisticsQueryDTO queryDTO) {
        return aiUsageStatisticsService.queryPageList(queryDTO);
    }

    /**
     * 按用户统计使用情况
     */
    @GetMapping("/user/list")
    public R<List<Map<String, Object>>> getUserUsageStatistics(AiUsageStatisticsQueryDTO queryDTO) {
        List<Map<String, Object>> result = aiUsageStatisticsService.getUserUsageStatistics(queryDTO);
        return R.ok(result);
    }

    /**
     * 按模型统计使用情况
     */
    @GetMapping("/model/list")
    public R<List<Map<String, Object>>> getModelUsageStatistics(AiUsageStatisticsQueryDTO queryDTO) {
        List<Map<String, Object>> result = aiUsageStatisticsService.getModelUsageStatistics(queryDTO);
        return R.ok(result);
    }

    /**
     * 获取用户对话次数统计
     */
    @GetMapping("/user/conversation")
    public R<List<Map<String, Object>>> countUserConversations(AiUsageStatisticsQueryDTO queryDTO) {
        List<Map<String, Object>> result = aiUsageStatisticsService.countUserConversations(queryDTO);
        return R.ok(result);
    }

    /**
     * 获取模型使用趋势
     */
    @GetMapping("/model/trend")
    public R<List<Map<String, Object>>> getModelUsageTrend(AiUsageStatisticsQueryDTO queryDTO) {
        List<Map<String, Object>> result = aiUsageStatisticsService.getModelUsageTrend(queryDTO);
        return R.ok(result);
    }

    /**
     * 导出AI使用统计列表
     */
    @Log(title = "AI使用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiUsageStatisticsQueryDTO queryDTO, HttpServletResponse response) {
        List<AiUsageStatistics> list = aiUsageStatisticsService.queryList(queryDTO);
        ExcelUtil.exportExcel(list, "AI使用统计", AiUsageStatistics.class, response);
    }

    /**
     * 导出用户使用统计
     */
    @Log(title = "用户使用统计导出", businessType = BusinessType.EXPORT)
    @PostMapping("/user/export")
    public void exportUserStatistics(AiUsageStatisticsQueryDTO queryDTO, HttpServletResponse response) {
        List<Map<String, Object>> list = aiUsageStatisticsService.getUserUsageStatistics(queryDTO);
        List<UserUsageStatisticsVO> voList = list.stream().map(map -> {
            UserUsageStatisticsVO vo = new UserUsageStatisticsVO();
            BeanUtils.copyProperties(map, vo);
            return vo;
        }).toList();
        ExcelUtil.exportExcel(voList, "用户使用统计", UserUsageStatisticsVO.class, response);
    }

    /**
     * 导出模型使用统计
     */
    @Log(title = "模型使用统计导出", businessType = BusinessType.EXPORT)
    @PostMapping("/model/export")
    public void exportModelStatistics(AiUsageStatisticsQueryDTO queryDTO, HttpServletResponse response) {
        List<Map<String, Object>> list = aiUsageStatisticsService.getModelUsageStatistics(queryDTO);
        List<ModelUsageStatisticsVO> voList = list.stream().map(map -> {
            ModelUsageStatisticsVO vo = new ModelUsageStatisticsVO();
            BeanUtils.copyProperties(map, vo);
            return vo;
        }).toList();
        ExcelUtil.exportExcel(voList, "模型使用统计", ModelUsageStatisticsVO.class, response);
    }

    /**
     * 同步指定日期的统计数据
     */
    @Log(title = "统计数据同步", businessType = BusinessType.UPDATE)
    @PostMapping("/sync/{statDate}")
    public R<Boolean> syncStatistics(@NotNull(message = "统计日期不能为空") @PathVariable String statDate) {
        LocalDate date = LocalDate.parse(statDate);
        boolean success = aiUsageStatisticsService.syncStatisticsFromChatMessage(date);
        return R.ok(success);
    }

    /**
     * 批量同步统计数据
     */
    @Log(title = "批量统计数据同步", businessType = BusinessType.UPDATE)
    @PostMapping("/sync/batch")
    public R<Boolean> batchSyncStatistics(@RequestParam String startDate, @RequestParam String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        boolean success = aiUsageStatisticsService.batchSyncStatistics(start, end);
        return R.ok(success);
    }
}