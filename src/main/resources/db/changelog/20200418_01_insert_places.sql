--liquibase formatted sql

--changeset author:schulewa

--comment add places for Sir Winston Churchill in order to test data relationships

insert resdb_place (name, version_no, status, created_by) values ('Blenheim Palace', 1, 'N', 'admin');

insert resdb_place (name, version_no, status, created_by) values ('London', 1, 'N', 'admin');
