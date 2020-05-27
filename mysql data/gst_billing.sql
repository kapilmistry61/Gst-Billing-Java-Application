-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3307
-- Generation Time: Apr 29, 2020 at 07:12 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gst_billing`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `code` varchar(200) NOT NULL,
  `name` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `mobile` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `gst` varchar(200) NOT NULL,
  `state` varchar(200) NOT NULL,
  `statecode` varchar(200) NOT NULL,
  `remark` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `code`, `name`, `address`, `mobile`, `phone`, `gst`, `state`, `statecode`, `remark`) VALUES
(1, 'OM', 'COM MARKETING', 'COM MARKETING SECTOR 7 SURAT', '873999023', '', '18HDGGDHGD992', 'GUJARAT', '140033', ''),
(15, 'TECHCO', 'TECH COM COMPUTER ', 'tech com computer vijay marg main road udaipur ', '9558582201', '', '18FDGHSGHGHS3122', 'RAJASTHAN', '220110', ''),
(16, '1520', 'NIKOLA', 'Brace Jovanovic 33/9 Pancevo, Serbia', '9758580202', '', '3571627GHGSHFG265', 'Srbija', '26000', ''),
(17, '01', 'Himgiri Soap ', 'Kalyan-kunj bunglow, vikasgruh rd, besides reliance fresh, dhumketu marg, Opp. Rameshwar apartments, fatehnagar, paldi. Ahmedabad-380007.', '7898980022', '', '64GGSGDSF26', 'Gujarat', '395006', ''),
(18, 'VIJAY01', 'VIJAY KUMAR DAS', 'KHANDICH, DUMARIYATAND, KHAIRA (JAMUI)', '7888753121', '', 'ABC125487895', 'BIHAR', '811317', ''),
(19, 'SK01', 'arya collateral warehousing ser.', 'noida uttar pradesh', '8854352143', '', '534GCHSGHFDGSH22', 'uttar pradesh', '09433', ''),
(21, 'VJ001', 'VIJAY KUMAR ', 'MALVIYA NAGAR, NDELHI', '9675646474', '', '5473SFDSFGDFG2', 'NEW DELHI', '542579', ''),
(22, 'CUST001', 'SRI KUMARAGURU COMPUTER', 'Anaimallai Main Road, Ambarapalayam, Pollachi ', '7856563383', '', '686GHGDFH3656', 'TAMILNADU', '61002', ''),
(23, 'HOC', 'HARI OM COMPUTER', 'HARI OM COMPUTER VIJAY MARG MAIN ROAD JAIPUR ', '9558583302', '', '180HSHFCGH37', '330322', 'RAJASTHAN ', ''),
(24, 'OMM', 'OM MARKETING PVT', 'MAIN ROAD JAIPUR ', '9668680088', '', 'GF67467676276', 'RAJASTHAN', '302022', 'ANY'),
(25, 'STC', 'SOFT TECH COMPUTER ', 'VIJAY MARG MAIN ROAD JAIPUR ', '9558581102', '', '18GHGFHSGF76376', 'RAJASTHAN', '203303', '');

-- --------------------------------------------------------

--
-- Table structure for table `datecwise`
--

CREATE TABLE `datecwise` (
  `id` int(11) NOT NULL,
  `fromdate` varchar(200) NOT NULL,
  `todate` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `datecwise`
--

INSERT INTO `datecwise` (`id`, `fromdate`, `todate`) VALUES
(1, '2020-04-28', '2020-04-28');

-- --------------------------------------------------------

--
-- Table structure for table `financialyear`
--

CREATE TABLE `financialyear` (
  `id` int(11) NOT NULL,
  `financialyear` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `financialyear`
--

INSERT INTO `financialyear` (`id`, `financialyear`) VALUES
(1, '2019-2020'),
(12, '2020-2021');

-- --------------------------------------------------------

--
-- Table structure for table `invoice`
--

CREATE TABLE `invoice` (
  `billno` int(11) NOT NULL,
  `billto` varchar(500) NOT NULL,
  `billdate` varchar(500) NOT NULL,
  `taxpayable` varchar(500) NOT NULL,
  `custcode` varchar(500) NOT NULL,
  `custname` varchar(500) NOT NULL,
  `billaddress` varchar(500) NOT NULL,
  `shipaddress` varchar(500) NOT NULL,
  `shipcustname` varchar(500) NOT NULL,
  `scode` varchar(500) NOT NULL,
  `state` varchar(500) NOT NULL,
  `gstinnumber` varchar(500) NOT NULL,
  `totalcgst` double NOT NULL,
  `totalsgst` double NOT NULL,
  `totaltax` double NOT NULL,
  `subtotal` varchar(500) NOT NULL,
  `vehicleno` varchar(500) NOT NULL,
  `tmode` varchar(500) NOT NULL,
  `psupply` varchar(500) NOT NULL,
  `datetime` varchar(500) NOT NULL,
  `fcharge` varchar(500) NOT NULL,
  `loading` varchar(500) NOT NULL,
  `insurance` varchar(500) NOT NULL,
  `other` varchar(500) NOT NULL,
  `note` varchar(500) NOT NULL,
  `grandtotal` double NOT NULL,
  `stime` varchar(500) NOT NULL,
  `searchtime` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoice`
