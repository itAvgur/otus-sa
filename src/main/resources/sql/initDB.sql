DROP TABLE IF EXISTS otus_sa.person;
DROP SEQUENCE IF EXISTS otus_sa.person_seq;
DROP SCHEMA IF EXISTS otus_sa;

CREATE SCHEMA otus_sa;
CREATE SEQUENCE otus_sa.person_seq START 1000;

CREATE TABLE otus_sa.person
(
    id         bigint PRIMARY KEY DEFAULT nextval('otus_sa.person_seq'),
    first_name varchar NOT NULL,
    last_name  varchar NOT NULL,
    city       varchar,
    enabled    boolean            DEFAULT TRUE
);


