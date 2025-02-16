-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: baza_lekova
-- ------------------------------------------------------
-- Server version	8.0.40

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
INSERT INTO `friendships` VALUES (1,'2025-02-16 00:00:00.000000',6,8);
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
INSERT INTO `lekovi` VALUES (1,'Analgin','Tableta','Alkaloid','Kratkotrajna primena kod jakih bolova (posttraumatskih i postoperativnih), kada se terapija drugim neopioidnim analgeticima pokaže neuspešnom.','Odrasli i adolescenti uzrasta od 15 godina ili stariji (> 53 kg) mogu uzeti do 1000 mg metamizola kao jednu dozu, a ona se može uzeti do 4 puta na dan u razmacima od 6 do 8 sati, što odgovara maksimalno 0 mg dnevno40.','/images/analgin.jpg','Glavobolja'),(2,'Brufen','Tableta','Mylan Madjarska','Reumatoidni artritis, artroza. Dismenoreja bez organskog uzroka. Bol blagog do umerenog intenziteta. Povišena telesna temperatura kod odraslih i adolescenata.','Odrasl (≥ 40 kg): Jedna tableta od 400 mg tri puta dnevno. Interval između doza treba da je najmanje 4-6 sati.','/images/brufen.jpg','Glavobolja'),(3,'Dimigal','Tableta','Galenika','Lek Dimigal je indikovan kod odraslih osoba i dece uzrasta 2 godine i više u:\nprevenciji mučnine, povraćanja i/ili vrtoglavice kod kinetoza;\nsimptomatskoj terapiji mučnine i povraćanja kod Menierove bolesti i drugih vestibularnih poremećaja.','Odrasli i deca uzrasta od 14 godina:\n50-100 mg svakih 4-6 sati po potrebi, maksimalno 400 mg dnevno.\nDeca od 6 do 14 godina:\n25-50 mg svakih 6-8 sati po potrebi, maksimalno 150 mg dnevno.','/images/dimigal.jpg','Stomak'),(4,'Nebilet','Tableta','Berlin Chemie','Terapija blage do umerene stabilne, hronične srčane insuficijencije, kao dodatak standardnoj terapiji kod pacijenata starosti 70 godina i više.','Odrasli: Uobičajeno doziranje je 1 tableta (5 mg) dnevno. Preporučuje se uzimanje tablete svakog dana u isto vreme. Efekat snižavanja krvnog pritiska se zapaža nakon 1-2 nedelje lečenja. ','/images/nebilet.jpg','Ostalo'),(5,'PropoMucil','Rastvor','AbelaPharm','Olakšava izbacivanje sekreta iz disajnih puteva i olakšava disanje. Pomaže uklanjanju virusa, bakterija i gljivica i pojačava imunski odgovor','Odrasli i deca starija od 14 godina:\n1 puta dnevno po 1 kesicu praška rastvoriti u čaši vode','/images/propomucil.jpg','Kašalj'),(6,'Herbiko','Rastvor','AbelaPharm','Kod suvog kašlja: Oblaže sluzokožu grla i umiruje napade kašlja\nKod produktivnog kašlja: otvara disajne puteve, razlaže gust sekret, olakšava iskašljavanje','Odrasli i deca starija od 12 godina: 3 puta dnevno po 1 velika kašika (15ml).\nDeca od 4-12 godina: 3 puta dnevno po 1 kašičica (5ml).','/images/herbiko.jpg','Kašalj'),(7,'Bulardi','Kapsula','AbelaPharm','Zustavlja dijareju, normalizuje rad creva i skraćuje trajanje već postojeće dijareje. ','Odrasli i deca starija od tri godine: 1 kapsula/kesica ujutru i 1 kapsula uveče','/images/bulardi.jpg','Stomak'),(8,'Bensedin','Tableta','Galenika','Za kratkoročno smanjenje simptoma (samo 2-4 nedelje) kod teškog oblika anksioznosti, cerebralna paraliza, mišićni spazam, dodatna terapija u pojedinim vrstama epilepsije, simptomatsko lečenje akutne alkoholne apstinencije.','Za postizanje anksiolitičkog efekta treba dati najnižu efektivnu dozu: lečenje ne treba da traje duže od 4 nedelje i lek treba postepeno povlačiti iz terapije. ','/images/bensedin.jpg','Ostalo'),(9,'BiVits ACTIVA','Tableta','AbelaPharm','Preporučuje se u periodu planiranja začeća, tokom trudnoće i u periodu dojenja. Folna kiselina doprinosi normalnom razvoju i funkciji nervnog sistema, normalnoj produkciji crvenih krvnih zrnaca i pravilnoj deobi ćelija i sintezi DNK.','Odrasli jednom dnevno po 1 tabletu.','/images/bivits.jpg','Vitamini'),(10,'Probiotic','Tableta','Hemofarm','Probiotic je dodatak ishrani koji obogaćuje crevnu mikrofloru i tako doprinosi očuvanju zdravlja i funkcije digestivnog trakta.','Odrasli i deca 1 tableta dnevno.','/images/probiotic.jpg','Stomak'),(11,'Coldrex','Rastvor','Hemofarm','Lek Coldrex HotRem Lemon je lek za ublažavanje simptoma prehlade i gripa. Kratkotrajno olakšava simptome prehlade i gripa, kao što su: glavobolja, bol u grlu, groznica, zapušenost nosa i sinusa (kongestija), bol u sinusima i u mišićima. ','Odrasli i deca starija od 12 godina: 1 kesica leka na svakih 4 do 6 sati. Ne sme se primenjivati u intervalu kraćem od 4 sata.  Nemojte uzeti više od 5 kesica tokom 24 sata. Nemojte davati lek Coldrex HotRem Lemon deci mlađoj od 12 godina.','/images/coldrex.jpg','Prehlada'),(12,'Febricet','Tableta','Hemofarm','Koristi u terapiji blagog do umerenog bola poput glavobolje, migrene, zubobolje, suvoće grla, kod menstrualnih bolova, uganuća i reumatskog bola i kod gripa, groznice i prehlade praćene povišenom telesnom temperaturom.','Odrasli i deca starija od 16 godina: 1 do 2 tablete na svakih 4-6 sati, do maksimalno 8 tableta dnevno.','/images/febricet.jpg','Prehlada'),(13,'Sinedol','Gel','Hemofarm','Namenjen je lečenju sportskih povreda i drugih povreda mišića, tetiva i zglobova izazvanih tupom silom. Primenjuje se i u terapiji teniskiog lakta, zapaljenja tetiva i omotača tetiva, burzitisa.',' Lek Sinedol gel se nanosi 1-2 puta dnevno na obolelo mesto, u tankom sloju i bez utrljavanja. Koža mora da bude suva i čista pre aplikacije Sinedol gela. Ukoliko se koriste zavoji, nakon nanošenja gela ne pokrivati kožu  nepropusnim zavojem.','/images/sinedol.jpg','Povreda'),(14,'Aspirin','Tableta','Bayer','Tablete Aspirin 500mg koriste se za: \n- Blag do umereni bol, \n- Groznicu','Za odrasle: 1 - 2 tablete (500 - 1000mg) koje možete ponovo uzeti kroz 4-8 sati. Najveća dnevna doza je 8 tableta (4000mg acetilsalicilne kiseline).','/images/aspirin.jpg','Prehlada'),(15,'Aktivni ugalj','Kapsula','Strong Nature','Aktivni ugalj ima sposobnost adsorpcije velikog broja supstanci organskog i neorganskog porekla, kao i gasova.Absorpcija velikog broja sastojaka hrane, kao i kontaminenata unetih hranom i vodom za piće, aktivnih komponenti mnogih lekova i dr.','Po potrebi uzeti 2-3 kapsule, 3-4 puta na dan','/images/ugalj.jpg','Stomak'),(16,'Canesten','Krem','Bayer','Dermatomikoze izazvane dermatofitima, kvasnicama, plesnima i drugim gljivicama (npr. gljivične infekcije stopala, gljivične infekcije ruku, gljivične infekcije trupa, udova i prevoja na koži, gljivične infekcije prepona, pitirijaza, kutane kandidijaze).','Lek Canesten, krem se nanosi u tankom sloju, 2-3 puta na dan i pažljivo utrljava u kožu. Mala količina krema (1/2 cm) je dovoljna za površinu veličine dlana.','/images/canesten.jpg','Ostalo'),(17,'Rapidol S','Kapsula','PharmaSwiss','Reumatoidni artritis, ankilozirajući spondilitis, osteoartritis, nereumatoidne artropatije, tendonitis, povrede mekih tkiva kao što su istegnuća i uganuća, stomatoloških i postoperativnih bolova, glavobolje i migrene, simptomatska terapija groznice','Uzima se u toku ili posle obroka,\nu najmanjem intervalu od 4 - 6 sati između 2 doze \n- Odrasli i deca iznad 12 godina (iznad 40kg): \n  1-6 x 200mg ili \n  1-3 x 400mg, \n  Max. 1,2g dnevno','/images/rapidol.jpg','Prehlada'),(18,'Magnezijum Citrat','Kapsula','Solgar','Simptomi i znaci nedostatka magnezijuma u organizmu su: neuromišićni poremećaji, srčane aritmije, povišen tonus muskulature, povećana osetljivost na stres, glavobolja, poremećaji raspoloženja, umor, iscrpljenost.','Odrasli:  2 tablete (266 mg) dnevno pre odlaska na spavanje.','/images/magnezijum.jpg','Vitamini'),(19,'Caffetin','Tableta','Alkaloid','Kratkotrajna terapija akutog, umerenog bola koji se ne ublažava primenom samog pararacetamola, ibuprofena ili acetilsalicilne kiselina','Ne primenjivati duže od 3 dana kontinuirano.\nOdrasli: 3 x 1 tabl. po potrebi, Max. 6 tableta dnevno;\nDeca 12 - 18 godina: 1 tabl. dnevno, po potrebi do 3 tabl. na dan','/images/caffetin.jpg','Glavobolja'),(20,'Panadol Advance','Film tableta','Glaxosmithkline Dungarvan LTD','Lek Panadol Advance je blag analgetik i antipiretik. Upotreba leka preporučuje se za kratkotrajno lečenje glavobolje, bolova u kostima i mišićima, menstrualnih bolova, zubobolje i za lečenje simtoma prehlade i gripa.','Lek Panadol Advance je namenjen za oralnu upotrebu.\nOdrasli (uključujući i starije) i deca uzrasta 16 ili više godina: \nJedna do dve tablete, najviše 4 puta dnevno, po potrebi.\nDeca od 10 do 15 godina:\nJedna tableta, najviše 4 puta dnevno, po potrebi.','/images/panadol.jpg','Prehlada'),(21,'Vitamin C Sopharma','Injekcija','Sopharma','Parenteralna primena: terapija deficijencije vitamina C (skorbut), profilaksa deficijencije askorbinske kiseline, kada oralna primena nije moguća ili resorpcija nakon oralnog unosa nije dovoljna za sve uzrasne grupe pacijenata.','Odrasli:\n  500 - 1000mg/dan (lečenje),\n  200 - 500mg/dan (prevencija)\nDeca:\n  100 - 300mg/dan (lečenje),\n  30mg/kg/dan (prevencija)','/images/sopharma.jpg','Vitamini');
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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (29,'Hello :)','2025-02-16 20:23:57.056672',6,8),(30,'Hi :)','2025-02-16 20:24:47.064456',8,6),(31,'How\'s it going?','2025-02-16 20:25:47.038336',6,8);
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
INSERT INTO `user_therapy` VALUES (8,1),(8,3);
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
INSERT INTO `users` VALUES (6,'lisa','$2a$10$dutXXMhcx/D5OC7HuShrjeFVv6o0s3kKDZade..Bfd42vhVTox.ea','/images/05b46d90-7336-4539-a11e-5efa71e93a70_WhatsApp Image 2023-03-12 at 01.58.55 (1).jpg','Projekat iz predmeta Razvoj informacionih sistema','Lisa','Walter','1990-02-15 00:00:00.000000',NULL,NULL),(8,'pox','$2a$10$RjXvSj9546cdpM/waiNoeOF4YAbr3WVybMKfHCoB1Ba6rdr/oxDhK','/images/2cfe167d-f2f8-40b7-9862-6bf0cda31904_home.jpg','Ovo je privremeni opis','Nemanja','Potic','1995-02-15 00:00:00.000000',NULL,NULL);
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

-- Dump completed on 2025-02-16 20:51:11
