CREATE DATABASE IF NOT EXISTS `db_notas`;
USE `db_notas`;

CREATE TABLE `nota` (
  `ID_NOTA` int(11) NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(100) NOT NULL,
  `DESCRICAO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID_NOTA`)
)