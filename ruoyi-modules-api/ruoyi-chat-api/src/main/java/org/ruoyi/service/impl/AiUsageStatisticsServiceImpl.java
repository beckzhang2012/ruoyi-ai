package org.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.domain.AiUsageStatistics;
import org.ruoyi.mapper.AiUsageStatisticsMapper;
import org.ruoyi.service.IAiUsageStatisticsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Date;

/**
 * AI使用统计表Service业务层处理
 *
 * @author ageerle
 * @date 2025-11-11
 */
@RequiredArgsConstructor
@Service
public class AiUsageStatisticsServiceImpl implements IAiUsageStatisticsService {

    private final AiUsageStatisticsMapper baseMapper;

    /**
     * 查询AI使用统计表
     *
     * @param id AI使用统计表主键
     * @return AI使用统计表
     */
    @Override
    public AiUsageStatisticsVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询AI使用统计表列表
     *
     * @param bo AI使用统计表业务对象
     * @return AI使用统计表集合
     */
    @Override
    public List<AiUsageStatisticsVo> queryList(AiUsageStatisticsBo bo) {
        LambdaQueryWrapper<AiUsageStatistics> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询AI使用统计表分页列表
     *
     * @param bo AI使用统计表业务对象
     * @param pageQuery 分页查询对象
     * @return AI使用统计表分页列表
     */
    @Override
    public TableDataInfo<AiUsageStatisticsVo> queryPageList(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AiUsageStatistics> lqw = buildQueryWrapper(bo);
        Page<AiUsageStatisticsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 构建查询条件
     *
     * @param bo AI使用统计表业务对象
     * @return 查询条件
     */
    private LambdaQueryWrapper<AiUsageStatistics> buildQueryWrapper(AiUsageStatisticsBo bo) {
        LambdaQueryWrapper<AiUsageStatistics> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, AiUsageStatistics::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getModelName()), AiUsageStatistics::getModelName, bo.getModelName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatType()), AiUsageStatistics::getStatType, bo.getStatType());
        lqw.ge(bo.getStartDate() != null, AiUsageStatistics::getStatDate, bo.getStartDate());
        lqw.le(bo.getEndDate() != null, AiUsageStatistics::getStatDate, bo.getEndDate());
        lqw.orderByDesc(AiUsageStatistics::getStatDate);
        return lqw;
    }

    /**
     * 新增AI使用统计表
     *
     * @param bo AI使用统计表业务对象
     * @return 结果
     */
    @Override
    public Boolean insertByBo(AiUsageStatisticsBo bo) {
        AiUsageStatistics entity = AiUsageStatisticsBo.INSTANCE.toEntity(bo);
        return baseMapper.insert(entity) > 0;
    }

    /**
     * 修改AI使用统计表
     *
     * @param bo AI使用统计表业务对象
     * @return 结果
     */
    @Override
    public Boolean updateByBo(AiUsageStatisticsBo bo) {
        AiUsageStatistics entity = AiUsageStatisticsBo.INSTANCE.toEntity(bo);
        return baseMapper.updateById(entity) > 0;
    }

    /**
     * 批量删除AI使用统计表
     *
     * @param ids 需要删除的AI使用统计表主键集合
     * @param isValid 是否进行有效性校验
     * @return 结果
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 这里可以添加有效性校验逻辑，比如判断统计数据是否存在
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 按用户统计使用情况
     *
     * @param bo 查询条件
     * @return 统计结果
     */
    @Override
    public List<AiUsageStatisticsVo> getUserUsageStatistics(AiUsageStatisticsBo bo) {
        return baseMapper.selectUserUsageStatistics(bo);
    }

    /**
     * 按用户统计使用情况分页列表
     *
     * @param bo 查询条件
     * @param pageQuery 分页查询对象
     * @return 统计结果分页列表
     */
    @Override
    public TableDataInfo<AiUsageStatisticsVo> getUserUsageStatisticsPage(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        // 这里需要实现分页查询逻辑，可能需要自定义分页查询方法
        // 暂时使用列表查询方法模拟分页
        List<AiUsageStatisticsVo> list = baseMapper.selectUserUsageStatistics(bo);
        return TableDataInfo.build(list);
    }

    /**
     * 按模型统计使用情况
     *
     * @param bo 查询条件
     * @return 统计结果
     */
    @Override
    public List<AiUsageStatisticsVo> getModelUsageStatistics(AiUsageStatisticsBo bo) {
        return baseMapper.selectModelUsageStatistics(bo);
    }

    /**
     * 按模型统计使用情况分页列表
     *
     * @param bo 查询条件
     * @param pageQuery 分页查询对象
     * @return 统计结果分页列表
     */
    @Override
    public TableDataInfo<AiUsageStatisticsVo> getModelUsageStatisticsPage(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        // 这里需要实现分页查询逻辑，可能需要自定义分页查询方法
        // 暂时使用列表查询方法模拟分页
        List<AiUsageStatisticsVo> list = baseMapper.selectModelUsageStatistics(bo);
        return TableDataInfo.build(list);
    }

    /**
     * 统计用户使用趋势
     *
     * @param userId 用户ID
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 趋势数据
     */
    @Override
    public List<AiUsageStatisticsVo> getUserUsageTrend(Long userId, Date startDate, Date endDate) {
        return baseMapper.selectUserUsageTrend(userId, startDate, endDate);
    }

    /**
     * 统计模型使用趋势
     *
     * @param modelName 模型名称
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 趋势数据
     */
    @Override
    public List<AiUsageStatisticsVo> getModelUsageTrend(String modelName, Date startDate, Date endDate) {
        return baseMapper.selectModelUsageTrend(modelName, startDate, endDate);
    }

    /**
     * 生成每日统计数据
     *
     * @param statDate 统计日期
     * @return 结果
     */
    @Override
    public Boolean generateDailyStatistics(Date statDate) {
        // 这里需要实现每日统计数据的生成逻辑
        // 1. 从chat_message表中查询指定日期的所有消息
        // 2. 按用户和模型分组统计对话次数、消息数量、总token数和总费用
        // 3. 将统计结果插入或更新到ai_usage_statistics表中
        
        // 暂时返回true，表示生成成功
        return true;
    }

}