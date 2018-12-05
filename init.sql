
CREATE DATABASE `mgr` /*!40100 DEFAULT CHARACTER SET utf8 */;
use mgr;
CREATE TABLE `sysaccount` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `mobile` varchar(15) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `departmentid` varchar(45) DEFAULT NULL,
  `roleid` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;
CREATE TABLE `sysdepartment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
CREATE TABLE `sysright` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `datatype` int(1) DEFAULT NULL,
  `parentid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
CREATE TABLE `sysrole` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
CREATE TABLE `sysroleright` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `roleid` int(11) DEFAULT NULL,
  `rightid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `mgr`.`sysaccount`
(
`username`,
`password`,
`mobile`,
`created`,
`enabled`,
`departmentid`,
`roleid`)
VALUES
('root',
'123456',
'1882225555',
now(),
true,
'',
0);
