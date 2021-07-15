create database harbitechtest;
use harbitechtest;
SELECT * FROM Category;
SELECT * FROM Subcategory;
SELECT * FROM Course;


CREATE TABLE Category(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
description TEXT,
study_guide TEXT,
status ENUM('INACTIVE','ACTIVE') DEFAULT 'INACTIVE',
order_visualization INT,
icon_path VARCHAR(400),
html_hex_color_code VARCHAR(7));

CREATE TABLE Subcategory(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
description TEXT,
study_guide TEXT,
status ENUM('INACTIVE','ACTIVE') DEFAULT 'INACTIVE',
order_visualization INT,
category_id BIGINT NOT NULL,
FOREIGN KEY(category_id) REFERENCES Category(id));

CREATE TABLE Course(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
completion_time_in_hours INT NOT NULL,
visibility ENUM('PUBLIC','PRIVATE') DEFAULT 'PRIVATE',
target_audience VARCHAR(250),
instructor VARCHAR(70),
description TEXT,
developed_skills TEXT,
subcategory_id BIGINT NOT NULL,
FOREIGN KEY(subcategory_id) REFERENCES Subcategory(id));