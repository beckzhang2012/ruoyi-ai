package org.ruoyi.chat.service.chat.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ruoyi.chat.domain.entity.AiUsageStatistics;
import org.ruoyi.chat.domain.dto.AiUsageStatisticsQueryDTO;
import org.ruoyi.chat.mapper.AiUsageStatisticsMapper;
import org.ruoyi.chat.service.chat.IAiUsageStatisticsService;
import org.ruoyi.core.page.TableDataInfo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * AI使用统计服务实现类
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Service
public class AiUsageStatisticsServiceImpl extends ServiceImpl<AiUsageStatisticsMapper, AiUsageStatistics> implements IAiUsageStatisticsService {

    @Override
    public TableDataInfo<AiUsageStatistics> queryPageList(AiUsageStatisticsQueryDTO queryDTO) {
        // 创建分页参数
        Page<AiUsageStatistics> page = new Page<>(queryDTO.getPageNum(), queryDTO.getPageSize());
        
        // 查询分页数据
        Page<AiUsageStatistics> result = baseMapper.selectStatisticsPage(page, queryDTO);
        
        // 构建分页结果
        return TableDataInfo.build(result);
    }

    @Override
    public List<AiUsageStatistics> queryList(AiUsageStatisticsQueryDTO queryDTO) {
        return baseMapper.selectStatisticsList(queryDTO);
    }

    @Override
    public List<Map<String, Object>> getUserUsageStatistics(AiUsageStatisticsQueryDTO queryDTO) {
        return baseMapper.selectUserUsageStatistics(queryDTO);
    }

    @Override
    public List<Map<String, Object>> getModelUsageStatistics(AiUsageStatisticsQueryDTO queryDTO) {
        return baseMapper.selectModelUsageStatistics(queryDTO);
    }

    @Override
    public List<Map<String, Object>> countUserConversations(AiUsageStatisticsQueryDTO queryDTO) {
        return baseMapper.countUserConversations(queryDTO);
    }

    @Override
    public List<Map<String, Object>> getModelUsageTrend(AiUsageStatisticsQueryDTO queryDTO) {
        return baseMapper.selectModelUsageTrend(queryDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean syncStatisticsFromChatMessage(LocalDate statDate) {
        // 先删除已有统计数据
        QueryWrapper<AiUsageStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("stat_date", statDate)
                    .eq("stat_type", "1");
        this.remove(queryWrapper);

        // 根据chat_message表统计用户使用情况
        AiUsageStatisticsQueryDTO queryDTO = new AiUsageStatisticsQueryDTO();
        queryDTO.setStartTime(statDate);
        queryDTO.setEndTime(statDate);
        queryDTO.setStatType("1");
        
        List<Map<String, Object>> userStats = baseMapper.selectUserUsageStatistics(queryDTO);
        
        // 保存用户统计数据
        for (Map<String, Object> userStat : userStats) {
            AiUsageStatistics statistics = new AiUsageStatistics();
            statistics.setStatDate(statDate);
            statistics.setStatType("1");
            statistics.setUserId(Long.valueOf(userStat.get("user_id").toString()));
            statistics.setConversationCount(Integer.valueOf(userStat.get("conversation_count").toString()));
            statistics.setMessageCount(Integer.valueOf(userStat.get("message_count").toString()));
            statistics.setTotalTokens(Long.valueOf(userStat.get("total_tokens").toString()));
            statistics.setTotalCost(new BigDecimal(userStat.get("total_cost").toString()));
            statistics.setCreateTime(LocalDateTime.now());
            statistics.setUpdateTime(LocalDateTime.now());
            this.save(statistics);
        }

        // 根据chat_message表统计模型使用情况
        List<Map<String, Object>> modelStats = baseMapper.selectModelUsageStatistics(queryDTO);
        
        // 保存模型统计数据
        for (Map<String, Object> modelStat : modelStats) {
            AiUsageStatistics statistics = new AiUsageStatistics();
            statistics.setStatDate(statDate);
            statistics.setStatType("1");
            statistics.setModelName(modelStat.get("model_name").toString());
            statistics.setConversationCount(Integer.valueOf(modelStat.get("conversation_count").toString()));
            statistics.setMessageCount(Integer.valueOf(modelStat.get("message_count").toString()));
            statistics.setTotalTokens(Long.valueOf(modelStat.get("total_tokens").toString()));
            statistics.setTotalCost(new BigDecimal(modelStat.get("total_cost").toString()));
            statistics.setCreateTime(LocalDateTime.now());
            statistics.setUpdateTime(LocalDateTime.now());
            this.save(statistics);
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean batchSyncStatistics(LocalDate startDate, LocalDate endDate) {
        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            syncStatisticsFromChatMessage(currentDate);
            currentDate = currentDate.plusDays(1);
        }
        return true;
    }
}