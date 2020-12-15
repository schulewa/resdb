package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.MeasureTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.MeasureTypeDto;
import com.apschulewitz.resdb.refdata.service.MeasureTypeService;
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
public class MeasureTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private MeasureTypeController controller;

  @MockBean
  private MeasureTypeService mockedService;

  @Autowired
  private MeasureTypeDao measureTypeDao;

  @Before
  public void beforeEachTest() {
    measureTypeDao.deleteAll();
    controller = new MeasureTypeController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given
    MeasureTypeDto dto1 = MeasureTypeDto.builder()
      .createdBy("system")
      .name("Measure type 1")
      .build();

    MeasureTypeDto dto2 = MeasureTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Measure type 2")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(dto1, dto2));

    // When
    ResponseEntity<List<MeasureTypeDto>> responseEntity = controller.findAll(false);

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
//    MeasureType unsaved1 = MeasureType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Measure type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    MeasureType unsaved2 = MeasureType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Measure type 2")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));
//
//    // When
//    ResponseEntity<List<MeasureType>> responseEntity = controller.findAll(true);
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
    MeasureTypeDto unsaved = MeasureTypeDto.builder()
      .createdBy("system")
      .name("Measure type 1")
      .build();

    MeasureTypeDto saved = MeasureTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Measure type 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<MeasureTypeDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_marked_as_cancelled() {
    // Given
    MeasureTypeDto saved = MeasureTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Measure type 1")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    MeasureTypeDto deleted = MeasureTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Measure type 1")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<MeasureTypeDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    MeasureType unsaved = MeasureType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Measure type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    MeasureType saved = MeasureType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Measure type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    MeasureType deleted = MeasureType.builder()
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
//    ResponseEntity<MeasureType> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    MeasureTypeDto saved = MeasureTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Measure type 1")
      .build();

    MeasureTypeDto updated = MeasureTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Measure type 1")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
    ResponseEntity<MeasureTypeDto> responseUpdatedEntity = controller.update(saved);

    // Then
    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    MeasureType unsaved = MeasureType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Measure type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    MeasureType saved = MeasureType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Measure type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    MeasureType updated = MeasureType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Measure type 1")
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
//    ResponseEntity<MeasureType> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<MeasureType> responseUpdatedEntity = controller.update(updated);
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
