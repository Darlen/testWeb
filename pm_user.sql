/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50150
Source Host           : localhost:3306
Source Database       : testweb

Target Server Type    : MYSQL
Target Server Version : 50150
File Encoding         : 65001

Date: 2014-06-19 22:42:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for pm_user
-- ----------------------------
DROP TABLE IF EXISTS `pm_user`;
CREATE TABLE `pm_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
