/*
 Navicat Premium Data Transfer

 Source Server         : 阿里云
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : 112.74.42.251:3306
 Source Schema         : mws

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 18/02/2022 20:44:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activities
-- ----------------------------
DROP TABLE IF EXISTS `activities`;
CREATE TABLE `activities`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动名',
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `beginDate` datetime(0) NULL DEFAULT NULL COMMENT '活动开始日期',
  `deadLine` datetime(0) NULL DEFAULT NULL COMMENT '活动截止日期',
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动介绍',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activities
-- ----------------------------
INSERT INTO `activities` VALUES (1, '全场八折', 'img/8zhe.jpg', '2021-10-01 00:00:01', '2021-12-31 23:59:59', '全场商品八折，优惠多多，可与其他优惠活动同时进行');
INSERT INTO `activities` VALUES (2, '蛋糕DIY', 'img/diy.jpg', '2021-10-01 00:00:01', '2021-12-31 23:59:59', '可预约蛋糕DIY，制作你自己喜欢的蛋糕吧！');
INSERT INTO `activities` VALUES (3, '满即送', 'img/mansong.jpg', '2021-10-27 19:58:52', '2021-11-15 23:59:59', '单次消费满20可在三元及以下商品任选一个免费赠送！');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imgsrc` varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of banner
-- ----------------------------
INSERT INTO `banner` VALUES (1, 'http://112.74.42.251:8080/mws/img/bread.png');
INSERT INTO `banner` VALUES (2, 'http://112.74.42.251:8080/mws/img/osxcmb.png');
INSERT INTO `banner` VALUES (3, 'http://112.74.42.251:8080/mws/img/bakery.png');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '面包');
INSERT INTO `category` VALUES (2, '蛋糕');
INSERT INTO `category` VALUES (3, '甜品');
INSERT INTO `category` VALUES (4, '饮料');

-- ----------------------------
-- Table structure for catesitem
-- ----------------------------
DROP TABLE IF EXISTS `catesitem`;
CREATE TABLE `catesitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `imgsrc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of catesitem
-- ----------------------------
INSERT INTO `catesitem` VALUES (1, '面包', 'http://112.74.42.251:8080/mws/img/面包.png');
INSERT INTO `catesitem` VALUES (2, '蛋糕', 'http://112.74.42.251:8080/mws/img/蛋糕.png');
INSERT INTO `catesitem` VALUES (3, '甜品', 'http://112.74.42.251:8080/mws/img/甜品.png');
INSERT INTO `catesitem` VALUES (4, '饮料', 'http://112.74.42.251:8080/mws/img/饮料.png');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '订单编号',
  `money` double(10, 2) NULL DEFAULT NULL COMMENT '订单金额',
  `receiverAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `receiverName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `receiverPhone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `paystate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '支付状态（0-取消，1-未支付，2-已支付）',
  `ordertime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '下单时间',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '会员编号',
  `orderState` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单状态（1-未发货，2-配送中，3-已到达，4-已取消）',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '订单备注',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid`(`user_id`) USING BTREE,
  CONSTRAINT `userid` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('Bkt4ZiPtQz', 37.50, '通港西街黎明大学', '吴泉标', '17880343423', '2', '2022-01-05 19:21:48', 43, '3', '');
INSERT INTO `order` VALUES ('Ux3oqT5H7r', 44.50, '通港西街黎明大学', '吴泉标', '17880343423', '2', '2022-01-05 19:20:47', 43, '1', '饮料要冰的，放门口');

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderId` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `productId` int(100) NOT NULL,
  `productName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `buyNum` int(11) NULL DEFAULT NULL,
  `buyPrice` float(10, 2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`, `orderId`, `productId`) USING BTREE,
  INDEX `fk_oi_p`(`productId`) USING BTREE,
  INDEX `oid`(`orderId`) USING BTREE,
  CONSTRAINT `oid` FOREIGN KEY (`orderId`) REFERENCES `order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `pid` FOREIGN KEY (`productId`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (71, 'Bkt4ZiPtQz', 1, '脏脏包', 3, 10.00);
INSERT INTO `orderitem` VALUES (72, 'Bkt4ZiPtQz', 3, '葡萄切片', 1, 7.50);
INSERT INTO `orderitem` VALUES (73, 'Ux3oqT5H7r', 1, '脏脏包', 1, 10.00);
INSERT INTO `orderitem` VALUES (74, 'Ux3oqT5H7r', 7, '蛋挞', 1, 8.00);
INSERT INTO `orderitem` VALUES (75, 'Ux3oqT5H7r', 35, '可乐', 1, 3.00);
INSERT INTO `orderitem` VALUES (76, 'Ux3oqT5H7r', 3, '葡萄切片', 1, 7.50);
INSERT INTO `orderitem` VALUES (77, 'Ux3oqT5H7r', 36, '芒果慕斯', 1, 12.00);
INSERT INTO `orderitem` VALUES (78, 'Ux3oqT5H7r', 2, '全麦面包', 1, 4.00);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` double(10, 2) NULL DEFAULT NULL,
  `categoryId` int(11) NULL DEFAULT NULL,
  `pnum` int(11) NULL DEFAULT NULL,
  `imgurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `description` varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shelfLife` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fenlei`(`categoryId`) USING BTREE,
  INDEX `name`(`name`) USING BTREE,
  CONSTRAINT `fenlei` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '脏脏包', 10.00, 1, 20, 'img/mb/zzb.jpg', '外面铺撒可可粉，内包巧克力馅，香甜可口，大家快来买，好吃又便宜，新品出售，爆品促销', 3);
INSERT INTO `product` VALUES (2, '全麦面包', 4.00, 1, 50, 'img/mb/qmmb.jpg', '全麦制作', 4);
INSERT INTO `product` VALUES (3, '葡萄切片', 7.50, 1, 30, 'img/mb/ptqp.jpg', '吐司切片内含葡萄干', 4);
INSERT INTO `product` VALUES (4, '杂粮面包', 4.00, 1, 25, 'img/mb/zlmb.jpg', NULL, 4);
INSERT INTO `product` VALUES (5, '三明治', 7.00, 1, 10, 'img/mb/smz.jpg', NULL, 2);
INSERT INTO `product` VALUES (6, '鸡肉汉堡', 7.00, 1, 6, 'img/mb/hb.jpg', '新鲜鸡肉排裹上蛋液面包糠现炸，隔壁小孩都馋哭了~', 2);
INSERT INTO `product` VALUES (7, '蛋挞', 8.00, 1, 40, 'img/mb/dt.jpg', '', 3);
INSERT INTO `product` VALUES (8, '泡芙', 1.50, 1, 60, 'img/mb/pf.jpg', NULL, 3);
INSERT INTO `product` VALUES (9, '南瓜棒', 8.50, 1, 15, 'img/mb/ngb.jpg', NULL, 3);
INSERT INTO `product` VALUES (10, '水果蛋糕', 66.00, 2, 10, 'img/dg/sgdg.jpg', NULL, 5);
INSERT INTO `product` VALUES (11, '翻糖蛋糕', 66.00, 2, 10, 'img/dg/ftdg.jpg', NULL, 5);
INSERT INTO `product` VALUES (12, '数码蛋糕', 66.00, 2, 10, 'img/dg/smdg.jpg', NULL, 5);
INSERT INTO `product` VALUES (13, '戚风蛋糕', 66.00, 2, 10, 'img/dg/qfdg.jpg', NULL, 5);
INSERT INTO `product` VALUES (35, '可乐', 3.00, 4, 10, 'img/yl/kl.jpg', '肥宅快乐水', 180);
INSERT INTO `product` VALUES (36, '芒果慕斯', 12.00, 3, 5, 'img/tp/mgms.jpg', NULL, 3);
INSERT INTO `product` VALUES (40, '8寸蛋糕', 80.00, 2, 99, 'img/dg/8c.jpg', '8寸蛋糕，冷藏更好吃，保存更久哦~', 3);
INSERT INTO `product` VALUES (41, '面包', 6.00, 2, 1, '', '', 3);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL,
  `roleName` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `roleDesc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, 'admin', '管理员');
INSERT INTO `role` VALUES (2, 'user', '普通会员');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gender` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `registTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `wx_openid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456', '男', '12345678901', '管理员', '管理员', '2021-08-27 15:47:30', '', NULL);
INSERT INTO `user` VALUES (4, '吴泉标', '123456', '男', '17880343423', '帅', '普通会员', '2021-10-13 04:51:57', '', NULL);
INSERT INTO `user` VALUES (7, '123', '123', '男', '123213', '123', '普通会员', '2021-10-12 17:12:23', '', NULL);
INSERT INTO `user` VALUES (8, '1231', '111', '未知', '1', '1', '普通会员', '2021-11-06 01:18:19', '', NULL);
INSERT INTO `user` VALUES (10, '33333', '33333', '男', '33333', '333333', '普通会员', '2021-10-12 17:42:47', '', NULL);
INSERT INTO `user` VALUES (11, '122222', '2121', '男', '1122111', '111', '普通会员', '2021-10-12 17:43:02', '', NULL);
INSERT INTO `user` VALUES (12, '111111', '1111', '女', '12314', '1', '普通会员', '2021-10-13 07:44:34', '', NULL);
INSERT INTO `user` VALUES (14, '444555', '333213', '女', '21412', '11232', '普通会员', '2021-10-13 07:48:01', '', NULL);
INSERT INTO `user` VALUES (15, '6666', '6', '女', '6', '6', '普通会员', '2021-10-12 17:50:34', '', NULL);
INSERT INTO `user` VALUES (16, '66', '6', '女', '6', '6', '普通会员', '2021-10-12 17:50:45', '', NULL);
INSERT INTO `user` VALUES (17, '777', '7', '女', '7', '7', '普通会员', '2021-10-12 17:52:03', '', NULL);
INSERT INTO `user` VALUES (18, '888', '88', '女', '8', '8', '普通会员', '2021-10-12 17:52:17', '', NULL);
INSERT INTO `user` VALUES (19, 'q', '1', '男', '1', '', '普通会员', '2021-10-12 18:06:48', '', NULL);
INSERT INTO `user` VALUES (20, 'ww', '1', '男', '12', '1', '普通会员', '2021-10-12 18:14:30', '', NULL);
INSERT INTO `user` VALUES (21, '546453', '123', '男', '1', '1', '普通会员', '2021-10-12 18:23:15', '', NULL);
INSERT INTO `user` VALUES (22, 'asd', '1', '男', '1', '', '普通会员', '2021-10-12 18:27:18', '', NULL);
INSERT INTO `user` VALUES (33, 'xiaos', '123', '女', '42141512', '1', '普通会员', '2021-11-08 20:33:21', NULL, NULL);
INSERT INTO `user` VALUES (38, 'Cola', '$2a$10$RPO/w..z.OL5E/Jyzhzl/ekqVp2oogYV3RTTAQonT3IPU3ZugJ6UC', NULL, '13960238380', NULL, '普通用户', '2021-11-09 18:37:52', 'olOZj5eWWvf19TMqxDM3J3iNwzPw', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJDb2xhIiwiY3JlYXRlZCI6MTYzNjQ1NDI3MjI4NCwiZXhwIjoxNjM2NDU0NDg4fQ.o69P6dawYzbHf4e9KyelRJQ7yxuc5H1o_f1OrJ6jfocOXPrGO4m8p_BMV82Kq6rLStYsjVrBdkwyU3gMCQyOJA');
INSERT INTO `user` VALUES (43, '。', '$2a$10$kMVL4oWdMpXI1GTcpCYHP.CRIs3.5PMvYwl3LrTrKo5U42ZHuE7nO', NULL, '17880343423', NULL, '普通用户', '2022-01-05 19:17:55', 'olOZj5f3M1qaP2YJJFq2qZLOOPIA', 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiLjgIIiLCJjcmVhdGVkIjoxNjQxMzgxNDc1ODYzLCJleHAiOjE2NDEzODE2OTF9.yL6TacihFRePig3jYObhZJ3JtCF-2HuEPPGQcCB6s-1-KoUU0m-RNzRcRvPsVBjNdcAwvNOTfjenORArrnSirw');

-- ----------------------------
-- Table structure for user_addr
-- ----------------------------
DROP TABLE IF EXISTS `user_addr`;
CREATE TABLE `user_addr`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(255) NULL DEFAULT NULL COMMENT '用户id',
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `mobile` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收货地址',
  `defaultState` int(255) NULL DEFAULT NULL COMMENT '默认状态（1-默认地址，0-其他地址）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `userid_addr`(`userId`) USING BTREE,
  CONSTRAINT `userid_addr` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_addr
-- ----------------------------
INSERT INTO `user_addr` VALUES (1, 1, '吴泉标', '17880343423', '福建省泉州市洛江区马甲镇', 1);
INSERT INTO `user_addr` VALUES (2, 1, '吴泉标', '17880343423', '福建省泉州市丰泽区城华北路泰禾首府', 0);
INSERT INTO `user_addr` VALUES (3, 1, '小张', '14668245823', '福建省泉州市晋江市深沪镇同心路', 0);
INSERT INTO `user_addr` VALUES (4, 12, '刘欢', '15482365368', '福建省泉州市石狮市', 1);
INSERT INTO `user_addr` VALUES (25, 43, '吴泉标', '17880343423', '通港西街黎明大学', 1);
INSERT INTO `user_addr` VALUES (26, 43, '123', '13960238380', '立达', 0);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `roleId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `uid`(`userId`) USING BTREE,
  INDEX `rid`(`roleId`) USING BTREE,
  CONSTRAINT `rid` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `uid` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (5, 1, 1);
INSERT INTO `user_role` VALUES (15, 33, 2);
INSERT INTO `user_role` VALUES (20, 38, 2);
INSERT INTO `user_role` VALUES (24, 43, 2);

SET FOREIGN_KEY_CHECKS = 1;
