-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.58-log


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
-- Definition of table `itemseligibleforoder`
--

DROP TABLE IF EXISTS `itemseligibleforoder`;
CREATE TABLE `itemseligibleforoder` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `product_code` varchar(45) NOT NULL,
  `purchase_qty` int(10) unsigned NOT NULL,
  `sold_qty` int(10) unsigned NOT NULL,
  `avilable_qty` int(10) unsigned NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `orderQty` int(10) unsigned NOT NULL,
  `orderStatus` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `itemseligibleforoder`
--

/*!40000 ALTER TABLE `itemseligibleforoder` DISABLE KEYS */;
INSERT INTO `itemseligibleforoder` (`id`,`product_code`,`purchase_qty`,`sold_qty`,`avilable_qty`,`product_name`,`orderQty`,`orderStatus`) VALUES 
 (10,'nok1200',11,7,4,'Nokia 1200',8,'Pending'),
 (11,'samj7',19,11,8,'Samsung J7',3,'Pending'),
 (12,'nok1200',11,7,4,'Nokia 1200',8,'Pending'),
 (13,'samj7',19,11,8,'Samsung J7',3,'Pending'),
 (14,'samj5',5,3,2,'Samsung J5',10,'Pending'),
 (15,'nok1200',11,7,4,'Nokia 1200',8,'Pending'),
 (16,'samj2',5,3,2,'Samsung J2',8,'Pending'),
 (17,'samj7',19,11,8,'Samsung J7',3,'Pending'),
 (18,'samj5',5,3,2,'Samsung J5',10,'Pending'),
 (19,'nok1200',11,7,4,'Nokia 1200',8,'Pending'),
 (20,'samj2',5,3,2,'Samsung J2',8,'Pending'),
 (21,'samSDOUS',5,4,1,'Samsung SDOUS',12,'Pending'),
 (22,'samj7',19,11,8,'Samsung J7',3,'Pending'),
 (23,'samj5',5,3,2,'Samsung J5',10,'Pending'),
 (24,'nok1200',11,7,4,'Nokia 1200',8,'Pending'),
 (25,'samj2',5,3,2,'Samsung J2',8,'Pending'),
 (26,'samSDOUS',5,4,1,'Samsung SDOUS',12,'Pending'),
 (27,'samj7',19,11,8,'Samsung J7',3,'Pending'),
 (28,'samj5',5,3,2,'Samsung J5',10,'Pending'),
 (29,'nok1200',11,7,4,'Nokia 1200',8,'Pending'),
 (30,'lg5800',5,4,1,'Lg Style 5800',10,'Pending');
/*!40000 ALTER TABLE `itemseligibleforoder` ENABLE KEYS */;


--
-- Definition of table `orderalertsettings`
--

DROP TABLE IF EXISTS `orderalertsettings`;
CREATE TABLE `orderalertsettings` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `minimumStockQty` int(10) unsigned NOT NULL,
  `stockAlertPriority` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `orderalertsettings`
--

/*!40000 ALTER TABLE `orderalertsettings` DISABLE KEYS */;
INSERT INTO `orderalertsettings` (`id`,`minimumStockQty`,`stockAlertPriority`) VALUES 
 (1,5,'URGENT'),
 (2,10,'HIGH'),
 (3,20,'LOW'),
 (4,21,'NORMAL');
/*!40000 ALTER TABLE `orderalertsettings` ENABLE KEYS */;


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
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` (`id`,`name`,`product_code`,`unitPrice`,`qty`,`totalPrice`,`category`,`purchase_date`) VALUES 
 (4,'Nokia 1100','nok1100',1500,20,30000,'Nokia','01-01-2017'),
 (5,'Nokia 1100','nok1100',1500,5,7500,'Nokia','10-01-2017'),
 (6,'Nokia 1100','nok1100',1500,3,4500,'Nokia','15-01-2017'),
 (7,'Nokia 1200','nok1200',1500,3,7500,'Nokia','15-01-2017'),
 (8,'Nokia 1200','nok1200',1500,8,12000,'Nokia','18-01-2017'),
 (9,'Samsung J7','samj7',16000,10,160000,'Samsung','19-01-2017'),
 (10,'Samsung J7','samj7',16000,2,32000,'Samsung','02-02-2017'),
 (11,'Samsung J7','samj7',16000,2,32000,'Samsung','06-02-2017'),
 (12,'Samsung J7','samj7',16000,5,16000,'Samsung','06-03-2017'),
 (13,'Samsung J5','samj5',16000,5,16000,'Samsung','06-03-2017'),
 (14,'Samsung J2','samj2',16000,5,16000,'Samsung','16-03-2017'),
 (15,'Samsung SDOUS','samSDOUS',17000,5,85000,'Samsung','28-03-2017'),
 (16,'Lg Style 5800','lg5800',20000,5,10000,'Lg','15-04-2017'),
 (17,'Symphony 1548','sym1548',2500,8,20000,'Symphony','12-05-2017'),
 (18,'Symphony 1548','sym1548',2600,5,12800,'Symphony','16-05-2017');
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `productsummary`
--

/*!40000 ALTER TABLE `productsummary` DISABLE KEYS */;
INSERT INTO `productsummary` (`id`,`product_code`,`purchase_qty`,`sold_qty`,`avilable_qty`,`product_name`) VALUES 
 (2,'nok1100',28,8,20,'Nokia 1100'),
 (3,'nok1200',11,7,4,'Nokia 1200'),
 (4,'samj7',19,11,8,'Samsung J7'),
 (5,'samj5',5,3,2,'Samsung J5'),
 (6,'samj2',5,3,2,'Samsung J2'),
 (7,'samSDOUS',5,4,1,'Samsung SDOUS'),
 (8,'lg5800',5,4,1,'Lg Style 5800'),
 (9,'sym1548',13,1,12,'Symphony 1548');
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sales_details`
--

/*!40000 ALTER TABLE `sales_details` DISABLE KEYS */;
INSERT INTO `sales_details` (`id`,`product_name`,`product_code`,`qty`,`unit_price`,`total_price`,`category`,`sales_date`,`customer_name`,`customer_mobile`) VALUES 
 (4,'Nokia 1100','nok1100',5,1800,'9000','','19-01-2017','Shariar','016868945658'),
 (5,'Nokia 1100','nok1100',2,1800,'9000','','19-01-2017','Shamim','016845544545'),
 (6,'Nokia 1100','nok1100',1,1800,'9000','','19-01-2017','Shabib','01684554454'),
 (7,'Samsung J5','samj5',3,20000,'60000','','25-01-2017','Rumi','01684554447'),
 (8,'Samsung J7','samj7',3,20000,'60000','','25-01-2017','Rumi','01684554447'),
 (9,'Samsung J2','samj2',3,20000,'60000','','28-01-2017','Firoza','01684554498'),
 (10,'Nokia 1200','nok1200',3,2000,'6000','','30-01-2017','Fahmida','01684554482'),
 (11,'Nokia 1200','nok1200',4,2000,'6000','','31-03-2017','Atik','01684554482'),
 (12,'Samsung SDOUS','samSDOUS',4,20000,'80000','','31-03-2017','Atik','01684554482'),
 (13,'Lg Style 5800','lg5800',4,20000,'80000','','22-04-2017','Urmi','01684554478'),
 (14,'Symphony 1548','sym1548',1,4500,'4500','','16-05-2017','Ruhul','01686239148'),
 (15,'Samsung J7','samj7',8,30000,'240000','','15-06-2017','Atik','0168656545');
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
 (1,'Mr. Admin','admin@gmail.com','455454','admin','admin','admin');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