--

INSERT INTO `invoice` (`billno`, `billto`, `billdate`, `taxpayable`, `custcode`, `custname`, `billaddress`, `shipaddress`, `shipcustname`, `scode`, `state`, `gstinnumber`, `totalcgst`, `totalsgst`, `totaltax`, `subtotal`, `vehicleno`, `tmode`, `psupply`, `datetime`, `fcharge`, `loading`, `insurance`, `other`, `note`, `grandtotal`, `stime`, `searchtime`) VALUES
(4, 'Home', '2020-04-21', 'Yes', 'OM', 'COM MARKETING', 'COM MARKETING SECTOR 7 SURAT', 'COM MARKETING SECTOR 7 SURAT', 'COM MARKETING', '140033', 'GUJARAT', '18HDGGDHGD992', 1550, 1550, 3100, '29800.0', 'RJ34 BD99889', 'LOCAL', 'JAIPUR', '2020-04-28', '100', '200', '300', '400', 'THANK ', 33900, '  12:08 PM', '2020-04-28 12:08 PM'),
(6, 'Home', '2020-04-23', 'Yes', '1520', 'NIKOLA', 'Brace Jovanovic 33/9 Pancevo, Serbia', 'Brace Jovanovic 33/9 Pancevo, Serbia', 'NIKOLA', '26000', 'Srbija', '3571627GHGSHFG265', 2065, 2065, 4130, '76300.0', 'RJ20 SN5565', 'LOCAL', 'JAIPUR', '2020-04-28', '200', '600', '0', '100', '', 81330, '  12:12 PM', '2020-04-28 12:12 PM'),
(7, 'Home', '2020-04-28', 'No', '01', 'Himgiri Soap ', 'Kalyan-kunj bunglow, vikasgruh rd, besides reliance fresh, dhumketu marg, Opp. Rameshwar apartments, fatehnagar, paldi. Ahmedabad-380007.', 'Kalyan-kunj bunglow, vikasgruh rd, besides reliance fresh, dhumketu marg, Opp. Rameshwar apartments, fatehnagar, paldi. Ahmedabad-380007.', 'Himgiri Soap ', '395006', 'Gujarat', '64GGSGDSF26', 800, 800, 1600, '23000.0', 'RJ40 DD4040', 'LOCAL', 'JAIPUR', '2020-04-28', '0', '0', '0', '0', '', 24600, '  12:14 PM', '2020-04-28 12:14 PM'),
(8, 'Home', '2020-04-24', 'No', 'VIJAY01', 'VIJAY KUMAR DAS', 'KHANDICH, DUMARIYATAND, KHAIRA (JAMUI)', 'KHANDICH, DUMARIYATAND, KHAIRA (JAMUI)', 'VIJAY KUMAR DAS', '811317', 'BIHAR', 'ABC125487895', 5000, 5000, 10000, '200000.0', 'RJ20 DD9090', 'LOCAL', 'JAIPUR', '2020-04-28', '1000', '2000', '0', '300', '', 213300, '  12:15 PM', '2020-04-28 12:15 PM'),
(9, 'Home', '2020-04-28', 'No', 'SK01', 'arya collateral warehousing ser.', 'noida uttar pradesh', 'noida uttar pradesh', 'arya collateral warehousing ser.', '09433', 'uttar pradesh', '534GCHSGHFDGSH22', 775, 775, 1550, '31000.0', 'RJ 67 SS9090', 'LOCAL', 'JAIPUER', '2020-04-28', '300', '700', '0', '200', '', 33750, '  12:16 PM', '2020-04-28 12:16 PM'),
(10, 'Home', '2020-04-20', 'No', 'A1', 'VARUN COMPUTER', '#26-3-234A, Melapuram, Hindupur, &, Anantapur, AP- 515201.', '#26-3-234A, Melapuram, Hindupur, &, Anantapur, AP- 515201.', 'VARUN COMPUTER', 'AP', '515202', '7578GFHGDFGH26', 1535, 1535, 3070, '26900.0', 'RJ 45 SS5678', 'LOCAL', 'JAIPUR ', '2020-04-28', '100', '400', '0', '0', '', 30470, '  12:18 PM', '2020-04-28 12:18 PM'),
(12, 'Home', '2020-04-18', 'No', 'CUST001', 'SRI KUMARAGURU COMPUTER', 'Anaimallai Main Road, Ambarapalayam, Pollachi ', 'Anaimallai Main Road, Ambarapalayam, Pollachi ', 'SRI KUMARAGURU COMPUTER', '61002', 'TAMILNADU', '686GHGDFH3656', 2275, 2275, 4550, '73000.0', 'RJ56 SN7878', 'LOCAL', 'JAIPUR', '2020-04-28', '300', '600', '0', '0', '', 78450, '  12:21 PM', '2020-04-28 12:21 PM'),
(14, 'Home', '2020-04-28', 'Yes', 'STC', 'SOFT TECH COMPUTER ', 'VIJAY MARG MAIN ROAD JAIPUR ', 'VIJAY MARG MAIN ROAD JAIPUR ', 'SOFT TECH COMPUTER ', '203303', 'RAJASTHAN', '18GHGFHSGF76376', 11147.5, 11147.5, 22295, '188000.0', 'RJ56 SD9090', 'LOCAL', 'JAIPUR ', '2020-04-28', '1000', '1500', '0', '300', 'ANY ', 213095, '  02:02 PM', '2020-04-28 02:02 PM');

