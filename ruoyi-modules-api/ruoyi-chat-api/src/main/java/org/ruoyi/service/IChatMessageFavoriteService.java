package org.ruoyi.service;

import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;
import org.ruoyi.domain.bo.ChatMessageFavoriteBo;
import org.ruoyi.domain.vo.ChatMessageFavoriteVo;

import java.util.Collection;
import java.util.List;

/**
 * 消息收藏Service接口
 *
 * @author ageerle
 * @date 2025-10-20
 */
public interface IChatMessageFavoriteService {

    /**
     * 查询消息收藏
     */
    ChatMessageFavoriteVo queryById(Long id);

    /**
     * 查询消息收藏列表（分页）
     */
    TableDataInfo<ChatMessageFavoriteVo> queryPageList(ChatMessageFavoriteBo bo, PageQuery pageQuery);

    /**
     * 查询消息收藏列表
     */
    List<ChatMessageFavoriteVo> queryList(ChatMessageFavoriteBo bo);

    /**
     * 新增消息收藏
     */
    Boolean insertByBo(ChatMessageFavoriteBo bo);

    /**
     * 修改消息收藏
     */
    Boolean updateByBo(ChatMessageFavoriteBo bo);

    /**
     * 校验并批量删除消息收藏信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 检查消息是否已收藏
     */
    Boolean isFavorite(Long userId, Long messageId);

    /**
     * 统计用户收藏数量
     */
    int countByUserId(Long userId);

    /**
     * 按模型名称筛选收藏
     */
    List<ChatMessageFavoriteVo> queryByModelName(Long userId, String modelName);

    /**
     * 按会话筛选收藏
     */
    List<ChatMessageFavoriteVo> queryBySessionId(Long userId, Long sessionId);

    /**
     * 按内容关键词搜索收藏
     */
    List<ChatMessageFavoriteVo> searchByContent(Long userId, String content);
}