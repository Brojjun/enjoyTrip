-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`sido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`sido` (
  `sido_code` INT NOT NULL,
  `sido_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_img` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`gugun`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`gugun` (
  `gugun_code` INT NOT NULL,
  `gugun_name` VARCHAR(30) NULL DEFAULT NULL,
  `sido_code` INT NOT NULL,
  PRIMARY KEY (`gugun_code`, `sido_code`),
  INDEX `gugun_to_sido_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  CONSTRAINT `gugun_to_sido_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_info` (
  `content_id` INT NOT NULL,
  `content_type_id` INT NULL DEFAULT NULL,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `addr1` VARCHAR(100) NULL DEFAULT NULL,
  `addr2` VARCHAR(50) NULL DEFAULT NULL,
  `zipcode` VARCHAR(50) NULL DEFAULT NULL,
  `tel` VARCHAR(50) NULL DEFAULT NULL,
  `first_image` VARCHAR(200) NULL DEFAULT NULL,
  `first_image2` VARCHAR(200) NULL DEFAULT NULL,
  `readcount` INT NULL DEFAULT NULL,
  `sido_code` INT NULL DEFAULT NULL,
  `gugun_code` INT NULL DEFAULT NULL,
  `latitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `longitude` DECIMAL(20,17) NULL DEFAULT NULL,
  `mlevel` VARCHAR(2) NULL DEFAULT NULL,
  `hits` INT NULL DEFAULT '0',
  `uses` INT NULL DEFAULT '0',
  PRIMARY KEY (`content_id`),
  INDEX `attraction_to_content_type_id_fk_idx` (`content_type_id` ASC) VISIBLE,
  INDEX `attraction_to_sido_code_fk_idx` (`sido_code` ASC) VISIBLE,
  INDEX `attraction_to_gugun_code_fk_idx` (`gugun_code` ASC) VISIBLE,
  CONSTRAINT `attraction_to_content_type_id_fk`
    FOREIGN KEY (`content_type_id`)
    REFERENCES `enjoytrip`.`content_type` (`content_type_id`),
  CONSTRAINT `attraction_to_gugun_code_fk`
    FOREIGN KEY (`gugun_code`)
    REFERENCES `enjoytrip`.`gugun` (`gugun_code`),
  CONSTRAINT `attraction_to_sido_code_fk`
    FOREIGN KEY (`sido_code`)
    REFERENCES `enjoytrip`.`sido` (`sido_code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_description`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_description` (
  `content_id` INT NOT NULL,
  `homepage` VARCHAR(100) NULL DEFAULT NULL,
  `overview` VARCHAR(10000) NULL DEFAULT NULL,
  `telname` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_attraciton_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`attraction_detail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`attraction_detail` (
  `content_id` INT NOT NULL,
  `cat1` VARCHAR(3) NULL DEFAULT NULL,
  `cat2` VARCHAR(5) NULL DEFAULT NULL,
  `cat3` VARCHAR(9) NULL DEFAULT NULL,
  `created_time` VARCHAR(14) NULL DEFAULT NULL,
  `modified_time` VARCHAR(14) NULL DEFAULT NULL,
  `booktour` VARCHAR(5) NULL DEFAULT NULL,
  PRIMARY KEY (`content_id`),
  CONSTRAINT `attraction_detail_to_basic_content_id_fk`
    FOREIGN KEY (`content_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`user` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email0` VARCHAR(45) NULL DEFAULT NULL,
  `email1` VARCHAR(45) NULL DEFAULT NULL,
  `sido` INT NULL DEFAULT NULL,
  `gugun` INT NULL DEFAULT NULL,
  `birth_year` INT NULL DEFAULT NULL,
  `birth_month` VARCHAR(2) NULL DEFAULT NULL,
  `birth_date` VARCHAR(2) NULL DEFAULT NULL,
  `regist_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` TIMESTAMP NULL DEFAULT NULL,
  `remove_date` TIMESTAMP NULL DEFAULT NULL,
  `is_admin` TINYINT NULL DEFAULT '0',
  `refresh_token` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`no`, `name`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`favorite`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`favorite` (
  `user_no` INT NOT NULL,
  `attraction_id` INT NOT NULL,
  INDEX `favorite_user_no_to_user_fk_idx` (`user_no` ASC) VISIBLE,
  INDEX `favorite_attraction_id_to_attraction_info_fk_idx` (`attraction_id` ASC) VISIBLE,
  CONSTRAINT `favorite_attraction_id_to_attraction_info_fk`
    FOREIGN KEY (`attraction_id`)
    REFERENCES `enjoytrip`.`attraction_info` (`content_id`)
    ON DELETE CASCADE,
  CONSTRAINT `favorite_user_no_to_user_fk`
    FOREIGN KEY (`user_no`)
    REFERENCES `enjoytrip`.`user` (`no`)
    ON DELETE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`file_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`file_info` (
  `file_no` INT NOT NULL AUTO_INCREMENT,
  `saveFolder` VARCHAR(100) NULL DEFAULT NULL,
  `originalFile` VARCHAR(100) NULL DEFAULT NULL,
  `saveFile` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`file_no`))
ENGINE = InnoDB
AUTO_INCREMENT = 54
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`my_route`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`my_route` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `member_no` INT NULL DEFAULT NULL,
  `title` VARCHAR(45) NULL DEFAULT NULL,
  `scrap_cnt` INT NULL DEFAULT '0',
  `hits` INT NULL DEFAULT '0',
  `regist_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `contents` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`no`),
  INDEX `my_route_member_no_to_member_fk_idx` (`member_no` ASC) VISIBLE,
  CONSTRAINT `my_route_member_no_to_member_fk`
    FOREIGN KEY (`member_no`)
    REFERENCES `enjoytrip`.`user` (`no`)
    ON DELETE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`notice` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `user_no` INT NULL DEFAULT NULL,
  `title` VARCHAR(200) NULL DEFAULT NULL,
  `contents` BLOB NULL DEFAULT NULL,
  `file_no` VARCHAR(100) NULL DEFAULT NULL,
  `regist_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` TIMESTAMP NULL DEFAULT NULL,
  `remove_date` TIMESTAMP NULL DEFAULT NULL,
  `hits` INT NULL DEFAULT '0',
  PRIMARY KEY (`no`),
  INDEX `fk_notice_file_info` (`file_no` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 155
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`notice_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`notice_comment` (
  `comment_no` INT NOT NULL AUTO_INCREMENT,
  `user_no` INT NOT NULL,
  `notice_no` INT NOT NULL,
  `contents` VARCHAR(500) NOT NULL,
  `recomment` INT NULL DEFAULT NULL,
  `regist_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `delete_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`comment_no`),
  INDEX `user_no` (`user_no` ASC) VISIBLE,
  INDEX `notice_no` (`notice_no` ASC) VISIBLE,
  CONSTRAINT `notice_comment_ibfk_1`
    FOREIGN KEY (`user_no`)
    REFERENCES `enjoytrip`.`user` (`no`),
  CONSTRAINT `notice_comment_ibfk_2`
    FOREIGN KEY (`notice_no`)
    REFERENCES `enjoytrip`.`notice` (`no`))
