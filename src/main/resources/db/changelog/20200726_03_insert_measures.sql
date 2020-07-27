--liquibase formatted sql

--changeset author:schulewa

--comment insert measures

insert into resdb_measure (name, measure_type_id, status, created_by)
select 'Chain', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Distance';

insert into resdb_measure (name, measure_type_id, associated_with_race_id, description, status, created_by)
select 'Cubit', mt.id, r.id,
       'This was the length from a man\'s elbow to the tip of his finger. It equalled two spans or 28 inches.',
       'N', 'admin'
from resdb_measure_type mt, resdb_race r
where mt.name = 'Distance'
and r.name = 'Egyptian';

insert into resdb_measure (name, measure_type_id, status, created_by)
select 'Foot', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Distance';

insert into resdb_measure (name, measure_type_id, associated_with_race_id, description, status, created_by)
select 'Furlong', mt.id, r.id,
       'Also once called a \' furrow long\' as it was the distance that an ox would plough the field in one direction in mediaeval times. Approximately 40 rods.',
       'N', 'admin'
from resdb_measure_type mt, resdb_race r
where mt.name = 'Distance'
  and r.name = 'English';

insert into resdb_measure (name, measure_type_id, associated_with_race_id, description, status, created_by)
select 'Hand', mt.id, r.id,
       'A hand width was usually four thumbs or four inches and is still in use today especially in the measurement of the height of horses from the ground to the shoulders.',
       'N', 'admin'
from resdb_measure_type mt, resdb_race r
where mt.name = 'Distance'
  and r.name = 'English';

insert into resdb_measure (name, measure_type_id, associated_with_race_id, description, status, created_by)
select 'Inch', mt.id, r.id,
       'The English word arrives via the Latin term for \' one twelfth part\' or in the specific case of the inch it was one twelfth of one foot (that is a standard human foot). In some systems it was the measure of the width of the thumb or the length of three corns of barley.',
       'N', 'admin'
from resdb_measure_type mt, resdb_race r
where mt.name = 'Distance'
  and r.name = 'English';

insert into resdb_measure (name, measure_type_id, status, created_by)
select 'Link', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Distance';

insert into resdb_measure (name, measure_type_id, description, status, created_by)
select 'Palm', mt.id,
       'This was the width of a man\' s palm not including the thumb.',
       'N', 'admin'
from resdb_measure_type mt
where mt.name = 'Distance';

insert into resdb_measure (name, measure_type_id, associated_with_race_id, description, status, created_by)
select 'Rod', mt.id, r.id,
       'This is an Anglo-Saxon term for a distance equalling 20 natural foot lengths of a man.',
       'N', 'admin'
from resdb_measure_type mt, resdb_race r
where mt.name = 'Distance'
  and r.name = 'Anglo-Saxon';

insert into resdb_measure (name, measure_type_id, associated_with_race_id, description, status, created_by)
select 'Royal Cubit', mt.id, r.id,
       'The Royal Cubit, which was a standard cubit enhanced by an extra palm—thus 7 palms or 28 digits long—was used in constructing buildings and monuments and in surveying in ancient Egypt.',
       'N', 'admin'
from resdb_measure_type mt, resdb_race r
where mt.name = 'Distance'
  and r.name = 'Egyptian';

insert into resdb_measure (name, measure_type_id, description, status, created_by)
select 'Span', mt.id,
       'This is the measure of one hand span or the width of a man\'s spread hand. It is approximately nine inches.',
       'N', 'admin'
from resdb_measure_type mt
where mt.name = 'Distance';

insert into resdb_measure (name, measure_type_id, status, created_by)
select 'Yard', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Distance';

insert into resdb_measure (name, measure_type_id, status, created_by)
select 'Mile', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Distance';

insert into resdb_measure (name, measure_type_id, status, created_by)
select 'Second', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Time';

insert into resdb_measure (name, measure_type_id, status, created_by)
select 'Minute', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Time';

insert into resdb_measure (name, measure_type_id, status, created_by)
select 'Hour', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Time';

select 'Day', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Time';

select 'Week', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Time';

select 'Month', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Time';

select 'Year', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Time';

insert into resdb_measure (name, measure_type_id, associated_with_race_id, description, status, created_by)
select 'Acre', mt.id, r.id,
       'The acre is a unit of land area used in the imperial and US customary systems. It is traditionally defined as the area of one chain by one furlong (66 by 660 feet), which is exactly equal to 10 square chains, ​1⁄640 of a square mile, or 43,560 square feet, and approximately 4,047 m2, or about 40% of a hectare.',
       'N', 'admin'
from resdb_measure_type mt, resdb_race r
where mt.name = 'Area'
  and r.name = 'Egyptian';

select 'Hectare', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Area';

select 'Ounce', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Weight';

select 'Pound', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Weight';

select 'Dram', mt.id, 'N', 'admin'
from resdb_measure_type mt where mt.name = 'Weight';

