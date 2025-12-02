package org.ruoyi.service;

import org.ruoyi.common.core.page.TableDataInfo;
import org.ruoyi.domain.bo.ChatMessageFavoriteBo;
import org.ruoyi.domain.vo.ChatMessageFavoriteVo;

import java.util.List;

/**
 * 消息收藏Service接口
 *
 * @author ruoyi
 * @date 2025-11-10
 */
public interface IChatMessageFavoriteService {

    /**
     * 查询消息收藏列表
     *
     * @param bo 消息收藏业务对象
     * @return 消息收藏视图对象列表
     */
    TableDataInfo<ChatMessageFavoriteVo> queryPageList(ChatMessageFavoriteBo bo);

    /**
     * 查询消息收藏列表
     *
     * @param bo 消息收藏业务对象
     * @return 消息收藏视图对象列表
     */
    List<ChatMessageFavoriteVo> queryList(ChatMessageFavoriteBo bo);

    /**
     * 通过主键查询消息收藏
     *
     * @param id 主键
     * @return 消息收藏视图对象
     */
    ChatMessageFavoriteVo queryById(Long id);

    /**
     * 新增消息收藏
     *
     * @param bo 消息收藏业务对象
     * @return 结果
     */
    Boolean insertByBo(ChatMessageFavoriteBo bo);

    /**
     * 修改消息收藏
     *
     * @param bo 消息收藏业务对象
     * @return 结果
     */
    Boolean updateByBo(ChatMessageFavoriteBo bo);

    /**
     * 批量删除消息收藏
     *
     * @param ids 主键数组
     * @return 结果
     */
    Boolean deleteWithValidByIds(List<Long> ids, Boolean isValid);
}