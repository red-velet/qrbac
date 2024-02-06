/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : 127.0.0.1:3306
 Source Schema         : qrbac

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 06/02/2024 18:11:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `p_id` int NULL DEFAULT 0,
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `href` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_menu` tinyint NULL DEFAULT NULL,
  `target` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `state` tinyint NULL DEFAULT 0,
  `is_deleted` tinyint NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, 0, NULL, NULL, 'fa fa-align-justify', '业务管理', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (2, 1, 'system:main', 'page/welcome.html', 'fa fa-home', '主页', 0, '_self', 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (3, 1, '', '', 'fa fa-user', '用户管理', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (4, 3, '', 'page/user-table.html', 'fa fa-user-o', '用户', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (32, 0, '', '', 'fa fa-gears', '系统设置', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (61, 32, '', '', 'fa fa-gears', '系统管理', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (62, 61, 'system:get', 'page/setting.html', 'fa fa-gear', '系统设置', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (69, 62, 'system:update', '', 'fa fa-gear', '修改系统设置', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (72, 62, 'system:clearCache', '', 'fa fa-trash', '清理缓存', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (73, 32, '', '', 'fa fa-shield', '权限管理', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (74, 73, 'permission:list', 'page/permission.html', 'fa fa-shield', '权限', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (75, 74, 'permission:add', '', 'fa fa-save', '添加权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (76, 74, 'permission:update', '', 'fa fa-gear', '修改权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (77, 74, 'permission:delete', '', 'fa fa-remove', '删除权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (78, 74, 'permission:treeSelect', '', 'fa fa-list', '树形下拉选择框', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (79, 74, 'permission:treeList', '', 'fa fa-tree', '树形', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (80, 73, 'role:list', 'page/role.html', 'fa fa-user-circle', '角色管理', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (81, 80, 'role:add', '', 'fa fa-save', '添加角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (82, 80, 'role:update', '', 'fa fa-gear', '修改角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (84, 80, 'role:delete', '', 'fa fa-remove', '删除角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (85, 80, 'role:authority', '', 'fa fa-user-secret', '分配权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (86, 80, 'role:getPermission', '', 'fa fa-info', '获取角色权限', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (87, 89, 'role:getRole', '', 'fa fa-info', '获取用户角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (88, 89, 'role:initRole', '', 'fa fa-user-o', '获取角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (89, 73, 'user:list', 'page/user-role.html', 'fa fa-user-o', '分配角色', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (90, 4, 'user:add', '', 'fa fa-save', '添加用户', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (91, 89, 'user:assignRole', '', 'fa fa-info', '分配用户角色', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (92, 4, 'user:update', '', 'fa fa-gear', '编辑用户', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (93, 4, 'user:delete', '', 'fa fa-remove', '删除用户', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (104, 2, 'system:echarts', '', 'fa fa-info', 'echarts图', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (107, 4, 'user:list', '', 'fa fa-list', '用户列表', 1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (123, 0, '', '', 'fa fa-address-card', '流程管理', -1, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (124, 123, 'liuc:pijia', '', 'fa fa-address-book', '批假流程', 0, NULL, 0, 0, NULL, NULL);
INSERT INTO `permission` VALUES (125, 123, '', '', 'fa ', '测试流程', 0, NULL, 0, 0, NULL, NULL);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_deleted` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (6, '超级管理员', '老大~', NULL, NULL, 0);
INSERT INTO `role` VALUES (9, '工具人', 'QAQ', NULL, NULL, 0);
INSERT INTO `role` VALUES (10, '二次元', '3333', NULL, NULL, 0);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `p_id` int NULL DEFAULT NULL,
  `r_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1327 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1198, 1, 6);
INSERT INTO `role_permission` VALUES (1199, 2, 6);
INSERT INTO `role_permission` VALUES (1200, 104, 6);
INSERT INTO `role_permission` VALUES (1201, 118, 6);
INSERT INTO `role_permission` VALUES (1202, 120, 6);
INSERT INTO `role_permission` VALUES (1203, 3, 6);
INSERT INTO `role_permission` VALUES (1204, 4, 6);
INSERT INTO `role_permission` VALUES (1205, 90, 6);
INSERT INTO `role_permission` VALUES (1206, 92, 6);
INSERT INTO `role_permission` VALUES (1207, 93, 6);
INSERT INTO `role_permission` VALUES (1208, 107, 6);
INSERT INTO `role_permission` VALUES (1209, 32, 6);
INSERT INTO `role_permission` VALUES (1210, 61, 6);
INSERT INTO `role_permission` VALUES (1211, 62, 6);
INSERT INTO `role_permission` VALUES (1212, 69, 6);
INSERT INTO `role_permission` VALUES (1213, 72, 6);
INSERT INTO `role_permission` VALUES (1214, 73, 6);
INSERT INTO `role_permission` VALUES (1215, 74, 6);
INSERT INTO `role_permission` VALUES (1216, 75, 6);
INSERT INTO `role_permission` VALUES (1217, 76, 6);
INSERT INTO `role_permission` VALUES (1218, 77, 6);
INSERT INTO `role_permission` VALUES (1219, 78, 6);
INSERT INTO `role_permission` VALUES (1220, 79, 6);
INSERT INTO `role_permission` VALUES (1221, 80, 6);
INSERT INTO `role_permission` VALUES (1222, 81, 6);
INSERT INTO `role_permission` VALUES (1223, 82, 6);
INSERT INTO `role_permission` VALUES (1224, 84, 6);
INSERT INTO `role_permission` VALUES (1225, 85, 6);
INSERT INTO `role_permission` VALUES (1226, 86, 6);
INSERT INTO `role_permission` VALUES (1227, 89, 6);
INSERT INTO `role_permission` VALUES (1228, 87, 6);
INSERT INTO `role_permission` VALUES (1229, 88, 6);
INSERT INTO `role_permission` VALUES (1230, 91, 6);
INSERT INTO `role_permission` VALUES (1244, 1, 9);
INSERT INTO `role_permission` VALUES (1245, 3, 9);
INSERT INTO `role_permission` VALUES (1246, 4, 9);
INSERT INTO `role_permission` VALUES (1247, 90, 9);
INSERT INTO `role_permission` VALUES (1248, 107, 9);
INSERT INTO `role_permission` VALUES (1319, 1, 10);
INSERT INTO `role_permission` VALUES (1320, 2, 10);
INSERT INTO `role_permission` VALUES (1321, 104, 10);
INSERT INTO `role_permission` VALUES (1322, 3, 10);
INSERT INTO `role_permission` VALUES (1323, 4, 10);
INSERT INTO `role_permission` VALUES (1324, 107, 10);
INSERT INTO `role_permission` VALUES (1325, 123, 10);
INSERT INTO `role_permission` VALUES (1326, 124, 10);
INSERT INTO `role_permission` VALUES (1327, 125, 10);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `is_deleted` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'qrbac', 'qrbac', NULL, NULL, 0);
INSERT INTO `user` VALUES (3, 'root', 'root', NULL, NULL, 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `r_id` int NULL DEFAULT NULL,
  `u_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (7, 6, 6);
INSERT INTO `user_role` VALUES (13, 10, 3);
INSERT INTO `user_role` VALUES (14, 6, 1);
INSERT INTO `user_role` VALUES (15, 9, 1);
INSERT INTO `user_role` VALUES (16, 10, 1);

SET FOREIGN_KEY_CHECKS = 1;
