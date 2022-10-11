-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema pointer
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema pointer
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pointer` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema dokb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dokb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dokb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `pointer` ;

-- -----------------------------------------------------
-- Table `pointer`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pointer`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `account` VARCHAR(50) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `password` VARCHAR(50) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `updated_at` DATETIME NULL DEFAULT NULL,
  `updated_by` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 26
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `pointer`.`user_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pointer`.`user_data` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `eye_pos` FLOAT NOT NULL,
  `eye_blink` FLOAT NOT NULL,
  `nod_rl` INT NOT NULL,
  `nod_fr` INT NOT NULL,
  `stt` TEXT CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `created_at` DATETIME NOT NULL,
  `created_by` VARCHAR(20) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_0900_ai_ci' NOT NULL,
  `user_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

USE `dokb` ;

-- -----------------------------------------------------
-- Table `dokb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dokb`.`user` (
  `register_number` INT NOT NULL,
  `name` VARCHAR(10) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `job` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`register_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dokb`.`account`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dokb`.`account` (
  `password` VARCHAR(100) NOT NULL,
  `account_number` VARCHAR(100) NOT NULL,
  `purpose` VARCHAR(45) NOT NULL,
  `sof` VARCHAR(45) NOT NULL DEFAULT 'source of funds',
  `balance` INT NOT NULL,
  `user_register_number` INT NOT NULL,
  PRIMARY KEY (`account_number`),
  INDEX `fk_account_user_idx1` (`user_register_number` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dokb`.`history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dokb`.`history` (
  `id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `opponent` VARCHAR(100) NOT NULL,
  `amount` VARCHAR(45) NOT NULL,
  `inout` CHAR(1) NOT NULL,
  `balance` VARCHAR(45) NULL DEFAULT NULL,
  `account_account_number1` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_history_account1_idx1` (`account_account_number1` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
