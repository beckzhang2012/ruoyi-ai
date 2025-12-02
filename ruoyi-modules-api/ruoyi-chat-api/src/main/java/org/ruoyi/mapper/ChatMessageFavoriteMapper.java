package org.ruoyi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ruoyi.core.mapper.BaseMapperPlus;
import org.ruoyi.domain.ChatMessageFavorite;
import org.ruoyi.domain.vo.ChatMessageFavoriteVo;

import java.util.List;

/**
 * 消息收藏Mapper接口
 *
 * @author ageerle
 * @date 2025-10-20
 */
@Mapper
public interface ChatMessageFavoriteMapper extends BaseMapperPlus<ChatMessageFavorite, ChatMessageFavoriteVo> {

    /**
     * 查询消息收藏列表（分页）
     *
     * @param userId   用户id
     * @param modelName 模型名称
     * @param sessionId 会话id
     * @param content   消息内容关键词
     * @return 消息收藏列表
     */
    List<ChatMessageFavoriteVo> selectChatMessageFavoriteList(
            @Param("userId") Long userId,
            @Param("modelName") String modelName,
            @Param("sessionId") Long sessionId,
            @Param("content") String content);

    /**
     * 根据消息id查询收藏
     *
     * @param userId    用户id
     * @param messageId 消息id
     * @return 收藏信息
     */
    @Select("SELECT * FROM chat_message_favorite WHERE user_id = #{userId} AND message_id = #{messageId}")
    ChatMessageFavorite selectByUserIdAndMessageId(@Param("userId") Long userId, @Param("messageId") Long messageId);

    /**
     * 统计用户收藏数量
     *
     * @param userId 用户id
     * @return 收藏数量
     */
    @Select("SELECT COUNT(*) FROM chat_message_favorite WHERE user_id = #{userId}")
    int countByUserId(@Param("userId") Long userId);
}