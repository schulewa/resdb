package com.apschulewitz.resdb.common.model.entity;

import com.apschulewitz.resdb.common.InvalidValueException;
import com.apschulewitz.resdb.common.model.DataEntityException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class DateColumnTest {

  @Test
  public void when_default_constructor_is_executed_then_return_DateColumn_with_data_type_set_to_DATE() {
    // Given

    // When
    DateColumn column = new DateColumn();

    // Then
    assertEquals("Default constructor data type", DataType.DATE, column.getType());
  }

  @Test
  public void when_default_constructor_is_executed_then_return_DateColumn_with_null_value() {
    // Given

    // When
    DateColumn column = new DateColumn();

    // Then
    assertTrue("DateColumn value", column.isNull());
  }

  @Test
  public void when_default_constructor_is_executed_and_set_value_then_return_DateColumn_with_non_null_value() {
    // Given
    DateColumn column = new DateColumn();

    // When
    column.setValue(2019, 8, 18);

    // Then
    assertFalse("DateColumn value", column.isNull());
  }

  @Test
  public void when_default_constructor_is_executed_and_set_value_without_calendar_value_then_throw_exception() {
    // Given
    DateColumn column = new DateColumn();
    BigDecimal nonCalendarValue = new BigDecimal("1000.00");
    boolean isExceptionThrown = false;

    // When
    try {
      column.setValue(nonCalendarValue);
    } catch (DataEntityException e) {
      isExceptionThrown = true;
    }

    // Then
    assertTrue("DateColumn exception", isExceptionThrown);
  }

  @Test
  public void when_default_constructor_is_executed_and_set_value_with_calendar_value_then_value_is_set() {
    // Given
    DateColumn column = new DateColumn();
    Calendar calendarValue = new GregorianCalendar();

    // When
    column.setValue(calendarValue);

    // Then
    assertNotNull("DateColumn calendar not null", column.getValue());
  }

  @Test
  public void when_default_constructor_is_executed_and_set_null_value_then_return_DateColumn_with_null_value() {
    // Given
    DateColumn column = new DateColumn();

    // When
    column.setNull();

    // Then
    assertNull("DateColumn value", column.getValue());
  }

  @Test
  public void given_column_name_when_constructor_is_executed_then_return_DateColumn_with_data_type_and_name_set() {
    // Given
    String columnName = "Date of birth";


    // When
    DateColumn column = new DateColumn(columnName);

    // Then
    assertEquals("Column data type", DataType.DATE, column.getType());
    assertEquals("Column name", columnName, column.getColumnName());
  }

  @Test
  public void given_column_length_when_setColumnLength_is_executed_then_exception_is_thrown() {
    // Given
    int columnLength = 20;
    DateColumn column = new DateColumn();
    boolean isUnsupportedOperationException = false;

    // When
    try {
      column.setColumnLength(columnLength);
    } catch (UnsupportedOperationException e) {
      isUnsupportedOperationException = true;
    }

    // Then
    assertTrue("DateColumn length", isUnsupportedOperationException);
  }

  @Test
  public void given_column_calendar_value_when_setValue_is_executed_then_value_is_set() {
    // Given
    GregorianCalendar value = new GregorianCalendar();
    DateColumn column = new DateColumn();

    // When
    column.setValue(value);

    // Then
    assertEquals("DateColumn value", value, column.getValue());
  }

  @Test
  public void given_column_year_month_day_value_when_setValue_is_executed_then_value_is_set() {
    // Given
    int year = 2019;
    int month = Calendar.AUGUST;
    int day = 18;
    DateColumn column = new DateColumn();

    // When
    column.setValue(year, month, day);

    // Then
    Calendar value = null;
    if (column.getValue() instanceof Calendar) {
      value = (Calendar) column.getValue();
    }
    assertNotNull("DateColumn calendar value", value);
    assertEquals("DateColumn year", year, value.get(Calendar.YEAR));
    assertEquals("DateColumn month", month, value.get(Calendar.MONTH));
    assertEquals("DateColumn day", day, value.get(Calendar.DAY_OF_MONTH));
  }

  @Test
  public void given_column_year_month_day_value_when_setValue_is_executed_again_then_value_is_set() {
    // Given
    int year = 2019;
    int yearPlus1 = year + 1;
    int month = Calendar.AUGUST;
    int day = 18;
    DateColumn column = new DateColumn();

    // When
    column.setValue(year, month, day);
    column.setValue(yearPlus1, month, day);

    // Then
    Calendar value = null;
    if (column.getValue() instanceof Calendar) {
      value = (Calendar) column.getValue();
    }
    assertNotNull("DateColumn calendar value - 2nd time", value);
    assertEquals("DateColumn year", yearPlus1, value.get(Calendar.YEAR));
    assertEquals("DateColumn month", month, value.get(Calendar.MONTH));
    assertEquals("DateColumn day", day, value.get(Calendar.DAY_OF_MONTH));
  }

}
