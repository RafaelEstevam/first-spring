create schema spring;

use spring;

create user 'spring'@'localhost' identified by 'spring';

grant select, create, delete, update on spring.* to spring@'localhost';