package org.ruoyi.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ruoyi.chat.domain.ChatMessageTagRelation;

import java.util.List;

/**
 * 聊天消息标签关系Mapper接口
 * 
 * @author ruoyi
 * @date 2024-05-20
 */
public interface ChatMessageTagRelationMapper extends BaseMapper<ChatMessageTagRelation>
{
    /**
     * 根据消息ID查询标签关系
     * 
     * @param messageId 消息ID
     * @return 标签关系集合
     */
    public List<ChatMessageTagRelation> selectRelationsByMessageId(Long messageId);

    /**
     * 根据标签ID查询标签关系
     * 
     * @param tagId 标签ID
     * @return 标签关系集合
     */
    public List<ChatMessageTagRelation> selectRelationsByTagId(Long tagId);

    /**
     * 根据消息ID和标签ID查询标签关系
     * 
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 标签关系对象
     */
    public ChatMessageTagRelation selectRelationByMessageIdAndTagId(Long messageId, Long tagId);

    /**
     * 删除标签关系
     * 
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 影响行数
     */
    public int deleteRelationByMessageIdAndTagId(Long messageId, Long tagId);

    /**
     * 批量查询标签关系
     * 
     * @param messageIds 消息ID集合
     * @return 标签关系集合
     */
    public List<ChatMessageTagRelation> selectRelationsByMessageIds(List<Long> messageIds);
}
