--liquibase formatted sql

--changeset rustam:1
alter table users
    add column created_at timestamp;
--changeset rustam:2
alter table users
    add column modified_at timestamp;
--changeset rustam:3
alter table users
    add column created_by varchar(32);
--changeset rustam:4
alter table users
    add column modified_by varchar(32);