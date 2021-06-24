CREATE DATABASE harbitech;
USE harbitech;

CREATE TABLE Category(
id BIGINT UNIQUE PRIMARY KEY AUTO_INCREMENT NOT NULL,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
description TEXT,
study_guide TEXT,
status ENUM('inactive', 'active') DEFAULT 'inactive',
order_visualization INT,
icon_path VARCHAR(400),
html_hex_color_code VARCHAR(7));

CREATE TABLE Subcategory(
id BIGINT UNIQUE PRIMARY KEY AUTO_INCREMENT NOT NULL,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
description TEXT,
study_guide TEXT,
status ENUM('inactive', 'active') DEFAULT 'inactive',
order_visualization INT,
category_id BIGINT NOT NULL,
FOREIGN KEY(category_id) REFERENCES Category(id));

CREATE TABLE Course(
id BIGINT UNIQUE PRIMARY KEY AUTO_INCREMENT NOT NULL,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
completion_time_in_hours INT NOT NULL,
visibility ENUM('public', 'private') DEFAULT 'private',
target_audience VARCHAR(250),
instructor VARCHAR(70),
description TEXT,
developed_skills TEXT,
subcategory_id BIGINT NOT NULL,
FOREIGN KEY(subcategory_id) REFERENCES Subcategory(id));

CREATE TABLE Section(
id BIGINT UNIQUE PRIMARY KEY AUTO_INCREMENT NOT NULL,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
order_visualization INT,
status ENUM('inactive', 'active') DEFAULT 'inactive',
type ENUM('lecture', 'exam') DEFAULT 'lecture',
course_id BIGINT NOT NULL,
FOREIGN KEY(course_id) REFERENCES Course(id));

CREATE TABLE Activity(
id BIGINT UNIQUE PRIMARY KEY AUTO_INCREMENT NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
title VARCHAR(150) NOT NULL,
text TEXT,
status ENUM('inactive', 'active') DEFAULT 'inactive',
subtype ENUM('video','question','explanation'),
section_id BIGINT NOT NULL,
FOREIGN KEY(section_id) REFERENCES Section(id));

CREATE TABLE Video(
id BIGINT UNIQUE PRIMARY KEY AUTO_INCREMENT NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
time_in_minutes INT,
transcription TEXT,
activity_id BIGINT NOT NULL,
FOREIGN KEY(activity_id) REFERENCES Activity(id));
 
CREATE TABLE Question(
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
text TEXT,
answer_type ENUM('single_choice','multiple_choice','true_or_false'),
activity_id BIGINT NOT NULL,
FOREIGN KEY(activity_id) REFERENCES Activity(id));

CREATE TABLE Alternative(
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
text VARCHAR(900) NOT NULL,
order_visualization INT,
correct BIT(1),
justification VARCHAR(500),
question_id BIGINT NOT NULL,
FOREIGN KEY(question_id) REFERENCES Question(id));

SELECT * FROM Category;
SELECT * FROM Subcategory;
SELECT * FROM Course;
SELECT * FROM Section;
SELECT * FROM Activity;
SELECT * FROM Video;
SELECT * FROM Question;
SELECT * FROM Alternative;


SHOW TABLES;










