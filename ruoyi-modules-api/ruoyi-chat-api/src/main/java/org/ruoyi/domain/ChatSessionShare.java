package org.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.util.Date;

/**
 * 会话分享表
 * 
 * @author ruoyi
 * @date 2025-10-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("chat_session_share")
public class ChatSessionShare implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /** 会话ID */
    @TableField("session_id")
    private Long sessionId;

    /** 分享者用户ID */
    @TableField("user_id")
    private Long userId;

    /** 分享链接唯一标识 */
    @TableField("share_code")
    private String shareCode;

    /** 分享密码（可选） */
    @TableField("password")
    private String password;

    /** 有效期（单位：小时，0表示永久有效） */
    @TableField("valid_hours")
    private Integer validHours;

    /** 分享创建时间 */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /** 分享更新时间 */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 分享状态（0：有效，1：已取消，2：已过期） */
    @TableField("status")
    private Integer status;

    /** 查看次数 */
    @TableField("view_count")
    private Integer viewCount;

    /** 删除标志（0：未删除，1：已删除） */
    @TableField("del_flag")
    private Integer delFlag;

    /** 备注 */
    @TableField("remark")
    private String remark;
}
