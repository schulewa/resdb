package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.refdata.model.helper.RegionTestHelper;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@ContextConfiguration(classes = {RegionMapper.class, RegionTestHelper.class})
@RunWith(SpringRunner.class)
@WithMockUser("testuser")
public class RegionMapperTest {

  @Autowired
  private RegionMapper regionMapper;

  @Autowired
  private RegionTestHelper regionTestHelper;

  @Test
  public void test_context() {
    assertNotNull(regionMapper);
  }

  @Test
  public void given_entity_and_all_values_when_toDto_is_executed_then_return_dto() {
    // given
    Region entity = regionTestHelper.constructUnsavedMinimalEntity();
    entity.setName("Europe");

    // when
    RegionDto dto = regionMapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getLastUpdated());
    assertEquals(entity.getName(), dto.getName());
    assertNull(dto.getStatus());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
  }

  @Test
  public void given_entity_and_mandatory_values_when_toDto_is_executed_then_return_dto() {
    // given
    Region entity = regionTestHelper.constructUnsavedMinimalEntity();
    entity.setName("Europe");

    // when
    RegionDto dto = regionMapper.toDto(entity);

    // then
    assertNotNull(dto);
    assertNull(dto.getCreatedBy());
    assertNull(dto.getId());
    assertNull(dto.getLastUpdated());
    assertEquals(entity.getName(), dto.getName());
    assertNull(dto.getStatus());
    assertNull(dto.getUpdatedBy());
    assertNull(dto.getVersionNumber());
  }

  @Test
  public void given_dto_and_all_values_when_toEntity_is_executed_then_return_entity() {
    // given
    RegionDto dto = regionTestHelper.constructUnsavedMinimalDto();
    dto.setName("Europe");

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
    RegionDto dto = regionTestHelper.constructUnsavedMinimalDto();
    dto.setName("Europe");

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
