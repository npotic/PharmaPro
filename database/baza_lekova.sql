-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: baza_lekova
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `friend_requests`
--

DROP TABLE IF EXISTS `friend_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_requests` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sender_id` int NOT NULL,
  `receiver_id` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `sender_id` (`sender_id`),
  KEY `receiver_id` (`receiver_id`),
  CONSTRAINT `friend_requests_ibfk_1` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`),
  CONSTRAINT `friend_requests_ibfk_2` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_requests`
--

LOCK TABLES `friend_requests` WRITE;
/*!40000 ALTER TABLE `friend_requests` DISABLE KEYS */;
INSERT INTO `friend_requests` VALUES (1,1,3,'PENDING'),(2,2,4,'PENDING'),(3,1,2,'ACCEPTED'),(4,1,2,'PENDING');
/*!40000 ALTER TABLE `friend_requests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friendships`
--

DROP TABLE IF EXISTS `friendships`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friendships` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_date` datetime(6) DEFAULT NULL,
  `user_id1` int NOT NULL,
  `user_id2` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjvdxo4hjiy15e3r0pqekrlxsd` (`user_id1`),
  KEY `FKkvv442rnfccd243t2drh5jl2i` (`user_id2`),
  CONSTRAINT `FKjvdxo4hjiy15e3r0pqekrlxsd` FOREIGN KEY (`user_id1`) REFERENCES `users` (`id`),
  CONSTRAINT `FKkvv442rnfccd243t2drh5jl2i` FOREIGN KEY (`user_id2`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friendships`
--

LOCK TABLES `friendships` WRITE;
/*!40000 ALTER TABLE `friendships` DISABLE KEYS */;
INSERT INTO `friendships` VALUES (1,'2010-01-05 00:00:00.000000',1,8),(2,'2002-12-25 00:00:00.000000',8,6);
/*!40000 ALTER TABLE `friendships` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lekovi`
--

DROP TABLE IF EXISTS `lekovi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lekovi` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `farmaceutski_oblik` varchar(255) DEFAULT NULL,
  `proizvodjac` varchar(255) DEFAULT NULL,
  `terapijske_indikacije` varchar(255) DEFAULT NULL,
  `doziranje_i_nacin_primene` varchar(255) DEFAULT NULL,
  `fotografija` varchar(255) DEFAULT NULL,
  `namena` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lekovi`
--

LOCK TABLES `lekovi` WRITE;
/*!40000 ALTER TABLE `lekovi` DISABLE KEYS */;
INSERT INTO `lekovi` VALUES (3,'Dimigal','Tableta','Galenika','Lek Dimigal je indikovan kod odraslih osoba i dece uzrasta 2 godine i više u:\nprevenciji mučnine, povraćanja i/ili vrtoglavice kod kinetoza;\nsimptomatskoj terapiji mučnine i povraćanja kod Menierove bolesti i drugih vestibularnih poremećaja.','Odrasli i deca uzrasta od 14 godina:\n50-100 mg svakih 4-6 sati po potrebi, maksimalno 400 mg dnevno.\nDeca od 6 do 14 godina:\n25-50 mg svakih 6-8 sati po potrebi, maksimalno 150 mg dnevno.','images/dimigal.jpg','Stomak'),(5,'PropoMucil','Rastvor','AbelaPharm','Olakšava izbacivanje sekreta iz disajnih puteva i olakšava disanje. Pomaže uklanjanju virusa, bakterija i gljivica i pojačava imunski odgovor','Odrasli i deca starija od 14 godina:\n1 puta dnevno po 1 kesicu praška rastvoriti u čaši vode','images/propomucil.jpg','Kašalj'),(6,'Herbiko','Rastvor','AbelaPharm','Kod suvog kašlja: Oblaže sluzokožu grla i umiruje napade kašlja\nKod produktivnog kašlja: otvara disajne puteve, razlaže gust sekret, olakšava iskašljavanje','Odrasli i deca starija od 12 godina: 3 puta dnevno po 1 velika kašika (15ml).\nDeca od 4-12 godina: 3 puta dnevno po 1 kašičica (5ml).','images/herbiko.jpg','Kašalj'),(7,'Bulardi','Kapsula','AbelaPharm','Zustavlja dijareju, normalizuje rad creva i skraćuje trajanje već postojeće dijareje. ','Odrasli i deca starija od tri godine: 1 kapsula/kesica ujutru i 1 kapsula uveče','images/bulardi.jpg','Stomak'),(9,'BiVits ACTIVA','Tableta','AbelaPharm','Preporučuje se u periodu planiranja začeća, tokom trudnoće i u periodu dojenja. Folna kiselina doprinosi normalnom razvoju i funkciji nervnog sistema, normalnoj produkciji crvenih krvnih zrnaca i pravilnoj deobi ćelija i sintezi DNK.','Odrasli jednom dnevno po 1 tabletu.','images/bivits.jpg','Vitamini'),(10,'Probiotic','Tableta','Hemofarm','Probiotic je dodatak ishrani koji obogaćuje crevnu mikrofloru i tako doprinosi očuvanju zdravlja i funkcije digestivnog trakta.','Odrasli i deca 1 tableta dnevno.','images/probiotic.jpg','Stomak'),(11,'Coldrex','Rastvor','Hemofarm','Lek Coldrex HotRem Lemon je lek za ublažavanje simptoma prehlade i gripa. Kratkotrajno olakšava simptome prehlade i gripa, kao što su: glavobolja, bol u grlu, groznica, zapušenost nosa i sinusa (kongestija), bol u sinusima i u mišićima. ','Odrasli i deca starija od 12 godina: 1 kesica leka na svakih 4 do 6 sati. Ne sme se primenjivati u intervalu kraćem od 4 sata.  Nemojte uzeti više od 5 kesica tokom 24 sata. Nemojte davati lek Coldrex HotRem Lemon deci mlađoj od 12 godina.','images/coldex.jpg','Prehlada'),(12,'Febricet','Tableta','Hemofarm','Koristi u terapiji blagog do umerenog bola poput glavobolje, migrene, zubobolje, suvoće grla, kod menstrualnih bolova, uganuća i reumatskog bola i kod gripa, groznice i prehlade praćene povišenom telesnom temperaturom.','Odrasli i deca starija od 16 godina: 1 do 2 tablete na svakih 4-6 sati, do maksimalno 8 tableta dnevno.','images/febricet.jpg','Prehlada'),(13,'Sinedol','Gel','Hemofarm','Namenjen je lečenju sportskih povreda i drugih povreda mišića, tetiva i zglobova izazvanih tupom silom. Primenjuje se i u terapiji teniskiog lakta, zapaljenja tetiva i omotača tetiva, burzitisa.',' Lek Sinedol gel se nanosi 1-2 puta dnevno na obolelo mesto, u tankom sloju i bez utrljavanja. Koža mora da bude suva i čista pre aplikacije Sinedol gela. Ukoliko se koriste zavoji, nakon nanošenja gela ne pokrivati kožu  nepropusnim zavojem.','images/sinedol.jpg','Povreda'),(14,'Aspirin','Tableta','Bayer','Tablete Aspirin 500mg koriste se za: \n- Blag do umereni bol, \n- Groznicu','Za odrasle: 1 - 2 tablete (500 - 1000mg) koje možete ponovo uzeti kroz 4-8 sati. Najveća dnevna doza je 8 tableta (4000mg acetilsalicilne kiseline).','images/aspirin.jpg','Prehlada'),(15,'Aktivni ugalj','Kapsula','Strong Nature','Aktivni ugalj ima sposobnost adsorpcije velikog broja supstanci organskog i neorganskog porekla, kao i gasova.Absorpcija velikog broja sastojaka hrane, kao i kontaminenata unetih hranom i vodom za piće, aktivnih komponenti mnogih lekova i dr.','Po potrebi uzeti 2-3 kapsule, 3-4 puta na dan','images/ugalj.jpg','Stomak'),(16,'Canesten','Krem','Bayer','Dermatomikoze izazvane dermatofitima, kvasnicama, plesnima i drugim gljivicama (npr. gljivične infekcije stopala, gljivične infekcije ruku, gljivične infekcije trupa, udova i prevoja na koži, gljivične infekcije prepona, pitirijaza, kutane kandidijaze).','Lek Canesten, krem se nanosi u tankom sloju, 2-3 puta na dan i pažljivo utrljava u kožu. Mala količina krema (1/2 cm) je dovoljna za površinu veličine dlana.','images/canesten.jpg','Ostalo'),(17,'Rapidol S','Kapsula','PharmaSwiss','Reumatoidni artritis, ankilozirajući spondilitis, osteoartritis, nereumatoidne artropatije, tendonitis, povrede mekih tkiva kao što su istegnuća i uganuća, stomatoloških i postoperativnih bolova, glavobolje i migrene, simptomatska terapija groznice','Uzima se u toku ili posle obroka,\nu najmanjem intervalu od 4 - 6 sati između 2 doze \n- Odrasli i deca iznad 12 godina (iznad 40kg): \n  1-6 x 200mg ili \n  1-3 x 400mg, \n  Max. 1,2g dnevno','images/rapidol.jpg','Prehlada'),(19,'Caffetin','Tableta','Alkaloid','Kratkotrajna terapija akutog, umerenog bola koji se ne ublažava primenom samog pararacetamola, ibuprofena ili acetilsalicilne kiselina','Ne primenjivati duže od 3 dana kontinuirano.\nOdrasli: 3 x 1 tabl. po potrebi, Max. 6 tableta dnevno;\nDeca 12 - 18 godina: 1 tabl. dnevno, po potrebi do 3 tabl. na dan','images/caffetin.jpg','Glavobolja'),(20,'Panadol Advance','Film tableta','Glaxosmithkline Dungarvan LTD','Lek Panadol Advance je blag analgetik i antipiretik. Upotreba leka preporučuje se za kratkotrajno lečenje glavobolje, bolova u kostima i mišićima, menstrualnih bolova, zubobolje i za lečenje simtoma prehlade i gripa.','Lek Panadol Advance je namenjen za oralnu upotrebu.\nOdrasli (uključujući i starije) i deca uzrasta 16 ili više godina: \nJedna do dve tablete, najviše 4 puta dnevno, po potrebi.\nDeca od 10 do 15 godina:\nJedna tableta, najviše 4 puta dnevno, po potrebi.','images/panadol.jpg','Prehlada'),(21,'Vitamin C Sopharma','Injekcija','Sopharma','Parenteralna primena: terapija deficijencije vitamina C (skorbut), profilaksa deficijencije askorbinske kiseline, kada oralna primena nije moguća ili resorpcija nakon oralnog unosa nije dovoljna za sve uzrasne grupe pacijenata.','Odrasli:\n  500 - 1000mg/dan (lečenje),\n  200 - 500mg/dan (prevencija)\nDeca:\n  100 - 300mg/dan (lečenje),\n  30mg/kg/dan (prevencija)','images/sopharma.jpg','Vitamini');
/*!40000 ALTER TABLE `lekovi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL,
  `timestamp` datetime(6) DEFAULT NULL,
  `receiver_id` int DEFAULT NULL,
  `sender_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt05r0b6n0iis8u7dfna4xdh73` (`receiver_id`),
  KEY `FK4ui4nnwntodh6wjvck53dbk9m` (`sender_id`),
  CONSTRAINT `FK4ui4nnwntodh6wjvck53dbk9m` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt05r0b6n0iis8u7dfna4xdh73` FOREIGN KEY (`receiver_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'cao','2025-02-04 17:59:02.914739',6,8),(2,'cao i tebi','2025-02-04 18:00:37.674530',1,8),(3,'zdravo pox','2025-02-04 18:01:08.512092',8,6),(4,'sta ima','2025-02-04 18:10:52.438868',8,6),(5,'nista kod tebe','2025-02-04 18:11:02.979820',8,6);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_therapy`
--

DROP TABLE IF EXISTS `user_therapy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_therapy` (
  `user_id` int NOT NULL,
  `lek_id` bigint NOT NULL,
  KEY `FKnwy0vo82kesg53yheu173b5ve` (`lek_id`),
  KEY `FKr2j3ikks12mifnycg1soiceck` (`user_id`),
  CONSTRAINT `FKnwy0vo82kesg53yheu173b5ve` FOREIGN KEY (`lek_id`) REFERENCES `lekovi` (`id`),
  CONSTRAINT `FKr2j3ikks12mifnycg1soiceck` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_therapy`
