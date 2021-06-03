-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Shop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Shop` DEFAULT CHARACTER SET utf8 ;
USE `Shop` ;

-- -----------------------------------------------------
-- Table `Shop`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Shop`.`product` (
  `maker` VARCHAR(10) NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`model`),
  UNIQUE INDEX `model_UNIQUE` (`model` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Shop`.`laptop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Shop`.`laptop` (
  `code` INT NOT NULL,
  `speed` SMALLINT NOT NULL,
  `ram` SMALLINT NOT NULL,
  `hd` REAL NOT NULL,
  `price` DECIMAL(15,2) NULL,
  `screen` TINYINT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_laptop_product1_idx` (`model` ASC) VISIBLE,
  CONSTRAINT `fk_laptop_product1`
    FOREIGN KEY (`model`)
    REFERENCES `Shop`.`product` (`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Shop`.`pc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Shop`.`pc` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `speed` SMALLINT NOT NULL,
  `ram` SMALLINT NOT NULL,
  `hd` REAL NOT NULL,
  `cd` VARCHAR(10) NOT NULL,
  `price` DECIMAL(15,2) NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_pc_product_idx` (`model` ASC) VISIBLE,
  CONSTRAINT `fk_pc_product`
    FOREIGN KEY (`model`)
    REFERENCES `Shop`.`product` (`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Shop`.`printer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Shop`.`printer` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `color` CHAR(1) NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `price` DECIMAL(15,2) NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_printer_product1_idx` (`model` ASC) VISIBLE,
  CONSTRAINT `fk_printer_product1`
    FOREIGN KEY (`model`)
    REFERENCES `Shop`.`product` (`model`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
