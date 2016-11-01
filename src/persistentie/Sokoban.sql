-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Sokoban
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Sokoban
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Sokoban` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `Sokoban` ;

-- -----------------------------------------------------
-- Table `Sokoban`.`Speler`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Sokoban`.`Speler` (
  `gebruikersnaam` VARCHAR(30) NOT NULL,
  `wachtwoord` VARCHAR(30) NOT NULL,
  `adminrechten` TINYINT(1) NOT NULL,
  `naam` VARCHAR(30) NULL,
  `voornaam` VARCHAR(30) NULL,
  PRIMARY KEY (`gebruikersnaam`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sokoban`.`Spel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Sokoban`.`Spel` (
  `spelId` INT NOT NULL,
  `naam` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`spelId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sokoban`.`Spelbord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Sokoban`.`Spelbord` (
  `spelBordId` INT NULL,
  `Spel_spelId` INT NOT NULL,
  `volgnummer` INT NOT NULL,
  PRIMARY KEY (`spelBordId`, `Spel_spelId`),
  INDEX `fk_SpelBord_Spel1_idx` (`Spel_spelId` ASC),
  CONSTRAINT `fk_SpelBord_Spel1`
    FOREIGN KEY (`Spel_spelId`)
    REFERENCES `Sokoban`.`Spel` (`spelId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Sokoban`.`Veld`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Sokoban`.`Veld` (
  `Spelbord_Spel_spelId` INT NOT NULL,
  `Spelbord_spelBordId` INT NOT NULL,
  `x` INT NOT NULL,
  `y` INT NOT NULL,
  `soortVeld` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Spelbord_Spel_spelId`, `Spelbord_spelBordId`, `x`, `y`),
  INDEX `fk_Veld_Spelbord1_idx` (`Spelbord_spelBordId` ASC, `Spelbord_Spel_spelId` ASC),
  CONSTRAINT `fk_Veld_Spelbord1`
    FOREIGN KEY (`Spelbord_spelBordId` , `Spelbord_Spel_spelId`)
    REFERENCES `Sokoban`.`Spelbord` (`spelBordId` , `Spel_spelId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
