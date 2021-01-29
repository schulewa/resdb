--liquibase formatted sql

--changeset author:schulewa

insert into resdb_image_type (name, version_no, no_of_dimensions, status, created_by)
values ('Thumbnail', 1, 2, 'N', 'admin');
