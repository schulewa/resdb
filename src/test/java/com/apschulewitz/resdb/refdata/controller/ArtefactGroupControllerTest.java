package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.ArtefactGroupDao;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.entity.ArtefactGroup;
import com.apschulewitz.resdb.refdata.service.ArtefactGroupService;
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
public class ArtefactGroupControllerTest {

  private final LocalDateTime now = LocalDateTime.now();

  private ArtefactGroupController controller;

  @Autowired
  private ArtefactGroupDao artefactGroupDao;

  @MockBean
  private ArtefactGroupService mockedService;

  @Before
  public void beforeEachTest() {
    artefactGroupDao.deleteAll();
    controller = new ArtefactGroupController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    ArtefactGroupDto unsavedArtefactGroup1 = ArtefactGroupDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    ArtefactGroupDto unsavedArtefactGroup2 = ArtefactGroupDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Weapons")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(unsavedArtefactGroup1, unsavedArtefactGroup2));

    // When
    ResponseEntity<List<ArtefactGroupDto>> responseEntityList = controller.findAll(false);

    // Then
    assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
    assertNotNull(responseEntityList.getBody());
    assertEquals(2, responseEntityList.getBody().size());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_none_when_findAll_is_executed__and_onlyactive_is_true_then_return_list() {
//    // Given
//
//    ArtefactGroupDto unsavedArtefactGroup1 = ArtefactGroupDto.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Ceramics")
//      .status(VersionStatus.New.name())
//      .updatedBy("system")
//      .build();
//
//    ArtefactGroupDto unsavedArtefactGroup2 = ArtefactGroupDto.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Weapons")
//      .status(VersionStatus.Cancel.name())
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsavedArtefactGroup1, unsavedArtefactGroup2));
//
//    // When
//    ResponseEntity<List<ArtefactGroup>> responseEntityList = controller.findAll(true);
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
    ArtefactGroupDto unsaved = ArtefactGroupDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    ArtefactGroupDto saved = ArtefactGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<ArtefactGroupDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_mark_entity_as_cancelled() {
    // Given

    ArtefactGroupDto saved = ArtefactGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    ArtefactGroupDto deleted = ArtefactGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<ArtefactGroupDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void when_delete_is_executed_then_return_not_found() {
//    // Given
//    ArtefactGroup unsaved = ArtefactGroup.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Ceramics")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    ArtefactGroup saved = ArtefactGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Ceramics")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    when(mockedDao.save(unsaved)).thenReturn(saved);
//
//    when(mockedDao.findById(saved.getId())).thenReturn(Optional.empty());
//
//    // When
//    ResponseEntity<ArtefactGroup> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    ArtefactGroupDto saved = ArtefactGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .build();

    ArtefactGroupDto updated = ArtefactGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Ceramics")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
    ResponseEntity<ArtefactGroupDto> responseUpdatedEntity = controller.update(updated);

    // Then
    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_new_entity_when_update_is_executed_then_return_motfound() {
//    // Given
//    ArtefactGroup unsaved = ArtefactGroup.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Ceramics")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    ArtefactGroup saved = ArtefactGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Ceramics")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    ArtefactGroup updated = ArtefactGroup.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Ceramics")
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
//    ResponseEntity<ArtefactGroup> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<ArtefactGroup> responseUpdatedEntity = controller.update(updated);
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
