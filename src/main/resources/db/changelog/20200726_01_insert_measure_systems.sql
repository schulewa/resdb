--liquibase formatted sql

--changeset author:schulewa

--comment insert measure systems

insert into resdb_measure_system (name, for_race_id, status, created_by)
select 'Imperial', r.id, 'N', 'admin'
from resdb_race r where r.name = 'English';

insert into resdb_measure_system (name, for_race_id, status, created_by)
 select 'U.S customary units', r.id, 'N', 'admin'
from resdb_race r where r.name = 'North American';
