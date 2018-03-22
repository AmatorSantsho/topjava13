DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (date_time, description, calories, user_id) VALUES
  ('2015-05-30 19:00:00', 'Ужин', 200,100000),
  ('2015-05-30 14:00:00', 'Обед', 400,100000),
  ('2015-05-30 09:00:00', 'Завтрак', 100,100000),
  ('2015-05-30 09:00:00', 'Завтрак', 250,100001);