package com.apschulewitz.resdb.security.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperationTypeTest {

  @Test
  public void given_C_when_getStatusFor_is_executed_then_return_Create() {
    // Given
    String status = "C";

    // When
    Permission.OperationType operationType = Permission.OperationType.getStatusFor(status);

    // Then
    assertEquals(Permission.OperationType.Create, operationType);
  }

  @Test
  public void given_CRU_when_getStatusFor_is_executed_then_return_CreateReadUpdate() {
    // Given
    String status = "CRU";

    // When
    Permission.OperationType operationType = Permission.OperationType.getStatusFor(status);

    // Then
    assertEquals(Permission.OperationType.CreateReadUpdate, operationType);
  }

  @Test
  public void given_CRUD_when_getStatusFor_is_executed_then_return_CreateReadUpdateDelete() {
    // Given
    String status = "CRUD";

    // When
    Permission.OperationType operationType = Permission.OperationType.getStatusFor(status);

    // Then
    assertEquals(Permission.OperationType.CreateReadUpdateDelete, operationType);
  }

  @Test
  public void given_D_when_getStatusFor_is_executed_then_return_Delete() {
    // Given
    String status = "D";

    // When
    Permission.OperationType operationType = Permission.OperationType.getStatusFor(status);

    // Then
    assertEquals(Permission.OperationType.Delete, operationType);
  }

  @Test
  public void given_R_when_getStatusFor_is_executed_then_return_Read() {
    // Given
    String status = "R";

    // When
    Permission.OperationType operationType = Permission.OperationType.getStatusFor(status);

    // Then
    assertEquals(Permission.OperationType.Read, operationType);
  }

  @Test
  public void given_U_when_getStatusFor_is_executed_then_return_Update() {
    // Given
    String status = "U";

    // When
    Permission.OperationType operationType = Permission.OperationType.getStatusFor(status);

    // Then
    assertEquals(Permission.OperationType.Update, operationType);
  }

  @Test
  public void given_X_when_getStatusFor_is_executed_then_return_Unknown() {
    // Given
    String status = "X";

    // When
    Permission.OperationType operationType = Permission.OperationType.getStatusFor(status);

    // Then
    assertEquals(Permission.OperationType.Unknown, operationType);
  }

}
