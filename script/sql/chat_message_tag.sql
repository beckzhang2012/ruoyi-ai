-- 聊天消息标签表
CREATE TABLE `chat_message_tag` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `tag_name` varchar(50) NOT NULL COMMENT '标签名称',
  `tag_color` varchar(20) DEFAULT '#1890ff' COMMENT '标签颜色',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `sort` int DEFAULT '0' COMMENT '排序',
  `remark` varchar(200) DEFAULT '' COMMENT '备注',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_tag_name` (`user_id`, `tag_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聊天消息标签表';

-- 聊天消息标签关联表
CREATE TABLE `chat_message_tag_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '关联ID',
  `message_id` bigint NOT NULL COMMENT '消息ID',
  `tag_id` bigint NOT NULL COMMENT '标签ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_message_tag` (`message_id`, `tag_id`),
  KEY `idx_tag_id` (`tag_id`),
  KEY `idx_message_id` (`message_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='聊天消息标签关联表';