-- --------------------------------------------------------

--
-- Table structure for table `invoicein`
--

CREATE TABLE `invoicein` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `qty` varchar(200) NOT NULL,
  `rate` varchar(200) NOT NULL,
  `total` varchar(200) NOT NULL,
  `discamt` varchar(200) NOT NULL,
  `taxvalue` varchar(200) NOT NULL,
  `taxper` varchar(200) NOT NULL,
  `sgst` varchar(200) NOT NULL,
  `cgst` varchar(200) NOT NULL,
  `igst` varchar(200) NOT NULL,
  `scgstrate` varchar(200) NOT NULL,
  `searchtime` varchar(200) NOT NULL,
  `taxpayable` varchar(200) NOT NULL,
  `invoicenumber` varchar(200) NOT NULL,
  `date` varchar(200) NOT NULL,
  `transmode` varchar(200) NOT NULL,
  `vechnumber` varchar(200) NOT NULL,
  `datetime` varchar(200) NOT NULL,
  `placesupply` varchar(200) NOT NULL,
  `gstnumber` varchar(200) NOT NULL,
  `partyname` varchar(200) NOT NULL,
  `billaddress` varchar(500) NOT NULL,
  `state` varchar(200) NOT NULL,
  `scode` varchar(200) NOT NULL,
  `shipaddress` varchar(500) NOT NULL,
  `remark` varchar(500) NOT NULL,
  `sgsttotal` varchar(300) NOT NULL,
  `cgsttotal` varchar(300) NOT NULL,
  `igsttotal` varchar(300) NOT NULL,
  `grandtotal` varchar(300) NOT NULL,
  `totaltaxvalue` varchar(300) NOT NULL,
  `subtotal` varchar(200) NOT NULL,
  `fright` varchar(200) NOT NULL,
  `loading` varchar(200) NOT NULL,
  `insurance` varchar(200) NOT NULL,
  `othercharge` varchar(200) NOT NULL,
  `ratecount` varchar(200) NOT NULL,
  `qtycount` varchar(200) NOT NULL,
  `stime` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `invoiceinsecond`
