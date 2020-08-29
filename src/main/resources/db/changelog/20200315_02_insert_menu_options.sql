--liquibase formatted sql

--changeset author:schulewa

-- comment CREATE STANDARD MENU OPTIONS

INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Address types maintenance','menu.entity.AddressType', 'Address types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Artefact attribute types maintenance','menu.entity.ArtefactAttributeType', 'Artefact attribute types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Artefact types maintenance','menu.entity.ArtefactType', 'Artefact types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Calendar types maintenance','menu.entity.CalendarType', 'Calendar types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Deity types maintenance','menu.entity.DeityType', 'Deity types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Entity types maintenance','menu.entity.EntityType', 'Entity types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Event types maintenance','menu.entity.EventType', 'Event types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Event attribute types maintenance','menu.entity.EventAttributeType', 'Event attribute types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Hierarchy types maintenance','menu.entity.HierarchyType', 'Hierarchy types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Material types maintenance','menu.entity.MeasureType', 'Measure types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Measure types maintenance','menu.entity.MeasureType', 'Measure types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Person types maintenance','menu.entity.PersonType', 'Person types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Publication types maintenance','menu.entity.PublicationType', 'Publication types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Race types maintenance','menu.entity.RaceType', 'Race types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Reference types maintenance','menu.entity.ReferenceType', 'Reference types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Tale types maintenance','menu.entity.TaleType', 'Tale types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Technology types maintenance','menu.entity.TechnologyType', 'Technology types', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Home','', 'Home', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Static Data','', 'Static Data', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Configuration','', 'Configuration', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Research','', 'Research', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Data Class Association','', 'Data Class Association', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Menu Configuration','', 'Menu Configuration', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('Reset Password','', 'Reset Password', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('User Login Maintenance','', 'User Login Maintenance', '');
INSERT INTO resdb_menu_option (name, data_object, default_menu_text, operation) VALUES ('System Parameters','', 'System Parameters', '');
