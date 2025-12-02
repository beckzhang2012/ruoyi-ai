-- 会话分享表
CREATE TABLE IF NOT EXISTS chat_session_share
(
    id            BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    session_id    BIGINT UNSIGNED NOT NULL COMMENT '会话ID',
    user_id       BIGINT UNSIGNED NOT NULL COMMENT '分享用户ID',
    share_code    VARCHAR(64)     NOT NULL COMMENT '分享码（唯一）',
    password      VARCHAR(255)             COMMENT '查看密码（可选）',
    expire_time   DATETIME                 COMMENT '过期时间（可选，永久有效则为null）',
    view_count    INT UNSIGNED   DEFAULT 0 COMMENT '查看次数',
    status        TINYINT        DEFAULT 1 COMMENT '分享状态：1-正常 0-已取消',
    create_time   DATETIME       DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time   DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (id),
    UNIQUE KEY uk_share_code (share_code),
    KEY idx_session_id (session_id),
    KEY idx_user_id (user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='会话分享表';