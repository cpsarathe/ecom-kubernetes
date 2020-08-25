CREATE SCHEMA `cp-kub-catalog` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `cp-kub-catalog`.`product` (
  `id` VARCHAR(64) NOT NULL COMMENT '',
  `name` VARCHAR(200) NULL COMMENT '',
  `image_url` VARCHAR(1024) NULL COMMENT '',
  `url` VARCHAR(1024) NULL COMMENT '',
  `retail_price` DECIMAL(19,5) NULL COMMENT '',
  `sale_price` DECIMAL(19,5) NULL COMMENT '',
  `seller_id` VARCHAR(64) NULL COMMENT '',
  `quantity` INT(11) NOT NULL DEFAULT 0 COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '')
  ENGINE=InnoDB DEFAULT CHARSET=utf8;