/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : takeout

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 08/10/2020 13:41:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for order_detail
-- ----------------------------
DROP TABLE IF EXISTS `order_detail`;
CREATE TABLE `order_detail`  (
  `detail_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `product_id` int(0) NOT NULL,
  `product_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '商品单价',
  `product_quantity` int(0) NOT NULL COMMENT '商品数量',
  `product_icon` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品小图',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`detail_id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单详情表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_detail
-- ----------------------------
INSERT INTO `order_detail` VALUES ('c966768513481b419ab39944e4ddd959', '1e059538c35ca863244eb835d97c353e', 7, '香辣牛肉面', 33.50, 1, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597809655720&di=122e120a433c55d42563729a404a0fdc&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftranslate%2F173%2Fw600h373%2F20180721%2F_Gzq-hfqtahi1090555.jpg', '2020-10-07 22:19:57', '2020-10-07 22:19:57');
INSERT INTO `order_detail` VALUES ('d5e3cf92d3b7622a785a3e4d4dc50a99', '4d55149a1403bf1b359b54e74a319972', 1, '肉夹馍', 16.00, 1, 'https://s1.st.meishij.net/rs/50/123/6030800/n6030800_152708155351112.jpg', '2020-10-07 22:16:28', '2020-10-07 22:16:28');
INSERT INTO `order_detail` VALUES ('daddb38237f360d0ac640448af01d8cb', 'cc671ba7c4c93acf00a554bb28869cda', 1, '肉夹馍', 16.00, 1, 'https://s1.st.meishij.net/rs/50/123/6030800/n6030800_152708155351112.jpg', '2020-10-07 22:23:04', '2020-10-07 22:23:04');
INSERT INTO `order_detail` VALUES ('f6edc5fa1aff69c9c992d48dfcbebd2b', 'dcf4ef2f50b3d4795e7a9657450d1fdd', 4, '宫保鸡丁', 33.00, 2, 'https://s1.st.meishij.net/rs/105/225/1306355/n1306355_150185137138378.jpg', '2020-10-07 23:37:17', '2020-10-07 23:37:17');

-- ----------------------------
-- Table structure for order_master
-- ----------------------------
DROP TABLE IF EXISTS `order_master`;
CREATE TABLE `order_master`  (
  `order_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `buyer_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '买家名字',
  `buyer_phone` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '买家电话',
  `buyer_address` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '买家地址',
  `buyer_openid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '买家微信openid',
  `order_amount` decimal(8, 2) NOT NULL COMMENT '订单总金额',
  `order_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '订单状态，默认0新下单',
  `pay_status` tinyint(0) NOT NULL DEFAULT 0 COMMENT '支付状态，默认0未支付',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`order_id`) USING BTREE,
  INDEX `idx_buyer_openid`(`buyer_openid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_master
-- ----------------------------
INSERT INTO `order_master` VALUES ('1e059538c35ca863244eb835d97c353e', '王忠秀', '18655451012', '杨浦区', 'sdfyc123Werdsfd98712Bdc', 33.50, 2, 1, '2020-10-07 22:19:57', '2020-10-07 22:20:50');
INSERT INTO `order_master` VALUES ('4d55149a1403bf1b359b54e74a319972', '吴迪', '19777628319', '静安区', 'sdfyc123Werdsfd98712Bdc', 16.00, 2, 0, '2020-10-07 22:16:28', '2020-10-07 22:17:36');
INSERT INTO `order_master` VALUES ('cc671ba7c4c93acf00a554bb28869cda', '贺思睿', '1998829182', '长浜路', 'sdfyc123Werdsfd98712Bdc', 16.00, 1, 1, '2020-10-07 22:23:04', '2020-10-07 22:32:33');
INSERT INTO `order_master` VALUES ('dcf4ef2f50b3d4795e7a9657450d1fdd', '周杰伦', '12132131312', '台北', 'sdfyc123Werdsfd98712Bdc', 66.00, 2, 0, '2020-10-07 23:37:17', '2020-10-07 23:37:26');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category`  (
  `category_id` int(0) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类目名称',
  `category_type` int(0) NOT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`category_id`) USING BTREE,
  UNIQUE INDEX `uqe_category_type`(`category_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '类目表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_category
-- ----------------------------
INSERT INTO `product_category` VALUES (1, '热销榜', 2, '2020-01-07 17:22:48', '2020-08-27 10:51:41');
INSERT INTO `product_category` VALUES (2, '夜宵', 3, '2020-01-07 17:26:42', '2020-01-14 16:57:36');
INSERT INTO `product_category` VALUES (3, '午餐', 10, '2020-01-07 17:37:19', '2020-01-14 16:57:36');
INSERT INTO `product_category` VALUES (16, '小吃', 11, '2020-01-22 17:34:56', '2020-01-22 17:34:56');
INSERT INTO `product_category` VALUES (17, '早餐', 1, '2020-01-31 16:50:16', '2020-01-31 16:50:16');

-- ----------------------------
-- Table structure for product_info
-- ----------------------------
DROP TABLE IF EXISTS `product_info`;
CREATE TABLE `product_info`  (
  `product_id` int(0) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `product_price` decimal(8, 2) NOT NULL COMMENT '商品单价',
  `product_stock` int(0) NOT NULL COMMENT '库存',
  `product_description` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `product_icon` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '小图',
  `category_type` int(0) NOT NULL COMMENT '类目编号',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  `product_status` int(0) NOT NULL COMMENT '商品状态，1正常0下架',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_info
-- ----------------------------
INSERT INTO `product_info` VALUES (1, '肉夹馍4', 16.00, 105, '陕西', 'https://s1.st.meishij.net/rs/50/123/6030800/n6030800_152708155351112.jpg', 2, '2020-01-07 23:45:33', '2020-09-21 22:04:15', 1);
INSERT INTO `product_info` VALUES (2, '皮蛋瘦肉粥', 3.50, 79, '很好喝的粥', 'https://s1.st.meishij.net/rs/105/225/1306355/n1306355_150185137138378.jpg', 2, '2020-01-07 23:32:00', '2020-09-21 22:04:15', 1);
INSERT INTO `product_info` VALUES (3, '羊肉泡馍', 33.00, 262, '好吃好吃', 'https://s1.st.meishij.net/rs/106/232/3995606/n3995606_152654133043680.jpg', 3, '2020-01-11 22:46:37', '2020-09-18 20:26:45', 1);
INSERT INTO `product_info` VALUES (4, '宫保鸡丁', 33.00, 98, '好吃好吃', 'https://s1.st.meishij.net/rs/105/225/1306355/n1306355_150185137138378.jpg', 3, '2020-01-22 15:53:36', '2020-10-07 23:37:17', 1);
INSERT INTO `product_info` VALUES (5, '鸡汤米线2', 332.00, 1107, '好吃好喝2', 'https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3407218543,1638670917&fm=26&gp=0.jpg', 2, '2020-01-22 16:15:51', '2020-10-07 22:31:47', 1);
INSERT INTO `product_info` VALUES (6, '油炸鸡翅', 66.00, 104, '好吃好吃', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1337981145,1578667496&fm=26&gp=0.jpg', 11, '2020-01-22 17:35:17', '2020-08-26 15:32:02', 1);
INSERT INTO `product_info` VALUES (7, '香辣牛肉面', 33.50, 94, '香辣', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1597809655720&di=122e120a433c55d42563729a404a0fdc&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ftranslate%2F173%2Fw600h373%2F20180721%2F_Gzq-hfqtahi1090555.jpg', 2, '2020-01-23 15:42:40', '2020-08-27 15:06:22', 1);
INSERT INTO `product_info` VALUES (10, '火锅', 666.00, 661, '好吃好吃', 'https://s1.st.meishij.net/rs/105/225/1306355/n1306355_150185137138378.jpg', 3, '2020-01-25 13:44:55', '2020-09-18 20:27:10', 1);
INSERT INTO `product_info` VALUES (12, '鸳鸯火锅', 66.00, 98, '好吃好吃好吃', 'https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1347607405,961608748&fm=26&gp=0.jpg', 11, '2020-01-26 17:06:32', '2020-08-26 15:32:02', 1);
INSERT INTO `product_info` VALUES (13, '卤肥肠', 38.00, 100, '好吃好吃', 'http://5b0988e595225.cdn.sohucs.com/images/20171020/6081aca340dd4ec692dcdeeca7f44fe6.jpeg', 11, '2020-01-26 17:40:25', '2020-08-20 14:04:45', 1);
INSERT INTO `product_info` VALUES (17, '牛肉汉堡', 22.50, 99, '好吃好吃', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1598433528413&di=22d2c81a763072271a60038e48555af2&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20180408%2F0c9a6946e0604dca85ad44ad209e3a05.jpeg', 1, '2020-08-26 14:44:04', '2020-10-07 16:01:10', 1);
INSERT INTO `product_info` VALUES (18, '汉堡王', 24.00, 233, '好吃好吃', 'https://s1.st.meishij.net/rs/50/123/6030800/n6030800_152708155351112.jpg', 10, '2020-01-07 23:45:33', '2020-10-07 22:34:15', 1);
INSERT INTO `product_info` VALUES (37, '螺蛳粉', 12.00, 232, '臭', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1602091422834&di=94a0897381b597f82d5d439586feaad6&imgtype=0&src=http%3A%2F%2Fp0.meituan.net%2Fbbia%2F89750d9be0be69d1e8f6cf16bf058afe1872878.png', 3, '2020-10-07 23:35:20', '2020-10-07 23:35:20', 1);
INSERT INTO `product_info` VALUES (38, '臭豆腐', 12.00, 23, '32', 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1602091422834&di=94a0897381b597f82d5d439586feaad6&imgtype=0&src=http%3A%2F%2Fp0.meituan.net%2Fbbia%2F89750d9be0be69d1e8f6cf16bf058afe1872878.png', 2, '2020-10-07 23:36:06', '2020-10-07 23:36:06', 1);

SET FOREIGN_KEY_CHECKS = 1;
