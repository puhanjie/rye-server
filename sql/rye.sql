/*
 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80033 (8.0.33)
 Source Host           : localhost:3306
 Source Schema         : rye

 Target Server Type    : MySQL
 Target Server Version : 80033 (8.0.33)
 File Encoding         : 65001
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`
(
    `id`          int unsigned                                                  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `path`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件地址',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名',
    `file_size`   bigint                                                                 DEFAULT NULL COMMENT '文件大小(Byte)',
    `uuid`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '文件唯一uuid值',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime                                                               DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='文件表';

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`
(
    `id`           int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `code`         int                                                           DEFAULT NULL COMMENT '响应状态码',
    `message`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '响应状态消息',
    `user_id`      int unsigned NOT NULL COMMENT '操作用户ID',
    `username`     varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '操作用户',
    `path`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '接口地址',
    `operate_time` datetime     NOT NULL                                         DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='操作日志表';

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名',
    `info`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '权限信息',
    `menu`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '菜单',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime                                                               DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 23
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (1, 'user:add', '新增', 'user', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (2, 'user:delete', '删除', 'user', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (3, 'user:edit', '编辑', 'user', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (4, 'user:view', '查看', 'user', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (5, 'user:resetPassword', '重置密码', 'user', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (6, 'user:batchDelete', '批量删除', 'user', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (7, 'role:add', '新增', 'role', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (8, 'role:delete', '删除', 'role', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (9, 'role:edit', '编辑', 'role', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (10, 'role:view', '查看', 'role', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (11, 'role:batchDelete', '批量删除', 'role', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (12, 'permission:add', '新增', 'permission', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (13, 'permission:delete', '删除', 'permission', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (14, 'permission:edit', '编辑', 'permission', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (15, 'permission:view', '查看', 'permission', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (16, 'permission:batchDelete', '批量删除', 'permission', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (17, 'dictionary:add', '新增', 'dictionary', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (18, 'dictionary:delete', '删除', 'dictionary', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (19, 'dictionary:edit', '编辑', 'dictionary', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (20, 'dictionary:view', '查看', 'dictionary', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (21, 'dictionary:batchDelete', '批量删除', 'dictionary', NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `create_time`, `update_time`, `delete_time`)
VALUES (22, 'settings:view', '查看', 'settings', NOW(), NOW(), NULL);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          int unsigned                                                  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
    `info`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '角色信息',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime                                                               DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `name`, `info`, `create_time`, `update_time`, `delete_time`)
VALUES (1, 'admin', '管理员', NOW(), NOW(), NULL);
INSERT INTO `role` (`id`, `name`, `info`, `create_time`, `update_time`, `delete_time`)
VALUES (2, 'guest', '访客', NOW(), NOW(), NULL);
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `id`            int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `role_id`       int unsigned NOT NULL COMMENT '角色id',
    `permission_id` int unsigned NOT NULL COMMENT '权限id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`)
VALUES (1, 2, 22);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int unsigned                                                  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `username`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户名(唯一)',
    `nickname`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '昵称',
    `user_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户状态(字典:user_status)',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登陆密码',
    `phone`       varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '用户手机',
    `avatar`      varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '头像url',
    `email`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '邮箱',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime                                                               DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `nickname`, `user_status`, `password`, `phone`, `avatar`, `email`, `create_time`,
                    `update_time`, `delete_time`)
VALUES (1, 'admin', '管理员', '0', 'c3284d0f94606de1fd2af172aba15bf3', '15887280652', NULL, 'hanjie.pu@outlook.com',
        NOW(),
        NOW(), NULL);
INSERT INTO `user` (`id`, `username`, `nickname`, `user_status`, `password`, `phone`, `avatar`, `email`, `create_time`,
                    `update_time`, `delete_time`)
VALUES (2, 'guest', '访客', '0', 'c42d47602bc29f89644841702ca0ebf6', '18650329448', NULL, 'example@fox.com', NOW(),
        NOW(), NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`      int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` int unsigned NOT NULL COMMENT '用户id',
    `role_id` int unsigned NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` (`id`, `user_id`, `role_id`)
VALUES (1, 1, 1);
INSERT INTO `user_role` (`id`, `user_id`, `role_id`)
VALUES (2, 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`
(
    `id`          int unsigned                                                  NOT NULL AUTO_INCREMENT COMMENT 'id',
    `dict_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名',
    `dict_text`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典文本',
    `item_value`  varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '字典值',
    `item_text`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典值文本',
    `description` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '描述',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime                                                               DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='字典表';

-- ----------------------------
-- Records of dictionary
-- ----------------------------
BEGIN;
INSERT INTO `dictionary` (`id`, `dict_name`, `dict_text`, `item_value`, `item_text`, `description`, `create_time`,
                          `update_time`, `delete_time`)
VALUES (1, 'user_status', '用户状态', '0', '正常', '用户账户状态', NOW(),
        NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_name`, `dict_text`, `item_value`, `item_text`, `description`, `create_time`,
                          `update_time`, `delete_time`)
VALUES (2, 'user_status', '用户状态', '1', '冻结', '用户账户状态', NOW(),
        NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_name`, `dict_text`, `item_value`, `item_text`, `description`, `create_time`,
                          `update_time`, `delete_time`)
VALUES (3, 'user_status', '用户状态', '2', '注销', '用户账户状态', NOW(),
        NOW(), NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
