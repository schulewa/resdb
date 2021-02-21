--liquibase formatted sql

--changeset author:schulewa

--comment add prefix and suffix titles for Sir Winston Churchill to test data relationships

insert into resdb_person_title (person_id, title_id, position, created_by, status, version_no)
select p.id, t.id, 1, 'system', 'N', 1
from resdb_person p, resdb_title t
where p.first_name = 'Winston' and p.middle_name = 'Leonard' and p.family_name = 'Spencer-Churchill' and p.status = 'N'
and t.title = 'Sir' and t.applies_to = 'M' and t.title_type = 'P' and t.status = 'N';

insert into resdb_person_title (person_id, title_id, position, created_by, status, version_no)
select p.id, t.id, 1, 'system', 'N', 1
from resdb_person p, resdb_title t
where p.first_name = 'Winston' and p.middle_name = 'Leonard' and p.family_name = 'Spencer-Churchill' and p.status = 'N'
and t.title = 'KG' and t.applies_to = 'M' and t.title_type = 'S' and t.status = 'N';

insert into resdb_person_title (person_id, title_id, position, created_by, status, version_no)
select p.id, t.id, 2, 'system', 'N', 1
from resdb_person p, resdb_title t
where p.first_name = 'Winston' and p.middle_name = 'Leonard' and p.family_name = 'Spencer-Churchill' and p.status = 'N'
and t.title = 'OM' and t.applies_to = 'M' and t.title_type = 'S' and t.status = 'N';

insert into resdb_person_title (person_id, title_id, position, created_by, status, version_no)
select p.id, t.id, 3, 'system', 'N', 1
from resdb_person p, resdb_title t
where p.first_name = 'Winston' and p.middle_name = 'Leonard' and p.family_name = 'Spencer-Churchill' and p.status = 'N'
and t.title = 'CH' and t.applies_to = 'M' and t.title_type = 'S' and t.status = 'N';

insert into resdb_person_title (person_id, title_id, position, created_by, status, version_no)
select p.id, t.id, 4, 'system', 'N', 1
from resdb_person p, resdb_title t
where p.first_name = 'Winston' and p.middle_name = 'Leonard' and p.family_name = 'Spencer-Churchill' and p.status = 'N'
and t.title = 'TD' and t.applies_to = 'M' and t.title_type = 'S' and t.status = 'N';

insert into resdb_person_title (person_id, title_id, position, created_by, status, version_no)
select p.id, t.id, 5, 'system', 'N', 1
from resdb_person p, resdb_title t
where p.first_name = 'Winston' and p.middle_name = 'Leonard' and p.family_name = 'Spencer-Churchill' and p.status = 'N'
and t.title = 'DL' and t.applies_to = 'M' and t.title_type = 'S' and t.status = 'N';

insert into resdb_person_title (person_id, title_id, position, created_by, status, version_no)
select p.id, t.id, 6, 'system', 'N', 1
from resdb_person p, resdb_title t
where p.first_name = 'Winston' and p.middle_name = 'Leonard' and p.family_name = 'Spencer-Churchill' and p.status = 'N'
and t.title = 'FRS' and t.applies_to = 'M' and t.title_type = 'S' and t.status = 'N';

insert into resdb_person_title (person_id, title_id, position, created_by, status, version_no)
select p.id, t.id, 7, 'system', 'N', 1
from resdb_person p, resdb_title t
where p.first_name = 'Winston' and p.middle_name = 'Leonard' and p.family_name = 'Spencer-Churchill' and p.status = 'N'
and t.title = 'RA' and t.applies_to = 'M' and t.title_type = 'S' and t.status = 'N';

