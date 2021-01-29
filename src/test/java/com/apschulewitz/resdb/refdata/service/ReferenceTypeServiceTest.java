package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.helper.ReferenceTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.dao.ReferenceTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.ReferenceTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ReferenceType;
import com.apschulewitz.resdb.refdata.model.mapper.ReferenceTypeMapper;
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
public class ReferenceTypeServiceTest {

  private ReferenceTypeService referenceTypeService;

  @Autowired
  private ReferenceTypeTestHelper referenceTypeTestHelper;

  @Autowired
  private ReferenceTypeMapper referenceTypeMapper;

  @Autowired
  private ReferenceTypeDao referenceTypeDao;

  @MockBean
  private ReferenceTypeDao mockedDao;

  @Before
  public void beforeEachTest() {
    referenceTypeService = new ReferenceTypeService(mockedDao, referenceTypeMapper);
    referenceTypeDao.deleteAll();
    reset(mockedDao);
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    // given
    ReferenceType entity = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    entity.setId(null);
    ReferenceType saved = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    ReferenceTypeDto dto = referenceTypeMapper.toDto(entity);
    when(mockedDao.save(any(ReferenceType.class))).thenReturn(saved);

    // when
    ReferenceTypeDto savedDto = referenceTypeService.add(dto);

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
    ReferenceType existingEntity = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    ReferenceTypeDto existingDto = referenceTypeTestHelper.constructUnsavedMinimalDto();
    when(mockedDao.findById(existingDto.getId())).thenReturn(Optional.of(existingEntity));
    when(mockedDao.save(any(ReferenceType.class))).thenReturn(existingEntity);

    // when
    ReferenceTypeDto deletedDto = referenceTypeService.deleteById(existingDto.getId());

    // then
    assertNotNull(deletedDto);
  }

  @Test
  public void given_entities_when_findAll_is_executed_then_return_list() {
    // given
    ReferenceType existingEntity1 = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    ReferenceType existingEntity2 = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    when(mockedDao.findAll()).thenReturn(Arrays.asList(existingEntity1, existingEntity2));

    // when
    List<ReferenceTypeDto> allEntities = referenceTypeService.findAll(false);

    // then
    assertNotNull(allEntities);
    assertTrue(allEntities.size() == 2);
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_data() {
    // given
    ReferenceType existingEntity1 = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    when(mockedDao.findById(anyLong())).thenReturn(Optional.of(existingEntity1));

    // when
    ReferenceTypeDto found = referenceTypeService.findById(1L);

    // then
    assertNotNull(found);
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    ReferenceType entity = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    entity.setId(null);
    ReferenceType saved = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    ReferenceTypeDto dto = referenceTypeMapper.toDto(entity);
    when(mockedDao.save(any(ReferenceType.class))).thenReturn(saved);
    ReferenceTypeDto savedDto = referenceTypeService.add(dto);

    // when
    ReferenceType updated = entity;
    updated.setId(1L);
    ReferenceTypeDto updatedDto = savedDto;
    updatedDto.setName("Reference type1 - modified");
    when(mockedDao.save(any(ReferenceType.class))).thenReturn(updated);
    updatedDto = referenceTypeService.update(updatedDto);

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
    ReferenceType entity = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    entity.setId(null);
    ReferenceType saved = referenceTypeTestHelper.constructUnsavedMinimalEntity();
    ReferenceTypeDto dto = referenceTypeMapper.toDto(entity);
    when(mockedDao.save(any(ReferenceType.class))).thenReturn(saved);
    ReferenceTypeDto savedDto = referenceTypeService.add(dto);
    savedDto.setStatus(VersionStatus.Amend.name());
    savedDto.setUpdatedBy("testuser");
    savedDto.setLastUpdated(ZonedDateTime.now());

    // when
    ReferenceType updated = entity;
    updated.setId(1L);
    ReferenceTypeDto updatedDto = savedDto;
    updatedDto.setName("Reference type1 - modified");
    when(mockedDao.save(any(ReferenceType.class))).thenReturn(updated);
    updatedDto = referenceTypeService.update(updatedDto);

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
