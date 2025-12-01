package org.ruoyi.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ruoyi.chat.domain.entity.ChatMessageTag;

import java.util.List;

/**
 * 聊天消息标签Mapper接口
 *
 * @author ruoyi
 * @date 2024-06-11
 */
public interface ChatMessageTagMapper extends BaseMapper<ChatMessageTag> {

    /**
     * 查询用户的所有标签
     *
     * @param userId 用户ID
     * @return 标签列表
     */
    List<ChatMessageTag> selectUserTags(Long userId);
}