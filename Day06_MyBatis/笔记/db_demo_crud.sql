/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80030
 Source Host           : localhost:3306
 Source Schema         : db_demo_crud

 Target Server Type    : MySQL
 Target Server Version : 80030
 File Encoding         : 65001

 Date: 08/01/2026 23:31:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_customer
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer`  (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `customer_name` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`customer_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES (1, 'c01');

-- ----------------------------
-- Table structure for t_emp
-- ----------------------------
DROP TABLE IF EXISTS `t_emp`;
CREATE TABLE `t_emp`  (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `emp_name` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `emp_salary` double NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_emp
-- ----------------------------
INSERT INTO `t_emp` VALUES (1, '张三', 500);
INSERT INTO `t_emp` VALUES (2, '李四', 800.5);
INSERT INTO `t_emp` VALUES (3, '王五', 200.75);

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `order_name` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `customer_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, 'o1', 1);
INSERT INTO `t_order` VALUES (2, 'o2', 1);
INSERT INTO `t_order` VALUES (3, 'o3', 1);

-- ----------------------------
-- Table structure for t_tiger
-- ----------------------------
DROP TABLE IF EXISTS `t_tiger`;
CREATE TABLE `t_tiger`  (
  `tiger_id` int NOT NULL AUTO_INCREMENT,
  `tiger_name` char(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `tiger_age` int NULL DEFAULT NULL,
  `tiger_salary` double NULL DEFAULT NULL,
  PRIMARY KEY (`tiger_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_tiger
-- ----------------------------
INSERT INTO `t_tiger` VALUES (1, '王虎', 5, 500);
INSERT INTO `t_tiger` VALUES (2, '李虎', 3, 800.5);
INSERT INTO `t_tiger` VALUES (3, '张虎', 7, 200.75);
INSERT INTO `t_tiger` VALUES (4, '赵虎', 4, 200.25);
INSERT INTO `t_tiger` VALUES (5, '刘虎', 6, 900);

SET FOREIGN_KEY_CHECKS = 1;
