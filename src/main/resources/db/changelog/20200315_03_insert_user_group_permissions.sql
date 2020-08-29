--liquibase formatted sql

--changeset author:schulewa

--comment CREATE USER GROUP PERMISSIONS FOR ADMIN USER

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
