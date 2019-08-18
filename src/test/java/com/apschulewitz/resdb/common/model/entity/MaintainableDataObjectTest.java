package com.apschulewitz.resdb.common.model.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaintainableDataObjectTest {

  @Test
  public void given_AddressType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.AddressType");

    // Then
    assertNotNull("AddressType identifier", maintainableDataObject);
  }

  @Test
  public void given_Artefact_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.Artefact");

    // Then
    assertNotNull("Artefact identifier", maintainableDataObject);
  }

  @Test
  public void given_ArtefactAttribute_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.ArtefactAttribute");

    // Then
    assertNotNull("ArtefactAttribute identifier", maintainableDataObject);
  }

  @Test
  public void given_ArtefactAttributeType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.ArtefactAttributeType");

    // Then
    assertNotNull("ArtefactAttributeType identifier", maintainableDataObject);
  }

  @Test
  public void given_ArtefactGroup_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.ArtefactGroup");

    // Then
    assertNotNull("ArtefactGroup identifier", maintainableDataObject);
  }

  @Test
  public void given_ArtefactImage_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.ArtefactImage");

    // Then
    assertNotNull("ArtefactImage identifier", maintainableDataObject);
  }

  @Test
  public void given_ArtefactImagePatternSymbol_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.ArtefactImagePatternSymbol");

    // Then
    assertNotNull("ArtefactImagePatternSymbol identifier", maintainableDataObject);
  }

  @Test
  public void given_ArtefactPlace_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.ArtefactPlace");

    // Then
    assertNotNull("ArtefactPlace identifier", maintainableDataObject);
  }

  @Test
  public void given_ArtefactType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.ArtefactType");

    // Then
    assertNotNull("ArtefactType identifier", maintainableDataObject);
  }

  @Test
  public void given_CalendarType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.CalendarType");

    // Then
    assertNotNull("CalendarType identifier", maintainableDataObject);
  }

  @Test
  public void given_DeityType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.DeityType");

    // Then
    assertNotNull("DeityType identifier", maintainableDataObject);
  }

  @Test
  public void given_EntityType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.EntityType");

    // Then
    assertNotNull("EntityType identifier", maintainableDataObject);
  }

  @Test
  public void given_EventType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.EventType");

    // Then
    assertNotNull("EventType identifier", maintainableDataObject);
  }

  @Test
  public void given_EventAttributeType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.EventAttributeType");

    // Then
    assertNotNull("EventAttributeType identifier", maintainableDataObject);
  }

  @Test
  public void given_HierarchyType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.HierarchyType");

    // Then
    assertNotNull("HierarchyType identifier", maintainableDataObject);
  }

  @Test
  public void given_MeasureType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.MeasureType");

    // Then
    assertNotNull("MeasureType identifier", maintainableDataObject);
  }

  @Test
  public void given_PersonType_identifier_when_getFor_is_executed_then_return_MaintainableDataObject() {
    // When
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor("menu.entity.PersonType");

    // Then
    assertNotNull("PersonType identifier", maintainableDataObject);
  }

  @Test
  public void given_invalid_identifier_when_getFor_is_executed_then_throw_exception() {
    // Given
    String invalidValue = "XXX";
    boolean isExceptionThrown = false;

    // When
    try {
      MaintainableDataObject.getFor(invalidValue);
    } catch (IllegalArgumentException e) {
      isExceptionThrown = true;
    }

    // Then
    assertTrue("Invalid identifier", isExceptionThrown);
  }

  @Test
  public void given_valid_identifier_when_getDataObjectIdentifier_is_executed_then_return_identifier() {
    // Given
    String identifier = "menu.entity.PersonType";
    MaintainableDataObject maintainableDataObject = MaintainableDataObject.getFor(identifier);

    // When
    String retrievedIdentifier = maintainableDataObject.getDataObjectIdentifier();

    // Then
    assertEquals("Retrieved identifier", identifier, retrievedIdentifier);
  }


}
