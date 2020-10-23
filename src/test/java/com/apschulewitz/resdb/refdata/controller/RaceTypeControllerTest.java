package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.RaceTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.RaceType;
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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RaceTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private RaceTypeController controller;

  @MockBean
  private RaceTypeDao mockedDao;

  private HttpServletRequest mockedRequest = mock(HttpServletRequest.class);

  @Before
  public void beforeEachTest() {
    controller = new RaceTypeController(mockedDao);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_none_when_findAll_is_executed_then_return_list() {
    // Given

    RaceType unsaved1 = RaceType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType unsaved2 = RaceType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 2")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    when(mockedDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(unsaved1, unsaved2));

    // When
    ResponseEntity<List<RaceType>> responseEntity = controller.findAll();

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(2, responseEntity.getBody().size());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_entity_when_save_is_executed_then_return_saved_entity() {
    // Given
    RaceType unsaved = RaceType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType saved = RaceType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    when(mockedDao.save(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<RaceType> responseEntity = controller.add(mockedRequest, unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_existing_entity_when_delete_is_executed_then_return_mark_entity_as_cancelled() {
    // Given
    RaceType unsaved = RaceType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType saved = RaceType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType deleted = RaceType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.Cancel)
      .updatedBy("system")
      .build();

    when(mockedDao.save(unsaved)).thenReturn(saved);

    when(mockedDao.save(saved)).thenReturn(deleted);

    when(mockedDao.findById(saved.getId())).thenReturn(Optional.of(saved));

    // When
    ResponseEntity<RaceType> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedDao, times(1)).save(any(RaceType.class));

    assertEquals(deleted, responseEntity.getBody());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
    // Given
    RaceType unsaved = RaceType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType saved = RaceType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType deleted = RaceType.builder()
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
    ResponseEntity<RaceType> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertNull(responseEntity.getBody());
  }

  @Test
  @WithMockUser(value = "adrian")
  public void given_new_entity_when_update_is_executed_then_return_updated_entity() {
    // Given
    RaceType unsaved = RaceType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType saved = RaceType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType updated = RaceType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.Amend)
      .updatedBy("system")
      .build();

    when(mockedDao.save(unsaved)).thenReturn(saved);

    when(mockedDao.save(saved)).thenReturn(updated);

    when(mockedDao.save(updated)).thenReturn(updated);

    when(mockedDao.findById(saved.getId())).thenReturn(Optional.of(saved));

    // When
    ResponseEntity<RaceType> responseEntity = controller.add(mockedRequest, unsaved);

    ResponseEntity<RaceType> responseUpdatedEntity = controller.update(updated);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());

    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

  @Test
  @WithMockUser(value = "adrian")
  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
    // Given
    RaceType unsaved = RaceType.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType saved = RaceType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.New)
      .updatedBy("system")
      .build();

    RaceType updated = RaceType.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Race type 1")
      .status(VersionStatus.Amend)
      .updatedBy("system")
      .build();

    when(mockedDao.save(unsaved)).thenReturn(saved);

    when(mockedDao.save(saved)).thenReturn(updated);

    when(mockedDao.save(updated)).thenReturn(updated);

    when(mockedDao.findById(saved.getId())).thenReturn(Optional.empty());

    // When
    ResponseEntity<RaceType> responseEntity = controller.add(mockedRequest, unsaved);

    ResponseEntity<RaceType> responseUpdatedEntity = controller.update(updated);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());

    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.NOT_FOUND, responseUpdatedEntity.getStatusCode());
  }
}
