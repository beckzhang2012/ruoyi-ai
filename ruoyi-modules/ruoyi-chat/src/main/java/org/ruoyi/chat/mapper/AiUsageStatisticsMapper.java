package org.ruoyi.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.ruoyi.chat.domain.entity.AiUsageStatistics;
import org.ruoyi.chat.domain.dto.AiUsageStatisticsQueryDTO;
import java.util.List;
import java.util.Map;

/**
 * AI使用统计Mapper接口
 *
 * @author ruoyi
 * @date 2024-06-11
 */
public interface AiUsageStatisticsMapper extends BaseMapper<AiUsageStatistics> {

    /**
     * 分页查询AI使用统计列表
     *
     * @param page 分页参数
     * @param query 查询条件
     * @return 分页结果
     */
    Page<AiUsageStatistics> selectStatisticsPage(@Param("page") Page<AiUsageStatistics> page, @Param("query") AiUsageStatisticsQueryDTO query);

    /**
     * 查询AI使用统计列表（不分页）
     *
     * @param query 查询条件
     * @return 统计列表
     */
    List<AiUsageStatistics> selectStatisticsList(@Param("query") AiUsageStatisticsQueryDTO query);

    /**
     * 按用户统计使用情况（按日）
     *
     * @param query 查询条件
     * @return 用户使用统计
     */
    List<Map<String, Object>> selectUserUsageStatistics(@Param("query") AiUsageStatisticsQueryDTO query);

    /**
     * 按模型统计使用情况
     *
     * @param query 查询条件
     * @return 模型使用统计
     */
    List<Map<String, Object>> selectModelUsageStatistics(@Param("query") AiUsageStatisticsQueryDTO query);

    /**
     * 统计用户对话次数（按日聚合）
     *
     * @param query 查询条件
     * @return 用户对话次数统计
     */
    List<Map<String, Object>> countUserConversations(@Param("query") AiUsageStatisticsQueryDTO query);

    /**
     * 统计模型使用趋势
     *
     * @param query 查询条件
     * @return 模型使用趋势数据
     */
    List<Map<String, Object>> selectModelUsageTrend(@Param("query") AiUsageStatisticsQueryDTO query);
}