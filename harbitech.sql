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

/*OPERAÇÕES PARA COMPLEMENTAR OS TESTES*/
UPDATE Course SET visibility = 'PRIVATE' WHERE id =1

UPDATE Subcategory SET description = NULL WHERE id = 2

/*- os dados de todas as categorias ativas, na ordem */
SELECT * FROM Category WHERE status = 'ACTIVE' ORDER BY order_visualization;

/*- os dados de todas as subcategorias ativas, na ordem */
SELECT * FROM Subcategory WHERE status = 'ACTIVE' ORDER BY order_visualization;

/*- os dados de todas os cursos públicos */
SELECT * FROM Course WHERE visibility = 'PUBLIC';

/*- os nomes das subcategorias que não tem descrições*/
SELECT name AS Nome FROM Subcategory WHERE description = '' OR description IS NULL;

/*- os nomes das subcategorias ativas e que tem algum curso, na ordem*/
SELECT 
DISTINCT 
	subcategory.name,
	subcategory.order_visualization 
FROM Subcategory subcategory 
JOIN Course course ON course.subcategory_id = subcategory.id
WHERE subcategory.status = 'ACTIVE'
ORDER BY subcategory.order_visualization 

/*- o nome e a quantidade de cursos do instrutor que tem mais cursos*/
SELECT 
	instructor AS instrutor,
	COUNT(*) AS quantidade 
FROM Course course 
GROUP BY instructor 
ORDER BY quantidade DESC
LIMIT 1

/*- os nomes de todas as categorias ativas com a respectiva quantidade de cursos públicos e total de horas 
 * estimados dos cursos públicos associados (sendo 0 se não existir nenhum curso público)*/
SELECT
	category.name AS Nome,
	COUNT(course.id) AS Quantidade, 
	COALESCE(SUM(course.completion_time_in_hours),NULL,0) AS "Total de horas" 
FROM Category category 
LEFT JOIN Subcategory subcategory ON category.id = subcategory.category_id 
LEFT JOIN Course course ON course.subcategory_id = subcategory.id
WHERE category.status = 'ACTIVE' GROUP BY category.id;

/*- os dados da categoria ativa que possui o maior número de horas estimadas em cursos públicos, em apenas uma consulta*/
SELECT 
	category.name, 
	SUM(course.completion_time_in_hours) AS "Quantidade" 
FROM Category category 
LEFT JOIN Subcategory subcategory ON category.id = subcategory.category_id 
LEFT JOIN Course course ON course.subcategory_id = subcategory.id
WHERE category.status = 'ACTIVE'  AND course.visibility ='PUBLIC'
GROUP BY category.name
ORDER BY Quantidade DESC
LIMIT 1;








