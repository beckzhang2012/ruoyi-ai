package org.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 消息收藏对象 chat_message_favorite
 *
 * @author ageerle
 * @date 2025-10-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("chat_message_favorite")
public class ChatMessageFavorite extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 消息id
     */
    private Long messageId;

    /**
     * 会话id
     */
    private Long sessionId;

    /**
     * 收藏备注
     */
    private String remark;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 模型名称
     */
    private String modelName;

    /**
     * 会话标题
     */
    private String sessionTitle;

    /**
     * 对话角色
     */
    private String role;

    /**
     * 收藏时间
     */
    private String favoriteTime;
}