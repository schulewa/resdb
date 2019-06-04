/********************************************************************************************************************************************
 ** CREATE MENU OPTIONS
 ********************************************************************************************************************************************/

INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (1, 'Address types maintenance','menu.entity.AddressType', 'Address types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (2, 'Artefact attribute types maintenance','menu.entity.ArtefactAttributeType', 'Artefact attribute types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (3, 'Artefact types maintenance','menu.entity.ArtefactType', 'Artefact types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (4, 'Calendar types maintenance','menu.entity.CalendarType', 'Calendar types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (5, 'Deity types maintenance','menu.entity.DeityType', 'Deity types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (6, 'Entity types maintenance','menu.entity.EntityType', 'Entity types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (7, 'Event types maintenance','menu.entity.EventType', 'Event types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (8, 'Event attribute types maintenance','menu.entity.EventAttributeType', 'Event attribute types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (9, 'Hierarchy types maintenance','menu.entity.HierarchyType', 'Hierarchy types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (10, 'Measure types maintenance','menu.entity.MeasureType', 'Measure types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (11, 'Person types maintenance','menu.entity.PersonType', 'Person types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (12, 'Publication types maintenance','menu.entity.PublicationType', 'Publication types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (13, 'Race types maintenance','menu.entity.RaceType', 'Race types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (14, 'Reference types maintenance','menu.entity.ReferenceType', 'Reference types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (15, 'Tale types maintenance','menu.entity.TaleType', 'Tale types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (16, 'Technology types maintenance','menu.entity.TechnologyType', 'Technology types', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (17,'Home','', 'Home', '');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (18,'Static Data','', 'Static Data', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (19,'Configuration','', 'Configuration', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (20,'Research','', 'Research', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (21,'Data Class Association','', 'Data Class Association', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (22,'Menu Configuration','', 'Menu Configuration', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (23,'Reset Password','', 'Reset Password', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (24,'User Login Maintenance','', 'User Login Maintenance', 'CRUD');
INSERT INTO resdb_menu_option (id, name, data_object, default_menu_text, operation) VALUES (25,'System Parameters','', 'System Parameters', 'CRUD');

/********************************************************************************************************************************************
 ** CREATE DEFAULT MENU SYSTEM
 ********************************************************************************************************************************************/

insert into resdb_menu (name,owner_name,owner_type) values ('default', 'adrian', 'U');
insert into resdb_menu (name,owner_name,owner_type) values ('empty', 'adrian', 'U');


insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (1, 1, 1, 1, 17, 0, 'Home', 'Go to Home screen', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (2, 1, 1, 1, 18, 0, 'Static Data', 'Go to Static Data screen', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (3, 1, 1, 1, 19, 0, 'Configuration', 'Go to Configuration screen', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (4, 1, 1, 1, 20, 0, 'Research', 'Go to Research screen', 'n', 'n', 'n', 0);

-- static data sub-menu
insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (5, 1, 2, 1, 1, 2, 'Address Type', 'Address types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (6, 1, 2, 2, 2, 2, 'Artefact Artifact Types', 'Artefact attribute types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (7, 1, 2, 3, 3, 2, 'Artefact Types', 'Artefact types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (8, 1, 2, 4, 4, 2, 'Calendar Types', 'Calendar types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (9, 1, 2, 5, 5, 2, 'Deity Types', 'Deity types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (10, 1, 2, 6, 6, 2, 'Entity Types', 'Entity types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (11, 1, 2, 7, 7, 2, 'Event Types', 'Event types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (12, 1, 2, 8, 8, 2, 'Event Attribute Types', 'Event attribute types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (13, 1, 2, 9, 9, 2, 'Hierarchy Types', 'Hierarchy types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (14, 1, 2, 10, 10, 2, 'Measure Types', 'Measure types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (15, 1, 2, 11, 11, 2, 'Person Types', 'Person types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (16, 1, 2, 12, 12, 2, 'Publication Types', 'Publication types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (17, 1, 2, 13, 13, 2, 'Race Types', 'Race types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (18, 1, 2, 14, 14, 2, 'Reference Types', 'Reference types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (19, 1, 2, 15, 15, 2, 'Tale Types', 'Tale types maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (20, 1, 2, 16, 16, 2, 'Technology Types', 'Technology types maintenance', 'n', 'n', 'n', 0);

-- configuration sub-menu
insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (21, 1, 2, 1, 21, 2, 'Data Class Association', 'Data class association maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (22, 1, 2, 2, 22, 2, 'Menu Configuration', 'Menu configuration maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (23, 1, 2, 3, 24, 2, 'User Login Maintenance', 'User login maintenance', 'n', 'n', 'n', 0);

insert into resdb_menu_item (id, menu_id, menu_level, sequence_no, internal_menuoption_id, parent_menuitem_id, item_text, prompt, is_leaf_node, has_separator_before, has_separator_after, permission)
values (24, 1, 2, 4, 25, 2, 'System Parameters', 'System parameters', 'n', 'n', 'n', 0);




