-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               5.7.14-log - MySQL Community Server (GPL)
-- Server OS:                    Win64
-- HeidiSQL Version:             8.0.0.4396
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Dumping database structure for authdb
DROP DATABASE IF EXISTS `authdb`;
CREATE DATABASE IF NOT EXISTS `authdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `authdb`;


-- Dumping structure for table authdb.groups
DROP TABLE IF EXISTS `groups`;
CREATE TABLE IF NOT EXISTS `groups` (
  `groupid` varchar(32) NOT NULL,
  `userid` varchar(32) NOT NULL,
  PRIMARY KEY (`groupid`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table authdb.groups: ~2 rows (approximately)
DELETE FROM `groups`;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` (`groupid`, `userid`) VALUES
	('registeredUsers', 'abc'),
	('registeredUsers', 'as'),
	('registeredUsers', 'asdf'),
	('registeredUsers', 'sadf'),
	('registeredUsers', 'xxx');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;


-- Dumping structure for table authdb.notes
DROP TABLE IF EXISTS `notes`;
CREATE TABLE IF NOT EXISTS `notes` (
  `noteId` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `userid` varchar(32) NOT NULL,
  `createdAt` datetime NOT NULL,
  `category` varchar(100) NOT NULL,
  `content` text NOT NULL,
  PRIMARY KEY (`noteId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Dumping data for table authdb.notes: ~3 rows (approximately)
DELETE FROM `notes`;
/*!40000 ALTER TABLE `notes` DISABLE KEYS */;
INSERT INTO `notes` (`noteId`, `title`, `userid`, `createdAt`, `category`, `content`) VALUES
	(1, 'test', 'abc', '2016-11-03 15:31:00', 'SOCIAL', 'test contreny'),
	(2, 'test2', 'abc', '2016-11-03 15:41:46', 'FOR_SALE', 'sd'),
	(3, 'asd', 'abc', '2016-11-03 15:43:21', 'FOR_SALE', 'sa'),
	(4, 'tet', 'abc', '2016-11-03 16:52:33', 'JOBS', 'df');
/*!40000 ALTER TABLE `notes` ENABLE KEYS */;


-- Dumping structure for table authdb.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userid` varchar(32) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Dumping data for table authdb.users: ~2 rows (approximately)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`userid`, `password`) VALUES
	('abc', 'ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad'),
	('as', 'f4bf9f7fcbedaba0392f108c59d8f4a38b3838efb64877380171b54475c2ade8'),
	('asdf', '45c9a6614fccd4f9592d8283a4f25bff84076fd43ee9f90eaa07746ebbed02ca'),
	('sadf', '9279fa6a314fb0728f7cfd93669cf7f35cc01b6389fd220664919f455b307203'),
	('xxx', 'cd2eb0837c9b4c962c22d2ff8b5441b7b45805887f051d39bf133b583baf6860');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
