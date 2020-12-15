--liquibase formatted sql

--changeset author:schulewa

insert into resdb_address_type (name, version_no, status, created_by, last_updated, updated_by) values ('Home postal address', 1, 'N', 'admin', '2020-04-12 18:08:00', 'admin');
insert into resdb_address_type (name, version_no, status, created_by, last_updated, updated_by) values ('Work postal address', 1, 'N', 'admin', '2020-04-12 18:08:00', 'admin');
insert into resdb_address_type (name, version_no, status, created_by, last_updated, updated_by) values ('Personal email address', 1, 'N', 'admin', '2020-04-12 18:08:00', 'admin');
insert into resdb_address_type (name, version_no, status, created_by, last_updated, updated_by) values ('Work email address', 1, 'N', 'admin', '2020-04-12 18:08:00', 'admin');

