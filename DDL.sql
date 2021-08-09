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

CREATE TABLE `spring`.`profile` (
    `id` BIGINT NOT NULL AUTO_INCREMENT ,
    `login_id` BIGINT NOT NULL ,
    `name` VARCHAR(255) NOT NULL ,
    `doc` VARCHAR(20) NOT NULL ,
    `gender` VARCHAR(255) NULL ,
    `birthday` DATE NULL ,
    `phone` VARCHAR(25) NULL ,
    `mobile` VARCHAR(25) NULL ,
    `created_at` DATE NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY (`id`),
    UNIQUE `profile_login_id` (`login_id`),
    FOREIGN KEY profile_login_fk (`login_id`) REFERENCES login(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB;

CREATE TABLE `spring`.`category` (
    `id` BIGINT NOT NULL AUTO_INCREMENT ,
    `name` VARCHAR(255) NOT NULL ,
    PRIMARY KEY (`id`),
    UNIQUE `category_name` (`name`)
) ENGINE = InnoDB;

CREATE TABLE `spring`.`task` (
    `id` BIGINT NOT NULL AUTO_INCREMENT ,
    `category_id` BIGINT NULL ,
    `profile_id` BIGINT NULL ,
    `title` VARCHAR(255) NOT NULL ,
    `description` VARCHAR(1000) NULL ,
    `status` VARCHAR(30) NULL ,
    `progress` INT NULL ,
    `deadline` DATE NULL ,
    `created_at` DATE NULL DEFAULT CURRENT_TIMESTAMP ,
    `updated_at` DATE NULL DEFAULT CURRENT_TIMESTAMP ,
    PRIMARY KEY (`id`),
    FOREIGN KEY task_category_fk (`category_id`) REFERENCES category(`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY task_profile_fk (`profile_id`) REFERENCES profile(`id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE = InnoDB;