package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.RegionDao;
import com.apschulewitz.resdb.refdata.model.dto.RegionDto;
import com.apschulewitz.resdb.refdata.model.entity.Region;
import com.apschulewitz.resdb.refdata.service.RegionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class RegionControllerTest {

  private ZonedDateTime now = ZonedDateTime.now();

  private RegionController controller;

  @MockBean
  private RegionService mockedService;

  @Before
  public void beforeEachTest() {
    controller = new RegionController(mockedService);
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void when_findAll_is_executed_then_return_list() {
//    // Given
//
//    RegionDto unsaved1 = RegionDto.builder()
//      .createdBy("testuser")
//      .lastUpdated(now)
//      .name("Region 1")
//      .status(VersionStatus.New.name())
//      .build();
//
//    RegionDto unsaved2 = RegionDto.builder()
//      .createdBy("testuser")
//      .lastUpdated(now)
//      .name("Region 2")
//      .status(VersionStatus.Cancel.name())
//      .updatedBy("testuser")
//      .build();
//
//    when(mockedService.findAll(true)).thenReturn(Arrays.asList(unsaved1, unsaved2));
//
//    // When
//    ResponseEntity<List<RegionDto>> responseEntity = controller.findAll(false);
//
//    // Then
//    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//    assertNotNull(responseEntity.getBody());
//    assertEquals(2, responseEntity.getBody().size());
//  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    RegionDto unsaved1 = RegionDto.builder()
      .createdBy("testuser")
      .lastUpdated(now)
      .name("Region 1")
      .status(VersionStatus.New.name())
      .build();

    RegionDto unsaved2 = RegionDto.builder()
      .createdBy("testuser")
      .lastUpdated(now)
      .name("Region 2")
      .status(VersionStatus.Cancel.name())
      .updatedBy("testuser")
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(unsaved1, unsaved2));

    // When
    ResponseEntity<List<RegionDto>> responseEntity = controller.findAll(false);

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(2, responseEntity.getBody().size());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_add_is_executed_then_return_saved_entity() {
    // Given
    RegionDto unsaved = RegionDto.builder()
      .createdBy("testuser")
      .lastUpdated(now)
      .name("Region 1")
      .status(VersionStatus.New.name())
      .build();

    RegionDto saved = RegionDto.builder()
      .id(1L)
      .createdBy("testuser")
      .lastUpdated(now)
      .name("Region 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<RegionDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_cancelled() {
    // Given
//    RegionDto unsaved = RegionDto.builder()
//      .createdBy("testuser")
//      .lastUpdated(now)
//      .name("Region 1")
//      .status(VersionStatus.New.name())
//      .build();
//
    RegionDto saved = RegionDto.builder()
      .id(1L)
      .createdBy("system")
      .name("Region 1")
      .status(VersionStatus.New.name())
      .build();

    RegionDto deleted = RegionDto.builder()
      .id(1L)
      .createdBy("testuser")
      .lastUpdated(now)
      .name("Region 1")
      .status(VersionStatus.Cancel.name())
      .updatedBy("testuser")
      .build();

    when(mockedService.deleteById(saved.getId())).thenReturn(deleted);

    // When
    ResponseEntity<RegionDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_entity_when_delete_is_executed_then_return_not_found() {
//    // Given
//    Region unsaved = Region.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Region 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    Region saved = Region.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Region 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    Region deleted = Region.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Gregorian")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .build();
//
//    when(mockedService.save(unsaved)).thenReturn(saved);
//
//    when(mockedService.findById(saved.getId())).thenReturn(Optional.empty());
//
//    // When
//    ResponseEntity<Region> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_entity() {
    // Given
//    RegionDto unsaved = RegionDto.builder()
//      .createdBy("testuser")
//      .lastUpdated(now)
//      .name("Region 1")
//      .status(VersionStatus.New.name())
//      .updatedBy("testuser")
//      .build();
//
//    Region saved = Region.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Region 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();

    RegionDto updated = RegionDto.builder()
      .id(1L)
      .createdBy("testuser")
      .lastUpdated(now)
      .name("Region 1")
      .status(VersionStatus.Amend.name())
      .updatedBy("testuser")
      .build();

//    when(mockedService.save(unsaved)).thenReturn(saved);
//
//    when(mockedService.save(saved)).thenReturn(updated);
//
//    when(mockedService.save(updated)).thenReturn(updated);
//
//    when(mockedService.findById(saved.getId())).thenReturn(Optional.of(saved));

    // When
//    ResponseEntity<RegionDto> responseEntity = controller.update(updated);

    ResponseEntity<RegionDto> responseUpdatedEntity = controller.update(updated);

    // Then
//    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
//    assertNotNull(responseEntity.getBody());
//    assertNotNull(responseEntity.getBody().getId());

    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    Region unsaved = Region.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Region 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    Region saved = Region.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Region 1")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    Region updated = Region.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Region 1")
//      .status(VersionStatus.Amend)
//      .updatedBy("system")
//      .build();
//
//    when(mockedService.save(unsaved)).thenReturn(saved);
//
//    when(mockedService.save(saved)).thenReturn(updated);
//
//    when(mockedService.save(updated)).thenReturn(updated);
//
//    when(mockedService.findById(saved.getId())).thenReturn(Optional.empty());
//
//    // When
//    ResponseEntity<Region> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<Region> responseUpdatedEntity = controller.update(updated);
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
