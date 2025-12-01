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
import org.ruoyi.domain.ChatMessageTag;
import org.ruoyi.domain.bo.ChatMessageTagBo;
import org.ruoyi.domain.vo.ChatMessageTagVo;
import org.ruoyi.mapper.ChatMessageTagMapper;
import org.ruoyi.service.IChatMessageTagService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 消息标签表Service业务层处理
 *
 * @author ageerle
 * @date 2025-11-11
 */
@RequiredArgsConstructor
@Service
public class ChatMessageTagServiceImpl implements IChatMessageTagService {

    private final ChatMessageTagMapper baseMapper;

    /**
     * 查询消息标签表
     *
     * @param id 消息标签表主键
     * @return 消息标签表
     */
    @Override
    public ChatMessageTagVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询消息标签表列表
     *
     * @param bo 消息标签表
     * @return 消息标签表集合
     */
    @Override
    public TableDataInfo<ChatMessageTagVo> queryPageList(ChatMessageTagBo bo) {
        LambdaQueryWrapper<ChatMessageTag> lqw = buildQueryWrapper(bo);
        Page<ChatMessageTagVo> result = baseMapper.selectVoPage(bo.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询消息标签表列表
     *
     * @param bo 消息标签表
     * @return 消息标签表集合
     */
    @Override
    public List<ChatMessageTagVo> queryList(ChatMessageTagBo bo) {
        LambdaQueryWrapper<ChatMessageTag> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ChatMessageTag> buildQueryWrapper(ChatMessageTagBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ChatMessageTag> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, ChatMessageTag::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getTagName()), ChatMessageTag::getTagName, bo.getTagName());
        lqw.eq(StringUtils.isNotBlank(bo.getTagColor()), ChatMessageTag::getTagColor, bo.getTagColor());
        lqw.eq(bo.getDelFlag() != null, ChatMessageTag::getDelFlag, bo.getDelFlag());
        return lqw;
    }

    /**
     * 新增消息标签表
     *
     * @param bo 消息标签表
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> insertByBo(ChatMessageTagBo bo) {
        ChatMessageTag add = BeanUtil.toBean(bo, ChatMessageTag.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return R.ok(flag);
    }

    /**
     * 修改消息标签表
     *
     * @param bo 消息标签表
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public R<Boolean> updateByBo(ChatMessageTagBo bo) {
        ChatMessageTag update = BeanUtil.toBean(bo, ChatMessageTag.class);
        validEntityBeforeSave(update);
        return R.ok(baseMapper.updateById(update) > 0);
    }

    /**
     * 保存前的数据校验
     *
     * @param entity 实体类数据
     */
    private void validEntityBeforeSave(ChatMessageTag entity) {
        // 自己实现校验逻辑，如唯一约束
    }

    /**
     * 批量删除消息标签表
     *
     * @param ids 需要删除的消息标签表主键集合
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
}
