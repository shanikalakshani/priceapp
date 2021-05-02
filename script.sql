CREATE DATABASE priceapp_db;
USE priceapp_db;


CREATE TABLE `priceapp_db`.`product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(50) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  `carton_size` INT NULL DEFAULT NULL,
  `carton_price` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC));
  
  
INSERT INTO `priceapp_db`.`product` (`id`, `code`, `name`, `carton_size`, `carton_price`) VALUES ('1', 'C001', 'Penguin-ears', '20', '175');
INSERT INTO `priceapp_db`.`product` (`id`, `code`, `name`, `carton_size`, `carton_price`) VALUES ('2', 'C002', 'Horseshoe', '5', '825');