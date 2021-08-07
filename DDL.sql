create schema spring;

use spring;

create user 'spring'@'localhost' identified by 'spring';

grant select, insert, delete, update on spring.* to spring@'localhost';

CREATE TABLE `spring`.`login` (
    `id` BIGINT NOT NULL AUTO_INCREMENT ,
    `email` VARCHAR(255) NOT NULL ,
    `password` VARCHAR(255) NOT NULL ,
    PRIMARY KEY (`id`),
    UNIQUE `uq_login_email` (`email`)
) ENGINE = InnoDB;

CREATE TABLE `spring`.`auth` (
    `id` BIGINT NOT NULL AUTO_INCREMENT ,
    `token` VARCHAR(1000) NOT NULL ,
    PRIMARY KEY (`id`), UNIQUE `uq_auth_token` (`token`)
) ENGINE = InnoDB;

CREATE TABLE `spring`.`auth_login` (
    `auth_id` BIGINT NOT NULL ,
    `login_id` BIGINT NOT NULL ,
    PRIMARY KEY (`auth_id`, `login_id`),
    FOREIGN KEY auth_login_fk (`login_id`) REFERENCES login(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY auth_auth_fk (`auth_id`) REFERENCES auth(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB;