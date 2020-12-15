package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.LanguageGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.LanguageGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.LanguageGroup;
import com.apschulewitz.resdb.refdata.service.LanguageGroupService;
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
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LanguageGroupControllerTest {

  private ZonedDateTime now = ZonedDateTime.now();

  private LanguageGroupController controller;

  @MockBean
  private LanguageGroupService mockedService;

  @Autowired
  private LanguageGroupDao languageGroupDao;

  @Before
  public void beforeEachTest() {
    languageGroupDao.deleteAll();
    controller = new LanguageGroupController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    LanguageGroupDto dto1 = LanguageGroupDto.builder()
      .createdBy("system")
      .name("Language group 1")
      .build();

    LanguageGroupDto dto2 = LanguageGroupDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Language group 2")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(dto1, dto2));

    // When
    ResponseEntity<List<LanguageGroupDto>> responseEntity = controller.findAll(false);

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(2, responseEntity.getBody().size());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void when_findAll_is_executed_then_return_list() {
//    // Given
//    LanguageGroupDto dto1 = LanguageGroupDto.builder()
//      .createdBy("system")
//      .name("Language group 1")
//      .build();
//
//    LanguageGroupDto dto2 = LanguageGroupDto.builder()
//      .createdBy("system")
//      .id(1L)
//      .lastUpdated(now)
//      .name("Language group 1")
//      .status(VersionStatus.Cancel.name())
//      .updatedBy("system")
//      .build();
//
//    when(mockedService.findAll(false)).thenReturn(Arrays.asList(dto1, dto2));
//
//    // When
//    ResponseEntity<List<LanguageGroupDto>> responseEntity = controller.findAll(false);
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
    LanguageGroupDto unsaved = LanguageGroupDto.builder()
      .createdBy("system")
      .name("Language group 1")
      .build();

    LanguageGroupDto saved = LanguageGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Language group 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<LanguageGroupDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_marked_as_cancelled() {
    // Given
    LanguageGroupDto saved = LanguageGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Language group 1")
      .status(VersionStatus.New.name())
      .build();

    LanguageGroupDto deleted = LanguageGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Language group 1")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<LanguageGroupDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    LanguageGroup unsaved = LanguageGroup.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Language group 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    LanguageGroup saved = LanguageGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Language group 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    LanguageGroup deleted = LanguageGroup.builder()
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
//    ResponseEntity<LanguageGroup> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    LanguageGroupDto saved = LanguageGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Language group 1")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    LanguageGroupDto updated = LanguageGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Language group 2")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
    ResponseEntity<LanguageGroupDto> responseUpdatedEntity = controller.update(saved);

    // Then
    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    LanguageGroup unsaved = LanguageGroup.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Language group 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    LanguageGroup saved = LanguageGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Language group 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    LanguageGroup updated = LanguageGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Language group 1")
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
//    ResponseEntity<LanguageGroup> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<LanguageGroup> responseUpdatedEntity = controller.update(updated);
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
