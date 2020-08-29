--liquibase formatted sql

--changeset author:schulewa

--comment insert event types

--comment Military event types

INSERT INTO resdb_event_type (name, event_type_group_id, status, created_by)
SELECT 'Battle', etg.id, 'N', 'admin' FROM resdb_event_type_group etg WHERE etg.name = 'Military';

INSERT INTO resdb_event_type (name, event_type_group_id, status, created_by)
SELECT 'War', etg.id, 'N', 'admin' FROM resdb_event_type_group etg WHERE etg.name = 'Military';

--comment Research event types

INSERT INTO resdb_event_type (name, event_type_group_id, status, created_by)
SELECT 'Medical', etg.id, 'N', 'admin' FROM resdb_event_type_group etg WHERE etg.name = 'Research';

INSERT INTO resdb_event_type (name, event_type_group_id, status, created_by)
SELECT 'Scientific', etg.id, 'N', 'admin' FROM resdb_event_type_group etg WHERE etg.name = 'Research';
