package org.ruoyi.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ruoyi.chat.domain.dto.ChatMessageTagDTO;
import org.ruoyi.chat.domain.dto.MessageTagRelationDTO;
import org.ruoyi.chat.domain.entity.ChatMessageTag;
import org.ruoyi.chat.domain.entity.ChatMessageTagRelation;
import org.ruoyi.chat.mapper.ChatMessageTagMapper;
import org.ruoyi.chat.mapper.ChatMessageTagRelationMapper;
import org.ruoyi.chat.service.IChatMessageTagService;
import org.ruoyi.common.core.domain.model.LoginUser;
import org.ruoyi.common.satoken.utils.LoginHelper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 聊天消息标签Service业务层处理
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Service
public class ChatMessageTagServiceImpl extends ServiceImpl<ChatMessageTagMapper, ChatMessageTag> implements IChatMessageTagService {

    private final ChatMessageTagRelationMapper chatMessageTagRelationMapper;

    public ChatMessageTagServiceImpl(ChatMessageTagRelationMapper chatMessageTagRelationMapper) {
        this.chatMessageTagRelationMapper = chatMessageTagRelationMapper;
    }

    /**
     * 获取当前登录用户ID
     *
     * @return 用户ID
     */
    private Long getCurrentUserId() {
        LoginUser loginUser = LoginHelper.getLoginUser();
        return loginUser.getUserId();
    }

    @Override
    public List<ChatMessageTag> selectUserTags() {
        Long userId = getCurrentUserId();
        return baseMapper.selectUserTags(userId);
    }

    @Override
    public ChatMessageTag selectChatMessageTagById(Long id) {
        Long userId = getCurrentUserId();
        return baseMapper.selectOne(new LambdaQueryWrapper<ChatMessageTag>()
                .eq(ChatMessageTag::getId, id)
                .eq(ChatMessageTag::getUserId, userId));
    }

    @Override
    public int insertChatMessageTag(ChatMessageTagDTO chatMessageTagDTO) {
        ChatMessageTag chatMessageTag = new ChatMessageTag();
        BeanUtils.copyProperties(chatMessageTagDTO, chatMessageTag);
        chatMessageTag.setUserId(getCurrentUserId());
        chatMessageTag.setCreateTime(LocalDateTime.now());
        chatMessageTag.setUpdateTime(LocalDateTime.now());
        return baseMapper.insert(chatMessageTag);
    }

    @Override
    public int updateChatMessageTag(ChatMessageTagDTO chatMessageTagDTO) {
        ChatMessageTag chatMessageTag = selectChatMessageTagById(chatMessageTagDTO.getId());
        if (chatMessageTag == null) {
            return 0;
        }
        BeanUtils.copyProperties(chatMessageTagDTO, chatMessageTag);
        chatMessageTag.setUpdateTime(LocalDateTime.now());
        return baseMapper.updateById(chatMessageTag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteChatMessageTagByIds(Long[] ids) {
        // 删除标签
        int result = baseMapper.deleteBatchIds(List.of(ids));
        // 删除关联关系
        for (Long id : ids) {
            chatMessageTagRelationMapper.deleteByTagId(id);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteChatMessageTagById(Long id) {
        // 删除标签
        int result = baseMapper.deleteById(id);
        // 删除关联关系
        chatMessageTagRelationMapper.deleteByTagId(id);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addMessageTags(MessageTagRelationDTO messageTagRelationDTO) {
        Long userId = getCurrentUserId();
        Long messageId = messageTagRelationDTO.getMessageId();
        List<Long> tagIds = messageTagRelationDTO.getTagIds();

        // 先删除已有关联
        chatMessageTagRelationMapper.deleteByMessageId(messageId);

        // 添加新关联
        List<ChatMessageTagRelation> relations = new ArrayList<>();
        for (Long tagId : tagIds) {
            // 验证标签属于当前用户
            ChatMessageTag tag = selectChatMessageTagById(tagId);
            if (tag != null) {
                ChatMessageTagRelation relation = new ChatMessageTagRelation();
                relation.setMessageId(messageId);
                relation.setTagId(tagId);
                relation.setUserId(userId);
                relation.setCreateTime(LocalDateTime.now());
                relations.add(relation);
            }
        }

        if (!relations.isEmpty()) {
            for (ChatMessageTagRelation relation : relations) {
                chatMessageTagRelationMapper.insert(relation);
            }
        }
        return relations.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int removeMessageTags(MessageTagRelationDTO messageTagRelationDTO) {
        Long messageId = messageTagRelationDTO.getMessageId();
        List<Long> tagIds = messageTagRelationDTO.getTagIds();

        LambdaQueryWrapper<ChatMessageTagRelation> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ChatMessageTagRelation::getMessageId, messageId)
                .in(ChatMessageTagRelation::getTagId, tagIds)
                .eq(ChatMessageTagRelation::getUserId, getCurrentUserId());

        return chatMessageTagRelationMapper.delete(queryWrapper);
    }

    @Override
    public List<ChatMessageTag> selectMessageTags(Long messageId) {
        List<Long> tagIds = chatMessageTagRelationMapper.selectTagIdsByMessageId(messageId);
        if (tagIds.isEmpty()) {
            return new ArrayList<>();
        }
        return baseMapper.selectBatchIds(tagIds);
    }

    @Override
    public List<Long> selectMessageIdsByTagId(Long tagId) {
        // 验证标签属于当前用户
        ChatMessageTag tag = selectChatMessageTagById(tagId);
        if (tag == null) {
            return new ArrayList<>();
        }
        return chatMessageTagRelationMapper.selectMessageIdsByTagId(tagId);
    }
}