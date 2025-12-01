package org.ruoyi.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.ruoyi.chat.domain.entity.ChatMessageTagRelation;

import java.util.List;

/**
 * 聊天消息标签关联Mapper接口
 *
 * @author ruoyi
 * @date 2024-06-11
 */
public interface ChatMessageTagRelationMapper extends BaseMapper<ChatMessageTagRelation> {

    /**
     * 查询消息的所有关联标签ID
     *
     * @param messageId 消息ID
     * @return 标签ID列表
     */
    List<Long> selectTagIdsByMessageId(Long messageId);

    /**
     * 查询标签的所有关联消息ID
     *
     * @param tagId 标签ID
     * @return 消息ID列表
     */
    List<Long> selectMessageIdsByTagId(Long tagId);

    /**
     * 根据消息ID删除关联关系
     *
     * @param messageId 消息ID
     * @return 结果
     */
    int deleteByMessageId(Long messageId);

    /**
     * 根据标签ID删除关联关系
     *
     * @param tagId 标签ID
     * @return 结果
     */
    int deleteByTagId(Long tagId);
}