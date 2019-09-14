package com.apschulewitz.resdb.common.model.utils;

import com.apschulewitz.resdb.common.model.entity.DataType;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataComparatorTest {

  @Test
  public void when_default_constructor_is_executed_then_return_instance() {
    // When
    DataComparator dataComparator = new DataComparator();

    // Then
    assertNotNull(dataComparator);
  }

  @Test
  public void given_args_when_nondefault_constructor_is_executed_then_return_instance() {
    // Given
    DataComparator.DataComparatorOperator dataComparisonOperator = DataComparator.DataComparatorOperator.CONTAINS;
    String dataValue = "Something";
    String dataType = DataType.BLOB.name();

    // When
    DataComparator dataComparator = new DataComparator(dataComparisonOperator, dataValue, dataType);

    // Then
    assertNotNull(dataComparator);
  }

  @Test
  public void given_DataComparator_when_getDataComparatorOperator_is_executed_then_return_operator() {
    // Given
    DataComparator dataComparator = DataComparator.builder()
      .operator(DataComparator.DataComparatorOperator.EQUAL_TO)
      .value("Something")
      .valueType(DataType.DATE.name())
      .build();

    // When
    DataComparator.DataComparatorOperator dataComparatorOperator = dataComparator.getOperator();

    // Then
    assertNotNull(dataComparatorOperator);
    assertEquals(DataComparator.DataComparatorOperator.EQUAL_TO, dataComparatorOperator);
  }

  @Test
  public void given_eq_when_toDataComparatorOperator_is_executed_then_return_EQUAL_TO() {
    // Given
    String name = "=";

    // When
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // Then
    assertNotNull(operator);
    assertEquals(DataComparator.DataComparatorOperator.EQUAL_TO, operator);
  }

  @Test
  public void given_lt_when_toDataComparatorOperator_is_executed_then_return_LESS_THAN() {
    // Given
    String name = "<";

    // When
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // Then
    assertNotNull(operator);
    assertEquals(DataComparator.DataComparatorOperator.LESS_THAN, operator);
  }

  @Test
  public void given_lte_when_toDataComparatorOperator_is_executed_then_return_LESS_THAN_OR_EQUAL_TO() {
    // Given
    String name = "<=";

    // When
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // Then
    assertNotNull(operator);
    assertEquals(DataComparator.DataComparatorOperator.LESS_THAN_OR_EQUAL_TO, operator);
  }

  @Test
  public void given_gt_when_toDataComparatorOperator_is_executed_then_return_GREATER_THAN() {
    // Given
    String name = ">";

    // When
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // Then
    assertNotNull(operator);
    assertEquals(DataComparator.DataComparatorOperator.GREATER_THAN, operator);
  }

  @Test
  public void given_gte_when_toDataComparatorOperator_is_executed_then_return_GREATER_THAN_OR_EQUAL_TO() {
    // Given
    String name = ">=";

    // When
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // Then
    assertNotNull(operator);
    assertEquals(DataComparator.DataComparatorOperator.GREATER_THAN_OR_EQUAL_TO, operator);
  }

  @Test
  public void given_c_when_toDataComparatorOperator_is_executed_then_return_CONTAINS() {
    // Given
    String name = "Contains";

    // When
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // Then
    assertNotNull(operator);
    assertEquals(DataComparator.DataComparatorOperator.CONTAINS, operator);
  }

  @Test
  public void given_sw_when_toDataComparatorOperator_is_executed_then_return_CONTAINS() {
    // Given
    String name = "Starts with";

    // When
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // Then
    assertNotNull(operator);
    assertEquals(DataComparator.DataComparatorOperator.STARTS_WITH, operator);
  }

  @Test
  public void given_ew_when_toDataComparatorOperator_is_executed_then_return_CONTAINS() {
    // Given
    String name = "Ends with";

    // When
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // Then
    assertNotNull(operator);
    assertEquals(DataComparator.DataComparatorOperator.ENDS_WITH, operator);
  }

  @Test
  public void given_anything_else_when_toDataComparatorOperator_is_executed_then_return_CONTAINS() {
    // Given
    String name = "XXX";

    // When
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // Then
    assertNull(operator);
  }

  @Test
  public void given_valid_comparator_when_toString_is_executed_then_return_name() {
    // Given
    String name = "Ends with";
    DataComparator.DataComparatorOperator operator = DataComparator.DataComparatorOperator.toDataComparatorOperator(name);

    // When
    String toStringValue = operator.toString();

    // Then
    assertNotNull(operator);
    assertNotNull(toStringValue);
    assertEquals(name, toStringValue);
  }

}
