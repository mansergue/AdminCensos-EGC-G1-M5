-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: admcensus
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `census`
--

DROP TABLE IF EXISTS `census`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `census` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endDate` datetime DEFAULT NULL,
  `idVotacion` int(11) DEFAULT NULL,
  `postalCode` varchar(255) DEFAULT NULL,
  `startDate` datetime DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `usernameCreator` varchar(255) DEFAULT NULL,
  `versionVotacion` int(11) NOT NULL,
  `valor` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_qx9ataj93wny5sbrfxx6maweq` (`idVotacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `census`
--

LOCK TABLES `census` WRITE;
/*!40000 ALTER TABLE `census` DISABLE KEYS */;
INSERT INTO `census` VALUES (1,0,'Elecciones','2018-12-02 00:00:00',23,'12345','2015-10-01 00:00:00','cerrado','Elecciones','admin1',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0test1sr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0test2sq\0~\0\0x\0'),(2,0,'Elecciones','2018-02-28 00:00:00',3,'12345','2015-10-01 00:00:00','cerrado','Censo cerrado VACIO, ACTIVO','admin1',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0test0sr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0test1q\0~\0x\0'),(3,0,'Elecciones','2018-12-02 00:00:00',25,'12345','2011-10-01 00:00:00','cerrado','Censo cerrado NO VACIO, NO ACTIVO','admin1',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0test3sr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpx\0'),(4,0,'Elecciones','2018-12-05 00:00:00',26,'12345','2015-10-07 00:00:00','abierto','Censo abierto NO VACIO, ACTIVO','admin1',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0test2sr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexp\0t\0test3sq\0~\0x\0'),(5,0,'Elecciones','2018-12-03 00:00:00',27,'12345','2010-10-11 00:00:00','abierto','Censo abierto NO VACIO, NO ACTIVO','admin1',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0test3sr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpx\0'),(6,0,'Elecciones','2018-12-03 00:00:00',28,'12345','2010-10-11 00:00:00','abierto','Censo abierto vacio NO ACTIVO','admin1',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),(7,0,'Elecciones','2018-12-03 00:00:00',29,'12345','2015-10-11 00:00:00','abierto','Censo abierto vacio activo','admin1',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),(8,0,'Elecciones','2018-12-03 00:00:00',30,'12345','2016-10-11 00:00:00','cerrado','Censo cerrado vacio activo','admin1',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),(9,0,'Elecciones','2018-12-03 00:00:00',31,'12345','2011-10-11 00:00:00','cerrado','Censo cerrado vacio NO activo','admin1',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),(10,0,'Elecciones','2018-12-03 00:00:00',32,'12345','2015-10-11 00:00:00','abierto','Censo abierto vacio activo','pabcargar2',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\npabcargar2sr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0admin1q\0~\0x\0'),(11,0,'Elecciones','2018-12-03 00:00:00',33,'12345','2015-10-11 00:00:00','abierto','Censo abierto vacio activo','pabcargar2',0,'¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\npabcargar2sr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0admin1q\0~\0t\0joseaq\0~\0x\0');
/*!40000 ALTER TABLE `census` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',3);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `autonomousCommunity` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_o6s94d43co03sx067ili5760c` (`userAccount_id`),
  CONSTRAINT `FK_o6s94d43co03sx067ili5760c` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (65537,0,22,NULL,'pabcargar2@alum.us.es','Masculino',65536);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (65536,1,'6d722561cf386015cb1d7c1937806bb5','pabcargar2');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (65536,'USER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vote`
--

DROP TABLE IF EXISTS `vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vote` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cp` varchar(255) DEFAULT NULL,
  `fechaCierre` datetime DEFAULT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `idVotacion` int(11) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_skxoi481n3j9r557eodnbgwrq` (`idVotacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` VALUES (32768,0,NULL,NULL,NULL,1,NULL),(32769,0,'41008','2017-01-14 00:00:00','2016-12-15 00:00:00',2,'VotaciÃ³n definitiva sobre la tortilla de patatas'),(32770,0,'01337','2017-01-14 00:00:00','2016-12-15 00:00:00',3,'Encuesta sobre intenciÃ³n de voto');
/*!40000 ALTER TABLE `vote` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-08 12:47:28
