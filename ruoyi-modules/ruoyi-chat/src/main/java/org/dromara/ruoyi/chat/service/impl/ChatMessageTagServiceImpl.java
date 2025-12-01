package org.ruoyi.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.ruoyi.chat.domain.ChatMessageTag;
import org.ruoyi.chat.mapper.ChatMessageTagMapper;
import org.ruoyi.chat.service.IChatMessageTagService;
import org.ruoyi.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 聊天消息标签Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-05-20
 */
@Service
public class ChatMessageTagServiceImpl extends ServiceImpl<ChatMessageTagMapper, ChatMessageTag> implements IChatMessageTagService
{
    @Autowired
    private ChatMessageTagMapper chatMessageTagMapper;

    /**
     * 查询聊天消息标签列表
     * 
     * @param chatMessageTag 聊天消息标签
     * @return 聊天消息标签集合
     */
    @Override
    public List<ChatMessageTag> selectChatMessageTagList(ChatMessageTag chatMessageTag)
    {
        return chatMessageTagMapper.selectChatMessageTagList(chatMessageTag);
    }

    /**
     * 根据用户ID查询标签列表
     * 
     * @param userId 用户ID
     * @return 标签集合
     */
    @Override
    public List<ChatMessageTag> selectTagsByUserId(Long userId)
    {
        return chatMessageTagMapper.selectTagsByUserId(userId);
    }

    /**
     * 创建标签
     * 
     * @param chatMessageTag 标签对象
     * @return 标签ID
     */
    @Override
    @Transactional
    public Long createTag(ChatMessageTag chatMessageTag)
    {
        // 设置用户ID
        chatMessageTag.setUserId(SecurityUtils.getUserId());
        // 检查标签名称是否已存在
        ChatMessageTag existingTag = chatMessageTagMapper.selectTagByNameAndUserId(chatMessageTag.getTagName(), chatMessageTag.getUserId());
        if (existingTag != null)
        {
            throw new RuntimeException("标签名称已存在");
        }
        // 设置初始使用次数为0
        chatMessageTag.setUseCount(0);
        // 插入标签
        chatMessageTagMapper.insert(chatMessageTag);
        return chatMessageTag.getTagId();
    }

    /**
     * 编辑标签
     * 
     * @param chatMessageTag 标签对象
     * @return 影响行数
     */
    @Override
    @Transactional
    public int updateTag(ChatMessageTag chatMessageTag)
    {
        // 设置用户ID
        chatMessageTag.setUserId(SecurityUtils.getUserId());
        // 检查标签名称是否已存在（排除当前标签）
        ChatMessageTag existingTag = chatMessageTagMapper.selectTagByNameAndUserId(chatMessageTag.getTagName(), chatMessageTag.getUserId());
        if (existingTag != null && !existingTag.getTagId().equals(chatMessageTag.getTagId()))
        {
            throw new RuntimeException("标签名称已存在");
        }
        // 更新标签
        return chatMessageTagMapper.updateById(chatMessageTag);
    }

    /**
     * 删除标签
     * 
     * @param tagId 标签ID
     * @return 影响行数
     */
    @Override
    @Transactional
    public int deleteTag(Long tagId)
    {
        // 检查标签是否属于当前用户
        ChatMessageTag tag = chatMessageTagMapper.selectById(tagId);
        if (tag == null || !tag.getUserId().equals(SecurityUtils.getUserId()))
        {
            throw new RuntimeException("标签不存在或无权限删除");
        }
        // 删除标签
        return chatMessageTagMapper.deleteById(tagId);
    }

    /**
     * 批量删除标签
     * 
     * @param tagIds 标签ID集合
     * @return 影响行数
     */
    @Override
    @Transactional
    public int deleteTags(List<Long> tagIds)
    {
        // 检查标签是否属于当前用户
        LambdaQueryWrapper<ChatMessageTag> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(ChatMessageTag::getTagId, tagIds);
        queryWrapper.eq(ChatMessageTag::getUserId, SecurityUtils.getUserId());
        List<ChatMessageTag> tags = chatMessageTagMapper.selectList(queryWrapper);
        if (tags.size() != tagIds.size())
        {
            throw new RuntimeException("部分标签不存在或无权限删除");
        }
        // 批量删除标签
        return chatMessageTagMapper.deleteBatchIds(tagIds);
    }
}
