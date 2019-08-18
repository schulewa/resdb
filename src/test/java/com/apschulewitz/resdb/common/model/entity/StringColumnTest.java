package com.apschulewitz.resdb.common.model.entity;

import com.apschulewitz.resdb.common.model.DataEntityException;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class StringColumnTest {

  @Test
  public void when_default_constructor_is_executed_then_return_StringColumn_with_data_type_set_to_STRING() {
    // Given

    // When
    StringColumn column = new StringColumn();

    // Then
    assertEquals("Default constructor data type", DataType.STRING, column.getType());
  }

  @Test
  public void when_default_constructor_is_executed_then_return_StringColumn_with_null_value() {
    // Given

    // When
    StringColumn column = new StringColumn();

    // Then
    assertTrue("StringColumn value", column.isNull());
  }

  @Test
  public void when_default_constructor_is_executed_and_set_value_then_return_StringColumn_with_non_null_value() {
    // Given
    StringColumn column = new StringColumn();

    // When
    column.setValue("StringColumn");

    // Then
    assertFalse("StringColumn value", column.isNull());
  }

  @Test
  public void when_default_constructor_is_executed_and_set_value_then_throw_exception() {
    // Given
    StringColumn column = new StringColumn();
    LocalDate now = LocalDate.now();
    boolean isExceptionThrown = false;

    // When
    try {
      column.setValue(now);
    } catch (DataEntityException e) {
      isExceptionThrown = true;
    }

    // Then
    assertTrue("StringColumn set value exception", isExceptionThrown);
  }

  @Test
  public void when_default_constructor_is_executed_and_set_null_value_then_return_StringColumn_with_null_value() {
    // Given
    StringColumn column = new StringColumn();

    // When
    column.setNull();

    // Then
    assertNull("StringColumn value", column.getValue());
  }

  @Test
  public void given_column_name_when_constructor_is_executed_then_return_StringColumn_with_data_type_and_name_set() {
    // Given
    String columnName = "First name";


    // When
    StringColumn column = new StringColumn(columnName);

    // Then
    assertEquals("Column data type", DataType.STRING, column.getType());
    assertEquals("Column name", columnName, column.getColumnName());
  }

  @Test
  public void given_column_name_when_constructor_is_executed_then_return_StringColumn_with_data_type_name_and_length_set() {
    // Given
    String columnName = "First name";
    int columnLength = 25;

    // When
    StringColumn column = new StringColumn(columnName, columnLength);

    // Then
    assertEquals("Column data type", DataType.STRING, column.getType());
    assertEquals("Column name", columnName, column.getColumnName());
    assertEquals("Column length", columnLength, column.getLength());
  }

}
