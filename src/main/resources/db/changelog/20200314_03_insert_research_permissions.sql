--liquibase formatted sql

--changeset author:schulewa

--comment RESEARCH PERMISSIONS
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('BIBLIOGRAPHY-CRUD', 'Bibliography maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('BIBLIOGRAPHY-CRU', 'Bibliography maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('BIBLIOGRAPHY-R', 'Bibliography maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('BIBLIOGRAPHY_PUBLICATION-CRUD', 'Bibliography publication maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('BIBLIOGRAPHY_PUBLICATION-CRU', 'Bibliography publication maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('BIBLIOGRAPHY_PUBLICATION-R', 'Bibliography publication maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('HIERARCHY-CRUD', 'Hierarchy maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('HIERARCHY-CRU', 'Hierarchy maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('HIERARCHY-R', 'Hierarchy maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('HIERARCHY_NODE-C', 'Hierarchy node maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('HIERARCHY_NODE-U', 'Hierarchy node maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('HIERARCHY_NODE-R', 'Hierarchy node maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('HIERARCHY_TYPE-C', 'Hierarchy type maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('HIERARCHY_TYPE-U', 'Hierarchy type maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('HIERARCHY_TYPE-R', 'Hierarchy type maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('REFERENCE-CRUD', 'Reference maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('REFERENCE-CRU', 'Reference maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('REFERENCE-R', 'Reference maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('REFERENCE_TYPE-C', 'Reference type maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('REFERENCE_TYPE-U', 'Reference type maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('REFERENCE_TYPE-R', 'Reference type maintenance (Read)', 'N', 'R');
