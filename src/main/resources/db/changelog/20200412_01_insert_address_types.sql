--liquibase formatted sql

--changeset author:schulewa

insert into resdb_address_type (name, status, created_by, last_updated, updated_by) values ('Home postal address', 'N', 'admin', '2020-04-12 18:08:00', 'admin');
insert into resdb_address_type (name, status, created_by, last_updated, updated_by) values ('Work postal address', 'N', 'admin', '2020-04-12 18:08:00', 'admin');
insert into resdb_address_type (name, status, created_by, last_updated, updated_by) values ('Personal email address', 'N', 'admin', '2020-04-12 18:08:00', 'admin');
insert into resdb_address_type (name, status, created_by, last_updated, updated_by) values ('Work email address', 'N', 'admin', '2020-04-12 18:08:00', 'admin');

