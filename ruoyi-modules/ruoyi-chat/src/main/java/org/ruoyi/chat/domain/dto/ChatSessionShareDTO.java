package org.ruoyi.chat.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 会话分享DTO
 *
 * @author ruoyi
 * @date 2025-12-01
 */
@Data
public class ChatSessionShareDTO {

    /**
     * 会话ID
     */
    @NotNull(message = "会话ID不能为空")
    private Long sessionId;

    /**
     * 查看密码（可选）
     */
    private String password;

    /**
     * 过期时间（可选，永久有效则为null）
     */
    private LocalDateTime expireTime;
}
