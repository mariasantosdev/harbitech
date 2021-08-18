CREATE TABLE activity(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    code_url VARCHAR (70) NOT NULL UNIQUE,
    title VARCHAR(150) NOT NULL,
    text TEXT,
    status ENUM('ACTIVITY','INACTIVITE') DEFAULT 'INACTIVITE',
    subtype ENUM('VIDEO','QUESTION','EXPLANATION'),
    section_id BIGINT NOT NULL,
    FOREIGN KEY(section_id) REFERENCES section(id)
                     );