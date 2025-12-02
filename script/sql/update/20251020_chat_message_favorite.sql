-- 消息收藏表
CREATE TABLE `chat_message_favorite` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `message_id` bigint(20) NOT NULL COMMENT '消息id',
  `session_id` bigint(20) NOT NULL COMMENT '会话id',
  `remark` varchar(200) DEFAULT NULL COMMENT '收藏备注',
  `message_content` text COMMENT '消息内容',
  `model_name` varchar(100) DEFAULT NULL COMMENT '模型名称',
  `session_title` varchar(200) DEFAULT NULL COMMENT '会话标题',
  `role` varchar(50) DEFAULT NULL COMMENT '对话角色',
  `favorite_time` datetime DEFAULT NULL COMMENT '收藏时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_message_id` (`message_id`),
  KEY `idx_session_id` (`session_id`),
  KEY `idx_model_name` (`model_name`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息收藏表';