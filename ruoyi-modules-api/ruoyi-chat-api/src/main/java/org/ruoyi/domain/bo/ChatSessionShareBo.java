package org.ruoyi.domain.bo;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 会话分享业务对象
 * 
 * @author ruoyi
 * @date 2025-10-20
 */
@Data
public class ChatSessionShareBo {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 会话ID */
    @NotNull(message = "会话ID不能为空")
    private Long sessionId;

    /** 分享密码（可选，长度不超过20） */
    @Size(max = 20, message = "分享密码长度不能超过20个字符")
    private String password;

    /** 有效期（单位：小时，0表示永久有效） */
    private Integer validHours = 0;

    /** 分享状态（0：有效，1：已取消，2：已过期） */
    private Integer status;

    /** 备注 */
    private String remark;
}
