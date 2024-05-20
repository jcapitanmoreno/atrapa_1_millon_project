-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-05-2024 a las 18:12:57
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `atrapa_1_millon`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `admin`
--

CREATE TABLE `admin` (
  `user` varchar(255) NOT NULL,
  `pasword` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='tabla separada de las demas solo es por seguridad';

--
-- Volcado de datos para la tabla `admin`
--

INSERT INTO `admin` (`user`, `pasword`) VALUES
('adm', 'adm');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `answer`
--

CREATE TABLE `answer` (
  `questionsID` int(11) NOT NULL,
  `playerID` int(11) NOT NULL,
  `time` date NOT NULL,
  `answerText` varchar(255) NOT NULL,
  `validate` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `answer`
--

INSERT INTO `answer` (`questionsID`, `playerID`, `time`, `answerText`, `validate`) VALUES
(50, 1, '0000-00-00', '1945', 1),
(50, 1, '0000-00-00', '1845', 0),
(50, 1, '0000-00-00', '1955', 0),
(51, 1, '0000-00-00', 'da Vinci.', 1),
(51, 1, '0000-00-00', 'di Caprio', 0),
(51, 1, '0000-00-00', 'di Maria', 0),
(52, 1, '0000-00-00', 'Francia', 1),
(52, 1, '0000-00-00', 'Italia', 0),
(52, 1, '0000-00-00', 'España', 0),
(53, 1, '0000-00-00', 'Italia', 1),
(53, 1, '0000-00-00', 'High Mountain', 0),
(53, 1, '0000-00-00', 'Peru', 0),
(54, 1, '0000-00-00', 'Mandarin', 1),
(54, 1, '0000-00-00', 'Chino', 0),
(54, 1, '0000-00-00', 'Gwulihan', 0),
(55, 1, '0000-00-00', 'España', 1),
(55, 1, '0000-00-00', 'Brasil', 0),
(55, 1, '0000-00-00', 'Argentina', 0),
(56, 1, '0000-00-00', 'Doom', 1),
(56, 1, '0000-00-00', 'Fifa 2003', 0),
(56, 1, '0000-00-00', 'Pong', 0),
(57, 1, '0000-00-00', '3,1416', 1),
(57, 1, '0000-00-00', '3,1406', 0),
(57, 1, '0000-00-00', '3,1614', 0),
(58, 1, '0000-00-00', 'ELA', 1),
(58, 1, '0000-00-00', 'HOLA', 0),
(58, 1, '0000-00-00', 'LEA', 0),
(59, 1, '0000-00-00', '2011', 1),
(59, 1, '0000-00-00', '2009', 0),
(59, 1, '0000-00-00', '2003', 0),
(60, 1, '0000-00-00', 'Enola Gay', 1),
(60, 1, '0000-00-00', 'Betty Boom', 0),
(60, 1, '0000-00-00', 'MOAB', 0),
(61, 108, '0000-00-00', 'Todas son correctas', 1),
(61, 108, '0000-00-00', 'un estudiante', 0),
(61, 108, '0000-00-00', 'kalamity player', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `game`
--

CREATE TABLE `game` (
  `gameID` int(11) NOT NULL,
  `gameDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `game`
--

INSERT INTO `game` (`gameID`, `gameDate`) VALUES
(1, '2024-05-07');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `player`
--

CREATE TABLE `player` (
  `playerID` int(11) NOT NULL,
  `name` varchar(3) NOT NULL,
  `earnedPoints` int(7) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='tabla que almacena los datos del participante';

--
-- Volcado de datos para la tabla `player`
--

INSERT INTO `player` (`playerID`, `name`, `earnedPoints`) VALUES
(1, 'adm', 1000000),
(106, 'adm', 0),
(107, 'adm', 0),
(108, 'JUA', 1000000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `questions`
--

CREATE TABLE `questions` (
  `questionID` int(11) NOT NULL,
  `questionText` varchar(255) NOT NULL,
  `gameID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `questions`
--

INSERT INTO `questions` (`questionID`, `questionText`, `gameID`) VALUES
(50, '¿Cuándo acabó la II Guerra Mundial?', 1),
(51, '¿Quién pintó “la última cena”?', 1),
(52, '¿Dónde se encuentra la famosa Torre Eiffel?', 1),
(53, '¿Qué país tiene forma de bota?', 1),
(54, '¿Cuál es el nombre de la lengua oficial en china?', 1),
(55, '¿Quién ganó el mundial de 2010?', 1),
(56, '¿Que videojuego fue portado en un test de embarazo?', 1),
(57, ' ¿Cuánto vale el número pi?', 1),
(58, '¿Qué enfermedad padeció Stephen Hawking?', 1),
(59, '¿En qué año murió Steve Jobs?', 1),
(60, '¿Como se llama el avion que tiro la bomba de Hiroshima?', 1),
(61, '¿Quien es Moises Jimenez?', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `answer`
--
ALTER TABLE `answer`
  ADD KEY `playerID` (`playerID`),
  ADD KEY `questionsID` (`questionsID`);

--
-- Indices de la tabla `game`
--
ALTER TABLE `game`
  ADD PRIMARY KEY (`gameID`);

--
-- Indices de la tabla `player`
--
ALTER TABLE `player`
  ADD PRIMARY KEY (`playerID`);

--
-- Indices de la tabla `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`questionID`),
  ADD KEY `questionsID` (`gameID`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `game`
--
ALTER TABLE `game`
  MODIFY `gameID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `player`
--
ALTER TABLE `player`
  MODIFY `playerID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT de la tabla `questions`
--
ALTER TABLE `questions`
  MODIFY `questionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `answer`
--
ALTER TABLE `answer`
  ADD CONSTRAINT `answer_ibfk_1` FOREIGN KEY (`questionsID`) REFERENCES `questions` (`questionID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `answer_ibfk_2` FOREIGN KEY (`playerID`) REFERENCES `player` (`playerID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `questions`
--
ALTER TABLE `questions`
  ADD CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`gameID`) REFERENCES `game` (`gameID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
