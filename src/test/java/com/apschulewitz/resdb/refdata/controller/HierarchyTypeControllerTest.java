package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.HierarchyTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.HierarchyTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
import com.apschulewitz.resdb.refdata.service.HierarchyTypeService;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HierarchyTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private HierarchyTypeController controller;

  @MockBean
  private HierarchyTypeService mockedService;

  @Autowired
  private HierarchyTypeDao hierarchyTypeDao;

  @Before
  public void beforeEachTest() {
    hierarchyTypeDao.deleteAll();
    controller = new HierarchyTypeController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    HierarchyTypeDto dto1 = HierarchyTypeDto.builder()
      .createdBy("system")
      .name("Hierarchy type 1")
      .status(VersionStatus.New.name())
      .build();

    HierarchyTypeDto dto2 = HierarchyTypeDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 2")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(dto1, dto2));

    // When
    ResponseEntity<List<HierarchyTypeDto>> responseHierarchyList = controller.findAll(false);

    // Then
    assertEquals(HttpStatus.OK, responseHierarchyList.getStatusCode());
    assertNotNull(responseHierarchyList.getBody());
    assertEquals(2, responseHierarchyList.getBody().size());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_add_is_executed_then_return_saved_entity() {
    // Given
    HierarchyTypeDto unsaved = HierarchyTypeDto.builder()
      .createdBy("system")
      .name("Hierarchy type 1")
      .status(VersionStatus.New.name())
      .build();

    HierarchyTypeDto saved = HierarchyTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Hierarchy type 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<HierarchyTypeDto> responseHierarchy = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseHierarchy.getStatusCode());
    assertNotNull(responseHierarchy.getBody());
    assertNotNull(responseHierarchy.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_marked_as_cancelled() {
    // Given
    HierarchyTypeDto saved = HierarchyTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Hierarchy type 1")
      .status(VersionStatus.New.name())
      .build();

    HierarchyTypeDto deleted = HierarchyTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<HierarchyTypeDto> responseHierarchy = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseHierarchy.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseHierarchy.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    HierarchyType unsaved = HierarchyType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Hierarchy type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    HierarchyType saved = HierarchyType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Hierarchy type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    HierarchyType deleted = HierarchyType.builder()
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
//    ResponseEntity<HierarchyType> responseHierarchy = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseHierarchy.getStatusCode());
//    assertNull(responseHierarchy.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
    HierarchyTypeDto saved = HierarchyTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Hierarchy type 1")
      .status(VersionStatus.New.name())
      .build();

    HierarchyTypeDto updated = HierarchyTypeDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .build();

    when(mockedService.update(saved)).thenReturn(updated);

    // When
    ResponseEntity<HierarchyTypeDto> responseUpdatedHierarchy = controller.update(saved);

    // Then
    assertNotNull(responseUpdatedHierarchy);
    assertEquals(HttpStatus.OK, responseUpdatedHierarchy.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    HierarchyType unsaved = HierarchyType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Hierarchy type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    HierarchyType saved = HierarchyType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Hierarchy type 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    HierarchyType updated = HierarchyType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Hierarchy type 1")
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
//    ResponseEntity<HierarchyType> responseHierarchy = controller.add(unsaved);
//
//    ResponseEntity<HierarchyType> responseUpdatedHierarchy = controller.update(updated);
//
//    // Then
//    assertEquals(HttpStatus.CREATED, responseHierarchy.getStatusCode());
//    assertNotNull(responseHierarchy.getBody());
//    assertNotNull(responseHierarchy.getBody().getId());
//
//    assertNotNull(responseUpdatedHierarchy);
//    assertEquals(HttpStatus.NOT_FOUND, responseUpdatedHierarchy.getStatusCode());
//  }
}
