package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.AddressTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.CalendarTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.dao.CalendarTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.entity.CalendarType;
import com.apschulewitz.resdb.refdata.model.mapper.AddressTypeMapper;
import com.apschulewitz.resdb.refdata.model.mapper.CalendarTypeMapper;
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
public class CalendarTypeServiceTest {

  private CalendarTypeService calendarTypeService;

  @Autowired
  private CalendarTypeMapper addressTypeMapper;

  @Autowired
  private CalendarTypeDao addressTypeDao;

  @MockBean
  private CalendarTypeDao mockedDao;

  @Before
  public void beforeEachTest() {
    calendarTypeService = new CalendarTypeService(mockedDao, addressTypeMapper);
    addressTypeDao.deleteAll();
    reset(mockedDao);
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    // given
    CalendarType entity = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Calendar type1");
    entity.setId(null);
    CalendarType saved = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(1L, entity.getName());
    CalendarTypeDto dto = addressTypeMapper.toDto(entity);
    when(mockedDao.save(any(CalendarType.class))).thenReturn(saved);

    // when
    CalendarTypeDto savedDto = calendarTypeService.add(dto);

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
    CalendarType existingEntity = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(2L, "Calendar type2");
    CalendarTypeDto existingDto = CalendarTypeTestHelper.constructNewDtoWithMandatoryValues(existingEntity.getId(), existingEntity.getName());
    when(mockedDao.findById(existingDto.getId())).thenReturn(Optional.of(existingEntity));
    when(mockedDao.save(any(CalendarType.class))).thenReturn(existingEntity);

    // when
    CalendarTypeDto deletedDto = calendarTypeService.deleteById(existingDto.getId());

    // then
    assertNotNull(deletedDto);
  }

  @Test
  public void given_entities_when_findAll_is_executed_then_return_list() {
    // given
    CalendarType existingEntity1 = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Calendar type1");
    CalendarType existingEntity2 = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(2L, "Calendar type2");
    when(mockedDao.findAll()).thenReturn(Arrays.asList(existingEntity1, existingEntity2));

    // when
    List<CalendarTypeDto> allEntities = calendarTypeService.findAll(false);

    // then
    assertNotNull(allEntities);
    assertTrue(allEntities.size() == 2);
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_data() {
    // given
    CalendarType existingEntity1 = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Calendar type1");
    when(mockedDao.findById(anyLong())).thenReturn(Optional.of(existingEntity1));

    // when
    CalendarTypeDto found = calendarTypeService.findById(1L);

    // then
    assertNotNull(found);
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    CalendarType entity = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Calendar type1");
    entity.setId(null);
    CalendarType saved = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Calendar type1");
    CalendarTypeDto dto = addressTypeMapper.toDto(entity);
    when(mockedDao.save(any(CalendarType.class))).thenReturn(saved);
    CalendarTypeDto savedDto = calendarTypeService.add(dto);

    // when
    CalendarType updated = entity;
    updated.setId(1L);
    CalendarTypeDto updatedDto = savedDto;
    updatedDto.setName("Calendar type1 - modified");
    when(mockedDao.save(any(CalendarType.class))).thenReturn(updated);
    updatedDto = calendarTypeService.update(updatedDto);

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
    CalendarType entity = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Calendar type1");
    entity.setId(null);
    CalendarType saved = CalendarTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Calendar type1");
    CalendarTypeDto dto = addressTypeMapper.toDto(entity);
    when(mockedDao.save(any(CalendarType.class))).thenReturn(saved);
    CalendarTypeDto savedDto = calendarTypeService.add(dto);
    savedDto.setStatus(VersionStatus.Amend.name());
    savedDto.setUpdatedBy("testuser");
    savedDto.setLastUpdated(ZonedDateTime.now());

    // when
    CalendarType updated = entity;
    updated.setId(1L);
    CalendarTypeDto updatedDto = savedDto;
    updatedDto.setName("Calendar type1 - modified");
    when(mockedDao.save(any(CalendarType.class))).thenReturn(updated);
    updatedDto = calendarTypeService.update(updatedDto);

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
