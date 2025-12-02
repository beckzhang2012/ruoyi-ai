package org.ruoyi.chat.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 聊天消息收藏实体类
 *
 * @author ruoyi
 * @date 2025-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("chat_message_favorite")
public class ChatMessageFavorite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 收藏用户ID
     */
    private Long userId;

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 会话ID
     */
    private Long sessionId;

    /**
     * 会话名称
     */
    private String sessionName;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 收藏备注
     */
    private String remark;

    /**
     * 收藏时间
     */
    private LocalDateTime createTime;
}
