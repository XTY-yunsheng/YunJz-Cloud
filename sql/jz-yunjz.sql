/*
 Navicat Premium Data Transfer

 Source Server         : Mysql80
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3308
 Source Schema         : ry-bill

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 19/10/2022 12:43:39
*/

DROP DATABASE IF EXISTS `jz-yunjz`;

CREATE DATABASE  `jz-yunjz` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

USE `jz-yunjz`;

-- ----------------------------
-- Table structure for acc_bill
-- ----------------------------
DROP TABLE IF EXISTS `acc_bill`;
CREATE TABLE `acc_bill`  (
  `bill_id` bigint(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `order_sn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单号',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单标题',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `book_id` bigint(0) NULL DEFAULT NULL COMMENT '账本id',
  `type_id` bigint(0) NULL DEFAULT NULL COMMENT '类型id',
  `category_id` bigint(0) NULL DEFAULT NULL COMMENT '类别id',
  `money` decimal(18, 2) NULL DEFAULT NULL COMMENT '金额',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除【0：未删，1：已删】',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`bill_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acc_bill
-- ----------------------------
INSERT INTO `acc_bill` VALUES (1, NULL, '阿里云ECS 1年', 1, 1, 1, 3, 35.00, 0, '2022-09-26 20:16:07', '2022-10-16 21:38:29');
INSERT INTO `acc_bill` VALUES (2, '115949384591474688', '阿里云短信服务-三网短信接口100条', 1, 2, 1, 1, 3.59, 0, '2022-09-26 20:22:42', '2022-10-16 20:38:39');
INSERT INTO `acc_bill` VALUES (4, '115949654738206720', '你知不知道', 5, 8, 1, 3, 88.80, 0, '2022-10-16 23:02:32', '2022-10-18 13:50:04');

-- ----------------------------
-- Table structure for acc_book
-- ----------------------------
DROP TABLE IF EXISTS `acc_book`;
CREATE TABLE `acc_book`  (
  `book_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账本名称',
  `user_id` bigint(0) NOT NULL COMMENT '用户id',
  `default_status` tinyint(0) NULL DEFAULT 0 COMMENT '是否为默认账本[0->是；1->不是]',
  `show_status` tinyint(1) NULL DEFAULT 0 COMMENT '启用状态【0：启用，1：禁用】',
  `deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '逻辑删除【0：未删，1：已删】',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`book_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acc_book
-- ----------------------------
INSERT INTO `acc_book` VALUES (1, 'admin的1号账本', 1, 0, 0, 0, '2022-09-20 21:25:30', '2022-10-12 15:33:02');
INSERT INTO `acc_book` VALUES (2, 'admin的2号账本', 1, 1, 0, 0, '2022-09-21 00:00:01', '2022-10-12 15:17:25');
INSERT INTO `acc_book` VALUES (3, 'admin的3号账本', 1, 1, 0, 0, '2022-09-20 21:25:30', '2022-09-22 01:01:13');
INSERT INTO `acc_book` VALUES (4, 'admin的4号账本', 1, 1, 0, 0, '2022-09-20 21:25:30', '2022-09-22 01:10:41');
INSERT INTO `acc_book` VALUES (5, 'admin的5号账本', 1, 1, 0, 0, '2022-09-20 21:25:30', '2022-09-21 21:33:18');
INSERT INTO `acc_book` VALUES (6, '吕玉东的1号账本', 4, 0, 0, 0, '2022-09-20 21:25:30', '2022-10-17 19:10:38');
INSERT INTO `acc_book` VALUES (7, '吕玉东的2号账本', 4, 1, 0, 0, '2022-09-22 01:16:48', '2022-10-17 19:10:32');
INSERT INTO `acc_book` VALUES (8, '西藏之旅', 5, 0, 0, 0, '2022-09-22 13:46:25', '2022-10-17 21:27:45');

-- ----------------------------
-- Table structure for acc_category
-- ----------------------------
DROP TABLE IF EXISTS `acc_category`;
CREATE TABLE `acc_category`  (
  `category_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_id` bigint(0) NULL DEFAULT NULL COMMENT '账单类型id',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单类别名称',
  `show_status` tinyint(1) NULL DEFAULT 0 COMMENT '启用状态【0：启用，1：禁用】',
  `sort` bigint(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acc_category
-- ----------------------------
INSERT INTO `acc_category` VALUES (1, 1, '餐饮', 0, 0);
INSERT INTO `acc_category` VALUES (2, 2, '工资', 0, 0);
INSERT INTO `acc_category` VALUES (3, 1, '购物', 0, 0);
INSERT INTO `acc_category` VALUES (4, 1, '交通', 0, 0);
INSERT INTO `acc_category` VALUES (5, 2, '收租金', 0, 0);
INSERT INTO `acc_category` VALUES (6, 2, '年终奖', 0, 0);
INSERT INTO `acc_category` VALUES (7, 2, '零食', 0, 0);
INSERT INTO `acc_category` VALUES (8, NULL, '红包', 0, 0);

-- ----------------------------
-- Table structure for acc_type
-- ----------------------------
DROP TABLE IF EXISTS `acc_type`;
CREATE TABLE `acc_type`  (
  `type_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单类型名称',
  `show_status` tinyint(1) NULL DEFAULT 0 COMMENT '启用状态【0：启用，1：禁用】',
  `sort` bigint(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acc_type
-- ----------------------------
INSERT INTO `acc_type` VALUES (1, '支出', 0, 0);
INSERT INTO `acc_type` VALUES (2, '收入', 0, 0);

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 103 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for mem_level
-- ----------------------------
DROP TABLE IF EXISTS `mem_level`;
CREATE TABLE `mem_level`  (
  `level_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '等级名称',
  `integration` bigint(0) NULL DEFAULT 0 COMMENT '所需积分值',
  `default_status` tinyint(0) NULL DEFAULT 0 COMMENT '是否为默认等级[0->是；1->不是]',
  `show_status` tinyint(1) NULL DEFAULT 0 COMMENT '启用状态【0：启用，1：禁用】',
  PRIMARY KEY (`level_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mem_level
-- ----------------------------
INSERT INTO `mem_level` VALUES (1, '普通用户', 0, 0, 0);
INSERT INTO `mem_level` VALUES (2, '铜牌会员', 1000, 1, 0);
INSERT INTO `mem_level` VALUES (3, '银牌会员', 2000, 1, 0);
INSERT INTO `mem_level` VALUES (4, '金牌会员', 5000, 1, 0);

-- ----------------------------
-- Table structure for mem_user
-- ----------------------------
DROP TABLE IF EXISTS `mem_user`;
CREATE TABLE `mem_user`  (
  `user_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `openid` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号',
  `nickname` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `level_id` bigint(0) NULL DEFAULT NULL COMMENT '会员等级id',
  `integration` bigint(0) NULL DEFAULT 0 COMMENT '积分',
  `gender` tinyint(0) NULL DEFAULT 0 COMMENT '性别【0：男，1：女】',
  `birth` date NULL DEFAULT NULL COMMENT '生日',
  `sign` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '个性签名',
  `show_status` tinyint(1) NULL DEFAULT 0 COMMENT '启用状态【0：启用，1：禁用】',
  `deleted` tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 0 COMMENT '逻辑删除【0：未删，1：已删】',
  `gmt_create` datetime(0) NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL COMMENT '更新时间',
  `social_uid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '社交用户的唯一id',
  `access_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '访问令牌',
  `expires_in` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '令牌的过期时间',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of mem_user
-- ----------------------------
INSERT INTO `mem_user` VALUES (1, 'admin', '123456', NULL, '', NULL, 4, 10000, 1, '2022-09-18', NULL, 0, 0, '2022-09-18 20:44:03', '2022-10-17 19:13:34', NULL, NULL, NULL);
INSERT INTO `mem_user` VALUES (2, '正在输入中...', NULL, NULL, '', NULL, 1, 0, 0, '2000-02-12', NULL, 0, 0, '2022-09-20 00:43:54', '2022-09-20 00:47:27', NULL, NULL, NULL);
INSERT INTO `mem_user` VALUES (3, '赖子哥', NULL, NULL, '', NULL, 1, 0, 0, '2022-09-20', NULL, 0, 0, '2022-09-20 14:08:57', '2022-09-22 01:10:08', NULL, NULL, NULL);
INSERT INTO `mem_user` VALUES (4, '吕玉东', NULL, NULL, '', NULL, 1, 0, 0, '2022-09-20', NULL, 0, 0, '2022-09-20 14:09:26', '2022-10-17 18:53:40', NULL, NULL, NULL);
INSERT INTO `mem_user` VALUES (5, '王哪跑', NULL, NULL, '', NULL, 1, 0, 0, '2022-09-20', NULL, 0, 0, '2022-09-20 14:09:56', '2022-10-17 18:54:18', NULL, NULL, NULL);
INSERT INTO `mem_user` VALUES (6, '洪俊', NULL, NULL, '', NULL, 1, 0, 0, '2022-09-20', NULL, 0, 0, '2022-09-20 14:20:01', '2022-09-20 14:21:56', NULL, NULL, NULL);
INSERT INTO `mem_user` VALUES (7, '吴奇', NULL, NULL, '', NULL, 1, 0, 0, '2022-09-20', NULL, 0, 0, '2022-09-20 14:29:18', '2022-09-27 11:42:08', NULL, NULL, NULL);
INSERT INTO `mem_user` VALUES (19, 'xty', '123456', NULL, '', NULL, 1, 100, 0, '2022-10-12', NULL, 0, 0, '2022-10-17 20:09:02', '2022-10-17 20:10:29', NULL, NULL, NULL);

-- ----------------------------
-- Table structure for type_category_relation
-- ----------------------------
DROP TABLE IF EXISTS `type_category_relation`;
CREATE TABLE `type_category_relation`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_id` bigint(0) NULL DEFAULT NULL COMMENT '账单类型id',
  `category_id` bigint(0) NULL DEFAULT NULL COMMENT '账单类别id',
  `type_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单类型名称',
  `category_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账单类别名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type_category_relation
-- ----------------------------
INSERT INTO `type_category_relation` VALUES (1, 1, 1, '', '餐饮');
INSERT INTO `type_category_relation` VALUES (2, 2, 2, '', '工资');
INSERT INTO `type_category_relation` VALUES (3, 1, 3, '', '购物');
INSERT INTO `type_category_relation` VALUES (4, 1, 4, NULL, '交通');
INSERT INTO `type_category_relation` VALUES (5, 2, 5, NULL, '收租金');
INSERT INTO `type_category_relation` VALUES (6, 2, 6, NULL, '年终奖');
INSERT INTO `type_category_relation` VALUES (16, 2, 7, NULL, NULL);

-- ----------------------------
-- Table structure for user_book_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_book_relation`;
CREATE TABLE `user_book_relation`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户id',
  `book_id` bigint(0) NULL DEFAULT NULL COMMENT '账本id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `book_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '账本名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_book_relation
-- ----------------------------
INSERT INTO `user_book_relation` VALUES (1, 1, 1, 'admin', 'admin的1号账本');
INSERT INTO `user_book_relation` VALUES (2, 1, 2, 'admin', 'admin的2号账本');
INSERT INTO `user_book_relation` VALUES (3, 1, 3, 'admin', 'admin的3号账本');
INSERT INTO `user_book_relation` VALUES (4, 1, 4, 'admin', 'admin的4号账本');
INSERT INTO `user_book_relation` VALUES (5, 1, 5, 'admin', 'admin的5号账本');
INSERT INTO `user_book_relation` VALUES (6, 4, 6, '吕玉东', '吕玉东的1号账本');
INSERT INTO `user_book_relation` VALUES (12, 4, 7, '吕玉东', '吕玉东的2号账本');
INSERT INTO `user_book_relation` VALUES (13, 5, 8, '王哪跑', '西藏之旅');

SET FOREIGN_KEY_CHECKS = 1;
