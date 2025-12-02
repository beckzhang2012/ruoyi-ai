package org.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.ruoyi.common.core.utils.MapstructUtils;
import org.ruoyi.common.core.utils.StringUtils;
import org.ruoyi.common.satoken.utils.LoginHelper;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.domain.ChatMessageFavorite;
import org.ruoyi.domain.bo.ChatMessageFavoriteBo;
import org.ruoyi.domain.vo.ChatMessageFavoriteVo;
import org.ruoyi.mapper.ChatMessageFavoriteMapper;
import org.ruoyi.service.IChatMessageFavoriteService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 聊天消息收藏Service业务层处理
 *
 * @author ruoyi
 * @date 2025-11-10
 */
@RequiredArgsConstructor
@Service
public class ChatMessageFavoriteServiceImpl implements IChatMessageFavoriteService {

    private final ChatMessageFavoriteMapper baseMapper;

    /**
     * 查询聊天消息收藏
     */
    @Override
    public ChatMessageFavoriteVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询聊天消息收藏列表
     */
    @Override
    public TableDataInfo<ChatMessageFavoriteVo> queryPageList(ChatMessageFavoriteBo bo, PageQuery pageQuery) {
        if (!LoginHelper.isLogin()) {
            return TableDataInfo.build();
        }
        // 只有非管理员才自动设置为自己的 ID
        if (!LoginHelper.isSuperAdmin()) {
            bo.setUserId(LoginHelper.getUserId());
        }
        LambdaQueryWrapper<ChatMessageFavorite> lqw = buildQueryWrapper(bo);
        Page<ChatMessageFavoriteVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询聊天消息收藏列表
     */
    @Override
    public List<ChatMessageFavoriteVo> queryList(ChatMessageFavoriteBo bo) {
        LambdaQueryWrapper<ChatMessageFavorite> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ChatMessageFavorite> buildQueryWrapper(ChatMessageFavoriteBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ChatMessageFavorite> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, ChatMessageFavorite::getUserId, bo.getUserId());
        lqw.eq(bo.getMessageId() != null, ChatMessageFavorite::getMessageId, bo.getMessageId());
        lqw.eq(bo.getSessionId() != null, ChatMessageFavorite::getSessionId, bo.getSessionId());
        lqw.like(StringUtils.isNotBlank(bo.getContent()), ChatMessageFavorite::getContent, bo.getContent());
        lqw.like(StringUtils.isNotBlank(bo.getModelName()), ChatMessageFavorite::getModelName, bo.getModelName());
        lqw.like(StringUtils.isNotBlank(bo.getRemark()), ChatMessageFavorite::getRemark, bo.getRemark());
        return lqw;
    }

    /**
     * 新增聊天消息收藏
     */
    @Override
    public Boolean insertByBo(ChatMessageFavoriteBo bo) {
        ChatMessageFavorite add = MapstructUtils.convert(bo, ChatMessageFavorite.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改聊天消息收藏
     */
    @Override
    public Boolean updateByBo(ChatMessageFavoriteBo bo) {
        ChatMessageFavorite update = MapstructUtils.convert(bo, ChatMessageFavorite.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ChatMessageFavorite entity) {
        //TODO 做一些数据校验，如唯一约束
    }

    /**
     * 批量删除聊天消息收藏
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验，判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}