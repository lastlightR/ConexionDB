-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 05, 2023 at 02:45 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `jcvd`
--

-- --------------------------------------------------------

--
-- Table structure for table `videojuegos`
--

CREATE TABLE `videojuegos` (
  `Id` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Genero` varchar(50) DEFAULT NULL,
  `FechaLanzamiento` date DEFAULT NULL,
  `Compañia` varchar(50) DEFAULT NULL,
  `Precio` decimal(10,0) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `videojuegos`
--

INSERT INTO `videojuegos` (`Id`, `Nombre`, `Genero`, `FechaLanzamiento`, `Compañia`, `Precio`) VALUES
(1, 'Valorant', 'Shooter', '2023-11-20', 'Riot', 0),
(2, 'League of Legends', 'MOBA', '2023-11-20', 'Riot', 0),
(3, 'Minecraft', 'Mundo Abierto', '2023-11-20', 'Mojang', 20),
(13, 'Hades', 'Roguelite', '2018-12-06', 'Supergiant', 25),
(14, 'Worms Armageddon', 'Estrategia por turnos', '1999-06-01', 'Team 17', 10),
(17, 'Mother 3', 'JRPG', '2006-04-20', 'HAL Laboratory', 10),
(18, 'VVVVVV', 'Platformer', '2010-01-11', 'Nicalis Inc.', 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `videojuegos`
--
ALTER TABLE `videojuegos`
  ADD PRIMARY KEY (`Id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `videojuegos`
--
ALTER TABLE `videojuegos`
  MODIFY `Id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
