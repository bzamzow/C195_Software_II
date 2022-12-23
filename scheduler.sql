-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 21, 2022 at 12:46 PM
-- Server version: 5.7.23-23
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `scheduler`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `Appointment_ID` int(11) NOT NULL,
  `Title` varchar(50) DEFAULT NULL,
  `Description` varchar(50) DEFAULT NULL,
  `Location` varchar(50) DEFAULT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Start` datetime DEFAULT NULL,
  `End` datetime DEFAULT NULL,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Updated` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL,
  `Customer_ID` int(11) DEFAULT NULL,
  `User_ID` int(11) DEFAULT NULL,
  `Contact_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `appointments`
--

INSERT INTO `appointments` (`Appointment_ID`, `Title`, `Description`, `Location`, `Type`, `Start`, `End`, `Create_Date`, `Created_By`, `Last_Updated`, `Last_Updated_By`, `Customer_ID`, `User_ID`, `Contact_ID`) VALUES
(11, 'Sell House', 'Selling property on 123 Hysteria Ln', '123 Hysteria Ln', 'Meeting', '2022-12-18 11:15:00', '2022-12-18 12:30:00', '2022-12-13 00:00:00', 'jz.funzies', '2022-12-18 17:09:34', 'jz.funzies', 8, 1, 3),
(14, 'TestUTC', 'testing utc time', 'home', 'meeting', '2022-12-19 17:00:00', '2022-12-20 18:45:00', '2022-12-19 11:24:28', 'jz.funzies', '2022-12-19 17:46:56', 'jz.funzies', 8, 1, 2),
(15, 'TestUTC2', 'testing utc time', 'home', 'meeting', '2022-12-20 16:15:00', '2022-12-20 17:45:00', '2022-12-19 11:25:52', 'jz.funzies', '2022-12-19 17:36:21', 'jz.funzies', 8, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `contacts`
--

CREATE TABLE `contacts` (
  `Contact_ID` int(11) NOT NULL,
  `Contact_Name` varchar(50) DEFAULT NULL,
  `Email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `contacts`
--

INSERT INTO `contacts` (`Contact_ID`, `Contact_Name`, `Email`) VALUES
(1, 'Bret Zamzow', 'bzamzow@wgu.edu'),
(2, 'Francis Devill', 'fdevill@company.com'),
(3, 'Jason Bourne', 'jbourne@cia.gov');

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries` (
  `Country_ID` int(11) NOT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Update` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `countries`
--

INSERT INTO `countries` (`Country_ID`, `Country`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`) VALUES
(1, 'Canada', '2022-11-25 00:00:00', 'Bret Zamzow', '2022-11-26 01:49:31', 'Bret Zamzow'),
(2, 'United Kingdom', '2021-11-25 00:00:00', 'Bret Zamzow', '2022-11-26 01:49:31', 'Bret Zamzow'),
(3, 'United States', '2020-11-25 00:00:00', 'Bret Zamzow', '2022-11-26 01:49:31', 'Bret Zamzow');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `Customer_ID` int(11) NOT NULL,
  `Customer_Name` varchar(50) DEFAULT NULL,
  `Address` varchar(100) DEFAULT NULL,
  `Postal_Code` varchar(50) DEFAULT NULL,
  `Phone` varchar(50) DEFAULT NULL,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Update` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL,
  `Division_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`Customer_ID`, `Customer_Name`, `Address`, `Postal_Code`, `Phone`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`, `Division_ID`) VALUES
(6, 'John Zamzow', 'Bosseyed 3', '33564', '7572562921', '2022-06-14 00:00:00', 'Bret Zamzow', '2022-12-13 17:51:12', 'jz.funzies', 1),
(7, 'Steve Zamzow', '223 B. Baker', 'UNK', '+897572562921', '2022-11-27 00:00:00', 'jz.funzies', '2022-12-13 17:46:33', 'jz.funzies', 2),
(8, 'Bret Zamzow', '2239 Moonlight Pt', '23185', '7572562921', '2022-12-13 00:00:00', 'jz.funzies', '2022-12-13 21:08:36', 'jz.funzies', 4),
(18, 'Richard Zamzow', '345 Diagonal Rd', '23665', '2257558545', '2022-12-13 00:00:00', 'jz.funzies', '2022-12-13 19:47:04', 'jz.funzies', 1);

