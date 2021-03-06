/* 所有的数据库依赖于：mysql数据库。阿里云第三方数据库服务 */

/*
    注意：表明末尾以"_debug"结尾的表示此表分为debug和relese版本。也就是说存在两个：_debug不表示debug版本的表，没有此结尾的表示线上的表
    表名称：
    通用：
        app_dictionary_activitys //字典表。只是几率每个activity的消息描述
        app_count_user  //统计用户登录次数
        app_count_activity_jump //统计页面的跳转路径情况
        app_count_activity_user_stay //统计用户的在页面上的停留时间情况
    relese环境的：
        app_general_config //通用用户配置
    relese环境的(表功能和relese环境一致)：
        app_general_config_debug
*/

/* 用户信息相关的统计表 */
CREATE TABLE `app_count_user` (
	`user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
	`name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '用户名称',
	`gender` tinyint(1) NULL DEFAULT 0 COMMENT '用户性别(0:女，1:男)',
	`login_addrss` varchar(20) CHARACTER SET utf8  NULL COMMENT '用户最近登录地点',
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

/* 记录App的用户在每个页面的停留时间 */
CREATE TABLE `app_count_activity_user_stay` (
	`user_id` varchar(10) NOT NULL COMMENT '用户的id',
	`stay_activity` varchar(100) NOT NULL COMMENT '停留的页面',
	`stay_time` int(8) NOT NULL DEFAULT 0 COMMENT '总共停留的时长,做累加统计(单位:秒)',
	`update_time` timestamp NULL COMMENT '最近更新的时间(也可以看做最近访问此页面的时间)'
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COMMENT='界面停留统计,统计界面用户停留情况。就是用户在某个界面停留时长。达到效果：能够反馈用户的停留界面时长信息';

/* 记录App的通用全局的配置。和具体用户无关的配置 */
CREATE TABLE `app_general_config` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
	`type` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配置类型',
	`value` tinyint(3) NULL COMMENT '配置的值，null:未配置',
	`valid_period` timestamp NULL COMMENT '配置生效结束日期(生效截止日期,包括当前日期),null表示长期有效',
	`effective_area` varchar(20) NULL COMMENT '生效区域(省级)，null表示全国',
	`msg` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '配置描述,值说明',
	PRIMARY KEY (`id`),
	Unique KEY `un_type`(`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARACTER SET=utf8 COMMENT='通用配置，记录应用的通用配置信息，和用户无关。正对所有应用有效的通用配置';
