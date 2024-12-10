CREATE TABLE user_self_assessment
(
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id        BIGINT,
    subcategory_id BIGINT,
    CONSTRAINT fk_user_self_assessment FOREIGN KEY (user_id) REFERENCES user (id),
    CONSTRAINT fk_subcategory_20240709 FOREIGN KEY (subcategory_id) REFERENCES subcategory (id)
);
