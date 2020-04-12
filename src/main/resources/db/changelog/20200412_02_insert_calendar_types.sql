--liquibase formatted sql

--changeset author:schulewa

insert into resdb_calendar_type (name, status, created_by) values ('** Unknown **', 'A', 'admin');

insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (210 days)', 210, 'A', 'admin');
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (260 days)', 260, 'A', 'admin');
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (364 days)', 364, 'A', 'admin');
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by) values ('Fixed (365 days)', 365, 'A', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Lunar', 'A', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Lunisolar', 'A', 'admin');
insert into resdb_calendar_type (name, days_per_leap_year, days_per_non_leap_year, status, created_by) values ('Lunisolar (354/ 384 days)', 384, 354, 'A', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Seasonal', 'A', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Seasonal / lunisolar', 'A', 'admin');
insert into resdb_calendar_type (name, status, created_by) values ('Solar', 'A', 'admin');

