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

INSERT INTO meals (id, user_id, description, calories, date_time) VALUES
  (100002, 100000, 'Завтрак', 500, '2017-11-21 10:00:00'),
  (100003, 100000, 'Обед', 700, '2017-11-21 13:00:00'),
  (100004, 100000, 'Ужин', 500, '2017-11-21 20:00:00'),
  (100005, 100001, 'Завтрак', 750, '2017-11-21 10:00:00'),
  (100006, 100001, 'Обед', 750, '2017-11-21 13:00:00'),
  (100007, 100001, 'Ужин', 550, '2017-11-21 20:00:00');
