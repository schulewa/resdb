package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.RaceTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.RaceTypeDto;
import com.apschulewitz.resdb.refdata.service.RaceTypeService;
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
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RaceTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private RaceTypeController controller;

  @MockBean
  private RaceTypeService mockedService;

  @Autowired
  private RaceTypeDao raceTypeDao;

  @Before
  public void beforeEachTest() {
    raceTypeDao.deleteAll();
    controller = new RaceTypeController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    RaceTypeDto dto1 = RaceTypeDto.builder()
      .createdBy("system")
      .name("Race type 1")
      .status(VersionStatus.New.name())
      .build();

    RaceTypeDto dto2 = RaceTypeDto.builder()
      .createdBy("system")
      .name("Race type 2")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(dto1, dto2));

    // When
    ResponseEntity<List<RaceTypeDto>> responseEntity = controller.findAll(false);

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
//    RaceType unsaved1 = RaceType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Race type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    RaceType unsaved2 = RaceType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Race type 2")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));
//
//    // When
//    ResponseEntity<List<RaceType>> responseEntity = controller.findAll(true);
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
    RaceTypeDto unsaved = RaceTypeDto.builder()
      .createdBy("system")
      .name("Race type 1")
      .build();

    RaceTypeDto saved = RaceTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Race type 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<RaceTypeDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_marked_as_cancelled() {
    // Given
    RaceTypeDto saved = RaceTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Race type 1")
      .status(VersionStatus.New.name())
      .build();

    RaceTypeDto deleted = RaceTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<RaceTypeDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    RaceType unsaved = RaceType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Race type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    RaceType saved = RaceType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Race type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    RaceType deleted = RaceType.builder()
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
//    ResponseEntity<RaceType> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    RaceTypeDto saved = RaceTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Race type 1")
      .status(VersionStatus.New.name())
      .build();

    RaceTypeDto updated = RaceTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1b")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
    ResponseEntity<RaceTypeDto> responseUpdatedEntity = controller.update(saved);

    // Then
    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    RaceType unsaved = RaceType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Race type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    RaceType saved = RaceType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Race type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    RaceType updated = RaceType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Race type 1")
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
//    ResponseEntity<RaceType> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<RaceType> responseUpdatedEntity = controller.update(updated);
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
