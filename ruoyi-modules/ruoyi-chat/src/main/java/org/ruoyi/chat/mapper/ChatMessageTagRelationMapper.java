package org.ruoyi.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ruoyi.chat.domain.ChatMessageTagRelation;
import org.ruoyi.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 聊天消息标签关系Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-01
 */
@Mapper
public interface ChatMessageTagRelationMapper extends BaseMapperPlus<ChatMessageTagRelation, ChatMessageTagRelation> {

    /**
     * 根据消息ID查询标签关系列表
     *
     * @param messageId 消息ID
     * @return 标签关系列表
     */
    List<ChatMessageTagRelation> selectRelationsByMessageId(@Param("messageId") Long messageId);

    /**
     * 根据标签ID查询标签关系列表
     *
     * @param tagId 标签ID
     * @return 标签关系列表
     */
    List<ChatMessageTagRelation> selectRelationsByTagId(@Param("tagId") Long tagId);

    /**
     * 根据消息ID和标签ID查询标签关系
     *
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 标签关系信息
     */
    ChatMessageTagRelation selectRelationByMessageIdAndTagId(@Param("messageId") Long messageId, @Param("tagId") Long tagId);

    /**
     * 根据消息ID删除标签关系
     *
     * @param messageId 消息ID
     * @return 影响行数
     */
    int deleteRelationsByMessageId(@Param("messageId") Long messageId);

    /**
     * 根据标签ID删除标签关系
     *
     * @param tagId 标签ID
     * @return 影响行数
     */
    int deleteRelationsByTagId(@Param("tagId") Long tagId);

    /**
     * 根据消息ID列表查询标签关系列表
     *
     * @param messageIds 消息ID列表
     * @return 标签关系列表
     */
    List<ChatMessageTagRelation> selectRelationsByMessageIds(@Param("messageIds") List<Long> messageIds);

}