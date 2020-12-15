package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.AddressTypeTestHelper;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.model.mapper.AddressTypeMapper;
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
public class AddressTypeServiceTest {

  private AddressTypeService addressTypeService;

  @Autowired
  private AddressTypeMapper addressTypeMapper;

  @Autowired
  private AddressTypeDao addressTypeDao;

  @MockBean
  private AddressTypeDao mockedDao;

  @Before
  public void beforeEachTest() {
    addressTypeService = new AddressTypeService(mockedDao, addressTypeMapper);
    addressTypeDao.deleteAll();
    reset(mockedDao);
  }

  @Test
  public void given_valid_dto_when_add_is_executed_then_return_saved_dto() {
    // given
    AddressType entity = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Address type1");
    entity.setId(null);
    AddressType saved = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(1L, entity.getName());
    AddressTypeDto dto = addressTypeMapper.toDto(entity);
    when(mockedDao.save(any(AddressType.class))).thenReturn(saved);

    // when
    AddressTypeDto savedDto = addressTypeService.add(dto);

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
    AddressType existingEntity = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(2L, "Address type2");
    AddressTypeDto existingDto = AddressTypeTestHelper.constructNewDtoWithMandatoryValues(existingEntity.getId(), existingEntity.getName());
    when(mockedDao.findById(existingDto.getId())).thenReturn(Optional.of(existingEntity));
    when(mockedDao.save(any(AddressType.class))).thenReturn(existingEntity);

    // when
    AddressTypeDto deletedDto = addressTypeService.deleteById(existingDto.getId());

    // then
    assertNotNull(deletedDto);
  }

  @Test
  public void given_entities_when_findAll_is_executed_then_return_list() {
    // given
    AddressType existingEntity1 = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Address type1");
    AddressType existingEntity2 = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(2L, "Address type2");
    when(mockedDao.findAll()).thenReturn(Arrays.asList(existingEntity1, existingEntity2));

    // when
    List<AddressTypeDto> allEntities = addressTypeService.findAll(false);

    // then
    assertNotNull(allEntities);
    assertTrue(allEntities.size() == 2);
  }

  @Test
  public void given_valid_id_when_findById_is_executed_then_return_data() {
    // given
    AddressType existingEntity1 = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Address type1");
    when(mockedDao.findById(anyLong())).thenReturn(Optional.of(existingEntity1));

    // when
    AddressTypeDto found = addressTypeService.findById(1L);

    // then
    assertNotNull(found);
  }

  @Test
  public void given_valid_dto_with_minimal_data_when_update_is_executed_then_return_saved_dto() {
    // given
    AddressType entity = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Address type1");
    entity.setId(null);
    AddressType saved = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Address type1");
    AddressTypeDto dto = addressTypeMapper.toDto(entity);
    when(mockedDao.save(any(AddressType.class))).thenReturn(saved);
    AddressTypeDto savedDto = addressTypeService.add(dto);

    // when
    AddressType updated = entity;
    updated.setId(1L);
    AddressTypeDto updatedDto = savedDto;
    updatedDto.setName("Address type1 - modified");
    when(mockedDao.save(any(AddressType.class))).thenReturn(updated);
    updatedDto = addressTypeService.update(updatedDto);

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
    AddressType entity = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Address type1");
    entity.setId(null);
    AddressType saved = AddressTypeTestHelper.constructNewEntityWithMandatoryValues(1L, "Address type1");
    AddressTypeDto dto = addressTypeMapper.toDto(entity);
    when(mockedDao.save(any(AddressType.class))).thenReturn(saved);
    AddressTypeDto savedDto = addressTypeService.add(dto);
    savedDto.setStatus(VersionStatus.Amend.name());
    savedDto.setUpdatedBy("testuser");
    savedDto.setLastUpdated(ZonedDateTime.now());

    // when
    AddressType updated = entity;
    updated.setId(1L);
    AddressTypeDto updatedDto = savedDto;
    updatedDto.setName("Address type1 - modified");
    when(mockedDao.save(any(AddressType.class))).thenReturn(updated);
    updatedDto = addressTypeService.update(updatedDto);

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
