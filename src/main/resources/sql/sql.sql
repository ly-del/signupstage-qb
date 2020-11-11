CREATE TABLE `user_customize` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '自定义名称',
  `type` int DEFAULT NULL COMMENT '是否自定义信息',
  `status` int DEFAULT NULL COMMENT '自定义种类状态  1正常 2 删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='考生自定义信息表';

