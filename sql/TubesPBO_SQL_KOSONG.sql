-- MariaDB dump 10.17  Distrib 10.4.14-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: tubespbo
-- ------------------------------------------------------
-- Server version	10.4.14-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `idComment` int(11) NOT NULL,
  `isiComment` varchar(100) DEFAULT NULL,
  `nicknameCommentar` varchar(20) DEFAULT NULL,
  `idPostingan` int(11) DEFAULT NULL,
  PRIMARY KEY (`idComment`),
  KEY `idPostingan` (`idPostingan`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`idPostingan`) REFERENCES `postingan` (`IdPostingan`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_req`
--

DROP TABLE IF EXISTS `friend_req`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `friend_req` (
  `idReq` int(11) NOT NULL,
  `Username` varchar(20) DEFAULT NULL,
  `Nickname_req` varchar(20) DEFAULT NULL,
  `tanggal_req` date DEFAULT NULL,
  PRIMARY KEY (`idReq`),
  KEY `Username` (`Username`),
  CONSTRAINT `friend_req_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_req`
--

LOCK TABLES `friend_req` WRITE;
/*!40000 ALTER TABLE `friend_req` DISABLE KEYS */;
/*!40000 ALTER TABLE `friend_req` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `liker`
--

DROP TABLE IF EXISTS `liker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `liker` (
  `nicknameLike` varchar(100) DEFAULT NULL,
  `idPostingan` int(11) DEFAULT NULL,
  KEY `idPostingan` (`idPostingan`),
  CONSTRAINT `liker_ibfk_1` FOREIGN KEY (`idPostingan`) REFERENCES `postingan` (`IdPostingan`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `liker`
--

LOCK TABLES `liker` WRITE;
/*!40000 ALTER TABLE `liker` DISABLE KEYS */;
/*!40000 ALTER TABLE `liker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `list_teman`
--

DROP TABLE IF EXISTS `list_teman`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `list_teman` (
  `idTeman` int(11) NOT NULL,
  `Username` varchar(20) DEFAULT NULL,
  `Nickname_teman` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`idTeman`),
  KEY `Username` (`Username`),
  CONSTRAINT `list_teman_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `list_teman`
--

LOCK TABLES `list_teman` WRITE;
/*!40000 ALTER TABLE `list_teman` DISABLE KEYS */;
/*!40000 ALTER TABLE `list_teman` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person`
--

DROP TABLE IF EXISTS `person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `person` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person`
--

LOCK TABLES `person` WRITE;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
/*!40000 ALTER TABLE `person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postingan`
--

DROP TABLE IF EXISTS `postingan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `postingan` (
  `IdPostingan` int(11) NOT NULL,
  `Username` varchar(20) DEFAULT NULL,
  `Caption` varchar(100) DEFAULT NULL,
  `GambarPost` text DEFAULT NULL,
  `Likes` int(11) DEFAULT NULL,
  `WaktuPost` date DEFAULT NULL,
  `PostNickname` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`IdPostingan`),
  KEY `Username` (`Username`),
  CONSTRAINT `postingan_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `user` (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postingan`
--

LOCK TABLES `postingan` WRITE;
/*!40000 ALTER TABLE `postingan` DISABLE KEYS */;
/*!40000 ALTER TABLE `postingan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_User` int(11) NOT NULL AUTO_INCREMENT,
  `Username` varchar(20) DEFAULT NULL,
  `Nickname` varchar(20) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `JumlahTeman` int(11) DEFAULT NULL,
  `ProfilePict` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_User`),
  KEY `Username` (`Username`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`Username`) REFERENCES `person` (`Username`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-22 12:45:38
