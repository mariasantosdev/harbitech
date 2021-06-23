CREATE DATABASE harbitech;
USE harbitech;

CREATE TABLE Category(
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '[-a-z]+'),
description TEXT,
study_guide TEXT,
status ENUM('inactive', 'active') DEFAULT 'inactive',
order_visualization INT,
icon_path VARCHAR(400),
html_hex_color_code VARCHAR(7));

CREATE TABLE Subcategory(
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '[-a-z]+'),
description TEXT,
study_guide TEXT,
status ENUM('inactive', 'active') DEFAULT 'inactive',
order_visualization INT,
category_code_url VARCHAR(70) NOT NULL,
FOREIGN KEY(category_code_url) REFERENCES Category(code_url));

CREATE TABLE Course(
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '[-a-z]+'),
completion_time_in_hours INT NOT NULL,
visibility ENUM('public', 'private') DEFAULT 'private',
target_audience VARCHAR(250),
instructor VARCHAR(70),
description TEXT,
developed_skills TEXT,
subcategory_code_url VARCHAR (70) NOT NULL,
FOREIGN KEY(subcategory_code_url) REFERENCES Subcategory(code_url));

CREATE TABLE Section(
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '[-a-z]+'),
order_visualization INT,
status ENUM('inactive', 'active') DEFAULT 'inactive',
type ENUM('lecture', 'exam') DEFAULT 'lecture',
course_code_url VARCHAR(70) NOT NULL,
FOREIGN KEY(course_code_url) REFERENCES Course(code_url));

CREATE TABLE Activity(
code_url VARCHAR (70) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '[-a-z]+'),
title VARCHAR(150) NOT NULL,
text TEXT,
status ENUM('inactive', 'active') DEFAULT 'inactive',
subtype ENUM('video','question'),
section_code_url VARCHAR(70) NOT NULL,
FOREIGN KEY(section_code_url) REFERENCES Course(code_url));

CREATE TABLE Video(
code_url VARCHAR (70) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '[-a-z]+'),
time_in_minutes INT,
transcription TEXT,
activity_code_url VARCHAR(70) NOT NULL, 
FOREIGN KEY(activity_code_url) REFERENCES Activity(code_url));
 
CREATE TABLE Question(
id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
text TEXT,
answer_type ENUM('single_choise','multiple_choise','true_or_false'),
activity_code_url VARCHAR(70) NOT NULL, 
FOREIGN KEY(activity_code_url) REFERENCES Activity(code_url));

CREATE TABLE Alternative(
text VARCHAR(500) NOT NULL,
order_visualization INT,
correct BIT(1),
justification VARCHAR(500),
question_id bigint,
FOREIGN KEY(question_id) REFERENCES Question(id));

SELECT * FROM Category;
SELECT * FROM SubCategory;
SELECT * FROM Course;
SELECT * FROM Section;
SELECT * FROM Activity;
SELECT * FROM Video;
SELECT * FROM Question;
SELECT * FROM Alternative;


