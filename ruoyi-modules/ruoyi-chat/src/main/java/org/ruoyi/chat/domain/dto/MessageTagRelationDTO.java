package org.ruoyi.chat.domain.dto;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 消息标签关联数据传输对象
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Data
public class MessageTagRelationDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 消息ID */
    @NotNull(message = "消息ID不能为空")
    private Long messageId;

    /** 标签ID列表 */
    @NotEmpty(message = "标签ID列表不能为空")
    private List<Long> tagIds;
}