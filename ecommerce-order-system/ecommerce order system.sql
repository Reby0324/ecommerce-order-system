CREATE DATABASE  IF NOT EXISTS `ecommerce_order_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `ecommerce_order_system`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: ecommerce_order_system
-- ------------------------------------------------------
-- Server version	8.0.46

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
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `quantity` int DEFAULT '1',
  `price` decimal(10,2) DEFAULT '0.00',
  `subtotal` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (1,1,2,3,680.00,1360.00),(3,1,2,2,680.00,1360.00),(4,1,2,2,780.00,1460.00),(5,1,1,1,699.00,699.00),(6,1,2,1,650.00,650.00),(7,1,11,2,60.00,120.00),(8,2,3,1,720.00,720.00),(9,2,12,2,25.00,50.00),(10,3,5,1,850.00,850.00),(11,3,15,1,120.00,120.00),(12,4,6,1,620.00,620.00),(13,4,17,1,150.00,150.00),(14,5,20,1,350.00,350.00),(15,8,9,1,850.00,850.00),(16,12,12,1,560.00,560.00),(17,13,10,1,620.00,620.00),(18,13,6,1,650.00,650.00),(19,13,20,1,80.00,80.00),(20,13,3,1,799.00,799.00),(21,13,9,1,850.00,850.00);
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `total_price` decimal(10,2) DEFAULT '0.00',
  `status` varchar(45) DEFAULT NULL,
  `order_date` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,1,1360.00,'已付款',NULL,'2026-06-25 13:06:25'),(3,3,2690.00,'已成立',NULL,'2026-06-25 13:20:03'),(5,1,1360.00,'已成立',NULL,'2026-06-25 13:40:40'),(6,1,1360.00,'已成立',NULL,'2026-06-25 13:43:23'),(7,1,1360.00,'已成立',NULL,'2026-06-25 13:57:27'),(8,1,850.00,'已成立',NULL,'2026-06-26 08:31:28'),(9,1,1360.00,'已成立',NULL,'2026-06-26 08:42:03'),(10,1,1360.00,'已成立',NULL,'2026-06-26 08:43:20'),(11,1,1360.00,'已成立',NULL,'2026-06-26 08:43:49'),(12,8,560.00,'已成立',NULL,'2026-06-26 09:03:58'),(13,8,2999.00,'已成立',NULL,'2026-06-26 09:04:22');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(100) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `price` decimal(10,2) DEFAULT '0.00',
  `stock` int DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'普通心理學','心理學書籍',699.00,24,'適合心理所考試','2026-06-25 13:11:43'),(2,'DSM-5-TR','精神科書籍',750.00,100,'適合心理所考試','2026-06-25 13:11:43'),(3,'CASIO計算機','考試文具',799.00,50,'適合心理所考試','2026-06-25 13:11:43'),(5,'普通心理學總複習','心理學書籍',699.00,30,'適合心理所考試','2026-06-25 14:03:58'),(6,'發展心理學重點整理','心理學書籍',650.00,25,'兒童發展與 lifespan 重點','2026-06-25 14:03:58'),(7,'變態心理學考前攻略','精神科書籍',720.00,20,'DSM與常見疾患整理','2026-06-25 14:03:58'),(8,'心理測驗與衡鑑','心理學書籍',780.00,18,'心理測驗考科使用','2026-06-25 14:03:58'),(9,'心理與教育統計學','心理學書籍',850.00,15,'統計觀念與公式練習','2026-06-25 14:03:58'),(10,'研究法題庫解析','心理學書籍',620.00,22,'研究方法與實驗設計','2026-06-25 14:03:58'),(11,'社會心理學重點筆記','心理學書籍',580.00,20,'社心考前複習','2026-06-25 14:03:58'),(12,'人格心理學整理書','心理學書籍',560.00,18,'人格理論與學派整理','2026-06-25 14:03:58'),(13,'認知心理學考點整理','心理學書籍',690.00,16,'記憶注意與認知歷程','2026-06-25 14:03:58'),(14,'諮商理論與技術','心理學書籍',760.00,14,'諮商學派與技巧整理','2026-06-25 14:03:58'),(15,'2B鉛筆組','考試文具',60.00,100,'選擇題畫卡使用','2026-06-25 14:03:58'),(16,'橡皮擦','考試文具',25.00,120,'考試必備文具','2026-06-25 14:03:58'),(17,'黑色原子筆','考試文具',35.00,100,'申論題書寫使用','2026-06-25 14:03:58'),(18,'藍色原子筆','考試文具',35.00,80,'筆記與標記使用','2026-06-25 14:03:58'),(19,'螢光筆組','考試文具',120.00,50,'重點標記使用','2026-06-25 14:03:58'),(20,'便利貼','考試文具',80.00,60,'章節標籤與提醒','2026-06-25 14:03:58'),(21,'考試透明筆袋','考試文具',150.00,40,'考場可用透明筆袋','2026-06-25 14:03:58'),(22,'修正帶','考試文具',45.00,70,'修改筆記使用','2026-06-25 14:03:58'),(23,'A4活頁紙','考試文具',90.00,50,'申論練習與整理筆記','2026-06-25 14:03:58'),(24,'藍色原子筆','考試文具',35.00,80,'筆記與標記使用','2026-06-26 08:35:36'),(25,'考試透明筆袋','考試文具',150.00,40,'考場可用透明筆袋','2026-06-26 08:43:58');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `level` varchar(20) NOT NULL,
  `balance` decimal(10,2) NOT NULL,
  `created_at` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'teacher','1234','teacher@gmail.com','Admin',10000.00,'2026-06-22 16:55:57'),(2,'rosie','1234','rosie@gmail.com','Member',10000.00,'2026-06-22 16:58:10'),(4,'Reby','5678','Reby@gmail.com','Silver',10000.00,'2026-06-22 16:59:22'),(6,'test','0000','test@gmail.com','Member',10000.00,'2026-06-22 17:11:10'),(7,'Angela','1234','Angela@gmail.com','Normal',10000.00,'2026-06-22 18:15:53'),(8,'luck','1234','luck@gmail.com','Nomal',10000.00,'2026-06-26 17:03:19');
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

-- Dump completed on 2026-06-26 17:06:02
