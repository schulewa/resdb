package com.apschulewitz.resdb.common.model.utils;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class DaoUtilTest {

  @Test
  public void given_null_iterable_when_iterToList_is_executed_then_return_empty_list() {
    // Given
    Iterable<String> iterable = null;
    DaoUtil<String> daoUtil = new DaoUtil<>();

    // When
    List<String> data = daoUtil.iterToList(iterable);

    // Then
    assertNotNull(data);
    assertTrue(data.isEmpty());
  }

  @Test
  public void given_empty_iterable_when_iterToList_is_executed_then_return_empty_list() {
    // Given
    Iterable<String> iterable = new ArrayList<>();
    DaoUtil<String> daoUtil = new DaoUtil<>();

    // When
    List<String> data = daoUtil.iterToList(iterable);

    // Then
    assertNotNull(data);
    assertTrue(data.isEmpty());
  }

  @Test
  public void given_nonempty_iterable_when_iterToList_is_executed_then_return_nonempty_list() {
    // Given
    Iterable<String> iterable = Arrays.asList("String1", "String2");
    DaoUtil<String> daoUtil = new DaoUtil<>();

    // When
    List<String> data = daoUtil.iterToList(iterable);

    // Then
    assertNotNull(data);
    assertFalse(data.isEmpty());
    assertEquals(2, data.size());
  }

}
