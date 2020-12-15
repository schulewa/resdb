package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.RegionTestHelper;
import com.apschulewitz.resdb.refdata.model.dao.RegionDao;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import com.apschulewitz.resdb.refdata.model.mapper.RegionMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
//@Ignore
public class RegionServiceTest {

  private RegionService regionService;

  @Autowired
  private RegionMapper regionMapper;

  @Autowired
  private RegionDao regionDao;

  @MockBean
  private RegionDao mockedDao;

  @Before
  public void beforeEachTest() {
    regionService = new RegionService(mockedDao, regionMapper);
    regionDao.deleteAll();
    reset(mockedDao);
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    // given
    Region entity = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Europe");
    entity.setId(null);
    Region saved = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Europe");
    RegionDto dto = regionMapper.toDto(entity);
    when(mockedDao.save(any(Region.class))).thenReturn(saved);

    // when
    RegionDto savedDto = regionService.add(dto);

    // then
    assertNotNull(savedDto);
    Long versionNumber = 1L;
    assertEquals(versionNumber, savedDto.getId());
    assertEquals(entity.getCreatedBy(), savedDto.getCreatedBy());
    assertEquals(entity.getLastUpdated(), savedDto.getLastUpdated());
    assertEquals(entity.getName(), savedDto.getName());
    assertEquals(entity.getStatus().name(), savedDto.getStatus());
    assertEquals(entity.getUpdatedBy(), savedDto.getUpdatedBy());
    assertEquals(entity.getVersionNumber(), savedDto.getVersionNumber());
  }


  @Test
  public void given_dto_with_id_when_add_is_executed_then_return_error() {
    // given
    RegionDto dto = RegionTestHelper.constructNewRegionDtoWithMandatoryValues(1L, "Europe");

    // when
    RegionDto saved = regionService.add(dto);

    // then
    assertNull(saved);
  }

  @Test
  public void given_existing_id_when_deleteById_is_executed_then_return_dto_marked_as_cancelled() {
    // given
    Region existingEntity = RegionTestHelper.constructNewRegionWithMandatoryValues(2L, "Australia");
    RegionDto existingDto = RegionTestHelper.constructNewRegionDtoWithMandatoryValues(existingEntity.getId(), existingEntity.getName());
    when(mockedDao.findById(existingDto.getId())).thenReturn(Optional.of(existingEntity));
    when(mockedDao.save(any(Region.class))).thenReturn(existingEntity);

    // when
    RegionDto deletedDto = regionService.deleteById(existingDto.getId());

    // then
    assertNotNull(deletedDto);
  }

  @Test
  public void given_nonexisting_id_when_deleteById_is_executed_then_return_null_dto() {
    // given
    Region existingEntity = RegionTestHelper.constructNewRegionWithMandatoryValues(2L, "Australia");
    RegionDto existingDto = RegionTestHelper.constructNewRegionDtoWithMandatoryValues(existingEntity.getId(), existingEntity.getName());
    when(mockedDao.findById(existingDto.getId())).thenReturn(Optional.empty());

    // when
    RegionDto deletedDto = regionService.deleteById(existingDto.getId());

    // then
    assertNull(deletedDto);
  }

  @Test
  public void given_inactive_entity_when_deletedById_is_executed_then_return_null_dto() {
    // given
    Region existingEntity = RegionTestHelper.constructNewRegionWithMandatoryValues(2L, "Australia");
    existingEntity.setStatus(VersionStatus.Cancel);
    RegionDto existingDto = RegionTestHelper.constructNewRegionDtoWithMandatoryValues(existingEntity.getId(), existingEntity.getName());
    when(mockedDao.findById(existingDto.getId())).thenReturn(Optional.of(existingEntity));

    // when
    RegionDto deletedDto = regionService.deleteById(existingDto.getId());

    // then
    assertNull(deletedDto);
  }

  @Test
  public void given_false_when_findAll_is_executed_then_return_active_and_inactive_entities() {
    // given
    Region activeRegion = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Active Region");
    Region inactiveRegion = RegionTestHelper.constructNewRegionWithAllValues(2L, "Inactive Region");
    inactiveRegion.setStatus(VersionStatus.Cancel);
    when(mockedDao.findAll()).thenReturn(Arrays.asList(activeRegion, inactiveRegion));

    // when
    List<RegionDto> allRegions = regionService.findAll(false);

    // then
    assertNotNull(allRegions);
    int expectedSize = 2;
    assertEquals(expectedSize, allRegions.size());
  }

