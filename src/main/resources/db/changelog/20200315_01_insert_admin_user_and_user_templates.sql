--liquibase formatted sql

--changeset author:schulewa

-- comment CREATE ADMIN USER

INSERT INTO resdb_user_account (logon_name, first_name, family_name, language_id, is_template, status, last_logon, invalid_logon_count, pwd_last_updated)
SELECT 'admin', 'Admin', 'Admin', id, 0, 'A', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM resdb_language WHERE iso_6391_code_1 = 'en';

INSERT INTO resdb_user_password (account_id, valid_from, valid_to, password) SELECT id, CURRENT_TIMESTAMP, null, 'admin' FROM resdb_user_account WHERE logon_name = 'admin';

INSERT INTO resdb_user_group (id, group_name, display_name, status) VALUES (1, 'ADMIN', 'Administration Group', 'A');

INSERT INTO resdb_user_group_membership (account_id, group_id, valid_from, valid_to) VALUES (1, 1, {ts '2000-01-01 09:00:00'}, null);


-- comment CREATE READ-ONLY TEMPLATE USER

INSERT INTO resdb_user_group (id, group_name, display_name, status) VALUES (2, 'DATA_ENTRY', 'Data Entry Group', 'A');

INSERT INTO resdb_user_account (id, logon_name, first_name, family_name, language_id, is_template, status, last_logon, invalid_logon_count, pwd_last_updated)
SELECT 2, 'READ_ONLY', 'READ_ONLY', 'READ_ONLY', id, 1, 'A', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM resdb_language WHERE iso_6391_code_1 = 'en';

INSERT INTO resdb_user_group_membership (account_id, group_id, valid_from, valid_to) VALUES (2, 2, {ts '2000-01-01 09:00:00'}, null);


-- comment CREATE READ-WRITE TEMPLATE USER

INSERT INTO resdb_user_group (id, group_name, display_name, status) VALUES (3, 'DATA_READWRITE', 'Data Read-Write Group', 'A');

INSERT INTO resdb_user_account (logon_name, first_name, family_name, language_id, is_template, status, last_logon, invalid_logon_count, pwd_last_updated)
SELECT 'READ_', 'READ_WRITE', 'READ_WRITE', id, 1, 'A', CURRENT_TIMESTAMP, 0, CURRENT_TIMESTAMP FROM resdb_language WHERE iso_6391_code_1 = 'en';

INSERT INTO resdb_user_group_membership (account_id, group_id, valid_from, valid_to) VALUES (3, 3, {ts '2000-01-01 09:00:00'}, null);

