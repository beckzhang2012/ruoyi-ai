package org.ruoyi.chat.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.ruoyi.common.core.domain.BaseEntity;

/**
 * 聊天消息标签对象 chat_message_tag
 * 
 * @author ruoyi
 * @date 2024-05-20
 */
@Data
@TableName("chat_message_tag")
public class ChatMessageTag extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标签ID */
    @TableId(type = IdType.AUTO)
    private Long tagId;

    /** 标签名称 */
    private String tagName;

    /** 标签颜色 */
    private String tagColor;

    /** 用户ID */
    private Long userId;

    /** 使用次数 */
    private Integer useCount;
}
