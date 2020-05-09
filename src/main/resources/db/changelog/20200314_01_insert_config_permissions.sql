--liquibase formatted sql

--changeset author:schulewa

--comment Set up configuration permissions
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS-CRUD', 'Data class maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS-CRU', 'Data class maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS-R', 'Data class maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS_ASSOCIATION-CRUD', 'Data class association maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS_ASSOCIATION-CRU', 'Data class association maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS_ASSOCIATION-R', 'Data class association maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS_METHOD-CRUD', 'Data class method maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS_METHOD-CRU', 'Data class method maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS_METHOD-R', 'Data class method maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS_METHOD_ARGUMENT-CRUD', 'Data class method argument maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS_METHOD_ARGUMENT-CRU', 'Data class method argument maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DATA_CLASS_METHOD_ARGUMENT-R', 'Data class method argument maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU-CRUD', 'Menu maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU-CRU', 'Menu maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU-R', 'Menu maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_CONFIGURATION', 'Configuration menu (Access)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_CONFIGURATION-CRUD', 'Menu configuration maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_CONFIGURATION-CRU', 'Menu configuration maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_CONFIGURATION-R', 'Menu configuration configuration maintenance (Read)', 'N', 'C');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_ITEM-CRUD', 'Menu item maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_ITEM-CRU', 'Menu item maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_ITEM-R', 'Menu item maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_OPTION-CRUD', 'Menu option maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_OPTION-CRU', 'Menu option maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_OPTION-R', 'Menu option maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_RESEARCH', 'Research menu (Access)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_STATIC_DATA', 'Static Data menu (Access)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MENU_TIME', 'Time menu (Access)', 'N', 'R');

--comment USER_ADMIN PERMISSIONS
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERMISSION-CRUD', 'Permissions maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERMISSION-CRU', 'Permissions maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERMISSION-R', 'Permissions maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RESET_PASSWORD', 'Reset Password', 'N', 'U');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('USER_ACCOUNT_MAINTENANCE-CRUD', 'User account maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('USER_ACCOUNT_MAINTENANCE-CRU', 'User account maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('USER_ACCOUNT_MAINTENANCE-R', 'User account maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('USER_GROUP_MAINTENANCE-CRUD', 'User group maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('USER_GROUP_MAINTENANCE-CRU', 'User group maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('USER_GROUP_MAINTENANCE-R', 'User group maintenance (Read)', 'N', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('USER_PASSWORD_MAINTENANCE-CRUD', 'User password maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('USER_PASSWORD_MAINTENANCE-CRU', 'User password maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('USER_PASSWORD_MAINTENANCE-R', 'User password maintenance (Read)', 'N', 'R');

--comment SYSTEM_ADMIN PERMISSIONS
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('SYSTEM_PARAMETERS-C', 'System parameter maintenance (Create/Read/Update/Delete)', 'N', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('SYSTEM_PARAMETERS-U', 'System parameter maintenance (Create/Read/Update)', 'N', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('SYSTEM_PARAMETERS-R', 'System parameter maintenance (Create)', 'N', 'R');
