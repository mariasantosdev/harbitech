CREATE DATABASE Harbitech;

CREATE TABLE categories(
code_url VARCHAR(30) NOT NULL PRIMARY KEY,
name VARCHAR(70) NOT NULL,
description VARCHAR(100),
study_guide VARCHAR(70),
status ENUM('inactive', 'active'),
order_visualization INT,
icon_path VARCHAR(12),
html_hex_color_code VARCHAR(7));

CREATE TABLE subcategories(
code_url VARCHAR(30) NOT NULL PRIMARY KEY,
name VARCHAR(70) NOT NULL,
description VARCHAR(100),
study_guide VARCHAR(70),
status ENUM('inactive', 'active'),
order_visualization INT,
fk_categories VARCHAR(30) NOT NULL,
FOREIGN KEY(fk_categories) REFERENCES categories(code_url));

CREATE TABLE courses(
code_url VARCHAR(30) NOT NULL PRIMARY KEY,
name VARCHAR(70) NOT NULL,
completion_time_in_hours INT NOT NULL,
visibility ENUM('public', 'private'),
target_audience VARCHAR(50),
instructor VARCHAR(70),
description VARCHAR(500),
developed_skills VARCHAR(250));

CREATE TABLE sections(
code_url VARCHAR(30) NOT NULL PRIMARY KEY,
name VARCHAR(70) NOT NULL,
order_visualization INT,
status ENUM('inactive', 'active'),
type ENUM('lecture', 'exam'),
fk_courses VARCHAR(30) NOT NULL,
FOREIGN KEY(fk_courses) REFERENCES courses(code_url));

CREATE TABLE activities(
code_url VARCHAR(30) NOT NULL PRIMARY KEY,
title VARCHAR(70) NOT NULL,
text VARCHAR(500),
status ENUM('inactive', 'active'),
fk_sections VARCHAR(30) NOT NULL,
FOREIGN KEY(fk_sections) REFERENCES courses(code_url));

CREATE TABLE alternatives(
text VARCHAR(500) NOT NULL,
order_visualization INT,
correct BIT(1),
justification VARCHAR(500)
/* AQUI VEM A FK DE QUESTION QUE É OBRIGATÓRIA */ 
);


/*ACTIVITY É UMA CLASSE ABSTRATA NA APLICAÇÃO - FILHAS: Explanation, Video, Question */




