--liquibase formatted sql

--changeset author:schulewa

--comment DATA_MAINTENANCE PERMISSIONS

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ADDRESS_TYPE-CRUD', 'Address types maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ADDRESS_TYPE-CRU', 'Address types maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ADDRESS_TYPE-R', 'Address types maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT-CRUD', 'Artefact maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT-CRU', 'Artefact maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT-R', 'Artefact maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_ATTRIBUTE-CRUD', 'Artefact attributes maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_ATTRIBUTE-CRU', 'Artefact attributes maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_ATTRIBUTE-R', 'Artefact attributes maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_ATTRIBUTE_TYPE-CRUD', 'Artefact attribute types maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_ATTRIBUTE_TYPE-CRU', 'Artefact attribute types maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_ATTRIBUTE_TYPE-R', 'Artefact attribute types maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_GROUP-CRUD', 'Artefact group maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_GROUP-CRU', 'Artefact group maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_GROUP-R', 'Artefact group maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_IMAGE-CRUD', 'Artefact image maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_IMAGE-CRU', 'Artefact image maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_IMAGE-R', 'Artefact image maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_IMG_PATTERN_SMBL-CRUD', 'Artefact image pattern symbol maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_IMG_PATTERN_SMBL-CRU', 'Artefact image pattern symbol maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_IMG_PATTERN_SMBL-R', 'Artefact image pattern symbol maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_PLACE-CRUD', 'Artefact place maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_PLACE-CRU', 'Artefact place maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_PLACE-R', 'Artefact place maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_RSTRTN_WORK-CRUD', 'Artefact restoration work maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_RSTRTN_WORK-CRU', 'Artefact restoration work maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_RSTRTN_WORK-R', 'Artefact restoration work maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_TYPE-CRUD', 'Artefact types maintenance(Create/Read//Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_TYPE-CRU', 'Artefact types maintenance(Create/Read//Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ARTEFACT_TYPE-R', 'Artefact types maintenance(Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR-CRUD', 'Calendar maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR-CRU', 'Calendar maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR-R', 'Calendar maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR_PLACE-CRUD', 'Calendar place maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR_PLACE-CRU', 'Calendar place maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR_PLACE-R', 'Calendar place maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR_RACE-CRUD', 'Calendar race maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR_RACE-CRU', 'Calendar race maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR_RACE-R', 'Calendar race maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR_TYPE-CRUD', 'Calendar types maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR_TYPE-CRU', 'Calendar types maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('CALENDAR_TYPE-R', 'Calendar types maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('COUNTRY-CRUD', 'Country maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('COUNTRY-CRU', 'Country maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('COUNTRY-R', 'Country maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('COUNTRY_REGION-CRUD', 'Country region maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('COUNTRY_REGION-CRU', 'Country region maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('COUNTRY_REGION-R', 'Country region maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY-CRUD', 'Deity maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY-CRU', 'Deity maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY-R', 'Deity maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission ( name, description, status, operation_type) VALUES ('DEITY_ALIAS-C', 'Deity alias maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY_ALIAS-U', 'Deity alias maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY_ALIAS-R', 'Deity alias maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY_RELIGION-C', 'Deity religion maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY_RELIGION-U', 'Deity religion maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY_RELIGION-R', 'Deity religion maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY_TYPE-CRUD', 'Deity types maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY_TYPE-CRU', 'Deity types maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DEITY_TYPE-R', 'Deity types maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DROPDOWN_CLASS-C', 'Dropdown class maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DROPDOWN_CLASS-U', 'Dropdown class maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DROPDOWN_CLASS-R', 'Dropdown class maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DROPDOWN_DOMAIN-C', 'Dropdown domain maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DROPDOWN_DOMAIN-U', 'Dropdown domain maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('DROPDOWN_DOIMAIN-R', 'Dropdown domain maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ENTITY-CRUD', 'Entity maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ENTITY-CRU', 'Entity maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ENTITY-R', 'Entity maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ENTITY_ADDRESS-C', 'Entity address maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ENTITY_ADDRESS-U', 'Entity address maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ENTITY_ADDRESS-R', 'Entity address maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ENTITY_TYPE-CRUD', 'Entity types maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ENTITY_TYPE-CRU', 'Entity types maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('ENTITY_TYPE-R', 'Entity types maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT-C', 'Event maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT-U', 'Event maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT-R', 'Event  maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_ATTRIBUTE-C', 'Event attribute maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_ATTRIBUTE-U', 'Event attribute maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_ATTRIBUTE-R', 'Event attribute maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_ATTRIBUTE_TYPE-CRUD', 'Event attribute types maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_ATTRIBUTE_TYPE-CRU', 'Event attribute types maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_ATTRIBUTE_TYPE-R', 'Event attribute types maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_PERSON-C', 'Event person maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_PERSON-U', 'Event person maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_PERSON-R', 'Event person maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_TYPE-CRUD', 'Event types maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_TYPE-CRU', 'Event types maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_TYPE-R', 'Event types maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_TYPE_GROUP-C', 'Event type group maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_TYPE_GROUP-U', 'Event type group maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('EVENT_TYPE_GROUP-R', 'Event type group maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE-CRUD', 'Image maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE-CRU', 'Image maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE-R', 'Image maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE_COLLECTION-CRUD', 'Image collection maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE_COLLECTION-CRU', 'Image collection maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE_COLLECTION-R', 'Image collection maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE_COLLECTION_SEQ-C', 'Image collection sequence maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE_COLLECTION_SEQ-U', 'Image collection sequence maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE_COLLECTION_SEQ-R', 'Image collection sequence maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE_TYPE-C', 'Image type maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE_TYPE-U', 'Image type maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('IMAGE_TYPE-R', 'Image type maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE-CRUD', 'Language maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE-CRU', 'Language maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE-R', 'Language maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE_GROUP-CRUD', 'Language group maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE_GROUP-CRU', 'Language group maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE_GROUP-R', 'Language group maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE_LOCATION-CRUD', 'Language location maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE_LOCATION-CRU', 'Language location maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE_LOCATION-R', 'Language location maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE_PATTERN_SYMBOL-C', 'Language pattern symbol maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE_PATTERN_SYMBOL-U', 'Language pattern symbol maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LANGUAGE_PATTERN_SYMBOL-R', 'Language pattern symbol maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LIBRARY_LOCATION-C', 'Library location maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LIBRARY_LOCATION-U', 'Library location maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('LIBRARY_LOCATION-R', 'Library location maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MATERIAL-CRUD', 'Material maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MATERIAL-CRU', 'Material maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MATERIAL-R', 'Material maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MATERIAL_TYPE-C', 'Material type maintenance (Create/Read/Update/Delete)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MATERIAL_TYPE-U', 'Material type maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MATERIAL_TYPE-R', 'Material type maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MEASURE-CRUD', 'Material maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MEASURE-CRU', 'Material maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MEASURE-R', 'Material maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MEASURE_CONVERTOR-C', 'Measure convertor maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MEASURE_CONVERTOR-U', 'Measure convertor maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MEASURE_CONVERTOR-R', 'Measure convertor maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MEASURE_TYPE-CRUD', 'Measure types maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MEASURE_TYPE-CRU', 'Measure types maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('MEASURE_TYPE-R', 'Measure types maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('OTHER_ADDRESS-C', 'Other address maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('OTHER_ADDRESS-U', 'Other address maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('OTHER_ADDRESS-R', 'Other address maintenance (Read', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PATTERN_SYMBOL-CRUD', 'Pattern symbol maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PATTERN_SYMBOL-CRU', 'Pattern symbol maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PATTERN_SYMBOL-R', 'Pattern symbol maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERIOD-CRUD', 'Period maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERIOD-CRU', 'Period maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERIOD-R', 'Period maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON-CRUD', 'Person maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON-CRU', 'Person maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON-R', 'Person maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_ALIAS-C', 'Person alias maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_ALIAS-U', 'Person alias maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_ALIAS-R', 'Person alias maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_DEFINITION-C', 'Person definition maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_DEFINITION-U', 'Person definition maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_DEFINITION-R', 'Person definition maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_ROLE-C', 'Person role maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_ROLE-U', 'Person role maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_ROLE-R', 'Person role maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_TYPE-C', 'Person type maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_TYPE-U', 'Person type maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PERSON_TYPE-R', 'Person type maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PLACE-CRUD', 'Person maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PLACE-CRU', 'Person maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PLACE-R', 'Person maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PLACE_ALIAS-C', 'Place alias maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PLACE_ALIAS-U', 'Place alias maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PLACE_ALIAS-R', 'Place alias maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('POSTAL_ADDRESS-C', 'Postal address maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('POSTAL_ADDRESS-U', 'Postal address maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('POSTAL_ADDRESS-R', 'Postal address maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PUBLICATION-CRUD', 'Publication maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PUBLICATION-CRU', 'Publication maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PUBLICATION-R', 'Publication maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PUBLICATION_ROLE-CRUD', 'Publication role maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PUBLICATION_ROLE-CRU', 'Publication role maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PUBLICATION_ROLE-R', 'Publication role maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PUBLICATION_TYPE-C', 'Publication type maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PUBLICATION_TYPE-U', 'Publication type maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('PUBLICATION_TYPE-R', 'Publication type maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE-CRUD', 'Race maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE-CRU', 'Race maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE-R', 'Race maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE_ALIAS-CRUD', 'Race alias maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE_ALIAS-CRU', 'Race alias maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE_ALIAS-R', 'Race alias maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE_PLACE-CRUD', 'Race place maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE_PLACE-CRU', 'Race place maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE_PLACE-R', 'Race place maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE_TYPE-C', 'Race type maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE_TYPE-U', 'Race type maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RACE_TYPE-R', 'Race type maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('REGION-CRUD', 'Region maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('REGION-CRU', 'Region maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('REGION-R', 'Region maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RELIGION-CRUD', 'Regliion maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RELIGION-CRU', 'Religion maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RELIGION-R', 'Religion maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RIVER-CRUD', 'River maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RIVER-CRU', 'River maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RIVER-R', 'River maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RIVER_ALIAS-CRUD', 'River alias maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RIVER_ALIAS-CRU', 'River alias maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('RIVER_ALIAS-R', 'River alias maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TALE-CRUD', 'Tale maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TALE-CRU', 'Tale maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TALE-R', 'Tale maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TALE_TYPE-C', 'Tale maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TALE_TYPE-U', 'Tale maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TALE_TYPE-R', 'Tale maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TECHNOLOGY-CRUD', 'Technology maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TECHNOLOGY-CRU', 'Technology maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TECHNOLOGY-R', 'Technology maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TECHNOLOGY_TYPE-C', 'Technology type maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TECHNOLOGY_TYPE-U', 'Technology type maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TECHNOLOGY_TYPE-R', 'Technology type maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TECHNOLOGY_TYPE_GROUP-C', 'Technology type group maintenance (Create/Read/Update/Delete)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TECHNOLOGY_TYPE_GROUP-U', 'Technology type group maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TECHNOLOGY_TYPE_GROUP-R', 'Technology type group maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('THEORY-CRUD', 'Theory maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('THEORY-CRU', 'Theory maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('THEORY-R', 'Theory maintenance (Read)', 'A', 'R');

INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TITLE-CRUD', 'Title maintenance (Create/Read/Update/Delete)', 'A', 'CRUD');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TITLE-CRU', 'Title maintenance (Create/Read/Update)', 'A', 'CRU');
INSERT INTO resdb_permission (name, description, status, operation_type) VALUES ('TITLE-R', 'Title maintenance (Read)', 'A', 'R');
