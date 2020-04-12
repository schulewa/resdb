/********************************************************************************************************************************************
 ** CREATE CALENDAR TYPES
 ********************************************************************************************************************************************/

delete from resdb_calendar_type where owned_by = 'SYSTEM';

insert into resdb_calendar_type (name, status, created_by, last_updated) values ('** Unknown **', 'A', 'SYSTEM');

insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (210 days)', 210, 'A', 'SYSTEM');
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (260 days)', 260, 'A', 'SYSTEM');
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (364 days)', 364, 'A', 'SYSTEM');
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (365 days)', 365, 'A', 'SYSTEM');
insert into resdb_calendar_type (name, status, created_by) values ('Lunar', 'A', 'SYSTEM');
insert into resdb_calendar_type (name, status, created_by) values ('Lunisolar', 'A', 'SYSTEM');
insert into resdb_calendar_type (name, days_per_leap_year, days_per_non_leap_year, status, created_by) values ('Lunisolar (354/ 384 days)', 384, 354, 'A', 'SYSTEM');
insert into resdb_calendar_type (name, status, created_by) values ('Seasonal', 'A', 'SYSTEM');
insert into resdb_calendar_type (name, status, created_by) values ('Seasonal / lunisolar', 'A', 'SYSTEM');
insert into resdb_calendar_type (name, status, created_by) values ('Solar', 'A', 'SYSTEM');

