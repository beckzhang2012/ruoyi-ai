package org.ruoyi.chat.service;

import org.ruoyi.chat.domain.ChatMessageTag;
import org.ruoyi.core.page.PageQuery;
import org.ruoyi.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 聊天消息标签Service接口
 *
 * @author ruoyi
 * @date 2025-11-01
 */
public interface IChatMessageTagService {

    /**
     * 查询聊天消息标签
     */
    ChatMessageTag queryById(Long id);

    /**
     * 查询聊天消息标签列表
     */
    TableDataInfo<ChatMessageTag> queryPageList(ChatMessageTag tag, PageQuery pageQuery);

    /**
     * 查询聊天消息标签列表
     */
    List<ChatMessageTag> queryList(ChatMessageTag tag);

    /**
     * 新增聊天消息标签
     */
    Long insertByTag(ChatMessageTag tag);

    /**
     * 修改聊天消息标签
     */
    Boolean updateByTag(ChatMessageTag tag);

    /**
     * 删除聊天消息标签
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 根据用户ID查询标签列表
     */
    List<ChatMessageTag> selectTagsByUserId(Long userId);

    /**
     * 根据标签名称和用户ID查询标签
     */
    ChatMessageTag selectTagByNameAndUserId(String tagName, Long userId);

}
