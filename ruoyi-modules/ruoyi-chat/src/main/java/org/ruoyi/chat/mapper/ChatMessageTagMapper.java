package org.ruoyi.chat.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.ruoyi.chat.domain.ChatMessageTag;
import org.ruoyi.core.mapper.BaseMapperPlus;

import java.util.List;

/**
 * 聊天消息标签Mapper接口
 *
 * @author ruoyi
 * @date 2025-11-01
 */
@Mapper
public interface ChatMessageTagMapper extends BaseMapperPlus<ChatMessageTag, ChatMessageTag> {

    /**
     * 根据用户ID查询标签列表
     *
     * @param userId 用户ID
     * @return 标签列表
     */
    List<ChatMessageTag> selectTagsByUserId(@Param("userId") Long userId);

    /**
     * 根据标签名称和用户ID查询标签
     *
     * @param tagName 标签名称
     * @param userId 用户ID
     * @return 标签信息
     */
    ChatMessageTag selectTagByNameAndUserId(@Param("tagName") String tagName, @Param("userId") Long userId);

    /**
     * 增加标签使用次数
     *
     * @param tagId 标签ID
     * @return 影响行数
     */
    int incrementTagUseCount(@Param("tagId") Long tagId);

    /**
     * 减少标签使用次数
     *
     * @param tagId 标签ID
     * @return 影响行数
     */
    int decrementTagUseCount(@Param("tagId") Long tagId);

}