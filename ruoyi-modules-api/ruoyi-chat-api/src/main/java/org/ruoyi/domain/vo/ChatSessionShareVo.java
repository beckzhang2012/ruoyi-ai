package org.ruoyi.domain.vo;

import lombok.Data;
import java.util.Date;

/**
 * 会话分享视图对象
 * 
 * @author ruoyi
 * @date 2025-10-20
 */
@Data
public class ChatSessionShareVo {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 会话ID */
    private Long sessionId;

    /** 会话名称 */
    private String sessionName;

    /** 分享者用户ID */
    private Long userId;

    /** 分享者用户名 */
    private String userName;

    /** 分享链接唯一标识 */
    private String shareCode;

    /** 分享密码（可选） */
    private String password;

    /** 有效期（单位：小时，0表示永久有效） */
    private Integer validHours;

    /** 分享创建时间 */
    private Date createTime;

    /** 分享更新时间 */
    private Date updateTime;

    /** 分享状态（0：有效，1：已取消，2：已过期） */
    private Integer status;

    /** 分享状态名称 */
    private String statusName;

    /** 查看次数 */
    private Integer viewCount;

    /** 备注 */
    private String remark;

    /** 分享链接 */
    private String shareUrl;
}
