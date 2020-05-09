--liquibase formatted sql

--changeset author:schulewa

--comment add user Sir Winston Churchill in order to test data relationships

insert into resdb_person (first_name,
                          middle_name,
                          family_name,
                          gender,
                          birth_calendar_id,
                          birth_year,
                          birth_month,
                          birth_day,
                          death_calendar_id,
                          death_year,
                          death_month,
                          death_day,
                          prefix_title_id,
                          suffix_title_id,
                          birth_place_id,
                          death_place_id,
                          status,
                          created_by)
select  'Winston', 'Leonard', 'Spencer-Churchill', 'M', c.id, 1874, 11, 30, c.id, 1965, 1, 24, prefix.id, suffix.id, birth.id, death.id, 'N', 'admin'
from resdb_calendar c, resdb_title prefix, resdb_title suffix, resdb_place birth, resdb_place death
where c.name = 'Gregorian calendar' and c.status = 'N'
and prefix.title = 'Sir' and prefix.status = 'N'
and suffix.title = 'KG' and suffix.status = 'N'
and birth.name = 'Blenheim Palace' and birth.status = 'N'
and death.name = 'London' and death.status = 'N';
