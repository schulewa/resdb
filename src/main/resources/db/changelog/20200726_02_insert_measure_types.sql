--liquibase formatted sql

--changeset author:schulewa

--comment insert measure types

insert into resdb_measure_type (name, status, created_by) values ('Area', 'N', 'admin');
insert into resdb_measure_type (name, status, created_by) values ('Distance', 'N', 'admin');
insert into resdb_measure_type (name, status, created_by) values ('Time', 'N', 'admin');
insert into resdb_measure_type (name, status, created_by) values ('Volume', 'N', 'admin');
insert into resdb_measure_type (name, status, created_by) values ('Weight', 'N', 'admin');

