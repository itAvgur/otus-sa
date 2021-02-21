DROP TABLE IF EXISTS otus_sa.person;
DROP TABLE IF EXISTS otus_sa.city;
DROP SEQUENCE IF EXISTS otus_sa.person_seq;
DROP SEQUENCE IF EXISTS otus_sa.city_seq;
DROP SCHEMA IF EXISTS otus_sa;

CREATE SCHEMA otus_sa;
CREATE SEQUENCE otus_sa.person_seq START 1000;
CREATE SEQUENCE otus_sa.city_seq START 1000;

CREATE TABLE otus_sa.city
(
    id   bigint PRIMARY KEY DEFAULT nextval('otus_sa.city_seq'),
    name varchar NOT NULL
);

CREATE TABLE otus_sa.person
(
    id         bigint PRIMARY KEY DEFAULT nextval('otus_sa.person_seq'),
    first_name varchar NOT NULL,
    last_name  varchar NOT NULL,
    city_id    bigint,
    enabled    boolean            DEFAULT TRUE,
    FOREIGN KEY (city_id) REFERENCES otus_sa.city (id)

);


