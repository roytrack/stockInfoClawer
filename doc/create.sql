/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2016-08-26 18:55:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for quotation
-- ----------------------------
DROP TABLE IF EXISTS `quotation`;
CREATE TABLE `quotation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `trans_date` datetime NOT NULL,
  `symbol` varchar(20) NOT NULL,
  `code` varchar(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  `trade` decimal(16,2) NOT NULL,
  `pricechange` decimal(16,2) DEFAULT NULL,
  `changepercent` decimal(16,2) DEFAULT NULL,
  `buy` decimal(16,2) DEFAULT NULL,
  `sell` decimal(16,2) DEFAULT NULL,
  `settlement` decimal(16,2) DEFAULT NULL,
  `open` decimal(16,2) DEFAULT NULL,
  `high` decimal(16,2) DEFAULT NULL,
  `low` decimal(16,2) DEFAULT NULL,
  `volume` decimal(16,2) DEFAULT NULL,
  `amount` decimal(16,2) DEFAULT NULL,
  `ticktime` datetime DEFAULT NULL,
  `per` decimal(16,2) DEFAULT NULL,
  `per_d` decimal(16,2) DEFAULT NULL,
  `nta` decimal(16,2) DEFAULT NULL,
  `pb` decimal(16,2) DEFAULT NULL,
  `mktcap` decimal(16,2) DEFAULT NULL,
  `nmc` decimal(16,2) DEFAULT NULL,
  `turnoverratio` decimal(16,2) DEFAULT NULL,
  `favor` varchar(50) DEFAULT NULL,
  `guba` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `q_code` (`code`),
  KEY `q_open` (`open`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of quotation
-- ----------------------------
