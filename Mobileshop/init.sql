create database mobileshopdb4;
-- MySQL dump 10.13  Distrib 5.5.28, for Win64 (x86)
--
-- Host: localhost    Database: mobileshopdb4
-- ------------------------------------------------------
-- Server version	5.5.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `authorities_ibfk_1` (`username`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authorities`
--

LOCK TABLES `authorities` WRITE;
/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
INSERT INTO `authorities` VALUES (1,'admin','ROLE_ADMIN'),(2,'john.doe@example.com','ROLE_CUSTOMER'),(3,'lov3u4ev3r97@gmail.com','ROLE_CUSTOMER'),(5,'Hungnguyen@gmail.com','ROLE_CUSTOMER'),(6,'test@gmail.com','ROLE_CUSTOMER'),(7,'jane.smith@example.com','ROLE_CUSTOMER'),(8,'bob.johnson@example.com','ROLE_CUSTOMER'),(9,'lovenevertolate97@gmail.com','ROLE_CUSTOMER'),(10,'HoangNam@gmail.com','ROLE_CUSTOMER'),(11,'emily.williams@example.com','ROLE_EMPLOYEE'),(12,'michael.johnson@example.com','ROLE_EMPLOYEE'),(14,'david.smith@example.com','ROLE_EMPLOYEE'),(15,'theanhnguyen@gmail.com','ROLE_CUSTOMER'),(16,'huynhnguyen@gmail.com','ROLE_EMPLOYEE'),(19,'NgiemHoangTuan@example.com','ROLE_EMPLOYEE'),(20,'Nguyennam1999@gmail.com','ROLE_CUSTOMER'),(22,'vuthuy270896@gmail.com','ROLE_CUSTOMER'),(23,'','ROLE_CUSTOMER');
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `brands`
--

DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brands` (
  `BrandID` int(11) NOT NULL AUTO_INCREMENT,
  `BrandName` varchar(255) NOT NULL,
  PRIMARY KEY (`BrandID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brands`
--

LOCK TABLES `brands` WRITE;
/*!40000 ALTER TABLE `brands` DISABLE KEYS */;
INSERT INTO `brands` VALUES (1,'Samsung'),(2,'Apple'),(3,'Huawei'),(4,'Google Pixel'),(5,'OnePlus'),(13,'Nokia'),(14,'Oppo');
/*!40000 ALTER TABLE `brands` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(255) NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (1,'Smartphones'),(2,'Tablets'),(3,'Accessories'),(4,'Laptop'),(6,'PC');
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colors`
--

DROP TABLE IF EXISTS `colors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `colors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) NOT NULL,
  `hex_code` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colors`
--

LOCK TABLES `colors` WRITE;
/*!40000 ALTER TABLE `colors` DISABLE KEYS */;
INSERT INTO `colors` VALUES (1,'Black','#000000'),(2,'Blue','#0000FF'),(3,'Bronze','#CD7F32'),(4,'Gold','#FFD700'),(5,'Gray','#808080'),(6,'Green','#00FF00'),(7,'Pink','#ffc0cb'),(8,'Purple','#800080'),(9,'Red','#FF0000'),(10,'Rose Gold','#ffc0cb'),(11,'Silver','#C0C0C0'),(12,'White','#FFFFFF');
/*!40000 ALTER TABLE `colors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `CustomerID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Email` varchar(255) NOT NULL,
  `Phone` varchar(20) NOT NULL,
  `Pass` varchar(50) NOT NULL,
  `Address` varchar(500) NOT NULL,
  `registration_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`CustomerID`),
  UNIQUE KEY `unique_email` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'Johny','Doe','john.doe@example.com','888-444-210-791','123456','123 Main St','2023-09-27 09:13:51'),(2,'Jane','Smith','jane.smith@example.com','987-654-3210','123456','456 Elm St','2023-09-27 09:13:56'),(3,'Bob','Johnson','bob.johnson@example.com','555-555-5555','123456','789 Oak St','2023-09-27 09:13:57'),(4,'HONAG','NGOC','lov3u4ev3r97@gmail.com','0084399036791','123456','diệt pháp, thiện phiến, tiên lữ','2023-09-27 01:27:41'),(5,'Hong','Quan','lovenevertolate97@gmail.com','0356911600','123','diệt pháp, thiện phiến, tiên lữ','2023-09-27 10:54:36'),(6,'Nam','Hoang','HoangNam@gmail.com','+843990363333','123','diệt pháp, thiện phiến, tiên lữ','2023-09-29 01:07:30'),(17,'Hung','Nguyen','Hungnguyen@gmail.com','+8439903633791','0123','số 12, hồ tùng mậu, hà nội','2023-09-29 04:57:15'),(18,'Test','test','test@gmail.com','123456789','123','Số 1, Tôn Thất Tùng, Hà Nội, Việt Nam','2023-10-05 01:20:48'),(19,'Nguyen','The Anh','theanhnguyen@gmail.com','026935691','123','Vĩnh Yên, Vĩnh Phúc, Việt Nam','2023-10-18 01:26:44'),(20,'Nguyen','Nam','Nguyennam1999@gmail.com','+84399036791','0399036791','số 42, phan chu trinh, TP Vĩnh Yên, Vĩnh Phúc','2023-10-25 02:58:25'),(22,'Vu','Thuy','vuthuy270896@gmail.com','096635694','123','minh khai, khai quang, tp vinh yen, vinh phuc','2023-11-02 13:27:50');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employees`
--

DROP TABLE IF EXISTS `employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `employees` (
  `EmployeeID` int(11) NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Email` varchar(255) NOT NULL,
  `Phone` varchar(20) DEFAULT NULL,
  `Pass` varchar(50) NOT NULL,
  `HireDate` timestamp NULL DEFAULT NULL,
  `HireEndDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employees`
--

LOCK TABLES `employees` WRITE;
/*!40000 ALTER TABLE `employees` DISABLE KEYS */;
INSERT INTO `employees` VALUES (1,'Michael','Johnson','michael.johnson@example.com','111-111-1112','123','2020-01-14 17:00:00','2024-04-01 01:44:37'),(2,'Emily','Williams','emily.williams@example.com','222-222-2222','123','2021-03-19 17:00:00',NULL),(3,'David','Smith','david.smith@example.com','333-333-3333','123','2022-05-09 17:00:00',NULL),(4,'Hoàng Tuấn','Nghiêm','NgiemHoangTuan@example.com','889-234-192','123','2020-04-10 17:00:00',NULL),(8,'huynh','nguyen','huynhnguyen@gmail.com','063695456','123','2023-10-18 01:36:23',NULL),(9,'Quan','Tran','admin','0399036791','123','2019-10-10 18:36:23',NULL);
/*!40000 ALTER TABLE `employees` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `models`
--

DROP TABLE IF EXISTS `models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `models` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `models`
--

LOCK TABLES `models` WRITE;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` VALUES (1,'iphone 14-2022-128-8-Black'),(2,'Samsung Galaxy S20-2020-256-8-Blue'),(3,'OnePlus 9-2021-128-8-White'),(4,'iphone 13-2021-256-8-Red'),(5,'Samsung Galaxy S21-2021-128-8-Purple'),(6,'OnePlus Nord-2020-128-6-Gray'),(7,'iphone 12-2020-64-4-Black'),(8,'Samsung Galaxy Note 20-2020-256-8-Bronze'),(9,'OnePlus 8T-2020-128-8-Silver'),(10,'iphone 11-2019-128-4-White'),(11,'Samsung Galaxy S10-2019-128-8-Green'),(12,'OnePlus 7 Pro-2019-256-12-Blue'),(13,'iphone X-2018-256-3-Black'),(14,'Samsung Galaxy Note 10-2018-256-8-Silver'),(15,'OnePlus 6T-2018-128-8-Red'),(16,'iphone 8-2017-64-2-Gray'),(17,'Samsung Galaxy S9-2018-64-4-Blue'),(18,'OnePlus 6-2018-128-6-Black'),(19,'iphone 7-2016-128-2-Silver'),(20,'Samsung Galaxy Note 9-2018-128-6-Blue'),(21,'OnePlus 5T-2017-64-6-Red'),(22,'iphone 6S-2015-32-2-Gray'),(23,'Samsung Galaxy S8-2017-64-4-Gold'),(24,'OnePlus 5-2017-64-6-Black'),(25,'iphone 6-2014-32-2-Silver'),(26,'Huawei Note 8-2017-64-6-Blue'),(27,'iphone 15 pro-2016-64-6-Black'),(28,'iphone SE-2016-32-2-Rose Gold'),(29,'iphone 11 pro-2016-32-4-Black'),(30,'OnePlus 3-2016-64-6-Silver'),(31,'iPhone 15-1890-64-3-Pink'),(32,'Huawie nova i5-2021-256-8-Blue'),(36,'iphone test5-2024-256-6-Gray'),(37,'iphone 15 plus-2023-128-8-Black'),(38,'iphone 15 pro-2023-256-8-Black'),(39,'iphone 15 pro max-2023-256-12-Black');
/*!40000 ALTER TABLE `models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operatingsystems`
--

DROP TABLE IF EXISTS `operatingsystems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operatingsystems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `os_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operatingsystems`
--

LOCK TABLES `operatingsystems` WRITE;
/*!40000 ALTER TABLE `operatingsystems` DISABLE KEYS */;
INSERT INTO `operatingsystems` VALUES (1,'Android'),(2,'IOS'),(3,'Tizen'),(4,'Windown');
/*!40000 ALTER TABLE `operatingsystems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitems`
--

DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitems` (
  `OrderItemID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) DEFAULT NULL,
  `PhoneID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `seri` varchar(255) DEFAULT NULL,
  `price` decimal(20,0) DEFAULT '0',
  `missing` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT 'Duyệt',
  PRIMARY KEY (`OrderItemID`),
  KEY `unique_model_idx` (`seri`),
  KEY `orderitems_ibfk_1_idx` (`OrderID`),
  KEY `fk_orderitems_phones_new` (`PhoneID`),
  CONSTRAINT `fk_orderitems_phones_new` FOREIGN KEY (`PhoneID`) REFERENCES `phones` (`PhoneID`) ON DELETE CASCADE,
  CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitems`
--

LOCK TABLES `orderitems` WRITE;
/*!40000 ALTER TABLE `orderitems` DISABLE KEYS */;
INSERT INTO `orderitems` VALUES (77,43,3,2,'[864209753, 112233445]',17490000,0,'Đã hoàn thành'),(78,43,1,2,'[157482936, 294785613]',19990000,0,'Đã hoàn thành'),(79,44,3,2,'[864209756, 112233446]',17490000,0,'Đã hoàn thành'),(80,45,2,3,'[753190642, 421087365, 975310864]',17490000,0,'Chờ duyệt'),(85,50,5,2,'[519873264, 872643091]',19990000,0,'Chờ duyệt'),(97,62,11,2,'[375281964, 987654321]',12490000,0,'Đã hoàn thành'),(99,64,7,1,'[871539264]',17490000,0,'Đã hủy'),(100,65,17,1,'[395817264]',9990000,0,NULL);
/*!40000 ALTER TABLE `orderitems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orderitemsimport`
--

DROP TABLE IF EXISTS `orderitemsimport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitemsimport` (
  `OrderItemImportID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderImportID` int(11) DEFAULT NULL,
  `PhoneID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `seri` varchar(255) DEFAULT NULL,
  `priceImport` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`OrderItemImportID`),
  KEY `orderitems_ibfk_2` (`OrderImportID`),
  KEY `orderitem_phone_fk_2_idx` (`PhoneID`),
  CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`OrderImportID`) REFERENCES `ordersimport` (`OrderImportID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orderitem_phone_fk_2` FOREIGN KEY (`PhoneID`) REFERENCES `phones` (`PhoneID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orderitemsimport`
--

LOCK TABLES `orderitemsimport` WRITE;
/*!40000 ALTER TABLE `orderitemsimport` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderitemsimport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(11) DEFAULT NULL,
  `OrderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateprocessed` timestamp NULL DEFAULT NULL,
  `PaymentMethodID` int(11) NOT NULL,
  `Amount` decimal(20,0) NOT NULL,
  `NumberOrAddressPayment` varchar(255) NOT NULL,
  `CVV` varchar(10) DEFAULT NULL,
  `ExpirationDate` varchar(10) DEFAULT NULL,
  `EmployeeID` int(11) DEFAULT NULL,
  `status` varchar(45) NOT NULL DEFAULT 'Duyệt',
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `fk_paymentmethod` (`PaymentMethodID`),
  KEY `fk_Order_Employees` (`EmployeeID`),
  CONSTRAINT `fk_Order_Employees` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`),
  CONSTRAINT `fk_paymentmethod` FOREIGN KEY (`PaymentMethodID`) REFERENCES `paymentmethods` (`PaymentMethodID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (43,17,'2023-10-18 23:50:05','2023-12-19 09:43:16',1,37480000,'','','',9,'Đã hoàn thành'),(44,5,'2023-10-19 02:04:05','2023-10-20 06:55:51',1,17490000,'','','',9,'Đã hoàn thành'),(45,20,'2023-10-25 02:58:25','0000-00-00 00:00:00',1,17490000,'','','',NULL,'Chờ duyệt'),(50,22,'2023-11-02 13:27:50','0000-00-00 00:00:00',1,19990000,'','','',NULL,'Chờ duyệt'),(62,5,'2024-03-26 07:57:11','2024-03-26 08:49:16',1,12490000,'','','',4,'Đã hoàn thành'),(64,4,'2024-04-01 14:06:38','2024-04-01 14:07:05',1,17490000,'','','',NULL,'Đã hủy'),(65,4,'2024-04-01 15:42:59','2024-04-01 15:43:35',1,9990000,'','','',9,'Đã hoàn thành');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordersimport`
--

DROP TABLE IF EXISTS `ordersimport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordersimport` (
  `OrderImportID` int(11) NOT NULL AUTO_INCREMENT,
  `EmployeeID` int(11) DEFAULT NULL,
  `OrderDateImport` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `Amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`OrderImportID`),
  KEY `fk_ordersImport_Employees` (`EmployeeID`),
  CONSTRAINT `fk_ordersImport_Employees` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordersimport`
--

LOCK TABLES `ordersimport` WRITE;
/*!40000 ALTER TABLE `ordersimport` DISABLE KEYS */;
/*!40000 ALTER TABLE `ordersimport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymentmethods`
--

DROP TABLE IF EXISTS `paymentmethods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentmethods` (
  `PaymentMethodID` int(11) NOT NULL AUTO_INCREMENT,
  `MethodName` varchar(255) NOT NULL,
  PRIMARY KEY (`PaymentMethodID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymentmethods`
--

LOCK TABLES `paymentmethods` WRITE;
/*!40000 ALTER TABLE `paymentmethods` DISABLE KEYS */;
INSERT INTO `paymentmethods` VALUES (1,'Cash'),(2,'Credit'),(3,'Debit'),(4,'PayPal'),(5,'MoMo'),(6,'ZaloPay'),(7,'Shopee'),(8,'ApplePay'),(9,'SamsungPay');
/*!40000 ALTER TABLE `paymentmethods` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_logins`
--

DROP TABLE IF EXISTS `persistent_logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_logins`
--

LOCK TABLES `persistent_logins` WRITE;
/*!40000 ALTER TABLE `persistent_logins` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phones`
--

DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phones` (
  `PhoneID` int(11) NOT NULL AUTO_INCREMENT,
  `BrandID` int(11) DEFAULT NULL,
  `CategoryID` int(11) DEFAULT NULL,
  `PhoneName` varchar(255) DEFAULT NULL,
  `ModelID` int(11) DEFAULT NULL,
  `ReleaseYear` int(11) DEFAULT NULL,
  `ScreenSize` decimal(4,2) DEFAULT NULL,
  `StorageCapacityID` int(11) DEFAULT NULL,
  `RAMID` int(11) DEFAULT NULL,
  `OperatingSystemID` int(11) DEFAULT NULL,
  `Price` decimal(20,0) DEFAULT NULL,
  `ColorID` int(11) DEFAULT NULL,
  `ImageName` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `seri` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`PhoneID`),
  KEY `FK_Phones_Brands2` (`BrandID`),
  KEY `FK_Phones_Categories2` (`CategoryID`),
  KEY `FK_Phones_Models2` (`ModelID`),
  KEY `FK_Phones_StorageCapacities2` (`StorageCapacityID`),
  KEY `FK_Phones_RAM2` (`RAMID`),
  KEY `FK_Phones_OperatingSystems2` (`OperatingSystemID`),
  KEY `FK_Phones_Colors2` (`ColorID`),
  CONSTRAINT `FK_Phones_Brands2` FOREIGN KEY (`BrandID`) REFERENCES `brands` (`BrandID`),
  CONSTRAINT `FK_Phones_Categories2` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`),
  CONSTRAINT `FK_Phones_Colors2` FOREIGN KEY (`ColorID`) REFERENCES `colors` (`id`),
  CONSTRAINT `FK_Phones_Models2` FOREIGN KEY (`ModelID`) REFERENCES `models` (`id`),
  CONSTRAINT `FK_Phones_OperatingSystems2` FOREIGN KEY (`OperatingSystemID`) REFERENCES `operatingsystems` (`id`),
  CONSTRAINT `FK_Phones_RAM2` FOREIGN KEY (`RAMID`) REFERENCES `ram` (`id`),
  CONSTRAINT `FK_Phones_StorageCapacities2` FOREIGN KEY (`StorageCapacityID`) REFERENCES `storagecapacities` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phones`
--

LOCK TABLES `phones` WRITE;
/*!40000 ALTER TABLE `phones` DISABLE KEYS */;
INSERT INTO `phones` VALUES (1,2,1,'iphone 14',1,2022,6.30,4,4,2,19990000,1,'iphone 14 black.png',0,'[]'),(2,1,2,'Samsung Galaxy S20',2,2020,6.20,5,4,1,17490000,2,'Samsung Galaxy Note 20 Mystic Bronze.png',0,'[]'),(3,3,1,'OnePlus 9',3,2021,6.50,4,4,1,17490000,12,'oneplus 9-white.png',0,'[]'),(4,2,2,'iphone 13',4,2021,6.10,5,4,2,22490000,9,'iPhone 13.jpg',4,'[753190628, 410987365, 187654329, 210987654]'),(5,1,1,'Samsung Galaxy S21',5,2021,6.40,4,4,1,19990000,8,'Samsung Galaxy S21 black.jpg',0,'[]'),(6,5,1,'OnePlus Nord',6,2020,6.40,4,3,1,12490000,5,'OnePlus Nord Gray.PNG',5,'[284619799, 572937801, 329174856, 654728913, 864209799]'),(7,2,3,'iphone 12',7,2020,6.10,3,2,2,17490000,1,'iphone-12-white.jpg',3,'[871539264, 527346198, 493175628]'),(8,1,1,'Samsung Galaxy Note 20',8,2020,6.70,5,4,1,19990000,3,'Samsung Galaxy Note 20 Mystic Bronze.png',2,'[157482937, 428671935]'),(9,3,2,'OnePlus 8T',9,2020,6.50,4,4,1,14990000,11,'Google Pixel 3 black.jpg',4,'[619753284, 578349162, 293746185, 462891357]'),(10,2,1,'iphone 11',10,2019,6.10,4,2,2,14990000,12,'se-2016-black.png',1,'[182736495]'),(11,1,1,'Samsung Galaxy S10',11,2019,6.10,4,4,1,12490000,6,'samsung galaxy s10 green.png',1,'[996852314]'),(12,3,1,'OnePlus 7 Pro',12,2019,6.60,5,5,1,17490000,2,'Google Pixel 4 Orange.png',2,'[193746582, 574839216]'),(13,2,1,'iphone X',13,2018,5.80,5,4,2,14990000,1,'iPhone_XR_white.png',5,'[295836471, 614729835, 571849236, 124637598, 352816479]'),(14,1,1,'Samsung Galaxy Note 10',14,2018,6.30,5,4,1,17490000,11,'rog.png',2,'[193846572, 485629713]'),(15,3,1,'OnePlus 6T',15,2018,6.40,4,4,1,12490000,9,'Google Pixel 5.png',3,'[627195384, 591384762, 746219538]'),(16,2,1,'iphone 8',16,2017,4.70,3,1,2,9990000,5,'se-2016-black.png',4,'[183749265, 572639184, 628473195, 475923618]'),(17,1,1,'Samsung Galaxy S9',17,2018,5.80,3,2,1,9990000,2,'Google-Pixel-6-Black_600x.jpg',2,'[496731582, 817246395]'),(18,3,1,'OnePlus 6',18,2018,6.20,4,3,1,12490000,1,'Huawei M.png',2,'[618472935, 215847639]'),(19,2,1,'iphone 7',19,2016,4.70,4,1,2,7490000,11,'header_iphone_7_plus_black_large_2x.png',5,'[294857163, 528716394, 187539642, 863951247, 952743816]'),(20,1,1,'Samsung Galaxy Note 9',20,2018,6.40,4,3,1,14990000,2,'Samsung_Galaxy_A52_Blue.png',3,'[725183946, 915628347, 862741935]'),(21,3,1,'OnePlus 5T',21,2017,6.00,3,3,1,9990000,9,'vn-galaxy-s23-ultra.PNG',2,'[165493728, 485721936]'),(22,2,1,'iphone 6S',22,2015,4.70,2,1,2,4990000,5,'header_iphone_8_silver_small_2x.png',4,'[285716394, 654729183, 745928163, 429871365]'),(23,1,1,'Samsung Galaxy S8',23,2017,5.80,3,2,1,9990000,4,'samsung galaxy s10 green.png',3,'[391872654, 752836941, 648371925]'),(24,3,1,'OnePlus 5',24,2017,5.50,3,3,1,9990000,1,'rog.png',2,'[194867253, 837429165]'),(25,2,1,'iphone 6',25,2014,4.70,2,1,2,3990000,11,'0000600_iphone-se-2022_240.png',5,'[295847163, 751982634, 819374652, 210398765, 317965824]'),(26,3,1,'Huawei Note 8',26,2017,6.30,3,3,1,12490000,2,'Huawei M.png',3,'[193475826, 518297364, 861937245]'),(27,3,1,'iphone 15 pro',27,2016,5.50,3,3,2,7490000,1,'iphone 15 pro 512GB VNA.png',2,'[794613528, 218946375]'),(28,2,1,'iphone SE',28,2016,4.00,2,1,2,4990000,10,'iPhone SE (2nd Gen).png',4,'[481957362, 317965824, 429178365, 538219647]'),(29,2,1,'iphone 11 pro',29,2016,5.10,2,2,2,7490000,1,'11pro-Gray.png',3,'[579432816, 218946357, 648271935]'),(30,3,1,'OnePlus 3',30,2016,5.50,3,3,1,7490000,11,'Google Pixel 4a Black.png',2,'[179463825, 538219674]'),(31,2,1,'iPhone 15',31,1890,6.30,3,6,2,19990000,7,'iphone-15-pink.png',2,'[555555555, 555555556]'),(32,3,1,'Huawie nova i5',32,2021,6.40,5,4,1,32490000,2,'Huawei Nova 5i.jpg',1,'[459997002]'),(33,2,1,'iphone 15 plus',37,2023,6.40,4,4,2,49990000,1,'iphone 15 plus.png',1,'[939384849]'),(34,2,1,'iphone 15 pro',38,2023,6.10,5,4,2,58390000,1,'iphone-15-pro-max-titanium-black-front-back.png',1,'[1092874544]'),(35,2,1,'iphone 15 pro max',39,2023,6.80,5,5,2,77490000,1,'15-PRO-MAX-XANH-TITAN-.png',1,'[889394929]');
/*!40000 ALTER TABLE `phones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productreviews`
--

DROP TABLE IF EXISTS `productreviews`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productreviews` (
  `ReviewID` int(11) NOT NULL AUTO_INCREMENT,
  `PhoneID` int(11) DEFAULT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `Rating` int(11) NOT NULL,
  `Comment` varchar(1000) DEFAULT NULL,
  `ReviewDate` datetime NOT NULL,
  PRIMARY KEY (`ReviewID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `fk_productreviews_phones_new` (`PhoneID`),
  CONSTRAINT `fk_productreviews_phones_new` FOREIGN KEY (`PhoneID`) REFERENCES `phones` (`PhoneID`) ON DELETE CASCADE,
  CONSTRAINT `productreviews_ibfk_2` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productreviews`
--

LOCK TABLES `productreviews` WRITE;
/*!40000 ALTER TABLE `productreviews` DISABLE KEYS */;
INSERT INTO `productreviews` VALUES (2,2,17,5,'helooo test','2023-11-29 15:06:38'),(3,2,5,5,'test tiếp','2023-11-29 15:31:22'),(7,17,18,5,'Điện thoại đẹp lắm! ủng hộ shop','2023-11-29 16:07:57'),(8,10,18,5,'Điện thoại cổ nhưng mà dùng khá tốt! cơ bản đáp ứng được','2023-11-29 16:37:00'),(9,10,5,3,'Tạm ổn','2023-11-29 17:00:35'),(10,2,17,5,'điện thoại đẹp, dùng tốt','2023-11-29 19:23:52'),(11,5,17,5,'đẹp, có ở Long An ko?','2023-11-29 19:24:44'),(12,11,17,4,'Màu này đẹp quá','2023-11-29 19:25:34'),(13,11,17,4,'rất phù hợp giá tiền','2023-11-29 19:25:43'),(14,3,20,5,'bản này màu đẹp quá','2023-11-29 19:26:06'),(15,12,20,5,'Sai ảnh rồi','2023-11-29 19:26:28'),(16,17,6,5,'Để sai hình ảnh r shop','2023-12-12 10:43:19');
/*!40000 ALTER TABLE `productreviews` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ram`
--

DROP TABLE IF EXISTS `ram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ram` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ram`
--

LOCK TABLES `ram` WRITE;
/*!40000 ALTER TABLE `ram` DISABLE KEYS */;
INSERT INTO `ram` VALUES (1,'2'),(2,'4'),(3,'6'),(4,'8'),(5,'12'),(6,'16'),(7,'20'),(8,'32'),(9,'48');
/*!40000 ALTER TABLE `ram` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `storagecapacities`
--

DROP TABLE IF EXISTS `storagecapacities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storagecapacities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `storagecapacities`
--

LOCK TABLES `storagecapacities` WRITE;
/*!40000 ALTER TABLE `storagecapacities` DISABLE KEYS */;
INSERT INTO `storagecapacities` VALUES (1,'16'),(2,'32'),(3,'64'),(4,'128'),(5,'256'),(6,'512'),(7,'1024'),(8,'2048'),(9,'3072');
/*!40000 ALTER TABLE `storagecapacities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `UserAccountID` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `CustomerID` int(11) DEFAULT NULL,
  `enabled` tinyint(2) DEFAULT '1',
  PRIMARY KEY (`UserAccountID`),
  UNIQUE KEY `unique_username` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john.doe@example.com','$2a$10$3EiQ6Czlp1veHfFKS1jOV.Wi5tJNv.QP6HF0ApvFuFUdgLOPyS3Ry',1,1),(2,'admin','$2a$10$M/gLdDUhNc0K4.D9X/eXmOabwCJhbK1wqGmUcAnj0gezcEqzjx2DS',NULL,1),(4,'lov3u4ev3r97@gmail.com','$2a$10$dbPJE6iB9w82vZnldAVSduGp8KuOSzNhBzVXMlm5oPrOCGW4pjXmi',4,1),(7,'Hungnguyen@gmail.com','$2a$10$9zsV0xjgQQ37eiLhD2pA3ufwXjcvMxZJQyYRpzUNjrcKjzY1plXSK',17,1),(8,'test@gmail.com','$2a$10$XFjPrrVtZ330VxyfTUpTfuZ8ocuzhqgiWwh0EPvfE9k40xCamjgUC',18,1),(9,'jane.smith@example.com','$2a$10$mQITU3/WVnuy6RwA5J8GS.HWvE9539Wv.fDfM3yw.D5xNzQXHbJru',2,1),(10,'bob.johnson@example.com','$2a$10$mQITU3/WVnuy6RwA5J8GS.HWvE9539Wv.fDfM3yw.D5xNzQXHbJru',3,1),(11,'lovenevertolate97@gmail.com','$2a$10$0M5EfbizvMiBpm9w.6Dpuu.YXW3sClsgJPhf4EG44Xs1g5Y6P1DeK',5,1),(12,'HoangNam@gmail.com','$2a$10$DtsWOgQmWvx/RwysYRvhqus/VFufFVglIOWpDoY2tgeY3o8bZMq1W',6,1),(13,'emily.williams@example.com','$2a$10$dwNMTAq//R.7S8TCt.kE0.f5dW9UiXq9KbdYqgvHzueKnvu4lJooq',2,1),(14,'michael.johnson@example.com','$2a$10$dCt8O0V0ukMdg/2zyUx/XOWNJyuFk5jFDw95E0VubHbZUwWT/sR4y',1,1),(16,'david.smith@example.com','$2a$10$RNseAkdguaWeeun5Mdf/juIqmjNedO1PA1.si/eDEYJ4Ci0UDrL7i',3,1),(17,'theanhnguyen@gmail.com','$2a$10$QOyjiKDgVCGuGMJ47f4/M.h3JswTGolynjlkUnACo5dkrJ5cyoCbC',19,1),(18,'huynhnguyen@gmail.com','$2a$10$eosLA/PgOYh757s6ZP37buTwuj8YXGwgHCqIew9bEZFRRdZOOt6KG',8,1),(22,'NgiemHoangTuan@example.com','$2a$10$XB6ttNQXs7U6RQOX47s7n.Vc8CwDuZ5AzfwbSXXjot.aS7SK6Qv3y',4,1),(23,'Nguyennam1999@gmail.com','$2a$10$7UyhDKyqtJQrBUsG5BouneVbqYSTY0DU.9ns//hRGaRMQj0vgwDGi',20,1),(25,'vuthuy270896@gmail.com','$2a$10$7lAfr0AMmYjXo/P5aAdcEeXXOiRBV3zpMb5zsARdfkfB5k8ZFS9qG',22,1),(26,'','$2a$10$eRuwdQxcdOVCFCqPSyGtO.1asRMOPwjwbAFknpgcGAnBV8aZLcab6',23,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wish`
--

DROP TABLE IF EXISTS `wish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wish` (
  `WhisID` int(11) NOT NULL,
  `CustomerID` int(11) NOT NULL,
  `PhoneID` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  PRIMARY KEY (`WhisID`),
  KEY `FK_Cart_Customers` (`CustomerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wish`
--

LOCK TABLES `wish` WRITE;
/*!40000 ALTER TABLE `wish` DISABLE KEYS */;
/*!40000 ALTER TABLE `wish` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-12 10:43:16
