package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.CalendarTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.CalendarTypeDto;
import com.apschulewitz.resdb.refdata.service.CalendarTypeService;
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
public class CalendarTypeControllerTest {

  private ZonedDateTime now = ZonedDateTime.now();

  private CalendarTypeController controller;

  @Autowired
  private CalendarTypeDao calendarTypeDao;

  @MockBean
  private CalendarTypeService mockedService;

  @Before
  public void beforeEachTest() {
    calendarTypeDao.deleteAll();
    controller = new CalendarTypeController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    CalendarTypeDto unsaved1 = CalendarTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Gregorian")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    CalendarTypeDto unsaved2 = CalendarTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Julian")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(unsaved1, unsaved2));

    // When
    ResponseEntity<List<CalendarTypeDto>> responseEntityList = controller.findAll(false);

    // Then
    assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
    assertNotNull(responseEntityList.getBody());
    assertEquals(2, responseEntityList.getBody().size());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_add_is_executed_then_return_saved_entity() {
    // Given
    CalendarTypeDto unsaved = CalendarTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Gregorian")
      .build();

    CalendarTypeDto saved = CalendarTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Gregorian")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<CalendarTypeDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_mark_entity_as_cancelled() {
    // Given
    CalendarTypeDto saved = CalendarTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Gregorian")
      .status(VersionStatus.New.name())
      .build();

    CalendarTypeDto deleted = CalendarTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Gregorian")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<CalendarTypeDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    CalendarTypeDto saved = CalendarTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Gregorian")
      .status(VersionStatus.New.name())
      .build();

    CalendarTypeDto updated = CalendarTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Gregorian")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(updated);

    // When
    ResponseEntity<CalendarTypeDto> responseUpdatedEntity = controller.update(updated);

    // Then
    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

}
