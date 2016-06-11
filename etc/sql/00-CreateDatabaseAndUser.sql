CREATE USER 'restapp'@'localhost' IDENTIFIED BY 'restapp';
DROP DATABASE IF EXISTS restapp;
CREATE DATABASE restapp DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
GRANT ALL PRIVILEGES on restapp.* TO 'restapp'@'localhost';