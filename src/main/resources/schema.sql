-- MySQL dump 10.13  Distrib 8.0.28, for Linux (x86_64)
--
-- Host: localhost    Database: Rivet
-- ------------------------------------------------------
-- Server version	8.0.28-0ubuntu0.20.04.3

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `blocked_users`
--

DROP TABLE IF EXISTS `blocked_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `blocked_users` (
  `id_a` int DEFAULT NULL,
  `id_b` int DEFAULT NULL,
  UNIQUE KEY `uq_bu` (`id_a`,`id_b`),
  KEY `id_a` (`id_a`),
  KEY `id_b` (`id_b`),
  CONSTRAINT `blocked_users_ibfk_1` FOREIGN KEY (`id_a`) REFERENCES `users` (`rivet_id`),
  CONSTRAINT `blocked_users_ibfk_2` FOREIGN KEY (`id_b`) REFERENCES `users` (`rivet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `blocked_users`
--

LOCK TABLES `blocked_users` WRITE;
/*!40000 ALTER TABLE `blocked_users` DISABLE KEYS */;
INSERT INTO `blocked_users` VALUES (1,10),(1,16),(1,24),(1,26),(2,7),(2,16),(2,23),(2,28),(3,10),(3,16),(3,23),(4,7),(4,15),(4,23),(4,28),(5,26),(6,21),(6,27),(7,3),(7,11),(7,18),(7,24),(7,29),(8,2),(8,7),(8,11),(9,5),(9,16),(9,19),(9,26),(10,9),(10,18),(10,21),(11,1),(11,3),(11,9),(11,17),(11,30),(12,4),(14,5),(14,10),(14,19),(14,23),(15,1),(15,3),(15,28),(17,1),(17,4),(17,29),(18,17),(18,28),(19,8),(19,27),(20,14),(20,16),(20,17),(20,24),(21,10),(22,1),(22,5),(22,12),(22,28),(22,30),(23,3),(23,5),(23,6),(23,15),(23,19),(25,9),(25,18),(26,4),(26,9),(26,13),(26,27),(26,28),(27,2),(27,9),(29,1),(29,13),(29,14),(29,18),(30,15),(30,22);
/*!40000 ALTER TABLE `blocked_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `id_a` int DEFAULT NULL,
  `id_b` int DEFAULT NULL,
  UNIQUE KEY `uq_friends` (`id_a`,`id_b`),
  KEY `id_a` (`id_a`),
  KEY `id_b` (`id_b`),
  CONSTRAINT `friends_ibfk_1` FOREIGN KEY (`id_a`) REFERENCES `users` (`rivet_id`),
  CONSTRAINT `friends_ibfk_2` FOREIGN KEY (`id_b`) REFERENCES `users` (`rivet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,10),(1,13),(1,15),(1,18),(2,3),(2,5),(2,16),(3,19),(3,30),(4,7),(4,11),(4,20),(4,22),(4,30),(5,9),(5,16),(6,4),(6,6),(7,6),(7,24),(10,18),(11,7),(11,9),(11,10),(13,7),(13,13),(13,14),(13,16),(13,22),(13,24),(15,18),(15,19),(15,22),(15,23),(15,24),(15,27),(16,15),(17,1),(17,4),(17,21),(17,30),(18,2),(18,4),(18,5),(18,22),(19,1),(19,9),(19,10),(19,14),(19,21),(19,23),(20,30),(21,7),(22,3),(23,9),(23,14),(23,27),(24,3),(24,5),(24,13),(24,22),(24,28),(26,1),(26,4),(26,8),(26,23),(26,28),(26,30),(27,5),(27,13),(27,22),(27,24),(29,8),(29,20);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `location` (
  `location_id` int NOT NULL AUTO_INCREMENT,
  `city_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

LOCK TABLES `location` WRITE;
/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES (1,'North Kenny'),(2,'Port Jannie'),(3,'Kossshire'),(4,'Grahamside'),(5,'Brooklynmouth'),(6,'Leopoldville'),(7,'North Lamontberg'),(8,'Verdietown'),(9,'Erdmanside'),(10,'East Eleazar'),(11,'South Collin'),(12,'West Winstonton'),(13,'Ivahville'),(14,'Port Cordiachester'),(15,'Port Laila'),(16,'Port Aida'),(17,'Raphaelbury'),(18,'North Laneybury'),(19,'Pacochamouth'),(20,'South Danielashire');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tags` (
  `tag_id` int NOT NULL,
  `tag` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (0,'Sports'),(1,'Art'),(2,'Movies'),(3,'Music'),(4,'Academics');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_tags`
--

DROP TABLE IF EXISTS `user_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_tags` (
  `rivet_id` int DEFAULT NULL,
  `tag_id` int DEFAULT NULL,
  UNIQUE KEY `utags` (`rivet_id`,`tag_id`),
  KEY `rivet_id` (`rivet_id`),
  KEY `tag_id` (`tag_id`),
  CONSTRAINT `user_tags_ibfk_1` FOREIGN KEY (`rivet_id`) REFERENCES `users` (`rivet_id`),
  CONSTRAINT `user_tags_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_tags`
--

LOCK TABLES `user_tags` WRITE;
/*!40000 ALTER TABLE `user_tags` DISABLE KEYS */;
INSERT INTO `user_tags` VALUES (1,2),(1,4),(4,1),(5,0),(5,3),(6,2),(6,4),(8,1),(8,4),(9,0),(9,1),(9,2),(9,4),(10,0),(10,2),(11,3),(12,2),(12,3),(13,0),(13,3),(14,1),(15,1),(15,2),(16,4),(18,0),(18,1),(22,0),(22,1),(22,4),(23,4),(25,4),(26,0),(26,2),(26,3),(27,1);
/*!40000 ALTER TABLE `user_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `first_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `last_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `rivet_id` int NOT NULL AUTO_INCREMENT,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone_number` int DEFAULT NULL,
  `location_id` int DEFAULT NULL,
  PRIMARY KEY (`rivet_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('James','Hackett',1,'abc',5292,1),('Delia','Bradtke',2,'ada47741849ed3236f66127e88a4f68eb97d7ed0',8869,2),('Schuyler','Waters',3,'dfd61c11566de1f588c77e47ae58937814961b03',6889,3),('Howell','Weimann',4,'7bf51e5d8a88e3680deefc275ea8e6966aa0febe',7877,4),('Eda','Ward',5,'2cc2e8e92575630b5136b980cf3dbefea769b176',4362,5),('Scot','Bosco',6,'451fc714bae2941bb9c08c407d8603e3abae3e35',8573,6),('Jalyn','Hartmann',7,'8e2e53993bc182ee88184590cf5da68ac24d47c6',5392,7),('Reyes','Jacobi',8,'d5e75b0d44730a474969e0cba7b56756bddaead3',5617,8),('Kaleigh','Mitchell',9,'d67a97791ce59ef5ba28204b105e282e3e42c4d0',8111,9),('Bennett','Roberts',10,'0ae01958c029f2b36a3eff977bca19b0e2333fdf',8523,10),('Janessa','Feest',11,'345f8fb320ce2e7c4724934a2a64cf3c8b45d50d',5070,11),('Rocio','McKenzie',12,'50deb4cf100efa048ebfbb57e8d7219bcbd81a0a',7671,12),('Emerald','Turner',13,'9ae6602d0fc9eca03a98363e251443ed6a19c988',5990,13),('Dimitri','Daugherty',14,'57d4bcb55fd3a74770066631ccfd167e97daa118',7661,14),('Willow','Kuhn',15,'ac7fefc08c645d93826d89abba8256e879c0c4cd',8387,15),('Cristian','Block',16,'e137249bad53dffb81bce1c1825e81c26c8f92d5',4303,16),('Ursula','Padberg',17,'09cc67e44925e246cb0ce990a0d91d950d9b3328',4767,17),('Kiarra','Mann',18,'3b30a3ee2230b9515a2ec14f89dc993a0a81f804',8037,18),('Adele','Beer',19,'ba9968c387592e0b809ea7653cb12875325bbe03',6742,19),('Stuart','Barrows',20,'26d917f670c9c1480cf35719b84f42f617e1c567',8181,20),('Kade','Jast',21,'9251d7f168c312289d3335d947c6df9b0142b5d8',5982,1),('Tad','Kemmer',22,'a645179523101084036600970709cf4abae6f49c',7617,2),('Kylie','Ortiz',23,'731744a9522812adba50be38b037fefc5f0dcd7c',5371,3),('Kaylie','Schuppe',24,'c62743a92aa1bf6a93ccda579016f243edb887a6',6920,4),('Eunice','Gorczany',25,'83e3d8f5f42d908d07be66cf3f24c240e32ae98b',7727,5),('Beryl','Leannon',26,'27e43cdf699c2a401928c13778533e329f1078aa',8197,6),('Elwyn','Dibbert',27,'c0e4ff856b6881a12fc19b57764c735f3b8b94a9',6150,7),('Amiya','Cummerata',28,'d80baef036b6efc32a525d623afe8eb69ab4fa5d',6516,8),('Arlo','Cronin',29,'1eeb45e49d3f2d3b870e65dea30d1499efb8f9f5',4881,9),('Axel','Ullrich',30,'cfc951f424d287287a9872ec25e5ab9745ed132a',4422,10);
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

-- Dump completed on 2022-03-17  2:15:10
