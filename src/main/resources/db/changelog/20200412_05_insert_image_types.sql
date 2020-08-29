--liquibase formatted sql

--changeset author:schulewa

insert into resdb_image_type (name, no_of_dimensions, status, created_by)
values ('Thumbnail', 2, 'N', 'admin');
