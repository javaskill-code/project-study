/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50730
 Source Host           : localhost:3306
 Source Schema         : mbts

 Target Server Type    : MySQL
 Target Server Version : 50730
 File Encoding         : 65001

 Date: 23/10/2020 13:31:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_bank
-- ----------------------------
DROP TABLE IF EXISTS `tb_bank`;
CREATE TABLE `tb_bank`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '持卡人姓名',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行卡预留手机号',
  `cardNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行卡号',
  `idNum` varchar(17) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '银行卡密码',
  `balance` decimal(65, 2) NOT NULL COMMENT '账户余额',
  `issuing_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发卡行',
  `graph_validate_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图形验证码',
  `validate_code` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '短信验证码',
  `salt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `delete_mark` tinyint(2) NULL DEFAULT NULL COMMENT '1 删除 0 未删除',
  `enable_mark` tinyint(2) NULL DEFAULT NULL COMMENT '1无效 0有效',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_bank
-- ----------------------------
INSERT INTO `tb_bank` VALUES (1, '无言灵，长得漂亮', '15101691259', '15101691259', '15101691259', '12345678', 9990000000.00, '中国银行', NULL, NULL, '', '0', '0', '111111', '2020-10-21 10:50:17', NULL, NULL, 0, NULL, NULL, '');

-- ----------------------------
-- Table structure for tb_trading_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_trading_log`;
CREATE TABLE `tb_trading_log`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `delete_mark` tinyint(2) NULL DEFAULT NULL COMMENT '1 删除 0 未删除',
  `enable_mark` tinyint(2) NULL DEFAULT NULL COMMENT '1无效 0有效',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_trading_log
-- ----------------------------
INSERT INTO `tb_trading_log` VALUES (1, '收到15101691259账户张转报文(TransferVo(BeneficiaryName=李振江, BeneficiaryBank=招商, beneficiaryAccountNo=15101691259, transferAmount=10000000, debitCard=15101691259, accountingType=1, signature=1111111111111111))', NULL, '2020-10-22 13:49:31', NULL, NULL, 0, NULL, NULL, '');
INSERT INTO `tb_trading_log` VALUES (2, '收到15101691259账户张转报文(TransferVo(BeneficiaryName=李振江, BeneficiaryBank=招商, beneficiaryAccountNo=15101691259, transferAmount=10000000, debitCard=15101691259, accountingType=1, signature=1111111111111111))', NULL, '2020-10-22 13:50:46', NULL, NULL, 0, NULL, NULL, '');
INSERT INTO `tb_trading_log` VALUES (3, '启动15101691259账户身份验证,发送网络随机数、手机短信验证码；等待客户端提供认证数据', NULL, '2020-10-22 13:50:47', NULL, NULL, 0, NULL, NULL, '');
INSERT INTO `tb_trading_log` VALUES (4, '收到15101691259账户张转报文(TransferVo(BeneficiaryName=李振江, BeneficiaryBank=招商, beneficiaryAccountNo=15101691259, transferAmount=10000000, debitCard=15101691259, accountingType=1, signature=1111111111111111))', NULL, '2020-10-22 14:05:56', NULL, NULL, 0, NULL, NULL, '');
INSERT INTO `tb_trading_log` VALUES (5, '启动15101691259账户身份验证,发送网络随机数、手机短信验证码；等待客户端提供认证数据', NULL, '2020-10-22 14:05:56', NULL, NULL, 0, NULL, NULL, '');
INSERT INTO `tb_trading_log` VALUES (6, '收到15101691259账户张转报文(TransferVo(BeneficiaryName=李振江, BeneficiaryBank=招商, beneficiaryAccountNo=15101691259, transferAmount=10000000, debitCard=15101691259, accountingType=1, signature=1111111111111111))', NULL, '2020-10-22 14:06:11', NULL, NULL, 0, NULL, NULL, '');
INSERT INTO `tb_trading_log` VALUES (7, '启动15101691259账户身份验证,发送网络随机数、手机短信验证码；等待客户端提供认证数据', NULL, '2020-10-22 14:06:12', NULL, NULL, 0, NULL, NULL, '');
INSERT INTO `tb_trading_log` VALUES (8, '接受完整签名并验证后，完成15101691259账户转账操作，记入后台业务系统，并发送电子回单至客户端', NULL, '2020-10-22 14:30:21', NULL, NULL, 0, NULL, NULL, '');

-- ----------------------------
-- Table structure for tb_trading_record
-- ----------------------------
DROP TABLE IF EXISTS `tb_trading_record`;
CREATE TABLE `tb_trading_record`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `from_cardNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转出银行卡',
  `from_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转出银行',
  `to_cardNo` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转入银行卡',
  `to_bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转入银行',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转账类型',
  `balance` decimal(65, 2) NOT NULL COMMENT '转入金额',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建日期',
  `update_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_time` timestamp(0) NULL DEFAULT NULL COMMENT '更新时间',
  `version` int(11) NULL DEFAULT 0 COMMENT '版本号',
  `delete_mark` tinyint(2) NULL DEFAULT NULL COMMENT '1 删除 0 未删除',
  `enable_mark` tinyint(2) NULL DEFAULT NULL COMMENT '1无效 0有效',
  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_trading_record
-- ----------------------------
INSERT INTO `tb_trading_record` VALUES (1, '15101691259', '中国银行', '15101691259', '招商', '1', 10000000.00, '李振江', '2020-10-22 13:50:46', NULL, NULL, 0, NULL, 1, '1111111111111111');
INSERT INTO `tb_trading_record` VALUES (2, '15101691259', '中国银行', '15101691259', '招商', '1', 10000000.00, '李振江', '2020-10-22 14:05:56', NULL, NULL, 0, NULL, 0, '1111111111111111');
INSERT INTO `tb_trading_record` VALUES (3, '15101691259', '中国银行', '15101691259', '招商', '1', 10000000.00, '李振江', '2020-10-22 14:06:11', NULL, NULL, 0, NULL, 0, '1111111111111111');

SET FOREIGN_KEY_CHECKS = 1;
