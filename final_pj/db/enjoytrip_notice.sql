-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: enjoytrip
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `no` int NOT NULL AUTO_INCREMENT,
  `user_no` int DEFAULT NULL,
  `title` varchar(200) DEFAULT NULL,
  `contents` blob,
  `is_notice` tinyint DEFAULT '0',
  `regist_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL,
  `remove_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,1,'테스트1',_binary '테스트1',0,'2023-04-11 06:57:59',NULL,NULL),(2,1,'테스트2',_binary '테스트2',0,'2023-04-11 06:57:59',NULL,NULL),(3,1,'테스트3',_binary '테스트3',0,'2023-04-11 06:57:59',NULL,NULL),(4,1,'테스트4',_binary '테스트4',0,'2023-04-11 06:57:59',NULL,NULL),(5,1,'테스트5',_binary '테스트5',0,'2023-04-11 06:57:59',NULL,NULL),(6,1,'테스트6',_binary '테스트6',0,'2023-04-11 06:57:59',NULL,NULL),(7,1,'테스트7',_binary '테스트7',0,'2023-04-11 06:57:59',NULL,NULL),(8,1,'테스트8',_binary '테스트8',0,'2023-04-11 06:57:59',NULL,NULL),(9,1,'테스트9',_binary '테스트9',0,'2023-04-11 06:57:59',NULL,NULL),(10,1,'테스트10',_binary '테스트10',0,'2023-04-11 06:57:59',NULL,NULL),(11,1,'테스트11',_binary '테스트11',0,'2023-04-11 06:57:59',NULL,NULL);
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-02 16:44:48
