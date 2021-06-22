CREATE DATABASE Harbitech;

CREATE TABLE Category(
code_url VARCHAR (50) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '^[a-z-]+$'),
name VARCHAR(70) NOT NULL,
description VARCHAR(100),
study_guide VARCHAR(70),
status ENUM('inactive', 'active') DEFAULT 'inactive',
order_visualization INT,
icon_path VARCHAR(12),
html_hex_color_code VARCHAR(7));

CREATE TABLE SubCategory(
code_url VARCHAR (50) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '^[a-z-]+$'),
name VARCHAR(70) NOT NULL,
description VARCHAR(100),
study_guide VARCHAR(70),
status ENUM('inactive', 'active') DEFAULT 'inactive',
order_visualization INT,
category_id VARCHAR(50) NOT NULL,
FOREIGN KEY(category_id) REFERENCES Category(code_url));

CREATE TABLE Course(
code_url VARCHAR (50) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '^[a-z-]+$'),
name VARCHAR(70) NOT NULL,
completion_time_in_hours INT NOT NULL,
visibility ENUM('public', 'private') DEFAULT 'private',
target_audience VARCHAR(50),
instructor VARCHAR(70),
description VARCHAR(500),
developed_skills VARCHAR(250));

CREATE TABLE Section(
code_url VARCHAR (50) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '^[a-z-]+$'),
name VARCHAR(70) NOT NULL,
order_visualization INT,
status ENUM('inactive', 'active') DEFAULT 'inactive',
type ENUM('lecture', 'exam'),
course_id VARCHAR(50) NOT NULL,
FOREIGN KEY(course_id) REFERENCES courses(code_url));

CREATE TABLE Activity(
code_url VARCHAR (50) NOT NULL PRIMARY KEY CHECK (code_url REGEXP '^[a-z-]+$'),
title VARCHAR(70) NOT NULL,
text VARCHAR(500),
status ENUM('inactive', 'active') DEFAULT 'inactive',
subtype ENUM('video','question'),
section_id VARCHAR(50) NOT NULL,
FOREIGN KEY(section_id) REFERENCES Course(code_url));
