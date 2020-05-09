--liquibase formatted sql

--changeset author:schulewa

--comment insert event type groups

insert into resdb_event_type_group (name, status, created_by) values ('Discovery', 'N', 'admin');
insert into resdb_event_type_group (name, status, created_by) values ('Exploration', 'N', 'admin');
insert into resdb_event_type_group (name, status, created_by) values ('Military', 'N', 'admin');
insert into resdb_event_type_group (name, status, created_by) values ('Research', 'N', 'admin');

