package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.HierarchyTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.HierarchyType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HierarchyTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private HierarchyTypeController controller;

  @MockBean
  private HierarchyTypeDao mockedDao;

  private HttpServletRequest mockedRequest = mock(HttpServletRequest.class);

  @Before
  public void beforeEachTest() {
    mockedDao.deleteAll();
    controller = new HierarchyTypeController(mockedDao);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_none_when_findAll_is_executed_then_return_list() {
    // Given

    HierarchyType unsaved1 = HierarchyType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType unsaved2 = HierarchyType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 2")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));

    // When
    ResponseEntity<List<HierarchyType>> responseHierarchyList = controller.findAll();

    // Then
    assertEquals(HttpStatus.OK, responseHierarchyList.getStatusCode());
    assertNotNull(responseHierarchyList.getBody());
    assertEquals(2, responseHierarchyList.getBody().size());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_entity_when_save_is_executed_then_return_saved_entity() {
    // Given
    HierarchyType unsaved = HierarchyType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType saved = HierarchyType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    when(mockedDao.save(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<HierarchyType> responseHierarchy = controller.add(mockedRequest, unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseHierarchy.getStatusCode());
    assertNotNull(responseHierarchy.getBody());
    assertNotNull(responseHierarchy.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_existing_entity_when_delete_is_executed_then_return_mark_entity_as_cancelled() {
    // Given
    HierarchyType unsaved = HierarchyType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType saved = HierarchyType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType deleted = HierarchyType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.Cancel)
      .updatedBy("system")
      .build();

    when(mockedDao.save(unsaved)).thenReturn(saved);

    when(mockedDao.save(saved)).thenReturn(deleted);

    when(mockedDao.findById(saved.getId())).thenReturn(Optional.of(saved));

    // When
    ResponseEntity<HierarchyType> responseHierarchy = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseHierarchy.getStatusCode());

    verify(mockedDao, times(1)).save(any(HierarchyType.class));

    assertEquals(deleted, responseHierarchy.getBody());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
    // Given
    HierarchyType unsaved = HierarchyType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType saved = HierarchyType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType deleted = HierarchyType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Gregorian")
      .status(VersionStatus.Cancel)
      .updatedBy("system")
      .build();

    when(mockedDao.save(unsaved)).thenReturn(saved);

    when(mockedDao.findById(saved.getId())).thenReturn(Optional.empty());

    // When
    ResponseEntity<HierarchyType> responseHierarchy = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.NOT_FOUND, responseHierarchy.getStatusCode());
    assertNull(responseHierarchy.getBody());
  }

  @Test
  @WithMockUser(value = "adrian")
  public void given_new_entity_when_update_is_executed_then_return_updated_entity() {
    // Given
    HierarchyType unsaved = HierarchyType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType saved = HierarchyType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType updated = HierarchyType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.Amend)
      .updatedBy("system")
      .build();

    when(mockedDao.save(unsaved)).thenReturn(saved);

    when(mockedDao.save(saved)).thenReturn(updated);

    when(mockedDao.save(updated)).thenReturn(updated);

    when(mockedDao.findById(saved.getId())).thenReturn(Optional.of(saved));

    // When
    ResponseEntity<HierarchyType> responseHierarchy = controller.add(mockedRequest, unsaved);

    ResponseEntity<HierarchyType> responseUpdatedHierarchy = controller.update(updated);

    // Then
    assertEquals(HttpStatus.CREATED, responseHierarchy.getStatusCode());
    assertNotNull(responseHierarchy.getBody());
    assertNotNull(responseHierarchy.getBody().getId());

    assertNotNull(responseUpdatedHierarchy);
    assertEquals(HttpStatus.OK, responseUpdatedHierarchy.getStatusCode());
  }

  @Test
  @WithMockUser(value = "adrian")
  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
    // Given
    HierarchyType unsaved = HierarchyType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType saved = HierarchyType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    HierarchyType updated = HierarchyType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Hierarchy type 1")
      .status(VersionStatus.Amend)
      .updatedBy("system")
      .build();

    when(mockedDao.save(unsaved)).thenReturn(saved);

    when(mockedDao.save(saved)).thenReturn(updated);

    when(mockedDao.save(updated)).thenReturn(updated);

    when(mockedDao.findById(saved.getId())).thenReturn(Optional.empty());

    // When
    ResponseEntity<HierarchyType> responseHierarchy = controller.add(mockedRequest, unsaved);

    ResponseEntity<HierarchyType> responseUpdatedHierarchy = controller.update(updated);

    // Then
    assertEquals(HttpStatus.CREATED, responseHierarchy.getStatusCode());
    assertNotNull(responseHierarchy.getBody());
    assertNotNull(responseHierarchy.getBody().getId());

    assertNotNull(responseUpdatedHierarchy);
    assertEquals(HttpStatus.NOT_FOUND, responseUpdatedHierarchy.getStatusCode());
  }
}
