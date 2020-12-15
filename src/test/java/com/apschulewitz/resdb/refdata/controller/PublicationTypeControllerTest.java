package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.PublicationTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.PublicationTypeDto;
import com.apschulewitz.resdb.refdata.service.PublicationTypeService;
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

import java.time.LocalDateTime;
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
public class PublicationTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private PublicationTypeController controller;

  @MockBean
  private PublicationTypeService mockedService;

  @Autowired
  private PublicationTypeDao publicationTypeDao;

  @Before
  public void beforeEachTest() {
    publicationTypeDao.deleteAll();
    controller = new PublicationTypeController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    PublicationTypeDto dto1 = PublicationTypeDto.builder()
      .createdBy("system")
      .name("Publication type 1")
      .status(VersionStatus.New.name())
      .build();

    PublicationTypeDto dto2 = PublicationTypeDto.builder()
      .createdBy("system")
      .name("Publication type 2")
      .status(VersionStatus.Cancel.name())
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(dto1, dto2));

    // When
    ResponseEntity<List<PublicationTypeDto>> responseEntity = controller.findAll(false);

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(2, responseEntity.getBody().size());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_none_when_findAll_is_executed_and_onlyactive_is_true_then_return_list() {
//    // Given
//
//    PublicationType unsaved1 = PublicationType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Publication type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PublicationType unsaved2 = PublicationType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Publication type 2")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));
//
//    // When
//    ResponseEntity<List<PublicationType>> responseEntity = controller.findAll(true);
//
//    // Then
//    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    assertNotNull(responseEntity.getBody());
//    assertEquals(1, responseEntity.getBody().size());
//  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_add_is_executed_then_return_saved_entity() {
    // Given
    PublicationTypeDto unsaved = PublicationTypeDto.builder()
      .createdBy("system")
      .name("Publication type 1")
      .build();

    PublicationTypeDto saved = PublicationTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Publication type 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<PublicationTypeDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_marked_as_cancelled() {
    // Given
    PublicationTypeDto saved = PublicationTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Publication type 1")
      .status(VersionStatus.New.name())
      .build();

    PublicationTypeDto deleted = PublicationTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Publication type 1")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<PublicationTypeDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    PublicationType unsaved = PublicationType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Publication type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PublicationType saved = PublicationType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Publication type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PublicationType deleted = PublicationType.builder()
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
//    ResponseEntity<PublicationType> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    PublicationTypeDto saved = PublicationTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Publication type 1")
      .status(VersionStatus.New.name())
      .build();

    PublicationTypeDto updated = PublicationTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Publication type 1")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
    ResponseEntity<PublicationTypeDto> responseUpdatedEntity = controller.update(saved);

    // Then
    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    PublicationType unsaved = PublicationType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Publication type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PublicationType saved = PublicationType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Publication type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PublicationType updated = PublicationType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Publication type 1")
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
//    ResponseEntity<PublicationType> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<PublicationType> responseUpdatedEntity = controller.update(updated);
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
