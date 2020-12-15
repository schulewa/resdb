package com.apschulewitz.resdb.common.model.entity;

import org.junit.Test;

import static org.junit.Assert.*;

public class TitleTypeTest {

  @Test
  public void given_P_code_when_getTypeFor_is_executed_then_return_TitleType() {
    // Given
    String code = "P";

    // When
    TitleType type = TitleType.getTypeForCode(code);

    // Then
    assertEquals("P code", TitleType.Prefix, type);
  }

  @Test
  public void given_S_code_when_getTypeFor_is_executed_then_return_TitleType() {
    // Given
    String code = "S";

    // When
    TitleType type = TitleType.getTypeForCode(code);

    // Then
    assertEquals("S code", TitleType.Suffix, type);
  }

  @Test
  public void given_invalid_code_when_getTypeFor_is_executed_then_throw_exception() {
    // Given
    String code = "X";
    boolean isExceptionThrown = false;

    // When
    try {
      TitleType.getTypeForCode(code);
    } catch (IllegalArgumentException e) {
      isExceptionThrown = true;
    }

    // Then
    assertTrue("Invalid code", isExceptionThrown);
  }

}
