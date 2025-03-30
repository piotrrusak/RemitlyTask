DROP DATABASE IF EXISTS swiftdb;
CREATE DATABASE swiftdb;
USE swiftdb;

CREATE TABLE BankName (
	id INT AUTO_INCREMENT PRIMARY KEY,
    bank_name VARCHAR(100)
);

CREATE TABLE Country (
	id INT AUTO_INCREMENT PRIMARY KEY,
    country_iso2 VARCHAR(100),
    country_name VARCHAR(100)
);

CREATE TABLE Address (
	id INT AUTO_INCREMENT PRIMARY KEY,
    address VARCHAR(100),
    town_name VARCHAR(100),
    time_zone VARCHAR(100)
);

CREATE TABLE CodeType(
	id INT AUTO_INCREMENT PRIMARY KEY,
    code_type VARCHAR(100)
);

CREATE TABLE Bank (
	id INT AUTO_INCREMENT PRIMARY KEY,
    address_id INT NOT NULL,
    bank_name_id INT NOT NULL,
    country_id INT NOT NULL,
    is_headquarter BOOL NOT NULL,
    headquarter INT,
    swift_code VARCHAR(100),
    swift_code_type_id INT,
    CONSTRAINT fk_bank_name
		FOREIGN KEY (bank_name_id) REFERENCES BankName(id),
	CONSTRAINT fk_country
		FOREIGN KEY (country_id) REFERENCES Country(id),
	CONSTRAINT fk_address
		FOREIGN KEY (address_id) REFERENCES Address(id),
	CONSTRAINT fk_code_type
		FOREIGN KEY (swift_code_type_id) REFERENCES CodeType(id),
	CONSTRAINT fk_headquarters
		FOREIGN KEY (headquarter) REFERENCES Bank(id)
);
