package com.apschulewitz.resdb.security.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PermissionStatusTest {

  @Test
  public void given_A_when_getStatusFor_is_executed_then_return_Active() {
    // Given
    String status = "A";

    // When
    Permission.PermissionStatus permissionStatus = Permission.PermissionStatus.getStatusFor(status);

    // Then
    assertEquals(Permission.PermissionStatus.Active, permissionStatus);
  }

  @Test
  public void given_I_when_getStatusFor_is_executed_then_return_Inactive() {
    // Given
    String status = "I";

    // When
    Permission.PermissionStatus permissionStatus = Permission.PermissionStatus.getStatusFor(status);

    // Then
    assertEquals(Permission.PermissionStatus.Inactive, permissionStatus);
  }

  @Test
  public void given_S_when_getStatusFor_is_executed_then_return_Suspended() {
    // Given
    String status = "S";

    // When
    Permission.PermissionStatus permissionStatus = Permission.PermissionStatus.getStatusFor(status);

    // Then
    assertEquals(Permission.PermissionStatus.Suspended, permissionStatus);
  }

  @Test
  public void given_X_when_getStatusFor_is_executed_then_return_Unknown() {
    // Given
    String status = "X";

    // When
    Permission.PermissionStatus permissionStatus = Permission.PermissionStatus.getStatusFor(status);

    // Then
    assertEquals(Permission.PermissionStatus.Unknown, permissionStatus);
  }

  @Test
  public void given_empty_when_getStatusFor_is_executed_then_return_Unknown() {
    // Given
    String status = "";

    // When
    Permission.PermissionStatus permissionStatus = Permission.PermissionStatus.getStatusFor(status);

    // Then
    assertEquals(Permission.PermissionStatus.Unknown, permissionStatus);
  }

  @Test
  public void given_PermissionStatus_when_getCode_is_executed_then_return_code() {
    // Given
    Permission.PermissionStatus permissionStatus = Permission.PermissionStatus.Active;

    // When
    String code = permissionStatus.getCode();

    // Then
    assertNotNull(code);
    assertEquals("A", code);
  }

}
