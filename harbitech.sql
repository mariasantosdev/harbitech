CREATE DATABASE harbitech;
USE harbitech;

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

CREATE TABLE Section(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
name VARCHAR(70) NOT NULL,
code_url VARCHAR (70) NOT NULL UNIQUE,
order_visualization INT,
status ENUM('INACTIVE','ACTIVE') DEFAULT 'INACTIVE',
type ENUM('LECTURE','EXAM') DEFAULT 'LECTURE',
course_id BIGINT NOT NULL,
FOREIGN KEY(course_id) REFERENCES Course(id));

CREATE TABLE Activity(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
code_url VARCHAR (70) NOT NULL UNIQUE,
title VARCHAR(150) NOT NULL,
text TEXT,
status ENUM('ACTIVITY','INACTIVITE') DEFAULT 'INACTIVITE',
subtype ENUM('VIDEO','QUESTION','EXPLANATION'),
section_id BIGINT NOT NULL,
FOREIGN KEY(section_id) REFERENCES Section(id));

CREATE TABLE Video(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
url VARCHAR (140) NOT NULL UNIQUE,
time_in_minutes INT,
transcription TEXT,
activity_id BIGINT NOT NULL,
FOREIGN KEY(activity_id) REFERENCES Activity(id));
 
CREATE TABLE Question(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
text TEXT,
answer_type ENUM('SINGLE_CHOICE','MULTIPLE_CHOICE','TRUE_OR_FALSE'),
activity_id BIGINT NOT NULL,
FOREIGN KEY(activity_id) REFERENCES Activity(id));

CREATE TABLE Explanation(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
text TEXT,
activity_id BIGINT NOT NULL,
FOREIGN KEY(activity_id) REFERENCES Activity(id));

CREATE TABLE Alternative(
id BIGINT PRIMARY KEY AUTO_INCREMENT,
text TEXT NOT NULL,
order_visualization INT,
correct BIT(1),
justification VARCHAR(500),
question_id BIGINT NOT NULL,
FOREIGN KEY(question_id) REFERENCES Question(id));

SELECT * FROM Category;
SELECT * FROM Subcategory;
SELECT * FROM Course;
SELECT * FROM Explanation;
SELECT * FROM Section;
SELECT * FROM Activity;
SELECT * FROM Video;
SELECT * FROM Question;
SELECT * FROM Alternative;

SELECT * FROM Category WHERE status = 'ACTIVE' ORDER BY order_visualization;

SELECT * FROM Subcategory WHERE status = 'ACTIVE' ORDER BY order_visualization;

SELECT * FROM Course WHERE visibility = 'PUBLIC';

SELECT name AS Nome FROM Subcategory WHERE description = '';

SELECT c.name AS Nome,COUNT(c2.id) AS Quantidade, COALESCE(SUM(c2.completion_time_in_hours),0) AS "Total de horas"  FROM Category c 
LEFT JOIN Subcategory s ON c.id = s.category_id 
LEFT JOIN Course c2 ON c2.subcategory_id = s.id
WHERE c.status = 'ACTIVE' GROUP BY c.id;

SELECT s.name AS Nome FROM Subcategory s 
JOIN Course c ON s.id = c.subcategory_id 
WHERE s.status = 'ACTIVE' 
GROUP BY s.id
ORDER BY order_visualization; 

SELECT instructor AS instrutor,COUNT(*) AS "Quantidade de cursos" FROM Course c 
GROUP BY 1
ORDER BY 2 DESC
LIMIT 1;  

SELECT c.*,c2.visibility, SUM(c2.completion_time_in_hours) AS "Quantidade" FROM Category c
LEFT JOIN Subcategory s ON c.id = s.category_id 
LEFT JOIN Course c2 ON c2.subcategory_id = s.id
WHERE c.status = 'ACTIVE'  AND c2.visibility ='PUBLIC'
GROUP BY c.name 
ORDER BY c2.completion_time_in_hours DESC
LIMIT 1;

/*- os dados da categoria ativa que possui o maior número de subcategorias ativas*/
SELECT * FROM Category c 
LEFT JOIN Subcategory s ON c.id = s.category_id 
WHERE c.status = 'ACTIVE' 
ORDER BY s.status DESC
LIMIT 1;

SHOW TABLES;

SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));









