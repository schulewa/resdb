--liquibase formatted sql

--changeset author:schulewa

--comment add places for Sir Winston Churchill in order to test data relationships

insert resdb_place (name, status, created_by) values ('Blenheim Palace', 'N', 'admin');

insert resdb_place (name, status, created_by) values ('London', 'N', 'admin');