--

CREATE TABLE `invoiceinsecond` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `qty` varchar(200) NOT NULL,
  `rate` varchar(200) NOT NULL,
  `total` varchar(200) NOT NULL,
  `discamt` varchar(200) NOT NULL,
  `taxvalue` varchar(200) NOT NULL,
  `taxper` varchar(200) NOT NULL,
  `sgst` varchar(200) NOT NULL,
  `cgst` varchar(200) NOT NULL,
  `igst` varchar(200) NOT NULL,
  `scgstrate` varchar(200) NOT NULL,
  `searchtime` varchar(200) NOT NULL,
  `taxpayable` varchar(200) NOT NULL,
  `invoicenumber` varchar(200) NOT NULL,
  `date` varchar(200) NOT NULL,
  `transmode` varchar(200) NOT NULL,
  `vechnumber` varchar(200) NOT NULL,
  `datetime` varchar(200) NOT NULL,
  `placesupply` varchar(200) NOT NULL,
  `gstnumber` varchar(200) NOT NULL,
  `partyname` varchar(200) NOT NULL,
  `billaddress` varchar(500) NOT NULL,
  `state` varchar(200) NOT NULL,
  `scode` varchar(200) NOT NULL,
  `shipaddress` varchar(500) NOT NULL,
  `remark` varchar(500) NOT NULL,
  `sgsttotal` varchar(300) NOT NULL,
  `cgsttotal` varchar(300) NOT NULL,
  `igsttotal` varchar(300) NOT NULL,
  `grandtotal` varchar(300) NOT NULL,
  `totaltaxvalue` varchar(300) NOT NULL,
  `subtotal` varchar(200) NOT NULL,
  `fright` varchar(200) NOT NULL,
  `loading` varchar(200) NOT NULL,
  `insurance` varchar(200) NOT NULL,
  `othercharge` varchar(200) NOT NULL,
  `ratecount` varchar(200) NOT NULL,
  `qtycount` varchar(200) NOT NULL,
  `stime` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `invoiceinsecond`
--

