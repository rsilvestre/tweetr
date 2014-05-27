-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Client :  localhost:3306
-- Généré le :  Dim 25 Mai 2014 à 16:01
-- Version du serveur :  5.5.34
-- Version de PHP :  5.5.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `Tweetr2`
--
CREATE DATABASE IF NOT EXISTS `Tweetr2`
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;
USE `Tweetr2`;

-- --------------------------------------------------------

--
-- Structure de la table `Follow`
--

DROP TABLE IF EXISTS `Follow`;
CREATE TABLE IF NOT EXISTS `Follow` (
  `FollowId`    INT(11)   NOT NULL AUTO_INCREMENT,
  `FollowerId`  INT(11)   NOT NULL,
  `FollowingId` INT(11)   NOT NULL,
  `UpdatedAt`   TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`FollowId`),
  KEY `followerIndex` (`FollowerId`),
  KEY `followingIndex` (`FollowingId`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  COLLATE utf8_general_ci
  AUTO_INCREMENT =125;

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
  (60, 2, 5, '2014-05-22 23:00:46'),
  (65, 2, 1, '2014-05-22 23:01:34'),
  (66, 8, 2, '2014-05-22 23:07:25'),
  (68, 8, 6, '2014-05-22 23:07:32'),
  (72, 8, 5, '2014-05-22 23:09:05'),
  (73, 6, 8, '2014-05-22 23:10:30'),
  (74, 3, 1, '2014-05-22 23:49:57'),
  (91, 3, 5, '2014-05-23 19:49:56'),
  (94, 10, 4, '2014-05-23 22:16:39'),
  (97, 10, 2, '2014-05-23 22:19:21'),
  (98, 10, 3, '2014-05-23 22:20:06'),
  (99, 9, 10, '2014-05-23 22:21:38'),
  (112, 13, 3, '2014-05-24 19:17:31'),
  (124, 13, 4, '2014-05-24 19:23:58');

-- --------------------------------------------------------

--
-- Structure de la table `Retweet`
--

DROP TABLE IF EXISTS `Retweet`;
CREATE TABLE IF NOT EXISTS `Retweet` (
  `RetweetId` INT(11)   NOT NULL AUTO_INCREMENT,
  `TweetId`   INT(11)   NOT NULL,
  `UserId`    INT(11)   NOT NULL,
  `UpdatedAt` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`RetweetId`),
  KEY `TweetIndex` (`TweetId`),
  KEY `UserIndex` (`UserId`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  COLLATE utf8_general_ci
  AUTO_INCREMENT =16;

--
-- Contenu de la table `Retweet`
--

INSERT INTO `Retweet` (`RetweetId`, `TweetId`, `UserId`, `UpdatedAt`) VALUES
  (1, 1, 5, '2014-05-20 09:44:00'),
  (2, 3, 2, '2014-05-21 07:17:05'),
  (3, 3, 1, '2014-05-21 13:40:30'),
  (4, 3, 6, '2014-05-21 16:09:16'),
  (5, 7, 8, '2014-05-22 23:08:13'),
  (6, 11, 8, '2014-05-22 23:10:05'),
  (7, 6, 3, '2014-05-23 14:29:53'),
  (8, 6, 10, '2014-05-23 22:18:27'),
  (9, 11, 10, '2014-05-23 22:18:38'),
  (10, 14, 10, '2014-05-23 22:20:22'),
  (11, 15, 9, '2014-05-23 22:22:09'),
  (13, 9, 13, '2014-05-24 19:18:55'),
  (14, 21, 13, '2014-05-24 19:23:54');

-- --------------------------------------------------------

--
-- Structure de la table `Tweet`
--

DROP TABLE IF EXISTS `Tweet`;
CREATE TABLE IF NOT EXISTS `Tweet` (
  `TweetId`   INT(11)      NOT NULL AUTO_INCREMENT,
  `UserId`    INT(11)      NOT NULL,
  `Body`      VARCHAR(140) NOT NULL,
  `UpdatedAt` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`TweetId`),
  KEY `UserIndex` (`UserId`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  COLLATE utf8_general_ci
  AUTO_INCREMENT =27;

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
  (13, 3, 'Hello Divine, tu as de beaux yeux tu sais?', '2014-05-22 23:50:20'),
  (14, 3, 'C’est une belle phrase @michael que tu nous dis là', '2014-05-23 14:30:41'),
  (15, 10, 'Vrooooomm', '2014-05-23 22:18:17'),
  (16, 10, 'Zorba le grec', '2014-05-23 22:20:58'),
  (17, 9, 'Salut Schumi, vivement que tu remontes sur tes skis', '2014-05-23 22:21:56'),
  (18, 3, 'Dernière ligne droite', '2014-05-24 09:04:10'),
  (19, 2, 'Salut @Toto, tu as été super actif hier soir!', '2014-05-24 09:05:26'),
  (21, 4, 'Hello @startrek', '2014-05-24 16:18:14'),
  (22, 13, 'Salut tout le monde est surtout bonjour à @Toto', '2014-05-24 19:17:22'),
  (23, 13, '@xavier j’adore ta dernière phrase!', '2014-05-24 19:19:19'),
  (26, 13, 'finalement ca craint ici, je me tire band de naze!', '2014-05-24 19:23:23');

-- --------------------------------------------------------

--
-- Structure de la table `User`
--

DROP TABLE IF EXISTS `User`;
CREATE TABLE IF NOT EXISTS `User` (
  `UserId`    INT(11)      NOT NULL AUTO_INCREMENT,
  `UserName`  VARCHAR(45)  NOT NULL,
  `FirstName` VARCHAR(45)  NOT NULL,
  `LastName`  VARCHAR(45)  NOT NULL,
  `Email`     VARCHAR(255) NOT NULL,
  `Password`  VARCHAR(64)  NOT NULL,
  `Image`     VARCHAR(45)  NOT NULL,
  `UpdatedAt` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`UserId`),
  UNIQUE KEY `EmailUNIQUE` (`Email`),
  UNIQUE KEY `UserNameUNIQUE` (`UserName`)
)
  ENGINE =InnoDB
  DEFAULT CHARSET =utf8
  COLLATE utf8_general_ci
  AUTO_INCREMENT =14;

--
-- Contenu de la table `User`
--

INSERT INTO `User` (`UserId`, `UserName`, `FirstName`, `LastName`, `Email`, `Password`, `Image`, `UpdatedAt`) VALUES
  (1, 'michael', 'michael', 'michael', 'michael@michael.be',
   '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', '0', '2014-05-20 09:26:03'),
  (2, 'rsilvestre', 'michael', 'silvestre', 'willtard@gmail.com',
   '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U2-wallpaper-335287.jpg',
   '2014-05-24 20:19:34'),
  (3, 'toto', 'toto', 'toto', 'toto@toto.be', '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18',
   'U3-41280.jpg', '2014-05-24 20:18:53'),
  (4, 'celine', 'Céline', 'Zoetardt', 'celine@yahoo.fr',
   '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', '0', '2014-05-22 15:23:40'),
  (5, 'divine', 'Céline', 'Zoetardt', 'celine@toto.be',
   '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U5-4words1024x768.jpg', '2014-05-24 20:19:17'),
  (6, 'xavier', 'xavier', 'planckaert', 'xavier@toto.be',
   '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U6-wallpaper-369.jpg', '2014-05-24 20:20:05'),
  (8, 'maxjlaurent', 'Jean-Laurent', 'Leroy', 'maxjlaurent@hotmail.com',
   '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U8-wallpaper-22537.jpg', '2014-05-24 20:20:21'),
  (9, 'celiavrancken', 'Célia', 'Vrancken', 'celiavrancken@gmail.com',
   '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', '0', '2014-05-23 22:13:59'),
  (10, 'schumi', 'Michael', 'Schumacker', 'schumi@gmail.com',
   '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U10-41280.jpg', '2014-05-25 13:22:22'),
  (13, 'startrek', 'star', 'trek', 'startrek@gmail.com',
   '2cb4b1431b84ec15d35ed83bb927e27e8967d75f4bcd9cc4b25c8d879ae23e18', 'U13-wallpaper-760272.jpg',
   '2014-05-24 20:20:48');

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `Follow`
--
ALTER TABLE `Follow`
ADD CONSTRAINT `Follow_Follower_User` FOREIGN KEY (`FollowerId`) REFERENCES `User` (`UserId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `Follow_Following_User` FOREIGN KEY (`FollowingId`) REFERENCES `User` (`UserId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Retweet`
--
ALTER TABLE `Retweet`
ADD CONSTRAINT `Retweet_Tweet` FOREIGN KEY (`TweetId`) REFERENCES `Tweet` (`TweetId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `Retweet_User` FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

--
-- Contraintes pour la table `Tweet`
--
ALTER TABLE `Tweet`
ADD CONSTRAINT `Tweet_User` FOREIGN KEY (`UserId`) REFERENCES `User` (`UserId`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;