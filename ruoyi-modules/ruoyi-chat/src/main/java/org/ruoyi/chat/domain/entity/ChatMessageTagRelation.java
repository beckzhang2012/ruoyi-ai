package org.ruoyi.chat.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天消息标签关联实体类
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Data
@TableName("chat_message_tag_relation")
public class ChatMessageTagRelation implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 关联ID */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 消息ID */
    @TableField("message_id")
    private Long messageId;

    /** 标签ID */
    @TableField("tag_id")
    private Long tagId;

    /** 用户ID */
    @TableField("user_id")
    private Long userId;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private LocalDateTime createTime;
}