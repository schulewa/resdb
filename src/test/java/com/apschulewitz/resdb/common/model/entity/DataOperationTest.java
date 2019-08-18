package com.apschulewitz.resdb.common.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataOperationTest {

  @Test
  public void given_I_code_when_getOperationFor_then_return_DataOperation() {
    // Given
    String code = "I";

    // When
    DataOperation operation = DataOperation.getOperationFor(code);

    // Then
    assertEquals("I code", DataOperation.CREATE, operation);
  }

  @Test
  public void given_D_code_when_getOperationFor_then_return_DataOperation() {
    // Given
    String code = "D";

    // When
    DataOperation operation = DataOperation.getOperationFor(code);

    // Then
    assertEquals("D code", DataOperation.DELETE, operation);
  }

  @Test
  public void given_Q_code_when_getOperationFor_then_return_DataOperation() {
    // Given
    String code = "Q";

    // When
    DataOperation operation = DataOperation.getOperationFor(code);

    // Then
    assertEquals("Q code", DataOperation.READ, operation);
  }

  @Test
  public void given_U_code_when_getOperationFor_then_return_DataOperation() {
    // Given
    String code = "U";

    // When
    DataOperation operation = DataOperation.getOperationFor(code);

    // Then
    assertEquals("U code", DataOperation.UPDATE, operation);
  }

  @Test
  public void given_invalid_code_when_getOperationFor_then_return_DataOperation() {
    // Given
    String code = "X";
    boolean isExceptionThrown = false;

    // When
    try {
      DataOperation.getOperationFor(code);
    } catch (IllegalArgumentException e) {
      isExceptionThrown = true;
    }

    // Then
    assertTrue("Invalid code", isExceptionThrown);
  }

  @Test
  public void given_valid_code_when_getCode_then_return_code() {
    // Given
    String code = "U";
    DataOperation operation = DataOperation.getOperationFor(code);

    // When
    String retrievedCode = operation.getCode();

    // Then
    assertEquals("Valid code", code, retrievedCode);
  }

}
