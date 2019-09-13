package com.apschulewitz.resdb.security.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PermissionTest {

  @Test
  public void given_name_when_getAuthority_is_executed_then_return_name() {
    // Given
    Permission permission = Permission.builder()
      .name("Some permission")
      .build();

    // When
    String name = permission.getAuthority();

    // Then
    assertNotNull(name);
    assertEquals("Some permission", name);
  }
}
