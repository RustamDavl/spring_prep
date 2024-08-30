--liquibase formatted sql

--changeset rustam:1
alter table users
    add column password varchar(128) default '{noop}123';

--changeset rustam:2
alter table users_aud
    add column password varchar(128);