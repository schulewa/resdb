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

 --comment CREATE DEFAULT MENU SYSTEM

insert into resdb_menu (name, owner_name, owner_type) values ('default', 'admin', 'U');

--comment TOP LEVEL MENU

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (1, 1, 1, 17, 0, 'Home', 'Go to Home screen', 0, 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (1, 1, 2, 18, 0, 'Static Data', 'Go to Static Data screen', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (1, 1, 3, 19, 0, 'Configuration', 'Go to Configuration screen', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (1, 1, 4, 20, 0, 'Research', 'Go to Research screen', 'n', 'n', 'n', 0);

--comment STATIC DATA SUB-MENU

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 1, 1, 2, 'Address Type', 'Address types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 2, 2, 2, 'Artefact Artifact Types', 'Artefact attribute types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 3, 3, 2, 'Artefact Types', 'Artefact types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 4, 4, 2, 'Calendar Types', 'Calendar types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 5, 5, 2, 'Deity Types', 'Deity types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 6, 6, 2, 'Entity Types', 'Entity types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 7, 7, 2, 'Event Types', 'Event types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 8, 8, 2, 'Event Attribute Types', 'Event attribute types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 9, 9, 2, 'Hierarchy Types', 'Hierarchy types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 10, 10, 2, 'Measure Types', 'Measure types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 11, 11, 2, 'Person Types', 'Person types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 12, 12, 2, 'Publication Types', 'Publication types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 13, 13, 2, 'Race Types', 'Race types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 14, 14, 2, 'Reference Types', 'Reference types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 15, 15, 2, 'Tale Types', 'Tale types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 2, 16, 16, 2, 'Technology Types', 'Technology types maintenance', 'n', 'n', 'n', 0);

--comment CONFIGURATION SUB-MENU
insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (3, 2, 1, 21, 2, 'Data Class Association', 'Data class association maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (3, 2, 2, 22, 2, 'Menu Configuration', 'Menu configuration maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (3, 2, 3, 24, 2, 'User Login Maintenance', 'User login maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (3, 2, 4, 25, 2, 'System Parameters', 'System parameters', 'n', 'n', 'n', 0);

--comment RESEARCH SUB-MENU


--comment CREATE PERMISSIONS FOR ADMIN USER

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_CONFIGURATION';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_RESEARCH';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_STATIC_DATA';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ADDRESS_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ADDRESS_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ADDRESS_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_ATTRIBUTE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_ATTRIBUTE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_ATTRIBUTE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_ATTRIBUTE_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_ATTRIBUTE_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_ATTRIBUTE_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_GROUP-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_GROUP-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_GROUP-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_IMAGE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_IMAGE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_IMAGE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_IMG_PATTERN_SMBL-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_IMG_PATTERN_SMBL-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_IMG_PATTERN_SMBL-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_PLACE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_PLACE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_PLACE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_RSTRTN_WORK-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_RSTRTN_WORK-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_RSTRTN_WORK-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ARTEFACT_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'BIBLIOGRAPHY-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'BIBLIOGRAPHY-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'BIBLIOGRAPHY-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'BIBLIOGRAPHY_PUBLICATION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'BIBLIOGRAPHY_PUBLICATION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'BIBLIOGRAPHY_PUBLICATION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'CALENDAR-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'CALENDAR-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'CALENDAR-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'CALENDAR_RACE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'CALENDAR_RACE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'CALENDAR_RACE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'CALENDAR_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'CALENDAR_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'CALENDAR_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'COUNTRY-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'COUNTRY-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'COUNTRY-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'COUNTRY_REGION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'COUNTRY_REGION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'COUNTRY_REGION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DATA_CLASS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DATA_CLASS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DATA_CLASS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DATA_CLASS_METHOD-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DATA_CLASS_METHOD-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DATA_CLASS_METHOD-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DATA_CLASS_METHOD_ARGUMENT-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DATA_CLASS_METHOD_ARGUMENT-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DATA_CLASS_METHOD_ARGUMENT-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY_ALIAS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY_ALIAS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY_ALIAS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY_RELIGION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY_RELIGION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY_RELIGION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DEITY_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DROPDOWN_CLASS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DROPDOWN_CLASS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DROPDOWN_CLASS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DROPDOWN_DOMAIN-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DROPDOWN_DOMAIN-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'DROPDOWN_DOMAIN-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ENTITY-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ENTITY-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ENTITY-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ENTITY_ADDRESS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ENTITY_ADDRESS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ENTITY_ADDRESS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ENTITY_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ENTITY_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'ENTITY_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_ATTRIBUTE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_ATTRIBUTE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_ATTRIBUTE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_ATTRIBUTE_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_ATTRIBUTE_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_ATTRIBUTE_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_PERSON-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_PERSON-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_PERSON-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_TYPE_GROUP-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_TYPE_GROUP-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'EVENT_TYPE_GROUP-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'HIERARCHY-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'HIERARCHY-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'HIERARCHY-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'HIERARCHY_NODE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'HIERARCHY_NODE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'HIERARCHY_NODE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'HIERARCHY_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'HIERARCHY_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'HIERARCHY_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE_COLLECTION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE_COLLECTION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE_COLLECTION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE_COLLECTION_SEQ-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE_COLLECTION_SEQ-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE_COLLECTION_SEQ-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'IMAGE_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE_GROUP-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE_GROUP-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE_GROUP-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE_LOCATION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE_LOCATION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE_LOCATION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE_PATTERN_SYMBOL-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE_PATTERN_SYMBOL-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LANGUAGE_PATTERN_SYMBOL-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LIBRARY_LOCATION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LIBRARY_LOCATION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'LIBRARY_LOCATION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MATERIAL-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MATERIAL-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MATERIAL-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MEASURE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MEASURE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MEASURE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MEASURE_CONVERTOR-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MEASURE_CONVERTOR-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MEASURE_CONVERTOR-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MEASURE_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MEASURE_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MEASURE_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_CONFIGURATION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_CONFIGURATION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_CONFIGURATION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_ITEM-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_ITEM-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_ITEM-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_OPTION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_OPTION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'MENU_OPTION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'OTHER_ADDRESS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'OTHER_ADDRESS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'OTHER_ADDRESS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PATTERN_SYMBOL-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PATTERN_SYMBOL-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PATTERN_SYMBOL-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERIOD-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERIOD-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERIOD-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERMISSION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERMISSION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERMISSION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_ALIAS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_ALIAS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_ALIAS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_DEFINITION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_DEFINITION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_DEFINITION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_ROLE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_ROLE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_ROLE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PERSON_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PLACE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PLACE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PLACE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PLACE_ALIAS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PLACE_ALIAS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PLACE_ALIAS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'POSTAL_ADDRESS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'POSTAL_ADDRESS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'POSTAL_ADDRESS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PUBLICATION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PUBLICATION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PUBLICATION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PUBLICATION_ROLE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PUBLICATION_ROLE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'PUBLICATION_ROLE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE_ALIAS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE_ALIAS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE_ALIAS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE_PLACE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE_PLACE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE_PLACE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RACE_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'REFERENCE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'REFERENCE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'REFERENCE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'REFERENCE_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'REFERENCE_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'REFERENCE_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'REGION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'REGION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'REGION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RELIGION-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RELIGION-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RELIGION-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RESET_PASSWORD-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RIVER-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RIVER-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RIVER-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RIVER_ALIAS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RIVER_ALIAS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'RIVER_ALIAS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'SYSTEM_PARAMETERS-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'SYSTEM_PARAMETERS-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'SYSTEM_PARAMETERS-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TALE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TALE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TALE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TALE_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TALE_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TALE_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TECHNOLOGY-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TECHNOLOGY-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TECHNOLOGY-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TECHNOLOGY_TYPE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TECHNOLOGY_TYPE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TECHNOLOGY_TYPE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TECHNOLOGY_TYPE_GROUP-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TECHNOLOGY_TYPE_GROUP-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TECHNOLOGY_TYPE_GROUP-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'THEORY-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'THEORY-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'THEORY-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TITLE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TITLE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'TITLE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'USER_ACCOUNT_MAINTENANCE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'USER_ACCOUNT_MAINTENANCE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'USER_ACCOUNT_MAINTENANCE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'USER_GROUP_MAINTENANCE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'USER_GROUP_MAINTENANCE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'USER_GROUP_MAINTENANCE-R';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'USER_PASSWORD_MAINTENANCE-C';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'USER_PASSWORD_MAINTENANCE-U';

insert into resdb_user_group_permission (group_id, permission_id)
select ug.id, p.id from resdb_user_group ug, resdb_permission p where ug.group_name = 'ADMIN' and p.name = 'USER_PASSWORD_MAINTENANCE-R';
