package org.ruoyi.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ruoyi.chat.domain.entity.ChatSessionShare;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会话分享Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-01
 */
public interface ChatSessionShareMapper extends BaseMapper<ChatSessionShare> {

    /**
     * 根据分享码查询会话分享信息
     *
     * @param shareCode 分享码
     * @return 会话分享信息
     */
    ChatSessionShare selectByShareCode(@Param("shareCode") String shareCode);

    /**
     * 根据用户ID查询会话分享列表
     *
     * @param userId 用户ID
     * @return 会话分享列表
     */
    List<ChatSessionShare> selectByUserId(@Param("userId") Long userId);

    /**
     * 根据会话ID查询会话分享信息
     *
     * @param sessionId 会话ID
     * @return 会话分享信息
     */
    ChatSessionShare selectBySessionId(@Param("sessionId") Long sessionId);

    /**
     * 增加查看次数
     *
     * @param shareCode 分享码
     * @return 影响行数
     */
    int incrementViewCount(@Param("shareCode") String shareCode);
}
