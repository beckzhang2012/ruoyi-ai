package org.ruoyi.chat.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * 聊天消息收藏DTO
 *
 * @author ruoyi
 * @date 2025-12-01
 */
@Data
public class ChatMessageFavoriteDTO {

    /**
     * 消息ID
     */
    @NotNull(message = "消息ID不能为空")
    private Long messageId;

    /**
     * 会话ID
     */
    @NotNull(message = "会话ID不能为空")
    private Long sessionId;

    /**
     * 会话名称
     */
    @NotBlank(message = "会话名称不能为空")
    private String sessionName;

    /**
     * 模型名称
     */
    @NotBlank(message = "模型名称不能为空")
    private String modelName;

    /**
     * 消息内容
     */
    @NotBlank(message = "消息内容不能为空")
    private String messageContent;

    /**
     * 收藏备注
     */
    private String remark;
}
