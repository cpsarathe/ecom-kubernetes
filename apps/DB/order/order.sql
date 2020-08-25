CREATE SCHEMA `cp-kub-order` DEFAULT CHARACTER SET utf8 ;


CREATE TABLE `cp-kub-order`.`order_info` (
  `id` int(11) NOT NULL COMMENT '',
  `customer_id` int(11) NOT NULL COMMENT '',
  `item_id` VARCHAR(64) NULL COMMENT '',
  `item_sale_price` DECIMAL(19,5) NULL COMMENT '',
  `item_quantity` int(11) NULL COMMENT '',
  `total` DECIMAL(19,5) NULL COMMENT '',
  `status` VARCHAR(32) NULL COMMENT '',
  `payment_reference` VARCHAR(64) NULL COMMENT '',
  `payment_type` VARCHAR(32) NULL COMMENT '',
  `created_date` DATETIME NULL COMMENT '',
  `updated_date` DATETIME NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8;