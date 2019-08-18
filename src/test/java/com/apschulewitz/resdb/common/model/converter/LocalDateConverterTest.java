package com.apschulewitz.resdb.common.model.converter;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LocalDateConverterTest {

  private LocalDateConverter converter = new LocalDateConverter();

  @Test
  public void given_LocalDate_when_convertToDatabaseColumn_is_executed_then_return_Date() {
    // Given
    LocalDate entityValue = LocalDate.now();

    // When
    Date databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertNotNull("Database value", databaseValue);
  }

  @Test
  public void given_null_LocalDate_when_convertToDatabaseColumn_is_executed_then_return_null() {
    // Given
    LocalDate entityValue = null;

    // When
    Date databaseValue = converter.convertToDatabaseColumn(entityValue);

    // Then
    assertNull("Database null value", databaseValue);
  }

  @Test
  public void given_valid_Date_when_convertToEntityAttribute_is_executed_then_return_LocalDate() {
    // Given
    Date databaseValue = new Date(new java.util.Date().getTime());

    // When
    LocalDate entityValue = converter.convertToEntityAttribute(databaseValue);

    // then
    assertNotNull("Entity value", entityValue);
  }

  @Test
  public void given_null_Date_when_convertToEntityAttribute_is_executed_then_return_null() {
    // Given
    Date databaseValue = null;

    // When
    LocalDate entityValue = converter.convertToEntityAttribute(databaseValue);

    // then
    assertNull("Entity null value", entityValue);
  }

}
