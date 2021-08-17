CREATE TABLE subcategory(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(70) NOT NULL UNIQUE,
    code_url VARCHAR (70) NOT NULL UNIQUE,
    description TEXT,
    study_guide TEXT,
    status ENUM('INACTIVE','ACTIVE') DEFAULT 'INACTIVE',
    order_visualization INT,
    category_id BIGINT NOT NULL,
    FOREIGN KEY(category_id) REFERENCES category(id)
                        );