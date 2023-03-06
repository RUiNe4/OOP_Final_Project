-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 06, 2023 at 05:28 PM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `possys`
--
CREATE DATABASE IF NOT EXISTS `possys` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `possys`;

-- --------------------------------------------------------

--
-- Table structure for table `cartproducts`
--

DROP TABLE IF EXISTS `cartproducts`;
CREATE TABLE IF NOT EXISTS `cartproducts` (
  `cartID` int DEFAULT NULL,
  `productID` int DEFAULT NULL,
  `productName` varchar(64) DEFAULT NULL,
  `productPrice` float DEFAULT NULL,
  `productQty` int DEFAULT NULL,
  `subPrice` float DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `cartproducts`
--

INSERT INTO `cartproducts` (`cartID`, `productID`, `productName`, `productPrice`, `productQty`, `subPrice`) VALUES
(10002, 1065, 'Acer Nitro 5', 700, 2, 1400),
(10002, 1065, 'Acer Nitro 5', 700, 3, 2100),
(10001, 1068, 'Clock', 12.5, 3, 37.5),
(10001, 1054, 'Laptop', 700, 1, 1400),
(10002, 1068, 'Clock', 12.5, 6, 75);

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `pid` int NOT NULL AUTO_INCREMENT,
  `pName` varchar(64) DEFAULT 'UNKNOWN',
  `pPrice` float DEFAULT '0',
  `pQty` int DEFAULT '0',
  `productImg` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pid`)
) ENGINE=MyISAM AUTO_INCREMENT=1069 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`pid`, `pName`, `pPrice`, `pQty`, `productImg`) VALUES
(1054, 'Laptop', 700, 38, NULL),
(1065, 'Acer Nitro 5', 700, 278, NULL),
(1068, 'Clock', 12.5, 369, NULL),
(1064, 'Monitor', 1290, 183, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `testDate` timestamp NULL DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lastName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` int DEFAULT NULL,
  `dateOfBirth` date NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=MyISAM AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `firstName`, `lastName`, `age`, `dateOfBirth`, `email`, `password`, `phone`, `gender`, `type`, `address`, `userName`) VALUES
(1000, 'admin', 'admin', 18, '2003-02-26', 'admin@domain.com', 'admin', '012792811', 'Male', 'Admin', 'Phnom Penh', 'admin'),
(1001, 'Rethtihpong', 'Em', 18, '2003-02-26', 'rithtipongem@gmail.com', 'rethtihpong1001', '012792811', 'Male', 'Employee', 'Phnom Penh', 'rethtihpong1001');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
