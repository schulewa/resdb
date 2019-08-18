package com.apschulewitz.resdb.common.model.converter;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LocalDateTimeConverterTest {

  private LocalDateTimeConverter converter = new LocalDateTimeConverter();

  @Test
  public void given_valid_LocalDateTime_when_convertToDatabaseColumn_is_executed_then_return_Timestamp() {
    // Given
    LocalDateTime localDateTime = LocalDateTime.now();

    // When
    Timestamp timestamp = converter.convertToDatabaseColumn(localDateTime);

    // Then
    assertNotNull("Database non null value", timestamp);
  }

  @Test
  public void given_null_LocalDateTime_when_convertToDatabaseColumn_is_executed_then_return_null() {
    // Given
    LocalDateTime localDateTime = null;

    // When
    Timestamp timestamp = converter.convertToDatabaseColumn(localDateTime);

    // Then
    assertNull("Database null value", timestamp);
  }

  @Test
  public void given_valid_Timestamp_when_convertToEntityAttribute_is_executed_then_return_LocalDateTime() {
    // Given
    Timestamp timestamp = Timestamp.from(Instant.now());

    // When
    LocalDateTime localDateTime = converter.convertToEntityAttribute(timestamp);

    // Then
    assertNotNull("Entity non null value", localDateTime);
  }

  @Test
  public void given_null_Timestamp_when_convertToEntityAttribute_is_executed_then_return_null() {
    // Given
    Timestamp timestamp = null;

    // When
    LocalDateTime localDateTime = converter.convertToEntityAttribute(timestamp);

    // Then
    assertNull("Entity null value", localDateTime);
  }

}
