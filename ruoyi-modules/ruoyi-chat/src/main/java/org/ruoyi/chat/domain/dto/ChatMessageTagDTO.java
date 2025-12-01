package org.ruoyi.chat.domain.dto;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

/**
 * 聊天消息标签数据传输对象
 *
 * @author ruoyi
 * @date 2024-06-11
 */
@Data
public class ChatMessageTagDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 标签ID */
    private Long id;

    /** 标签名称 */
    @NotBlank(message = "标签名称不能为空")
    private String tagName;

    /** 标签颜色 */
    private String tagColor;

    /** 排序 */
    private Integer sort;

    /** 备注 */
    private String remark;
}