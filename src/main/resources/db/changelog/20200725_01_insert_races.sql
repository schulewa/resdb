--liquibase formatted sql

--changeset author:schulewa

--comment insert races along with aliases

insert into resdb_race (name) values ('Anasazi');
insert into resdb_race (name) values ('Ancestral Pueblo');
insert into resdb_race (name) values ('Anglo-Saxon');
insert into resdb_race (name) values ('Egyptian');
insert into resdb_race (name) values ('English');
insert into resdb_race (name) values ('Etruscan');
insert into resdb_race (name) values ('North American');
insert into resdb_race (name) values ('Rasenna');

insert into resdb_race_alias (from_race_id, to_race_id)
select r1.id, r2.id
from resdb_race r1, resdb_race r2
where r1.name = 'Ancestral Pueblo'
and r2.name = 'Anasazi';

insert into resdb_race_alias (from_race_id, to_race_id)
select r1.id, r2.id
from resdb_race r1, resdb_race r2
where r1.name = 'Etruscan'
and r2.name = 'Rasenna';
