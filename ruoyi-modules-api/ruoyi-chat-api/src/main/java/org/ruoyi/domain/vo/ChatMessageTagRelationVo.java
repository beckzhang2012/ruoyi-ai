package org.ruoyi.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.domain.ChatMessageTagRelation;

import java.util.Date;

/**
 * 消息标签关联表视图对象
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatMessageTagRelationVo extends ChatMessageTagRelation {

    /**
     * 主键
     */
    private Long id;

    /**
     * 消息ID
     */
    private Long messageId;

    /**
     * 标签ID
     */
    private Long tagId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
