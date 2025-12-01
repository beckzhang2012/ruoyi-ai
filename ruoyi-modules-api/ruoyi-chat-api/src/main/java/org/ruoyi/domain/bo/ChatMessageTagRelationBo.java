package org.ruoyi.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.domain.ChatMessageTagRelation;

import javax.validation.constraints.NotNull;

/**
 * 消息标签关联表业务对象
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatMessageTagRelationBo extends ChatMessageTagRelation {

    /**
     * 消息ID
     */
    @NotNull(message = "消息ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long messageId;

    /**
     * 标签ID
     */
    @NotNull(message = "标签ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long tagId;
}
