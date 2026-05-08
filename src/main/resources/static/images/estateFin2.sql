CREATE DATABASE  IF NOT EXISTS `estatebasic` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `estatebasic`;
-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: localhost    Database: estatebasic
-- ------------------------------------------------------
-- Server version	8.0.45

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignmentbuilding`
--

DROP TABLE IF EXISTS `assignmentbuilding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignmentbuilding` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `staffid` bigint NOT NULL,
  `buildingid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_building_user` (`buildingid`),
  KEY `fk_user_building` (`staffid`),
  CONSTRAINT `fk_building_user` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`),
  CONSTRAINT `fk_user_building` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignmentbuilding`
--

LOCK TABLES `assignmentbuilding` WRITE;
/*!40000 ALTER TABLE `assignmentbuilding` DISABLE KEYS */;
INSERT INTO `assignmentbuilding` VALUES (1,2,1),(2,2,2),(3,2,5),(4,3,3),(5,3,4),(6,3,6),(7,4,7),(8,4,8),(9,2,9),(10,3,10),(11,4,11),(12,6,12),(13,6,13),(14,7,14),(15,7,15),(16,2,16),(17,3,17),(18,4,18),(19,6,19),(20,7,20),(21,9,21),(22,9,22),(23,10,23),(24,10,24);
/*!40000 ALTER TABLE `assignmentbuilding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assignmentcustomer`
--

DROP TABLE IF EXISTS `assignmentcustomer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignmentcustomer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `staffid` bigint NOT NULL,
  `customerid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_user` (`customerid`),
  KEY `fk_user_customer` (`staffid`),
  CONSTRAINT `fk_customer_user` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_user_customer` FOREIGN KEY (`staffid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignmentcustomer`
--

LOCK TABLES `assignmentcustomer` WRITE;
/*!40000 ALTER TABLE `assignmentcustomer` DISABLE KEYS */;
INSERT INTO `assignmentcustomer` VALUES (1,2,1),(2,2,2),(3,2,3),(4,3,4),(5,3,5),(6,3,6),(7,4,7),(8,4,8),(9,4,9),(10,2,10),(11,2,11),(12,2,12),(13,3,13),(14,3,14),(15,4,15),(16,4,16),(17,6,17),(18,6,18),(19,7,19),(20,7,20),(21,3,21),(22,4,22),(23,6,23),(24,7,24),(25,9,25),(26,9,26),(27,10,27),(28,10,28),(29,2,29),(30,3,30);
/*!40000 ALTER TABLE `assignmentcustomer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `building`
--

DROP TABLE IF EXISTS `building`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `building` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `street` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `districtid` bigint NOT NULL,
  `numberofbasement` int DEFAULT NULL,
  `floorarea` int DEFAULT NULL,
  `rentprice` int NOT NULL,
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
  KEY `fk_building_district` (`districtid`),
  CONSTRAINT `fk_building_district` FOREIGN KEY (`districtid`) REFERENCES `district` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `building`
--

