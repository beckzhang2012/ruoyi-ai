package org.ruoyi.chat.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ruoyi.chat.domain.entity.ChatMessageFavorite;
import org.ruoyi.chat.domain.dto.ChatMessageFavoriteDTO;
import org.ruoyi.chat.mapper.ChatMessageFavoriteMapper;
import org.ruoyi.chat.service.IChatMessageFavoriteService;
import org.ruoyi.common.core.utils.SecurityUtils;
import org.ruoyi.common.core.utils.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 聊天消息收藏Service业务层处理
 *
 * @author ruoyi
 * @date 2025-12-01
 */
@Service
public class ChatMessageFavoriteServiceImpl extends ServiceImpl<ChatMessageFavoriteMapper, ChatMessageFavorite> implements IChatMessageFavoriteService {

    @Override
    public ChatMessageFavorite addFavorite(ChatMessageFavoriteDTO favoriteDTO) {
        Long userId = SecurityUtils.getUserId();
        
        // 检查是否已收藏
        ChatMessageFavorite existingFavorite = baseMapper.selectByUserIdAndMessageId(userId, favoriteDTO.getMessageId());
        if (existingFavorite != null) {
            return existingFavorite;
        }

        ChatMessageFavorite favorite = new ChatMessageFavorite();
        favorite.setUserId(userId);
        favorite.setMessageId(favoriteDTO.getMessageId());
        favorite.setSessionId(favoriteDTO.getSessionId());
        favorite.setSessionName(favoriteDTO.getSessionName());
        favorite.setModelName(favoriteDTO.getModelName());
        favorite.setMessageContent(favoriteDTO.getMessageContent());
        favorite.setRemark(favoriteDTO.getRemark());

        baseMapper.insert(favorite);
        return favorite;
    }

    @Override
    public void cancelFavorite(Long messageId) {
        Long userId = SecurityUtils.getUserId();
        ChatMessageFavorite favorite = baseMapper.selectByUserIdAndMessageId(userId, messageId);
        if (favorite != null) {
            baseMapper.deleteById(favorite.getId());
        }
    }

    @Override
    public IPage<ChatMessageFavorite> getMyFavoritePage(Page<ChatMessageFavorite> page,
                                                        Long sessionId,
                                                        String modelName,
                                                        String searchKey) {
        Long userId = SecurityUtils.getUserId();
        return baseMapper.selectMyFavoritePage(page, userId, sessionId, modelName, searchKey);
    }

    @Override
    public boolean isFavorite(Long messageId) {
        Long userId = SecurityUtils.getUserId();
        ChatMessageFavorite favorite = baseMapper.selectByUserIdAndMessageId(userId, messageId);
        return favorite != null;
    }

    @Override
    public void updateRemark(Long id, String remark) {
        ChatMessageFavorite favorite = getById(id);
        if (favorite != null && favorite.getUserId().equals(SecurityUtils.getUserId())) {
            favorite.setRemark(remark);
            updateById(favorite);
        }
    }
}
