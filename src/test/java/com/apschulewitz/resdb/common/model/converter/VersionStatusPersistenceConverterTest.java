package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import cucumber.api.java.tr.Ve;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class VersionStatusPersistenceConverterTest {

  private VersionStatusPersistenceConverter converter = new VersionStatusPersistenceConverter();

  @Test
  public void given_valid_VersionStatus_when_convertToDatabaseColumn_is_executed_then_return_code() {
    // Given
    VersionStatus entityValue = VersionStatus.New;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertEquals("Database value", VersionStatus.New.getCode(), databaseValue);
  }

  @Test
  public void given_null_VersionStatus_when_convertToDatabaseColumn_is_executed_then_return_null() {
    // Given
    VersionStatus entityValue = null;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertNull("Database null value", databaseValue);
  }

  @Test
  public void given_valid_code_when_convertToEntityAttribute_is_executed_then_return_VersionStatus() {
    // Given
    String databaseValue = VersionStatus.Cancel.getCode();

    // When
    VersionStatus entityValue = converter.convertToEntityAttribute(databaseValue);

    // Then
    assertEquals("Entity value", VersionStatus.Cancel, entityValue);
  }

  @Test
  public void given_null_code_when_convertToEntityAttribute_is_executed_then_return_null() {
    // Given
    String databaseValue = null;

    // When
    VersionStatus entityValue = converter.convertToEntityAttribute(databaseValue);

    // Then
    assertNull("Entity null value", entityValue);
  }

}
