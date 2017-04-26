CREATE TABLE `pool_ref`.`twitter_token` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `twitter_name` VARCHAR(45) NOT NULL,
  `token` VARCHAR(80) NOT NULL,
  `token_secret` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8;

ALTER TABLE `pool_ref`.`user`
ADD COLUMN `twitter_token_id` INT(11) NULL AFTER `security_question_answer`
