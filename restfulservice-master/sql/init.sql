CREATE TABLE `t_login` (
  `id` bigint(20) NOT NULL auto_increment,
  `customerId` varchar(255) default NULL,
  `customerName` varchar(255) default NULL,
  `mobilePhone` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `passWord` varchar(255) default NULL,
  `type` int(11) default 0 COMMENT "0:普通用户;-1:管理员",
  `status` int(11) default 0 COMMENT "0:正常;-1:冻结",
  `createTime` Datetime default "2000-01-01 00:00:00",
  `updateTime` Datetime default "2000-01-01 00:00:00",
  PRIMARY KEY  (`id`),
  KEY `query_index_customerId` (`customerId`),
  KEY `query_index_customerId_createTime` (`customerId`,`createTime`),
  KEY `query_index_login` (`customerName`,`mobilePhone`,`email`,`passWord`),
  KEY `query_index_console` (`customerId`,`type`,`status`,`createTime`)

) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `t_login_log` (
  `id` bigint(20) NOT NULL auto_increment,
  `customerId` varchar(255) default NULL,
  `IPadress` varchar(255) default NULL,
  `type` int(11) default 0 COMMENT  "0:登录;1:退出登录",
  `createTime` Datetime default "2000-01-01 00:00:00",
  PRIMARY KEY  (`id`),
  KEY `query_index_customerId` (`customerId`),
  KEY `query_index_customerId_createTime` (`customerId`,`createTime`),
  KEY `query_index_console` (`customerId`,`type`,`createTime`)

) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;