CREATE DATABASE harbitech;
USE harbitech;

CREATE TABLE category(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
description TEXT,
study_guide TEXT,
status ENUM('INACTIVE','ACTIVE') DEFAULT 'INACTIVE',
order_visualization INT,
icon_path VARCHAR(400),
html_hex_color_code VARCHAR(7));

CREATE TABLE subcategory(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
description TEXT,
study_guide TEXT,
status ENUM('INACTIVE','ACTIVE') DEFAULT 'INACTIVE',
order_visualization INT,
category_id BIGINT NOT NULL,
FOREIGN KEY(category_id) REFERENCES category(id));

CREATE TABLE course(
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
FOREIGN KEY(subcategory_id) REFERENCES subcategory(id));

CREATE TABLE section(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
order_visualization INT,
status ENUM('INACTIVE','ACTIVE') DEFAULT 'INACTIVE',
type ENUM('LECTURE','EXAM') DEFAULT 'LECTURE',
course_id BIGINT NOT NULL,
FOREIGN KEY(course_id) REFERENCES course(id));

CREATE TABLE activity(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
code_url VARCHAR (70) NOT NULL UNIQUE,
title VARCHAR(150) NOT NULL,
text TEXT,
status ENUM('ACTIVITY','INACTIVITE') DEFAULT 'INACTIVITE',
subtype ENUM('VIDEO','QUESTION','EXPLANATION'),
section_id BIGINT NOT NULL,
FOREIGN KEY(section_id) REFERENCES section(id));

CREATE TABLE video(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
url VARCHAR (140) NOT NULL UNIQUE,
time_in_minutes INT,
transcription TEXT,
activity_id BIGINT NOT NULL,
FOREIGN KEY(activity_id) REFERENCES activity(id));
 
CREATE TABLE question(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
text TEXT,
answer_type ENUM('SINGLE_CHOICE','MULTIPLE_CHOICE','TRUE_OR_FALSE'),
activity_id BIGINT NOT NULL,
FOREIGN KEY(activity_id) REFERENCES activity(id));

CREATE TABLE explanation(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
text TEXT,
activity_id BIGINT NOT NULL,
FOREIGN KEY(activity_id) REFERENCES activity(id));

CREATE TABLE alternative(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
text TEXT NOT NULL,
order_visualization INT,
correct BIT(1),
justification VARCHAR(500),
question_id BIGINT NOT NULL,
FOREIGN KEY(question_id) REFERENCES question(id));

SELECT * FROM category;
SELECT * FROM subcategory;
SELECT * FROM course;   
SELECT * FROM Explanation;
SELECT * FROM Section;
SELECT * FROM Activity;
SELECT * FROM Video;
SELECT * FROM Question;
SELECT * FROM Alternative;

