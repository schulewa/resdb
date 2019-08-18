package com.apschulewitz.resdb.common.model.converter;

import org.junit.Test;

import java.time.OffsetDateTime;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class OffsetDateTimePersistenceConverterTest {

  @Test
  public void given_non_null_OffsetDateTime_when_convertToDatabaseColumn_is_executed_then_return_Timestamp() {
    // Given
    OffsetDateTime zonedDateTime = OffsetDateTime.now();
    OffsetDateTimePersistenceConverter converter = new OffsetDateTimePersistenceConverter();

    // When
    String toDatabaseValue = converter.convertToDatabaseColumn(zonedDateTime);

    // Then
    assertNotNull("To database", toDatabaseValue);
  }

  @Test
  public void given_null_OffsetDateTime_when_convertToDatabaseColumn_is_executed_then_return_Timestamp() {
    // Given
    OffsetDateTime zonedDateTime = null;
    OffsetDateTimePersistenceConverter converter = new OffsetDateTimePersistenceConverter();

    // When
    String toDatabaseValue = converter.convertToDatabaseColumn(zonedDateTime);

    // Then
    assertNull("To database null", toDatabaseValue);
  }

  @Test
  public void given_non_null_timestamp_when_convertToEntityAttribute_is_executed_then_return_OffsetDateTime() {
    // Given
    String timestamp = "2019-08-18T16:05:30+01:00";
    OffsetDateTimePersistenceConverter converter = new OffsetDateTimePersistenceConverter();

    // When
    OffsetDateTime fromDatabaseValue = converter.convertToEntityAttribute(timestamp);

    // Then
    assertNotNull("To entity", fromDatabaseValue);
  }

  @Test
  public void given_null_Timestamp_when_convertToEntityAttribute_is_executed_then_return_OffsetDateTime() {
    // Given
    String timestamp = null;
    OffsetDateTimePersistenceConverter converter = new OffsetDateTimePersistenceConverter();

    // When
    OffsetDateTime fromDatabaseValue = converter.convertToEntityAttribute(timestamp);

    // Then
    assertNull("To entity null", fromDatabaseValue);
  }

}
