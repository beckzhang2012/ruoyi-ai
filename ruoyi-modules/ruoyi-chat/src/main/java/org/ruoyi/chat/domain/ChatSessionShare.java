package org.ruoyi.chat.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 会话分享表 chat_session_share
 * 
 * @author ageerle
 * @date 2025-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("chat_session_share")
public class ChatSessionShare extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 会话ID */
    private Long sessionId;

    /** 分享token */
    private String shareToken;

    /** 查看密码 */
    private String password;

    /** 有效期 */
    private Date expireTime;

    /** 状态（0: 无效，1: 有效） */
    private Integer status;

    /** 创建者 */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新者 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 备注 */
    private String remark;
}
