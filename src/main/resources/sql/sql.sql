CREATE TABLE `user_customize` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint DEFAULT NULL COMMENT '创建账号id',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '自定义名称',
  `type` int DEFAULT NULL COMMENT '是否自定义信息',
  `status` int DEFAULT NULL COMMENT '自定义种类状态  1正常 2 删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='用户自定义信息表';


CREATE TABLE `user_group_bind` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint DEFAULT NULL COMMENT '创建账号id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `group_id` bigint DEFAULT NULL COMMENT '分组id',
  `status` int DEFAULT NULL COMMENT '状态 1正常 2删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT'创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户分组关系表';



CREATE TABLE `sign_info` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint DEFAULT NULL COMMENT '创建账号id',
  `group_id` bigint DEFAULT NULL COMMENT '所在分组',
  `name` varchar(255) DEFAULT NULL COMMENT '报名名称',
  `describe` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '描述',
  `max_total` int DEFAULT NULL COMMENT '最大报名人数上限',
  `cost` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `start_time` datetime DEFAULT NULL COMMENT '报名开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '报名结束时间',
  `release` int DEFAULT NULL COMMENT '发布状态 1已发布 2未发布',
  `status` int DEFAULT NULL COMMENT '状态(预留)',
  `unstarted_dec` varchar(255) DEFAULT NULL COMMENT '未开始提示语',
  `complete_dec` varchar(255) DEFAULT NULL COMMENT '结束提示语',
  `succeed_dec` varchar(255) DEFAULT NULL COMMENT '成功提示语',
  `fail_dec` varchar(255) DEFAULT NULL COMMENT '失败提示语',
  `is_skip` int DEFAULT NULL COMMENT '是否跳转到指定页面1不跳转2手动跳转3自动跳转',
  `is_share` int DEFAULT NULL COMMENT '是否分享',
  `share_dec` varchar(255) DEFAULT NULL COMMENT '分享提示语',
  `is_open` int DEFAULT NULL COMMENT '是否开启滑动验证',
  `url` varchar(255) DEFAULT NULL COMMENT '报名链接',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COMMENT='报名信息表';


CREATE TABLE `sign_info_form` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `account_id` bigint DEFAULT NULL COMMENT '创建账号id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(255) DEFAULT NULL COMMENT '手机',
  `custom_information` varchar(1024) DEFAULT NULL COMMENT '自定义字段',
  `sign_info_id` bigint DEFAULT NULL COMMENT '报名id',
  `status` int DEFAULT NULL COMMENT '状态(预留)',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='报名页面设置表';




