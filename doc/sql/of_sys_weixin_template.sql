/*
Navicat MySQL Data Transfer

Source Server         : 106.12.205.33
Source Server Version : 50713
Source Host           : 106.12.205.33:3306
Source Database       : ofcms

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2018-11-17 15:16:38
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for of_sys_weixin_template
-- ----------------------------
DROP TABLE IF EXISTS `of_sys_weixin_template`;
CREATE TABLE `of_sys_weixin_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `template_key` varchar(150) NOT NULL COMMENT '模版ID',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `firstclass` varchar(50) NOT NULL COMMENT '一级行业',
  `secondclass` varchar(50) NOT NULL COMMENT '二级行业',
  `content` varchar(500) NOT NULL COMMENT '详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统微信模板消息推送';

-- ----------------------------
-- Records of of_sys_weixin_template
-- ----------------------------
INSERT INTO `of_sys_weixin_template` VALUES ('1', '3DtNZT3ILnHZxmYoiEHYxze8OMQZ4ZWPfS2jbVpwMhY', '商品已发出通知', 'IT科技', ' 互联网|电子商务', '{{first.DATA联系电话：0991-8777555或直接公众号账号中咨询。}}  快递公司：{{delivername.DATA阿凡提物}} 快递单号：{{ordername.DATA00001}} {{remark.DATA点击进入查看详情}} ');
INSERT INTO `of_sys_weixin_template` VALUES ('2', 'WwHb22Rb7EILTpXpRIrIqlnDwi8udXBEV_9YeXvk6KU', '购买成功通知', 'IT科技', '互联网|电子商务', '您好，您已购买成功。  商品信息：{{name.DATA模版建站}} {{remark.DATA点击进入查看详情}}');
