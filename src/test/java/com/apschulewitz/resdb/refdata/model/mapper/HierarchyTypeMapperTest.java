package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
import com.apschulewitz.resdb.refdata.model.helper.HierarchyTypeTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {HierarchyTypeMapper.class, HierarchyTypeTestHelper.class})
@RunWith(SpringRunner.class)
public class HierarchyTypeMapperTest {

  @Autowired
  private HierarchyTypeTestHelper testHelper;

  @Autowired
  private HierarchyTypeMapper mapper;

  @Test
  public void test_context() {
    assertNotNull(mapper);
  }

  @Test
  public void given_null_entity_and_mandatory_values_when_toDto_is_executed_then_return_error() {
    // given
    HierarchyType entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null hierarchy type cannot be mapped to dto");

    // then
  }

  @Test
  public void given_entity_and_all_values_when_toDto_is_executed_then_return_dto() {
    // given
    HierarchyType entity = testHelper.constructNewEntityWithAllValues();

    // when
    HierarchyTypeDto dto = mapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertEquals(entity.getCreatedBy(), dto.getCreatedBy());
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getLastUpdated(), dto.getLastUpdated());
    assertEquals(entity.getName(), dto.getName());
    assertNotNull(dto.getCreatedBy());
    assertNotNull(dto.getId());
    assertNotNull(dto.getLastUpdated());
    assertNotNull(dto.getStatus());
    assertNotNull(dto.getUpdatedBy());
    assertNotNull(dto.getVersionNumber());
  }

  @Test
  public void given_null_dto_when_toEntity_is_executed_then_return_error() {
    // given
    HierarchyTypeDto dto = null;

    // when
    assertThatThrownBy(() -> mapper.toEntity(dto))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null hierarchy type cannot be mapped to entity");

    // then
  }

  @Test
  public void given_dto_and_all_values_when_toEntity_is_executed_then_return_entity() {
    // given
    HierarchyTypeDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    HierarchyType entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_dto_and_mandatory_values_when_toEntity_is_executed_then_return_entity() {
    // given
    HierarchyTypeDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    HierarchyType entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_dto_and_region_when_toEntity_is_executed_then_return_entity() {
    // given
    HierarchyTypeDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    HierarchyType entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_dto_and_all_booleans_false_when_toEntity_is_executed_then_return_no_error() {
    // given
    HierarchyTypeDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    HierarchyType entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
  }

  @Test
  public void given_dto_and_all_booleans_true_when_toEntity_is_executed_then_return_no_error() {
    // given
    HierarchyTypeDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    HierarchyType entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
  }

  @Test
  public void given_null_dto_and_onlyactive_when_toEntity_is_executed_then_return_error() {
    // given
    HierarchyTypeDto dto = null;

    // when
    assertThatThrownBy(() -> mapper.toEntity(dto, true))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null hierarchy type cannot be mapped to entity");

    // then
  }

  @Test
  public void given_null_entity_and_onlyactive_when_toDto_is_executed_then_return_error() {
    // given
    HierarchyType entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity, true))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null hierarchy type cannot be mapped to dto");

    // then
  }

  @Test
  public void given_dto_and_onlyactive_false_when_toEntity_is_executed_then_return_entity() {
    // given
    HierarchyTypeDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    HierarchyType entity = mapper.toEntity(dto, false);

    // then
    assertNotNull(entity);
  }

  @Test
  public void given_entity_and_onlyactive_false_when_toDto_is_executed_then_return_dto() {
    // given
    HierarchyType entity = testHelper.constructUnsavedMinimalEntity();

    // when
    HierarchyTypeDto dto = mapper.toDto(entity, false);

    // then
    assertNotNull(dto);
  }
}