-- --------------------------------------------------------

--
-- Table structure for table `first-level divisions`
--

CREATE TABLE `first-level divisions` (
  `Division_ID` int(11) NOT NULL,
  `Division` varchar(50) DEFAULT NULL,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Update` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL,
  `Country_ID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `first-level divisions`
--

INSERT INTO `first-level divisions` (`Division_ID`, `Division`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`, `Country_ID`) VALUES
(1, 'Montreal', '2022-11-25 00:00:00', 'Bret Zamzow', '2022-11-26 01:56:47', 'Bret Zamzow', 1),
(2, 'London', '2021-11-25 00:00:00', 'Bret Zamzow', '2022-11-26 01:56:47', 'Bret Zamzow', 2),
(3, 'Phoenix', '2020-11-25 00:00:00', 'Bret Zamzow', '2022-11-26 01:56:47', 'Bret Zamzow', 3),
(4, 'White Plains', '2020-11-25 00:00:00', 'Bret Zamzow', '2022-11-26 01:56:47', 'Bret Zamzow', 3);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `User_ID` int(11) NOT NULL,
  `User_Name` varchar(50) DEFAULT NULL,
  `Password` text,
  `Create_Date` datetime DEFAULT NULL,
  `Created_By` varchar(50) DEFAULT NULL,
  `Last_Update` timestamp NULL DEFAULT NULL,
  `Last_Updated_By` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`User_ID`, `User_Name`, `Password`, `Create_Date`, `Created_By`, `Last_Update`, `Last_Updated_By`) VALUES
(1, 'jz.funzies', 'hahaha123', '2022-01-02 00:00:00', 'Bret Zamzow', '2022-11-26 02:17:33', 'Bret Zamzow'),
(2, 'hj.hurah', 'nope5678', '2022-02-02 00:00:00', 'Bret Zamzow', '2022-11-26 02:17:33', 'Bret Zamzow'),
(3, 'af.huah', 'bye9876', '2022-03-02 00:00:00', 'Bret Zamzow', '2022-11-26 02:17:33', 'Bret Zamzow');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`Appointment_ID`),
  ADD KEY `Customer_ID_idx` (`Customer_ID`),
  ADD KEY `User_ID_idx` (`User_ID`),
  ADD KEY `Contact_ID_idx` (`Contact_ID`);

--
-- Indexes for table `contacts`
--
ALTER TABLE `contacts`
  ADD PRIMARY KEY (`Contact_ID`);

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`Country_ID`);

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`Customer_ID`),
  ADD KEY `Division_ID_idx` (`Division_ID`);

--
-- Indexes for table `first-level divisions`
--
ALTER TABLE `first-level divisions`
  ADD PRIMARY KEY (`Division_ID`),
  ADD KEY `Country_ID_idx` (`Country_ID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`User_ID`),
  ADD UNIQUE KEY `User_Name_UNIQUE` (`User_Name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appointments`
--
ALTER TABLE `appointments`
  MODIFY `Appointment_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `contacts`
--
ALTER TABLE `contacts`
  MODIFY `Contact_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `Country_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `Customer_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `first-level divisions`
--
ALTER TABLE `first-level divisions`
  MODIFY `Division_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `User_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `Contact_ID` FOREIGN KEY (`Contact_ID`) REFERENCES `contacts` (`Contact_ID`),
  ADD CONSTRAINT `Customer_ID` FOREIGN KEY (`Customer_ID`) REFERENCES `customers` (`Customer_ID`),
  ADD CONSTRAINT `User_ID` FOREIGN KEY (`User_ID`) REFERENCES `users` (`User_ID`);

--
-- Constraints for table `customers`
--
ALTER TABLE `customers`
  ADD CONSTRAINT `Division_ID` FOREIGN KEY (`Division_ID`) REFERENCES `first-level divisions` (`Division_ID`);

--
-- Constraints for table `first-level divisions`
--
ALTER TABLE `first-level divisions`
  ADD CONSTRAINT `Country_ID` FOREIGN KEY (`Country_ID`) REFERENCES `countries` (`Country_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
