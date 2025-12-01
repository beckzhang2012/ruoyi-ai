package org.ruoyi.chat.controller.statistics;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.ruoyi.chat.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.chat.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.chat.service.IAiUsageStatisticsService;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.common.excel.utils.ExcelUtil;
import org.ruoyi.common.log.annotation.Log;
import org.ruoyi.common.log.enums.BusinessType;
import org.ruoyi.common.web.core.BaseController;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * AI使用统计
 *
 * @author ageerle
 * @date 2025-04-08
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
    public TableDataInfo<AiUsageStatisticsVo> list(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        return aiUsageStatisticsService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询用户使用统计列表
     */
    @GetMapping("/user/list")
    public TableDataInfo<AiUsageStatisticsVo> listUserStatistics(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        return aiUsageStatisticsService.queryUserUsageStatistics(bo, pageQuery);
    }

    /**
     * 查询模型使用统计列表
     */
    @GetMapping("/model/list")
    public TableDataInfo<AiUsageStatisticsVo> listModelStatistics(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        return aiUsageStatisticsService.queryModelUsageStatistics(bo, pageQuery);
    }

    /**
     * 查询模型使用趋势数据
     */
    @GetMapping("/model/trend")
    public R<List<AiUsageStatisticsVo>> getModelUsageTrend(AiUsageStatisticsBo bo) {
        return R.ok(aiUsageStatisticsService.queryModelUsageTrend(bo));
    }

    /**
     * 导出AI使用统计列表
     */
    @Log(title = "AI使用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AiUsageStatisticsBo bo, HttpServletResponse response) {
        List<AiUsageStatisticsVo> list = aiUsageStatisticsService.queryList(bo);
        ExcelUtil.exportExcel(list, "AI使用统计", AiUsageStatisticsVo.class, response);
    }

    /**
     * 导出用户使用统计列表
     */
    @Log(title = "用户使用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/user/export")
    public void exportUserStatistics(AiUsageStatisticsBo bo, HttpServletResponse response) {
        List<AiUsageStatisticsVo> list = aiUsageStatisticsService.queryUserUsageStatistics(bo, null).getRows();
        ExcelUtil.exportExcel(list, "用户使用统计", AiUsageStatisticsVo.class, response);
    }

    /**
     * 导出模型使用统计列表
     */
    @Log(title = "模型使用统计", businessType = BusinessType.EXPORT)
    @PostMapping("/model/export")
    public void exportModelStatistics(AiUsageStatisticsBo bo, HttpServletResponse response) {
        List<AiUsageStatisticsVo> list = aiUsageStatisticsService.queryModelUsageStatistics(bo, null).getRows();
        ExcelUtil.exportExcel(list, "模型使用统计", AiUsageStatisticsVo.class, response);
    }

    /**
     * 获取AI使用统计详细信息
     *
     * @param id 主键
     */
    @GetMapping("/{id}")
    public R<AiUsageStatisticsVo> getInfo(@NotNull(message = "主键不能为空")
                                         @PathVariable Long id) {
        return R.ok(aiUsageStatisticsService.queryById(id));
    }

    /**
     * 新增AI使用统计
     */
    @Log(title = "AI使用统计", businessType = BusinessType.INSERT)
    @PostMapping()
    public R<Long> add(@Validated(AddGroup.class) @RequestBody AiUsageStatisticsBo bo) {
        return R.ok(aiUsageStatisticsService.insertByBo(bo));
    }

    /**
     * 修改AI使用统计
     */
    @Log(title = "AI使用统计", businessType = BusinessType.UPDATE)
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AiUsageStatisticsBo bo) {
        return toAjax(aiUsageStatisticsService.updateByBo(bo));
    }

    /**
     * 删除AI使用统计
     *
     * @param ids 主键串
     */
    @Log(title = "AI使用统计", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(aiUsageStatisticsService.deleteWithValidByIds(List.of(ids), true));
    }

    /**
     * 生成统计数据
     */
    @Log(title = "AI使用统计", businessType = BusinessType.GENCODE)
    @PostMapping("/generate")
    public R<Void> generateStatistics(@RequestBody AiUsageStatisticsBo bo) {
        aiUsageStatisticsService.generateStatistics(bo);
        return R.ok();
    }
}