CREATE SCHEMA IF NOT EXISTS auth;
CREATE SEQUENCE auth.user_seq START WITH 1000;
CREATE TABLE auth.users
(
    id       BIGINT PRIMARY KEY   DEFAULT nextval('auth.user_seq'),
    login    VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    enabled  BOOLEAN     NOT NULL DEFAULT TRUE
);
