CREATE TABLE category(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(70) NOT NULL UNIQUE,
    code_url VARCHAR (70) NOT NULL UNIQUE,
    description TEXT,
    study_guide TEXT,
    status ENUM('INACTIVE','ACTIVE') DEFAULT 'INACTIVE',
    order_visualization INT,
    icon_path VARCHAR(400),
    html_hex_color_code VARCHAR(7)
                     );