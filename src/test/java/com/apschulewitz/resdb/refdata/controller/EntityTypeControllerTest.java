package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.EntityTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.EntityTypeDto;
import com.apschulewitz.resdb.refdata.service.EntityTypeService;
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
public class EntityTypeControllerTest {

  private ZonedDateTime now = ZonedDateTime.now();

  private EntityTypeController controller;

  @Autowired
  private EntityTypeDao entityTypeDao;

  @MockBean
  private EntityTypeService mockedService;

  @Before
  public void beforeEachTest() {
    entityTypeDao.deleteAll();
    controller = new EntityTypeController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    EntityTypeDto unsaved1 = EntityTypeDto.builder()
      .createdBy("system")
      .name("Entity type 1")
      .status(VersionStatus.New.name())
      .build();

    EntityTypeDto unsaved2 = EntityTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Entity type 2")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(unsaved1, unsaved2));

    // When
    ResponseEntity<List<EntityTypeDto>> responseEntityList = controller.findAll(false);

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
//    EntityType unsaved1 = EntityType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Entity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EntityType unsaved2 = EntityType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Entity type 2")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));
//
//    // When
//    ResponseEntity<List<EntityType>> responseEntityList = controller.findAll(true);
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
    EntityTypeDto unsaved = EntityTypeDto.builder()
      .createdBy("system")
      .name("Entity type 1")
      .build();

    EntityTypeDto saved = EntityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Entity type 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<EntityTypeDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_marked_as_cancelled() {
    // Given
    EntityTypeDto saved = EntityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Entity type 1")
      .status(VersionStatus.New.name())
      .build();

    EntityTypeDto deleted = EntityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Entity type 1")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<EntityTypeDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    EntityType unsaved = EntityType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Entity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EntityType saved = EntityType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Entity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EntityType deleted = EntityType.builder()
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
//    ResponseEntity<EntityType> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
//    EntityType unsaved = EntityType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Entity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();

    EntityTypeDto saved = EntityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Entity type 1")
      .status(VersionStatus.New.name())
      .build();

    EntityTypeDto updated = EntityTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Entity type 1")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
//    ResponseEntity<EntityType> responseEntity = controller.add(unsaved);

    ResponseEntity<EntityTypeDto> responseUpdatedEntity = controller.update(saved);

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
//    EntityType unsaved = EntityType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Entity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EntityType saved = EntityType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Entity type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EntityType updated = EntityType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Entity type 1")
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
//    ResponseEntity<EntityType> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<EntityType> responseUpdatedEntity = controller.update(updated);
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
