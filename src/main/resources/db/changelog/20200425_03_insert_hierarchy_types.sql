--liquibase formatted sql

--changeset author:schulewa

--comment insert hierarchy types

insert into resdb_hierarchy_type (name, version_no, status, created_by) values ('Academic', 1, 'N', 'admin');
insert into resdb_hierarchy_type (name, version_no, status, created_by) values ('Ecclesiastical', 1, 'N', 'admin');
insert into resdb_hierarchy_type (name, version_no, status, created_by) values ('Genealogy', 1, 'N', 'admin');
insert into resdb_hierarchy_type (name, version_no, status, created_by) values ('Military', 1, 'N', 'admin');
insert into resdb_hierarchy_type (name, version_no, status, created_by) values ('Political', 1, 'N', 'admin');
