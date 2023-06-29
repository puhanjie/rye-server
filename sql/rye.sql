/*
 Navicat Premium Data Transfer

 Source Server         : mydb
 Source Server Type    : MySQL
 Source Server Version : 80028 (8.0.28)
 Source Host           : localhost:3306
 Source Schema         : rye

 Target Server Type    : MySQL
 Target Server Version : 80028 (8.0.28)
 File Encoding         : 65001

 Date: 16/12/2022 23:30:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file`
(
    `id`          int unsigned                                                  NOT NULL AUTO_INCREMENT,
    `path`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件地址',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '文件名',
    `file_size`   bigint                                                                 DEFAULT NULL COMMENT '文件大小（Byte）',
    `uuid`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci           DEFAULT NULL COMMENT '文件唯一uuid值',
    `create_time` datetime(3)                                                   NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `update_time` datetime(3)                                                   NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    `delete_time` datetime(3)                                                            DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `md5_del` (`uuid`, `delete_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 33
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='文件表';

-- ----------------------------
-- Records of file
-- ----------------------------
BEGIN;
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (1, 'http://localhost:8088/resources/20221214/8d5e6e72-0e07-4eb1-a9d9-d51b788019bd.txt', 'java各种对象区分.txt',
        424, '8d5e6e72-0e07-4eb1-a9d9-d51b788019bd', '2022-12-14 14:41:54.260', '2022-12-14 14:41:54.260', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (2, 'http://localhost:8088/resources/20221214/5d024617-da56-4df1-b1b4-132b63bdd2cf.txt', 'java各种对象区分.txt',
        424, '5d024617-da56-4df1-b1b4-132b63bdd2cf', '2022-12-14 14:54:24.486', '2022-12-14 14:54:24.486', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (3, 'http://localhost:8088/resources/20221214/a6fa8d80-e606-4771-86aa-cdccc7f6c62b.txt', 'java各种对象区分.txt',
        424, 'a6fa8d80-e606-4771-86aa-cdccc7f6c62b', '2022-12-14 15:14:44.869', '2022-12-14 15:14:44.869', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (4, 'http://localhost:8088/resources/20221214/ac33f38c-ff19-45ff-bcfd-31e7cf19a722.pdf',
        'certificate_2884631.pdf', 681777, 'ac33f38c-ff19-45ff-bcfd-31e7cf19a722', '2022-12-14 15:14:44.874',
        '2022-12-14 15:14:44.874', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (5, 'http://localhost:8088/resources/20221214/87d850c9-f340-415b-ad5f-859b6a8267b4.txt', 'java各种对象区分.txt',
        424, '87d850c9-f340-415b-ad5f-859b6a8267b4', '2022-12-14 15:35:39.487', '2022-12-14 15:35:39.487', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (6, 'http://localhost:8088/resources/20221214/a7c514c9-37aa-4c99-94e8-0af3d3b08e35.pdf',
        'certificate_2884631.pdf', 681777, 'a7c514c9-37aa-4c99-94e8-0af3d3b08e35', '2022-12-14 15:35:39.493',
        '2022-12-14 15:35:39.493', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (7, 'http://localhost:8088/resources/20221214/b03442f6-9dbc-4f77-a19e-fdbb5d575910.txt', 'java各种对象区分.txt',
        424, 'b03442f6-9dbc-4f77-a19e-fdbb5d575910', '2022-12-14 16:18:36.621', '2022-12-14 16:18:36.621', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (8, 'http://localhost:8088/resources/20221214/3a0a0d5a-f3ba-40e0-881c-e2177164f103.txt', 'java各种对象区分.txt',
        424, '3a0a0d5a-f3ba-40e0-881c-e2177164f103', '2022-12-14 17:41:13.533', '2022-12-14 17:41:13.533', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (9,
        '/Users/puhanjie/Projects/JavaProjects/rye/target/classes/static/upload/20221214/2b112e4c-7c4d-4c27-98e7-8f4c551870cf.txt',
        'java各种对象区分.txt', 424, '2b112e4c-7c4d-4c27-98e7-8f4c551870cf', '2022-12-14 17:47:24.475',
        '2022-12-14 17:47:24.475', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (10,
        'http://localhost:8088/Users/puhanjie/Projects/JavaProjects/rye/target/classes/static/upload/20221214/eae2528f-e0ee-43b9-b12b-87350e3b9369.txt',
        'java各种对象区分.txt', 424, 'eae2528f-e0ee-43b9-b12b-87350e3b9369', '2022-12-14 17:48:37.971',
        '2022-12-14 17:48:37.971', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (11,
        'http://localhost:8088/Users/puhanjie/Projects/JavaProjects/rye/target/classes/static/upload/20221214/9255d4d3-dcfe-4f8d-83ef-c223cbd75148.txt',
        'java各种对象区分.txt', 424, '9255d4d3-dcfe-4f8d-83ef-c223cbd75148', '2022-12-14 17:50:27.727',
        '2022-12-14 17:50:27.727', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (12,
        'http://localhost:8088/Users/puhanjie/Projects/JavaProjects/rye/target/classes/static/upload/20221214/1194abd4-9085-4ca3-a16a-32a12a502a27.txt',
        'java各种对象区分.txt', 424, '1194abd4-9085-4ca3-a16a-32a12a502a27', '2022-12-14 17:52:03.469',
        '2022-12-14 17:52:03.469', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (13, 'http://localhost:8088/upload/20221214/34d8cb63-6a50-4071-b15c-261b408a4055.txt', 'java各种对象区分.txt',
        424, '34d8cb63-6a50-4071-b15c-261b408a4055', '2022-12-14 17:54:44.889', '2022-12-14 17:54:44.889', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (14, 'http://localhost:8088/upload/20221214/a3f4b964-3046-4ed2-9985-49f1a96fb1e1.txt', 'java各种对象区分.txt',
        424, 'a3f4b964-3046-4ed2-9985-49f1a96fb1e1', '2022-12-14 18:05:12.369', '2022-12-14 18:05:12.369', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (15, 'http://localhost:8088/upload/20221214/bbfda998-2a3d-4148-a0a0-fe03c034c953.txt', 'java各种对象区分.txt',
        424, 'bbfda998-2a3d-4148-a0a0-fe03c034c953', '2022-12-14 18:07:24.372', '2022-12-14 18:07:24.372', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (16, 'http://localhost:8088/upload/20221214/0143309f-dcf7-4fe2-9fae-3c2c4c62b7b8.txt', 'java各种对象区分.txt',
        424, '0143309f-dcf7-4fe2-9fae-3c2c4c62b7b8', '2022-12-14 18:10:34.683', '2022-12-14 18:10:34.683', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (17, 'http://localhost:8088/upload/20221214/325ecf84-e3ae-49a9-9ca3-532f6ef7bf53.txt', 'java各种对象区分.txt',
        424, '325ecf84-e3ae-49a9-9ca3-532f6ef7bf53', '2022-12-14 18:14:10.497', '2022-12-14 18:14:10.497', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (18, 'http://localhost:8088/upload/20221214/b298cf3b-6f38-479d-8a45-99fa4d096d33.txt', 'java各种对象区分.txt',
        424, 'b298cf3b-6f38-479d-8a45-99fa4d096d33', '2022-12-14 18:18:05.733', '2022-12-14 18:18:05.733', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (19, 'http://localhost:8088/upload/20221214/320d7bc0-0f82-4a89-81a4-6a7886f52c82.txt', 'java各种对象区分.txt',
        424, '320d7bc0-0f82-4a89-81a4-6a7886f52c82', '2022-12-14 18:19:04.311', '2022-12-14 18:19:04.311', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (20, 'http://localhost:8088/upload/20221214/2fe822ff-b783-4985-8e8a-8140fc6ea9e6.txt', 'java各种对象区分.txt',
        424, '2fe822ff-b783-4985-8e8a-8140fc6ea9e6', '2022-12-14 21:10:36.572', '2022-12-14 21:10:36.572', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (21, 'http://localhost:8088/upload/20221214/af1dcac9-09fd-4355-a1c8-555020f5a917.txt', 'java各种对象区分.txt',
        424, 'af1dcac9-09fd-4355-a1c8-555020f5a917', '2022-12-14 21:12:32.862', '2022-12-14 21:12:32.862', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (22, 'http://localhost:8088/upload/20221214/e6eb5820-7a67-4ff7-9da8-082a422964a8.txt', 'java各种对象区分.txt',
        424, 'e6eb5820-7a67-4ff7-9da8-082a422964a8', '2022-12-14 21:19:00.249', '2022-12-14 21:19:00.249', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (23, 'http://localhost:8088/static/upload/20221214/971428d3-6db3-4106-822f-4b6f640544d1.txt',
        'java各种对象区分.txt', 424, '971428d3-6db3-4106-822f-4b6f640544d1', '2022-12-14 21:43:18.003',
        '2022-12-14 21:43:18.003', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (24, 'http://localhost:8088/static/20221214/759560d4-a7a1-4e0e-a449-0625c9cb5ed7.txt', 'java各种对象区分.txt',
        424, '759560d4-a7a1-4e0e-a449-0625c9cb5ed7', '2022-12-14 21:47:36.094', '2022-12-14 21:47:36.094', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (25, 'http://localhost:8088/static/20221214/236b170e-bfac-40fb-a6ec-41edd2de2e81.txt', 'java各种对象区分.txt',
        424, '236b170e-bfac-40fb-a6ec-41edd2de2e81', '2022-12-14 22:01:02.248', '2022-12-14 22:01:02.248', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (26, 'http://localhost:8088/static/upload/20221214/ed74e810-076d-4d61-af23-e3d9ba21f773.txt',
        'java各种对象区分.txt', 424, 'ed74e810-076d-4d61-af23-e3d9ba21f773', '2022-12-14 22:01:53.450',
        '2022-12-14 22:01:53.450', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (27, 'http://localhost:8088/static/upload/20221214/ccf64138-2853-4353-ae21-66143f5b4cde.txt',
        'java各种对象区分.txt', 424, 'ccf64138-2853-4353-ae21-66143f5b4cde', '2022-12-14 22:04:01.214',
        '2022-12-14 22:04:01.214', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (28, 'http://localhost:8088/static/upload/20221214/461b9254-5e8c-466b-a904-5ae7f14b22b5.txt',
        'java各种对象区分.txt', 424, '461b9254-5e8c-466b-a904-5ae7f14b22b5', '2022-12-14 22:06:50.888',
        '2022-12-14 22:06:50.888', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (29, 'http://localhost:8088/static/upload/20221214/01c0e730-e8f9-4f8d-af9d-9ba9c29407fe.txt',
        'java各种对象区分.txt', 424, '01c0e730-e8f9-4f8d-af9d-9ba9c29407fe', '2022-12-14 22:07:23.031',
        '2022-12-14 22:07:23.031', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (30, 'http://localhost:8088/static/upload/20221214/129968f7-a93f-4424-9a40-8847a1e49a38.txt',
        'java各种对象区分.txt', 424, '129968f7-a93f-4424-9a40-8847a1e49a38', '2022-12-14 22:09:04.992',
        '2022-12-14 22:09:04.992', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (31, 'http://localhost:8088/res/upload/20221216/14a07c07-8916-45a8-ad36-f888c8cb7e95.sql',
        '1-alter_topenday_2023workday.sql', 61389, '14a07c07-8916-45a8-ad36-f888c8cb7e95', '2022-12-16 22:46:06.151',
        '2022-12-16 22:46:06.151', NULL);
INSERT INTO `file` (`id`, `path`, `name`, `file_size`, `uuid`, `create_time`, `update_time`, `delete_time`)
VALUES (32, 'http://localhost:8088/res/upload/20221216/08177668-239f-4e75-97c4-b58bd5a79a9e.sql', '中文下载.sql', 61389,
        '08177668-239f-4e75-97c4-b58bd5a79a9e', '2022-12-16 22:56:54.262', '2022-12-16 22:56:54.262', NULL);
COMMIT;

-- ----------------------------
-- Table structure for log
-- ----------------------------
DROP TABLE IF EXISTS `log`;
CREATE TABLE `log`
(
    `id`           int unsigned NOT NULL AUTO_INCREMENT,
    `code`         int                                                           DEFAULT NULL COMMENT '响应状态码',
    `message`      varchar(450) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '响应状态消息',
    `user_id`      int unsigned NOT NULL COMMENT '操作用户ID',
    `username`     varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '操作用户',
    `path`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  DEFAULT NULL COMMENT '接口地址',
    `operate_time` datetime(3)  NOT NULL                                         DEFAULT CURRENT_TIMESTAMP(3) COMMENT '操作时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 80
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='操作日志表';

-- ----------------------------
-- Records of log
-- ----------------------------
BEGIN;
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (1, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-01 22:05:03.824');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (2, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-01 22:12:38.466');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (3, 0, '成功', 1, 'admin', 'GET /api/v1/user/all', '2022-12-01 22:13:43.530');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (4, 0, '成功', 1, 'admin', 'GET /api/v1/user/all', '2022-12-01 22:20:42.291');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (5, 0, '成功', 1, 'admin', 'GET /api/v1/user/all', '2022-12-01 23:15:44.686');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (6, 0, '成功', 1, 'admin', 'GET /api/v1/user/list', '2022-12-01 23:40:48.467');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (7, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-02 18:05:06.038');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (8, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-02 18:09:41.763');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (9, 0, '成功', 1, 'admin', 'GET /api/v1/user/list', '2022-12-02 18:10:22.533');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (10, -10003, '无资源访问权限', 1, 'admin', 'GET /api/v1/user/list', '2022-12-02 18:10:54.175');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (11, 0, '成功', 1, 'admin', 'GET /api/v1/user/list', '2022-12-02 18:26:32.786');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (12, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-02 18:27:28.909');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (13, 0, '成功', 1, 'admin', 'GET /api/v1/user/list', '2022-12-02 18:52:31.930');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (14, 0, '成功', 1, 'admin', 'GET /api/v1/user/list', '2022-12-02 19:34:12.113');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (15, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-02 19:34:25.160');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (16, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-02 20:02:28.972');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (17, 0, '成功', 1, 'admin', 'GET /api/v1/user/list', '2022-12-02 20:02:38.654');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (18, 10003, '无资源访问权限', 1, 'admin', 'GET /api/v1/user/list', '2022-12-02 20:07:46.967');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (19, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-02 23:13:04.583');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (20, 0, '成功', 1, 'admin', 'GET /api/v1/user/list', '2022-12-02 23:24:24.657');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (21, 0, '成功', 1, 'admin', 'GET /api/v1/user/info', '2022-12-02 23:38:39.053');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (22, 0, '成功', 1, 'admin', 'GET /api/v1/user/list', '2022-12-02 23:42:38.396');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (23, 0, '成功', 1, 'admin', 'GET /api/v1/user/info', '2022-12-02 23:42:57.971');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (24, 0, '成功', 1, 'admin', 'GET /api/v1/user/info', '2022-12-03 00:11:29.812');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (25, 0, '成功', 1, 'admin', 'GET /api/v1/role/list', '2022-12-03 00:11:43.325');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (26, -1, '失败', 1, 'admin', 'GET /api/v1/permission/list', '2022-12-03 00:12:09.888');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (27, -1, '失败', 1, 'admin', 'GET /api/v1/permission/list', '2022-12-03 00:13:13.116');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (28, 0, '成功', 1, 'admin', 'GET /api/v1/permission/list', '2022-12-03 00:21:28.536');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (29, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-14 14:37:52.352');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (30, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 14:39:16.579');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (31, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 14:41:54.285');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (32, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 14:54:24.529');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (33, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 14:56:15.642');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (34, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 15:01:51.515');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (35, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 15:14:44.911');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (36, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 15:35:39.519');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (37, 0, '成功', 1, 'admin', 'POST /api/v1/permission/add', '2022-12-14 15:44:30.057');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (38, 0, '成功', 1, 'admin', 'POST /api/v1/permission/add', '2022-12-14 15:45:18.927');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (39, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 16:18:36.672');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (40, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 16:21:49.233');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (41, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 16:47:59.196');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (42, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-14 16:53:48.456');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (43, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:21:30.425');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (44, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:33:39.579');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (45, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:36:36.911');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (46, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:37:58.668');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (47, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:41:13.558');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (48, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:47:24.503');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (49, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:48:37.996');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (50, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:50:27.750');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (51, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:52:03.499');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (52, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 17:54:44.914');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (53, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 18:05:12.397');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (54, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 18:07:24.422');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (55, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 18:10:34.720');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (56, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 18:14:10.533');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (57, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 18:18:05.792');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (58, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 18:19:04.337');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (59, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 18:20:01.633');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (60, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-14 20:54:55.082');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (61, -1, '失败', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 20:55:19.420');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (62, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 21:10:36.610');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (63, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 21:12:32.894');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (64, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 21:19:00.292');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (65, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 21:26:25.060');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (66, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 21:42:47.203');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (67, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 21:43:18.035');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (68, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 21:47:36.124');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (69, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 22:01:02.331');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (70, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 22:01:53.510');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (71, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 22:04:01.237');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (72, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 22:06:50.940');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (73, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 22:07:23.064');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (74, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-14 22:09:05.045');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (75, 0, '成功', 1, 'admin', 'POST /api/v1/user/login', '2022-12-16 22:42:44.117');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (76, 0, '成功', 1, 'admin', 'GET /api/v1/user/list', '2022-12-16 22:43:36.914');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (77, 0, '成功', 1, 'admin', 'POST /api/v1/user/add', '2022-12-16 22:44:45.363');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (78, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-16 22:46:06.161');
INSERT INTO `log` (`id`, `code`, `message`, `user_id`, `username`, `path`, `operate_time`)
VALUES (79, 0, '成功', 1, 'admin', 'POST /api/v1/file/upload', '2022-12-16 22:56:54.274');
COMMIT;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`
(
    `id`          int                                                          NOT NULL AUTO_INCREMENT,
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
    `info`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '权限信息',
    `menu`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '归属菜单',
    `menu_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '归属菜单名称',
    `create_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '创建者',
    `update_user` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT '' COMMENT '更新者',
    `create_time` datetime(3)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `update_time` datetime(3)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `menu_name`, `create_user`, `update_user`, `create_time`,
                          `update_time`)
VALUES (1, 'app:admin', '系统管理员', '*', 'all', 'admin', 'admin', '2022-11-12 13:19:33.000',
        '2022-11-12 13:19:33.000');
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `menu_name`, `create_user`, `update_user`, `create_time`,
                          `update_time`)
VALUES (2, 'system:user:view', '用户管理:查看', 'user', '用户管理', 'admin', 'admin', '2022-12-14 15:51:01.000',
        '2022-12-14 15:51:06.000');
INSERT INTO `permission` (`id`, `name`, `info`, `menu`, `menu_name`, `create_user`, `update_user`, `create_time`,
                          `update_time`)
VALUES (3, 'system:user:add', '用户管理:新增', 'user', '用户管理', 'admin', 'admin', '2022-12-14 15:51:09.000',
        '2022-12-14 15:51:13.000');
COMMIT;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`
(
    `id`          int unsigned                                                 NOT NULL AUTO_INCREMENT,
    `name`        varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
    `info`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '角色信息',
    `create_time` datetime(3)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `update_time` datetime(3)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    `delete_time` datetime(3)                                                           DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `name_del` (`name`, `delete_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` (`id`, `name`, `info`, `create_time`, `update_time`, `delete_time`)
VALUES (1, 'admin', '超级管理员', '2022-03-14 21:03:42.217', '2022-03-14 21:03:42.217', NULL);
INSERT INTO `role` (`id`, `name`, `info`, `create_time`, `update_time`, `delete_time`)
VALUES (2, 'guest', '访客', '2022-03-14 21:03:42.219', '2022-03-14 21:03:42.219', NULL);
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`
(
    `id`            int unsigned NOT NULL AUTO_INCREMENT,
    `role_id`       int unsigned NOT NULL COMMENT '角色id',
    `permission_id` int unsigned NOT NULL COMMENT '权限id',
    PRIMARY KEY (`id`),
    KEY `role_id_permission_id` (`role_id`, `permission_id`) USING BTREE COMMENT '联合索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='角色权限表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`)
VALUES (1, 1, 1);
INSERT INTO `role_permission` (`id`, `role_id`, `permission_id`)
VALUES (2, 2, 2);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int unsigned                                                 NOT NULL AUTO_INCREMENT,
    `username`    varchar(24) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名，唯一',
    `password`    varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登陆密码',
    `phone`       varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '用户手机',
    `avatar`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '头像url',
    `email`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '邮箱',
    `create_time` datetime(3)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    `update_time` datetime(3)                                                  NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '更新时间',
    `delete_time` datetime(3)                                                           DEFAULT NULL COMMENT '删除时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `username_del` (`username`, `delete_time`),
    UNIQUE KEY `email_del` (`email`, `delete_time`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` (`id`, `username`, `password`, `phone`, `avatar`, `email`, `create_time`, `update_time`,
                    `delete_time`)
VALUES (1, 'admin', 'admin', '15887280652', NULL, NULL, '2022-03-14 21:03:42.212', '2022-12-02 18:27:17.797', NULL);
INSERT INTO `user` (`id`, `username`, `password`, `phone`, `avatar`, `email`, `create_time`, `update_time`,
                    `delete_time`)
VALUES (2, 'guest', 'guest', '18650329448', '', 'example@fox.com', '2022-12-16 22:44:45.355', '2022-12-16 22:44:45.355',
        NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`      int unsigned NOT NULL AUTO_INCREMENT,
    `user_id` int unsigned NOT NULL COMMENT '用户id',
    `role_id` int unsigned NOT NULL COMMENT '角色id',
    PRIMARY KEY (`id`),
    KEY `user_id_role_id` (`user_id`, `role_id`) USING BTREE COMMENT '联合索引'
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_general_ci COMMENT ='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` (`id`, `user_id`, `role_id`)
VALUES (1, 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
