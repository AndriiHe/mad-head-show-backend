INSERT INTO users (email, fullName, password, username)
VALUES ('admin@test.com', 'admin', '$2a$10$szkD5Adu/HE.8SS8sJr0qua3PbgkqD70jsv4sCG5Bp9ByDhhbTBeO', 'admin');
INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);