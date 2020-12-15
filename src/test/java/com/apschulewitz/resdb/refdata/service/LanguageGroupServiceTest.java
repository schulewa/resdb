package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.LanguageGroupTestHelper;
import com.apschulewitz.resdb.refdata.model.dao.LanguageGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.refdata.model.mapper.LanguageGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class LanguageGroupServiceTest {

  private LanguageGroupService languageGroupService;

  @Autowired
  private LanguageGroupMapper languageGroupMapper;

  @Autowired
  private LanguageGroupDao languageGroupDao;

  @MockBean
  private LanguageGroupDao mockedDao;

  @Before
  public void beforeEachTest() {
    languageGroupService = new LanguageGroupService(mockedDao, languageGroupMapper);
    languageGroupDao.deleteAll();
    reset(mockedDao);
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    // given
    LanguageGroup entity = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(1L, "Language Group1");
    entity.setId(null);
    LanguageGroup saved = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(1L, entity.getName());
    LanguageGroupDto dto = languageGroupMapper.toDto(entity);
    when(mockedDao.save(any(LanguageGroup.class))).thenReturn(saved);

    // when
    LanguageGroupDto savedDto = languageGroupService.add(dto);

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
    LanguageGroup existingEntity = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(2L, "Language Group2");
    LanguageGroupDto existingDto = LanguageGroupTestHelper.constructNewLanguageGroupDtoWithMandatoryValues(existingEntity.getId(), existingEntity.getName());
    when(mockedDao.findById(existingDto.getId())).thenReturn(Optional.of(existingEntity));
    when(mockedDao.save(any(LanguageGroup.class))).thenReturn(existingEntity);

    // when
    LanguageGroupDto deletedDto = languageGroupService.deleteById(existingDto.getId());

    // then
    assertNotNull(deletedDto);
  }

  @Test
  public void given_entities_when_findAll_is_executed_then_return_list() {
    // given
    LanguageGroup existingEntity1 = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(1L, "Language Group1");
    LanguageGroup existingEntity2 = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(2L, "Language Group2");
    when(mockedDao.findAll()).thenReturn(Arrays.asList(existingEntity1, existingEntity2));

    // when
    List<LanguageGroupDto> allEntities = languageGroupService.findAll(false);

    // then
    assertNotNull(allEntities);
    assertTrue(allEntities.size() == 2);
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_data() {
    // given
    LanguageGroup existingEntity1 = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(1L, "Language Group1");
    when(mockedDao.findById(anyLong())).thenReturn(Optional.of(existingEntity1));

    // when
    LanguageGroupDto found = languageGroupService.findById(1L);

    // then
    assertNotNull(found);
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    LanguageGroup entity = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(1L, "Language Group1");
    entity.setId(null);
    LanguageGroup saved = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(1L, "Language Group1");
    LanguageGroupDto dto = languageGroupMapper.toDto(entity);
    when(mockedDao.save(any(LanguageGroup.class))).thenReturn(saved);
    LanguageGroupDto savedDto = languageGroupService.add(dto);

    // when
    LanguageGroup updated = entity;
    updated.setId(1L);
    LanguageGroupDto updatedDto = savedDto;
    updatedDto.setName("Language Group1 - modified");
    when(mockedDao.save(any(LanguageGroup.class))).thenReturn(updated);
    updatedDto = languageGroupService.update(updatedDto);

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
    LanguageGroup entity = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(1L, "Language Group1");
    entity.setId(null);
    LanguageGroup saved = LanguageGroupTestHelper.constructNewLanguageGroupWithMandatoryValues(1L, "Language Group1");
    LanguageGroupDto dto = languageGroupMapper.toDto(entity);
    when(mockedDao.save(any(LanguageGroup.class))).thenReturn(saved);
    LanguageGroupDto savedDto = languageGroupService.add(dto);
    savedDto.setStatus(VersionStatus.Amend.name());
    savedDto.setUpdatedBy("testuser");
    savedDto.setLastUpdated(ZonedDateTime.now());

    // when
    LanguageGroup updated = entity;
    updated.setId(1L);
    LanguageGroupDto updatedDto = savedDto;
    updatedDto.setName("Language Group1 - modified");
    when(mockedDao.save(any(LanguageGroup.class))).thenReturn(updated);
    updatedDto = languageGroupService.update(updatedDto);

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
