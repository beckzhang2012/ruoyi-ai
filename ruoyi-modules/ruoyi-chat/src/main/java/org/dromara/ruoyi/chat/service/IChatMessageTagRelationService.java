package org.ruoyi.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ruoyi.chat.domain.ChatMessageTagRelation;

import java.util.List;

/**
 * 聊天消息标签关系Service接口
 * 
 * @author ruoyi
 * @date 2024-05-20
 */
public interface IChatMessageTagRelationService extends IService<ChatMessageTagRelation>
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
     * 为消息添加标签
     * 
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 关系ID
     */
    public Long addTagToMessage(Long messageId, Long tagId);

    /**
     * 为消息移除标签
     * 
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 影响行数
     */
    public int removeTagFromMessage(Long messageId, Long tagId);

    /**
     * 为消息批量添加标签
     * 
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     * @return 影响行数
     */
    public int batchAddTagsToMessage(Long messageId, List<Long> tagIds);

    /**
     * 为消息批量移除标签
     * 
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     * @return 影响行数
     */
    public int batchRemoveTagsFromMessage(Long messageId, List<Long> tagIds);

    /**
     * 根据标签ID查询消息ID集合
     * 
     * @param tagId 标签ID
     * @return 消息ID集合
     */
    public List<Long> selectMessageIdsByTagId(Long tagId);

    /**
     * 根据标签ID集合查询消息ID集合
     * 
     * @param tagIds 标签ID集合
     * @return 消息ID集合
     */
    public List<Long> selectMessageIdsByTagIds(List<Long> tagIds);
}