--

LOCK TABLES `user_therapy` WRITE;
/*!40000 ALTER TABLE `user_therapy` DISABLE KEYS */;
INSERT INTO `user_therapy` VALUES (8,21),(8,19),(8,7);
/*!40000 ALTER TABLE `user_therapy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `date_of_birth` datetime(6) DEFAULT NULL,
  `jbr` varchar(255) DEFAULT NULL,
  `zanimanje` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'john_updated','hashed_password1','path/to/profile.jpg','Specijalista za lekove.','Nemanja','Doe',NULL,NULL,NULL),(2,'farmaceut2','hashed_password2',NULL,'Farmaceut u lokalnoj apoteci.',NULL,NULL,NULL,NULL,NULL),(3,'pacijent1','hashed_password3',NULL,'Redovan korisnik.',NULL,NULL,NULL,NULL,NULL),(4,'pacijent2','hashed_password4',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,'john_doe','$2a$10$6fWUfq4FF/z0rM1W4YgV0.iSccIJYXfPeDp9QbzK0JZYkO2aaPWf.',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'Nemanja','$2a$10$dutXXMhcx/D5OC7HuShrjeFVv6o0s3kKDZade..Bfd42vhVTox.ea','C:/ProgramData/MySQL/MySQL Server 8.0/Data/images/profilna.jpg','Projekat iz predmeta Razvoj informacionih sistema','Nemanja','Potic',NULL,NULL,NULL),(7,'potic','$2a$10$e3AjJ3l7bRcLVbiMUI.Ih.DCJt.KidJte2GzTgj8gOLod4/w4GKuS',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,'pox','$2a$10$RjXvSj9546cdpM/waiNoeOF4YAbr3WVybMKfHCoB1Ba6rdr/oxDhK','D:/Eclipse/eclipse-workspace/Backend/uploads/profilna.jpg','Ovo je privremeni opis','Nemanja','Potic',NULL,NULL,NULL),(9,'noviKorisnik','$2a$10$tA0Rvb7bLp9TTgm6AJjbRu2rLeSj./HoJvDjzpNyNShZH3CMAM7d6',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,'potara','$2a$10$CulfeTY/Zb4QuCxmrNdcX.EIXg6zukR0plcI3ZHTfgUqxWNNHXT2y',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,'test','$2a$10$IgT6BYI4LhOUmtreiHHWCe/EUKjL/mjysmDDhlveopBMLAwKrfIMa',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,'ja','$2a$10$GZMoqpuYh8LnI03u497vR.0ZeXmW53Y8nEA1.7urV6XxnL3bIxqNa',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,'Nikola','$2a$10$pYlCx5hdfUHc7tEvMxKC9uvxsmns3pHYrzwr/Wr/Jw2cjS5l0kBu2',NULL,NULL,NULL,NULL,'2021-06-13 02:00:00.000000',NULL,NULL),(14,'','$2a$10$DGfb7aH6v0rtV0rxNx.dPua2IPCMcr6ymtzrnNypzwHE2BSMV86pi',NULL,NULL,NULL,NULL,'2002-09-15 02:00:00.000000',NULL,NULL),(15,'Nina','$2a$10$xhZEVmRRwF27Hes8w84Mo.8awWtYWcCFIAC0iPS32jGHEznmS06qy',NULL,NULL,NULL,NULL,'2025-01-01 01:00:00.000000',NULL,NULL),(16,'pox1','$2a$10$cc2Ryrlzz7moYFNAbaL9Pevgy.4rlleENhYeA9mhRSpZdzcGv6Edm',NULL,NULL,NULL,NULL,'2024-11-28 01:00:00.000000',NULL,NULL),(17,'dejan','$2a$10$LWZU4GFbZmGztD5okVoP3uVL0pKplDw2qas6hjKD9KMneoar4e4ui',NULL,NULL,NULL,NULL,'1970-01-30 01:00:00.000000','729232/71',NULL),(18,'asd','$2a$10$uoarNfKP9g0zatcUfrSY1.k.fXyIB0CzT/yLmlauN3CLE4hMB/ezu',NULL,NULL,NULL,NULL,'2025-01-10 01:00:00.000000','1234',NULL),(19,'assd','$2a$10$Z1CorAp3szTyN54W4zOuZ./LDRYup93PZZ8KD0J3kp/npHHNqKu1K',NULL,NULL,NULL,NULL,'2025-01-08 01:00:00.000000',NULL,NULL),(20,'abc','$2a$10$/77/UXLsUsf81D7E2DDjH.ajpWdoJQZkrFgWFuERhWLaMMfE.Xxs6',NULL,NULL,NULL,NULL,'2025-01-08 01:00:00.000000',NULL,NULL),(21,'neca','$2a$10$DPHn9Tm0vuHWvBSDAFMph.vf7uT7Ff1ckW4ER2e.Ea.C7GMN.X7i.',NULL,NULL,NULL,NULL,'2025-01-02 01:00:00.000000','729231/80',NULL),(22,'necaa','$2a$10$Nl5NViC0WyWElfncrkq1.eghLneYJTpawk28h8oMYFAuxsMbaOVoO',NULL,NULL,NULL,NULL,'2025-01-01 01:00:00.000000','619201/80',NULL),(23,'necaaa','$2a$10$6RShyhJ/hB3didhptS5fKeMUr01VA3jtkymSyw.KSrtcekn0MOFCW',NULL,NULL,NULL,NULL,'2025-01-03 01:00:00.000000',NULL,NULL),(24,'aaa','$2a$10$T1JbG62h3bnzoBj1VEnl.uv3M8oEizYXwxf4JlC8lD11UOqzyUTH.',NULL,NULL,NULL,NULL,'2025-01-01 01:00:00.000000',NULL,NULL),(25,'aaaa','$2a$10$oaSeB02e0i8QDost62ZhcufiV5hRJZv8OOzlvVxYxFPkx4MLV35SK',NULL,NULL,NULL,NULL,'2025-01-01 01:00:00.000000',NULL,NULL),(26,'aa','$2a$10$sz/2f/5cMFNKjlcWfILTp.TR3QuWiyBFkFs12WeLJI9YcPgZTaPTy',NULL,NULL,NULL,NULL,'2025-01-08 01:00:00.000000',NULL,NULL),(27,'a','$2a$10$D4TVXV6yK99.G1QnTGJvDOi3ydrnYHXeM6WylEn0gmPjygoDxifMS',NULL,NULL,NULL,NULL,'2025-01-01 01:00:00.000000',NULL,'729235/80'),(28,'b','$2a$10$7c.TTOtYAdiupar3aC7KaOlAAQDauZhGnv8bf0eK99KNuvJmpc8vO',NULL,NULL,NULL,NULL,'2025-01-01 01:00:00.000000',NULL,'FARMACEUT - SPECIJALISTA LABORATORIJSKE HEMATOLOGIJE'),(29,'bb','$2a$10$c48pT606XXxnKdLfxd2d7uk.XHrT4PgiPzUfCI0TDlF8/APnY1ulm',NULL,NULL,NULL,NULL,'2025-01-01 01:00:00.000000',NULL,'DOKTOR NAUKA - FARMACEUT');
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

-- Dump completed on 2025-02-06 15:26:23
