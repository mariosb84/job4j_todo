CREATE TABLE if not exists todo_user (
  id SERIAL PRIMARY KEY,
  name VARCHAR NOT NULL,
  login VARCHAR NOT NULL UNIQUE,
  password VARCHAR NOT NULL UNIQUE

);

