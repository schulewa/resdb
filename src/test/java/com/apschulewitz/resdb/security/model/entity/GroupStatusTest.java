package com.apschulewitz.resdb.security.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GroupStatusTest {

  @Test
  public void given_A_when_getStatusFor_is_executed_then_return_Active() {
    // Given
    String status = "A";

    // When
    GroupStatus groupStatus = GroupStatus.getStatusFor(status);

    // Then
    assertEquals(GroupStatus.Active, groupStatus);
  }

  @Test
  public void given_I_when_getStatusFor_is_executed_then_return_Inactive() {
    // Given
    String status = "I";

    // When
    GroupStatus groupStatus = GroupStatus.getStatusFor(status);

    // Then
    assertEquals(GroupStatus.Inactive, groupStatus);
  }

  @Test
  public void given_L_when_getStatusFor_is_executed_then_return_Locked() {
    // Given
    String status = "L";

    // When
    GroupStatus groupStatus = GroupStatus.getStatusFor(status);

    // Then
    assertEquals(GroupStatus.Locked, groupStatus);
  }

  @Test
  public void given_R_when_getStatusFor_is_executed_then_return_PasswordNeedsResetting() {
    // Given
    String status = "R";

    // When
    GroupStatus groupStatus = GroupStatus.getStatusFor(status);

    // Then
    assertEquals(GroupStatus.PasswordNeedsResetting, groupStatus);
  }

  @Test
  public void given_S_when_getStatusFor_is_executed_then_return_Suspended() {
    // Given
    String status = "S";

    // When
    GroupStatus groupStatus = GroupStatus.getStatusFor(status);

    // Then
    assertEquals(GroupStatus.Suspended, groupStatus);
  }

  @Test
  public void given_X_when_getStatusFor_is_executed_then_return_Unknown() {
    // Given
    String status = "X";

    // When
    GroupStatus groupStatus = GroupStatus.getStatusFor(status);

    // Then
    assertEquals(GroupStatus.Unknown, groupStatus);
  }

  @Test
  public void given_empty_when_getStatusFor_is_executed_then_return_Unknown() {
    // Given
    String status = "";

    // When
    GroupStatus groupStatus = GroupStatus.getStatusFor(status);

    // Then
    assertEquals(GroupStatus.Unknown, groupStatus);
  }

  @Test
  public void given_PermissionStatus_when_getCode_is_executed_then_return_code() {
    // Given
    GroupStatus groupStatus = GroupStatus.Active;

    // When
    String code = groupStatus.getCode();

    // Then
    assertNotNull(code);
    assertEquals("A", code);
  }
}
