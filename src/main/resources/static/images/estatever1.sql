CREATE DATABASE IF NOT EXISTS `estatebasic` DEFAULT CHARACTER SET utf8mb4;
USE `estatebasic`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ------------------------------------------------------
-- 1. CÁC BẢNG DANH MỤC (TABLES WITHOUT FOREIGN KEYS)
-- ------------------------------------------------------

-- Table: district
DROP TABLE IF EXISTS `district`;
CREATE TABLE `district` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `district` (`id`, `code`, `name`) VALUES (1,'Q1','Quận 1'),(2,'Q2','Quận 2'),(3,'Q4','Quận 4');

-- Table: renttype
DROP TABLE IF EXISTS `renttype`;
CREATE TABLE `renttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `renttype` (`id`, `code`, `name`) VALUES (1,'tang-tret','Tầng trệt'),(2,'nguyen-can','Nguyên căn'),(3,'noi-that','Nội thất');

-- Table: role
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `role` (`id`, `name`, `code`) VALUES (1,'Quản lý','manager'),(2,'Nhân viên','staff');

-- Table: user
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (`id`, `username`, `password`, `fullname`, `status`) VALUES 
(1,'nguyenvana','123456','nguyen van a',1),
(2,'nguyenvanb','123456','nguyen van b',1),
(3,'nguyenvanc','123456','nguyen van c',1),
(4,'nguyenvand','123456','nguyen van d',1);

-- Table: customer
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: transactiontype
DROP TABLE IF EXISTS `transactiontype`;
CREATE TABLE `transactiontype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ------------------------------------------------------
-- 2. CÁC BẢNG NGHIỆP VỤ & TRUNG GIAN (WITH FOREIGN KEYS)
-- ------------------------------------------------------

-- Table: building (Đã lọc theo Java Model của bạn)
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `districtid` bigint(20) NOT NULL,
  `numberofbasement` int(11) DEFAULT NULL,
  `floorarea` int(11) DEFAULT NULL,
  `rentprice` int(11) NOT NULL,
  `rentpricedescription` text,
  `deposit` varchar(255) DEFAULT NULL,
  `payment` varchar(255) DEFAULT NULL,
  `renttime` varchar(255) DEFAULT NULL,
  `brokeragefee` decimal(13,2) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `managername` varchar(255) DEFAULT NULL,
  `managerphonenumber` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_building_district` FOREIGN KEY (`districtid`) REFERENCES `district` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `building` (`id`, `name`, `street`, `ward`, `districtid`, `numberofbasement`, `floorarea`, `rentprice`, `rentpricedescription`, `managername`, `managerphonenumber`) VALUES 
(1,'Nam Giao Building Tower','59 phan xích long','Phường 2',1,2,500,15,'15 triệu/m2','Anh Nam-Chị Linh','0915354727'),
(2,'ACM Tower','96 cao thắng','Phường 4',2,2,650,18,'18 triệu/m2','Chú Thuận','0173546263'),
(3,'Alpha 2 Building Tower','153 nguyễn đình chiểu','Phường 6',1,1,200,20,'20 triệu/m2','Cô Lý','0555532578'),
(4,'IDD 1 Building','111 Lý Chính Thắng','Phường 7',3,1,200,12,'12 triệu/m2','Anh Long','017345253');

-- Table: rentarea (Quan hệ 1-N với Building)
DROP TABLE IF EXISTS `rentarea`;
CREATE TABLE `rentarea` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `value` int(11) DEFAULT NULL,
  `buildingid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `rentarea_building` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `rentarea` (`value`, `buildingid`) VALUES (100,1),(200,1),(200,2),(300,2),(400,2),(300,3),(500,3),(100,4);

-- Table: user_role (Bảng trung gian N-N giữa User và Role)
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `roleid` bigint(20) NOT NULL,
  `userid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user_role` (`roleid`, `userid`) VALUES (1,1),(2,2),(2,3),(2,4);

-- Table: assignmentbuilding (Bảng trung gian N-N giữa Staff và Building)
DROP TABLE IF EXISTS `assignmentbuilding`;
CREATE TABLE `assignmentbuilding` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `staffid` bigint(20) NOT NULL,
  `buildingid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_building_user` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`),
  CONSTRAINT `fk_user_building` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `assignmentbuilding` (`staffid`, `buildingid`) VALUES (2,1),(2,3),(3,1),(3,4);

-- Table: buildingrenttype (Bảng trung gian N-N giữa Building và RentType)
DROP TABLE IF EXISTS `buildingrenttype`;
CREATE TABLE `buildingrenttype` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `buildingid` bigint(20) NOT NULL,
  `renttypeid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_buildingtype_building` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`),
  CONSTRAINT `fk_buildingtype_renttype` FOREIGN KEY (`renttypeid`) REFERENCES `renttype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `buildingrenttype` (`buildingid`, `renttypeid`) VALUES (1,1),(1,2),(2,2),(3,3),(4,1),(4,2),(4,3);

-- Table: assignmentcustomer (Bảng trung gian N-N giữa Staff và Customer)
DROP TABLE IF EXISTS `assignmentcustomer`;
CREATE TABLE `assignmentcustomer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `staffid` bigint(20) NOT NULL,
  `customerid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_customer_user` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_user_customer` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Table: transaction (Ghi lại các giao dịch của Khách hàng)
DROP TABLE IF EXISTS `transaction`;
CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `customerid` bigint(20) NOT NULL,
  `type` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_customer_transaction` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_transactiontype_transaction` FOREIGN KEY (`type`) REFERENCES `transactiontype` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;