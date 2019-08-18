package com.apschulewitz.resdb.common.model.converter;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZonedDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ZonedDateTimeConverterTest {

  @Test
  public void given_non_null_ZonedDateTime_when_convertToDatabaseColumn_is_executed_then_return_Timestamp() {
    // Given
    ZonedDateTime zonedDateTime = ZonedDateTime.now();
    ZonedDateTimeConverter converter = new ZonedDateTimeConverter();

    // When
    Timestamp toDatabaseValue = converter.convertToDatabaseColumn(zonedDateTime);

    // Then
    assertNotNull("To database", toDatabaseValue);
  }

  @Test
  public void given_null_ZonedDateTime_when_convertToDatabaseColumn_is_executed_then_return_Timestamp() {
    // Given
    ZonedDateTime zonedDateTime = null;
    ZonedDateTimeConverter converter = new ZonedDateTimeConverter();

    // When
    Timestamp toDatabaseValue = converter.convertToDatabaseColumn(zonedDateTime);

    // Then
    assertNull("To database null", toDatabaseValue);
  }

  @Test
  public void given_non_null_Timestamp_when_convertToEntityAttribute_is_executed_then_return_ZonedDateTime() {
    // Given
    Timestamp timestamp = Timestamp.from(Instant.now());
    ZonedDateTimeConverter converter = new ZonedDateTimeConverter();

    // When
    ZonedDateTime fromDatabaseValue = converter.convertToEntityAttribute(timestamp);

    // Then
    assertNotNull("To entity", fromDatabaseValue);
  }

  @Test
  public void given_null_Timestamp_when_convertToEntityAttribute_is_executed_then_return_ZonedDateTime() {
    // Given
    Timestamp timestamp = null;
    ZonedDateTimeConverter converter = new ZonedDateTimeConverter();

    // When
    ZonedDateTime fromDatabaseValue = converter.convertToEntityAttribute(timestamp);

    // Then
    assertNull("To entity null", fromDatabaseValue);
  }

}
