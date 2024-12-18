DROP TABLE IF EXISTS users CASCADE;

CREATE TABLE users (
                      id BIGINT GENERATED BY DEFAULT AS IDENTITY,
                      username VARCHAR UNIQUE,
                      password VARCHAR,
                      role VARCHAR(10),
                      status VARCHAR(10),
                      PRIMARY KEY (id)
);