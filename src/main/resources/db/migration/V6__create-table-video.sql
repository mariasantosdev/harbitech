CREATE TABLE video(
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    url VARCHAR (140) NOT NULL UNIQUE,
    time_in_minutes INT,
    transcription TEXT,
    activity_id BIGINT NOT NULL,
    FOREIGN KEY(activity_id) REFERENCES activity(id)
                  );