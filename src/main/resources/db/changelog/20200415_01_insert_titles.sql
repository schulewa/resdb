--liquibase formatted sql

--changeset author:schulewa

insert into resdb_title (title, applies_to, title_type, status, created_by)
values ('Sir', 'M', 'P', 'A', 'admin');

