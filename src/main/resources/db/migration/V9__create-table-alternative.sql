CREATE TABLE alternative(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    text TEXT NOT NULL,
    order_visualization INT,
    correct BIT(1),
    justification VARCHAR(500),
    question_id BIGINT NOT NULL,
    FOREIGN KEY(question_id) REFERENCES question(id)
                        );