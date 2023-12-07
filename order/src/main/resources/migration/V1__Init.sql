CREATE SCHEMA IF NOT EXISTS orders;

CREATE TABLE orders.states
(
    state_id SERIAL PRIMARY KEY NOT NULL,
    name     VARCHAR
);
INSERT INTO orders.states ("state_id", "name")
VALUES (0, 'READY'),
       (1, 'IN_PROGRESS'),
       (2, 'DONE'),
       (3, 'CLOSED'),
       (4, 'DELETED');

CREATE TABLE orders.orders
(
    order_id    SERIAL PRIMARY KEY NOT NULL,
    customer_id BIGINT             NOT NULL,
    description VARCHAR            NOT NULL,
    sum         NUMERIC(11, 2)     NOT NULL,
    state_id    INT                NOT NULL,
    FOREIGN KEY (customer_id) REFERENCES customers.personal_data (id),
    FOREIGN KEY (state_id) REFERENCES orders.states (state_id)
);

CREATE TABLE orders.idemp_key
(
    idemp_key_id UUID PRIMARY KEY NOT NULL,
    date_time    TIMESTAMP        NOT NULL
);
