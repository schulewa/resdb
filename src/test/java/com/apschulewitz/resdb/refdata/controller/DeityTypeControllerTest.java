package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.DeityTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.DeityTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.DeityType;
import com.apschulewitz.resdb.refdata.service.DeityTypeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DeityTypeControllerTest {

  private ZonedDateTime now = ZonedDateTime.now();

  private DeityTypeController controller;

  @Autowired
  private DeityTypeDao deityTypeDao;

  @MockBean
  private DeityTypeService mockedService;

  @Before
  public void beforeEachTest() {
    deityTypeDao.deleteAll();
    controller = new DeityTypeController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    DeityTypeDto unsaved1 = DeityTypeDto.builder()
      .createdBy("system")
      .name("Deity type 1")
      .build();

    DeityTypeDto unsaved2 = DeityTypeDto.builder()
      .createdBy("system")
      .name("Deity type 2")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(unsaved1, unsaved2));

    // When
    ResponseEntity<List<DeityTypeDto>> responseEntityList = controller.findAll(false);

    // Then
    assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
    assertNotNull(responseEntityList.getBody());
    assertEquals(2, responseEntityList.getBody().size());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_none_when_findAll_is_executed_and_onlyactive_is_true_then_return_list() {
//    // Given
//
//    DeityType unsaved1 = DeityType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Deity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    DeityType unsaved2 = DeityType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Deity type 2")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));
//
//    // When
//    ResponseEntity<List<DeityType>> responseEntityList = controller.findAll(true);
//
//    // Then
//    assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
//    assertNotNull(responseEntityList.getBody());
//    assertEquals(1, responseEntityList.getBody().size());
//  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_add_is_executed_then_return_saved_entity() {
    // Given
    DeityTypeDto unsaved = DeityTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Deity type 1")
      .build();

    DeityTypeDto saved = DeityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Deity type 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<DeityTypeDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_mark_entity_as_cancelled() {
    // Given
    DeityType unsaved = DeityType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Deity type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    DeityTypeDto saved = DeityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Deity type 1")
      .status(VersionStatus.New.name())
      .build();

    DeityTypeDto deleted = DeityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Deity type 1")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

//    when(mockedDao.save(unsaved)).thenReturn(saved);
//
//    when(mockedDao.save(saved)).thenReturn(deleted);

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<DeityTypeDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    DeityType unsaved = DeityType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Deity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    DeityType saved = DeityType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Deity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    DeityType deleted = DeityType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Gregorian")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.save(unsaved)).thenReturn(saved);
//
//    when(mockedDao.findById(saved.getId())).thenReturn(Optional.empty());
//
//    // When
//    ResponseEntity<DeityType> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    DeityType unsaved = DeityType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Deity type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    DeityTypeDto saved = DeityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Deity type 1")
      .status(VersionStatus.New.name())
      .build();

    DeityTypeDto updated = DeityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Deity type 1")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
//    ResponseEntity<DeityType> responseEntity = controller.add(unsaved);

    ResponseEntity<DeityTypeDto> responseUpdatedEntity = controller.update(saved);

    // Then
//    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//    assertNotNull(responseEntity.getBody());
//    assertNotNull(responseEntity.getBody().getId());

    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    DeityType unsaved = DeityType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Deity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    DeityType saved = DeityType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Deity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    DeityType updated = DeityType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Deity type 1")
//      .status(VersionStatus.Amend)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.save(unsaved)).thenReturn(saved);
//
//    when(mockedDao.save(saved)).thenReturn(updated);
//
//    when(mockedDao.save(updated)).thenReturn(updated);
//
//    when(mockedDao.findById(saved.getId())).thenReturn(Optional.empty());
//
//    // When
//    ResponseEntity<DeityType> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<DeityType> responseUpdatedEntity = controller.update(updated);
//
//    // Then
//    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//    assertNotNull(responseEntity.getBody());
//    assertNotNull(responseEntity.getBody().getId());
//
//    assertNotNull(responseUpdatedEntity);
//    assertEquals(HttpStatus.NOT_FOUND, responseUpdatedEntity.getStatusCode());
//  }
}
