CREATE TABLE `pool_ref`.`security_question` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `question` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC));

ALTER TABLE `pool_ref`.`user`
ADD COLUMN `security_question_id` INT(11) NULL AFTER `hashedpassword`,
ADD COLUMN `security_question_answer` VARCHAR(80) NULL AFTER `security_question_id`;

ALTER TABLE `pool_ref`.`user`
CHANGE COLUMN `hashedpassword` `hashed_password` VARCHAR(80) NOT NULL ;

INSERT INTO `pool_ref`.`security_question` (id, question)
VALUES ('1', "What is your mother's maiden name?");

INSERT INTO `pool_ref`.`security_question` (id, question)
VALUES ('2', "In what city were you born?");

INSERT INTO `pool_ref`.`security_question` (id, question)
VALUES ('3', "What secondary school did you attend?");

INSERT INTO `pool_ref`.`security_question` (id, question)
VALUES ('4', "What street did you grow up on?");

INSERT INTO `pool_ref`.`security_question` (id, question)
VALUES ('5', "What was the make of your first car?");

INSERT INTO `pool_ref`.`security_question` (id, question)
VALUES ('6', "Which is your favorite web browser?");

INSERT INTO `pool_ref`.`security_question` (id, question)
VALUES ('7', "What is the name of your favorite pet?");