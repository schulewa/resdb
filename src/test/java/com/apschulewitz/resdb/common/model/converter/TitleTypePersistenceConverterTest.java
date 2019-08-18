package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.TitleType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TitleTypePersistenceConverterTest {

  private TitleTypePersistenceConverter converter = new TitleTypePersistenceConverter();

  @Test
  public void given_valid_TitleType_when_convertToDatabaseColumn_is_executed_then_return_String() {
    // Given
    TitleType entityValue = TitleType.Prefix;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertEquals("Database value", TitleType.Prefix.getCode(), databaseValue);
  }

  @Test
  public void given_null_TitleType_when_convertToDatabaseColumn_is_executed_then_return_null() {
    // Given
    TitleType entityValue = null;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertNull("Database null value", databaseValue);
  }

  @Test
  public void given_valid_code_when_convertToEntityAttribute_is_executed_then_return_TitleType() {
    // Given
    String code = TitleType.Prefix.getCode();

    // When
    TitleType entityValue = converter.convertToEntityAttribute(code);

    // Then
    assertEquals("Entity value", code, entityValue.getCode());
  }

  @Test
  public void given_null_code_when_convertToEntityAttribute_is_executed_then_return_null() {
    // Given
    String code = null;

    // When
    TitleType entityValue = converter.convertToEntityAttribute(code);

    // Then
    assertNull("Entity null value", entityValue);
  }

}
