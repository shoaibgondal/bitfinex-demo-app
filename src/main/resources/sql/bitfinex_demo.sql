/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.5-10.1.14-MariaDB-1~wily : Database - bitfinex_demo
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bitfinex_demo` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bitfinex_demo`;

/*Table structure for table `oauth_user` */

DROP TABLE IF EXISTS `oauth_user`;

CREATE TABLE `oauth_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `oauth_provider` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `first_name` varchar(100) DEFAULT NULL,
  `last_name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `created_at` datetime NOT NULL,
  `last_login_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `oauth_user` */

insert  into `oauth_user`(`id`,`oauth_provider`,`name`,`first_name`,`last_name`,`email`,`created_at`,`last_login_at`) values (1,'Google','Shoaib Ahmad Gondal','Shoaib','Ahmad Gondal','kool.gondal@gmail.com','2019-02-17 23:48:39','2019-02-19 02:58:49');

/*Table structure for table `ticker_log` */

DROP TABLE IF EXISTS `ticker_log`;

CREATE TABLE `ticker_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `bid` float DEFAULT NULL,
  `bid_size` float DEFAULT NULL,
  `ask` float DEFAULT NULL,
  `ask_size` float DEFAULT NULL,
  `daily_change` float DEFAULT NULL,
  `daily_change_perc` float DEFAULT NULL,
  `last_price` float DEFAULT NULL,
  `volume` float DEFAULT NULL,
  `high` float DEFAULT NULL,
  `low` float DEFAULT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

/*Data for the table `ticker_log` */

insert  into `ticker_log`(`id`,`bid`,`bid_size`,`ask`,`ask_size`,`daily_change`,`daily_change_perc`,`last_price`,`volume`,`high`,`low`,`created_at`) values (1,3705.8,206.125,3705.9,128.241,39.5,0.0108,3705.9,6699.76,3726.7,3659.4,'2019-02-17 00:31:58'),(2,3705.8,206.125,3705.9,128.241,39.5,0.0108,3705.9,6699.76,3726.7,3659.4,'2019-02-17 00:32:04'),(3,3705.8,206.125,3705.9,128.241,39.5,0.0108,3705.9,6699.76,3726.7,3659.4,'2019-02-17 00:32:10'),(4,3705.8,206.125,3705.9,128.241,39.5,0.0108,3705.9,6699.76,3726.7,3659.4,'2019-02-17 00:32:15'),(5,3705.8,199.847,3705.9,49.4039,39.4,0.0107,3705.8,6699.77,3726.7,3659.4,'2019-02-17 00:32:21'),(6,3705.8,199.847,3705.9,49.4039,39.4,0.0107,3705.8,6699.77,3726.7,3659.4,'2019-02-17 00:32:27'),(7,3705.8,199.847,3705.9,49.4039,39.4,0.0107,3705.8,6699.77,3726.7,3659.4,'2019-02-17 00:32:33'),(8,3705.8,199.847,3705.9,49.4039,39.4,0.0107,3705.8,6699.77,3726.7,3659.4,'2019-02-17 00:32:38'),(9,3705.8,199.847,3705.9,49.4039,39.4,0.0107,3705.8,6699.77,3726.7,3659.4,'2019-02-17 00:32:44'),(10,3683.9,51.2434,3684,41.4397,-18.0337,-0.0049,3683.97,12466.2,3790,3655,'2019-02-17 21:51:31'),(11,3683.9,51.2434,3684,41.4397,-18.0337,-0.0049,3683.97,12466.2,3790,3655,'2019-02-17 21:51:37'),(12,3683.9,51.2434,3684,41.4397,-18.0337,-0.0049,3683.97,12466.2,3790,3655,'2019-02-17 21:51:42'),(13,3683.9,51.2434,3684,41.4397,-18.0337,-0.0049,3683.97,12466.2,3790,3655,'2019-02-17 21:51:48'),(14,3683.9,51.2434,3684,41.4397,-18.0337,-0.0049,3683.97,12466.2,3790,3655,'2019-02-17 21:51:54'),(15,3683.9,51.2434,3684,41.4397,-18.0337,-0.0049,3683.97,12466.2,3790,3655,'2019-02-17 21:52:00'),(16,3683.9,51.2434,3684,41.4397,-18.0337,-0.0049,3683.97,12466.2,3790,3655,'2019-02-17 21:52:05'),(17,3683.9,51.2434,3684,41.4397,-18.0337,-0.0049,3683.97,12466.2,3790,3655,'2019-02-17 21:52:11');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
