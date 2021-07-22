/* 所有的数据库依赖于：mysql数据库。阿里云第三方数据库服务 */

/* 用户信息相关的统计表 */
CREATE TABLE `app_count_user` (
	`user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
	`name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户名称',
	`gender` tinyint(1) NULL DEFAULT 0 COMMENT '用户性别(0:女，1:男)',
	`create_time` timestamp NULL COMMENT '用户创建时间',
	`update_time` timestamp NULL COMMENT '用户更新时间(最后一次更新时间)',
	`login_time` timestamp NULL COMMENT '最新的登录时间(最后一次登录时间)',
	`login_count` int NULL  DEFAULT 1 COMMENT '登录的次数,历史登录次数',
	`del` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除,逻辑删除(0:未删除，1:已删除)',
	PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci COMMENT='用户信息表'
ROW_FORMAT=DYNAMIC AVG_ROW_LENGTH=0;

/* 记录App的跳转路径的统计,记录app的跳转和被跳转次数记录 */
CREATE TABLE `app_count_activity_jump` (
	`this_activity` varchar(80) NOT NULL COMMENT '当前的页面,自身当前所在的页面(采用全包路径+类名作为内容)',
	`to_activity` varchar(80) NOT NULL COMMENT '去往的的页面,要跳转的的目标页面(采用全包路径+类名作为内容))',
	`total_count` int(11) NOT NULL DEFAULT 1 COMMENT '总的跳转次数,就是记录此路径跳转的总次数',
	PRIMARY KEY (`this_activity`,`to_activity`)
) ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci COMMENT='app的页面页面路径统计,统计app的跳转路径以及流程，目的为了实现记录用户使用的跳转流程'
ROW_FORMAT=DYNAMIC
AVG_ROW_LENGTH=0;


/* 记录App的所有Activity的字段表。Activity的字典表 */
CREATE TABLE `app_dictionary_activitys` (
	`id` int NOT NULL AUTO_INCREMENT COMMENT '这个activity在字典对应的id，此id表示指定页面',
	`name` varchar(80) CHARACTER SET utf8 NOT NULL COMMENT 'activity的页面名称(包名+类名)',
	`desc` varchar(200) NULL COMMENT '对这个页面的描述',
	PRIMARY KEY (`id`),
	Unique KEY `unique_index`(`name`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COMMENT='app字典表,记录所有activity的名称作为字典,提供给[app_count_activity_jump]等表使用';

/* 请确认以下SQL符合您的变更需求，务必确认无误后再提交执行 */

CREATE TABLE `app_dictionary_model_tag` (
	`id` int NOT NULL AUTO_INCREMENT COMMENT '唯一id',
	`name` varchar(20) NOT NULL COMMENT 'tag的名称,标志某个统计类型名称',
	`desc` varchar(100) NULL COMMENT '描述信息',
	PRIMARY KEY (`id`),
	Unique KEY `唯一索引`(`name`)
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8
COMMENT='app字典表,记录当前模块所有统计分类标志。具有哪些统计分类的字典';
