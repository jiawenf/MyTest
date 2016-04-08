CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `base_number` double(13,2) DEFAULT NULL COMMENT '用来换算积分的基数',
  `bank_id` int(11) NOT NULL COMMENT '银行',
  `nick_name` varchar(32) CHARACTER SET latin1 DEFAULT NULL COMMENT '昵称',
  `subsection_id` int(11) DEFAULT NULL COMMENT '地区',
  `num` int(3) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `priority` int(3) NOT NULL DEFAULT '100' COMMENT '排序优先级 1-100  越小越靠前',
  `priority_id` int(11) NOT NULL COMMENT '分页用到 取值=priority*100000+id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `priority_id` (`priority_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
--创建company表(岳晓测试表)
CREATE TABLE `company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '公司编码',
  `code` varchar(6) NOT NULL COMMENT '公司编码',
  `address` varchar(100) CHARACTER SET utf8 DEFAULT NULL COMMENT '公司地址',

  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;