package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.refdata.model.RegionTestHelper;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = RegionMapper.class)
@RunWith(SpringRunner.class)
public class RegionMapperTest {

  @Autowired
  private RegionMapper regionMapper;

  @Test
  public void test_context() {
    assertNotNull(regionMapper);
  }

  @Test
  public void given_entity_and_all_values_when_toDto_is_executed_then_return_dto() {
    // given
    Region entity = RegionTestHelper.constructNewRegionWithAllValues(1L, "Europe");

    // when
    RegionDto dto = regionMapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertEquals(entity.getCreatedBy(), dto.getCreatedBy());
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getLastUpdated(), dto.getLastUpdated());
    assertEquals(entity.getName(), dto.getName());
    assertEquals(entity.getStatus().name(), dto.getStatus());
    assertEquals(entity.getUpdatedBy(), dto.getUpdatedBy());
  }

  @Test
  public void given_entity_and_mandatory_values_when_toDto_is_executed_then_return_dto() {
    // given
    Region entity = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Europe");

    // when
    RegionDto dto = regionMapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertEquals(entity.getCreatedBy(), dto.getCreatedBy());
    assertEquals(entity.getId(), dto.getId());
    assertEquals(entity.getLastUpdated(), dto.getLastUpdated());
    assertEquals(entity.getName(), dto.getName());
    assertEquals(entity.getStatus().name(), dto.getStatus());
    assertEquals(entity.getUpdatedBy(), dto.getUpdatedBy());
  }

  @Test
  public void given_dto_and_all_values_when_toEntity_is_executed_then_return_entity() {
    // given
    RegionDto dto = RegionTestHelper.constructNewRegionDtoWithAllValues(1L, "Europe");

    // when
    Region entity = regionMapper.toEntity(dto);

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
    RegionDto dto = RegionTestHelper.constructNewRegionDtoWithMandatoryValues(1L, "Europe");

    // when
    Region entity = regionMapper.toEntity(dto);

    // then
    assertNotNull(entity);
    assertEquals(dto.getCreatedBy(), entity.getCreatedBy());
    assertEquals(dto.getId(), entity.getId());
    assertEquals(dto.getLastUpdated(), entity.getLastUpdated());
    assertEquals(dto.getName(), entity.getName());
    assertEquals(dto.getUpdatedBy(), entity.getUpdatedBy());
  }

}
