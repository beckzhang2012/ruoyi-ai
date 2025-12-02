package org.ruoyi.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.core.domain.BaseEntity;
import org.ruoyi.domain.ChatMessageFavorite;

/**
 * 消息收藏业务对象 chat_message_favorite
 *
 * @author ruoyi
 * @date 2025-11-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ChatMessageFavorite.class, reverseConvertGenerate = false)
public class ChatMessageFavoriteBo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /** 用户ID */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class })
    private Long userId;

    /** 消息ID */
    @NotNull(message = "消息ID不能为空", groups = { AddGroup.class })
    private Long messageId;

    /** 会话ID */
    private Long sessionId;

    /** 消息内容（冗余存储，方便查询） */
    private String content;

    /** 模型名称 */
    private String modelName;

    /** 备注 */
    private String remark;
}