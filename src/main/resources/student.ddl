CREATE SCHEMA `test` ;
CREATE TABLE `test`.`student` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NULL,
  `last_name` VARCHAR(50) NULL,
  `course_id` VARCHAR(25) NULL,
  PRIMARY KEY (`id`));