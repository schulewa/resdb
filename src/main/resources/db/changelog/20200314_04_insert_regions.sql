--liquibase formatted sql

--changeset author:schulewa

INSERT INTO resdb_region (name, status, created_by) VALUES ('Africa', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('Africa, Asia', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('Africa, Asia, Oceania', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('Asia', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('Asia, Europe', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('Australia', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('Caucasian', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('New Guinea', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('North America', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('South America', 'N', 'admin');
INSERT INTO resdb_region (name, status, created_by) VALUES ('World ?', 'N', 'admin');
