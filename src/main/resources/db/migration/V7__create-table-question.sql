CREATE TABLE question(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    text TEXT NOT NULL,
    answer_type ENUM('SINGLE_CHOICE','MULTIPLE_CHOICE','TRUE_OR_FALSE'),
    activity_id BIGINT NOT NULL,
    FOREIGN KEY(activity_id) REFERENCES activity(id)
                     );