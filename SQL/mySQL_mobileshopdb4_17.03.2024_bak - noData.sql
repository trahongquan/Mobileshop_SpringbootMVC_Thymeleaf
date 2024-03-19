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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `brands`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `brands` (
  `BrandID` int(11) NOT NULL AUTO_INCREMENT,
  `BrandName` varchar(255) NOT NULL,
  PRIMARY KEY (`BrandID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `CategoryName` varchar(255) NOT NULL,
  PRIMARY KEY (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
  PRIMARY KEY (`EmployeeID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `models` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `operatingsystems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operatingsystems` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `os_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `orderitems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orderitems` (
  `OrderItemID` int(11) NOT NULL AUTO_INCREMENT,
  `OrderID` int(11) DEFAULT NULL,
  `PhoneID` int(11) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `seri` varchar(255) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT '0.00',
  `missing` int(11) DEFAULT NULL,
  PRIMARY KEY (`OrderItemID`),
  KEY `unique_model_idx` (`seri`),
  KEY `orderitems_ibfk_1_idx` (`OrderID`),
  KEY `orderitem_phone_fk_1_idx` (`PhoneID`),
  CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`OrderID`) REFERENCES `orders` (`OrderID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



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
  KEY `orderitem_phone_fk_2` (`PhoneID`),
  CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`OrderImportID`) REFERENCES `ordersimport` (`OrderImportID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `orderitem_phone_fk_2` FOREIGN KEY (`PhoneID`) REFERENCES `phones` (`PhoneID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `OrderID` int(11) NOT NULL AUTO_INCREMENT,
  `CustomerID` int(11) DEFAULT NULL,
  `OrderDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateprocessed` timestamp NULL DEFAULT NULL,
  `PaymentMethodID` int(11) NOT NULL,
  `Amount` decimal(10,2) NOT NULL,
  `NumberOrAddressPayment` varchar(255) NOT NULL,
  `CVV` varchar(10) DEFAULT NULL,
  `ExpirationDate` varchar(10) DEFAULT NULL,
  `EmployeeID` int(11) DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  KEY `CustomerID` (`CustomerID`),
  KEY `fk_paymentmethod` (`PaymentMethodID`),
  KEY `fk_Order_Employees` (`EmployeeID`),
  CONSTRAINT `fk_Order_Employees` FOREIGN KEY (`EmployeeID`) REFERENCES `employees` (`EmployeeID`),
  CONSTRAINT `fk_paymentmethod` FOREIGN KEY (`PaymentMethodID`) REFERENCES `paymentmethods` (`PaymentMethodID`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



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


DROP TABLE IF EXISTS `paymentmethods`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymentmethods` (
  `PaymentMethodID` int(11) NOT NULL AUTO_INCREMENT,
  `MethodName` varchar(255) NOT NULL,
  PRIMARY KEY (`PaymentMethodID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



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



DROP TABLE IF EXISTS `phones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phones` (
  `PhoneID` int(11) NOT NULL AUTO_INCREMENT,
  `BrandID` int(11) DEFAULT NULL,
  `CategoryID` int(11) DEFAULT NULL,
  `PhoneName` varchar(255) DEFAULT NULL,
  `Model` varchar(255) DEFAULT NULL,
  `ReleaseYear` int(11) DEFAULT NULL,
  `ScreenSize` decimal(4,2) DEFAULT NULL,
  `StorageCapacity` int(11) DEFAULT NULL,
  `RAM` int(11) DEFAULT NULL,
  `OperatingSystem` varchar(50) DEFAULT NULL,
  `Price` decimal(10,2) DEFAULT NULL,
  `Color` varchar(50) DEFAULT NULL,
  `ImageName` varchar(255) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `seri` varchar(10000) DEFAULT NULL,
  PRIMARY KEY (`PhoneID`),
  KEY `FK_Phones_Brands` (`BrandID`),
  KEY `FK_Phones_Categories` (`CategoryID`),
  CONSTRAINT `FK_Phones_Brands` FOREIGN KEY (`BrandID`) REFERENCES `brands` (`BrandID`),
  CONSTRAINT `FK_Phones_Categories` FOREIGN KEY (`CategoryID`) REFERENCES `categories` (`CategoryID`)
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `phones_new`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `phones_new` (
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
  `Price` decimal(10,2) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
  KEY `PhoneID` (`PhoneID`),
  KEY `CustomerID` (`CustomerID`),
  CONSTRAINT `productreviews_ibfk_1` FOREIGN KEY (`PhoneID`) REFERENCES `phones` (`PhoneID`),
  CONSTRAINT `productreviews_ibfk_2` FOREIGN KEY (`CustomerID`) REFERENCES `customers` (`CustomerID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `ram`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ram` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS `storagecapacities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `storagecapacities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `capacity` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;



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


DROP TABLE IF EXISTS `zz`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zz` (
  `PhoneID` int(11) DEFAULT NULL,
  `BrandID` int(11) DEFAULT NULL,
  `CategoryID` int(11) DEFAULT NULL,
  `PhoneName` text,
  `ModelID` int(11) DEFAULT NULL,
  `ReleaseYear` int(11) DEFAULT NULL,
  `ScreenSize` double DEFAULT NULL,
  `StorageCapacityID` int(11) DEFAULT NULL,
  `RAMID` int(11) DEFAULT NULL,
  `OperatingSystemID` int(11) DEFAULT NULL,
  `Price` int(11) DEFAULT NULL,
  `ColorID` int(11) DEFAULT NULL,
  `ImageName` text,
  `quantity` int(11) DEFAULT NULL,
  `seri` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

