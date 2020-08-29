--liquibase formatted sql

--changeset author:schulewa

--comment insert hierarchy types

insert into resdb_hierarchy_type (name, status, created_by) values ('Academic', 'N', 'admin');
insert into resdb_hierarchy_type (name, status, created_by) values ('Ecclesistical', 'N', 'admin');
insert into resdb_hierarchy_type (name, status, created_by) values ('Genealogy', 'N', 'admin');
insert into resdb_hierarchy_type (name, status, created_by) values ('Military', 'N', 'admin');
insert into resdb_hierarchy_type (name, status, created_by) values ('Political', 'N', 'admin');
