package org.ruoyi.chat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ruoyi.chat.domain.ChatMessageTag;
import org.ruoyi.chat.domain.ChatMessageTagRelation;
import org.ruoyi.chat.mapper.ChatMessageTagMapper;
import org.ruoyi.chat.mapper.ChatMessageTagRelationMapper;
import org.ruoyi.chat.service.IChatMessageTagRelationService;
import org.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 聊天消息标签关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-20
 */
@Service
public class ChatMessageTagRelationServiceImpl extends ServiceImpl<ChatMessageTagRelationMapper, ChatMessageTagRelation> implements IChatMessageTagRelationService
{
    @Autowired
    private ChatMessageTagRelationMapper chatMessageTagRelationMapper;

    @Autowired
    private ChatMessageTagMapper chatMessageTagMapper;

    /**
     * 根据消息ID查询标签关系
     * 
     * @param messageId 消息ID
     * @return 标签关系集合
     */
    @Override
    public List<ChatMessageTagRelation> selectRelationsByMessageId(Long messageId)
    {
        return chatMessageTagRelationMapper.selectRelationsByMessageId(messageId);
    }

    /**
     * 根据标签ID查询标签关系
     * 
     * @param tagId 标签ID
     * @return 标签关系集合
     */
    @Override
    public List<ChatMessageTagRelation> selectRelationsByTagId(Long tagId)
    {
        return chatMessageTagRelationMapper.selectRelationsByTagId(tagId);
    }

    /**
     * 为消息添加标签
     * 
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 关系ID
     */
    @Override
    @Transactional
    public Long addTagToMessage(Long messageId, Long tagId)
    {
        // 检查标签是否属于当前用户
        ChatMessageTag tag = chatMessageTagMapper.selectById(tagId);
        if (tag == null || !tag.getUserId().equals(SecurityUtils.getUserId()))
        {
            throw new RuntimeException("标签不存在或无权限使用");
        }
        // 检查标签关系是否已存在
        ChatMessageTagRelation existingRelation = chatMessageTagRelationMapper.selectRelationByMessageIdAndTagId(messageId, tagId);
        if (existingRelation != null)
        {
            throw new RuntimeException("标签已添加到该消息");
        }
        // 创建标签关系
        ChatMessageTagRelation relation = new ChatMessageTagRelation();
        relation.setMessageId(messageId);
        relation.setTagId(tagId);
        chatMessageTagRelationMapper.insert(relation);
        // 增加标签使用次数
        chatMessageTagMapper.incrementTagUseCount(tagId);
        return relation.getRelationId();
    }

    /**
     * 为消息移除标签
     * 
     * @param messageId 消息ID
     * @param tagId 标签ID
     * @return 影响行数
     */
    @Override
    @Transactional
    public int removeTagFromMessage(Long messageId, Long tagId)
    {
        // 检查标签是否属于当前用户
        ChatMessageTag tag = chatMessageTagMapper.selectById(tagId);
        if (tag == null || !tag.getUserId().equals(SecurityUtils.getUserId()))
        {
            throw new RuntimeException("标签不存在或无权限使用");
        }
        // 检查标签关系是否存在
        ChatMessageTagRelation existingRelation = chatMessageTagRelationMapper.selectRelationByMessageIdAndTagId(messageId, tagId);
        if (existingRelation == null)
        {
            throw new RuntimeException("标签未添加到该消息");
        }
        // 删除标签关系
        int result = chatMessageTagRelationMapper.deleteRelationByMessageIdAndTagId(messageId, tagId);
        // 减少标签使用次数
        chatMessageTagMapper.decrementTagUseCount(tagId);
        return result;
    }

    /**
     * 为消息批量添加标签
     * 
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     * @return 影响行数
     */
    @Override
    @Transactional
    public int batchAddTagsToMessage(Long messageId, List<Long> tagIds)
    {
        int count = 0;
        for (Long tagId : tagIds)
        {
            try
            {
                addTagToMessage(messageId, tagId);
                count++;
            }
            catch (Exception e)
            {
                // 忽略已存在的标签关系
                if (!e.getMessage().equals("标签已添加到该消息"))
                {
                    throw e;
                }
            }
        }
        return count;
    }

    /**
     * 为消息批量移除标签
     * 
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     * @return 影响行数
     */
    @Override
    @Transactional
    public int batchRemoveTagsFromMessage(Long messageId, List<Long> tagIds)
    {
        int count = 0;
        for (Long tagId : tagIds)
        {
            try
            {
                removeTagFromMessage(messageId, tagId);
                count++;
            }
            catch (Exception e)
            {
                // 忽略不存在的标签关系
                if (!e.getMessage().equals("标签未添加到该消息"))
                {
                    throw e;
                }
            }
        }
        return count;
    }

    /**
     * 根据标签ID查询消息ID集合
     * 
     * @param tagId 标签ID
     * @return 消息ID集合
     */
    @Override
    public List<Long> selectMessageIdsByTagId(Long tagId)
    {
        // 检查标签是否属于当前用户
        ChatMessageTag tag = chatMessageTagMapper.selectById(tagId);
        if (tag == null || !tag.getUserId().equals(SecurityUtils.getUserId()))
        {
            throw new RuntimeException("标签不存在或无权限使用");
        }
        // 查询标签关系
        List<ChatMessageTagRelation> relations = chatMessageTagRelationMapper.selectRelationsByTagId(tagId);
        // 提取消息ID
        return relations.stream().map(ChatMessageTagRelation::getMessageId).toList();
    }

    /**
     * 根据标签ID集合查询消息ID集合
     * 
     * @param tagIds 标签ID集合
     * @return 消息ID集合
     */
    @Override
    public List<Long> selectMessageIdsByTagIds(List<Long> tagIds)
    {
        // 检查标签是否属于当前用户
        for (Long tagId : tagIds)
        {
            ChatMessageTag tag = chatMessageTagMapper.selectById(tagId);
            if (tag == null || !tag.getUserId().equals(SecurityUtils.getUserId()))
            {
                throw new RuntimeException("标签不存在或无权限使用");
            }
        }
        // 查询标签关系
        List<ChatMessageTagRelation> relations = chatMessageTagRelationMapper.selectList(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<ChatMessageTagRelation>()
                        .in(ChatMessageTagRelation::getTagId, tagIds)
        );
        // 提取消息ID
        return relations.stream().map(ChatMessageTagRelation::getMessageId).distinct().toList();
    }
}
