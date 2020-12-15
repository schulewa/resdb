--liquibase formatted sql

--changeset author:schulewa

insert into resdb_calendar_type (name, status, created_by, version_no) values ('** Unknown **', 'N', 'admin', 0);

insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by, version_no) values ('Fixed (210 days)', 210, 'N', 'admin', 0);
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by, version_no) values ('Fixed (260 days)', 260, 'N', 'admin', 0);
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by, version_no) values ('Fixed (364 days)', 364, 'N', 'admin', 0);
insert into resdb_calendar_type (name, days_per_non_leap_year, status, created_by, version_no) values ('Fixed (365 days)', 365, 'N', 'admin', 0);
insert into resdb_calendar_type (name, status, created_by, version_no) values ('Lunar', 'N', 'admin', 0);
insert into resdb_calendar_type (name, status, created_by, version_no) values ('Lunisolar', 'N', 'admin', 0);
insert into resdb_calendar_type (name, days_per_leap_year, days_per_non_leap_year, status, created_by, version_no) values ('Lunisolar (354/ 384 days)', 384, 354, 'N', 'admin', 0);
insert into resdb_calendar_type (name, status, created_by, version_no) values ('Seasonal', 'N', 'admin', 0);
insert into resdb_calendar_type (name, status, created_by, version_no) values ('Seasonal / lunisolar', 'N', 'admin', 0);
insert into resdb_calendar_type (name, status, created_by, version_no) values ('Solar', 'N', 'admin', 0);

