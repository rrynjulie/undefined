SELECT * FROM user_authority;

INSERT INTO user_authority
VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2, 2);

DELETE FROM user_authority
WHERE user_id = 1 AND authority_id = 3;