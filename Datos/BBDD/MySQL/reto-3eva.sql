-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-04-2023 a las 18:42:31
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
  `Contraseña` varchar(255) NOT NULL,
  `Fecha_Nacimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`DNI`, `Nombre`, `Apellidos`, `Correo`, `Contraseña`, `Fecha_Nacimiento`) VALUES
('11111111A', 'a', 'a', 'aaa@aaa.com', '895b317c76b8e504c2fb32dbb4420178f60ce321', '2004-10-15'),
('12345678A', 'Aitor', 'Etxaide Baroja', 'aitoretxaide@a.com', '2be88ca4242c76e8253ac62474851065032d6833', '2023-10-15'),
('22222222A', 'Pepe', 'Perez', 'pperez@hotmail.com', '895b317c76b8e504c2fb32dbb4420178f60ce321', '2004-04-23');

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

--
-- Volcado de datos para la tabla `clinica`
--

INSERT INTO `clinica` (`ID_Clinica`, `Nombre_Clinica`, `Direccion`, `Telefono`) VALUES
(1, 'Clinica Etxaide', ' Paseo Fanderia 5', 617606457),
(2, 'PruebaCl2', 'NoSe', 32);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleados`
--

CREATE TABLE `empleados` (
  `DNI_Emp` varchar(9) NOT NULL,
  `Nombre` varchar(255) NOT NULL,
  `Apellidos` varchar(255) NOT NULL,
  `Correo` varchar(255) NOT NULL,
  `Contraseña` varchar(255) NOT NULL,
  `Fecha_Nacimiento` date NOT NULL,
  `ID_Puesto` int(3) NOT NULL,
  `ID_Clinica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `empleados`
--

INSERT INTO `empleados` (`DNI_Emp`, `Nombre`, `Apellidos`, `Correo`, `Contraseña`, `Fecha_Nacimiento`, `ID_Puesto`, `ID_Clinica`) VALUES
('12345678B', 'Julen', 'Cano', 'jcano@gmail.com', '2be88ca4242c76e8253ac62474851065032d6833', '2004-11-17', 1, 1),
('12345678C', 'Arnold', 'Bermel', 'abermell@yahoo.com', '2be88ca4242c76e8253ac62474851065032d6833', '2008-07-14', 2, 2);

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
  `ID_Clinica` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `habitaciones`
--

INSERT INTO `habitaciones` (`ID_Habitacion`, `Num_Habitacion`, `Especialidad`, `ID_Clinica`) VALUES
(1, 1, 'Ortodoncias', 1),
(2, 2, 'Prueba', 1),
(3, 3, 'Limpiezas', 1),
(4, 4, 'Rayos-X', 1),
(5, 1, 'ClinicasSecundarias', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_cliente`
--

CREATE TABLE `historial_cliente` (
  `DNI` varchar(9) NOT NULL,
  `Fecha_Revision` date NOT NULL,
  `Observaciones` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puestos`
--

CREATE TABLE `puestos` (
  `ID_Puesto` int(3) NOT NULL,
  `Nombre_Puesto` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
(1, '11111111A', '2023-04-22', '12:00:00'),
(1, '11111111A', '2023-10-15', '11:30:00'),
(1, '12345678A', '2023-04-24', '12:00:00'),
(1, '12345678A', '2023-04-24', '19:45:00'),
(1, '12345678A', '2023-04-25', '14:00:00'),
(1, '12345678A', '2023-10-15', '12:01:00'),
(1, '12345678A', '2023-10-15', '12:02:00'),
(1, '12345678A', '2023-10-15', '12:03:00'),
(1, '12345678A', '2023-10-15', '12:31:00'),
(2, '12345678A', '2023-04-25', '14:20:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `telefonos`
--

CREATE TABLE `telefonos` (
  `DNI` varchar(9) NOT NULL,
  `Telefono` int(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `telefonos`
--

INSERT INTO `telefonos` (`DNI`, `Telefono`) VALUES
('11111111A', 33),
('12345678A', 666666666),
('22222222A', 617658745);

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
-- Indices de la tabla `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`DNI_Emp`),
  ADD KEY `ID_Clinica` (`ID_Clinica`),
  ADD KEY `ID_Puesto` (`ID_Puesto`);

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
  ADD PRIMARY KEY (`ID_Habitacion`,`ID_Clinica`),
  ADD KEY `ID_Clinica` (`ID_Clinica`);

--
-- Indices de la tabla `historial_cliente`
--
ALTER TABLE `historial_cliente`
  ADD PRIMARY KEY (`DNI`,`Fecha_Revision`);

--
-- Indices de la tabla `puestos`
--
ALTER TABLE `puestos`
  ADD PRIMARY KEY (`ID_Puesto`);

--
-- Indices de la tabla `realizacitas`
--
ALTER TABLE `realizacitas`
  ADD PRIMARY KEY (`ID_Clinica`,`Fecha_Cita`,`Hora_Cita`),
  ADD KEY `DNI_Cliente` (`DNI_Cliente`);

--
-- Indices de la tabla `telefonos`
--
ALTER TABLE `telefonos`
  ADD PRIMARY KEY (`DNI`,`Telefono`),
  ADD KEY `DNI` (`DNI`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clinica`
--
ALTER TABLE `clinica`
  MODIFY `ID_Clinica` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `equipamiento`
--
ALTER TABLE `equipamiento`
  MODIFY `ID_Equipamiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `habitaciones`
--
ALTER TABLE `habitaciones`
  MODIFY `ID_Habitacion` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `puestos`
--
ALTER TABLE `puestos`
  MODIFY `ID_Puesto` int(3) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

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
-- Filtros para la tabla `historial_cliente`
--
ALTER TABLE `historial_cliente`
  ADD CONSTRAINT `historial_cliente_ibfk_1` FOREIGN KEY (`DNI`) REFERENCES `cliente` (`DNI`);

--
-- Filtros para la tabla `realizacitas`
--
ALTER TABLE `realizacitas`
  ADD CONSTRAINT `realizacitas_ibfk_1` FOREIGN KEY (`ID_Clinica`) REFERENCES `clinica` (`ID_Clinica`),
  ADD CONSTRAINT `realizacitas_ibfk_3` FOREIGN KEY (`DNI_Cliente`) REFERENCES `cliente` (`DNI`);

--
-- Filtros para la tabla `telefonos`
--
ALTER TABLE `telefonos`
  ADD CONSTRAINT `telefonos_ibfk_1` FOREIGN KEY (`DNI`) REFERENCES `cliente` (`DNI`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