  @Test
  public void given_true_when_findAll_is_executed_then_return_active_and_inactive_entities() {
    // given
    Region activeRegion = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Active Region");
    when(mockedDao.findByStatusIn(anyList())).thenReturn(Arrays.asList(activeRegion));

    // when
    List<RegionDto> activeRegions = regionService.findAll(true);

    // then
    assertNotNull(activeRegions);
    int expectedSize = 1;
    assertEquals(expectedSize, activeRegions.size());
  }

  @Test
  public void given_nonexisting_id_when_findById_is_executed_then_return_null() {
    // given
    Region entity = RegionTestHelper.constructNewRegionWithMandatoryValues(3L, "North America");
    RegionDto regionDto = RegionTestHelper.constructNewRegionDtoWithMandatoryValues(entity.getId(), entity.getName());
    when(mockedDao.findById(regionDto.getId())).thenReturn(Optional.empty());

    // when
    RegionDto found = regionService.findById(regionDto.getId());

    //then
    assertNull(found);
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_entity() {
    // given
    Region entity = RegionTestHelper.constructNewRegionWithMandatoryValues(4L, "Central America");
    RegionDto regionDto = RegionTestHelper.constructNewRegionDtoWithMandatoryValues(entity.getId(), entity.getName());
    when(mockedDao.findById(regionDto.getId())).thenReturn(Optional.of(entity));

    // when
    RegionDto found = regionService.findById(regionDto.getId());

    //then
    assertNotNull(found);
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    Region entity = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Europe");
    entity.setId(null);
    Region saved = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Europe");
    RegionDto dto = regionMapper.toDto(entity);
    when(mockedDao.save(any(Region.class))).thenReturn(saved);
    RegionDto savedDto = regionService.add(dto);

    // when
    Region updated = entity;
    updated.setId(1L);
    RegionDto updatedDto = savedDto;
    updatedDto.setName("Europe - modified");
    when(mockedDao.save(any(Region.class))).thenReturn(updated);
    updatedDto = regionService.update(updatedDto);

    // then
    assertNotNull(updatedDto);
    Long versionNumber = 1L;
    assertEquals(versionNumber, updatedDto.getId());
    assertEquals(entity.getCreatedBy(), updatedDto.getCreatedBy());
    assertEquals(entity.getLastUpdated(), updatedDto.getLastUpdated());
    assertEquals(entity.getName(), updatedDto.getName());
    assertEquals(entity.getStatus().name(), updatedDto.getStatus());
    assertEquals(entity.getUpdatedBy(), updatedDto.getUpdatedBy());
    assertEquals(entity.getVersionNumber(), updatedDto.getVersionNumber());
  }

  @Test
  public void given_valid_amended_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    Region entity = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Europe");
    entity.setId(null);
    Region saved = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Europe");
    RegionDto dto = regionMapper.toDto(entity);
    when(mockedDao.save(any(Region.class))).thenReturn(saved);
    RegionDto savedDto = regionService.add(dto);
    savedDto.setStatus(VersionStatus.Amend.name());
    savedDto.setUpdatedBy("testuser");
    savedDto.setLastUpdated(ZonedDateTime.now());

    // when
    Region updated = entity;
    updated.setId(1L);
    RegionDto updatedDto = savedDto;
    updatedDto.setName("Europe - modified");
    when(mockedDao.save(any(Region.class))).thenReturn(updated);
    updatedDto = regionService.update(updatedDto);

    // then
    assertNotNull(updatedDto);
    Long versionNumber = 1L;
    assertEquals(versionNumber, updatedDto.getId());
    assertEquals(entity.getCreatedBy(), updatedDto.getCreatedBy());
    assertEquals(entity.getLastUpdated(), updatedDto.getLastUpdated());
    assertEquals(entity.getName(), updatedDto.getName());
    assertEquals(entity.getStatus().name(), updatedDto.getStatus());
    assertEquals(entity.getUpdatedBy(), updatedDto.getUpdatedBy());
    assertEquals(entity.getVersionNumber(), updatedDto.getVersionNumber());
  }

  @Test
  public void given_dto_with_no_id_when_update_is_executed_then_return_null() {
    // given
    Region entity = RegionTestHelper.constructNewRegionWithMandatoryValues(1L, "Europe");
    entity.setId(null);
    RegionDto dto = regionMapper.toDto(entity);

    // when
    Region updated = entity;
    updated.setId(null);
    RegionDto updatedDto = dto;
    updatedDto.setName("Europe - modified");
    when(mockedDao.save(any(Region.class))).thenReturn(updated);
    updatedDto = regionService.update(updatedDto);

    // then
    assertNull(updatedDto);
  }
}
