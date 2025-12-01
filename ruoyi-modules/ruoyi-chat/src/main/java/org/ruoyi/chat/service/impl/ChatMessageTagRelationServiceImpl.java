package org.ruoyi.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.ruoyi.chat.domain.ChatMessageTag;
import org.ruoyi.chat.domain.ChatMessageTagRelation;
import org.ruoyi.chat.mapper.ChatMessageTagMapper;
import org.ruoyi.chat.mapper.ChatMessageTagRelationMapper;
import org.ruoyi.chat.service.IChatMessageTagRelationService;
import org.ruoyi.common.satoken.utils.LoginHelper;
import org.ruoyi.domain.ChatMessage;
import org.ruoyi.mapper.ChatMessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 聊天消息标签关系Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-01
 */
@RequiredArgsConstructor
@Service
public class ChatMessageTagRelationServiceImpl implements IChatMessageTagRelationService {

    private final ChatMessageTagRelationMapper relationMapper;
    private final ChatMessageTagMapper tagMapper;
    private final ChatMessageMapper messageMapper;

    /**
     * 为消息添加标签
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean addTagToMessage(Long messageId, Long tagId) {
        // 检查消息是否存在
        ChatMessage message = messageMapper.selectById(messageId);
        if (message == null) {
            throw new RuntimeException("消息不存在");
        }
        // 检查当前用户是否有权限操作该消息
        if (!LoginHelper.isSuperAdmin() && !message.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限操作该消息");
        }
        // 检查标签是否存在
        ChatMessageTag tag = tagMapper.selectById(tagId);
        if (tag == null) {
            throw new RuntimeException("标签不存在");
        }
        // 检查当前用户是否有权限操作该标签
        if (!LoginHelper.isSuperAdmin() && !tag.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限操作该标签");
        }
        // 检查标签是否已经添加到消息
        ChatMessageTagRelation existingRelation = relationMapper.selectRelationByMessageIdAndTagId(messageId, tagId);
        if (existingRelation != null) {
            return true; // 标签已经存在，返回成功
        }
        // 创建标签关系
        ChatMessageTagRelation relation = new ChatMessageTagRelation();
        relation.setMessageId(messageId);
        relation.setTagId(tagId);
        relationMapper.insert(relation);
        // 增加标签使用次数
        tagMapper.incrementTagUseCount(tagId);
        return true;
    }

    /**
     * 为消息移除标签
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeTagFromMessage(Long messageId, Long tagId) {
        // 检查消息是否存在
        ChatMessage message = messageMapper.selectById(messageId);
        if (message == null) {
            throw new RuntimeException("消息不存在");
        }
        // 检查当前用户是否有权限操作该消息
        if (!LoginHelper.isSuperAdmin() && !message.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限操作该消息");
        }
        // 检查标签是否存在
        ChatMessageTag tag = tagMapper.selectById(tagId);
        if (tag == null) {
            throw new RuntimeException("标签不存在");
        }
        // 检查当前用户是否有权限操作该标签
        if (!LoginHelper.isSuperAdmin() && !tag.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限操作该标签");
        }
        // 检查标签是否已经添加到消息
        ChatMessageTagRelation existingRelation = relationMapper.selectRelationByMessageIdAndTagId(messageId, tagId);
        if (existingRelation == null) {
            return true; // 标签不存在，返回成功
        }
        // 删除标签关系
        relationMapper.deleteById(existingRelation.getId());
        // 减少标签使用次数
        tagMapper.decrementTagUseCount(tagId);
        return true;
    }

    /**
     * 为消息设置标签（替换现有标签）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean setTagsForMessage(Long messageId, List<Long> tagIds) {
        // 检查消息是否存在
        ChatMessage message = messageMapper.selectById(messageId);
        if (message == null) {
            throw new RuntimeException("消息不存在");
        }
        // 检查当前用户是否有权限操作该消息
        if (!LoginHelper.isSuperAdmin() && !message.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限操作该消息");
        }
        // 获取消息当前的标签
        List<ChatMessageTagRelation> currentRelations = relationMapper.selectRelationsByMessageId(messageId);
        List<Long> currentTagIds = currentRelations.stream().map(ChatMessageTagRelation::getTagId).collect(Collectors.toList());

        // 找出需要添加的标签
        List<Long> tagsToAdd = tagIds.stream().filter(tagId -> !currentTagIds.contains(tagId)).collect(Collectors.toList());
        // 找出需要移除的标签
        List<Long> tagsToRemove = currentTagIds.stream().filter(tagId -> !tagIds.contains(tagId)).collect(Collectors.toList());

        // 添加新标签
        for (Long tagId : tagsToAdd) {
            addTagToMessage(messageId, tagId);
        }

        // 移除旧标签
        for (Long tagId : tagsToRemove) {
            removeTagFromMessage(messageId, tagId);
        }

        return true;
    }

    /**
     * 查询消息的所有标签
     */
    @Override
    public List<ChatMessageTag> getTagsByMessageId(Long messageId) {
        // 检查消息是否存在
        ChatMessage message = messageMapper.selectById(messageId);
        if (message == null) {
            throw new RuntimeException("消息不存在");
        }
        // 检查当前用户是否有权限查看该消息的标签
        if (!LoginHelper.isSuperAdmin() && !message.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限查看该消息的标签");
        }
        // 获取消息的标签关系
        List<ChatMessageTagRelation> relations = relationMapper.selectRelationsByMessageId(messageId);
        // 获取标签ID列表
        List<Long> tagIds = relations.stream().map(ChatMessageTagRelation::getTagId).collect(Collectors.toList());
        // 查询标签信息
        if (tagIds.isEmpty()) {
            return new ArrayList<>();
        }
        LambdaQueryWrapper<ChatMessageTag> lqw = Wrappers.lambdaQuery();
        lqw.in(ChatMessageTag::getId, tagIds);
        lqw.orderByDesc(ChatMessageTag::getCreateTime);
        return tagMapper.selectList(lqw);
    }

