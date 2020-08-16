--liquibase formatted sql

--changeset author:schulewa

--comment insert periods, associated races and aliases

insert into resdb_period (name, start_approximated, period_start_year, end_approximated, period_end_year)
values ('Stone Age', 'Y', -7000, 'Y', 3000);

insert into resdb_period (name, start_approximated, period_start_year, end_approximated, period_end_year)
values ('Bronze Age', 'Y', -3000, 'Y', -1000);

insert into resdb_period (name, start_approximated, period_start_year, end_approximated, period_end_year)
values ('Paleolithic', 'Y', -2500000, 'Y', -10000);

insert into resdb_period (name, start_approximated, period_start_year, end_approximated, period_end_year)
values ('Neolithic', 'Y', -5000, 'Y', -3500);

insert into resdb_period (name, start_approximated, period_start_year, end_approximated, period_end_year)
values ('Early Bronze Age', 'Y', -3000, 'Y', -2200);

insert into resdb_period (name, start_approximated, period_start_year, end_approximated, period_end_year)
values ('Chalcolithic', 'Y', -3500, 'Y', -2300);

insert into resdb_period (name, start_approximated, period_start_year, end_approximated, period_end_year)
values ('Middle Bronze Age', 'Y', -2200, 'Y', -1570);

insert into resdb_period (name)
values ('Old Kingdom');

insert into resdb_period (name)
values ('New Kingdom');

insert into resdb_race_period (race_id, period_id, start_approximated, period_start_year, end_approximated, period_end_year)
select r.id, p.id, 'Y', -1650, 'Y', -1500
from resdb_race r, resdb_period p
where r.name = 'Hittite'
and p.name = 'Old Kingdom';

insert into resdb_race_period (race_id, period_id, start_approximated, period_start_year, end_approximated, period_end_year)
select r.id, p.id, 'Y', -1400, 'Y', -1180
from resdb_race r, resdb_period p
where r.name = 'Hittite'
and p.name = 'New Kingdom';


insert into resdb_race_period_alias (from_race_period_id, to_race_period_id)
select rp1.id, rp2.id
from resdb_period p1, resdb_period p2, resdb_race_period rp1, resdb_race_period rp2
where rp1.period_id = p1.id and p1.name = 'Paleolithic'
and rp2.period_id = p2.id and p2.name = 'Old Stone Age';

insert into resdb_race_period_alias (from_race_period_id, to_race_period_id)
select rp1.id, rp2.id
from resdb_period p1, resdb_period p2, resdb_race_period rp1, resdb_race_period rp2
where rp1.period_id = p1.id and p1.name = 'Neolithic'
and rp2.period_id = p2.id and p2.name = 'New Stone Age';

