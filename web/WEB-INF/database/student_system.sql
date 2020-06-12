/*
 Navicat Premium Data Transfer

 Source Server         : root@localhost
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : student_system

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 13/06/2020 00:31:12
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '管理员ID',
  `admin_psw` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`admin_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', '123456');

-- ----------------------------
-- Table structure for class
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class`  (
  `class_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '班级ID',
  `class_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级名称',
  PRIMARY KEY (`class_id`) USING BTREE,
  UNIQUE INDEX `class_name_unique`(`class_name`) USING BTREE COMMENT '班级名称唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES (1, '17计科3班');
INSERT INTO `class` VALUES (2, '18网络2班');

-- ----------------------------
-- Table structure for result
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`  (
  `result_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '学生成绩单ID',
  `student_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生成绩单所属学生ID',
  `result_average` float NULL DEFAULT NULL COMMENT '考试平均成绩',
  `result_mutual` float NULL DEFAULT NULL COMMENT '同学互评分',
  `result_moral` float NULL DEFAULT NULL COMMENT '品德成绩',
  `result_teacher` float NULL DEFAULT NULL COMMENT '任课教师评分',
  PRIMARY KEY (`result_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of result
-- ----------------------------
INSERT INTO `result` VALUES (1, '1708170340', 90.57, 90.7, 90.6, 90.66);
INSERT INTO `result` VALUES (2, '17081703244', 90.5, 90.7, 90.6, 90.66);
INSERT INTO `result` VALUES (4, '1708170320', 91, 98, 99, 96);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '学生学号',
  `class_id` int(0) NULL DEFAULT NULL COMMENT '学生班级ID',
  `student_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生姓名',
  `student_sex` int(0) NULL DEFAULT 3 COMMENT '学生性别，1：男，2：女，3：其他',
  `student_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生家庭住址',
  `student_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '学生手机号',
  PRIMARY KEY (`student_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1708170320', 1, 'Lyy', 2, 'Quanzhou', '654321');
INSERT INTO `student` VALUES ('17081703244', 1, '嗡嗡嗡', 1, '', '');
INSERT INTO `student` VALUES ('1708170340', 1, 'JVxie', 1, 'Annkway', '111111');

SET FOREIGN_KEY_CHECKS = 1;
