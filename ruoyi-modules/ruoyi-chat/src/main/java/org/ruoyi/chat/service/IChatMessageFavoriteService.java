package org.ruoyi.chat.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.ruoyi.chat.domain.entity.ChatMessageFavorite;
import org.ruoyi.chat.domain.dto.ChatMessageFavoriteDTO;

/**
 * 聊天消息收藏Service接口
 *
 * @author ruoyi
 * @date 2025-12-01
 */
public interface IChatMessageFavoriteService extends IService<ChatMessageFavorite> {

    /**
     * 添加消息收藏
     *
     * @param favoriteDTO 收藏DTO
     * @return 收藏信息
     */
    ChatMessageFavorite addFavorite(ChatMessageFavoriteDTO favoriteDTO);

    /**
     * 取消消息收藏
     *
     * @param messageId 消息ID
     */
    void cancelFavorite(Long messageId);

    /**
     * 分页查询我的收藏列表
     *
     * @param page 分页参数
     * @param sessionId 会话ID（可选）
     * @param modelName 模型名称（可选）
     * @param searchKey 搜索关键词（可选）
     * @return 分页结果
     */
    IPage<ChatMessageFavorite> getMyFavoritePage(Page<ChatMessageFavorite> page,
                                                 Long sessionId,
                                                 String modelName,
                                                 String searchKey);

    /**
     * 判断消息是否已收藏
     *
     * @param messageId 消息ID
     * @return 是否已收藏
     */
    boolean isFavorite(Long messageId);

    /**
     * 更新收藏备注
     *
     * @param id 收藏ID
     * @param remark 备注内容
     */
    void updateRemark(Long id, String remark);
}
