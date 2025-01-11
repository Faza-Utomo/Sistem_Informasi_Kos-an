-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 11, 2025 at 11:52 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sikosan_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `kamar`
--

CREATE TABLE `kamar` (
  `IdKamar` int(8) NOT NULL,
  `noKamar` varchar(10) NOT NULL,
  `harga` varchar(15) NOT NULL,
  `jenisKamar` varchar(20) NOT NULL,
  `ketersediaan` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kamar`
--

INSERT INTO `kamar` (`IdKamar`, `noKamar`, `harga`, `jenisKamar`, `ketersediaan`) VALUES
(111, '321wadsa', '100000', 'sa das ', 'sadas'),
(123, '1', '1000000', 'Luxury', 'Tersedia'),
(124, '2', '1000000', 'biasa', 'Tersedia'),
(321, '321321', '2131', '21321', '2131'),
(324, '5', '321412', 'sadas', 'sdadas'),
(662, '4', '232412', 'sda', 'dsa'),
(3213, '321', '3123', '12312', '1231'),
(3321, '421', '321', '3231', '32131'),
(32134142, '41412', '31231', '3213123', '213');

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE `pegawai` (
  `IdPegawai` int(8) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `noHp` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`IdPegawai`, `nama`, `noHp`, `email`, `password`) VALUES
(123, 'aceng gondok', '889', 'aceng@gmail.com', 'aceng123'),
(3213, '1312', '21312', '1231', '3123');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `IdPembayaran` int(8) NOT NULL,
  `IdReservasi` int(8) NOT NULL,
  `tglPembayaran` date NOT NULL,
  `metodePembayaran` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`IdPembayaran`, `IdReservasi`, `tglPembayaran`, `metodePembayaran`) VALUES
(1, 1, '2025-01-11', 'Gopay');

-- --------------------------------------------------------

--
-- Table structure for table `penghuni`
--

CREATE TABLE `penghuni` (
  `IdPenghuni` int(8) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `noHp` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `penghuni`
--

INSERT INTO `penghuni` (`IdPenghuni`, `nama`, `noHp`, `email`, `password`) VALUES
(1, 'usep bejo', '00139218739', 'usep@gmail.com', 'usep123');

-- --------------------------------------------------------

--
-- Table structure for table `reservasi`
--

CREATE TABLE `reservasi` (
  `IdReservasi` int(8) NOT NULL,
  `IdPenghuni` int(8) NOT NULL,
  `IdKamar` int(8) NOT NULL,
  `lama_sewa` int(3) NOT NULL,
  `satuan_sewa` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservasi`
--

INSERT INTO `reservasi` (`IdReservasi`, `IdPenghuni`, `IdKamar`, `lama_sewa`, `satuan_sewa`) VALUES
(1, 1, 123, 2, 'Bulan');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `kamar`
--
ALTER TABLE `kamar`
  ADD PRIMARY KEY (`IdKamar`);

--
-- Indexes for table `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`IdPegawai`);

--
-- Indexes for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD PRIMARY KEY (`IdPembayaran`),
  ADD UNIQUE KEY `IdReservasi` (`IdReservasi`);

--
-- Indexes for table `penghuni`
--
ALTER TABLE `penghuni`
  ADD PRIMARY KEY (`IdPenghuni`);

--
-- Indexes for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD PRIMARY KEY (`IdReservasi`),
  ADD UNIQUE KEY `IdPenghuni` (`IdPenghuni`,`IdKamar`),
  ADD UNIQUE KEY `IdKamar` (`IdKamar`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `kamar`
--
ALTER TABLE `kamar`
  MODIFY `IdKamar` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32134143;

--
-- AUTO_INCREMENT for table `pegawai`
--
ALTER TABLE `pegawai`
  MODIFY `IdPegawai` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3214;

--
-- AUTO_INCREMENT for table `pembayaran`
--
ALTER TABLE `pembayaran`
  MODIFY `IdPembayaran` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `penghuni`
--
ALTER TABLE `penghuni`
  MODIFY `IdPenghuni` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reservasi`
--
ALTER TABLE `reservasi`
  MODIFY `IdReservasi` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `pembayaran`
--
ALTER TABLE `pembayaran`
  ADD CONSTRAINT `pembayaran_ibfk_1` FOREIGN KEY (`IdReservasi`) REFERENCES `reservasi` (`IdReservasi`);

--
-- Constraints for table `reservasi`
--
ALTER TABLE `reservasi`
  ADD CONSTRAINT `reservasi_ibfk_2` FOREIGN KEY (`IdKamar`) REFERENCES `kamar` (`IdKamar`),
  ADD CONSTRAINT `reservasi_ibfk_3` FOREIGN KEY (`IdPenghuni`) REFERENCES `penghuni` (`IdPenghuni`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
