package org.ruoyi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.domain.ChatMessageFavorite;
import org.ruoyi.domain.bo.ChatMessageFavoriteBo;
import org.ruoyi.domain.vo.ChatMessageFavoriteVo;
import org.ruoyi.mapper.ChatMessageFavoriteMapper;
import org.ruoyi.service.IChatMessageFavoriteService;

import java.util.Collection;
import java.util.List;

/**
 * 消息收藏Service业务层处理
 *
 * @author ageerle
 * @date 2025-10-20
 */
@RequiredArgsConstructor
@Service
public class ChatMessageFavoriteServiceImpl extends ServiceImpl<ChatMessageFavoriteMapper, ChatMessageFavorite>
        implements IChatMessageFavoriteService {

    private final ChatMessageFavoriteMapper baseMapper;

    @Override
    public ChatMessageFavoriteVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public TableDataInfo<ChatMessageFavoriteVo> queryPageList(ChatMessageFavoriteBo bo, PageQuery pageQuery) {
        IPage<ChatMessageFavoriteVo> page = baseMapper.selectPage(pageQuery.build(), buildQueryWrapper(bo));
        return TableDataInfo.build(page);
    }

    @Override
    public List<ChatMessageFavoriteVo> queryList(ChatMessageFavoriteBo bo) {
        return baseMapper.selectList(buildQueryWrapper(bo));
    }

    private QueryWrapper<ChatMessageFavorite> buildQueryWrapper(ChatMessageFavoriteBo bo) {
        QueryWrapper<ChatMessageFavorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(bo.getUserId() != null, "user_id", bo.getUserId());
        queryWrapper.eq(bo.getMessageId() != null, "message_id", bo.getMessageId());
        queryWrapper.eq(bo.getSessionId() != null, "session_id", bo.getSessionId());
        queryWrapper.eq(bo.getModelName() != null, "model_name", bo.getModelName());
        queryWrapper.like(bo.getContent() != null, "message_content", bo.getContent());
        queryWrapper.orderByDesc("create_time");
        return queryWrapper;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean insertByBo(ChatMessageFavoriteBo bo) {
        ChatMessageFavorite add = new ChatMessageFavorite();
        BeanUtils.copyProperties(bo, add);
        return baseMapper.insert(add) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateByBo(ChatMessageFavoriteBo bo) {
        ChatMessageFavorite update = new ChatMessageFavorite();
        BeanUtils.copyProperties(bo, update);
        return baseMapper.updateById(update) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            // 可以添加删除前的校验逻辑
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean isFavorite(Long userId, Long messageId) {
        ChatMessageFavorite favorite = baseMapper.selectByUserIdAndMessageId(userId, messageId);
        return favorite != null;
    }

    @Override
    public int countByUserId(Long userId) {
        return baseMapper.countByUserId(userId);
    }

    @Override
    public List<ChatMessageFavoriteVo> queryByModelName(Long userId, String modelName) {
        ChatMessageFavoriteBo bo = new ChatMessageFavoriteBo();
        bo.setUserId(userId);
        bo.setModelName(modelName);
        return queryList(bo);
    }

    @Override
    public List<ChatMessageFavoriteVo> queryBySessionId(Long userId, Long sessionId) {
        ChatMessageFavoriteBo bo = new ChatMessageFavoriteBo();
        bo.setUserId(userId);
        bo.setSessionId(sessionId);
        return queryList(bo);
    }

    @Override
    public List<ChatMessageFavoriteVo> searchByContent(Long userId, String content) {
        ChatMessageFavoriteBo bo = new ChatMessageFavoriteBo();
        bo.setUserId(userId);
        bo.setContent(content);
        return queryList(bo);
    }
}