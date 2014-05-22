-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Ven 23 Mai 2014 à 01:52
-- Version du serveur :  5.5.34
-- Version de PHP :  5.5.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `Tweetr2`
--

-- --------------------------------------------------------

--
-- Structure de la table `Follow`
--

CREATE TABLE `Follow` (
  `FollowId` int(11) NOT NULL AUTO_INCREMENT,
  `FollowerId` int(11) NOT NULL,
  `FollowingId` int(11) NOT NULL,
  `UpdatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`FollowId`),
  KEY `followerIndex` (`FollowerId`),
  KEY `followingIndex` (`FollowingId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=76 ;

--
-- Contenu de la table `Follow`
--

INSERT INTO `Follow` (`FollowId`, `FollowerId`, `FollowingId`, `UpdatedAt`) VALUES
(13, 4, 2, '2014-05-20 09:32:43'),
(14, 5, 2, '2014-05-20 09:38:49'),
(16, 1, 5, '2014-05-20 09:41:29'),
(26, 1, 2, '2014-05-21 15:30:52'),
(30, 2, 3, '2014-05-21 15:54:33'),
(31, 6, 2, '2014-05-21 16:08:50'),
(32, 6, 4, '2014-05-21 16:10:00'),
(41, 2, 7, '2014-05-22 20:47:43'),
(60, 2, 5, '2014-05-22 23:00:46'),
(65, 2, 1, '2014-05-22 23:01:34'),
(66, 8, 2, '2014-05-22 23:07:25'),
(67, 8, 7, '2014-05-22 23:07:31'),
(68, 8, 6, '2014-05-22 23:07:32'),
(72, 8, 5, '2014-05-22 23:09:05'),
(73, 6, 8, '2014-05-22 23:10:30'),
(74, 3, 1, '2014-05-22 23:49:57'),
(75, 3, 5, '2014-05-22 23:50:48');

-- --------------------------------------------------------

--
-- Structure de la table `Retweet`
--

CREATE TABLE `Retweet` (
  `RetweetId` int(11) NOT NULL AUTO_INCREMENT,
  `TweetId` int(11) NOT NULL,
  `UserId` int(11) NOT NULL,
  `UpdatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`RetweetId`),
  KEY `TweetIndex` (`TweetId`),
  KEY `UserIndex` (`UserId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=7 ;

--
-- Contenu de la table `Retweet`
--

INSERT INTO `Retweet` (`RetweetId`, `TweetId`, `UserId`, `UpdatedAt`) VALUES
(1, 1, 5, '2014-05-20 09:44:00'),
(2, 3, 2, '2014-05-21 07:17:05'),
(3, 3, 1, '2014-05-21 13:40:30'),
(4, 3, 6, '2014-05-21 16:09:16'),
(5, 7, 8, '2014-05-22 23:08:13'),
(6, 11, 8, '2014-05-22 23:10:05');

-- --------------------------------------------------------

--
-- Structure de la table `Tweet`
--

CREATE TABLE `Tweet` (
  `TweetId` int(11) NOT NULL AUTO_INCREMENT,
  `UserId` int(11) NOT NULL,
  `Body` varchar(140) NOT NULL,
  `UpdatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`TweetId`),
  KEY `UserIndex` (`UserId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=14 ;

--
-- Contenu de la table `Tweet`
--

INSERT INTO `Tweet` (`TweetId`, `UserId`, `Body`, `UpdatedAt`) VALUES
(1, 2, 'Bonjour, bien content d''être là  aujourd''hui', '2014-05-19 19:44:44'),
(2, 1, 'hello world', '2014-05-19 18:23:36'),
(3, 5, 'Salut Michael, super ta photo de profil!', '2014-05-20 09:39:23'),
(4, 1, 'Il va faire chaud demain!', '2014-05-21 13:27:05'),
(5, 1, 'Quoi que, qui peut dire...', '2014-05-21 13:37:46'),
(6, 1, 'Mais j''ai bonne espoir ;)', '2014-05-22 15:08:50'),
(7, 6, 'Ca semble cool comme site', '2014-05-21 16:07:55'),
(8, 5, 'bonjour mon petit chat, comment vas-tu ajourd''hui?\r\nAu fait, il ne faudra pas oublier d''acheter du pain pour les invités ce soir.\r\nGros KISS', '2014-05-22 15:17:16'),
(9, 6, 'Salut tout le monde, j’ai passé une super journée. Et vous?', '2014-05-22 16:04:04'),
(10, 8, 'Salut les amis, je suis nouveau sur ce site. Si vous avez des conseils à me donnez, n’hésitez pas', '2014-05-22 23:08:06'),
(11, 5, 'Qui est donc le petit chat de Divine?', '2014-05-22 23:09:22'),
(12, 8, '@rsilvestre j’espère que c’est toi le petit chat de divine', '2014-05-22 23:16:49'),
(13, 3, 'Hello Divine, tu as de beaux yeux tu sais?', '2014-05-22 23:50:20');

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

CREATE TABLE `User` (
  `UserId` int(11) NOT NULL AUTO_INCREMENT,
  `UserName` varchar(45) NOT NULL,
  `FirstName` varchar(45) NOT NULL,
  `LastName` varchar(45) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(64) NOT NULL,
  `Image` varchar(45) NOT NULL,
  `UpdatedAt` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `EmailUNIQUE` (`Email`),
  UNIQUE KEY `UserNameUNIQUE` (`UserName`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Contenu de la table `User`
--

INSERT INTO `User` (`UserId`, `UserName`, `FirstName`, `LastName`, `Email`, `Password`, `Image`, `UpdatedAt`) VALUES
(1, 'michael', 'michael', 'michael', 'michael@michael.be', '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', '0', '2014-05-20 09:26:03'),
(2, 'rsilvestre', 'michael', 'silvestre', 'willtard@gmail.com', '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U2-wallpaper-430278.jpg', '2014-05-22 23:46:55'),
(3, 'toto', 'toto', 'toto', 'toto@toto.be', '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U3-wallpaper-335288.jpg', '2014-05-22 23:49:33'),
(4, 'celine', 'Céline', 'Zoetardt', 'celine@yahoo.fr', '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', '0', '2014-05-22 15:23:40'),
(5, 'divine', 'Céline', 'Zoetardt', 'celine@toto.be', '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U5-wallpaper-127191.jpg', '2014-05-22 23:48:30'),
(6, 'xavier', 'xavier', 'planckaert', 'xavier@toto.be', '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U6-4words1024x768.jpg', '2014-05-22 23:45:47'),
(7, 'Luccas', 'Eric', 'Marc', 'eric@marc.be', '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', '0', '2014-05-22 15:32:45'),
(8, 'maxjlaurent', 'Jean-Laurent', 'Leroy', 'maxjlaurent@hotmail.com', '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U8-21280.jpg', '2014-05-22 23:12:24');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Follow`
--
ALTER TABLE `Follow`
  ADD CONSTRAINT `Follow_Follower_User` FOREIGN KEY (`FollowerId`) REFERENCES `User` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Follow_Following_User` FOREIGN KEY (`FollowingId`) REFERENCES `User` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Retweet`
--
ALTER TABLE `Retweet`
  ADD CONSTRAINT `Retweet_Tweet` FOREIGN KEY (`TweetId`) REFERENCES `Tweet` (`TweetId`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Retweet_User` FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Tweet`
--
ALTER TABLE `Tweet`
  ADD CONSTRAINT `Tweet_User` FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
