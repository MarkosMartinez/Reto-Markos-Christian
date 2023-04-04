-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-04-2023 a las 15:36:25
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `reto-3eva`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `realizacitas`
--

CREATE TABLE `realizacitas` (
  `ID_Clinica` int(11) NOT NULL,
  `DNI_Cliente` varchar(255) NOT NULL,
  `Fecha_Cita` date NOT NULL,
  `Hora_Cita` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `realizacitas`
--

INSERT INTO `realizacitas` (`ID_Clinica`, `DNI_Cliente`, `Fecha_Cita`, `Hora_Cita`) VALUES
(1, '12345678A', '2023-10-15', '12:00:00'),
(1, '12345678A', '2023-10-15', '12:01:00'),
(1, '12345678A', '2023-10-15', '12:02:00'),
(1, '12345678A', '2023-10-15', '12:03:00'),
(1, '12345678A', '2023-10-15', '12:31:00');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `realizacitas`
--
ALTER TABLE `realizacitas`
  ADD PRIMARY KEY (`ID_Clinica`,`Fecha_Cita`,`Hora_Cita`),
  ADD KEY `DNI_Cliente` (`DNI_Cliente`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `realizacitas`
--
ALTER TABLE `realizacitas`
  ADD CONSTRAINT `realizacitas_ibfk_1` FOREIGN KEY (`ID_Clinica`) REFERENCES `clinica` (`ID_Clinica`),
  ADD CONSTRAINT `realizacitas_ibfk_3` FOREIGN KEY (`DNI_Cliente`) REFERENCES `cliente` (`DNI`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
