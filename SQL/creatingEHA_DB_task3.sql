-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema EHA
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema EHA
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `EHA` DEFAULT CHARACTER SET utf8 ;
USE `EHA` ;

-- -----------------------------------------------------
-- Table `EHA`.`hotel_residents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EHA`.`hotel_residents` (
  `full_name` VARCHAR(45) NOT NULL,
  `passport_number` INT NOT NULL,
  PRIMARY KEY (`full_name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EHA`.`hotel_rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EHA`.`hotel_rooms` (
  `number_of_room` INT NOT NULL,
  `number_of_stars` INT NOT NULL,
  `room_capacity` INT NOT NULL,
  `price` DECIMAL(15,2) NOT NULL,
  `room_condition` VARCHAR(45) NULL,
  `room_is_occupied` BIT NULL,
  PRIMARY KEY (`number_of_room`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EHA`.`registration_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EHA`.`registration_cards` (
  `hotel_room` INT NOT NULL,
  `residents` VARCHAR(45) NOT NULL,
  `services` VARCHAR(45) NULL,
  `check_in_date` DATE NOT NULL,
  `departure_date` DATE NOT NULL,
  PRIMARY KEY (`hotel_room`),
  CONSTRAINT `fk_hotel_rooms_registration_cards1`
    FOREIGN KEY (`hotel_room`)
    REFERENCES `EHA`.`hotel_rooms` (`number_of_room`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EHA`.`services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EHA`.`services` (
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(15,2) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EHA`.`last_residents_rooms`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EHA`.`last_residents_rooms` (
  `number_of_room` INT NOT NULL,
  `full_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`number_of_room`, `full_name`),
  INDEX `fk_residents_rooms_hotel_rooms_idx` (`number_of_room` ASC) VISIBLE,
  INDEX `fk_residents_rooms_hotel_residents1_idx` (`full_name` ASC) VISIBLE,
  CONSTRAINT `fk_residents_rooms_hotel_rooms`
    FOREIGN KEY (`number_of_room`)
    REFERENCES `EHA`.`hotel_rooms` (`number_of_room`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_residents_rooms_hotel_residents1`
    FOREIGN KEY (`full_name`)
    REFERENCES `EHA`.`hotel_residents` (`full_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EHA`.`residents_cards`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EHA`.`residents_cards` (
  `hotel_room` INT NOT NULL,
  `full_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`hotel_room`, `full_name`),
  INDEX `fk_residents_cards_hotel_residents1_idx` (`full_name` ASC) VISIBLE,
  CONSTRAINT `fk_residents_cards_registration_cards1`
    FOREIGN KEY (`hotel_room`)
    REFERENCES `EHA`.`registration_cards` (`hotel_room`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_residents_cards_hotel_residents1`
    FOREIGN KEY (`full_name`)
    REFERENCES `EHA`.`hotel_residents` (`full_name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `EHA`.`cards_services`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `EHA`.`cards_services` (
  `name` VARCHAR(45) NOT NULL,
  `hotel_room` INT NOT NULL,
  PRIMARY KEY (`name`, `hotel_room`),
  INDEX `fk_cards_services_registration_cards1_idx` (`hotel_room` ASC) VISIBLE,
  CONSTRAINT `fk_cards_services_services1`
    FOREIGN KEY (`name`)
    REFERENCES `EHA`.`services` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cards_services_registration_cards1`
    FOREIGN KEY (`hotel_room`)
    REFERENCES `EHA`.`registration_cards` (`hotel_room`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
