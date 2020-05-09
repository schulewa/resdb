--liquibase formatted sql

--changeset author:schulewa

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