ENGINE = InnoDB
AUTO_INCREMENT = 69
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`notice_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`notice_like` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `user_no` INT NOT NULL,
  `notice_no` INT NOT NULL,
  PRIMARY KEY (`no`),
  INDEX `user_no` (`user_no` ASC) VISIBLE,
  INDEX `notice_no` (`notice_no` ASC) VISIBLE,
  CONSTRAINT `notice_like_ibfk_1`
    FOREIGN KEY (`user_no`)
    REFERENCES `enjoytrip`.`user` (`no`),
  CONSTRAINT `notice_like_ibfk_2`
    FOREIGN KEY (`notice_no`)
    REFERENCES `enjoytrip`.`notice` (`no`))
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`route_orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`route_orders` (
  `route_no` INT NULL DEFAULT NULL,
  `attraction_id` INT NULL DEFAULT NULL,
  `order_num` INT NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`share_attraction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`share_attraction` (
  `share_no` INT NULL DEFAULT NULL,
  `attraction_id` INT NULL DEFAULT NULL,
  `register_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `remove_date` TIMESTAMP NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `enjoytrip`.`share_board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `enjoytrip`.`share_board` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `hits` INT NULL DEFAULT NULL,
  `title` VARCHAR(30) NULL DEFAULT NULL,
  `contents` BLOB NULL DEFAULT NULL,
  `user_no` INT NULL DEFAULT NULL,
  `register_time` TIMESTAMP NULL DEFAULT NULL,
  `update_date` TIMESTAMP NULL DEFAULT NULL,
  `remove_date` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
