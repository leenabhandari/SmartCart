-- phpMyAdmin SQL Dump
-- version 4.6.6
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 26, 2017 at 04:34 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `id2756184_mall`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `TransactionID` int(15) NOT NULL,
  `CustID` bigint(15) NOT NULL,
  `ItemID` int(11) NOT NULL,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `PaymentStatus` char(2) COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(90) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cart`
--

INSERT INTO `cart` (`TransactionID`, `CustID`, `ItemID`, `Time`, `PaymentStatus`, `url`) VALUES
(54, 3, 11, '2017-10-24 19:34:25', 'p', 'http://anshpurii.000webhostapp.com/images/11%20Top%20Pantaloons.jpg'),
(55, 3, 12, '2017-10-24 19:34:25', 'p', 'http://anshpurii.000webhostapp.com/images/12%20Black%20Honey%20Dress.jpg'),
(56, 3, 13, '2017-10-24 19:34:25', 'p', 'http://anshpurii.000webhostapp.com/images/13%20Alto%20Moda%20Tunic%20Pantaloons.jpg'),
(57, 3, 14, '2017-10-24 19:34:25', 'p', 'http://anshpurii.000webhostapp.com/images/14%20Athena%20Teal%20Dress.jpg'),
(62, 3, 17, '2017-10-24 19:34:25', 'p', 'http://anshpurii.000webhostapp.com/images/17%20Veni%20Vidid%20Vici%20black%20crop%20top.jp'),
(79, 3, 26, '2017-10-24 19:34:25', 'p', 'http://anshpurii.000webhostapp.com/images/26%20crew%20Tshirt.jpg'),
(86, 3, 8, '2017-10-24 19:34:25', 'p', 'http://anshpurii.000webhostapp.com/images/8%20Honey%20Pantaloons%20dress.jpg'),
(87, 3, 42, '2017-10-24 19:34:25', 'p', 'http://anshpurii.000webhostapp.com/images/42%20Peter%20England%20Grey%20Kurta.jpg'),
(89, 3, 1, '2017-10-24 19:34:25', 'p', 'https://anshpurii.000webhostapp.com/images/1%20Spring%20Red%20Dress.jpg'),
(103, 3, 41, '2017-10-25 04:04:40', 'p', 'http://anshpurii.000webhostapp.com/images/41%20Peter%20England%20Grey%20Blazer.jpg'),
(104, 3, 42, '2017-10-25 04:04:40', 'p', 'http://anshpurii.000webhostapp.com/images/42%20Peter%20England%20Grey%20Kurta.jpg'),
(105, 3, 43, '2017-10-25 04:04:40', 'p', 'http://anshpurii.000webhostapp.com/images/43%20Kurta%20Pyjama.jpg'),
(106, 3, 44, '2017-10-25 04:04:40', 'p', 'http://anshpurii.000webhostapp.com/images/44%20Orange%20TShirt.jpg'),
(119, 951, 8, '2017-10-25 04:09:46', 'p', 'http://anshpurii.000webhostapp.com/images/8%20Honey%20Pantaloons%20dress.jpg'),
(121, 3, 25, '2017-10-25 04:25:39', 'p', 'http://anshpurii.000webhostapp.com/images/25%20manvaa%20Silk%20Suit%20Dress.jpg'),
(122, 3, 8, '2017-10-25 04:25:39', 'p', 'http://anshpurii.000webhostapp.com/images/8%20Honey%20Pantaloons%20dress.jpg'),
(123, 3, 1, '2017-10-25 04:25:39', 'p', 'https://anshpurii.000webhostapp.com/images/1%20Spring%20Red%20Dress.jpg'),
(124, 3, 8, '2017-10-25 04:25:39', 'p', 'http://anshpurii.000webhostapp.com/images/8%20Honey%20Pantaloons%20dress.jpg'),
(125, 3, 1, '2017-10-25 04:25:39', 'p', 'https://anshpurii.000webhostapp.com/images/1%20Spring%20Red%20Dress.jpg'),
(131, 3, 25, '2017-10-25 05:40:08', 'p', 'http://anshpurii.000webhostapp.com/images/25%20manvaa%20Silk%20Suit%20Dress.jpg'),
(132, 3, 25, '2017-10-25 07:50:32', 'p', 'http://anshpurii.000webhostapp.com/images/25%20manvaa%20Silk%20Suit%20Dress.jpg'),
(135, 3, 8, '2017-10-25 07:50:32', 'p', 'http://anshpurii.000webhostapp.com/images/8%20Honey%20Pantaloons%20dress.jpg'),
(136, 3, 8, '2017-10-25 07:50:32', 'p', 'http://anshpurii.000webhostapp.com/images/8%20Honey%20Pantaloons%20dress.jpg'),
(138, 3, 25, '2017-10-25 18:01:09', 'p', 'http://anshpurii.000webhostapp.com/images/25%20manvaa%20Silk%20Suit%20Dress.jpg'),
(139, 5858585858, 4, '2017-10-25 18:01:09', 'p', 'http://anshpurii.000webhostapp.com/images/4%20Red%20%20Gown.jpg'),
(140, 5858585858, 4, '2017-10-25 18:01:09', 'p', 'http://anshpurii.000webhostapp.com/images/4%20Red%20%20Gown.jpg'),
(142, 5858585858, 4, '2017-10-25 18:02:30', 'p', 'http://anshpurii.000webhostapp.com/images/4%20Red%20%20Gown.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `couponcode`
--

CREATE TABLE `couponcode` (
  `code` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `value` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `couponcode`
--

INSERT INTO `couponcode` (`code`, `value`) VALUES
('000', 0),
('100OFF', 100),
('150OFF', 150),
('200OFF', 200),
('250OFF', 250),
('500OFF', 500),
('50OFF', 50);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `CustID` bigint(15) NOT NULL,
  `CustName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `CustPassword` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `Balance` int(15) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`CustID`, `CustName`, `CustPassword`, `Balance`) VALUES
