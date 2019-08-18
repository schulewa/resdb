package com.apschulewitz.resdb.common.model.entity;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class DataEntityPrimaryKeyTest {

  @Test
  public void given_valid_key_when_constructor_is_executed_then_return_DataEntityPrimaryKey() {
    // Given
    Map<String, Object> originalKey = new HashMap<>();
    DataEntityPrimaryKey primaryKey;

    // When
    originalKey.put("Field1", "Value1");
    originalKey.put("Field2", "Value2");
    primaryKey = new DataEntityPrimaryKey(originalKey);

    // Then
    assertNotNull("DataEntityPrimaryKey constructor", primaryKey);
    assertNotNull("Key not null", primaryKey.getKey());
  }
}
