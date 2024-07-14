CREATE TABLE enrollment
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id   BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    CONSTRAINT fk_enrollment_user FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_enrollment_course FOREIGN KEY (course_id) REFERENCES course (id)
);
