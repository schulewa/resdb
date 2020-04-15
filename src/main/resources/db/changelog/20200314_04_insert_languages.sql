--liquibase formatted sql

--changeset author:schulewa

INSERT INTO resdb_language (id, iso_6391_code, iso_6392_code_alpha_3b, iso_6392_code_alpha_3t, iso_6392_code_alpha_2, name, language_group_id) VALUES (1, 'en-GB', 'eng', 'eng', 'uk', 'English (UK)', 1);

INSERT INTO resdb_language_group (id, name, status, created_by, last_updated, updated_by) VALUES (1, 'Indo-European', 'A', 'admin', CURRENT_TIMESTAMP, 'adrian');
