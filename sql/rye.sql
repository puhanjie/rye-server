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
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '部门id',
    `parent_id`   int unsigned NOT NULL COMMENT '上级部门id',
    `code`        varchar(50)  NOT NULL COMMENT '部门编码',
    `name`        varchar(100) NOT NULL COMMENT '部门名称',
    `leader`      int unsigned NOT NULL COMMENT '负责人(用户id)',
    `dept_status` varchar(10)  NOT NULL COMMENT '部门状态(字典:dept_status)',
    `create_user` int unsigned NOT NULL COMMENT '创建者(用户id)',
    `update_user` int unsigned NOT NULL COMMENT '更新者(用户id)',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime              DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `code` (`code`, `delete_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
BEGIN;
INSERT INTO `department` (`id`, `parent_id`, `code`, `name`, `leader`, `dept_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (1, 0, '100', 'Rye集团', 1, '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `department` (`id`, `parent_id`, `code`, `name`, `leader`, `dept_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (2, 1, '101', '研发部', 1, '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `department` (`id`, `parent_id`, `code`, `name`, `leader`, `dept_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (3, 1, '102', '财务部', 2, '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `department` (`id`, `parent_id`, `code`, `name`, `leader`, `dept_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (4, 1, '103', '市场部', 2, '0', 1, 1,
        NOW(), NOW(), NULL);
COMMIT;

-- ----------------------------
-- Table structure for department_role
-- ----------------------------
DROP TABLE IF EXISTS `department_role`;
CREATE TABLE `department_role`
(
    `id`            int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `department_id` int unsigned NOT NULL COMMENT '部门id',
    `role_id`       int unsigned NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='部门与角色关联表';

-- ----------------------------
-- Records of department_role
-- ----------------------------
BEGIN;
INSERT INTO `department_role` (`id`, `department_id`, `role_id`)
VALUES (1, 1, 1);
INSERT INTO `department_role` (`id`, `department_id`, `role_id`)
VALUES (2, 3, 2);
COMMIT;

-- ----------------------------
-- Table structure for dictionary
-- ----------------------------
DROP TABLE IF EXISTS `dictionary`;
CREATE TABLE `dictionary`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '字典id',
    `dict_type`   varchar(100) NOT NULL COMMENT '字典类型',
    `dict_name`   varchar(100) NOT NULL COMMENT '字典名称',
    `dict_value`  varchar(10)  NOT NULL COMMENT '字典键值',
    `dict_label`  varchar(50)  NOT NULL COMMENT '字典标签',
    `description` varchar(100) COMMENT '描述',
    `create_user` int unsigned NOT NULL COMMENT '创建者(用户id)',
    `update_user` int unsigned NOT NULL COMMENT '更新者(用户id)',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime              DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='字典表';

-- ----------------------------
-- Records of dictionary
-- ----------------------------
BEGIN;
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (1, 'user_status', '用户状态', '0', '正常', '用户账户状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (2, 'user_status', '用户状态', '1', '冻结', '用户账户状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (3, 'user_status', '用户状态', '2', '注销', '用户账户状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (4, 'sex', '性别', '1', '男', '性别', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (5, 'sex', '性别', '2', '女', '性别', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (6, 'sex', '性别', '3', '未知', '性别', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (7, 'role_status', '角色状态', '0', '正常', '角色状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (8, 'role_status', '角色状态', '1', '停用', '角色状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (9, 'permission_status', '权限状态', '0', '正常', '权限状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (10, 'permission_status', '权限状态', '1', '停用', '权限状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (11, 'dept_status', '部门状态', '0', '正常', '部门状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (12, 'dept_status', '部门状态', '1', '停用', '部门状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (13, 'post_status', '岗位状态', '0', '正常', '岗位状态', 1,
        1, NOW(), NOW(), NULL);
INSERT INTO `dictionary` (`id`, `dict_type`, `dict_name`, `dict_value`, `dict_label`, `description`, `create_user`,
                          `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (14, 'post_status', '岗位状态', '1', '停用', '岗位状态', 1,
        1, NOW(), NOW(), NULL);
COMMIT;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '文件id',
    `path`        varchar(200) NOT NULL COMMENT '文件地址',
    `name`        varchar(100) NOT NULL COMMENT '文件名',
    `file_size`   bigint       NOT NULL COMMENT '文件大小(Byte)',
    `uuid`        varchar(50)  NOT NULL COMMENT 'uuid',
    `upload_user` int unsigned NOT NULL COMMENT '上传者(用户id)',
    `delete_user` int unsigned          DEFAULT NULL COMMENT '删除者(用户id)',
    `upload_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
    `delete_time` datetime              DEFAULT NULL COMMENT '删除时间',
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
    `id`           int unsigned NOT NULL AUTO_INCREMENT COMMENT '日志id',
    `url`          varchar(100) NOT NULL COMMENT '请求url',
    `code`         int          NOT NULL COMMENT '响应状态码',
    `message`      varchar(50)  NOT NULL COMMENT '响应消息',
    `operate_user` int unsigned NOT NULL COMMENT '操作人(用户id)',
    `operate_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
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
    `id`                int unsigned NOT NULL AUTO_INCREMENT COMMENT '权限id',
    `code`              varchar(100) NOT NULL COMMENT '权限编码',
    `name`              varchar(100)          DEFAULT NULL COMMENT '权限名称',
    `menu`              varchar(50)  NOT NULL COMMENT '菜单',
    `permission_status` varchar(10)  NOT NULL COMMENT '权限状态(字典:permission_status)',
    `create_user`       int unsigned NOT NULL COMMENT '创建者(用户id)',
    `update_user`       int unsigned NOT NULL COMMENT '更新者(用户id)',
    `create_time`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`       datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time`       datetime              DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `code` (`code`, `delete_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 34
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (1, 'user:add', '新增', 'user', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (2, 'user:delete', '删除', 'user', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (3, 'user:edit', '编辑', 'user', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (4, 'user:view', '查看', 'user', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (5, 'user:resetPassword', '重置密码', 'user', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (6, 'role:add', '新增', 'role', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (7, 'role:delete', '删除', 'role', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (8, 'role:edit', '编辑', 'role', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (9, 'role:view', '查看', 'role', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (10, 'permission:add', '新增', 'permission', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (11, 'permission:delete', '删除', 'permission', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (12, 'permission:edit', '编辑', 'permission', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (13, 'permission:view', '查看', 'permission', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (14, 'dictionary:add', '新增', 'dictionary', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (15, 'dictionary:delete', '删除', 'dictionary', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (16, 'dictionary:edit', '编辑', 'dictionary', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (17, 'dictionary:view', '查看', 'dictionary', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (18, 'settings:view', '查看', 'settings', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (19, 'log:delete', '删除', 'log', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (20, 'log:empty', '清空', 'log', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (21, 'log:view', '查看', 'log', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (22, 'post:add', '新增', 'post', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (23, 'post:delete', '删除', 'post', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (24, 'post:edit', '编辑', 'post', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (25, 'post:view', '查看', 'post', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (26, 'department:add', '新增', 'department', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (27, 'department:delete', '删除', 'department', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (28, 'department:edit', '编辑', 'department', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (29, 'department:view', '查看', 'department', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (30, 'file:upload', '上传', 'file', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (31, 'file:delete', '删除', 'file', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (32, 'file:download', '下载', 'file', '0', 1, 1,
        NOW(), NOW(), NULL);
INSERT INTO `permission` (`id`, `code`, `name`, `menu`, `permission_status`, `create_user`, `update_user`,
                          `create_time`, `update_time`, `delete_time`)
VALUES (33, 'file:view', '查看', 'file', '0', 1, 1,
        NOW(), NOW(), NULL);
COMMIT;

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '岗位id',
    `code`        varchar(50)  NOT NULL COMMENT '岗位编码',
    `name`        varchar(50)           DEFAULT NULL COMMENT '岗位名称',
    `post_status` varchar(10)  NOT NULL COMMENT '岗位状态(字典:post_status)',
    `remark`      varchar(300)          DEFAULT NULL COMMENT '备注',
    `create_user` int unsigned NOT NULL COMMENT '创建者(用户id)',
    `update_user` int unsigned NOT NULL COMMENT '更新者(用户id)',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime              DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `code` (`code`, `delete_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='岗位表';

-- ----------------------------
-- Records of post
-- ----------------------------
BEGIN;
INSERT INTO `post` (`id`, `code`, `name`, `post_status`, `remark`, `create_user`, `update_user`, `create_time`,
                    `update_time`, `delete_time`)
VALUES (1, '100001', '董事长', '0', NULL, 1, 1, NOW(),
        NOW(), NULL);
INSERT INTO `post` (`id`, `code`, `name`, `post_status`, `remark`, `create_user`, `update_user`, `create_time`,
                    `update_time`, `delete_time`)
VALUES (2, '101001', '研发部总经理', '0', NULL, 1, 1, NOW(),
        NOW(), NULL);
INSERT INTO `post` (`id`, `code`, `name`, `post_status`, `remark`, `create_user`, `update_user`, `create_time`,
                    `update_time`, `delete_time`)
VALUES (3, '102001', '财务部总经理', '0', NULL, 1, 1, NOW(),
        NOW(), NULL);
INSERT INTO `post` (`id`, `code`, `name`, `post_status`, `remark`, `create_user`, `update_user`, `create_time`,
                    `update_time`, `delete_time`)
VALUES (4, '103001', '市场部总经理', '0', NULL, 1, 1, NOW(),
        NOW(), NULL);
COMMIT;

-- ----------------------------
-- Table structure for post_role
-- ----------------------------
DROP TABLE IF EXISTS `post_role`;
CREATE TABLE `post_role`
(
    `id`      int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `post_id` int unsigned NOT NULL COMMENT '岗位id',
    `role_id` int unsigned NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='岗位与角色关联表';

-- ----------------------------
-- Records of post_role
-- ----------------------------
BEGIN;
INSERT INTO `post_role` (`id`, `post_id`, `role_id`)
VALUES (1, 1, 1);
INSERT INTO `post_role` (`id`, `post_id`, `role_id`)
VALUES (2, 3, 2);
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '角色id',
    `code`        varchar(100) NOT NULL COMMENT '角色编码',
    `name`        varchar(200)          DEFAULT NULL COMMENT '角色名称',
    `role_status` varchar(10)  NOT NULL COMMENT '角色状态(字典:role_status)',
    `create_user` int unsigned NOT NULL COMMENT '创建者(用户id)',
    `update_user` int unsigned NOT NULL COMMENT '更新者(用户id)',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime              DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `code` (`code`, `delete_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `code`, `name`, `role_status`, `create_user`, `update_user`, `create_time`, `update_time`,
                    `delete_time`)
VALUES (1, 'admin', '管理员', '0', 1, 1, NOW(), NOW(),
        NULL);
INSERT INTO `role` (`id`, `code`, `name`, `role_status`, `create_user`, `update_user`, `create_time`, `update_time`,
                    `delete_time`)
VALUES (2, 'guest', '访客', '0', 1, 1, NOW(), NOW(),
        NULL);
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
  COLLATE = utf8mb4_general_ci COMMENT ='角色与权限关联表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`)
VALUES (1, 2, 18);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `department`  int unsigned NOT NULL COMMENT '部门(部门id)',
    `username`    varchar(50)  NOT NULL COMMENT '用户名',
    `name`        varchar(100)          DEFAULT NULL COMMENT '姓名',
    `sex`         varchar(10)  NOT NULL COMMENT '性别(字典:sex)',
    `user_status` varchar(10)  NOT NULL COMMENT '用户状态(字典:user_status)',
    `password`    varchar(100) NOT NULL COMMENT '登陆密码',
    `phone`       varchar(11)           DEFAULT NULL COMMENT '用户手机',
    `avatar`      varchar(200)          DEFAULT NULL COMMENT '头像url',
    `email`       varchar(100)          DEFAULT NULL COMMENT '邮箱',
    `create_user` int unsigned NOT NULL COMMENT '创建者(用户id)',
    `update_user` int unsigned NOT NULL COMMENT '更新者(用户id)',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_time` datetime              DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username` (`username`, `delete_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `department`, `username`, `name`, `sex`, `user_status`, `password`, `phone`, `avatar`,
                    `email`, `create_user`, `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (1, 1, 'admin', '管理员', '1', '0', 'c3284d0f94606de1fd2af172aba15bf3', '15887280652', NULL,
        'hanjie.pu@outlook.com', 1, 1, NOW(), NOW(), NULL);
INSERT INTO `user` (`id`, `department`, `username`, `name`, `sex`, `user_status`, `password`, `phone`, `avatar`,
                    `email`, `create_user`, `update_user`, `create_time`, `update_time`, `delete_time`)
VALUES (2, 3, 'guest', '访客', '2', '0', 'c42d47602bc29f89644841702ca0ebf6', '18650329448', NULL,
        'example@fox.com', 1, 1, NOW(), NOW(), NULL);
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
  COLLATE = utf8mb4_general_ci COMMENT ='用户与角色关联表';

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
-- Table structure for user_post
-- ----------------------------
DROP TABLE IF EXISTS `user_post`;
CREATE TABLE `user_post`
(
    `id`      int unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id` int unsigned NOT NULL COMMENT '用户id',
    `post_id` int unsigned NOT NULL COMMENT '岗位id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户与岗位关联表';

-- ----------------------------
-- Records of user_post
-- ----------------------------
BEGIN;
INSERT INTO `user_post` (`id`, `user_id`, `post_id`)
VALUES (1, 1, 1);
INSERT INTO `user_post` (`id`, `user_id`, `post_id`)
VALUES (2, 2, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
