package org.ruoyi.chat.service.chat;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ruoyi.chat.domain.entity.AiUsageStatistics;
import org.ruoyi.chat.domain.dto.AiUsageStatisticsQueryDTO;
import org.ruoyi.core.page.TableDataInfo;

import java.util.List;
import java.util.Map;

/**
 * AI使用统计服务接口
 *
 * @author ruoyi
 * @date 2024-06-11
 */
public interface IAiUsageStatisticsService {

    /**
     * 分页查询AI使用统计列表
     *
     * @param queryDTO 查询条件
     * @return 分页数据
     */
    TableDataInfo<AiUsageStatistics> queryPageList(AiUsageStatisticsQueryDTO queryDTO);

    /**
     * 查询AI使用统计列表（不分页）
     *
     * @param queryDTO 查询条件
     * @return 统计列表
     */
    List<AiUsageStatistics> queryList(AiUsageStatisticsQueryDTO queryDTO);

    /**
     * 按用户统计使用情况
     *
     * @param queryDTO 查询条件
     * @return 用户使用统计
     */
    List<Map<String, Object>> getUserUsageStatistics(AiUsageStatisticsQueryDTO queryDTO);

    /**
     * 按模型统计使用情况
     *
     * @param queryDTO 查询条件
     * @return 模型使用统计
     */
    List<Map<String, Object>> getModelUsageStatistics(AiUsageStatisticsQueryDTO queryDTO);

    /**
     * 获取用户对话次数统计
     *
     * @param queryDTO 查询条件
     * @return 用户对话次数统计
     */
    List<Map<String, Object>> countUserConversations(AiUsageStatisticsQueryDTO queryDTO);

    /**
     * 获取模型使用趋势
     *
     * @param queryDTO 查询条件
     * @return 模型使用趋势数据
     */
    List<Map<String, Object>> getModelUsageTrend(AiUsageStatisticsQueryDTO queryDTO);

    /**
     * 从chat_message表同步统计数据
     *
     * @param statDate 统计日期
     * @return 同步结果
     */
    boolean syncStatisticsFromChatMessage(java.time.LocalDate statDate);

    /**
     * 批量同步统计数据
     *
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 同步结果
     */
    boolean batchSyncStatistics(java.time.LocalDate startDate, java.time.LocalDate endDate);
}