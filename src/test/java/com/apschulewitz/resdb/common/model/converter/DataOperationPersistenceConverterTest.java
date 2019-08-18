package com.apschulewitz.resdb.common.model.converter;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DataOperationPersistenceConverterTest {

  private DataOperationPersistenceConverter converter = new DataOperationPersistenceConverter();

  @Test
  public void given_valid_DataOperation_when_convertToDatabaseColumn_is_executed_then_return_code() {
    // Given
    DataOperation entityValue = DataOperation.CREATE;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertEquals("Database value", DataOperation.CREATE.getCode(), databaseValue);
  }

  @Test
  public void given_null_DataOperation_when_convertToDatabaseColumn_is_executed_then_return_null() {
    // Given
    DataOperation entityValue = null;

    // When
    String databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertNull("Database null value", databaseValue);
  }

  @Test
  public void given_valid_code_when_convertToEntityAttribute_is_executed_then_return_DataOperation() {
    // Given
    String code = "I";

    // When
    DataOperation entityValue = converter.convertToEntityAttribute(code);

    // Then
    assertEquals("Entity value", DataOperation.CREATE, entityValue);
  }

  @Test
  public void given_null_code_when_convertToEntityAttribute_is_executed_then_return_null() {
    // Given
    String code = null;

    // When
    DataOperation entityValue = converter.convertToEntityAttribute(code);

    // Then
    assertNull("Entity null value", entityValue);
  }

}
