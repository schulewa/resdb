package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.MenuOwnerType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MenuOwnerTypePersistenceConverterTest {

  private MenuOwnerTypePersistenceConverter converter = new MenuOwnerTypePersistenceConverter();

  @Test
  public void given_valid_MenuOwnerType_when_convertToDatabaseColumn_is_executed_then_return_String() {
    // Given
    MenuOwnerType entityValue = MenuOwnerType.GROUP;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertEquals("Database value", entityValue.getCode(), databaseValue);
  }

  @Test
  public void given_null_MenuOwnerType_when_convertToDatabaseColumn_is_executed_then_return_null() {
    // Given
    MenuOwnerType entityValue = null;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertNull("Database null value", databaseValue);
  }

  @Test
  public void given_valid_code_when_convertToEntityAttribute_is_executed_then_return_MenuOwnerType() {
    // Given
    String code = "G";

    // When
    MenuOwnerType entityValue = converter.convertToEntityAttribute(code);

    // Then
    assertEquals("Entity value", MenuOwnerType.GROUP, entityValue);
  }

  @Test
  public void given_null_code_when_convertToEntityAttribute_is_executed_then_return_null() {
    // Given
    String code = null;

    // When
    MenuOwnerType entityValue = converter.convertToEntityAttribute(code);

    // Then
    assertNull("Entity null value", entityValue);
  }

}
