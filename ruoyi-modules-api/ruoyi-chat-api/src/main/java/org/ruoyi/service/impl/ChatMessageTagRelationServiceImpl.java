package org.ruoyi.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.page.TableDataInfo;
import org.ruoyi.common.utils.StringUtils;
import org.ruoyi.workflow.base.ThreadContext;
import org.ruoyi.domain.ChatMessageTagRelation;
import org.ruoyi.domain.bo.ChatMessageTagRelationBo;
import org.ruoyi.domain.vo.ChatMessageTagRelationVo;
import org.ruoyi.mapper.ChatMessageTagRelationMapper;
import org.ruoyi.service.IChatMessageTagRelationService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消息标签关联表Service业务层处理
 *
 * @author ageerle
 * @date 2025-11-11
 */
@RequiredArgsConstructor
@Service
public class ChatMessageTagRelationServiceImpl implements IChatMessageTagRelationService {

    private final ChatMessageTagRelationMapper baseMapper;

    /**
     * 查询消息标签关联表
     *
     * @param id 消息标签关联表主键
     * @return 消息标签关联表
     */
    @Override
    public ChatMessageTagRelationVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询消息标签关联表列表
     *
     * @param bo 消息标签关联表
     * @return 消息标签关联表集合
     */
    @Override
    public TableDataInfo<ChatMessageTagRelationVo> queryPageList(ChatMessageTagRelationBo bo) {
        LambdaQueryWrapper<ChatMessageTagRelation> lqw = buildQueryWrapper(bo);
        Page<ChatMessageTagRelationVo> result = baseMapper.selectVoPage(bo.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询消息标签关联表列表
     *
     * @param bo 消息标签关联表
     * @return 消息标签关联表集合
     */
    @Override
    public List<ChatMessageTagRelationVo> queryList(ChatMessageTagRelationBo bo) {
        LambdaQueryWrapper<ChatMessageTagRelation> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ChatMessageTagRelation> buildQueryWrapper(ChatMessageTagRelationBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ChatMessageTagRelation> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMessageId() != null, ChatMessageTagRelation::getMessageId, bo.getMessageId());
        lqw.eq(bo.getTagId() != null, ChatMessageTagRelation::getTagId, bo.getTagId());
        lqw.eq(bo.getUserId() != null, ChatMessageTagRelation::getUserId, bo.getUserId());
        return lqw;
    }

    /**
     * 新增消息标签关联表
     *
     * @param bo 消息标签关联表
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> insertByBo(ChatMessageTagRelationBo bo) {
        ChatMessageTagRelation add = BeanUtil.toBean(bo, ChatMessageTagRelation.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return R.ok(flag);
    }

    /**
     * 修改消息标签关联表
     *
     * @param bo 消息标签关联表
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> updateByBo(ChatMessageTagRelationBo bo) {
        ChatMessageTagRelation update = BeanUtil.toBean(bo, ChatMessageTagRelation.class);
        validEntityBeforeSave(update);
        return R.ok(baseMapper.updateById(update) > 0);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(ChatMessageTagRelation entity) {
        // 自己实现校验逻辑，如唯一约束
    }

    /**
     * 批量删除消息标签关联表
     *
     * @param ids 需要删除的消息标签关联表主键集合
     * @param isValid 是否需要验证数据有效性
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> deleteWithValidByIds(List<Long> ids, Boolean isValid) {
        if (isValid) {
            // 自己实现校验逻辑，判断是否有相关联的数据
        }
        return R.ok(baseMapper.deleteBatchIds(ids) > 0);
    }

    /**
     * 为消息添加标签
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> addTagsToMessage(Long messageId, List<Long> tagIds) {
        // 先查询消息的所有标签
        List<ChatMessageTagRelationVo> existingRelations = getTagsByMessageId(messageId);
        List<Long> existingTagIds = existingRelations.stream().map(ChatMessageTagRelationVo::getTagId).collect(Collectors.toList());

        // 过滤掉已经存在的标签
        List<Long> newTagIds = tagIds.stream().filter(tagId -> !existingTagIds.contains(tagId)).collect(Collectors.toList());

        // 批量插入新的标签关联
        if (!newTagIds.isEmpty()) {
            List<ChatMessageTagRelation> relations = newTagIds.stream().map(tagId -> {
                ChatMessageTagRelation relation = new ChatMessageTagRelation();
                relation.setMessageId(messageId);
                relation.setTagId(tagId);
                // 这里需要设置用户ID，从当前登录用户获取
                relation.setUserId(ThreadContext.getCurrentUserId());
                return relation;
            }).collect(Collectors.toList());

            baseMapper.insertBatchSomeColumn(relations);
        }

        return R.ok(true);
    }

    /**
     * 从消息中移除标签
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> removeTagsFromMessage(Long messageId, List<Long> tagIds) {
        // 批量删除标签关联
        LambdaQueryWrapper<ChatMessageTagRelation> lqw = Wrappers.lambdaQuery();
        lqw.eq(ChatMessageTagRelation::getMessageId, messageId);
        lqw.in(ChatMessageTagRelation::getTagId, tagIds);

        baseMapper.delete(lqw);

        return R.ok(true);
    }

    /**
     * 获取消息的所有标签
     *
     * @param messageId 消息ID
     * @return 标签集合
     */
    @Override
    public List<ChatMessageTagRelationVo> getTagsByMessageId(Long messageId) {
        LambdaQueryWrapper<ChatMessageTagRelation> lqw = Wrappers.lambdaQuery();
        lqw.eq(ChatMessageTagRelation::getMessageId, messageId);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 获取标签的所有消息
     *
     * @param tagId 标签ID
     * @return 消息ID集合
     */
    @Override
    public List<Long> getMessagesByTagId(Long tagId) {
        LambdaQueryWrapper<ChatMessageTagRelation> lqw = Wrappers.lambdaQuery();
        lqw.eq(ChatMessageTagRelation::getTagId, tagId);
        List<ChatMessageTagRelationVo> relations = baseMapper.selectVoList(lqw);
        return relations.stream().map(ChatMessageTagRelationVo::getMessageId).collect(Collectors.toList());
    }
}
