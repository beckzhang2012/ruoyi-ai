package org.ruoyi.chat.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.ruoyi.common.core.domain.BaseEntity;

/**
 * 聊天消息标签关系对象 chat_message_tag_relation
 * 
 * @author ruoyi
 * @date 2024-05-20
 */
@Data
@TableName("chat_message_tag_relation")
public class ChatMessageTagRelation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关系ID */
    @TableId(type = IdType.AUTO)
    private Long relationId;

    /** 标签ID */
    private Long tagId;

    /** 消息ID */
    private Long messageId;
}
