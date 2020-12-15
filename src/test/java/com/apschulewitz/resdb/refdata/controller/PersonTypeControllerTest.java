package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.PersonTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.PersonTypeDto;
import com.apschulewitz.resdb.refdata.service.PersonTypeService;
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
public class PersonTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private PersonTypeController controller;

  @MockBean
  private PersonTypeService mockedService;

  @Autowired
  private PersonTypeDao personTypeDao;

  @Before
  public void beforeEachTest() {
    personTypeDao.deleteAll();
    controller = new PersonTypeController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    PersonTypeDto dto1 = PersonTypeDto.builder()
      .createdBy("system")
      .name("Person type 1")
      .build();

    PersonTypeDto dto2 = PersonTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Person type 2")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(dto1, dto2));

    // When
    ResponseEntity<List<PersonTypeDto>> responseEntity = controller.findAll(false);

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
//    PersonType unsaved1 = PersonType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Person type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PersonType unsaved2 = PersonType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Person type 2")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));
//
//    // When
//    ResponseEntity<List<PersonType>> responseEntity = controller.findAll(true);
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
    PersonTypeDto unsaved = PersonTypeDto.builder()
      .createdBy("system")
      .name("Person type 1")
      .build();

    PersonTypeDto saved = PersonTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Person type 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<PersonTypeDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_marked_as_cancelled() {
    // Given
    PersonTypeDto saved = PersonTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Person type 1")
      .status(VersionStatus.New.name())
      .build();

    PersonTypeDto deleted = PersonTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Person type 1")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<PersonTypeDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    PersonType unsaved = PersonType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Person type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PersonType saved = PersonType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Person type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PersonType deleted = PersonType.builder()
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
//    ResponseEntity<PersonType> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    PersonTypeDto saved = PersonTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Person type 1")
      .status(VersionStatus.New.name())
      .build();

    PersonTypeDto updated = PersonTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Person type 1a")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
    ResponseEntity<PersonTypeDto> responseUpdatedEntity = controller.update(saved);

    // Then
    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    PersonType unsaved = PersonType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Person type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PersonType saved = PersonType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Person type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    PersonType updated = PersonType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Person type 1")
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
//    ResponseEntity<PersonType> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<PersonType> responseUpdatedEntity = controller.update(updated);
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
