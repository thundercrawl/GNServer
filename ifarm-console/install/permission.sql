CREATE TABLE `t_console_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_id` int(11) NULL DEFAULT NULL,
  `permission_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `edit_able` tinyint(1) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;


INSERT INTO `t_console_permission` VALUES (3011, 301, '/expenseVelocity/read', '查询', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3012, 301, '/expenseVelocity/edit', '编辑', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3013, 301, '/expenseVelocity/excelI', 'Excel导入', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3014, 301, '/expenseVelocity/excelE', 'Excel导出', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3021, 302, '/kunpengBussiness/read', '查询', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3022, 302, '/kunpengBussiness/edit', '编辑', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3023, 302, '/kunpengBussiness/excelI', 'Excel导入', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3024, 302, '/kunpengBussiness/excelE', 'Excel导出', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3031, 303, '/xinshengBussiness/read', '查询', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3032, 303, '/xinshengBussiness/edit', '编辑', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3033, 303, '/xinshengBussiness/excelI', 'Excel导入', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3034, 303, '/xinshengBussiness/excelE', 'Excel导出', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3041, 304, '/zhideContractDownload/read', '查询', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3042, 304, '/zhideContractDownload/edit', '编辑', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3043, 304, '/zhideContractDownload/excelI', 'Excel导入', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3044, 304, '/zhideContractDownload/excelE', 'Excel导出', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3051, 305, '/BussinessStat/read', '查询', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3052, 305, '/BussinessStat/edit', '编辑', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3053, 305, '/BussinessStat/excelI', 'Excel导入', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (3054, 305, '/BussinessStat/excelE', 'Excel导出', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (1011, 101, '/UserMgr/read', '查询', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (1012, 101, '/UserMgr/edit', '编辑', 1, '2018-05-11 23:33:23');
INSERT INTO `t_console_permission` VALUES (1013, 101, '/UserMgr/delete', '删除', 1, '2018-05-11 23:33:23');