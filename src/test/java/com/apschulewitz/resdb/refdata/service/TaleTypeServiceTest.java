package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.helper.TaleTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.dao.TaleTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.TaleTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.TaleType;
import com.apschulewitz.resdb.refdata.model.mapper.TaleTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@Ignore
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TaleTypeServiceTest {

  private TaleTypeService taleTypeService;

  @Autowired
  private TaleTypeTestHelper taleTypeTestHelper;

  @Autowired
  private TaleTypeMapper taleTypeMapper;

  @Autowired
  private TaleTypeDao taleTypeDao;

  @MockBean
  private TaleTypeDao mockedDao;

  @Before
  public void beforeEachTest() {
    taleTypeService = new TaleTypeService(mockedDao, taleTypeMapper);
    taleTypeDao.deleteAll();
    reset(mockedDao);
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    // given
    TaleType entity = taleTypeTestHelper.constructUnsavedMinimalEntity();
    TaleType saved = taleTypeTestHelper.constructUnsavedMinimalEntity();
    TaleTypeDto dto = taleTypeMapper.toDto(entity);
    when(mockedDao.save(any(TaleType.class))).thenReturn(saved);

    // when
    TaleTypeDto savedDto = taleTypeService.add(dto);

    // then
    assertNotNull(savedDto);
    assertNotNull(savedDto.getId());
    assertEquals(entity.getCreatedBy(), savedDto.getCreatedBy());
    assertEquals(entity.getLastUpdated(), savedDto.getLastUpdated());
    assertEquals(entity.getName(), savedDto.getName());
    assertEquals(entity.getStatus().name(), savedDto.getStatus());
    assertEquals(entity.getUpdatedBy(), savedDto.getUpdatedBy());
    assertEquals(entity.getVersionNumber(), savedDto.getVersionNumber());
  }

  @Test
  public void given_existing_id_when_deleteById_is_executed_then_return_dto_marked_as_cancelled() {
    // given
    TaleType existingEntity = taleTypeTestHelper.constructUnsavedMinimalEntity();
    TaleTypeDto existingDto = taleTypeTestHelper.constructUnsavedMinimalDto();
    when(mockedDao.findById(existingDto.getId())).thenReturn(Optional.of(existingEntity));
    when(mockedDao.save(any(TaleType.class))).thenReturn(existingEntity);

    // when
    TaleTypeDto deletedDto = taleTypeService.deleteById(existingDto.getId());

    // then
    assertNotNull(deletedDto);
  }

  @Test
  public void given_entities_when_findAll_is_executed_then_return_list() {
    // given
    TaleType existingEntity1 = taleTypeTestHelper.constructUnsavedMinimalEntity();
    TaleType existingEntity2 = taleTypeTestHelper.constructUnsavedMinimalEntity();
    when(mockedDao.findAll()).thenReturn(Arrays.asList(existingEntity1, existingEntity2));

    // when
    List<TaleTypeDto> allEntities = taleTypeService.findAll(false);

    // then
    assertNotNull(allEntities);
    assertTrue(allEntities.size() == 2);
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_data() {
    // given
    TaleType existingEntity1 = taleTypeTestHelper.constructUnsavedMinimalEntity();
    when(mockedDao.findById(anyLong())).thenReturn(Optional.of(existingEntity1));

    // when
    TaleTypeDto found = taleTypeService.findById(1L);

    // then
    assertNotNull(found);
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    TaleType entity = taleTypeTestHelper.constructUnsavedMinimalEntity();
    entity.setId(null);
    TaleType saved = taleTypeTestHelper.constructUnsavedMinimalEntity();
    TaleTypeDto dto = taleTypeMapper.toDto(entity);
    when(mockedDao.save(any(TaleType.class))).thenReturn(saved);
    TaleTypeDto savedDto = taleTypeService.add(dto);

    // when
    TaleType updated = entity;
    updated.setId(1L);
    TaleTypeDto updatedDto = savedDto;
    updatedDto.setName("Tale type1 - modified");
    when(mockedDao.save(any(TaleType.class))).thenReturn(updated);
    updatedDto = taleTypeService.update(updatedDto);

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
    TaleType entity = taleTypeTestHelper.constructUnsavedMinimalEntity();
    entity.setId(null);
    TaleType saved = taleTypeTestHelper.constructUnsavedMinimalEntity();
    TaleTypeDto dto = taleTypeMapper.toDto(entity);
    when(mockedDao.save(any(TaleType.class))).thenReturn(saved);
    TaleTypeDto savedDto = taleTypeService.add(dto);
    savedDto.setStatus(VersionStatus.Amend.name());
    savedDto.setUpdatedBy("testuser");
    savedDto.setLastUpdated(ZonedDateTime.now());

    // when
    TaleType updated = entity;
    updated.setId(1L);
    TaleTypeDto updatedDto = savedDto;
    updatedDto.setName("Tale type1 - modified");
    when(mockedDao.save(any(TaleType.class))).thenReturn(updated);
    updatedDto = taleTypeService.update(updatedDto);

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
}
