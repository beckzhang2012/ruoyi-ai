package org.ruoyi.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ruoyi.chat.domain.ChatMessageTag;

import java.util.List;

/**
 * 聊天消息标签Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-20
 */
public interface ChatMessageTagMapper extends BaseMapper<ChatMessageTag>
{
    /**
     * 查询聊天消息标签列表
     * 
     * @param chatMessageTag 聊天消息标签
     * @return 聊天消息标签集合
     */
    public List<ChatMessageTag> selectChatMessageTagList(ChatMessageTag chatMessageTag);

    /**
     * 根据用户ID查询标签列表
     * 
     * @param userId 用户ID
     * @return 标签集合
     */
    public List<ChatMessageTag> selectTagsByUserId(Long userId);

    /**
     * 根据标签名称和用户ID查询标签
     * 
     * @param tagName 标签名称
     * @param userId 用户ID
     * @return 标签对象
     */
    public ChatMessageTag selectTagByNameAndUserId(String tagName, Long userId);

    /**
     * 增加标签使用次数
     * 
     * @param tagId 标签ID
     * @return 影响行数
     */
    public int incrementTagUseCount(Long tagId);

    /**
     * 减少标签使用次数
     * 
     * @param tagId 标签ID
     * @return 影响行数
     */
    public int decrementTagUseCount(Long tagId);
}
