package org.ruoyi.domain.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.core.domain.BaseEntity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 消息收藏业务对象 chat_message_favorite
 *
 * @author ageerle
 * @date 2025-10-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("消息收藏业务对象")
public class ChatMessageFavoriteBo extends BaseEntity {

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long userId;

    /**
     * 消息id
     */
    @ApiModelProperty(value = "消息id", required = true)
    @NotNull(message = "消息id不能为空")
    private Long messageId;

    /**
     * 会话id
     */
    @ApiModelProperty(value = "会话id")
    private Long sessionId;

    /**
     * 收藏备注
     */
    @ApiModelProperty(value = "收藏备注")
    @Size(max = 200, message = "收藏备注长度不能超过200个字符")
    private String remark;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String messageContent;

    /**
     * 模型名称
     */
    @ApiModelProperty(value = "模型名称")
    private String modelName;

    /**
     * 会话标题
     */
    @ApiModelProperty(value = "会话标题")
    private String sessionTitle;

    /**
     * 对话角色
     */
    @ApiModelProperty(value = "对话角色")
    private String role;

    /**
     * 收藏时间
     */
    @ApiModelProperty(value = "收藏时间")
    private String favoriteTime;
}