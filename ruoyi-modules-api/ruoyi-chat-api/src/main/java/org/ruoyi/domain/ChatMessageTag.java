package org.ruoyi.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息标签表实体类
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("chat_message_tag")
public class ChatMessageTag extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签颜色
     */
    private String tagColor;

    /**
     * 删除标志（0-存在，2-删除）
     */
    private String delFlag;
}
