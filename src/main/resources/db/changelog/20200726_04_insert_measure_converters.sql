--liquibase formatted sql

--changeset author:schulewa

--comment insert measure converters

insert into resdb_measure_converter (name, from_measure_id, from_quantity, to_measure_id, to_quantity, math_operator, approximation, status)
select 'Chain - Link', m1.id, 1, m2.id, 100, 'MULTIPLY', 'N', 'N'
from resdb_measure m1, resdb_measure m2
where m1.name = 'Chain'
and m2.name = 'Link';

insert into resdb_measure_converter (name, from_measure_id, from_quantity, to_measure_id, to_quantity, math_operator, approximation, status)
select 'Cubit - Inch', m1.id, 1, m2.id, 28, 'MULTIPLY', 'N', 'N'
from resdb_measure m1, resdb_measure m2
where m1.name = 'Cubit'
and m2.name = 'Inch';

insert into resdb_measure_converter (name, from_measure_id, from_quantity, to_measure_id, to_quantity, math_operator, approximation, status)
select 'Cubit - Palm', m1.id, 6, m2.id, 1, 'DIVIDE', 'N', 'N'
from resdb_measure m1, resdb_measure m2
where m1.name = 'Cubit'
and m2.name = 'Palm';

insert into resdb_measure_converter (name, from_measure_id, from_quantity, to_measure_id, to_quantity, math_operator, approximation, status)
select 'Cubit - Span', m1.id, 1, m2.id, 2, 'MULTIPLY', 'N', 'N'
from resdb_measure m1, resdb_measure m2
where m1.name = 'Cubit'
and m2.name = 'Span';

