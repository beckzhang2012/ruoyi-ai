package org.ruoyi.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.ruoyi.common.log.annotation.Log;
import org.ruoyi.common.log.enums.BusinessType;
import org.ruoyi.core.controller.BaseController;
import org.ruoyi.core.domain.R;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.domain.AiUsageStatistics;
import org.ruoyi.service.IAiUsageStatisticsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Collection;
import java.util.Date;

/**
 * AI使用统计表Controller
 *
 * @author ageerle
 * @date 2025-11-11
 */
@RestController
@RequestMapping("/ai/usage/statistics")
public class AiUsageStatisticsController extends BaseController {

    @Resource
    private IAiUsageStatisticsService aiUsageStatisticsService;

    /**
     * 查询AI使用统计表列表
     *
     * @param bo AI使用统计表业务对象
     * @param pageQuery 分页查询对象
     * @return AI使用统计表分页列表
     */
    @GetMapping("/list")
    public TableDataInfo<AiUsageStatisticsVo> list(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        return aiUsageStatisticsService.queryPageList(bo, pageQuery);
    }

    /**
     * 查询AI使用统计表详情
     *
     * @param id AI使用统计表主键
     * @return AI使用统计表详情
     */
    @GetMapping("/{id}")
    public R<AiUsageStatisticsVo> getInfo(@PathVariable("id") Long id) {
        return R.ok(aiUsageStatisticsService.queryById(id));
    }

    /**
     * 新增AI使用统计表
     *
     * @param bo AI使用统计表业务对象
     * @return 结果
     */
    @PostMapping
    @Log(title = "AI使用统计", businessType = BusinessType.INSERT)
    public R<Boolean> add(@Validated(AiUsageStatisticsBo.AddGroup.class) @RequestBody AiUsageStatisticsBo bo) {
        return R.ok(aiUsageStatisticsService.insertByBo(bo));
    }

    /**
     * 修改AI使用统计表
     *
     * @param bo AI使用统计表业务对象
     * @return 结果
     */
    @PutMapping
    @Log(title = "AI使用统计", businessType = BusinessType.UPDATE)
    public R<Boolean> edit(@Validated(EditGroup.class) @RequestBody AiUsageStatisticsBo bo) {
        return R.ok(aiUsageStatisticsService.updateByBo(bo));
    }

    /**
     * 删除AI使用统计表
     *
     * @param ids AI使用统计表主键集合
     * @return 结果
     */
    @DeleteMapping("/{ids}")
    @Log(title = "AI使用统计", businessType = BusinessType.DELETE)
    public R<Boolean> remove(@PathVariable Collection<Long> ids) {
        return R.ok(aiUsageStatisticsService.deleteWithValidByIds(ids, true));
    }

    /**
     * 按用户统计使用情况
     *
     * @param bo 查询条件
     * @param pageQuery 分页查询对象
     * @return 统计结果分页列表
     */
    @GetMapping("/user/list")
    public TableDataInfo<AiUsageStatisticsVo> getUserUsageStatistics(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        return aiUsageStatisticsService.getUserUsageStatisticsPage(bo, pageQuery);
    }

    /**
     * 按模型统计使用情况
     *
     * @param bo 查询条件
     * @param pageQuery 分页查询对象
     * @return 统计结果分页列表
     */
    @GetMapping("/model/list")
    public TableDataInfo<AiUsageStatisticsVo> getModelUsageStatistics(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        return aiUsageStatisticsService.getModelUsageStatisticsPage(bo, pageQuery);
    }

    /**
     * 统计用户使用趋势
     *
     * @param userId 用户ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 趋势数据
     */
    @GetMapping("/user/trend")
    public R<List<AiUsageStatisticsVo>> getUserUsageTrend(@RequestParam Long userId, 
                                                               @RequestParam Date startDate, 
                                                               @RequestParam Date endDate) {
        return R.ok(aiUsageStatisticsService.getUserUsageTrend(userId, startDate, endDate));
    }

    /**
     * 统计模型使用趋势
     *
     * @param modelName 模型名称
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 趋势数据
     */
    @GetMapping("/model/trend")
    public R<List<AiUsageStatisticsVo>> getModelUsageTrend(@RequestParam String modelName, 
                                                                @RequestParam Date startDate, 
                                                                @RequestParam Date endDate) {
        return R.ok(aiUsageStatisticsService.getModelUsageTrend(modelName, startDate, endDate));
    }

    /**
     * 生成每日统计数据
     *
     * @param statDate 统计日期
     * @return 结果
     */
    @PostMapping("/generate/daily")
    @Log(title = "生成每日统计数据", businessType = BusinessType.INSERT)
    public R<Boolean> generateDailyStatistics(@RequestParam Date statDate) {
        return R.ok(aiUsageStatisticsService.generateDailyStatistics(statDate));
    }

}