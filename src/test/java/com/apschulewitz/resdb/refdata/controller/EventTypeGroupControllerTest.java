package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.EventTypeGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.EventTypeGroupDto;
import com.apschulewitz.resdb.refdata.service.EventTypeGroupService;
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
public class EventTypeGroupControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private EventTypeGroupController controller;

  @Autowired
  private EventTypeGroupDao eventTypeGroupDao;

  @MockBean
  private EventTypeGroupService mockedService;

  @Before
  public void beforeEachTest() {
    eventTypeGroupDao.deleteAll();
    controller = new EventTypeGroupController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    EventTypeGroupDto dto1 = EventTypeGroupDto.builder()
      .createdBy("system")
      .name("Event type group 1")
      .build();

    EventTypeGroupDto dto2 = EventTypeGroupDto.builder()
      .createdBy("system")
      .name("Event type group 2")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(dto1, dto2));

    // When
    ResponseEntity<List<EventTypeGroupDto>> responseEntityList = controller.findAll(false);

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
//    EventTypeGroup unsaved1 = EventTypeGroup.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Event type group 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EventTypeGroup unsaved2 = EventTypeGroup.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Event type group 2")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));
//
//    // When
//    ResponseEntity<List<EventTypeGroup>> responseEntityList = controller.findAll(true);
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
    EventTypeGroupDto unsaved = EventTypeGroupDto.builder()
      .createdBy("system")
      .name("Event type group 1")
      .build();

    EventTypeGroupDto saved = EventTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Event type group 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<EventTypeGroupDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_marked_as_cancelled() {
    // Given
    EventTypeGroupDto saved = EventTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Event type group 1")
      .status(VersionStatus.New.name())
      .build();

    EventTypeGroupDto deleted = EventTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Event type group 1")
      .status(VersionStatus.Cancel.name())
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<EventTypeGroupDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    EventTypeGroup unsaved = EventTypeGroup.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Event type group 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EventTypeGroup saved = EventTypeGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Event type group 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EventTypeGroup deleted = EventTypeGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Event type group 1")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.save(unsaved)).thenReturn(saved);
//
//    when(mockedDao.findById(saved.getId())).thenReturn(Optional.empty());
//
//    // When
//    ResponseEntity<EventTypeGroup> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    EventTypeGroupDto saved = EventTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Event type group 1")
      .status(VersionStatus.New.name())
      .build();

    EventTypeGroupDto updated = EventTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Entity type 1")
      .status(VersionStatus.Amend.name())
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
    ResponseEntity<EventTypeGroupDto> responseUpdatedEntity = controller.update(saved);

    // Then
    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    EventTypeGroup unsaved = EventTypeGroup.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Event type group 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EventTypeGroup saved = EventTypeGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Event type group 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    EventTypeGroup updated = EventTypeGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Event type group 1")
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
//    ResponseEntity<EventTypeGroup> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<EventTypeGroup> responseUpdatedEntity = controller.update(updated);
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
