/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : project_appraisal

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2019-08-16 10:59:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for file_info
-- ----------------------------
DROP TABLE IF EXISTS `file_info`;
CREATE TABLE `file_info` (
  `file_info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `file_type` int(2) DEFAULT NULL COMMENT '文件类型 1图片 2文档 3其它',
  `file_name` varchar(255) DEFAULT NULL COMMENT '文件名',
  `file_info_path` varchar(255) DEFAULT NULL COMMENT '文件路径',
  `file_url` varchar(255) DEFAULT NULL COMMENT '文件访问地址',
  PRIMARY KEY (`file_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='文件信息表';

-- ----------------------------
-- Records of file_info
-- ----------------------------
INSERT INTO `file_info` VALUES ('1', '1', '本人学籍.PNG', 'D:/我的资料库/Documents/WeChat Files/wxid_29l4xskq65ws21/FileStorage/File/2019-08/mass-control/src/main/resources/static/file/upload/userinfo/2019-8-15//db777bd9-6d19-4e1c-85ea-98b3ae738480.PNG', 'http://localhost/static/userinfo//db777bd9-6d19-4e1c-85ea-98b3ae738480.PNG');
INSERT INTO `file_info` VALUES ('2', '1', '本人学籍.PNG', 'D:/我的资料库/Documents/WeChat Files/wxid_29l4xskq65ws21/FileStorage/File/2019-08/mass-control/src/main/resources/static/file/upload/userinfo/2019-8-15//49882df4-39bb-4573-a062-ed82fb14d690.PNG', 'D:/我的资料库/Documents/WeChat Files/wxid_29l4xskq65ws21/FileStorage/File/2019-08/mass-control/src/main/resources/static/file/upload/userinfo//49882df4-39bb-4573-a062-ed82fb14d690.PNG');
INSERT INTO `file_info` VALUES ('3', '1', '本人学籍.PNG', 'D:/我的资料库/Documents/WeChat Files/wxid_29l4xskq65ws21/FileStorage/File/2019-08/mass-control/src/main/resources/static/file/upload/userinfo/2019-8-15//933e5b31-726e-41ce-8943-a1237d36ad97.PNG', 'http://localhost/static/file/upload/userinfo//933e5b31-726e-41ce-8943-a1237d36ad97.PNG');
INSERT INTO `file_info` VALUES ('4', '1', '本人学籍.PNG', 'D:/我的资料库/Documents/WeChat Files/wxid_29l4xskq65ws21/FileStorage/File/2019-08/mass-control/src/main/resources/static/file/upload/userinfo/2019-8-15//0a43b8df-82b4-4d32-a814-51b6e6110d38.PNG', 'http://localhost/static/file/upload/userinfo//2019-8-15//0a43b8df-82b4-4d32-a814-51b6e6110d38.PNG');
INSERT INTO `file_info` VALUES ('5', '1', '本人学籍.PNG', 'D:/我的资料库/Documents/WeChat Files/wxid_29l4xskq65ws21/FileStorage/File/2019-08/mass-control/src/main/resources/static/file/upload/userinfo/2019-8-15//9f17f26f-b400-4ae3-acd6-a5681d07cf8e.PNG', 'http://localhost/static/file/upload/userinfo//2019-8-15//9f17f26f-b400-4ae3-acd6-a5681d07cf8e.PNG');
INSERT INTO `file_info` VALUES ('6', '1', '本人学籍.PNG', 'D:/我的资料库/Documents/WeChat Files/wxid_29l4xskq65ws21/FileStorage/File/2019-08/mass-control/src/main/resources/static/file/upload/userinfo/2019-8-15//df7a9f4b-65eb-4224-89fc-a86495482800.PNG', 'http://localhost/static/file/upload/userinfo//2019-8-15//df7a9f4b-65eb-4224-89fc-a86495482800.PNG');
INSERT INTO `file_info` VALUES ('7', '1', '本人学籍.PNG', 'D:/我的资料库/Documents/WeChat Files/wxid_29l4xskq65ws21/FileStorage/File/2019-08/mass-control/src/main/resources/static/file/upload/userinfo/2019-8-15//1015cb91-b76e-4f28-af40-94ffc9c1d230.PNG', 'http://localhost/static/file/upload/userinfo//2019-8-15//1015cb91-b76e-4f28-af40-94ffc9c1d230.PNG');
INSERT INTO `file_info` VALUES ('8', '1', 'axurerp8_bdald.exe', 'D:/我的资料库/Documents/WeChat Files/wxid_29l4xskq65ws21/FileStorage/File/2019-08/mass-control/src/main/resources/static/file/upload/userinfo/2019-8-15//aac9ba94-f59f-449b-9ab3-2ae74f7e9953.exe', 'http://localhost/static/file/upload/userinfo//2019-8-15//aac9ba94-f59f-449b-9ab3-2ae74f7e9953.exe');

-- ----------------------------
-- Table structure for organization_info
-- ----------------------------
DROP TABLE IF EXISTS `organization_info`;
CREATE TABLE `organization_info` (
  `organization_info_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `organization_name` varchar(255) DEFAULT NULL COMMENT '单位名称，组织名称',
  `organization_code` varchar(255) DEFAULT NULL COMMENT '组织社会信用码',
  `organization_area` varchar(255) DEFAULT NULL COMMENT '地区',
  `organization_site` varchar(255) DEFAULT NULL COMMENT '地址',
  `organization_postcode` varchar(255) DEFAULT NULL COMMENT '邮编',
  `organization_telephone` varchar(255) DEFAULT NULL COMMENT '单位电话',
  `organization_mail` varchar(255) DEFAULT NULL COMMENT '单位邮箱',
  `organization_principal_name` varchar(255) DEFAULT NULL COMMENT '法定代表人名字',
  `organization_principal_mobilephone` varchar(255) DEFAULT NULL COMMENT '法定代表人移动电话',
  `organization_performance_content` text COMMENT '组织业绩文本内容',
  `organization_license_file_info_id` int(11) DEFAULT NULL COMMENT '营业执照文件id',
  `organization_adjunct_file_info_id` int(11) DEFAULT NULL COMMENT '附件文件id',
  PRIMARY KEY (`organization_info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='第三方机构信息';

-- ----------------------------
-- Records of organization_info
-- ----------------------------
INSERT INTO `organization_info` VALUES ('3', null, null, null, null, null, '11111111', null, null, null, null, '5', null);
INSERT INTO `organization_info` VALUES ('4', null, null, null, null, null, '11111111', null, null, null, null, '5', null);
INSERT INTO `organization_info` VALUES ('5', null, null, null, null, null, '11111111', null, null, null, null, '5', null);

-- ----------------------------
-- Table structure for organization_info_careerman
-- ----------------------------
DROP TABLE IF EXISTS `organization_info_careerman`;
CREATE TABLE `organization_info_careerman` (
  `organization_info_careerman_id` int(11) NOT NULL AUTO_INCREMENT,
  `organization_info_id` int(11) DEFAULT NULL COMMENT '所属机构信息id',
  `organization_careerman_name` varchar(255) DEFAULT NULL COMMENT '人员姓名',
  `organization_profession` int(8) DEFAULT NULL COMMENT '人员专业 1采矿工程 2通风安全 3供电 4四大件 5水文地质 6总平面工程 7造价 8环保节能 9土建工程',
  `organization_performance` varchar(255) DEFAULT NULL COMMENT '人员业绩',
  PRIMARY KEY (`organization_info_careerman_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='机构信息，专业人员';

-- ----------------------------
-- Records of organization_info_careerman
-- ----------------------------
INSERT INTO `organization_info_careerman` VALUES ('1', '3', 'w', '0', null);
INSERT INTO `organization_info_careerman` VALUES ('2', '3', 's', '0', null);
INSERT INTO `organization_info_careerman` VALUES ('3', '4', 'w', '0', null);
INSERT INTO `organization_info_careerman` VALUES ('4', '4', 's', '0', null);
INSERT INTO `organization_info_careerman` VALUES ('5', '5', 'w', '0', null);
INSERT INTO `organization_info_careerman` VALUES ('6', '5', 's', '0', null);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aaaa` int(11) DEFAULT NULL,
  `bsss` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('1', '1', '1');
INSERT INTO `test` VALUES ('2', null, '2');
INSERT INTO `test` VALUES ('3', '2', '2');
INSERT INTO `test` VALUES ('4', '3', '3');
INSERT INTO `test` VALUES ('5', '4', '4');
INSERT INTO `test` VALUES ('6', '5', '5');
INSERT INTO `test` VALUES ('7', '6', '6');
INSERT INTO `test` VALUES ('8', '7', '7');
INSERT INTO `test` VALUES ('9', '8', '8');
INSERT INTO `test` VALUES ('10', '9', '9');
INSERT INTO `test` VALUES ('11', '10', '10');
INSERT INTO `test` VALUES ('12', '11', '11');
INSERT INTO `test` VALUES ('13', '12', '12');
INSERT INTO `test` VALUES ('14', '13', '13');
INSERT INTO `test` VALUES ('15', '14', '14');
INSERT INTO `test` VALUES ('16', '15', '15');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '数据主键',
  `user_mobile_phone` varchar(255) DEFAULT NULL COMMENT '用户移动电话',
  `user_register_time` bigint(14) DEFAULT NULL COMMENT '注册时间',
  `user_password` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `user_role` int(2) DEFAULT NULL COMMENT '用户角色： 1专家 2第三方机构 3煤监局 4能源局',
  `info_id` int(11) DEFAULT NULL COMMENT '用户信息id',
  `user_state` int(3) DEFAULT '1' COMMENT '用户状态',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='用户信息';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('3', '13333333333', '1565768333200', 'd3cd8c37e8e77066796f5e845b8125c5', '3', null, '1');
INSERT INTO `user` VALUES ('4', '18482142486', '1565769497175', '34aa438d95464c532b5521c242d096bb', '0', null, '1');
INSERT INTO `user` VALUES ('6', '13333233', '1565771613511', 'd3cd8c37e8e77066796f5e845b8125c5', '3', null, '1');
INSERT INTO `user` VALUES ('7', '13368604444', '1565834355989', '34aa438d95464c532b5521c242d096bb', '0', null, '1');
INSERT INTO `user` VALUES ('8', '13368604442', '1565835475046', '34aa438d95464c532b5521c242d096bb', '1', null, '1');
INSERT INTO `user` VALUES ('9', '13368601111', '1565835929750', '34aa438d95464c532b5521c242d096bb', '1', null, '1');

-- ----------------------------
-- Table structure for user_info_audit
-- ----------------------------
DROP TABLE IF EXISTS `user_info_audit`;
CREATE TABLE `user_info_audit` (
  `user_info_audit_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_info_audit_state` int(2) DEFAULT NULL COMMENT '审核状态 1未审核 2拒绝 3 通过',
  `user_info_audit_describe` varchar(255) DEFAULT NULL COMMENT '审核描述',
  `user_info_audit_addtime` bigint(14) DEFAULT NULL COMMENT '审核添加时间 申请时间',
  `user_info_audit_updatetime` bigint(14) DEFAULT NULL COMMENT '审核修改时间 、审核时间',
  `user_info_audit_type` int(3) DEFAULT NULL COMMENT '审核类型 1初审 2变更审核',
  `user_info_audit_content` int(3) DEFAULT NULL COMMENT '审核类容 1第三方机构信息 2专家信息',
  `info_id` int(11) DEFAULT NULL COMMENT '审核的信息id',
  `user_id_add` int(11) DEFAULT NULL COMMENT '添加入id，申请人',
  `user_id_edit` int(11) DEFAULT NULL COMMENT '修改人id，审核人',
  PRIMARY KEY (`user_info_audit_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户信息审核表';

-- ----------------------------
-- Records of user_info_audit
-- ----------------------------
INSERT INTO `user_info_audit` VALUES ('1', null, null, '1565875372909', null, '1', '1', '3', '6', null);
INSERT INTO `user_info_audit` VALUES ('2', '1', null, '1565875800189', null, '1', '1', '4', '6', null);
INSERT INTO `user_info_audit` VALUES ('3', '1', null, '1565881726627', null, '1', '1', '5', '6', null);
