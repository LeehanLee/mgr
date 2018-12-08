
CREATE DATABASE `mgr` /*!40100 DEFAULT CHARACTER SET utf8 */;
use mgr;
CREATE TABLE `sysaccount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `created` datetime NOT NULL,
  `enabled` bit(1) NOT NULL,
  `orgid` int(11) NOT NULL,
  `roleid` varchar(36) DEFAULT NULL,
  `extraright` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `sysorg` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `created` datetime NOT NULL,
  `enabled` bit(1) NOT NULL,
  `parentid` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `sysright` (
  `id` varchar(45) NOT NULL,
  `name` varchar(10) NOT NULL,
  `created` datetime NOT NULL,
  `datatype` int(1) NOT NULL,
  `parentid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `sysrole` (
  `id` varchar(36) NOT NULL,
  `name` varchar(10) NOT NULL,
  `created` datetime NOT NULL,
  `enabled` bit(1) NOT NULL,
  `rights` text NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('admin','系统管理', now(), 0, null);

INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('account','用户管理',now(), 1, 'admin');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('account/getList','列表',now(), 2, 'account');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('account/insert','添加',now(), 3, 'account');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('account/update','编辑',now(), 4, 'account');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('account/delete','删除',now(), 5, 'account');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('account/enable','启用',now(), 5,'account');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('account/disable','禁用',now(), 5,'account');

INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('org','组织管理',now(), 1, 'admin');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('org/getList','列表',now(), 2, 'org');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('org/insert','添加',now(), 3, 'org');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('org/update','编辑',now(), 4, 'org');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('org/delete','删除',now(), 5, 'org');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('org/enable','启用',now(), 5,'org');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('org/disable','禁用',now(), 5,'org');

INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('role','角色管理',now(), 1, 'admin');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('role/getList','列表',now(), 2, 'role');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('role/insert','添加',now(), 3, 'role');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('role/update','编辑',now(), 4, 'role');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('role/delete','删除',now(), 5, 'role');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('role/enable','启用',now(), 5,'role');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('role/disable','禁用',now(), 5,'role');

INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('right','权限管理',now(), 1, 'admin');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('right/getList','列表',now(), 2, 'right');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('right/insert','添加',now(), 3, 'right');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('right/update','编辑',now(), 4, 'right');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('right/delete','删除',now(), 5, 'right');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('right/enable','启用',now(), 5,'right');
INSERT INTO `mgr`.`sysright`(`id`,`name`,`created`,`datatype`,`parentid`) VALUES('right/disable','禁用',now(), 5,'right');

INSERT INTO `mgr`.`sysorg`(`name`,`created`,`enabled`,`parentid`) VALUES('Content', now(),true,0);
INSERT INTO `mgr`.`sysrole`(`id`,`name`,`created`,`enabled`,`rights`) VALUES('super','超级管理员',now(),true,'');
INSERT INTO `mgr`.`sysaccount`(`username`,`password`,`mobile`,`created`,`enabled`,`orgid`,`roleid`, `extraright`) VALUES('root','123456','1882225555',now(),true,1,'super','');



