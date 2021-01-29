--liquibase formatted sql

--changeset author:schulewa

--comment insert measure types

insert into resdb_measure_type (name, version_no, status, created_by) values ('Area', 1, 'N', 'admin');
insert into resdb_measure_type (name, version_no, status, created_by) values ('Distance', 1, 'N', 'admin');
insert into resdb_measure_type (name, version_no, status, created_by) values ('Time', 1, 'N', 'admin');
insert into resdb_measure_type (name, version_no, status, created_by) values ('Volume', 1, 'N', 'admin');
insert into resdb_measure_type (name, version_no, status, created_by) values ('Weight', 1, 'N', 'admin');

