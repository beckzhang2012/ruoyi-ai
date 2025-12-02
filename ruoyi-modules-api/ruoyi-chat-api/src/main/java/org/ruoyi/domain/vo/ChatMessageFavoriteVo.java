package org.ruoyi.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.ruoyi.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 消息收藏视图对象 chat_message_favorite
 *
 * @author ageerle
 * @date 2025-10-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel("消息收藏视图对象")
public class ChatMessageFavoriteVo extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

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
    @ApiModelProperty(value = "消息id")
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