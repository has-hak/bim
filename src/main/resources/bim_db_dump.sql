-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: bim_db
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Table structure for table `compilations`
--

DROP TABLE IF EXISTS `compilations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compilations` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  FULLTEXT KEY `title_2` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compilations`
--

LOCK TABLES `compilations` WRITE;
/*!40000 ALTER TABLE `compilations` DISABLE KEYS */;
INSERT INTO `compilations` VALUES (2,'բետոնե պատեր և միջնապատեր'),(3,'խմելու ջրի բաքերի ախտահանում'),(1,'միաձյուլ երկաթբետոնե կոնստրուկցիաներ');
/*!40000 ALTER TABLE `compilations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'1','initial tables','SQL','V1__initial_tables.sql',1715225546,'root','2021-10-24 12:28:30',1002,1),(2,'2','fill messages','SQL','V2__fill_messages.sql',-1384875909,'root','2021-10-24 12:28:30',47,1),(3,NULL,'fill languages','SQL','R__fill_languages.sql',-1606504866,'root','2021-10-24 12:28:30',6,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `languages`
--

DROP TABLE IF EXISTS `languages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `languages` (
  `id` int NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  UNIQUE KEY `id` (`id`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `languages`
--

LOCK TABLES `languages` WRITE;
/*!40000 ALTER TABLE `languages` DISABLE KEYS */;
INSERT INTO `languages` VALUES (2,'ARM'),(1,'ENG'),(3,'RUS');
/*!40000 ALTER TABLE `languages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `machines`
--

DROP TABLE IF EXISTS `machines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `machines` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `unit` double NOT NULL,
  `unit_cost` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `machines`
--

LOCK TABLES `machines` WRITE;
/*!40000 ALTER TABLE `machines` DISABLE KEYS */;
INSERT INTO `machines` VALUES (1,'19-100-1105','Աշտարակային կռունկներ 8 տ բետնամբարձությամբ',0.86,18.2311),(2,'19-100-1113','Ավտոմոբիլային կռունկներ 10 տ բետնամբարձությամբ',0.0239,6.4635),(3,'19-100-1029','Ավտոբեռնիչներ 5տ',0.0027,7.2444),(4,'19-100-1033','Խորքային թրթռիչներ',0.6,0.2326),(5,'19-100-1035','Ավտոմոբլիներ մինչև 8 տ բետնամբարձությամբ',0.0359,0),(6,'19-100-1061','Եռակցման ձեռքի սարքեր (հաստատուն հոսանքի)',1,1.4557),(7,'19-100-1111','Էլեկտրական սղոցներ (շղթայական)',0.019,0.4211);
/*!40000 ALTER TABLE `machines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials`
--

DROP TABLE IF EXISTS `materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `unit` double NOT NULL,
  `unit_cost` double NOT NULL,
  `measure_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials`
--

LOCK TABLES `materials` WRITE;
/*!40000 ALTER TABLE `materials` DISABLE KEYS */;
INSERT INTO `materials` VALUES (1,'2105-0301-3001','Ամրան',1,311,'WEIGHT'),(2,'2101-0101-1001','Ծանր բետոն B 25',1.015,33.014,'VOLUME'),(3,'2107-0201-0204','Սղոցված փայտանյութ մինչև 44 մմ հաստության II կարգի',0.0025,122.9,'VOLUME'),(4,'2107-0203-0103','Տախտակ վահանի 25 մմ հաստության',1.35,3.07,'AREA'),(5,'2113-0209','Մեխ շինարարական',0.45,100,'WEIGHT'),(6,'0001','Ջուր',0.0025,0,'VOLUME'),(7,'2107-0201-0301','Սղոցված փայտանյութ մինչև 75-150մմ հաստության III կարգի',0.01159,122.9,'VOLUME'),(8,'2105-0307-0104','Լար մինչև 1․1 մմ հաստության',0.47,0,'WEIGHT'),(9,'2113-0812-1046','Էլեկտրոդներ 4 մմ տրամագծով Э42',0.17,0,'WEIGHT'),(13,'2113-0812-1047','Էլեկտրոդներ 6 մմ տրամագծով Э42',0.17,0,'WEIGHT'),(14,'2113-0803-0801','Պաստառ',0.429,0,'AREA'),(15,'2107-0506-0401','\"Դոկա\" կաղապարամածի նրբատախտակ',0.292,0,'AREA'),(16,'2113-0702-0101','Յուղեր անտրացենային',1.73,0,'WEIGHT');
/*!40000 ALTER TABLE `materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` varchar(255) NOT NULL,
  `values` json NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES ('ARM','{\"1\": \"Armenian\", \"2\": \"Հայերեն\", \"3\": \"Армянский\"}'),('compilation','{\"1\": \"Compilation\", \"2\": \"Ժողովածու\", \"3\": \"Сборник\"}'),('compilations','{\"1\": \"Compilations\", \"2\": \"Ժողովածուներ\", \"3\": \"Сборники\"}'),('ENG','{\"1\": \"English\", \"2\": \"Անգլերեն\", \"3\": \"Английский\"}'),('fields.code','{\"1\": \"Code\", \"2\": \"Ծածկագիր\", \"3\": \"Код\"}'),('fields.id','{\"1\": \"ID\", \"2\": \"ID\", \"3\": \"ID\"}'),('fields.measure-type','{\"1\": \"Measure type\", \"2\": \"Չափման միավոր\", \"3\": \"Тип измирения\"}'),('fields.title','{\"1\": \"Title\", \"2\": \"Անվանում\", \"3\": \"Название\"}'),('fields.unit','{\"1\": \"Unit\", \"2\": \"Միավոր\", \"3\": \"Единица\"}'),('fields.unit-cost','{\"1\": \"Unit cost\", \"2\": \"Միավոր արժեք\", \"3\": \"Стоимость единицы\"}'),('machines','{\"1\": \"Machines\", \"2\": \"Մեքենաներ\", \"3\": \"Машины\"}'),('materials','{\"1\": \"Materials\", \"2\": \"Նյութեր\", \"3\": \"Материалы\"}'),('measure-types.AREA','{\"1\": \"Area\", \"2\": \"Մակերես\", \"3\": \"Площадь\"}'),('measure-types.HEIGHT','{\"1\": \"Height\", \"2\": \"Բարձրություն\", \"3\": \"Высота\"}'),('measure-types.PERIMETER','{\"1\": \"Perimeter\", \"2\": \"Պարագիծ\", \"3\": \"Периметр\"}'),('measure-types.THICKNESS','{\"1\": \"Thickness\", \"2\": \"Հաստություն\", \"3\": \"Толщина\"}'),('measure-types.VOLUME','{\"1\": \"Volume\", \"2\": \"Ծավալ\", \"3\": \"Объем\"}'),('measure-types.WEIGHT','{\"1\": \"Weight\", \"2\": \"Քաշ\", \"3\": \"Масса\"}'),('measure-units.HUMAN_HOUR','{\"1\": \"human-hour\", \"2\": \"մարդ-ժամ\", \"3\": \"человек-час\"}'),('measure-units.KG','{\"1\": \"kg\", \"2\": \"կգ\", \"3\": \"кг\"}'),('measure-units.M','{\"1\": \"m\", \"2\": \"մ\", \"3\": \"м\"}'),('measure-units.M2','{\"1\": \"m²\", \"2\": \"մ²\", \"3\": \"м²\"}'),('measure-units.M3','{\"1\": \"m³\", \"2\": \"մ³\", \"3\": \"м³\"}'),('measure-units.MACHINE_HOUR','{\"1\": \"machine-hour\", \"2\": \"մեքենա-ժամ\", \"3\": \"машина-час\"}'),('measure-units.MM','{\"1\": \"mm\", \"2\": \"մմ\", \"3\": \"мм\"}'),('measure-units.SM','{\"1\": \"sm\", \"2\": \"սմ\", \"3\": \"см\"}'),('measure-units.TON','{\"1\": \"ton\", \"2\": \"տ\", \"3\": \"тонна\"}'),('N/A','{\"1\": \"N/A\", \"2\": \"Չկա\", \"3\": \"Н/П\"}'),('resources','{\"1\": \"Resources\", \"2\": \"Աշխատանքներ\", \"3\": \"Работы\"}'),('RUS','{\"1\": \"Russian\", \"2\": \"Ռուսերեն\", \"3\": \"Русский\"}'),('ui.create','{\"1\": \"Save\", \"2\": \"Պահպանել\", \"3\": \"Сохранить\"}'),('ui.forms.compilation.create','{\"1\": \"Create new compilation\", \"2\": \"Ավելացնել նոր ժողավածու\", \"3\": \"Добавить новый сборник\"}'),('ui.forms.machine.create','{\"1\": \"Create new machine\", \"2\": \"Ավելացնել նոր մեքենա\", \"3\": \"Добавить новую машину\"}'),('ui.forms.material.create','{\"1\": \"Create new material\", \"2\": \"Ավելացնել նոր նյութ\", \"3\": \"Добавить новый материал\"}'),('ui.forms.resource.create','{\"1\": \"Create new resource\", \"2\": \"Ավելացնել նոր աշխատանք\", \"3\": \"Добавить новую работу\"}'),('ui.forms.workforce.create','{\"1\": \"Create new workforce\", \"2\": \"Ավելացնել նոր աշխատուժ\", \"3\": \"Добавить новую рабочую силу\"}'),('ui.logout','{\"1\": \"Logout\", \"2\": \"Դուրս Գալ\", \"3\": \"Выход\"}'),('ui.main.data-view','{\"1\": \"Data View\", \"2\": \"Տվյալների դիտում\", \"3\": \"Просмотр данных\"}'),('ui.main.data-view.add-new','{\"1\": \"Add New\", \"2\": \"Ավելացնել\", \"3\": \"Добавить\"}'),('ui.main.outlay-calculation','{\"1\": \"Outlay Calculation\", \"2\": \"Նախահաշվի կազմում\", \"3\": \"Расчет сметы\"}'),('ui.main.outlay-calculation.download-excel-version','{\"1\": \"Download Excel Version\", \"2\": \"Ներբեռնել Excel տարբերակը\", \"3\": \"Скачать Excel версию\"}'),('ui.main.outlay-calculation.not-selected','{\"1\": \"Not Selected\", \"2\": \"Ընտրված չէ\", \"3\": \"Не выбрано\"}'),('ui.main.outlay-calculation.select-document','{\"1\": \"Select document\", \"2\": \"Ընտրեք ծավալաթերթը\", \"3\": \"Выберите документ\"}'),('ui.main.outlay-calculation.select-file','{\"1\": \"Select File\", \"2\": \"Ընտրեք ֆայլը\", \"3\": \"Выберите файл\"}'),('ui.main.outlay-table.direct-cost','{\"1\": \"Direct cost\", \"2\": \"Ուղղակի ծախսեր\", \"3\": \"Прямые затраты\"}'),('ui.main.outlay-table.machine/mechanisms','{\"1\": \"Machine/mechanisms\", \"2\": \"Մեքենա/մեխանիզմենր\", \"3\": \"Машины/Механизмы\"}'),('ui.main.outlay-table.net-cost','{\"1\": \"Net cost\", \"2\": \"Ինքնաժեք\", \"3\": \"Net cost\"}'),('ui.main.outlay-table.outlay-cost','{\"1\": \"Total Outlay cost\", \"2\": \"Ընդամենը նախահաշվային արժեք\", \"3\": \"Общая сметная цена\"}'),('ui.main.outlay-table.overall-cost','{\"1\": \"Overall cost\", \"2\": \"Ընդհանուր ծախսեր\", \"3\": \"Общие затраты\"}'),('ui.main.outlay-table.overhead-cost','{\"1\": \"Overhead cost\", \"2\": \"Վերադիր ծախսեր\", \"3\": \"Накладные расходы\"}'),('ui.main.outlay-table.profit','{\"1\": \"Profit\", \"2\": \"Շահույթ\", \"3\": \"Прибыль\"}'),('ui.main.outlay-table.structure','{\"1\": \"Structure\", \"2\": \"Կառուցվածք\", \"3\": \"Структура\"}'),('ui.main.outlay-table.total-cost','{\"1\": \"Total cost\", \"2\": \"Ընդհանուր արժեք\", \"3\": \"Общая цена\"}'),('ui.sign-in','{\"1\": \"Sign In\", \"2\": \"Մուտք\", \"3\": \"Вход\"}'),('ui.sign-up','{\"1\": \"Sign Up\", \"2\": \"Գրանցվել\", \"3\": \"Регистрация\"}'),('validation.code.not-blank','{\"1\": \"Code must be non empty\", \"2\": \"Code must be non empty\", \"3\": \"Code must be non empty\"}'),('validation.code.not-null','{\"1\": \"Code must be not null\", \"2\": \"Code must be not null\", \"3\": \"Code must be not null\"}'),('validation.compilationId.not-null','{\"1\": \"Compilation must be not null\", \"2\": \"Compilation must be not null\", \"3\": \"Compilation must be not null\"}'),('validation.measureType.not-null','{\"1\": \"Measure type must be not null\", \"2\": \"Measure type must be not null\", \"3\": \"Measure type must be not null\"}'),('validation.title.not-blank','{\"1\": \"Title must be non empty\", \"2\": \"Title must be non empty\", \"3\": \"Title must be non empty\"}'),('validation.title.not-null','{\"1\": \"Title must be not null\", \"2\": \"Title must be not null\", \"3\": \"Title must be not null\"}'),('validation.unit.not-null','{\"1\": \"Unit must be not null\", \"2\": \"Unit must be not null\", \"3\": \"Unit must be not null\"}'),('validation.unitCost.not-null','{\"1\": \"Unit cost must be not null\", \"2\": \"Unit cost must be not null\", \"3\": \"Unit cost must be not null\"}'),('workforces','{\"1\": \"Workforces\", \"2\": \"Աշխատուժ\", \"3\": \"Рабочая сила\"}');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_machines`
--

DROP TABLE IF EXISTS `resource_machines`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource_machines` (
  `resource_id` bigint NOT NULL,
  `machine_id` bigint NOT NULL,
  KEY `resource_id` (`resource_id`),
  KEY `machine_id` (`machine_id`),
  CONSTRAINT `resource_machines_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`) ON DELETE CASCADE,
  CONSTRAINT `resource_machines_ibfk_2` FOREIGN KEY (`machine_id`) REFERENCES `machines` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_machines`
--

LOCK TABLES `resource_machines` WRITE;
/*!40000 ALTER TABLE `resource_machines` DISABLE KEYS */;
INSERT INTO `resource_machines` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,7),(3,1),(3,2),(3,3),(3,4),(3,5),(3,7),(4,1),(4,2),(4,3),(4,4),(4,5),(4,7),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7);
/*!40000 ALTER TABLE `resource_machines` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_materials`
--

DROP TABLE IF EXISTS `resource_materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource_materials` (
  `resource_id` bigint NOT NULL,
  `material_id` bigint NOT NULL,
  KEY `resource_id` (`resource_id`),
  KEY `material_id` (`material_id`),
  CONSTRAINT `resource_materials_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`) ON DELETE CASCADE,
  CONSTRAINT `resource_materials_ibfk_2` FOREIGN KEY (`material_id`) REFERENCES `materials` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_materials`
--

LOCK TABLES `resource_materials` WRITE;
/*!40000 ALTER TABLE `resource_materials` DISABLE KEYS */;
INSERT INTO `resource_materials` VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6),(2,7),(2,8),(2,9),(3,1),(3,2),(3,4),(3,6),(3,7),(3,8),(3,13),(3,14),(4,1),(4,2),(4,4),(4,5),(4,6),(4,7),(4,8),(4,9),(5,1),(5,2),(5,5),(5,6),(5,7),(5,8),(5,9),(5,14),(5,15),(5,16),(1,1),(1,2),(1,3),(1,4),(1,5),(1,6);
/*!40000 ALTER TABLE `resource_materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource_workforces`
--

DROP TABLE IF EXISTS `resource_workforces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resource_workforces` (
  `resource_id` bigint NOT NULL,
  `workforce_id` bigint NOT NULL,
  KEY `resource_id` (`resource_id`),
  KEY `workforce_id` (`workforce_id`),
  CONSTRAINT `resource_workforces_ibfk_1` FOREIGN KEY (`resource_id`) REFERENCES `resources` (`id`) ON DELETE CASCADE,
  CONSTRAINT `resource_workforces_ibfk_2` FOREIGN KEY (`workforce_id`) REFERENCES `workforces` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource_workforces`
--

LOCK TABLES `resource_workforces` WRITE;
/*!40000 ALTER TABLE `resource_workforces` DISABLE KEYS */;
INSERT INTO `resource_workforces` VALUES (2,1),(2,2),(3,1),(3,2),(4,1),(4,2),(5,1),(5,2),(1,1),(1,2);
/*!40000 ALTER TABLE `resource_workforces` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resources`
--

DROP TABLE IF EXISTS `resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resources` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `measures` json NOT NULL,
  `compilation_id` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  KEY `compilation_id` (`compilation_id`),
  FULLTEXT KEY `title` (`title`),
  CONSTRAINT `resources_ibfk_1` FOREIGN KEY (`compilation_id`) REFERENCES `compilations` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resources`
--

LOCK TABLES `resources` WRITE;
/*!40000 ALTER TABLE `resources` DISABLE KEYS */;
INSERT INTO `resources` VALUES (1,'11221--','սյուների իրականացում ԴՈԿԱ տիպի մոդուլային կաղապարամածում (պարագիծը մինչև 2 մ , բարձրությունը ՝մինչև 4 մ)','{}',1),(2,'06-01-031','երկաթբետոնե հեծանների իրականացում փայտե կաղապարամածում (բարձրությունը մինչև 500 մմ , բարձրությունը հարթակից՝ մինչև 6 մ)','{}',1),(3,'06-01-041','երկաթբետոնե ծածկերի իրականացում փայտե կաղապարամածում (հաստությունը մինչև 200 մմ , բարձրությունը հարթակից՝ մինչև 6 մ)','{}',1),(4,'06-01-0314','երկաթբետոնե պատերի իրականացում փայտե կաղապարամածում (հաստությունը մինչև 300 մմ , բարձրությունը՝ մինչև 6 մ)','{}',1),(5,'06-01-1111','երկաթբետոնե ուղղանկյունաձև աստիճանաքայլերի իրականացում ԴՈԿԱ տիպի մոդուլային կաղապարամածում','{}',1);
/*!40000 ALTER TABLE `resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_preferences`
--

DROP TABLE IF EXISTS `user_preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_preferences` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `language_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  KEY `language_id` (`language_id`),
  CONSTRAINT `user_preferences_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `user_preferences_ibfk_2` FOREIGN KEY (`language_id`) REFERENCES `languages` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_preferences`
--

LOCK TABLES `user_preferences` WRITE;
/*!40000 ALTER TABLE `user_preferences` DISABLE KEYS */;
INSERT INTO `user_preferences` VALUES (2,2,2);
/*!40000 ALTER TABLE `user_preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role` varchar(255) NOT NULL,
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (2,'anikhachatryan57@gmail.com','Ani Khachatryan','$2a$10$NBdF2ao9UCNKkj/xczVsnOP/7VVqxM9AHT5/PrB2.1M0c.IJU12Ga');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_scope`
--

DROP TABLE IF EXISTS `work_scope`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `work_scope` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_scope`
--

LOCK TABLES `work_scope` WRITE;
/*!40000 ALTER TABLE `work_scope` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_scope` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workforces`
--

DROP TABLE IF EXISTS `workforces`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workforces` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `unit` double NOT NULL,
  `unit_cost` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workforces`
--

LOCK TABLES `workforces` WRITE;
/*!40000 ALTER TABLE `workforces` DISABLE KEYS */;
INSERT INTO `workforces` VALUES (1,'1','Ժամանակի նորման շինարար-բանվորների համար',14.63,1046),(2,'2','Ժամանակի նորման մեքենավար-բանվորների համար',0.92,1046);
/*!40000 ALTER TABLE `workforces` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-12 23:17:20
