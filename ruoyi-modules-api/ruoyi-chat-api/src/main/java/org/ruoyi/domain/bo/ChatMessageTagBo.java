package org.ruoyi.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.common.core.validate.AddGroup;
import org.ruoyi.common.core.validate.EditGroup;
import org.ruoyi.domain.ChatMessageTag;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 消息标签表业务对象
 *
 * @author ageerle
 * @date 2025-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatMessageTagBo extends ChatMessageTag {

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = {AddGroup.class, EditGroup.class})
    @Size(max = 50, message = "标签名称不能超过50个字符", groups = {AddGroup.class, EditGroup.class})
    private String tagName;

    /**
     * 标签颜色
     */
    @Size(max = 20, message = "标签颜色不能超过20个字符", groups = {AddGroup.class, EditGroup.class})
    private String tagColor;
}
