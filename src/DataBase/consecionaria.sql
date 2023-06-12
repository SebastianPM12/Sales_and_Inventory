CREATE DATABASE  IF NOT EXISTS `medgarperu` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `medgarperu`;
-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: medgarperu
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `asesores`
--

DROP TABLE IF EXISTS `asesores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `asesores` (
  `DniAsesor` int NOT NULL,
  `Nombres` varchar(30) DEFAULT NULL,
  `Apellidos` varchar(30) DEFAULT NULL,
  `Celular` int DEFAULT NULL,
  PRIMARY KEY (`DniAsesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asesores`
--

LOCK TABLES `asesores` WRITE;
/*!40000 ALTER TABLE `asesores` DISABLE KEYS */;
INSERT INTO `asesores` VALUES (123456,'DASSAD','ADSDAS',312123213),(321123,'ASDASD','DSAASD',132),(784545,'dasads','GAA',231123);
/*!40000 ALTER TABLE `asesores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cabeceradeventas`
--

DROP TABLE IF EXISTS `cabeceradeventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabeceradeventas` (
  `OrdenDeVenta` varchar(15) NOT NULL,
  `DniRuc` varchar(15) DEFAULT NULL,
  `Ruc` varchar(15) DEFAULT NULL,
  `NombreCliente` varchar(30) DEFAULT NULL,
  `FechaEmision` date DEFAULT NULL,
  `DniAsesor` int DEFAULT NULL,
  `IdVendedor` varchar(10) DEFAULT NULL,
  `ValorTotal` double DEFAULT NULL,
  `Igv` double DEFAULT NULL,
  `PrecioTotal` double DEFAULT NULL,
  PRIMARY KEY (`OrdenDeVenta`),
  KEY `DniRuc` (`DniRuc`),
  KEY `Ruc` (`Ruc`),
  KEY `cabeceradeventas_ibfk_3` (`DniAsesor`),
  KEY `cabeceradeventas_ibfk_4` (`IdVendedor`),
  CONSTRAINT `cabeceradeventas_ibfk_1` FOREIGN KEY (`DniRuc`) REFERENCES `clientes` (`DniRuc`),
  CONSTRAINT `cabeceradeventas_ibfk_2` FOREIGN KEY (`Ruc`) REFERENCES `empresa` (`Ruc`),
  CONSTRAINT `cabeceradeventas_ibfk_3` FOREIGN KEY (`DniAsesor`) REFERENCES `asesores` (`DniAsesor`),
  CONSTRAINT `cabeceradeventas_ibfk_4` FOREIGN KEY (`IdVendedor`) REFERENCES `vendedores` (`IdVendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabeceradeventas`
--

LOCK TABLES `cabeceradeventas` WRITE;
/*!40000 ALTER TABLE `cabeceradeventas` DISABLE KEYS */;
INSERT INTO `cabeceradeventas` VALUES ('001 - 00001','77819452','20607107131','Sebastia','2023-02-19',123456,'u2023',302.58,66.42,369),('001 - 00002','77819452','20607107131','Sebastia','2023-02-23',123456,'u2023',870.84,191.16,1062),('001 - 00003','77819452','20607107131','Sebastia','2023-02-23',NULL,'u2023',870.84,191.16,1062),('001 - 00004','77819452','20607107131','Sebastia','2023-02-23',123456,NULL,1584.24,347.76,1932),('001 - 00005','77819452','20607107131','Sebastia','2023-02-23',NULL,NULL,302.58,66.42,369);
/*!40000 ALTER TABLE `cabeceradeventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cabeceraentrada`
--

DROP TABLE IF EXISTS `cabeceraentrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabeceraentrada` (
  `NumeroEntrada` varchar(15) NOT NULL,
  `IdProveedor` varchar(15) NOT NULL,
  `IdPersonal` varchar(15) NOT NULL,
  `Tipo` varchar(15) DEFAULT NULL,
  `FechaEmision` date DEFAULT NULL,
  `ValorTotal` double DEFAULT NULL,
  `Igv` double DEFAULT NULL,
  `PrecioTotal` double DEFAULT NULL,
  PRIMARY KEY (`NumeroEntrada`),
  KEY `IdProveedor` (`IdProveedor`),
  KEY `IdPersonal` (`IdPersonal`),
  CONSTRAINT `cabeceraentrada_ibfk_1` FOREIGN KEY (`IdProveedor`) REFERENCES `proveedor` (`IdProveedor`),
  CONSTRAINT `cabeceraentrada_ibfk_2` FOREIGN KEY (`IdPersonal`) REFERENCES `personalinventario` (`IdPersonal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabeceraentrada`
--

LOCK TABLES `cabeceraentrada` WRITE;
/*!40000 ALTER TABLE `cabeceraentrada` DISABLE KEYS */;
INSERT INTO `cabeceraentrada` VALUES ('E001 - 00001','13123','123456','COMPRA','2023-03-13',65.6,14.4,80),('E001 - 00002','13123','123456','COMPRA','2023-03-13',90.2,19.8,110);
/*!40000 ALTER TABLE `cabeceraentrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cabecerasalida`
--

DROP TABLE IF EXISTS `cabecerasalida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cabecerasalida` (
  `NumeroSalida` varchar(15) NOT NULL,
  `OrdenDeVenta` varchar(15) NOT NULL,
  `IdPersonal` varchar(15) NOT NULL,
  `FechaEmision` date DEFAULT NULL,
  `ValorTotal` double DEFAULT NULL,
  `Igv` double DEFAULT NULL,
  `PrecioTotal` double DEFAULT NULL,
  PRIMARY KEY (`NumeroSalida`),
  KEY `OrdenDeVenta` (`OrdenDeVenta`),
  KEY `IdPersonal` (`IdPersonal`),
  CONSTRAINT `cabecerasalida_ibfk_1` FOREIGN KEY (`OrdenDeVenta`) REFERENCES `cabeceradeventas` (`OrdenDeVenta`),
  CONSTRAINT `cabecerasalida_ibfk_2` FOREIGN KEY (`IdPersonal`) REFERENCES `personalinventario` (`IdPersonal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cabecerasalida`
--

LOCK TABLES `cabecerasalida` WRITE;
/*!40000 ALTER TABLE `cabecerasalida` DISABLE KEYS */;
INSERT INTO `cabecerasalida` VALUES ('S001 - 00001','001 - 00001','123456','2023-03-13',82,18,100),('S001 - 00002','001 - 00001','123456','2023-03-13',65.6,14.4,80),('S001 - 00003','001 - 00001','123456','2023-03-13',32.8,7.2,40),('S001 - 00004','001 - 00001','123456','2023-03-13',16.4,3.6,20),('S001 - 00005','001 - 00001','123456','2023-03-13',8.2,1.8,10),('S001 - 00006','001 - 00003','das','2023-04-06',8.2,1.8,10);
/*!40000 ALTER TABLE `cabecerasalida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `DniRuc` varchar(15) NOT NULL,
  `Nombres` varchar(30) DEFAULT NULL,
  `Apellidos` varchar(30) DEFAULT NULL,
  `Celular` int DEFAULT NULL,
  PRIMARY KEY (`DniRuc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES ('77819452','Sebastian','Pinto Montes',942312258);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comision`
--

DROP TABLE IF EXISTS `comision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comision` (
  `IdComision` int NOT NULL AUTO_INCREMENT,
  `OrdenDeVenta` varchar(15) DEFAULT NULL,
  `ComisionVendedor` double DEFAULT NULL,
  `ComisionAsesor` double DEFAULT NULL,
  PRIMARY KEY (`IdComision`),
  KEY `OrdenDeVenta` (`OrdenDeVenta`),
  CONSTRAINT `comision_ibfk_1` FOREIGN KEY (`OrdenDeVenta`) REFERENCES `cabeceradeventas` (`OrdenDeVenta`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comision`
--

LOCK TABLES `comision` WRITE;
/*!40000 ALTER TABLE `comision` DISABLE KEYS */;
INSERT INTO `comision` VALUES (1,'001 - 00001',30.26,6.05),(4,'001 - 00002',87.08,17.42),(6,'001 - 00003',87.08,0),(8,'001 - 00004',0,31.68),(9,'001 - 00005',0,6.05);
/*!40000 ALTER TABLE `comision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleentrada`
--

DROP TABLE IF EXISTS `detalleentrada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleentrada` (
  `NumeroEntrada` varchar(15) NOT NULL,
  `CodigoInsumo` varchar(15) NOT NULL,
  `Descripcion` varchar(20) DEFAULT NULL,
  `Unidad` varchar(20) DEFAULT NULL,
  `Cantidad` int DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  `ValorTotal` double DEFAULT NULL,
  PRIMARY KEY (`NumeroEntrada`,`CodigoInsumo`),
  KEY `detalleentrada_ibfk_2` (`CodigoInsumo`),
  CONSTRAINT `detalleentrada_ibfk_1` FOREIGN KEY (`NumeroEntrada`) REFERENCES `cabeceraentrada` (`NumeroEntrada`),
  CONSTRAINT `detalleentrada_ibfk_2` FOREIGN KEY (`CodigoInsumo`) REFERENCES `insumos` (`CodigoInsumo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleentrada`
--

LOCK TABLES `detalleentrada` WRITE;
/*!40000 ALTER TABLE `detalleentrada` DISABLE KEYS */;
INSERT INTO `detalleentrada` VALUES ('E001 - 00001','132','addas','adsasd',4,10,40),('E001 - 00001','dasdsa','dasads','ga',4,10,40),('E001 - 00002','132','addas','adsasd',5,10,50),('E001 - 00002','dasdsa','dasads','ga',6,10,60);
/*!40000 ALTER TABLE `detalleentrada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detallesalidas`
--

DROP TABLE IF EXISTS `detallesalidas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detallesalidas` (
  `NumeroSalida` varchar(15) NOT NULL,
  `CodigoInsumo` varchar(15) NOT NULL,
  `Descripcion` varchar(20) DEFAULT NULL,
  `Unidad` varchar(20) DEFAULT NULL,
  `Cantidad` int DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  `ValorTotal` double DEFAULT NULL,
  PRIMARY KEY (`NumeroSalida`,`CodigoInsumo`),
  KEY `CodigoInsumo` (`CodigoInsumo`),
  CONSTRAINT `detallesalidas_ibfk_1` FOREIGN KEY (`NumeroSalida`) REFERENCES `cabecerasalida` (`NumeroSalida`),
  CONSTRAINT `detallesalidas_ibfk_2` FOREIGN KEY (`CodigoInsumo`) REFERENCES `insumos` (`CodigoInsumo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detallesalidas`
--

LOCK TABLES `detallesalidas` WRITE;
/*!40000 ALTER TABLE `detallesalidas` DISABLE KEYS */;
INSERT INTO `detallesalidas` VALUES ('S001 - 00001','132','addas','adsasd',5,10,50),('S001 - 00001','dasdsa','dasads','ga',5,10,50),('S001 - 00002','132','addas','adsasd',6,10,60),('S001 - 00002','dasdsa','dasads','ga',2,10,20),('S001 - 00003','dasdsa','dasads','ga',4,10,40),('S001 - 00004','dasdsa','dasads','ga',2,10,20),('S001 - 00005','dasdsa','dasads','ga',1,10,10),('S001 - 00006','dasdsa','dasads','ga',1,10,10);
/*!40000 ALTER TABLE `detallesalidas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalleventas`
--

DROP TABLE IF EXISTS `detalleventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalleventas` (
  `OrdenDeVenta` varchar(15) NOT NULL,
  `Codigo` varchar(10) NOT NULL,
  `Descripcion` varchar(20) DEFAULT NULL,
  `Cantidad` int DEFAULT NULL,
  `Abono` double DEFAULT NULL,
  `AbonoP` varchar(6) DEFAULT NULL,
  `PorCobrar` double DEFAULT NULL,
  `PrecioUnitario` double DEFAULT NULL,
  `PrecioTotal` double DEFAULT NULL,
  PRIMARY KEY (`OrdenDeVenta`,`Codigo`),
  KEY `Codigo` (`Codigo`),
  CONSTRAINT `detalleventas_ibfk_1` FOREIGN KEY (`OrdenDeVenta`) REFERENCES `cabeceradeventas` (`OrdenDeVenta`),
  CONSTRAINT `detalleventas_ibfk_2` FOREIGN KEY (`Codigo`) REFERENCES `productos` (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalleventas`
--

LOCK TABLES `detalleventas` WRITE;
/*!40000 ALTER TABLE `detalleventas` DISABLE KEYS */;
INSERT INTO `detalleventas` VALUES ('001 - 00001','123','SADDAS',3,123,'33.33%',246,123,369),('001 - 00002','11','das',3,123,'17.75%',570,231,693),('001 - 00002','123','SADDAS',3,123,'33.33%',246,123,369),('001 - 00003','11','das',3,24,'3.46%',669,231,693),('001 - 00003','123','SADDAS',3,123,'33.33%',246,123,369),('001 - 00004','11','das',4,123,'13.31%',801,231,924),('001 - 00004','123','SADDAS',3,123,'33.33%',246,123,369),('001 - 00004','1234','dsaasd',3,123,'19.25%',516,213,639),('001 - 00005','123','SADDAS',3,123,'33.33%',246,123,369);
/*!40000 ALTER TABLE `detalleventas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empresa` (
  `Ruc` varchar(15) NOT NULL,
  `RazonSocial` varchar(120) DEFAULT NULL,
  `Direccion` varchar(120) DEFAULT NULL,
  `Telefono` int DEFAULT NULL,
  PRIMARY KEY (`Ruc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empresa`
--

LOCK TABLES `empresa` WRITE;
/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` VALUES ('20607107131','INDUSTRIAS MEDGAR SAC','Mza. C Lote. 4-B3 asc. Las Vegas Lima - Lima - Puente Piedra',976780793);
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `insumos`
--

DROP TABLE IF EXISTS `insumos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `insumos` (
  `CodigoInsumo` varchar(15) NOT NULL,
  `Costo` double DEFAULT NULL,
  `Idproveedor` varchar(15) DEFAULT NULL,
  `Descripcion` varchar(20) DEFAULT NULL,
  `Tipo` varchar(20) DEFAULT NULL,
  `Area` varchar(20) DEFAULT NULL,
  `Unidad` varchar(20) DEFAULT NULL,
  `Ingresos` int DEFAULT NULL,
  `Salidas` int DEFAULT NULL,
  `Stock` int DEFAULT NULL,
  PRIMARY KEY (`CodigoInsumo`),
  KEY `Idproveedor` (`Idproveedor`),
  CONSTRAINT `insumos_ibfk_1` FOREIGN KEY (`Idproveedor`) REFERENCES `proveedor` (`IdProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `insumos`
--

LOCK TABLES `insumos` WRITE;
/*!40000 ALTER TABLE `insumos` DISABLE KEYS */;
INSERT INTO `insumos` VALUES ('132',10,'13123','addas','ads','adsdas','adsasd',9,11,-2),('dasdsa',10,'13123','dasads','adsasd','daads','ga',24,24,0);
/*!40000 ALTER TABLE `insumos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personalinventario`
--

DROP TABLE IF EXISTS `personalinventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personalinventario` (
  `IdPersonal` varchar(15) NOT NULL,
  `Nombres` varchar(30) DEFAULT NULL,
  `Apellidos` varchar(30) DEFAULT NULL,
  `Dni` int DEFAULT NULL,
  `Celular` int DEFAULT NULL,
  `Posicion` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`IdPersonal`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personalinventario`
--

LOCK TABLES `personalinventario` WRITE;
/*!40000 ALTER TABLE `personalinventario` DISABLE KEYS */;
INSERT INTO `personalinventario` VALUES ('123456','Sebastian Alonso','Pinto Montes',77819452,942312258,'SUPERVISOR'),('das','dasads','asddas',32332,312123,'GESTOR LOGICO');
/*!40000 ALTER TABLE `personalinventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `Codigo` varchar(10) NOT NULL,
  `PrecioUnitario` double DEFAULT NULL,
  `Valor` double DEFAULT NULL,
  `Capacidad` varchar(10) DEFAULT NULL,
  `Tipo` varchar(10) DEFAULT NULL,
  `Descripcion` varchar(20) DEFAULT NULL,
  `Largo` varchar(15) DEFAULT NULL,
  `Ancho` varchar(15) DEFAULT NULL,
  `Alto` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`Codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES ('11',231,213,'asd','das','das','32112dsa','32123','asd'),('123',123,213,'2TN','GA','SADDAS','20MTS','30MTS','50MTS'),('1234',213,23,'2tn','sadsad','dsaasd','213mts','213mts','123mts');
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `IdProveedor` varchar(15) NOT NULL,
  `NombreEmpresa` varchar(50) DEFAULT NULL,
  `Direccion` varchar(200) DEFAULT NULL,
  `Celular` int DEFAULT NULL,
  PRIMARY KEY (`IdProveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES ('13123','asddas','ads',123213);
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vendedores`
--

DROP TABLE IF EXISTS `vendedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vendedores` (
  `IdVendedor` varchar(10) NOT NULL,
  `Nombres` varchar(30) DEFAULT NULL,
  `Apellidos` varchar(30) DEFAULT NULL,
  `Celular` int DEFAULT NULL,
  `Posicion` varchar(20) DEFAULT NULL,
  `Dni` int DEFAULT NULL,
  PRIMARY KEY (`IdVendedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vendedores`
--

LOCK TABLES `vendedores` WRITE;
/*!40000 ALTER TABLE `vendedores` DISABLE KEYS */;
INSERT INTO `vendedores` VALUES ('u19203340','Angel','Garcia',942312258,'JEFE DE AREA',77819452),('u2023','Sebastian Alonso','Pinto Montes',5250344,'VENDEDOR',942312258);
/*!40000 ALTER TABLE `vendedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'medgarperu'
--

--
-- Dumping routines for database 'medgarperu'
--
/*!50003 DROP PROCEDURE IF EXISTS `agregarEntradas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregarEntradas`(
in entradas int,
in id varchar(15))
BEGIN
	UPDATE Insumos set Ingresos=Ingresos+entradas, Stock=Stock+entradas where CodigoInsumo = id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `agregarSalidas` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `agregarSalidas`(
in nsalidas int,
in id varchar(15))
BEGIN
	UPDATE Insumos set Salidas=Salidas+nsalidas, Stock=Stock-nsalidas where CodigoInsumo = id;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-11 23:46:14
