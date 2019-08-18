package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.Gender;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GenderPersistenceConverterTest {

  private GenderPersistenceConverter converter = new GenderPersistenceConverter();

  @Test
  public void given_valid_Gender_when_convertToDatabaseColumn_is_executed_then_return_String() {
    // Given
    Gender entityValue = Gender.Male;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertEquals("Database value", Gender.Male.getCode(), databaseValue);
  }

  @Test
  public void given_null_Gender_when_convertToDatabaseColumn_is_executed_then_return_null() {
    // Given
    Gender entityValue = null;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertNull("Database null value", databaseValue);
  }

  @Test
  public void given_valid_code_when_convertToEntityAttribute_is_executed_then_return_Gender() {
    // Given
    String code = Gender.Male.getCode();

    // When
    Gender entityValue = converter.convertToEntityAttribute(code);

    // Then
    assertEquals("Entity value", Gender.Male, entityValue);
  }

  @Test
  public void given_null_code_when_convertToEntityAttribute_is_executed_then_return_null() {
    // Given
    String code = null;

    // When
    Gender entityValue = converter.convertToEntityAttribute(code);

    // Then
    assertNull("Entity null value", entityValue);
  }

}
