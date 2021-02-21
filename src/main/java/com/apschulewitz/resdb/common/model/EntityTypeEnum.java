package com.apschulewitz.resdb.common.model;

public enum EntityTypeEnum {

  ADDRESS_TYPE("AddressType"),
  ARTEFACT_GROUP("ArtefactGroup"),
  ARTEFACT_TYPE("ArtefactType"),
  AUTHENTICATION_CONFIGURATION("AuthenticationConfiguration"),
  CALENDAR_TYPE("CalendarType"),
  CLASSIFICATION_COLLECTION("ClassificationCollection"),
  COUNTRY("COUNTRY"),
  DEITY_TYPE("DeityType"),
  ENTITY_TYPE("EntityType"),
  EVENT_TYPE("EventType"),
  EVENT_TYPE_GROUP("EventTypeGroup"),
  HIERARCHY_TYPE("HierarchyType"),
  IMAGE_TYPE("ImageType"),
  LANGUAGE("Language"),
  LANGUAGE_GROUP("LanguageGroup"),
  MEASURE_TYPE("MeasureType"),
  PERSON("Person"),
  PERSON_TYPE("PersonType"),
  PLACE("Place"),
  PUBLICATION_TYPE("PublicationType"),
  RACE_TYPE("RaceType"),
  REFERENCE_TYPE("ReferenceType"),
  REGION("Region"),
  TALE_TYPE("TaleType"),
  TECHNOLOGY_TYPE_GROUP("TechnologyTypeGroup"),
  TITLE("Title"),
  USER_ACCOUNT("UserAccount");

  private final String type;

  EntityTypeEnum(String type) {
    this.type = type;
  }

  public final String getType() {
    return type;
  }
}
