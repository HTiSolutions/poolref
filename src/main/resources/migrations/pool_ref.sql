-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema pool_ref
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `pool_ref` DEFAULT CHARACTER SET utf8 ;
USE `pool_ref` ;

-- -----------------------------------------------------
-- Table `pool_ref`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pool_ref`.`user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `forename` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `nickname` VARCHAR(45) NOT NULL,
  `hashedpassword` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `pool_ref`.`game`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `pool_ref`.`game` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `winner_id` INT(11) NOT NULL,
  `loser_id` INT(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `game.winner_id user.id_idx` (`winner_id` ASC),
  INDEX `game.loser_id user.id_idx` (`loser_id` ASC),
  CONSTRAINT `game.loser_id user.id`
    FOREIGN KEY (`loser_id`)
    REFERENCES `pool_ref`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `game.winner_id user.id`
    FOREIGN KEY (`winner_id`)
    REFERENCES `pool_ref`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
