package org.ruoyi.chat.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 会话分享实体类
 *
 * @author ruoyi
 * @date 2025-12-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("chat_session_share")
public class ChatSessionShare implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 会话ID
     */
    private Long sessionId;

    /**
     * 分享用户ID
     */
    private Long userId;

    /**
     * 分享码（唯一）
     */
    private String shareCode;

    /**
     * 查看密码（可选）
     */
    private String password;

    /**
     * 过期时间（可选，永久有效则为null）
     */
    private LocalDateTime expireTime;

    /**
     * 查看次数
     */
    private Integer viewCount;

    /**
     * 分享状态：1-正常 0-已取消
     */
    private Integer status;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}