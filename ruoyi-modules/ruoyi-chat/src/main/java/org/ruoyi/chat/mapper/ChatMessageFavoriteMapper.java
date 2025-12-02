package org.ruoyi.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.ruoyi.chat.domain.entity.ChatMessageFavorite;
import org.apache.ibatis.annotations.Param;

/**
 * 聊天消息收藏Mapper接口
 *
 * @author ruoyi
 * @date 2025-12-01
 */
public interface ChatMessageFavoriteMapper extends BaseMapper<ChatMessageFavorite> {

    /**
     * 分页查询我的收藏列表
     *
     * @param page 分页参数
     * @param userId 用户ID
     * @param sessionId 会话ID（可选）
     * @param modelName 模型名称（可选）
     * @param searchKey 搜索关键词（可选，搜索消息内容或备注）
     * @return 分页结果
     */
    IPage<ChatMessageFavorite> selectMyFavoritePage(Page<ChatMessageFavorite> page,
                                                    @Param("userId") Long userId,
                                                    @Param("sessionId") Long sessionId,
                                                    @Param("modelName") String modelName,
                                                    @Param("searchKey") String searchKey);

    /**
     * 根据用户ID和消息ID查询收藏
     *
     * @param userId 用户ID
     * @param messageId 消息ID
     * @return 收藏信息
     */
    ChatMessageFavorite selectByUserIdAndMessageId(@Param("userId") Long userId, @Param("messageId") Long messageId);
}
