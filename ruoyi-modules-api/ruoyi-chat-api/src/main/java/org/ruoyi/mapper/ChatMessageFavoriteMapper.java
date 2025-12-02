package org.ruoyi.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.ruoyi.common.mybatis.core.BaseMapperPlus;
import org.ruoyi.domain.ChatMessageFavorite;
import org.ruoyi.domain.vo.ChatMessageFavoriteVo;

/**
 * 消息收藏Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-10
 */
@Mapper
public interface ChatMessageFavoriteMapper extends BaseMapperPlus<ChatMessageFavorite, ChatMessageFavoriteVo> {
}