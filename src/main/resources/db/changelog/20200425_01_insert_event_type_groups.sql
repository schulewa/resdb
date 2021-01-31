--liquibase formatted sql

--changeset author:schulewa

--comment insert event type groups

insert into resdb_event_type_group (name, version_no, status, created_by) values ('Discovery', 1, 'N', 'admin');
insert into resdb_event_type_group (name, version_no, status, created_by) values ('Exploration', 1, 'N', 'admin');
insert into resdb_event_type_group (name, version_no, status, created_by) values ('Medical', 1, 'N', 'admin');
insert into resdb_event_type_group (name, version_no, status, created_by) values ('Military', 1, 'N', 'admin');
insert into resdb_event_type_group (name, version_no, status, created_by) values ('Research', 1, 'N', 'admin');
insert into resdb_event_type_group (name, version_no, status, created_by) values ('Sport', 1, 'N', 'admin');

