CREATE TABLE user_profiles(
    user_id BIGINT,
    profiles_id BIGINT,
    primary key (user_id, profiles_id),
    FOREIGN KEY(user_id) REFERENCES user(id),
    FOREIGN KEY(profiles_id) REFERENCES profile(id)
                          );