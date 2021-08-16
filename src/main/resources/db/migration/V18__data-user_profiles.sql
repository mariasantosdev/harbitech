INSERT INTO user_profiles(user_id, profiles_id) VALUES ((SELECT u.id FROM user u
WHERE u.email = 'maria@gmail.com'), (SELECT p.id FROM profile p WHERE
p.type = 'ROLE_MANAGER'));

INSERT INTO user_profiles(user_id, profiles_id) VALUES ((SELECT u.id FROM user u
WHERE u.email = 'alexandre@gmail.com'), (SELECT p.id FROM profile p WHERE
p.type = 'ROLE_STUDENT'));