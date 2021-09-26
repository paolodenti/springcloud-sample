DROP TABLE IF EXISTS product;

CREATE TABLE `product` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `sku` VARCHAR(100) NOT NULL,
    `description` VARCHAR(256) NOT NULL,
    `unit_price` DECIMAL(5, 2) NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO `product` (`sku`, `description`, `unit_price`)
VALUES ('111111111', 'The product # 1', 151.23);

INSERT INTO `product` (`sku`, `description`, `unit_price`)
VALUES ('222222222', 'The product # 2', 152.23);

INSERT INTO `product` (`sku`, `description`, `unit_price`)
VALUES ('333333333', 'The product # 3', 153.23);

INSERT INTO `product` (`sku`, `description`, `unit_price`)
VALUES ('444444444', 'The product # 4', 154.23);

INSERT INTO `product` (`sku`, `description`, `unit_price`)
VALUES ('555555555', 'The product # 5', 155.23);
