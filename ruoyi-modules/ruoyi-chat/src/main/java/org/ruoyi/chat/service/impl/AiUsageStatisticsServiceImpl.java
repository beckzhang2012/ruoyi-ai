package org.ruoyi.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ruoyi.chat.domain.AiUsageStatistics;
import org.ruoyi.chat.domain.bo.AiUsageStatisticsBo;
import org.ruoyi.chat.domain.vo.AiUsageStatisticsVo;
import org.ruoyi.chat.mapper.AiUsageStatisticsMapper;
import org.ruoyi.chat.service.IAiUsageStatisticsService;
import org.ruoyi.common.core.utils.MapstructUtils;
import org.ruoyi.common.core.utils.StringUtils;
import org.ruoyi.common.excel.utils.ExcelUtil;
import org.ruoyi.common.satoken.utils.LoginHelper;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.domain.ChatMessage;
import org.ruoyi.mapper.ChatMessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AI使用统计Service业务层处理
 *
 * @author ruoyi
 * @date 2025-10-26
 */
@RequiredArgsConstructor
@Service
public class AiUsageStatisticsServiceImpl implements IAiUsageStatisticsService {

    private final AiUsageStatisticsMapper baseMapper;
    private final ChatMessageMapper chatMessageMapper;

    /**
     * 查询AI使用统计
     */
    @Override
    public AiUsageStatisticsVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询AI使用统计列表
     */
    @Override
    public TableDataInfo<AiUsageStatisticsVo> queryPageList(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        // 只有非管理员才自动设置为自己的 ID
        if (!LoginHelper.isSuperAdmin()) {
            bo.setUserId(LoginHelper.getUserId());
        }
        LambdaQueryWrapper<AiUsageStatistics> lqw = buildQueryWrapper(bo);
        Page<AiUsageStatisticsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询AI使用统计列表
     */
    @Override
    public List<AiUsageStatisticsVo> queryList(AiUsageStatisticsBo bo) {
        // 只有非管理员才自动设置为自己的 ID
        if (!LoginHelper.isSuperAdmin()) {
            bo.setUserId(LoginHelper.getUserId());
        }
        LambdaQueryWrapper<AiUsageStatistics> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AiUsageStatistics> buildQueryWrapper(AiUsageStatisticsBo bo) {
        LambdaQueryWrapper<AiUsageStatistics> lqw = Wrappers.lambdaQuery();
        lqw.ge(bo.getStatDateStart() != null, AiUsageStatistics::getStatDate, bo.getStatDateStart());
        lqw.le(bo.getStatDateEnd() != null, AiUsageStatistics::getStatDate, bo.getStatDateEnd());
        lqw.eq(bo.getStatType() != null, AiUsageStatistics::getStatType, bo.getStatType());
        lqw.eq(bo.getUserId() != null, AiUsageStatistics::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getModelName()), AiUsageStatistics::getModelName, bo.getModelName());
        lqw.orderByDesc(AiUsageStatistics::getStatDate);
        return lqw;
    }

    /**
     * 生成AI使用统计
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean generateStatistics() {
        // 生成昨天的统计数据
        LocalDate yesterday = LocalDate.now().minusDays(1);
        return generateStatisticsByDate(yesterday);
    }

    /**
     * 按指定日期生成AI使用统计
     */
    private Boolean generateStatisticsByDate(LocalDate statDate) {
        // 格式化日期
        String dateStr = statDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        // 构建查询条件，查询指定日期的所有聊天消息
        LambdaQueryWrapper<ChatMessage> lqw = Wrappers.lambdaQuery();
        lqw.ge(ChatMessage::getCreateTime, dateStr + " 00:00:00");
        lqw.lt(ChatMessage::getCreateTime, dateStr + " 23:59:59");

        // 查询指定日期的所有聊天消息
        List<ChatMessage> chatMessages = chatMessageMapper.selectList(lqw);

        if (chatMessages.isEmpty()) {
            return true;
        }

        // 按用户和模型分组，统计使用情况
        Map<Long, Map<String, List<ChatMessage>>> userModelMap = chatMessages.stream()
                .collect(Collectors.groupingBy(ChatMessage::getUserId,
                        Collectors.groupingBy(ChatMessage::getModelName)));

        // 处理统计数据
        for (Map.Entry<Long, Map<String, List<ChatMessage>>> userEntry : userModelMap.entrySet()) {
            Long userId = userEntry.getKey();
            Map<String, List<ChatMessage>> modelMap = userEntry.getValue();

            for (Map.Entry<String, List<ChatMessage>> modelEntry : modelMap.entrySet()) {
                String modelName = modelEntry.getKey();
                List<ChatMessage> modelMessages = modelEntry.getValue();

                // 统计会话次数（按session_id分组）
                Integer conversationCount = modelMessages.stream()
                        .collect(Collectors.groupingBy(ChatMessage::getSessionId))
                        .size();

                // 统计消息数量
                Integer messageCount = modelMessages.size();

                // 统计token消耗
                long totalTokens = modelMessages.stream()
                        .mapToLong(ChatMessage::getTotalTokens)
                        .sum();

                // 统计费用
                BigDecimal totalCost = modelMessages.stream()
                        .map(ChatMessage::getDeductCost)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);

                // 构建统计实体
                AiUsageStatistics statistics = new AiUsageStatistics();
                statistics.setStatDate(Date.from(statDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                statistics.setStatType("1"); // 1-按天统计
                statistics.setUserId(userId);
                statistics.setModelName(modelName);
                statistics.setConversationCount(conversationCount);
                statistics.setMessageCount(messageCount);
                statistics.setTotalTokens(totalTokens);
                statistics.setTotalCost(totalCost);

                // 先删除已存在的统计数据
                LambdaQueryWrapper<AiUsageStatistics> deleteLqw = Wrappers.lambdaQuery();
                deleteLqw.eq(AiUsageStatistics::getStatDate, statDate);
                deleteLqw.eq(AiUsageStatistics::getStatType, 1);
                deleteLqw.eq(AiUsageStatistics::getUserId, userId);
                deleteLqw.eq(AiUsageStatistics::getModelName, modelName);
                baseMapper.delete(deleteLqw);

                // 插入新的统计数据
                baseMapper.insert(statistics);
            }
        }

        return true;
    }

    /**
     * 导出AI使用统计Excel
     */
    @Override
    public void exportExcel(AiUsageStatisticsBo bo, HttpServletResponse response) {
        List<AiUsageStatisticsVo> list = queryList(bo);
        ExcelUtil.exportExcel(list, "AI使用统计", AiUsageStatisticsVo.class, response);
    }

    /**
     * 查询用户使用统计
     */
    @Override
    public TableDataInfo<AiUsageStatisticsVo> queryUserUsageStatistics(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        // 只有非管理员才自动设置为自己的 ID
        if (!LoginHelper.isSuperAdmin()) {
            bo.setUserId(LoginHelper.getUserId());
        }
        // 使用Mapper接口中定义的查询方法
        List<AiUsageStatisticsVo> voList = baseMapper.queryUserUsageStatistics(bo);
        // 分页处理
        return TableDataInfo.build(Page.<AiUsageStatisticsVo>of(pageQuery.getPageNum(), pageQuery.getPageSize(), voList.size()).setRecords(voList));
    }

    /**
     * 查询模型使用统计
     */
    @Override
    public TableDataInfo<AiUsageStatisticsVo> queryModelUsageStatistics(AiUsageStatisticsBo bo, PageQuery pageQuery) {
        // 使用Mapper接口中定义的查询方法
        List<AiUsageStatisticsVo> voList = baseMapper.queryModelUsageStatistics(bo);
        // 分页处理
        return TableDataInfo.build(Page.<AiUsageStatisticsVo>of(pageQuery.getPageNum(), pageQuery.getPageSize(), voList.size()).setRecords(voList));
    }

    /**
     * 查询模型使用趋势
     */
    @Override
    public List<AiUsageStatisticsVo> queryModelUsageTrend(AiUsageStatisticsBo bo) {
        // 使用Mapper接口中定义的查询方法
        return baseMapper.queryModelUsageTrend(bo);
    }

    /**
     * 生成AI使用统计
     */
    @Override
    public Boolean generateStatistics(AiUsageStatisticsBo bo) {
        // 生成指定日期的统计数据
        LocalDate statDate = LocalDate.parse(bo.getStatDateStr());
        return generateStatisticsByDate(statDate);
    }

    /**
     * 新增AI使用统计
     */
    @Override
    public Long insertByBo(AiUsageStatisticsBo bo) {
        AiUsageStatistics statistics = MapstructUtils.convert(bo, AiUsageStatistics.class);
        baseMapper.insert(statistics);
        return statistics.getId();
    }

    /**
     * 修改AI使用统计
     */
    @Override
    public Boolean updateByBo(AiUsageStatisticsBo bo) {
        AiUsageStatistics statistics = MapstructUtils.convert(bo, AiUsageStatistics.class);
        return baseMapper.updateById(statistics) > 0;
    }

    /**
     * 删除AI使用统计
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        // 如果需要验证，则需要先查询数据是否存在
        if (isValid) {
            for (Long id : ids) {
                AiUsageStatistics statistics = baseMapper.selectById(id);
                if (statistics == null) {
                    return false;
                }
            }
        }
        // 删除数据
        baseMapper.deleteBatchIds(ids);
        return true;
    }
}