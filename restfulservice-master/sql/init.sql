CREATE TABLE `t_login` (
	`id` BIGINT (20) NOT NULL auto_increment,
	`customerId` VARCHAR (255) DEFAULT NULL,
	`customerName` VARCHAR (255) DEFAULT NULL,
	`mobilePhone` VARCHAR (255) DEFAULT NULL,
	`email` VARCHAR (255) DEFAULT NULL,
	`passWord` VARCHAR (255) DEFAULT NULL,
	`type` INT (11) DEFAULT 0 COMMENT "0:普通用户;-1:管理员",
	`status` INT (11) DEFAULT 0 COMMENT "0:正常;-1:冻结",
	`createTime` Datetime DEFAULT "2000-01-01 00:00:00",
	`updateTime` Datetime DEFAULT "2000-01-01 00:00:00",
	PRIMARY KEY (`id`),
	KEY `query_index_customerId` (`customerId`),
	KEY `query_index_customerId_createTime` (`customerId`, `createTime`),
	KEY `query_index_login` (
		`customerName`,
		`mobilePhone`,
		`email`,
		`passWord`
	),
	KEY `query_index_console` (
		`customerId`,
		`type`,
		`status`,
		`createTime`
	)
) ENGINE = INNODB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8;

CREATE TABLE `t_login_log` (
	`id` BIGINT (20) NOT NULL auto_increment,
	`customerId` VARCHAR (255) DEFAULT NULL,
	`IPadress` VARCHAR (255) DEFAULT NULL,
	`type` INT (11) DEFAULT 0 COMMENT "0:登录;1:退出登录",
	`createTime` Datetime DEFAULT "2000-01-01 00:00:00",
	PRIMARY KEY (`id`),
	KEY `query_index_customerId` (`customerId`),
	KEY `query_index_customerId_createTime` (`customerId`, `createTime`),
	KEY `query_index_console` (
		`customerId`,
		`type`,
		`createTime`
	)
) ENGINE = INNODB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8;

CREATE TABLE `t_person` (
	`id` BIGINT (20) NOT NULL auto_increment,
	`customerId` VARCHAR (255) DEFAULT NULL,
	`customerName` VARCHAR (255) DEFAULT NULL,
	`email` VARCHAR (255) DEFAULT NULL,
	`mobilePhone` VARCHAR (255) DEFAULT NULL,
	`IdCard` VARCHAR (255) DEFAULT NULL,
	`name` VARCHAR (255) DEFAULT NULL,
	`sex` INT (11) DEFAULT 0 COMMENT "0:男;1:女",
	`birthday` VARCHAR (255) DEFAULT NULL,
	`address` VARCHAR (255) DEFAULT NULL,
	`postcode` VARCHAR (255) DEFAULT NULL,
	`type` INT (11) DEFAULT 0 COMMENT "0:成人;1:学生",
	`createTime` Datetime DEFAULT "2000-01-01 00:00:00",
	`updateTime` Datetime DEFAULT "2000-01-01 00:00:00",
	PRIMARY KEY (`id`),
	KEY `query_index_customerId` (`customerId`)
) ENGINE = INNODB AUTO_INCREMENT = 4 DEFAULT CHARSET = utf8;