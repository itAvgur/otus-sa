CREATE SCHEMA IF NOT EXISTS customers;
CREATE SEQUENCE customers.personal_data_seq START WITH 1000;
CREATE TABLE customers.personal_data
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('customers.personal_data_seq'),
    login      VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    city_id    INT,
    birth_date DATE,
    FOREIGN KEY (city_id) REFERENCES customers.personal_data (id)
);

CREATE SEQUENCE customers.city_seq START WITH 1000;
CREATE TABLE customers.city
(
    id   INT PRIMARY KEY DEFAULT nextval('customers.city_seq'),
    name VARCHAR NOT NULL
);
