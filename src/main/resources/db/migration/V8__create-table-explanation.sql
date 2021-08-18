CREATE TABLE explanation(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    text TEXT NOT NULL,
    activity_id BIGINT NOT NULL,
    FOREIGN KEY(activity_id) REFERENCES activity(id)
                        );