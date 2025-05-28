CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age INT
    );

INSERT INTO users (name, age) VALUES
  ('Alice', 25),
  ('Bob', 30),
  ('Cathy', 28);
