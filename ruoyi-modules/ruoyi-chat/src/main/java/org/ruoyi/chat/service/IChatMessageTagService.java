package org.ruoyi.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ruoyi.chat.domain.dto.ChatMessageTagDTO;
import org.ruoyi.chat.domain.dto.MessageTagRelationDTO;
import org.ruoyi.chat.domain.entity.ChatMessageTag;

import java.util.List;

/**
 * 聊天消息标签Service接口
 *
 * @author ruoyi
 * @date 2024-06-11
 */
public interface IChatMessageTagService extends IService<ChatMessageTag> {

    /**
     * 查询用户的所有标签
     *
     * @return 标签列表
     */
    List<ChatMessageTag> selectUserTags();

    /**
     * 查询聊天消息标签
     *
     * @param id 聊天消息标签主键
     * @return 聊天消息标签
     */
    ChatMessageTag selectChatMessageTagById(Long id);

    /**
     * 新增聊天消息标签
     *
     * @param chatMessageTagDTO 聊天消息标签
     * @return 结果
     */
    int insertChatMessageTag(ChatMessageTagDTO chatMessageTagDTO);

    /**
     * 修改聊天消息标签
     *
     * @param chatMessageTagDTO 聊天消息标签
     * @return 结果
     */
    int updateChatMessageTag(ChatMessageTagDTO chatMessageTagDTO);

    /**
     * 批量删除聊天消息标签
     *
     * @param ids 需要删除的聊天消息标签主键集合
     * @return 结果
     */
    int deleteChatMessageTagByIds(Long[] ids);

    /**
     * 删除聊天消息标签信息
     *
     * @param id 聊天消息标签主键
     * @return 结果
     */
    int deleteChatMessageTagById(Long id);

    /**
     * 给消息添加标签
     *
     * @param messageTagRelationDTO 消息标签关联信息
     * @return 结果
     */
    int addMessageTags(MessageTagRelationDTO messageTagRelationDTO);

    /**
     * 移除消息的标签
     *
     * @param messageTagRelationDTO 消息标签关联信息
     * @return 结果
     */
    int removeMessageTags(MessageTagRelationDTO messageTagRelationDTO);

    /**
     * 获取消息的标签列表
     *
     * @param messageId 消息ID
     * @return 标签列表
     */
    List<ChatMessageTag> selectMessageTags(Long messageId);

    /**
     * 获取标签下的所有消息ID
     *
     * @param tagId 标签ID
     * @return 消息ID列表
     */
    List<Long> selectMessageIdsByTagId(Long tagId);
}