    /**
     * 查询标签的所有消息ID
     */
    @Override
    public List<Long> getMessageIdsByTagId(Long tagId) {
        // 检查标签是否存在
        ChatMessageTag tag = tagMapper.selectById(tagId);
        if (tag == null) {
            throw new RuntimeException("标签不存在");
        }
        // 检查当前用户是否有权限查看该标签的消息
        if (!LoginHelper.isSuperAdmin() && !tag.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限查看该标签的消息");
        }
        // 获取标签的关系
        List<ChatMessageTagRelation> relations = relationMapper.selectRelationsByTagId(tagId);
        // 获取消息ID列表
        return relations.stream().map(ChatMessageTagRelation::getMessageId).collect(Collectors.toList());
    }

    /**
     * 根据标签ID删除所有相关关系
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRelationsByTagId(Long tagId) {
        // 检查标签是否存在
        ChatMessageTag tag = tagMapper.selectById(tagId);
        if (tag == null) {
            throw new RuntimeException("标签不存在");
        }
        // 检查当前用户是否有权限删除该标签的关系
        if (!LoginHelper.isSuperAdmin() && !tag.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限删除该标签的关系");
        }
        // 删除标签关系
        int count = relationMapper.deleteRelationsByTagId(tagId);
        // 更新标签使用次数
        if (count > 0) {
            tag.setUseCount(0);
            tagMapper.updateById(tag);
        }
        return true;
    }

    /**
     * 根据消息ID删除所有相关关系
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteRelationsByMessageId(Long messageId) {
        // 检查消息是否存在
        ChatMessage message = messageMapper.selectById(messageId);
        if (message == null) {
            throw new RuntimeException("消息不存在");
        }
        // 检查当前用户是否有权限删除该消息的关系
        if (!LoginHelper.isSuperAdmin() && !message.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限删除该消息的关系");
        }
        // 获取消息的标签关系
        List<ChatMessageTagRelation> relations = relationMapper.selectRelationsByMessageId(messageId);
        // 删除标签关系
        int count = relationMapper.deleteRelationsByMessageId(messageId);
        // 更新标签使用次数
        if (count > 0) {
            for (ChatMessageTagRelation relation : relations) {
                tagMapper.decrementTagUseCount(relation.getTagId());
            }
        }
        return true;
    }

    /**
     * 查询消息是否有指定标签
     */
    @Override
    public Boolean hasTag(Long messageId, Long tagId) {
        // 检查消息是否存在
        ChatMessage message = messageMapper.selectById(messageId);
        if (message == null) {
            throw new RuntimeException("消息不存在");
        }
        // 检查当前用户是否有权限查看该消息的标签
        if (!LoginHelper.isSuperAdmin() && !message.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限查看该消息的标签");
        }
        // 检查标签是否存在
        ChatMessageTag tag = tagMapper.selectById(tagId);
        if (tag == null) {
            throw new RuntimeException("标签不存在");
        }
        // 检查标签是否已经添加到消息
        ChatMessageTagRelation existingRelation = relationMapper.selectRelationByMessageIdAndTagId(messageId, tagId);
        return existingRelation != null;
    }

}
