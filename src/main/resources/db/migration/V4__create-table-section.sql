CREATE TABLE section(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(70) NOT NULL,
    code_url VARCHAR (70) NOT NULL UNIQUE,
    order_visualization INT,
    status ENUM('INACTIVE','ACTIVE') DEFAULT 'INACTIVE',
    type ENUM('LECTURE','EXAM') DEFAULT 'LECTURE',
    course_id BIGINT NOT NULL,
    FOREIGN KEY(course_id) REFERENCES course(id)
                    );