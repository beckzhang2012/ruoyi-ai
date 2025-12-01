package org.ruoyi.chat.service;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.ruoyi.chat.domain.ChatMessageTag;
import org.ruoyi.chat.domain.ChatMessageTagRelation;

import java.util.List;

/**
 * 聊天消息标签关系Service接口
 *
 * @author ruoyi
 * @date 2025-11-01
 */
public interface IChatMessageTagRelationService {

    /**
     * 为消息添加标签
     *
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 是否成功
     */
    Boolean addTagToMessage(Long messageId, Long tagId);

    /**
     * 为消息移除标签
     *
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 是否成功
     */
    Boolean removeTagFromMessage(Long messageId, Long tagId);

    /**
     * 为消息设置标签（替换现有标签）
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID列表
     * @return 是否成功
     */
    Boolean setTagsForMessage(Long messageId, List<Long> tagIds);

    /**
     * 查询消息的所有标签
     *
     * @param messageId 消息ID
     * @return 标签列表
     */
    List<ChatMessageTag> getTagsByMessageId(Long messageId);

    /**
     * 查询标签的所有消息ID
     *
     * @param tagId 标签ID
     * @return 消息ID列表
     */
    List<Long> getMessageIdsByTagId(Long tagId);

    /**
     * 根据标签ID删除所有相关关系
     *
     * @param tagId 标签ID
     * @return 是否成功
     */
    Boolean deleteRelationsByTagId(Long tagId);

    /**
     * 根据消息ID删除所有相关关系
     *
     * @param messageId 消息ID
     * @return 是否成功
     */
    Boolean deleteRelationsByMessageId(Long messageId);

    /**
     * 查询消息是否有指定标签
     *
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 是否有标签
     */
    Boolean hasTag(Long messageId, Long tagId);

}
