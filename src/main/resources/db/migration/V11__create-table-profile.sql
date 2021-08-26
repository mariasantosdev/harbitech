CREATE TABLE profile(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    type ENUM('ROLE_STUDENT', 'ROLE_MANAGER')
                    );