(3, 'lorenzo', '', 45051),
(8, 'xx', 'xx', 0),
(9, 'm', 'q', 0),
(22, 'abc', '111', 0),
(33, 'ch', 'i', 8),
(56, 't', 'gj', 76),
(88, 'dx', 'ss', 33),
(147, 'q', 'q', 2052),
(951, 'jon ', '123', 7551),
(999, 'lll', '000', 78000),
(1111, 'z', 'z', 1),
(2323, 'co', 'o', 6789),
(5679, 'fyi', 'h', 478),
(111111, 'Monica', 'friends', 10000),
(123456, 'Maithilee', 'hi', 7000),
(1111333, 'rachel', '12345', 5000),
(123123123, 'Ross Geller', 'hello', 3620),
(123456789, 'Monika ', '12345', 5000),
(1111111111, 'mai', '123456', 1000),
(1234123498, 'abc', '12345qwe', 9000),
(1234567890, 'Rachel', 'hello12345', 5000),
(5858585858, 'qwe', '12345qwe', 822),
(7249162264, 'mitesh', '12345678', 8500),
(8421971358, 'Maithilee', 'hi', 50100),
(8698985928, 'hariom', '123', 50000),
(8805773559, 'Aishwarya ', '1234qwerty', 0),
(8888888888, 'Mayuri ', 'mayuri', 10000),
(8983764967, 'lalitmetkar', '1234567890', 1000),
(9021829583, 'rahul', '12345', 45800),
(9503643353, 'adgd9500@gmail.com ', 'akshata123', 3000),
(9763722187, 'Sits', '1234', 1100),
(9764055014, 'Mukul', 'irock123', 3000000),
(9766053295, 'amol', '1234', 50000),
(9876543210, 'leena', '12345', 47601),
(9881164909, 'ad', 'ad', 66777);

-- --------------------------------------------------------

--
-- Table structure for table `items`
--