LOCK TABLES `building` WRITE;
/*!40000 ALTER TABLE `building` DISABLE KEYS */;
INSERT INTO `building` VALUES (1,'Nam Giao Building','59 Phan Xích Long','Phường 2',5,2,500,15,'15 triệu/m2/tháng','3 tháng','Thanh toán theo quý','Tối thiểu 1 năm',1.00,'Tòa nhà phù hợp văn phòng vừa và nhỏ','/images/nam-giao.jpg','Anh Nam','0915354727'),(2,'ACM Tower','96 Cao Thắng','Phường 4',2,2,650,18,'18 triệu/m2/tháng','3 tháng','Thanh toán theo quý','Tối thiểu 1 năm',1.00,'Vị trí trung tâm, giao thông thuận tiện','/images/acm-tower.jpg','Chú Thuận','0903000002'),(3,'Alpha Tower','153 Nguyễn Đình Chiểu','Phường 6',2,1,300,20,'20 triệu/m2/tháng','2 tháng','Thanh toán theo tháng','Tối thiểu 6 tháng',1.00,'Có sẵn nội thất cơ bản','/images/alpha-tower.jpg','Cô Lý','0903000003'),(4,'IDD Building','111 Lý Chính Thắng','Phường 7',2,1,250,12,'12 triệu/m2/tháng','2 tháng','Thanh toán theo quý','Tối thiểu 1 năm',1.00,'Giá tốt, phù hợp startup','/images/idd-building.jpg','Anh Long','0903000004'),(5,'The Sun Office','25 Nguyễn Huệ','Phường Bến Nghé',1,3,800,30,'30 triệu/m2/tháng','3 tháng','Thanh toán theo quý','Tối thiểu 2 năm',1.50,'Tòa nhà hạng A tại trung tâm Quận 1','/images/the-sun-office.jpg','Chị Hạnh','0903000005'),(6,'Green View Building','120 Điện Biên Phủ','Phường 15',3,2,420,16,'16 triệu/m2/tháng','2 tháng','Thanh toán theo tháng','Tối thiểu 1 năm',1.00,'Không gian thoáng, gần khu dân cư','/images/green-view.jpg','Anh Dũng','0903000006'),(7,'Lotus Office Center','45 Trường Sơn','Phường 2',4,2,550,17,'17 triệu/m2/tháng','2 tháng','Thanh toán theo quý','Tối thiểu 1 năm',1.00,'Gần sân bay, phù hợp công ty logistics','/images/lotus-office.jpg','Chị Trang','0903000007'),(8,'Golden Land Tower','88 Pasteur','Phường Bến Nghé',1,3,700,28,'28 triệu/m2/tháng','3 tháng','Thanh toán theo quý','Tối thiểu 2 năm',1.50,'Tòa nhà mới, có hầm gửi xe rộng','/images/golden-land.jpg','Anh Phúc','0903000008'),(9,'River Gate Office','151 Bến Vân Đồn','Phường 6',1,2,620,24,'24 triệu/m2/tháng','3 tháng','Thanh toán theo quý','Tối thiểu 1 năm',1.20,'Gần trung tâm, phù hợp công ty dịch vụ','/images/river-gate-office.jpg','Anh Tuấn','0903000009'),(10,'Skyline Center','72 Nguyễn Cơ Thạch','Phường An Lợi Đông',7,3,900,26,'26 triệu/m2/tháng','3 tháng','Thanh toán theo quý','Tối thiểu 2 năm',1.50,'Khu đô thị mới, có sảnh lễ tân rộng','/images/skyline-center.jpg','Chị Vân','0903000010'),(11,'Central Park Building','208 Nguyễn Hữu Cảnh','Phường 22',3,2,480,19,'19 triệu/m2/tháng','2 tháng','Thanh toán theo tháng','Tối thiểu 1 năm',1.00,'Gần khu căn hộ cao cấp, tiện di chuyển','/images/central-park-building.jpg','Anh Khải','0903000011'),(12,'Maple Business Center','37 Hoàng Văn Thụ','Phường 15',5,1,360,15,'15 triệu/m2/tháng','2 tháng','Thanh toán theo quý','Tối thiểu 1 năm',1.00,'Phù hợp văn phòng đại diện nhỏ','/images/maple-business-center.jpg','Chị Phương','0903000012'),(13,'Saigon Pearl Office','92 Nguyễn Văn Linh','Phường Tân Phong',6,2,720,22,'22 triệu/m2/tháng','3 tháng','Thanh toán theo quý','Tối thiểu 2 năm',1.30,'Tòa nhà hiện đại, nhiều tiện ích xung quanh','/images/saigon-pearl-office.jpg','Anh Khoa','0903000013'),(14,'Harmony Tower','18 Cộng Hòa','Phường 4',4,2,510,16,'16 triệu/m2/tháng','2 tháng','Thanh toán theo tháng','Tối thiểu 1 năm',1.00,'Gần sân bay, phù hợp nhóm kinh doanh online','/images/harmony-tower.jpg','Chị Liên','0903000014'),(15,'Diamond Plaza Office','34 Lê Duẩn','Phường Bến Nghé',1,3,850,32,'32 triệu/m2/tháng','4 tháng','Thanh toán theo quý','Tối thiểu 2 năm',1.80,'Vị trí hạng A, hình ảnh chuyên nghiệp','/images/diamond-plaza-office.jpg','Anh Hải','0903000015'),(16,'Eco Space Building','65 Xa Lộ Hà Nội','Phường Thảo Điền',7,1,410,14,'14 triệu/m2/tháng','2 tháng','Thanh toán theo tháng','Tối thiểu 6 tháng',1.00,'Không gian xanh, phù hợp công ty sáng tạo','/images/eco-space-building.jpg','Chị Mai','0903000016'),(17,'Sunrise Business Hub','102 Nguyễn Oanh','Phường 7',8,1,320,13,'13 triệu/m2/tháng','2 tháng','Thanh toán theo tháng','Tối thiểu 6 tháng',1.00,'Phù hợp văn phòng nhỏ, chi phí hợp lý','/images/sunrise-business-hub.jpg','Anh Lâm','0903000017'),(18,'Grand View Tower','44 Võ Văn Kiệt','Phường Cô Giang',1,3,760,29,'29 triệu/m2/tháng','3 tháng','Thanh toán theo quý','Tối thiểu 2 năm',1.60,'Tầm nhìn đẹp, giao thông thuận tiện','/images/grand-view-tower.jpg','Chị Thảo','0903000018'),(19,'Phoenix Office','31 Nguyễn Thị Thập','Phường Tân Hưng',6,2,540,18,'18 triệu/m2/tháng','2 tháng','Thanh toán theo quý','Tối thiểu 1 năm',1.10,'Khu vực nhiều công ty, dễ tuyển nhân sự','/images/phoenix-office.jpg','Anh Quân','0903000019'),(20,'New City Building','220 Kinh Dương Vương','Phường An Lạc',9,1,400,12,'12 triệu/m2/tháng','2 tháng','Thanh toán theo tháng','Tối thiểu 1 năm',1.00,'Giá thuê tốt, phù hợp chi nhánh bán hàng','/images/new-city-building.jpg','Chị Yến','0903000020'),(21,'Innovation Tower','5 Nguyễn Gia Thiều','Phường Võ Thị Sáu',2,2,610,21,'21 triệu/m2/tháng','3 tháng','Thanh toán theo quý','Tối thiểu 1 năm',1.30,'Không gian hiện đại, phù hợp công ty công nghệ','/images/innovation-tower.jpg','Anh Minh','0903000021'),(22,'Metro Office House','77 Phổ Quang','Phường 2',4,1,350,15,'15 triệu/m2/tháng','2 tháng','Thanh toán theo tháng','Tối thiểu 6 tháng',1.00,'Gần tuyến đường lớn, dễ đón khách','/images/metro-office-house.jpg','Chị Hương','0903000022'),(23,'Vista Work Space','19 Ung Văn Khiêm','Phường 25',3,2,470,17,'17 triệu/m2/tháng','2 tháng','Thanh toán theo quý','Tối thiểu 1 năm',1.00,'Có khu vực làm việc linh hoạt','/images/vista-work-space.jpg','Anh Bảo','0903000023'),(24,'Royal Office Center','11 Đồng Khởi','Phường Bến Nghé',1,4,950,35,'35 triệu/m2/tháng','4 tháng','Thanh toán theo quý','Tối thiểu 2 năm',2.00,'Tòa nhà cao cấp, phù hợp doanh nghiệp lớn','/images/royal-office-center.jpg','Chị Ngân','0903000024');
/*!40000 ALTER TABLE `building` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buildingrenttype`
--

DROP TABLE IF EXISTS `buildingrenttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buildingrenttype` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `buildingid` bigint NOT NULL,
  `renttypeid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_buildingtype_building` (`buildingid`),
  KEY `fk_buildingtype_renttype` (`renttypeid`),
  CONSTRAINT `fk_buildingtype_building` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`),
  CONSTRAINT `fk_buildingtype_renttype` FOREIGN KEY (`renttypeid`) REFERENCES `renttype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buildingrenttype`
--

LOCK TABLES `buildingrenttype` WRITE;
/*!40000 ALTER TABLE `buildingrenttype` DISABLE KEYS */;
INSERT INTO `buildingrenttype` VALUES (1,1,2),(2,1,3),(3,2,2),(4,2,3),(5,3,1),(6,3,3),(7,4,1),(8,5,2),(9,5,3),(10,6,2),(11,7,2),(12,7,4),(13,8,2),(14,8,3),(15,9,2),(16,9,3),(17,10,2),(18,10,3),(19,10,4),(20,11,2),(21,11,3),(22,12,1),(23,12,3),(24,13,2),(25,13,3),(26,14,2),(27,14,4),(28,15,2),(29,15,3),(30,16,1),(31,16,4),(32,17,1),(33,17,4),(34,18,2),(35,18,3),(36,19,2),(37,19,3),(38,20,1),(39,20,2),(40,21,2),(41,21,3),(42,21,4),(43,22,1),(44,22,3),(45,23,2),(46,23,4),(47,24,2),(48,24,3);
/*!40000 ALTER TABLE `buildingrenttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Công ty TNHH Minh Long','0912000001','minhlong@gmail.com'),(2,'Nguyễn Thị Mai','0912000002','mai.nt@gmail.com'),(3,'Trần Quốc Việt','0912000003','viet.tq@gmail.com'),(4,'Công ty Cổ phần Sao Việt','0912000004','saoviet@gmail.com'),(5,'Lê Thanh Tùng','0912000005','tung.le@gmail.com'),(6,'Phạm Thu Hà','0912000006','ha.pt@gmail.com'),(7,'Công ty TNHH Green Home','0912000007','greenhome@gmail.com'),(8,'Hoàng Minh Đức','0912000008','duc.hm@gmail.com'),(9,'Vũ Ngọc Anh','0912000009','anh.vn@gmail.com'),(10,'Công ty TNHH Tech Future','0912000010','techfuture@gmail.com'),(11,'Công ty TNHH An Phát','0912000011','anphat@gmail.com'),(12,'Nguyễn Hoàng Nam','0912000012','nam.nguyen@gmail.com'),(13,'Công ty Cổ phần Blue Sea','0912000013','bluesea@gmail.com'),(14,'Trần Thị Bích Ngọc','0912000014','ngoc.tran@gmail.com'),(15,'Công ty TNHH Nova Tech','0912000015','novatech@gmail.com'),(16,'Phạm Quốc Hưng','0912000016','hung.pham@gmail.com'),(17,'Công ty TNHH Happy Land','0912000017','happyland@gmail.com'),(18,'Lê Minh Châu','0912000018','chau.le@gmail.com'),(19,'Công ty Cổ phần Việt Tín','0912000019','viettin@gmail.com'),(20,'Đặng Thanh Sơn','0912000020','son.dang@gmail.com'),(21,'Công ty TNHH Future Media','0912000021','futuremedia@gmail.com'),(22,'Nguyễn Minh Phúc','0912000022','phuc.nguyen@gmail.com'),(23,'Công ty Cổ phần Đông Dương','0912000023','dongduong@gmail.com'),(24,'Lê Thị Hồng Nhung','0912000024','nhung.le@gmail.com'),(25,'Công ty TNHH Smart Edu','0912000025','smartedu@gmail.com'),(26,'Hoàng Tuấn Kiệt','0912000026','kiet.hoang@gmail.com'),(27,'Công ty Cổ phần Mekong Digital','0912000027','mekongdigital@gmail.com'),(28,'Bùi Thanh Vy','0912000028','vy.bui@gmail.com'),(29,'Công ty TNHH Global Link','0912000029','globallink@gmail.com'),(30,'Phan Đức Anh','0912000030','anh.phan@gmail.com');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `district`
--

DROP TABLE IF EXISTS `district`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `district` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `district`
--

LOCK TABLES `district` WRITE;
/*!40000 ALTER TABLE `district` DISABLE KEYS */;
INSERT INTO `district` VALUES (1,'QUAN_1','Quận 1'),(2,'QUAN_3','Quận 3'),(3,'BINH_THANH','Quận Bình Thạnh'),(4,'TAN_BINH','Quận Tân Bình'),(5,'PHU_NHUAN','Quận Phú Nhuận'),(6,'QUAN_7','Quận 7'),(7,'THU_DUC','Thành phố Thủ Đức'),(8,'GO_VAP','Quận Gò Vấp'),(9,'BINH_TAN','Quận Bình Tân');
/*!40000 ALTER TABLE `district` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rentarea`
--

DROP TABLE IF EXISTS `rentarea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rentarea` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `value` int DEFAULT NULL,
  `buildingid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rentarea_building` (`buildingid`),
  CONSTRAINT `rentarea_building` FOREIGN KEY (`buildingid`) REFERENCES `building` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rentarea`
--

LOCK TABLES `rentarea` WRITE;
/*!40000 ALTER TABLE `rentarea` DISABLE KEYS */;
INSERT INTO `rentarea` VALUES (1,100,1),(2,200,1),(3,150,2),(4,300,2),(5,500,2),(6,100,3),(7,200,3),(8,80,4),(9,150,4),(10,200,5),(11,400,5),(12,600,5),(13,120,6),(14,250,6),(15,180,7),(16,350,7),(17,250,8),(18,500,8),(19,150,9),(20,300,9),(21,450,9),(22,250,10),(23,500,10),(24,750,10),(25,120,11),(26,240,11),(27,90,12),(28,180,12),(29,200,13),(30,400,13),(31,600,13),(32,150,14),(33,300,14),(34,250,15),(35,500,15),(36,700,15),(37,100,16),(38,200,16),(39,350,16),(40,80,17),(41,160,17),(42,240,17),(43,200,18),(44,400,18),(45,600,18),(46,150,19),(47,300,19),(48,450,19),(49,100,20),(50,200,20),(51,180,21),(52,360,21),(53,540,21),(54,90,22),(55,180,22),(56,270,22),(57,120,23),(58,240,23),(59,360,23),(60,250,24),(61,500,24),(62,800,24);
/*!40000 ALTER TABLE `rentarea` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `renttype`
--

DROP TABLE IF EXISTS `renttype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `renttype` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `renttype`
--

LOCK TABLES `renttype` WRITE;
/*!40000 ALTER TABLE `renttype` DISABLE KEYS */;
INSERT INTO `renttype` VALUES (1,'tang-tret','Tầng trệt'),(2,'nguyen-can','Nguyên căn'),(3,'noi-that','Nội thất'),(4,'van-phong-chia-se','Văn phòng chia sẻ');
/*!40000 ALTER TABLE `renttype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Quản lý','manager'),(2,'Nhân viên','staff'),(3,'Khách hàng','customer');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `note` varchar(255) DEFAULT NULL,
  `customerid` bigint NOT NULL,
  `type` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_customer_transaction` (`customerid`),
  KEY `fk_transactiontype_transaction` (`type`),
  CONSTRAINT `fk_customer_transaction` FOREIGN KEY (`customerid`) REFERENCES `customer` (`id`),
  CONSTRAINT `fk_transactiontype_transaction` FOREIGN KEY (`type`) REFERENCES `transactiontype` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'Đã gọi chăm sóc và ghi nhận nhu cầu thuê văn phòng 100-200m2 tại Quận 1.',1,1),(2,'Khách muốn xem ACM Tower vào chiều thứ sáu.',2,2),(3,'Đã tư vấn giá thuê Alpha Tower và gửi bảng diện tích còn trống.',3,3),(4,'Hẹn gặp trực tiếp đại diện công ty Sao Việt tại văn phòng.',4,4),(5,'Đã gửi báo giá The Sun Office qua email.',5,5),(6,'Khách quan tâm văn phòng gần sân bay, ngân sách khoảng 17 triệu/m2/tháng.',6,3),(7,'Green Home cần diện tích 250m2, ưu tiên khu Bình Thạnh.',7,1),(8,'Khách đã đồng ý đặt cọc Golden Land Tower.',8,6),(9,'Khách cần thêm hình ảnh và phí dịch vụ của Lotus Office Center.',9,5),(10,'Tech Future đang so sánh ACM Tower và Alpha Tower.',10,3),(11,'An Phát cần thuê văn phòng 300m2 tại khu vực Quận 1, ưu tiên có hầm xe.',11,3),(12,'Đã dẫn khách Nam đi xem River Gate Office và chờ phản hồi giá.',12,2),(13,'Blue Sea yêu cầu báo giá diện tích 400-600m2 tại Quận 7.',13,5),(14,'Khách Ngọc muốn xem văn phòng gần sân bay trong tuần này.',14,2),(15,'Nova Tech quan tâm Skyline Center, cần thêm thông tin phí quản lý.',15,3),(16,'Anh Hưng đã hẹn gặp trực tiếp để trao đổi hợp đồng thuê.',16,4),(17,'Happy Land cần mặt bằng tầng trệt, diện tích khoảng 100m2.',17,1),(18,'Khách Châu tạm hoãn nhu cầu thuê do thay đổi kế hoạch kinh doanh.',18,8),(19,'Việt Tín đã đồng ý phương án thuê Saigon Pearl Office.',19,6),(20,'Anh Sơn cần tư vấn thêm giữa Harmony Tower và Eco Space Building.',20,3),(21,'Future Media cần văn phòng sáng tạo, ưu tiên không gian mở tại Bình Thạnh.',21,3),(22,'Anh Phúc đã xem Grand View Tower và yêu cầu giảm giá thuê.',22,2),(23,'Đông Dương muốn ký hợp đồng nếu được giữ diện tích 300m2 trong 7 ngày.',23,7),(24,'Chị Nhung cần báo giá Metro Office House và phí gửi xe hằng tháng.',24,5),(25,'Smart Edu quan tâm Innovation Tower để mở trung tâm đào tạo.',25,1),(26,'Anh Kiệt muốn thuê New City Building vì gần kho hàng hiện tại.',26,3),(27,'Mekong Digital yêu cầu xem Vista Work Space vào sáng thứ hai.',27,2),(28,'Chị Vy chưa quyết định do ngân sách thấp hơn giá thuê hiện tại.',28,8),(29,'Global Link đang đàm phán gia hạn hợp đồng thuê văn phòng cũ.',29,9),(30,'Anh Đức Anh đã hoàn tất thanh lý hợp đồng thuê cũ.',30,10);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactiontype`
--

DROP TABLE IF EXISTS `transactiontype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactiontype` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactiontype`
--

LOCK TABLES `transactiontype` WRITE;
/*!40000 ALTER TABLE `transactiontype` DISABLE KEYS */;
INSERT INTO `transactiontype` VALUES (1,'Chăm sóc','cham-soc'),(2,'Dẫn đi xem','dan-di-xem'),(3,'Tư vấn','tu-van'),(4,'Hẹn gặp trực tiếp','hen-gap-truc-tiep'),(5,'Gửi báo giá','gui-bao-gia'),(6,'Chốt cọc','chot-coc'),(7,'Ký hợp đồng','ky-hop-dong'),(8,'Tạm ngưng chăm sóc','tam-ngung-cham-soc'),(9,'Gia hạn hợp đồng','gia-han-hop-dong'),(10,'Thanh lý hợp đồng','thanh-ly-hop-dong');
/*!40000 ALTER TABLE `transactiontype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `status` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'manager01','123456','Nguyễn Văn Quản','0901000001','manager01@gmail.com',1),(2,'staff01','123456','Trần Minh An','0901000002','staff01@gmail.com',1),(3,'staff02','123456','Lê Hoàng Bình','0901000003','staff02@gmail.com',1),(4,'staff03','123456','Phạm Gia Huy','0901000004','staff03@gmail.com',1),(5,'customer01','123456','Đỗ Minh Khang','0902000001','customer01@gmail.com',1),(6,'staff04','123456','Ngô Đức Thành','0901000006','staff04@gmail.com',1),(7,'staff05','123456','Bùi Nhật Linh','0901000007','staff05@gmail.com',1),(8,'customer02','123456','Nguyễn Hải Yến','0902000002','customer02@gmail.com',1),(9,'staff06','123456','Đỗ Khánh Duy','0901000009','staff06@gmail.com',1),(10,'staff07','123456','Võ Minh Trí','0901000010','staff07@gmail.com',1),(11,'customer03','123456','Trần Kim Chi','0902000003','customer03@gmail.com',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `roleid` bigint NOT NULL,
  `userid` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_role_user` (`roleid`),
  KEY `fk_user_role` (`userid`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1),(2,2,2),(3,2,3),(4,2,4),(5,3,5),(6,2,6),(7,2,7),(8,3,8),(9,2,9),(10,2,10),(11,3,11);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-05-09  3:05:11
