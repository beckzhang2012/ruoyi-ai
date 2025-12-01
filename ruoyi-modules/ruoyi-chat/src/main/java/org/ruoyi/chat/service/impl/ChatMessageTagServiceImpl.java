package org.ruoyi.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ruoyi.chat.domain.ChatMessageTag;
import org.ruoyi.chat.mapper.ChatMessageTagMapper;
import org.ruoyi.chat.service.IChatMessageTagService;
import org.ruoyi.common.core.utils.StringUtils;
import org.ruoyi.common.satoken.utils.LoginHelper;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * 聊天消息标签Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-01
 */
@RequiredArgsConstructor
@Service
public class ChatMessageTagServiceImpl implements IChatMessageTagService {

    private final ChatMessageTagMapper baseMapper;

    /**
     * 查询聊天消息标签
     */
    @Override
    public ChatMessageTag queryById(Long id) {
        return baseMapper.selectById(id);
    }

    /**
     * 查询聊天消息标签列表
     */
    @Override
    public TableDataInfo<ChatMessageTag> queryPageList(ChatMessageTag tag, PageQuery pageQuery) {
        // 只有非管理员才自动设置为自己的 ID
        if (!LoginHelper.isSuperAdmin()) {
            tag.setUserId(LoginHelper.getUserId());
        }
        LambdaQueryWrapper<ChatMessageTag> lqw = buildQueryWrapper(tag);
        Page<ChatMessageTag> result = baseMapper.selectPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询聊天消息标签列表
     */
    @Override
    public List<ChatMessageTag> queryList(ChatMessageTag tag) {
        // 只有非管理员才自动设置为自己的 ID
        if (!LoginHelper.isSuperAdmin()) {
            tag.setUserId(LoginHelper.getUserId());
        }
        LambdaQueryWrapper<ChatMessageTag> lqw = buildQueryWrapper(tag);
        return baseMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<ChatMessageTag> buildQueryWrapper(ChatMessageTag tag) {
        LambdaQueryWrapper<ChatMessageTag> lqw = Wrappers.lambdaQuery();
        lqw.eq(tag.getUserId() != null, ChatMessageTag::getUserId, tag.getUserId());
        lqw.like(StringUtils.isNotBlank(tag.getTagName()), ChatMessageTag::getTagName, tag.getTagName());
        lqw.like(StringUtils.isNotBlank(tag.getTagColor()), ChatMessageTag::getTagColor, tag.getTagColor());
        lqw.like(StringUtils.isNotBlank(tag.getDescription()), ChatMessageTag::getDescription, tag.getDescription());
        lqw.orderByDesc(ChatMessageTag::getCreateTime);
        return lqw;
    }

    /**
     * 新增聊天消息标签
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertByTag(ChatMessageTag tag) {
        // 设置当前用户ID
        tag.setUserId(LoginHelper.getUserId());
        // 检查标签名称是否已存在
        ChatMessageTag existingTag = baseMapper.selectTagByNameAndUserId(tag.getTagName(), tag.getUserId());
        if (existingTag != null) {
            throw new RuntimeException("标签名称已存在");
        }
        // 设置默认值
        if (StringUtils.isBlank(tag.getTagColor())) {
            tag.setTagColor("#667eea"); // 默认蓝色
        }
        if (tag.getUseCount() == null) {
            tag.setUseCount(0);
        }
        baseMapper.insert(tag);
        return tag.getId();
    }

    /**
     * 修改聊天消息标签
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByTag(ChatMessageTag tag) {
        // 检查标签是否存在
        ChatMessageTag existingTag = baseMapper.selectById(tag.getId());
        if (existingTag == null) {
            throw new RuntimeException("标签不存在");
        }
        // 检查当前用户是否有权限修改该标签
        if (!LoginHelper.isSuperAdmin() && !existingTag.getUserId().equals(LoginHelper.getUserId())) {
            throw new RuntimeException("无权限修改该标签");
        }
        // 检查标签名称是否已存在（排除当前标签）
        ChatMessageTag sameNameTag = baseMapper.selectTagByNameAndUserId(tag.getTagName(), existingTag.getUserId());
        if (sameNameTag != null && !sameNameTag.getId().equals(tag.getId())) {
            throw new RuntimeException("标签名称已存在");
        }
        // 更新标签信息
        tag.setUserId(existingTag.getUserId()); // 确保用户ID不变
        baseMapper.updateById(tag);
        return true;
    }

    /**
     * 删除聊天消息标签
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        // 检查当前用户是否有权限删除这些标签
        for (Long id : ids) {
            ChatMessageTag tag = baseMapper.selectById(id);
            if (tag == null) {
                throw new RuntimeException("标签不存在");
            }
            if (!LoginHelper.isSuperAdmin() && !tag.getUserId().equals(LoginHelper.getUserId())) {
                throw new RuntimeException("无权限删除该标签");
            }
            // 如果需要验证标签是否被使用
            if (isValid && tag.getUseCount() != null && tag.getUseCount() > 0) {
                throw new RuntimeException("标签正在被使用，无法删除");
            }
        }
        // 删除标签
        baseMapper.deleteBatchIds(ids);
        return true;
    }

    /**
     * 根据用户ID查询标签列表
     */
    @Override
    public List<ChatMessageTag> selectTagsByUserId(Long userId) {
        return baseMapper.selectTagsByUserId(userId);
    }

    /**
     * 根据标签名称和用户ID查询标签
     */
    @Override
    public ChatMessageTag selectTagByNameAndUserId(String tagName, Long userId) {
        return baseMapper.selectTagByNameAndUserId(tagName, userId);
    }

}
