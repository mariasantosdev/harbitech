CREATE TABLE course(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(70) NOT NULL,
    code_url VARCHAR (70) NOT NULL UNIQUE,
    completion_time_in_hours INT NOT NULL,
    visibility ENUM('PUBLIC','PRIVATE') DEFAULT 'PRIVATE',
    target_audience VARCHAR(250),
    instructor VARCHAR(70) NOT NULL,
    description TEXT,
    developed_skills TEXT,
    subcategory_id BIGINT NOT NULL,
    FOREIGN KEY(subcategory_id) REFERENCES subcategory(id)
                    );