SELECT * FROM user_authority
ORDER BY user_id;

DELETE FROM user_authority
WHERE user_id = 1 AND authority_id = 3;