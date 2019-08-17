package com.apschulewitz.resdb.common.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DataTypeTest {

  @Test
  public void given_BLOB_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.BLOB;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("BLOB", isValid);
  }

  @Test
  public void given_CLOB_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.CLOB;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("CLOB", isValid);
  }

  @Test
  public void given_DATE_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.DATE;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("DATE", isValid);
  }

  @Test
  public void given_DATETIME_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.DATETIME;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("DATETIME", isValid);
  }

  @Test
  public void given_DOUBLE_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.DOUBLE;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("DOUBLE", isValid);
  }

  @Test
  public void given_FLOAT_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.FLOAT;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("FLOAT", isValid);
  }

  @Test
  public void given_INTEGER_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.INTEGER;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("INTEGER", isValid);
  }

  @Test
  public void given_LONG_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.LONG;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("LONG", isValid);
  }

  @Test
  public void given_SHORT_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.SHORT;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("SHORT", isValid);
  }

  @Test
  public void given_STRING_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.STRING;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("STRING", isValid);
  }

  @Test
  public void given_TIME_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.TIME;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("TIME", isValid);
  }

  @Test
  public void given_TIMESTAMP_data_type_when_isValidDataType_is_executed_then_return_true() {
    // Given
    DataType dataType = DataType.TIMESTAMP;

    // When
    boolean isValid = DataType.isValidDataType(dataType);

    // Then
    assertTrue("TIMESTAMP", isValid);
  }
}
