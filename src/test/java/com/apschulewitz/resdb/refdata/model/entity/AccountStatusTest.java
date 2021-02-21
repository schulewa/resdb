package com.apschulewitz.resdb.refdata.model.entity;

import com.apschulewitz.resdb.security.model.entity.AccountStatus;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountStatusTest {

  @Test
  public void given_A_code_when_getStatusFor_is_executed_then_return_AccountStatus() {
    // Given
    String code = "A";

    // When
    AccountStatus accountStatus = AccountStatus.getStatusFor(code);

    // Then
    assertEquals("A code", AccountStatus.Active, accountStatus);
  }

  @Test
  public void given_I_code_when_getStatusFor_is_executed_then_return_AccountStatus() {
    // Given
    String code = "I";

    // When
    AccountStatus accountStatus = AccountStatus.getStatusFor(code);

    // Then
    assertEquals("I code", AccountStatus.Inactive, accountStatus);
  }

  @Test
  public void given_L_code_when_getStatusFor_is_executed_then_return_AccountStatus() {
    // Given
    String code = "L";

    // When
    AccountStatus accountStatus = AccountStatus.getStatusFor(code);

    // Then
    assertEquals("L code", AccountStatus.Locked, accountStatus);
  }

  @Test
  public void given_R_code_when_getStatusFor_is_executed_then_return_AccountStatus() {
    // Given
    String code = "R";

    // When
    AccountStatus accountStatus = AccountStatus.getStatusFor(code);

    // Then
    assertEquals("R code", AccountStatus.PasswordNeedsResetting, accountStatus);
  }

  @Test
  public void given_S_code_when_getStatusFor_is_executed_then_return_AccountStatus() {
    // Given
    String code = "S";

    // When
    AccountStatus accountStatus = AccountStatus.getStatusFor(code);

    // Then
    assertEquals("S code", AccountStatus.Suspended, accountStatus);
  }

  @Test
  public void given_X_code_when_getStatusFor_is_executed_then_return_AccountStatus() {
    // Given
    String code = "X";

    // When
    AccountStatus accountStatus = AccountStatus.getStatusFor(code);

    // Then
    assertEquals("X code", AccountStatus.Unknown, accountStatus);
  }

  @Test
  public void given_invalid_code_when_getStatusFor_is_executed_then_return_AccountStatus() {
    // Given
    String code = "?";

    // When
    AccountStatus accountStatus = AccountStatus.getStatusFor(code);

    // Then
    assertEquals("Invalid code", AccountStatus.Unknown, accountStatus);
  }

  @Test
  public void given_valid_code_when_getCode_is_executed_then_return_code() {
    // Given
    String code = "A";

    // When
    AccountStatus accountStatus = AccountStatus.getStatusFor(code);

    // Then
    assertEquals("Get code", code, accountStatus.getCode());
  }

}
