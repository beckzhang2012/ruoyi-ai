package org.ruoyi.chat.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.ruoyi.chat.domain.ChatMessageTag;

import java.util.List;

/**
 * 聊天消息标签Service接口
 * 
 * @author ruoyi
 * @date 2024-05-20
 */
public interface IChatMessageTagService extends IService<ChatMessageTag>
{
    /**
     * 查询聊天消息标签列表
     * 
     * @param chatMessageTag 聊天消息标签
     * @return 聊天消息标签集合
     */
    public List<ChatMessageTag> selectChatMessageTagList(ChatMessageTag chatMessageTag);

    /**
     * 根据用户ID查询标签列表
     * 
     * @param userId 用户ID
     * @return 标签集合
     */
    public List<ChatMessageTag> selectTagsByUserId(Long userId);

    /**
     * 创建标签
     * 
     * @param chatMessageTag 标签对象
     * @return 标签ID
     */
    public Long createTag(ChatMessageTag chatMessageTag);

    /**
     * 编辑标签
     * 
     * @param chatMessageTag 标签对象
     * @return 影响行数
     */
    public int updateTag(ChatMessageTag chatMessageTag);

    /**
     * 删除标签
     * 
     * @param tagId 标签ID
     * @return 影响行数
     */
    public int deleteTag(Long tagId);

    /**
     * 批量删除标签
     * 
     * @param tagIds 标签ID集合
     * @return 影响行数
     */
    public int deleteTags(List<Long> tagIds);
}
