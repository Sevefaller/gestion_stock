-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Sam 07 Septembre 2019 à 19:23
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `g_stock_hibernate`
--
CREATE DATABASE IF NOT EXISTS `g_stock_hibernate` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `g_stock_hibernate`;

-- --------------------------------------------------------

--
-- Structure de la table `entree`
--

CREATE TABLE IF NOT EXISTS `entree` (
  `NUMBONENTREE` varchar(255) NOT NULL,
  `NUMPRODUIT` varchar(255) DEFAULT NULL,
  `QTEENTREE` int(11) DEFAULT NULL,
  `DATEENTREE` datetime DEFAULT NULL,
  PRIMARY KEY (`NUMBONENTREE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `produit`
--

CREATE TABLE IF NOT EXISTS `produit` (
  `NUMPRODUIT` varchar(255) NOT NULL,
  `DESIGN` varchar(255) DEFAULT NULL,
  `STOCK` int(11) DEFAULT NULL,
  PRIMARY KEY (`NUMPRODUIT`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `sortie`
--

CREATE TABLE IF NOT EXISTS `sortie` (
  `NUMBONSORTIE` varchar(255) NOT NULL,
  `NUMPRODUIT` varchar(255) DEFAULT NULL,
  `QTESORTIE` int(11) DEFAULT NULL,
  `DATESORTIE` datetime DEFAULT NULL,
  PRIMARY KEY (`NUMBONSORTIE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
