package com.apschulewitz.resdb.common.model.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class YesNoChoiceTest {

  @Test
  public void given_N_code_when_getChoiceFor_is_executed_then_return_choice() {
    // given

    // when
    YesNoChoice choice = YesNoChoice.getChoiceFor("N");

    // then
    assertNotNull(choice);
    assertEquals(YesNoChoice.No, choice);
  }

  @Test
  public void given_Y_code_when_getChoiceFor_is_executed_then_return_choice() {
    // given

    // when
    YesNoChoice choice = YesNoChoice.getChoiceFor("Y");

    // then
    assertNotNull(choice);
    assertEquals(YesNoChoice.Yes, choice);
  }

  @Test
  public void given_code_other_than_N_or_Y_when_getChoiceFor_is_executed_then_return_choice() {
    // given

    // when
    YesNoChoice choice = YesNoChoice.getChoiceFor("x");

    // then
    assertNotNull(choice);
    assertEquals(YesNoChoice.Unknown, choice);
  }
}
