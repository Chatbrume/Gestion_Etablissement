-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 01 avr. 2021 à 15:44
-- Version du serveur :  8.0.21
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestionetablissement`
--

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `id` int NOT NULL AUTO_INCREMENT,
  `coursesubject` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `nbhours` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `course`
--

INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES
(1, 'informatique', 25),
(79, 'Français', 2),
(80, 'Enseignement moral et civique', 3),
(81, 'EPS', 2),
(82, 'Anglais', 1),
(83, 'Espagnol', 2),
(85, 'Physique chimie', 2),
(86, 'SVT', 2),
(87, 'Mathématiques', 4),
(88, 'Java', 9),
(90, 'Informatique', 25);

-- --------------------------------------------------------

--
-- Structure de la table `course_person`
--

DROP TABLE IF EXISTS `course_person`;
CREATE TABLE IF NOT EXISTS `course_person` (
  `idPerson` int NOT NULL,
  `idCourse` int NOT NULL,
  PRIMARY KEY (`idPerson`,`idCourse`),
  KEY `fk_person_course` (`idCourse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `course_person`
--

INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES
(117, 79),
(118, 80),
(119, 81),
(120, 82),
(121, 83),
(118, 87);

-- --------------------------------------------------------

--
-- Structure de la table `mark`
--

DROP TABLE IF EXISTS `mark`;
CREATE TABLE IF NOT EXISTS `mark` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idStudent` int NOT NULL,
  `idCourse` int NOT NULL,
  `mark` float NOT NULL,
  `assessment` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_mark_person` (`idStudent`),
  KEY `fk_mark_course` (`idCourse`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE IF NOT EXISTS `person` (
  `id` int NOT NULL AUTO_INCREMENT,
  `firstname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `role` int NOT NULL DEFAULT '4',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `dateofbirth` date DEFAULT NULL,
  `subjecttaught` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`) VALUES
(6, 'Chaumont', 'Eric', 'e.chaumont@ensup.eu', '34 rue du général De Gaule 75001', '0641458596', 1, 'e.chaumont', NULL, NULL),
(7, 'Demaison', 'Magalie', 'm.demaison@ensup.eu', '124 avenue de Paris 75016', '0685749685', 2, 'm.demaison', NULL, NULL),
(117, 'Parker', 'John', 'j.parker@ensup.eu', '25 rue des fleurs 54100', '0641458596', 4, 'j.parker', '2001-03-08', NULL),
(118, 'Mark', 'Ruben', 'm.ruben@ensup.eu', '10 bis avenue du bois 41000', '0685984552', 4, 'm.ruben', '2020-10-01', NULL),
(119, 'Chaumez', 'Bernard', 'b.chaumez@ensup.eu', '53 rue de Saint Germain 95550', '0612539874', 4, 'b.chaumez', '2000-08-11', NULL),
(120, 'Gigon', 'Bernadette', 'b.gigon@ensup.eu', '36 quai des orfévres 78100', '0605248587', 4, 'b.gigon', '1995-06-22', NULL),
(121, 'Moulin', 'Pierre', 'p.moulin@ensup.eu', '10 rue Jean Moulin 95630', '0601854596', 4, 'p.moulin', '1997-02-11', NULL),
(127, 'Dupont', 'Pierre', 'pdp@mail.gc', '5 rue des prés', '0823654819', 4, '123456', '1999-06-06', NULL),
(134, 'a', 'a', 'a', 'a', '12345678', 4, 'a', '1993-03-06', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `school`
--

DROP TABLE IF EXISTS `school`;
CREATE TABLE IF NOT EXISTS `school` (
  `id` int NOT NULL AUTO_INCREMENT,
  `surname` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `director` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_person_school` (`director`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Déchargement des données de la table `school`
--

INSERT INTO `school` (`id`, `surname`, `email`, `address`, `phone`, `director`) VALUES
(4, 'Ensup', 'ensup@gmail.com', '1 bis Avenue du 8 mai 1945', '0161380575', 6);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `course_person`
--
ALTER TABLE `course_person`
  ADD CONSTRAINT `fk_course_person` FOREIGN KEY (`idPerson`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_person_course` FOREIGN KEY (`idCourse`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `mark`
--
ALTER TABLE `mark`
  ADD CONSTRAINT `fk_mark_course` FOREIGN KEY (`idCourse`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_mark_person` FOREIGN KEY (`idStudent`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `school`
--
ALTER TABLE `school`
  ADD CONSTRAINT `fk_person_school` FOREIGN KEY (`director`) REFERENCES `person` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
