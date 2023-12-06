drop schema if exists customers cascade;
create schema customers;

create sequence customers.personal_data_seq start with 1000;
create table customers.personal_data
(
    id         bigint primary key default nextval('customers.personal_data_seq'),
    login      varchar not null,
    email      varchar not null,
    first_name varchar not null,
    last_name  varchar not null,
    city_id    bigint,
    birth_date date,
    foreign key (city_id) references customers.personal_data (id)
);

create sequence customers.city_seq start with 1000;
create table customers.city
(
    id   bigint primary key default nextval('customers.city_seq'),
    name varchar not null
);
