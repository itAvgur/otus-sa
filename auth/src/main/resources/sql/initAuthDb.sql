drop schema if exists auth cascade;
create schema auth;
create sequence auth.user_seq start with 1000;
create table auth.users
(
    id       bigint primary key default nextval('auth.user_seq'),
    login    varchar not null,
    password varchar not null,
    enabled  boolean            default true
);
