CREATE SCHEMA `cp-kub-customer` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `cp-kub-customer`.`address` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `type` VARCHAR(32) NULL COMMENT '',
  `address` VARCHAR(255) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cp-kub-customer`.`customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL COMMENT '',
  `email` VARCHAR(255) NULL COMMENT '',
  `mobile` VARCHAR(21) NULL COMMENT '',
  `date_of_birth` VARCHAR(200) NULL COMMENT '',
  `address_id` int(11) NOT NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '' ,
  CONSTRAINT `fk_customer_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;