package org.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.ruoyi.common.annotation.Excel;
import org.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 消息收藏对象 chat_message_favorite
 *
 * @author ruoyi
 * @date 2025-11-10
 */
@Data
@TableName("chat_message_favorite")
public class ChatMessageFavorite extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 用户ID */
    private Long userId;

    /** 消息ID */
    private Long messageId;

    /** 会话ID */
    private Long sessionId;

    /** 消息内容（冗余存储，方便查询） */
    private String content;

    /** 模型名称 */
    private String modelName;

    /** 备注 */
    private String remark;

    /** 收藏时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新时间 */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /** 删除标志（0-存在，2-删除） */
    @TableLogic
    private String delFlag;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("messageId", getMessageId())
            .append("sessionId", getSessionId())
            .append("content", getContent())
            .append("modelName", getModelName())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("delFlag", getDelFlag())
            .toString();
    }
}