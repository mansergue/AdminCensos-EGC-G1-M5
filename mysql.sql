start transaction;


create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';




grant select, insert, update, delete 
	on `ADMCensus`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
        create temporary tables, lock tables, create view, create routine, 
        alter routine, execute, trigger, show view
    on `ADMCensus`.* to 'acme-manager'@'%';


drop database if exists `ADMCensus`;
create database `ADMCensus`;
USE `ADMCensus`;


SET NAMES utf8;



-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: admcensus
-- ------------------------------------------------------
-- Server version	5.5.29

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
  `fecha_fin_votacion` datetime DEFAULT NULL,
  `fecha_inicio_votacion` datetime DEFAULT NULL,
  `id_votacion` int(11) DEFAULT NULL,
  `tipo_censo` varchar(255) DEFAULT NULL,
  `titulo_votacion` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `valor` tinyblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b1gix6fwe2tdtuui079sk633` (`id_votacion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `census`
--

LOCK TABLES `census` WRITE;
/*!40000 ALTER TABLE `census` DISABLE KEYS */;
INSERT INTO `census` VALUES (1,0,'2015-12-02 00:00:00','2015-10-01 00:00:00',23,'cerrado','Elecciones','admin1','Ỳí\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0test1sr\0java.lang.BooleanÍ rÕúî\0Z\0valuexpt\0test2sq\0~\0\0x\0'),(2,0,'2016-02-28 00:00:00','2015-10-01 00:00:00',3,'cerrado','Censo cerrado VACIO, ACTIVO','admin1','Ỳí\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0test0sr\0java.lang.BooleanÍ rÕúî\0Z\0valuexpt\0test1q\0~\0x\0'),(3,0,'2012-12-02 00:00:00','2011-10-01 00:00:00',25,'cerrado','Censo cerrado NO VACIO, NO ACTIVO','admin1','Ỳí\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0t\0test3sr\0java.lang.BooleanÍ rÕúî\0Z\0valuexpx\0'),(4,0,'2016-12-05 00:00:00','2015-10-07 00:00:00',26,'abierto','Censo abierto NO VACIO, ACTIVO','admin1','Ỳí\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0test2sr\0java.lang.BooleanÍ rÕúî\0Z\0valuexp\0t\0test3sq\0~\0x\0'),(5,0,'2011-12-03 00:00:00','2010-10-11 00:00:00',27,'abierto','Censo abierto NO VACIO, NO ACTIVO','admin1','Ỳí\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0t\0test3sr\0java.lang.BooleanÍ rÕúî\0Z\0valuexpx\0'),(6,0,'2014-12-03 00:00:00','2010-10-11 00:00:00',28,'abierto','Censo abierto vacio NO ACTIVO','admin1','Ỳí\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),(7,0,'2016-12-03 00:00:00','2015-10-11 00:00:00',29,'abierto','Censo abierto vacio activo','admin1','Ỳí\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),(8,0,'2016-12-03 00:00:00','2016-01-01 00:00:00',30,'cerrado','Censo cerrado vacio activo','admin1','Ỳí\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),(9,0,'2012-12-03 00:00:00','2011-10-11 00:00:00',31,'cerrado','Censo cerrado vacio NO activo','admin1','Ỳí\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMapÚÁÃ`Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0\0x\0');
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
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_castjbvpeeus0r8lbpehiu0e4` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_account_authorities`
--

DROP TABLE IF EXISTS `user_account_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_account_authorities` (
  `user_account` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_pao8cwh93fpccb0bx6ilq6gsl` (`user_account`),
  CONSTRAINT `FK_pao8cwh93fpccb0bx6ilq6gsl` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-23 17:59:36

commit;