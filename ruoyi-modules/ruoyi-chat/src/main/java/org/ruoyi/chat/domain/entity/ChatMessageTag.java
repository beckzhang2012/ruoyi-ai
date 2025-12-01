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
 * 聊天消息标签实体类
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Data
@TableName("chat_message_tag")
public class ChatMessageTag implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 标签ID */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 标签名称 */
    @TableField("tag_name")
    private String tagName;

    /** 标签颜色 */
    @TableField("tag_color")
    private String tagColor;

    /** 用户ID */
    @TableField("user_id")
    private Long userId;

    /** 排序 */
    @TableField("sort")
    private Integer sort;

    /** 备注 */
    @TableField("remark")
    private String remark;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("create_time")
    private LocalDateTime createTime;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("update_time")
    private LocalDateTime updateTime;
}