-- ----------------------------
-- Table structure for chat_session_share
-- ----------------------------
DROP TABLE IF EXISTS `chat_session_share`;
CREATE TABLE `chat_session_share`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `session_id` bigint(20) NOT NULL COMMENT '会话ID',
    `user_id` bigint(20) NOT NULL COMMENT '分享者用户ID',
    `share_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分享链接唯一标识',
    `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分享密码（可选）',
    `valid_hours` int(11) NOT NULL DEFAULT 0 COMMENT '有效期（单位：小时，0表示永久有效）',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '分享创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '分享更新时间',
    `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '分享状态（0：有效，1：已取消，2：已过期）',
    `view_count` int(11) NOT NULL DEFAULT 0 COMMENT '查看次数',
    `del_flag` tinyint(4) NOT NULL DEFAULT 0 COMMENT '删除标志（0：未删除，1：已删除）',
    `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `uk_share_code`(`share_code`) USING BTREE,
    INDEX `idx_session_id`(`session_id`) USING BTREE,
    INDEX `idx_user_id`(`user_id`) USING BTREE,
    INDEX `idx_status`(`status`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会话分享表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_session_share
-- ----------------------------

-- ----------------------------
-- Table structure for chat_session_share_view
-- ----------------------------
DROP TABLE IF EXISTS `chat_session_share_view`;
CREATE TABLE `chat_session_share_view`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `share_id` bigint(20) NOT NULL COMMENT '会话分享ID',
    `visitor_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问者IP',
    `visitor_user_agent` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问者浏览器信息',
    `view_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_share_id`(`share_id`) USING BTREE,
    INDEX `idx_view_time`(`view_time`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会话分享访问记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of chat_session_share_view
-- ----------------------------
