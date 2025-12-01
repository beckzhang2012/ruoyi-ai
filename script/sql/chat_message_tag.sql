-- ----------------------------
-- Table structure for chat_message_tag
-- ----------------------------
DROP TABLE IF EXISTS `chat_message_tag`;
CREATE TABLE `chat_message_tag`  (
  `tag_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `tag_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '标签名称',
  `tag_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标签颜色',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `use_count` int(11) NULL DEFAULT 0 COMMENT '使用次数',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`tag_id`) USING BTREE,
  UNIQUE INDEX `unique_tag_name_user_id`(`tag_name`, `user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '聊天消息标签表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for chat_message_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `chat_message_tag_relation`;
CREATE TABLE `chat_message_tag_relation`  (
  `relation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系ID',
  `tag_id` bigint(20) NOT NULL COMMENT '标签ID',
  `message_id` bigint(20) NOT NULL COMMENT '消息ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`relation_id`) USING BTREE,
  UNIQUE INDEX `unique_tag_id_message_id`(`tag_id`, `message_id`) USING BTREE,
  INDEX `index_tag_id`(`tag_id`) USING BTREE,
  INDEX `index_message_id`(`message_id`) USING BTREE,
  CONSTRAINT `fk_chat_message_tag_relation_tag_id` FOREIGN KEY (`tag_id`) REFERENCES `chat_message_tag` (`tag_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_chat_message_tag_relation_message_id` FOREIGN KEY (`message_id`) REFERENCES `chat_message` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '聊天消息标签关系表' ROW_FORMAT = DYNAMIC;