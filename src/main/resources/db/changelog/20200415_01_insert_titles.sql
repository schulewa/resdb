--liquibase formatted sql

--changeset author:schulewa

insert into resdb_title (title, applies_to, title_type, status, created_by)
values ('Sir', 'M', 'P', 'N', 'admin');

insert into resdb_title (title, description, applies_to, title_type, status, created_by)
values ('HRH', 'Her Royal Highness', 'F', 'P', 'N', 'admin');

insert into resdb_title (title, description, applies_to, title_type, status, created_by)
values ('HRH', 'His Royal Highness', 'M', 'P', 'N', 'admin');

insert into resdb_title (title, description, applies_to, title_type, status, created_by)
values ('DL', 'Deputy Lieutenant', 'M', 'S', 'N', 'admin');

insert into resdb_title (title, description, applies_to, title_type, status, created_by)
values ('CH', 'Order of The Companions of Honour', 'M', 'S', 'N', 'admin');

insert into resdb_title (title, description, applies_to, title_type, status, created_by)
values ('FRS', 'Fellow of the Royal Society', 'N', 'S', 'N', 'admin');

insert into resdb_title (title, description, applies_to, title_type, status, created_by)
values ('RA', 'Royal Academy of Arts', 'N', 'S', 'N', 'admin');

insert into resdb_title (title, description, applies_to, title_type, status, created_by)
values ('KG', 'Order of The Garter', 'M', 'S', 'N', 'admin');

insert into resdb_title (title, description, applies_to, title_type, status, created_by)
values ('OM', 'Order of Merit', 'M', 'S', 'N', 'admin');

insert into resdb_title (title, description, applies_to, title_type, status, created_by)
values ('TD', 'Territorial Decoration', 'M', 'S', 'N', 'admin');
