CREATE SCHEMA `cp-kub-payment` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `cp-kub-payment`.`payment` (
  `id` int(11) NOT NULL COMMENT '',
  `order_id` int(11) NOT NULL COMMENT '',
  `customer_id` int(11) NOT NULL COMMENT '',
  `total` DECIMAL(19,5) NULL COMMENT '',
  `status` VARCHAR(32) NULL COMMENT '',
  `payment_type` VARCHAR(32) NULL COMMENT '',
  `transaction_reference` VARCHAR(64) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;