-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: fishingshop
-- ------------------------------------------------------
-- Server version	8.0.26

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
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(20) COLLATE utf8_bin NOT NULL,
  `email` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `last_name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(13) COLLATE utf8_bin DEFAULT NULL,
  `role_id` int NOT NULL,
  `status_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_UNIQUE` (`login`),
  KEY `roleId` (`role_id`),
  KEY `statusId` (`status_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `users_ibfk_2` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb3 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'user','wertyal.87@mail.ru','ad42c83ac4d3b86de14f207c46a0df0e','anton','Пых','+375295533509',2,2),(2,'manager','wertyal.87@mail.ru','78ae9a36ad80c5d88220578ef91f9e56','anton','Пых','+375295533509',3,2),(3,'admin','wertyal.87@mail.ru','b9d11b3be25f5a1a7dc8ca04cd310b28','anton','Пых','+375295533509',4,2),(4,'Toxa1987','wertyal.87@mail.ru','f78c3aa885573a385ac0ce267c40286d','anton','Пых','+375295533509',3,2),(5,'user123','wertyal.87@mail.ru','9a91a755053c148682dd43bcf817b09e','anton',NULL,'+375295533509',2,2),(6,'masha','wertyal.87@mail.ru','b8373a9f933f4b3e31b702662f20694c','masha','atata','+375295533509',2,2),(7,'ddd','wertyal.87@mail.ru','7b6b42be6c44b379888e97cdd2ef0297',NULL,NULL,NULL,1,1),(8,'wer','wertyal.87@mail.ru','e6334de54299af99edeb021879994083',NULL,NULL,NULL,1,1),(9,'wertyal','wertyal.87@mail.ru','7957012cc18ffe966dda62fbda12e9a7','anton','Пых','+375295533509',2,2),(10,'dasha','wertyal.87@mail.ru','03f4c0324a5a608d7eae11bafff3f776','dasha','atata','+375295533510',2,2),(11,'manager2','wertyal.87@mail.ru','dff2f32c204278913638ed03b235a78e','anton','Пых','+375295533509',3,2),(12,'manager3','wertyal.87@mail.ru','d1f7c662b92656074158f56332ff9db2','anton','atata','+375295533509',3,2),(13,'sdfsdf','wertyal.87@mail.ru','39c8e9953fe8ea40ff1c59876e0e2f28',NULL,NULL,NULL,1,1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-25 19:47:27
