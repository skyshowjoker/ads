create database if not exists ads;

use ads;

DROP TABLE `patient` ;
CREATE table IF NOT EXISTS `patient`(
   `id` INT UNSIGNED AUTO_INCREMENT,
   `name` VARCHAR(100) NOT NULL,
   `gender` VARCHAR(40) NOT NULL,
   `birthday` DATE,
   `case_id` VARCHAR(100) NOT NULL,
   `phone` VARCHAR(20) NOT NULL,
   `email` VARCHAR(100) NOT NULL,
   `description` VARCHAR(1000) NOT NULL,
   `active_ind` VARCHAR(1) NOT NUll,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `file`;

CREATE TABLE IF NOT EXISTS `file` (
   `id` INT UNSIGNED AUTO_INCREMENT,
   `patient_id` INT NOT NULL,
   `upload_date` DATE,
   `file_path` VARCHAR(200) NOT NULL,
   `emzl_result` VARCHAR(100) NOT NULL,
   `progressive_result` VARCHAR(100) NOT NULL,
   `active_ind` VARCHAR(1) NOT NUll,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
