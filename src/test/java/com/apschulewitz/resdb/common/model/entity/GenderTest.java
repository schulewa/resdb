package com.apschulewitz.resdb.common.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GenderTest {

    @Test
    public void given_A_code_when_getTypeFor_is_executed_then_return_Gender() {
        // Given
        String code = "A";

        // When
        Gender type = Gender.getGenderFor(code);

        // Then
        assertEquals("A code", Gender.Any, type);
    }

  @Test
  public void given_M_code_when_getTypeFor_is_executed_then_return_Gender() {
    // Given
    String code = "M";

    // When
    Gender type = Gender.getGenderFor(code);

    // Then
    assertEquals("M code", Gender.Male, type);
  }

  @Test
  public void given_F_code_when_getTypeFor_is_executed_then_return_Gender() {
    // Given
    String code = "F";

    // When
    Gender type = Gender.getGenderFor(code);

    // Then
    assertEquals("F code", Gender.Female, type);
  }

  @Test
  public void given_U_code_when_getTypeFor_is_executed_then_return_unknown() {
    // Given
    String code = "U";

    // When
    Gender type = Gender.getGenderFor(code);

    // Then
    assertEquals("U code", Gender.Unknown, type);
  }

  @Test
  public void given_invalid_code_when_getTypeFor_is_executed_then_return_unknown() {
    // Given
    String code = "X";

    // When
    Gender type = Gender.getGenderFor(code);

    // Then
    assertEquals("Invalid code", Gender.Unknown, type);
  }

}
