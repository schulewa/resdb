
/********************************************************************************************************************************************/
/********************************************************************************************************************************************/
-- CREATE STANDARD USERS AND GROUPS
DELETE FROM resdb_user_account;
INSERT INTO resdb_user_account (id, logon_name, first_name, family_name, language_id, status, last_logon, invalid_logon_count, pwd_last_updated) VALUES (1, 'admin', 'Admin', 'Admin', 1, 'A', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP);

DELETE FROM resdb_user_group;
INSERT INTO resdb_user_group (id, group_name, display_name, status) VALUES (1, 'ADMIN', 'Administration Group', 'A');
INSERT INTO resdb_user_group (id, group_name, display_name, status) VALUES (2, 'DATA_ENTRY', 'Data Entry Group', 'A');
INSERT INTO resdb_user_group (id, group_name, display_name, status) VALUES (3, 'DATA_READONLY', 'Data Read-Only Group', 'A');

DELETE FROM resdb_language;
INSERT INTO resdb_language (id, name) VALUES (1, 'English (UK)');

DELETE FROM resdb_user_group_membership;
INSERT INTO resdb_user_group_membership (id, account_id, group_id, valid_from, valid_to) VALUES (1, 1, 1, TIMESTAMP('2000-01-01 09:00:00'), null);

-- groups: 1=ADMIN, 2=DATA_ENTRY, 3=DATA_READONLY
DELETE FROM resdb_user_group_permission;
INSERT INTO resdb_user_group_permission (group_id, permission_id) SELECT g.id, p.id FROM resdb_permission p , resdb_user_group g  WHERE g.group_name = 'ADMIN' AND p.operation_type = 'CRUD';
INSERT INTO resdb_user_group_permission (group_id, permission_id) SELECT g.id, p.id FROM resdb_permission p , resdb_user_group g  WHERE g.group_name = 'DATA_ENTRY' AND p.operation_type = 'CRUD';
INSERT INTO resdb_user_group_permission (group_id, permission_id) SELECT g.id, p.id FROM resdb_permission p , resdb_user_group g  WHERE g.group_name = 'DATA_READONLY' AND p.operation_type = 'R' AND p.name LIKE '%-R';

