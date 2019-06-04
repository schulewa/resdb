/********************************************************************************************************************************************
 ** CREATE ADDRESS TYPES
 ********************************************************************************************************************************************/

delete from resdb_address_type where name in
('Home postal address', 'Work postal address', 'Personal email address', 'Work email address');

insert into resdb_address_type (name, status, created_by, last_updated, updated_by) values ('Home postal address', 'N', 'system', '2019-03-23 09:00:00', 'system');
insert into resdb_address_type (name, status, created_by, last_updated, updated_by) values ('Work postal address', 'N', 'system', '2019-03-23 09:00:00', 'system');
insert into resdb_address_type (name, status, created_by, last_updated, updated_by) values ('Personal email address', 'N', 'system', '2019-03-23 09:00:00', 'system');
insert into resdb_address_type (name, status, created_by, last_updated, updated_by) values ('Work email address', 'N', 'system', '2019-03-23 09:00:00', 'system');