INSERT INTO `invoiceinsecond` (`id`, `name`, `qty`, `rate`, `total`, `discamt`, `taxvalue`, `taxper`, `sgst`, `cgst`, `igst`, `scgstrate`, `searchtime`, `taxpayable`, `invoicenumber`, `date`, `transmode`, `vechnumber`, `datetime`, `placesupply`, `gstnumber`, `partyname`, `billaddress`, `state`, `scode`, `shipaddress`, `remark`, `sgsttotal`, `cgsttotal`, `igsttotal`, `grandtotal`, `totaltaxvalue`, `subtotal`, `fright`, `loading`, `insurance`, `othercharge`, `ratecount`, `qtycount`, `stime`) VALUES
(1, 'Tenda router', '10', '900', '9000.0', '0', '900.0', '10', '450.0', '450.0', '0', '5.0', '2020-04-28 12:08 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:08 PM'),
(2, 'Logitech Keyboard', '8', '500', '4000.0', '0', '280.0', '7', '140.0', '140.0', '0', '3.5', '2020-04-28 12:08 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:08 PM'),
(3, 'Tenda WIFI', '8', '600', '4800.0', '0', '480.0', '10', '240.0', '240.0', '0', '5.0', '2020-04-28 12:08 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:08 PM'),
(4, 'HP PRINTER', '1', '12000', '12000.0', '0', '1440.0', '12', '720.0', '720.0', '0', '6.0', '2020-04-28 12:08 PM', 'Yes', '', '2020-04-21', 'LOCAL', 'RJ34 BD99889', '2020-04-28', 'JAIPUR', '18HDGGDHGD992', 'COM MARKETING', 'COM MARKETING SECTOR 7 SURAT', 'GUJARAT', '140033', 'COM MARKETING SECTOR 7 SURAT', 'THANK ', '1550.0', '1550.0', '0', '33900.0', '3100.0', '29800.0', '100', '200', '300', '400', '14000.0', '27.0', '  12:08 PM'),
(1, 'Tenda router', '7', '900', '6300.0', '0', '630.0', '10', '315.0', '315.0', '0', '5.0', '2020-04-28 12:12 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:12 PM'),
(2, 'TVS KEYBOARD', '100', '700', '70000.0', '0', '3500.0', '5', '1750.0', '1750.0', '0', '2.5', '2020-04-28 12:12 PM', 'Yes', '', '2020-04-23', 'LOCAL', 'RJ20 SN5565', '2020-04-28', 'JAIPUR', '3571627GHGSHFG265', 'NIKOLA', 'Brace Jovanovic 33/9 Pancevo, Serbia', 'Srbija', '26000', 'Brace Jovanovic 33/9 Pancevo, Serbia', '', '2065.0', '2065.0', '0', '81330.0', '4130.0', '76300.0', '200', '600', '0', '100', '1600.0', '107.0', '  12:12 PM'),
(1, 'Tenda router', '10', '900', '9000.0', '0', '900.0', '10', '450.0', '450.0', '0', '5.0', '2020-04-28 12:14 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:14 PM'),
(2, 'Enter mouse ', '50', '200', '10000.0', '0', '500.0', '5', '250.0', '250.0', '0', '2.5', '2020-04-28 12:14 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:14 PM'),
(3, 'Intext mouse', '20', '200', '4000.0', '0', '200.0', '5', '100.0', '100.0', '0', '2.5', '2020-04-28 12:14 PM', 'No', '', '2020-04-28', 'LOCAL', 'RJ40 DD4040', '2020-04-28', 'JAIPUR', '64GGSGDSF26', 'Himgiri Soap ', 'Kalyan-kunj bunglow, vikasgruh rd, besides reliance fresh, dhumketu marg, Opp. Rameshwar apartments, fatehnagar, paldi. Ahmedabad-380007.', 'Gujarat', '395006', 'Kalyan-kunj bunglow, vikasgruh rd, besides reliance fresh, dhumketu marg, Opp. Rameshwar apartments, fatehnagar, paldi. Ahmedabad-380007.', '', '800.0', '800.0', '0', '24600.0', '1600.0', '23000.0', '0', '0', '0', '0', '1300.0', '80.0', '  12:14 PM'),
(1, 'Hp pendrive (64 gb)', '100', '2000', '200000.0', '0', '10000.0', '5', '5000.0', '5000.0', '0', '2.5', '2020-04-28 12:15 PM', 'No', '', '2020-04-24', 'LOCAL', 'RJ20 DD9090', '2020-04-28', 'JAIPUR', 'ABC125487895', 'VIJAY KUMAR DAS', 'KHANDICH, DUMARIYATAND, KHAIRA (JAMUI)', 'BIHAR', '811317', 'KHANDICH, DUMARIYATAND, KHAIRA (JAMUI)', '', '5000.0', '5000.0', '0', '213300.0', '10000.0', '200000.0', '1000', '2000', '0', '300', '2000.0', '100.0', '  12:15 PM'),
(1, 'intex keyboard ', '60', '350', '21000.0', '0', '1050.0', '5', '525.0', '525.0', '0', '2.5', '2020-04-28 12:16 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:16 PM'),
(2, 'Intext mouse', '20', '200', '4000.0', '0', '200.0', '5', '100.0', '100.0', '0', '2.5', '2020-04-28 12:16 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:16 PM'),
(3, 'Enter mouse ', '30', '200', '6000.0', '0', '300.0', '5', '150.0', '150.0', '0', '2.5', '2020-04-28 12:16 PM', 'No', '', '2020-04-28', 'LOCAL', 'RJ 67 SS9090', '2020-04-28', 'JAIPUER', '534GCHSGHFDGSH22', 'arya collateral warehousing ser.', 'noida uttar pradesh', 'uttar pradesh', '09433', 'noida uttar pradesh', '', '775.0', '775.0', '0', '33750.0', '1550.0', '31000.0', '300', '700', '0', '200', '750.0', '110.0', '  12:16 PM'),
(1, 'Tenda router', '1', '900', '900.0', '0', '90.0', '10', '45.0', '45.0', '0', '5.0', '2020-04-28 12:18 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:18 PM'),
(2, 'HP PRINTER', '2', '12000', '24000.0', '0', '2880.0', '12', '1440.0', '1440.0', '0', '6.0', '2020-04-28 12:18 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:18 PM'),
(3, 'Enter mouse ', '10', '200', '2000.0', '0', '100.0', '5', '50.0', '50.0', '0', '2.5', '2020-04-28 12:18 PM', 'No', '', '2020-04-20', 'LOCAL', 'RJ 45 SS5678', '2020-04-28', 'JAIPUR ', '7578GFHGDFGH26', 'VARUN COMPUTER', '#26-3-234A, Melapuram, Hindupur, &, Anantapur, AP- 515201.', '515202', 'AP', '#26-3-234A, Melapuram, Hindupur, &, Anantapur, AP- 515201.', '', '1535.0', '1535.0', '0', '30470.0', '3070.0', '26900.0', '100', '400', '0', '0', '13100.0', '13.0', '  12:18 PM'),
(1, 'Tenda router', '20', '900', '18000.0', '0', '1800.0', '10', '900.0', '900.0', '0', '5.0', '2020-04-28 12:21 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:21 PM'),
(2, 'TVS KEYBOARD', '50', '700', '35000.0', '0', '1750.0', '5', '875.0', '875.0', '0', '2.5', '2020-04-28 12:21 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  12:21 PM'),
(3, 'Hp pendrive (64 gb)', '10', '2000', '20000.0', '0', '1000.0', '5', '500.0', '500.0', '0', '2.5', '2020-04-28 12:21 PM', 'No', '', '2020-04-18', 'LOCAL', 'RJ56 SN7878', '2020-04-28', 'JAIPUR', '686GHGDFH3656', 'SRI KUMARAGURU COMPUTER', 'Anaimallai Main Road, Ambarapalayam, Pollachi ', 'TAMILNADU', '61002', 'Anaimallai Main Road, Ambarapalayam, Pollachi ', '', '2275.0', '2275.0', '0', '78450.0', '4550.0', '73000.0', '300', '600', '0', '0', '3600.0', '80.0', '  12:21 PM'),
(1, 'Tenda router', '5', '900', '4500.0', '0', '450.0', '10', '225.0', '225.0', '0', '5.0', '2020-04-28 02:02 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  02:02 PM'),
(2, 'HP PRINTER', '7', '12000', '84000.0', '0', '10080.0', '12', '5040.0', '5040.0', '0', '6.0', '2020-04-28 02:02 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  02:02 PM'),
(3, 'HP PRINTER', '8', '12000', '96000.0', '0', '11520.0', '12', '5760.0', '5760.0', '0', '6.0', '2020-04-28 02:02 PM', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '  02:02 PM'),
(4, 'Logitech Keyboard', '7', '500', '3500.0', '0', '245.0', '7', '122.5', '122.5', '0', '3.5', '2020-04-28 02:02 PM', 'Yes', '', '2020-04-28', 'LOCAL', 'RJ56 SD9090', '2020-04-28', 'JAIPUR ', '18GHGFHSGF76376', 'SOFT TECH COMPUTER ', 'VIJAY MARG MAIN ROAD JAIPUR ', 'RAJASTHAN', '203303', 'VIJAY MARG MAIN ROAD JAIPUR ', 'ANY ', '11147.5', '11147.5', '0', '213095.0', '22295.0', '188000.0', '1000', '1500', '0', '300', '25400.0', '27.0', '  02:02 PM');

