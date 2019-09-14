package com.apschulewitz.resdb.security.model.entity;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import org.junit.Test;

public class MenuOptionTest {

  @Test(expected = IllegalArgumentException.class)
  public void given_invalid_op_when_getOperationFor_is_executed_then_throw_exception() {
    // Given
    String op = "X";

    // When
    DataOperation dataOperation = DataOperation.getOperationFor(op);
  }
}
