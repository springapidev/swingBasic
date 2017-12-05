-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.35-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema inventory
--

CREATE DATABASE IF NOT EXISTS inventory;
USE inventory;

--
-- Definition of table `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `product_code` varchar(45) DEFAULT NULL,
  `unitPrice` int(11) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `totalPrice` int(11) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `purchase_date` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`,`name`,`product_code`,`unitPrice`,`qty`,`totalPrice`,`category`,`purchase_date`) VALUES 
 (2,'sari','1000',1200,200,240000,'Jamdani','11-11-2017'),
 (3,'sari','1000',1200,20,40000,'Jamdani','12-11-2017');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;


--
-- Definition of table `productsummary`
--

DROP TABLE IF EXISTS `productsummary`;
CREATE TABLE `productsummary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(45) DEFAULT NULL,
  `purchase_qty` int(11) DEFAULT NULL,
  `sold_qty` int(11) DEFAULT NULL,
  `avilable_qty` int(11) DEFAULT NULL,
  `product_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `productsummary`
--

/*!40000 ALTER TABLE `productsummary` DISABLE KEYS */;
INSERT INTO `productsummary` (`id`,`product_code`,`purchase_qty`,`sold_qty`,`avilable_qty`,`product_name`) VALUES 
 (1,'1000',220,11,209,'sari');
/*!40000 ALTER TABLE `productsummary` ENABLE KEYS */;


--
-- Definition of table `sales_details`
--

DROP TABLE IF EXISTS `sales_details`;
CREATE TABLE `sales_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) DEFAULT NULL,
  `product_code` varchar(45) DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  `unit_price` int(11) DEFAULT NULL,
  `total_price` varchar(45) DEFAULT NULL,
  `category` varchar(45) DEFAULT NULL,
  `sales_date` varchar(45) DEFAULT NULL,
  `customer_name` varchar(45) DEFAULT NULL,
  `customer_mobile` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sales_details`
--

/*!40000 ALTER TABLE `sales_details` DISABLE KEYS */;
INSERT INTO `sales_details` (`id`,`product_name`,`product_code`,`qty`,`unit_price`,`total_price`,`category`,`sales_date`,`customer_name`,`customer_mobile`) VALUES 
 (1,'sari','1000',3,2500,'7500','','15-11-2017','A122','4546565'),
 (2,'sari','1000',3,2600,'7800','','16-11-2017','gd','3434'),
 (3,'sari','1000',5,2100,'10500','','16-11-2017','gfgf','47563456');
/*!40000 ALTER TABLE `sales_details` ENABLE KEYS */;


--
-- Definition of table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `mobile` varchar(45) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `user_type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`,`name`,`email`,`mobile`,`username`,`password`,`user_type`) VALUES 
 (1,'Urmi','urmi@gmail.com','455454','urmi','urmi','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
