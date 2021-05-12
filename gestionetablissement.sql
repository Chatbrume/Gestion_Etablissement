-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 11 mai 2021 à 10:19
-- Version du serveur :  10.4.18-MariaDB
-- Version de PHP : 8.0.3

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
CREATE DATABASE IF NOT EXISTS `gestionetablissement` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestionetablissement`;

-- --------------------------------------------------------

--
-- Structure de la table `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL,
  `coursesubject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbhours` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS POUR LA TABLE `course`:
--

--
-- Déchargement des données de la table `course`
--

INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES(79, 'Français', 2);
INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES(80, 'Enseignement moral et civique', 3);
INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES(81, 'EPS', 4);
INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES(82, 'Anglais', 1);
INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES(83, 'Espagnol', 2);
INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES(85, 'Physique chimie', 2);
INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES(86, 'SVT', 2);
INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES(87, 'Mathématiques', 4);
INSERT INTO `course` (`id`, `coursesubject`, `nbhours`) VALUES(88, 'Java', 9);

-- --------------------------------------------------------

--
-- Structure de la table `course_person`
--

DROP TABLE IF EXISTS `course_person`;
CREATE TABLE `course_person` (
  `idPerson` int(11) NOT NULL,
  `idCourse` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS POUR LA TABLE `course_person`:
--   `idPerson`
--       `person` -> `id`
--   `idCourse`
--       `course` -> `id`
--

--
-- Déchargement des données de la table `course_person`
--

INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES(117, 79);
INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES(117, 88);
INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES(118, 80);
INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES(118, 87);
INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES(119, 81);
INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES(119, 83);
INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES(120, 82);
INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES(121, 83);
INSERT INTO `course_person` (`idPerson`, `idCourse`) VALUES(127, 87);

-- --------------------------------------------------------

--
-- Structure de la table `mark`
--

DROP TABLE IF EXISTS `mark`;
CREATE TABLE `mark` (
  `id` int(11) NOT NULL,
  `idStudent` int(11) NOT NULL,
  `idCourse` int(11) NOT NULL,
  `mark` float DEFAULT NULL,
  `assessment` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- RELATIONS POUR LA TABLE `mark`:
--   `idCourse`
--       `course` -> `id`
--   `idStudent`
--       `person` -> `id`
--

--
-- Déchargement des données de la table `mark`
--

INSERT INTO `mark` (`id`, `idStudent`, `idCourse`, `mark`, `assessment`) VALUES(1, 117, 79, 10, '');
INSERT INTO `mark` (`id`, `idStudent`, `idCourse`, `mark`, `assessment`) VALUES(3, 117, 88, 12, '');
INSERT INTO `mark` (`id`, `idStudent`, `idCourse`, `mark`, `assessment`) VALUES(4, 117, 88, 14, '');
INSERT INTO `mark` (`id`, `idStudent`, `idCourse`, `mark`, `assessment`) VALUES(5, 117, 79, 8, '');

-- --------------------------------------------------------

--
-- Structure de la table `person`
--

DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL,
  `firstname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `lastname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `role` int(11) NOT NULL DEFAULT 4,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `dateofbirth` date DEFAULT NULL,
  `subjecttaught` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `average` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS POUR LA TABLE `person`:
--

--
-- Déchargement des données de la table `person`
--

INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES(6, 'Chaumont', 'Eric', 'e.chaumont@ensup.eu', '34 rue du général De Gaule 75001', '0641458596', 1, 'e.chaumont', NULL, NULL, 0);
INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES(7, 'Demaison', 'Magalie', 'm.demaison@ensup.eu', '124 avenue de Paris 75016', '0685749685', 2, 'm.demaison', NULL, NULL, 0);
INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES(117, 'Parker', 'John', 'j.parker@ensup.eu', '25 rue des fleurs 54100', '0641458596', 4, 'j.parker', '2001-03-08', NULL, 0);
INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES(118, 'Mark', 'Ruben', 'm.ruben@ensup.eu', '10 bis avenue du bois 41000', '0685984552', 4, 'm.ruben', '2020-10-01', NULL, 0);
INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES(119, 'Chaumez', 'Bernard', 'b.chaumez@ensup.eu', '53 rue de Saint Germain 95550', '0612539874', 4, 'b.chaumez', '2000-08-11', NULL, 0);
INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES(120, 'Gigon', 'Bernadette', 'b.gigon@ensup.eu', '36 quai des orfévres 78100', '0605248587', 4, 'b.gigon', '1995-06-22', NULL, 0);
INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES(121, 'Moulin', 'Pierre', 'p.moulin@ensup.eu', '10 rue Jean Moulin 95630', '0601854596', 4, 'p.moulin', '1997-02-11', NULL, 0);
INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES(127, 'Dupont', 'Pierre', 'pdp@mail.gc', '5 rue des prés', '0823654819', 4, '123456', '1999-06-06', NULL, 0);
INSERT INTO `person` (`id`, `firstname`, `lastname`, `email`, `address`, `phone`, `role`, `password`, `dateofbirth`, `subjecttaught`, `average`) VALUES(134, 'a', 'a', 'a', 'a', '12345678', 1, 'a', '1993-03-06', NULL, 0);

-- --------------------------------------------------------

--
-- Structure de la table `school`
--

DROP TABLE IF EXISTS `school`;
CREATE TABLE `school` (
  `id` int(11) NOT NULL,
  `surname` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `director` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- RELATIONS POUR LA TABLE `school`:
--   `director`
--       `person` -> `id`
--

--
-- Déchargement des données de la table `school`
--

INSERT INTO `school` (`id`, `surname`, `email`, `address`, `phone`, `director`) VALUES(4, 'Ensup', 'ensup@gmail.com', '1 bis Avenue du 8 mai 1945', '0161380575', 6);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `course_person`
--
ALTER TABLE `course_person`
  ADD PRIMARY KEY (`idPerson`,`idCourse`),
  ADD KEY `fk_person_course` (`idCourse`);

--
-- Index pour la table `mark`
--
ALTER TABLE `mark`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idStudent` (`idStudent`) USING BTREE,
  ADD KEY `idCourse` (`idCourse`) USING BTREE;

--
-- Index pour la table `person`
--
ALTER TABLE `person`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Index pour la table `school`
--
ALTER TABLE `school`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_person_school` (`director`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `course`
--
ALTER TABLE `course`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=89;

--
-- AUTO_INCREMENT pour la table `mark`
--
ALTER TABLE `mark`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `person`
--
ALTER TABLE `person`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=135;

--
-- AUTO_INCREMENT pour la table `school`
--
ALTER TABLE `school`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
  ADD CONSTRAINT `fk_course_mark` FOREIGN KEY (`idCourse`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_student_mark` FOREIGN KEY (`idStudent`) REFERENCES `person` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `school`
--
ALTER TABLE `school`
  ADD CONSTRAINT `fk_person_school` FOREIGN KEY (`director`) REFERENCES `person` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
