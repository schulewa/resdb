package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.TechnologyTypeGroupDto;
import com.apschulewitz.resdb.refdata.service.TechnologyTypeGroupService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

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
//@DataJpaTest
public class TechnologyTypeGroupControllerTest {

  private ZonedDateTime now = ZonedDateTime.now();

  private TechnologyTypeGroupController controller;

  @MockBean
  private TechnologyTypeGroupService mockedService;

  @Before
  public void beforeEachTest() {
    controller = new TechnologyTypeGroupController(mockedService);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_findAll_is_executed_then_return_list() {
    // Given

    TechnologyTypeGroupDto unsaved1 = TechnologyTypeGroupDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 1")
      .status(VersionStatus.New.name())
      .build();

    TechnologyTypeGroupDto unsaved2 = TechnologyTypeGroupDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 2")
      .status(VersionStatus.Cancel.name())
      .build();

    when(mockedService.findAll(false)).thenReturn(Arrays.asList(unsaved1, unsaved2));

    // When
    ResponseEntity<List<TechnologyTypeGroupDto>> responseEntity = controller.findAll(false);

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
//    TechnologyTypeGroupDto unsaved1 = TechnologyTypeGroupDto.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Technology type group 1")
//      .status(VersionStatus.New.name())
//      .build();
//
//    TechnologyTypeGroupDto unsaved2 = TechnologyTypeGroupDto.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Technology type group 2")
//      .status(VersionStatus.Cancel.name())
//      .build();
//
//    when(mockedService.findAll(true)).thenReturn(Arrays.asList(unsaved1, unsaved2));
//
//    // When
//    ResponseEntity<List<TechnologyTypeGroupDto>> responseEntity = controller.findAll(true);
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
    TechnologyTypeGroupDto unsaved = TechnologyTypeGroupDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 1")
      .status(VersionStatus.New.name())
      .build();

    TechnologyTypeGroupDto saved = TechnologyTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 1")
      .status(VersionStatus.New.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    // When
    ResponseEntity<TechnologyTypeGroupDto> responseEntity = controller.add(unsaved);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_mark_entity_as_cancelled() {
    // Given
    TechnologyTypeGroupDto unsaved = TechnologyTypeGroupDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 1")
      .status(VersionStatus.New.name())
      .build();

    TechnologyTypeGroupDto saved = TechnologyTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 1")
      .status(VersionStatus.New.name())
      .build();

    TechnologyTypeGroupDto deleted = TechnologyTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 1")
      .status(VersionStatus.Cancel.name())
      .build();

//    when(mockedService.save(unsaved)).thenReturn(saved);

//    when(mockedService.save(saved)).thenReturn(deleted);

//    when(mockedService.findById(saved.getId())).thenReturn(saved);

    when(mockedService.deleteById(anyLong())).thenReturn(deleted);

    // When
    ResponseEntity<TechnologyTypeGroupDto> responseEntity = controller.delete(saved.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedService, times(1)).deleteById(anyLong());

    assertEquals(deleted, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void when_delete_is_executed_then_return_not_found() {
//    // Given
//    TechnologyTypeGroupDto unsaved = TechnologyTypeGroupDto.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Technology type group 1")
//      .status(VersionStatus.New.name())
//      .build();
//
//    TechnologyTypeGroupDto saved = TechnologyTypeGroupDto.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Technology type group 1")
//      .status(VersionStatus.New.name())
//      .build();
//
//    TechnologyTypeGroupDto deleted = TechnologyTypeGroupDto.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Gregorian")
//      .status(VersionStatus.Cancel.name())
//      .build();
//
//    when(mockedService.add(unsaved)).thenReturn(saved);
//
//    when(mockedService.findById(saved.getId())).thenReturn(null);
//
//    // When
//    ResponseEntity<TechnologyTypeGroupDto> responseEntity = controller.delete(saved.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_null() {
    // Given
    TechnologyTypeGroupDto unsaved = TechnologyTypeGroupDto.builder()
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 1")
      .status(VersionStatus.New.name())
      .build();

    TechnologyTypeGroupDto saved = TechnologyTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 1")
      .status(VersionStatus.New.name())
      .build();

    TechnologyTypeGroupDto updated = TechnologyTypeGroupDto.builder()
      .id(1L)
      .createdBy("system")
      .lastUpdated(now)
      .name("Technology type group 1")
      .status(VersionStatus.Amend.name())
      .build();

    when(mockedService.add(unsaved)).thenReturn(saved);

    when(mockedService.update(saved)).thenReturn(updated);

    when(mockedService.update(updated)).thenReturn(updated);

    when(mockedService.findById(saved.getId())).thenReturn(saved);

    // When
    ResponseEntity<TechnologyTypeGroupDto> responseEntity = controller.add(unsaved);

    ResponseEntity<TechnologyTypeGroupDto> responseUpdatedEntity = controller.update(updated);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());

    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }

//  @Test
//  @WithMockUser(value = "adrian")
//  public void given_unknown_entity_when_update_is_executed_then_return_notfound() {
//    // Given
//    TechnologyTypeGroupDto unsaved = TechnologyTypeGroupDto.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Technology type group 1")
//      .status(VersionStatus.New.name())
//      .build();
//
//    TechnologyTypeGroupDto saved = TechnologyTypeGroupDto.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Technology type group 1")
//      .status(VersionStatus.New.name())
//      .build();
//
//    TechnologyTypeGroupDto updated = TechnologyTypeGroupDto.builder()
//      .id(9999L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Technology type group 9999")
//      .status(VersionStatus.Amend.name())
//      .build();
//
//    when(mockedService.save(unsaved)).thenReturn(saved);
//
//    when(mockedService.save(saved)).thenReturn(updated);
//
//    when(mockedService.save(updated)).thenReturn(updated);
//
//    when(mockedService.findById(saved.getId())).thenReturn(null);
//
//    // When
//    ResponseEntity<TechnologyTypeGroupDto> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<TechnologyTypeGroupDto> responseUpdatedEntity = controller.update(updated);
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
