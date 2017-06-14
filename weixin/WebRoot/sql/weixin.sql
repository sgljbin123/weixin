/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50630
Source Host           : localhost:3306
Source Database       : weixin

Target Server Type    : MYSQL
Target Server Version : 50630
File Encoding         : 65001

Date: 2017-06-14 19:05:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wx_card
-- ----------------------------
DROP TABLE IF EXISTS `wx_card`;
CREATE TABLE `wx_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) NOT NULL,
  `plateProvince` varchar(3) DEFAULT NULL,
  `plateChar` varchar(1) DEFAULT NULL,
  `plateNumber` varchar(10) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `openid` (`openid`),
  KEY `card_index_1` (`openid`),
  KEY `card_index_2` (`plateProvince`,`plateChar`,`plateNumber`),
  CONSTRAINT `pk_index1` FOREIGN KEY (`openid`) REFERENCES `wx_user` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(100) NOT NULL,
  `username` varchar(10) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  `verificationCode` varchar(6) DEFAULT NULL,
  `createTime` timestamp NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `updateTime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `userType` int(11) DEFAULT NULL,
  `sex` varchar(6) DEFAULT NULL,
  `notify_start` int(11) DEFAULT NULL,
  `notify_stop` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_card` (`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
