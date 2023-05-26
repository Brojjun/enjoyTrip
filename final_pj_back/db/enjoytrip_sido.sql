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
-- Table structure for table `sido`
--

DROP TABLE IF EXISTS `sido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sido` (
  `sido_code` int NOT NULL,
  `sido_name` varchar(30) DEFAULT NULL,
  `sido_img` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`sido_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sido`
--

LOCK TABLES `sido` WRITE;
/*!40000 ALTER TABLE `sido` DISABLE KEYS */;
INSERT INTO `sido` VALUES (1,'서울','http://tong.visitkorea.or.kr/cms/resource/95/2660695_image2_1.jpg'),(2,'인천','http://tong.visitkorea.or.kr/cms/resource/95/2660695_image2_1.jpg'),(3,'대전','http://tong.visitkorea.or.kr/cms/resource/70/2661770_image2_1.jpg'),(4,'대구','http://tong.visitkorea.or.kr/cms/resource/82/2818982_image2_1.jpg'),(5,'광주','http://tong.visitkorea.or.kr/cms/resource/29/2661529_image2_1.jpg'),(6,'부산','http://tong.visitkorea.or.kr/cms/resource/84/2612484_image2_1.jpg'),(7,'울산','http://tong.visitkorea.or.kr/cms/resource/67/2558467_image2_1.jpg'),(8,'세종','http://tong.visitkorea.or.kr/cms/resource/38/2724238_image2_1.jpg'),(31,'경기도','http://tong.visitkorea.or.kr/cms/resource/29/2482729_image2_1.jpg'),(32,'강원도','http://tong.visitkorea.or.kr/cms/resource/70/2656670_image2_1.jpg'),(33,'충청북도','http://tong.visitkorea.or.kr/cms/resource/57/922357_image2_1.jpg'),(34,'충청남도','http://tong.visitkorea.or.kr/cms/resource/35/1278835_image2_1.jpg'),(35,'경상북도','http://tong.visitkorea.or.kr/cms/resource/16/2654216_image2_1.jpg'),(36,'경상남도','http://tong.visitkorea.or.kr/cms/resource/85/2703885_image2_1.jpg'),(37,'전라북도','http://tong.visitkorea.or.kr/cms/resource/92/2378792_image2_1.jpg'),(38,'전라남도','http://tong.visitkorea.or.kr/cms/resource/38/1993038_image2_1.jpg'),(39,'제주도','http://tong.visitkorea.or.kr/cms/resource/00/2613500_image2_1.jpg');
/*!40000 ALTER TABLE `sido` ENABLE KEYS */;
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
