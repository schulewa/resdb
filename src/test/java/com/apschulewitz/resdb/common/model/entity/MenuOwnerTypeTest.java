package com.apschulewitz.resdb.common.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MenuOwnerTypeTest {

  @Test
  public void given_G_code_when_getTypeFor_is_executed_then_return_group_MenuOwnerType() {
    // Given
    String code = "G";

    // When
    MenuOwnerType menuOwnerType = MenuOwnerType.getTypeFor(code);

    // Then
    assertEquals("G code", MenuOwnerType.GROUP, menuOwnerType);
  }

  @Test
  public void given_U_code_when_getTypeFor_is_executed_then_return_user_MenuOwnerType() {
    // Given
    String code = "U";

    // When
    MenuOwnerType menuOwnerType = MenuOwnerType.getTypeFor(code);

    // Then
    assertEquals("U code", MenuOwnerType.USER, menuOwnerType);
  }

  @Test
  public void given_invalid_code_when_getTypeFor_is_executed_then_throw_exception() {
    // Given
    String code = "X";
    boolean isExceptionThrown = false;

    // When
    try {
      MenuOwnerType.getTypeFor(code);
    } catch (IllegalArgumentException e) {
      isExceptionThrown = true;
    }

    // Then
    assertTrue("Invalid code", isExceptionThrown);
  }

  @Test
  public void given_valid_code_when_getCode_is_executed_then_return_code() {
    // Given
    String code = "U";
    MenuOwnerType menuOwnerType = MenuOwnerType.getTypeFor(code);

    // When
    String retrievedCode = menuOwnerType.getCode();

    // Then
    assertEquals("Valid code", code, retrievedCode);
  }

}