-- --------------------------------------------------------

--
-- Table structure for table `item`
--

CREATE TABLE `item` (
  `id` int(11) NOT NULL,
  `itemcode` varchar(200) NOT NULL,
  `itemname` varchar(200) NOT NULL,
  `hsncode` varchar(200) NOT NULL,
  `unit` varchar(200) NOT NULL,
  `price` varchar(200) NOT NULL,
  `gstvalue` varchar(200) NOT NULL,
  `tax` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `item`
--

INSERT INTO `item` (`id`, `itemcode`, `itemname`, `hsncode`, `unit`, `price`, `gstvalue`, `tax`) VALUES
(5, 'TENDAR', 'Tenda router', 'tendar001', '1', '900', '10', 'hjhjhjhjh'),
(6, 'Intex001', 'Intext mouse', 'intex0013', '1', '200', '5', 'hjhjhjhjh'),
(7, 'Intex001', 'intex keyboard ', 'intex0011', '1', '350', '5', 'hjhjhjhjh'),
(8, 'Tenda003', 'Tenda WIFI', 'tenda001', '1', '600', '10', 'hjhjhjhjh'),
(9, 'Enter001', 'Enter mouse ', 'enter001', '1', '200', '5', 'hjhjhjhjh'),
(10, 'Logitech0002', 'Logitech Keyboard', 'logi001', '1', '500', '7', 'hjhjhjhjh'),
(11, 'Techcom0088', 'Techcom SMPS', 'techco002', '1', '1200', '10', 'hjhjhjhjh'),
(13, 'HP001', 'Hp pendrive (64 gb)', 'hp002', '1', '2000', '5', 'hjhjhjhjh'),
(14, 'HPP02', 'HP PRINTER', 'HP0002', '1', '12000', '12', 'GST 12%'),
(15, 'PHLP01', 'PHILIPS HEADPHONE', '2233', '1', '1000', '20', 'GST 20%'),
(16, 'TVS001', 'TVS KEYBOARD', '9888', '1', '700', '5', 'GST 5%'),
(17, 'HP002', 'HP COLOR PRINTER ', '8889', '1', '30000', '15', 'GST 15%'),
(18, 'TENDA ', 'TENDA ROUTER WIFI', '9090', '1', '2000', '5', 'GST 5%');

-- --------------------------------------------------------

--
-- Table structure for table `tax`
--

CREATE TABLE `tax` (
  `id` int(11) NOT NULL,
  `taxname` varchar(200) NOT NULL,
  `taxvalue` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tax`
--

INSERT INTO `tax` (`id`, `taxname`, `taxvalue`) VALUES
(18, 'GST 5%', '5'),
(19, 'GST 10%', '10'),
(20, 'GST 15%', '15'),
(31, 'GST 9%', '9'),
(33, 'GST 25%', '25');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`) VALUES
('kapil61', 'kapil61');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `datecwise`
--
ALTER TABLE `datecwise`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `financialyear`
--
ALTER TABLE `financialyear`
  ADD PRIMARY KEY (`financialyear`),
  ADD KEY `id` (`id`);

--
-- Indexes for table `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`billno`);

--
-- Indexes for table `invoicein`
--
ALTER TABLE `invoicein`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tax`
--
ALTER TABLE `tax`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT for table `datecwise`
--
ALTER TABLE `datecwise`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `financialyear`
--
ALTER TABLE `financialyear`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `invoice`
--
ALTER TABLE `invoice`
  MODIFY `billno` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `invoicein`
--
ALTER TABLE `invoicein`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `item`
--
ALTER TABLE `item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `tax`
--
ALTER TABLE `tax`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