CREATE TABLE `items` (
  `ItemID` int(11) NOT NULL,
  `ItemName` varchar(30) NOT NULL,
  `ItemPrice` int(11) NOT NULL,
  `ItemQty` int(11) NOT NULL,
  `url` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `items`
--

INSERT INTO `items` (`ItemID`, `ItemName`, `ItemPrice`, `ItemQty`, `url`) VALUES
(1, 'Spring Red Dress', 1500, 41, 'https://anshpurii.000webhostapp.com/images/1%20Spring%20Red%20Dress.jpg'),
(2, 'printed black top', 1000, 50, 'http://anshpurii.000webhostapp.com/images/2%20black%20printed%20cold%20shoulder%20Pantaloon.jpg'),
(3, 'Akkriti Blue Skirt', 750, 50, 'http://anshpurii.000webhostapp.com/images/3%20Akkriti%20%20Skirts.jpg'),
(4, 'Red Gown', 3200, 50, 'http://anshpurii.000webhostapp.com/images/4%20Red%20%20Gown.jpg'),
(5, 'Ahalyaa Kurta and Skirt', 2399, 50, 'http://anshpurii.000webhostapp.com/images/5%20ahalyaa%20Kurta%20and%20Skirt.jpg'),
(6, 'Blue Tshirt', 750, 50, 'http://anshpurii.000webhostapp.com/images/6%20Blue%20Tshirt.jpg'),
(7, 'Levi\'s Tshirt', 1220, 50, 'http://anshpurii.000webhostapp.com/images/7%20Levi\'s%20Tshirt.jpg'),
(8, 'Honey Pantaloons Dress', 1699, 50, 'http://anshpurii.000webhostapp.com/images/8%20Honey%20Pantaloons%20dress.jpg'),
(9, 'Akkriti Pantaloons Dress', 2570, 50, 'http://anshpurii.000webhostapp.com/images/9%20Akkkriti%20dress%20by%20Pantaloons.jpg'),
(10, 'Imara White Skirt', 2000, 50, 'http://anshpurii.000webhostapp.com/images/10%20Imara%20Skirt.jpg'),
(11, 'Pantaloons orange top', 800, 50, 'http://anshpurii.000webhostapp.com/images/11%20Top%20Pantaloons.jpg'),
(12, 'Black Honey Dress', 1050, 50, 'http://anshpurii.000webhostapp.com/images/12%20Black%20Honey%20Dress.jpg'),
(13, 'Alto Moda Tunic', 1300, 50, 'http://anshpurii.000webhostapp.com/images/13%20Alto%20Moda%20Tunic%20Pantaloons.jpg'),
(14, 'Athena Teal Dress', 980, 50, 'http://anshpurii.000webhostapp.com/images/14%20Athena%20Teal%20Dress.jpg'),
(15, 'Annabelle Pallazo', 850, 50, 'http://anshpurii.000webhostapp.com/images/15%20Annabelle%20Palazzo%20Pants%20Pantaloons.jpg'),
(16, 'Tommy Hilfiger PolkaDot Midi', 1690, 50, 'http://anshpurii.000webhostapp.com/images/16%20Tommy%20Hilfiger%20Polka-Dot%20Midi%20Dress.jpg'),
(17, 'Black Crop  Top', 560, 50, 'http://anshpurii.000webhostapp.com/images/17%20Veni%20Vidid%20Vici%20black%20crop%20top.jpg'),
(18, 'Willow Crop Tank', 500, 50, 'http://anshpurii.000webhostapp.com/images/18%20willow%20Lace%20Up%20Crop%20Tank.jpg'),
(19, 'Cropped Adidas Hoodie', 890, 50, 'http://anshpurii.000webhostapp.com/images/19%20Cropped%20Adidas%20Hoodie.jpg'),
(20, 'UCB Navy Printed Tshirt', 1050, 50, 'http://anshpurii.000webhostapp.com/images/20%20Printed%20Tshirt.jpg'),
(21, 'Green Cardigan', 1200, 50, 'http://anshpurii.000webhostapp.com/images/21%20United%20Colors%20of%20Benetton%20Cardigan.jpg'),
(22, 'Fabron Lehenga', 4500, 50, 'http://anshpurii.000webhostapp.com/images/22%20Fabron%20Lehenga.jpg'),
(23, 'Blue Dress Material ', 655, 50, 'http://anshpurii.000webhostapp.com/images/23%20Blue%20Dress%20Material.jpg'),
(24, 'Silk Sharara', 2545, 50, 'http://anshpurii.000webhostapp.com/images/24%20Silk%20Sharara.jpg'),
(25, 'Manvaa Silk Suit', 850, 50, 'http://anshpurii.000webhostapp.com/images/25%20manvaa%20Silk%20Suit%20Dress.jpg'),
(26, 'Crew Tshirt', 700, 50, 'http://anshpurii.000webhostapp.com/images/26%20crew%20Tshirt.jpg'),
(27, 'Checked Flannel Shirt', 850, 50, 'http://anshpurii.000webhostapp.com/images/27%20Checked%20Flannel%20Shirt.jpg'),
(28, 'Grey Shirt', 1000, 50, 'http://anshpurii.000webhostapp.com/images/28%20Shirt.jpg'),
(29, 'Tuxedo', 5000, 50, 'http://anshpurii.000webhostapp.com/images/29%20Tuxedo.jpg'),
(30, 'Formal Wear', 3750, 50, 'http://anshpurii.000webhostapp.com/images/30%20Men\'s%20Formal.jpg'),
(31, 'Semi-Formal Wear', 1500, 50, 'http://anshpurii.000webhostapp.com/images/31%20semi%20Formal.jpg'),
(32, 'Blazer', 2200, 50, 'http://anshpurii.000webhostapp.com/images/32%20Blazer.jpg'),
(33, 'Blue Polo TShirt', 450, 50, 'http://anshpurii.000webhostapp.com/images/33%20Blue%20Polo%20Tshirt.jpg'),
(34, 'United Colors Grey Tshirt ', 750, 50, 'http://anshpurii.000webhostapp.com/images/34%20United%20Colors%20Grey%20Tshirt.jpg'),
(35, 'Van Heusen Black Suit', 3500, 50, 'http://anshpurii.000webhostapp.com/images/35%20Van%20Huesen%20Black%20Suit.jpg'),
(36, 'Van Heusen Black Blazer', 2000, 50, 'http://anshpurii.000webhostapp.com/images/36%20Van%20Huesen%20Black%20Blazer.jpg'),
(37, 'Allen Solly Brown Shirt', 2275, 50, 'http://anshpurii.000webhostapp.com/images/37%20Allen%20Solly%20Brown%20Shirt.jpg'),
(38, 'Van Heusen Red TShirt', 2000, 50, 'http://anshpurii.000webhostapp.com/images/38%20Van%20Huesen%20Red%20TShirt.jpg'),
(39, 'Peter England Black Waistcoat', 2799, 50, 'http://anshpurii.000webhostapp.com/images/39%20Peter%20England%20Black%20Waistcoat.jpg'),
(40, 'Peter England Brown Kurta', 3200, 50, 'http://anshpurii.000webhostapp.com/images/40%20Peter%20England%20Brown%20Kurta.jpeg'),
(41, 'Peter England Gray Blazer', 2225, 50, 'http://anshpurii.000webhostapp.com/images/41%20Peter%20England%20Grey%20Blazer.jpg'),
(42, 'Peter England Gray Kurta', 3100, 50, 'http://anshpurii.000webhostapp.com/images/42%20Peter%20England%20Grey%20Kurta.jpg'),
(43, 'Kurta Pyajama', 4200, 50, 'http://anshpurii.000webhostapp.com/images/43%20Kurta%20Pyjama.jpg'),
(44, 'Orange Tshirt', 899, 50, 'http://anshpurii.000webhostapp.com/images/44%20Orange%20TShirt.jpg'),
(45, 'Cambridge Collar', 2700, 50, 'http://anshpurii.000webhostapp.com/images/45%20Cambridge%20Collar.jpg'),
(46, 'Ethnic Wear', 4500, 50, 'http://anshpurii.000webhostapp.com/images/46%20Mens%20Ethnic%20Wear.jpg'),
(47, 'Kisaah Maroon Kurta ', 890, 50, 'http://anshpurii.000webhostapp.com/images/47%20Kisah%20Men\'s%20Maroon%20Kurta.jpg'),
(48, 'Cotton Kurta', 1500, 50, 'http://anshpurii.000webhostapp.com/images/48%20Cotton%20Kurta.jpg'),
(49, 'Keith Ethnic Kurta', 999, 50, 'http://anshpurii.000webhostapp.com/images/49%20Keith%20Ethnic%20Kurta.jpg'),
(50, 'ASOS Black Denim Jacket', 1225, 50, 'http://anshpurii.000webhostapp.com/images/50%20ASOS%20Skinny%20Denim%20JAcket.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`TransactionID`),
  ADD KEY `CustID` (`CustID`),
  ADD KEY `ItemID` (`ItemID`);

--
-- Indexes for table `couponcode`
--
ALTER TABLE `couponcode`
  ADD PRIMARY KEY (`code`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`CustID`);

--
-- Indexes for table `items`
--
ALTER TABLE `items`
  ADD PRIMARY KEY (`ItemID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `TransactionID` int(15) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=143;
--
-- AUTO_INCREMENT for table `items`
--
ALTER TABLE `items`
  MODIFY `ItemID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`CustID`) REFERENCES `customer` (`CustID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cart_ibfk_2` FOREIGN KEY (`ItemID`) REFERENCES `items` (`ItemID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
