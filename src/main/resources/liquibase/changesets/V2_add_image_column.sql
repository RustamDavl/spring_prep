--liquibase formatted sql

--changeset rustam:1
alter table users
    add column image varchar(64);

--changeset rustam:2
alter table users_aud
    add column image varchar(64);