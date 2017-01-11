create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';


start transaction;

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
  `end_date` datetime DEFAULT NULL,
  `id_votacion` int(11) DEFAULT NULL,
  `postal_code` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `username_creator` varchar(255) DEFAULT NULL,
  `valor` longblob,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_b1gix6fwe2tdtuui079sk633` (`id_votacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `census`
--

LOCK TABLES `census` WRITE;
/*!40000 ALTER TABLE `census` DISABLE KEYS */;
INSERT INTO `census` VALUES (1,1,'2016-06-10 20:38:02',999,'12345','2016-02-09 20:38:02','cerrado','Elecciones','aletormar','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	aletormarsr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpx\0'),(2,1,'2018-04-09 20:38:02',998,'12345','2016-02-09 20:38:02','cerrado','Elecciones para presidencia','alerodrom','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	rubsuaalmsr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0teteteq\0~\0x\0'),(3,1,'2018-02-23 20:38:02',997,'12345','2016-02-09 20:38:02','cerrado','Censo nuevo','carruivar','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	carruivarsr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0lefosoq\0~\0x\0'),(4,1,'2017-03-09 20:38:02',996,'12345','2017-01-09 20:38:02','abierto','Primarias del partido XX','carcamcue','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	mancabclasr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpx\0'),(5,0,'2017-04-09 20:38:02',995,'12345','2016-02-09 20:38:02','abierto','Referendum local','fuerte94','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0fuerte94sr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0	aletormarq\0~\0x\0'),(6,0,'2017-04-09 20:38:02',994,'12345','2016-02-09 20:38:02','abierto','Podemos S.A.','admin1','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	raftrugonsr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0adminq\0~\0x\0'),(7,0,'2017-04-09 20:38:02',993,'12345','2017-01-01 20:38:02','abierto','Mejor coche del mercado','agubordia','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	agubordiasr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0cabinaTelegramq\0~\0x\0'),(8,0,'2017-03-09 20:38:02',992,'12345','2017-01-01 20:38:02','cerrado','Censo seguro','carcamcue','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0\nrusuarez95sr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0teteteq\0~\0t\0	agubordiaq\0~\0t\0cabinaTelegramq\0~\0x\0'),(9,1,'2017-05-09 20:38:02',991,'12345','2017-02-09 20:38:02','cerrado','El mejor estudiante de la clase','rusuarez95','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0\0x\0'),(10,0,'2018-05-09 20:38:02',990,'99999','2017-01-09 20:38:02','cerrado','El censo de carcamcue','carcamcue','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	carcamcuesr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0lefosoq\0~\0x\0'),(11,3,'2018-05-09 20:38:02',989,'00000','2017-01-09 20:38:02','cerrado','El mejor censo','censo94','¬\í\0sr\0java.util.LinkedHashMap4ÀN\\lÀû\0Z\0accessOrderxr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0	carcamcuesr\0java.lang.Boolean\Í r€Õœú\î\0Z\0valuexpt\0censo94q\0~\0x\0'),(32773,0,'2017-02-09 00:00:00',1,'41008','2017-01-10 00:00:00','Abierto','VotaciÃ³n definitiva sobre la tortilla de patatas','censo94','¬\í\0sr\0java.util.HashMap\ÚÁ\Ã`\Ñ\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0\0w\0\0\0\0\0\0\0x');
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
INSERT INTO `hibernate_sequences` VALUES ('domain_entity',2);
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
  `autonomous_community` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `user_account` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cjn1wn3ecn1kacgqxryr6a5c6` (`user_account`),
  CONSTRAINT `FK_cjn1wn3ecn1kacgqxryr6a5c6` FOREIGN KEY (`user_account`) REFERENCES `user_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (32769,0,66,'Madrid','censo94@gmail.com','Masculino','Machupichu','USUARIO','Wollowitz',32768);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account`
--

LOCK TABLES `user_account` WRITE;
/*!40000 ALTER TABLE `user_account` DISABLE KEYS */;
INSERT INTO `user_account` VALUES (32768,1,'a532d5fff480f63cb33b138e45a973ac','censo94');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_account_authorities`
--

LOCK TABLES `user_account_authorities` WRITE;
/*!40000 ALTER TABLE `user_account_authorities` DISABLE KEYS */;
INSERT INTO `user_account_authorities` VALUES (32768,'USER');
/*!40000 ALTER TABLE `user_account_authorities` ENABLE KEYS */;
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
  `fecha_cierre` datetime DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `id_votacion` int(11) DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kskusi3k8xljp2vfwo8fkoihn` (`id_votacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vote`
--

LOCK TABLES `vote` WRITE;
/*!40000 ALTER TABLE `vote` DISABLE KEYS */;
INSERT INTO `vote` VALUES (32774,0,'41008','2017-02-09 00:00:00','2017-01-10 00:00:00',1,'VotaciÃ³n definitiva sobre la tortilla de patatas'),(32775,0,'01337','2016-12-11 00:00:00','2017-01-10 00:00:00',2,'Encuesta sobre intenciÃ³n de voto'),(32776,0,'28033','2018-01-11 00:00:00','2017-01-11 00:00:00',3,'Ejemplo 1');
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

-- Dump completed on 2017-01-11 22:50:08


commit; 
