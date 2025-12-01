package org.ruoyi.service;

import org.ruoyi.common.core.domain.R;
import org.ruoyi.common.core.page.TableDataInfo;
import org.ruoyi.domain.bo.ChatMessageTagRelationBo;
import org.ruoyi.domain.vo.ChatMessageTagRelationVo;

import java.util.List;

/**
 * 消息标签关联表Service接口
 *
 * @author ageerle
 * @date 2025-11-11
 */
public interface IChatMessageTagRelationService {

    /**
     * 查询消息标签关联表
     *
     * @param id 消息标签关联表主键
     * @return 消息标签关联表
     */
    ChatMessageTagRelationVo queryById(Long id);

    /**
     * 查询消息标签关联表列表
     *
     * @param bo 消息标签关联表
     * @return 消息标签关联表集合
     */
    TableDataInfo<ChatMessageTagRelationVo> queryPageList(ChatMessageTagRelationBo bo);

    /**
     * 查询消息标签关联表列表
     *
     * @param bo 消息标签关联表
     * @return 消息标签关联表集合
     */
    List<ChatMessageTagRelationVo> queryList(ChatMessageTagRelationBo bo);

    /**
     * 新增消息标签关联表
     *
     * @param bo 消息标签关联表
     * @return 结果
     */
    R<Boolean> insertByBo(ChatMessageTagRelationBo bo);

    /**
     * 修改消息标签关联表
     *
     * @param bo 消息标签关联表
     * @return 结果
     */
    R<Boolean> updateByBo(ChatMessageTagRelationBo bo);

    /**
     * 批量删除消息标签关联表
     *
     * @param ids 需要删除的消息标签关联表主键集合
     * @return 结果
     */
    R<Boolean> deleteWithValidByIds(List<Long> ids, Boolean isValid);

    /**
     * 为消息添加标签
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     * @return 结果
     */
    R<Boolean> addTagsToMessage(Long messageId, List<Long> tagIds);

    /**
     * 从消息中移除标签
     *
     * @param messageId 消息ID
     * @param tagIds 标签ID集合
     * @return 结果
     */
    R<Boolean> removeTagsFromMessage(Long messageId, List<Long> tagIds);

    /**
     * 获取消息的所有标签
     *
     * @param messageId 消息ID
     * @return 标签集合
     */
    List<ChatMessageTagRelationVo> getTagsByMessageId(Long messageId);

    /**
     * 获取标签的所有消息
     *
     * @param tagId 标签ID
     * @return 消息ID集合
     */
    List<Long> getMessagesByTagId(Long tagId);
}
