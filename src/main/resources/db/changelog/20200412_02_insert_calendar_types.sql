--liquibase formatted sql

--changeset author:schulewa

insert into resdb_calendar_type (name, status, created_by) values ('** Unknown **', 'N', 'admin');

insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (210 days)', 210, 'N', 'admin');
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (260 days)', 260, 'N', 'admin');
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (364 days)', 364, 'N', 'admin');
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (365 days)', 365, 'N', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Lunar', 'N', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Lunisolar', 'N', 'admin');
insert into resdb_calendar_type (name, days_per_leap_year, days_per_non_leap_year, status, created_by) values ('Lunisolar (354/ 384 days)', 384, 354, 'N', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Seasonal', 'N', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Seasonal / lunisolar', 'N', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Solar', 'N', 'admin');

