--liquibase formatted sql

--changeset author:schulewa

--comment add user Sir Winston Churchill in order to test data relationships

/*
    first_name        VARCHAR(30) NOT NULL,
    middle_name       VARCHAR(30) NULL,
    family_name       VARCHAR(50) NOT NULL,
    gender            VARCHAR(1)  NULL,
    birth_calendar_id BIGINT      NULL,
    birth_year        BIGINT      NULL,
    birth_month       INTEGER     NULL,
    birth_day         INTEGER     NULL,
    death_calendar_id BIGINT      NULL,
    death_year        INTEGER     NULL,
    death_month       INTEGER     NULL,
    death_day         INTEGER     NULL,
    prefix_title_id   BIGINT      NULL,
    suffix_title_id   BIGINT      NULL,
    birth_place_id    BIGINT      NULL,
    death_place_id    BIGINT      NULL,
    status            VARCHAR(1)  NOT NULL,
    created_by        VARCHAR(20) NOT NULL,
    last_updated      TIMESTAMP   NOT NULL,
    updated_by        VARCHAR(20) NOT NULL,
*/

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
select  'Winston', 'Leonard', 'Spencer-Churchill', 'M', c.id, 1874, 11, 30, c.id, 1965, 1, 24, prefix.id, suffix.id, birth.id, death.id, 'A', 'admin'
from resdb_calendar c, resdb_title prefix, resdb_title suffix, resdb_place birth, resdb_place death
where c.name = 'Gregorian calendar' and c.status = 'A'
and prefix.title = 'Sir' and prefix.status = 'A'
and suffix.title = 'KG' and suffix.status = 'A'
and birth.name = 'Blenheim Palace' and birth.status = 'A'
and death.name = 'London' and death.status = 'A';
