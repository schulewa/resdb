package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.dto.RiverDto;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import com.apschulewitz.resdb.research.model.helper.PlaceTestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = {PlaceMapper.class, PlaceTestHelper.class})
@RunWith(SpringRunner.class)
public class PlaceMapperTest {

  @Autowired
  private PlaceTestHelper testHelper;

  @Autowired
  private PlaceMapper mapper;

  @Test
  public void test_context() {
    assertNotNull(mapper);
  }

  @Test
  public void given_null_entity_and_mandatory_values_when_toDto_is_executed_then_return_error() {
    // given
    Place entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null place cannot be mapped to dto");

    // then
  }

  @Test
  public void given_entity_and_all_values_when_toDto_is_executed_then_return_dto() {
    // given
    Place entity = testHelper.constructNewEntityWithAllValues();

    // when
    PlaceDto dto = mapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertEquals(entity.getCreatedBy(), dto.getCreatedBy());
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getLastUpdated(), dto.getLastUpdated());
    assertEquals(entity.getName(), dto.getName());
    assertNotNull(entity.getAltitude());
    assertNotNull(dto.getCreatedBy());
    assertNotNull(dto.getId());
    assertNotNull(dto.getLastUpdated());
    assertNotNull(dto.getLatitude());
    assertNotNull(dto.getLongitude());
    assertNotNull(dto.getStatus());
    assertNotNull(dto.getUpdatedBy());
    assertNotNull(dto.getVersionNumber());
  }

  @Test
  public void given_null_dto_when_toEntity_is_executed_then_return_error() {
    // given
    PlaceDto dto = null;

    // when
    assertThatThrownBy(() -> mapper.toEntity(dto))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null place cannot be mapped to entity");

    // then
  }

  @Test
  public void given_dto_and_all_values_when_toEntity_is_executed_then_return_entity() {
    // given
    PlaceDto dto = testHelper.constructNewDtoWithAllValues();

    // when
    Place entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getAltitude().getValue(),entity.getAltitude().getValue());
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getLatitude().getValue(), entity.getLatitude().getValue());
    assertEquals(dto.getLongitude().getValue(), entity.getLongitude().getValue());
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_dto_and_river_when_toEntity_is_executed_then_return_entity() {
    // given
    PlaceDto dto = testHelper.constructUnsavedMinimalDto();
    dto.setRiver(RiverDto.builder().name("Arun").build());

    // when
    Place entity = mapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

  @Test
  public void given_null_dto_and_onlyactive_when_toEntity_is_executed_then_return_error() {
    // given
    PlaceDto dto = null;

    // when
    assertThatThrownBy(() -> mapper.toEntity(dto, true))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null place cannot be mapped to entity");

    // then
  }

  @Test
  public void given_null_entity_and_onlyactive_when_toDto_is_executed_then_return_error() {
    // given
    Place entity = null;

    // when
    assertThatThrownBy(() -> mapper.toDto(entity, true))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null place cannot be mapped to dto");

    // then
  }

  @Test
  public void given_dto_and_onlyactive_false_when_toEntity_is_executed_then_return_entity() {
    // given
    PlaceDto dto = testHelper.constructUnsavedMinimalDto();

    // when
    Place entity = mapper.toEntity(dto, false);

    // then
    assertNotNull(entity);
  }

  @Test
  public void given_entity_and_onlyactive_false_when_toDto_is_executed_then_return_dto() {
    // given
    Place entity = testHelper.constructUnsavedMinimalEntity();

    // when
    PlaceDto dto = mapper.toDto(entity, false);

    // then
    assertNotNull(dto);
  }
}
