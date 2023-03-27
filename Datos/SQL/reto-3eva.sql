-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 27-03-2023 a las 14:21:57
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
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `DNI` varchar(9) NOT NULL,
  `Nombre` varchar(255) NOT NULL,
  `Apellidos` varchar(255) NOT NULL,
  `Correo` varchar(255) NOT NULL,
  `Fecha_Nacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clinica`
--

CREATE TABLE `clinica` (
  `ID_Clinica` int(11) NOT NULL,
  `Nombre_Clinica` varchar(255) NOT NULL,
  `Direccion` varchar(255) NOT NULL,
  `Telefono` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credencialesempleados`
--

CREATE TABLE `credencialesempleados` (
  `Usuario` varchar(255) NOT NULL,
  `Contraseña` varchar(255) NOT NULL,
  `DNI_Emp` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `DNI_Emp` varchar(9) NOT NULL,
  `Num_Habitacion` int(3) NOT NULL,
  `Nombre` varchar(255) NOT NULL,
  `Apellidos` varchar(255) NOT NULL,
  `Correo` varchar(255) NOT NULL,
  `Fecha_Nacimiento` date NOT NULL,
  `Puesto` varchar(255) NOT NULL,
  `ID_Clinica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `equipamiento`
--

CREATE TABLE `equipamiento` (
  `ID_Equipamiento` int(11) NOT NULL,
  `Nombre_Equipamiento` varchar(255) NOT NULL,
  `Precio` float NOT NULL,
  `Stock` int(7) NOT NULL,
  `ID_Clinica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitaciones`
--

CREATE TABLE `habitaciones` (
  `ID_Habitacion` int(11) NOT NULL,
  `Num_Habitacion` int(3) NOT NULL,
  `Especialidad` varchar(255) NOT NULL,
  `ID_Clinica` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `realizacitas`
--

CREATE TABLE `realizacitas` (
  `ID_Clinica` int(11) NOT NULL,
  `ID_Habitacion` int(11) NOT NULL,
  `DNI_Cliente` varchar(255) NOT NULL,
  `Fecha_Cita` date NOT NULL,
  `Hora_Cita` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`DNI`);

--
-- Indices de la tabla `clinica`
--
ALTER TABLE `clinica`
  ADD PRIMARY KEY (`ID_Clinica`);

--
-- Indices de la tabla `credencialesempleados`
--
ALTER TABLE `credencialesempleados`
  ADD PRIMARY KEY (`Usuario`),
  ADD KEY `DNI_Emp` (`DNI_Emp`);

--
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`DNI_Emp`),
  ADD KEY `ID_Clinica` (`ID_Clinica`);

--
-- Indices de la tabla `equipamiento`
--
ALTER TABLE `equipamiento`
  ADD PRIMARY KEY (`ID_Equipamiento`,`ID_Clinica`),
  ADD KEY `ID_Clinica` (`ID_Clinica`);

--
-- Indices de la tabla `habitaciones`
--
ALTER TABLE `habitaciones`
  ADD PRIMARY KEY (`ID_Habitacion`),
  ADD KEY `ID_Clinica` (`ID_Clinica`);

--
-- Indices de la tabla `realizacitas`
--
ALTER TABLE `realizacitas`
  ADD PRIMARY KEY (`ID_Clinica`,`ID_Habitacion`,`Fecha_Cita`,`Hora_Cita`),
  ADD KEY `ID_Habitacion` (`ID_Habitacion`),
  ADD KEY `DNI_Cliente` (`DNI_Cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clinica`
--
ALTER TABLE `clinica`
  MODIFY `ID_Clinica` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `equipamiento`
--
ALTER TABLE `equipamiento`
  MODIFY `ID_Equipamiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `habitaciones`
--
ALTER TABLE `habitaciones`
  MODIFY `ID_Habitacion` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `credencialesempleados`
--
ALTER TABLE `credencialesempleados`
  ADD CONSTRAINT `credencialesempleados_ibfk_1` FOREIGN KEY (`DNI_Emp`) REFERENCES `empleados` (`DNI_Emp`);

--
-- Filtros para la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `empleados_ibfk_1` FOREIGN KEY (`ID_Clinica`) REFERENCES `clinica` (`ID_Clinica`);

--
-- Filtros para la tabla `equipamiento`
--
ALTER TABLE `equipamiento`
  ADD CONSTRAINT `equipamiento_ibfk_1` FOREIGN KEY (`ID_Clinica`) REFERENCES `clinica` (`ID_Clinica`);

--
-- Filtros para la tabla `habitaciones`
--
ALTER TABLE `habitaciones`
  ADD CONSTRAINT `habitaciones_ibfk_1` FOREIGN KEY (`ID_Clinica`) REFERENCES `clinica` (`ID_Clinica`);

--
-- Filtros para la tabla `realizacitas`
--
ALTER TABLE `realizacitas`
  ADD CONSTRAINT `realizacitas_ibfk_1` FOREIGN KEY (`ID_Clinica`) REFERENCES `clinica` (`ID_Clinica`),
  ADD CONSTRAINT `realizacitas_ibfk_2` FOREIGN KEY (`ID_Habitacion`) REFERENCES `habitaciones` (`ID_Habitacion`),
  ADD CONSTRAINT `realizacitas_ibfk_3` FOREIGN KEY (`DNI_Cliente`) REFERENCES `cliente` (`DNI`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
