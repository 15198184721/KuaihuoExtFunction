/* 所有的数据库依赖于：mysql数据库。阿里云第三方数据库服务 */

/* 用户信息表 */
CREATE TABLE `user` (
	`user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
	`name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户名称',
	`age` tinyint(3) NULL COMMENT '用户年龄',
	`gender` tinyint(1) NULL DEFAULT 0 COMMENT '用户性别(0:女，1:男)',
	`email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '邮箱',
	`tel` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '电话',
	`create_time` timestamp NULL COMMENT '用户创建时间',
	`update_time` timestamp NULL COMMENT '用户更新时间(最后一次更新时间)',
	`del` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除,逻辑删除(0:未删除，1:已删除)',
	PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci COMMENT='用户信息表'
ROW_FORMAT=DYNAMIC AVG_ROW_LENGTH=0;




