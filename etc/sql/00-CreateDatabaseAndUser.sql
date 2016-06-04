CREATE USER 'restapp'@'localhost' IDENTIFIED BY 'restapp';
CREATE DATABASE restapp;
GRANT ALL PRIVILEGES on restapp.* TO 'restapp'@'localhost';