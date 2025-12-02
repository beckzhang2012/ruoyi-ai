-- ----------------------------
-- Table structure for chat_session_share
-- ----------------------------
DROP TABLE IF EXISTS `chat_session_share`;
CREATE TABLE `chat_session_share`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `session_id` bigint(20) NOT NULL COMMENT '会话ID',
  `share_token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分享token',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '查看密码',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '有效期',
  `status` tinyint(1) NULL DEFAULT 1 COMMENT '状态（0: 无效，1: 有效）',
  `create_by` bigint(20) NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` bigint(20) NULL DEFAULT NULL COMMENT '更新者',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_share_token`(`share_token`) USING BTREE,
  INDEX `idx_session_id`(`session_id`) USING BTREE,
  INDEX `idx_create_by`(`create_by`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '会话分享表' ROW_FORMAT = Dynamic;
