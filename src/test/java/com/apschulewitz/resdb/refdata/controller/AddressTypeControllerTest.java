package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.service.AddressTypeService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressTypeControllerTest {

  private ZonedDateTime now = ZonedDateTime.now();

  private AddressTypeController controller;

  @Autowired
  private AddressTypeDao addressTypeDao;

  @MockBean
  private AddressTypeService mockedAddressTypeService;

  @Before
  public void beforeEachTest() {
    addressTypeDao.deleteAll();
    controller = new AddressTypeController(mockedAddressTypeService);
  }

  @WithMockUser(value = "adrian")
  @Test
  @Ignore
  public void when_findAll_is_executed_then_return_list() {
    // Given

    AddressTypeDto savedHome = AddressTypeDto.builder()
      .id(1L)
      .name("Home")
      .status(VersionStatus.New.name())
      .updatedBy("testuser")
      .createdBy("testuser")
      .lastUpdated(now)
      .build();

    AddressTypeDto savedWork = AddressTypeDto.builder()
      .name("Work")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    when(mockedAddressTypeService.findAll(true)).thenReturn(Arrays.asList(savedHome, savedWork));

    // When
    ResponseEntity<List<AddressTypeDto>> responseEntityList = controller.findAll(false);

    // Then
    assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
    assertNotNull(responseEntityList.getBody());
    assertTrue(responseEntityList.getBody().size() > 0);
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void givenNone_when_findAll_is_executed_and_onlyactive_is_true_then_return_list() {
//    // Given
//
//    AddressTypeDto savedHome = AddressTypeDto.builder()
//      .id(1L)
//      .name("Home")
//      .status(VersionStatus.New.name())
//      .updatedBy("system")
//      .createdBy("system")
//      .lastUpdated(now)
//      .build();
//
//    AddressTypeDto savedWork = AddressTypeDto.builder()
//      .name("Work")
//      .status(VersionStatus.Cancel.name())
//      .updatedBy("system")
//      .createdBy("system")
//      .lastUpdated(now)
//      .build();
//
//    when(mockedAddressTypeService.findAll(true)).thenReturn(Arrays.asList(savedHome, savedWork));
//
//    // When
//    ResponseEntity<List<AddressTypeDto>> responseEntityList = controller.findAll(true);
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
    AddressTypeDto unsavedHome = AddressTypeDto.builder()
      .name("Home")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    AddressTypeDto savedHome = AddressTypeDto.builder()
      .id(1L)
      .name("Home")
      .status(VersionStatus.New.name())
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    when(mockedAddressTypeService.add(unsavedHome)).thenReturn(savedHome);

    // When
    ResponseEntity<AddressTypeDto> responseEntity = controller.add(unsavedHome);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void when_delete_is_executed_then_return_entity_marked_as_cancelled() {
    // Given
    AddressType unsavedHome = AddressType.builder()
      .name("Home")
      .status(VersionStatus.New)
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    AddressType savedHome = AddressType.builder()
      .id(1L)
      .name("Home")
      .status(VersionStatus.New)
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    AddressTypeDto deletedHome = AddressTypeDto.builder()
      .id(1L)
      .name("Home")
      .status(VersionStatus.Cancel.name())
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

//    when(mockedDao.save(unsavedHome)).thenReturn(savedHome);
//
//    when(mockedDao.save(savedHome)).thenReturn(deletedHome);
//
//    when(mockedDao.findById(savedHome.getId())).thenReturn(Optional.of(savedHome));
    when(mockedAddressTypeService.deleteById(savedHome.getId())).thenReturn(deletedHome);
    // When
    ResponseEntity<AddressTypeDto> responseEntity = controller.delete(savedHome.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

//    verify(mockedDao, times(1)).save(any(AddressType.class));

    assertEquals(deletedHome, responseEntity.getBody());
  }

//  @WithMockUser(value = "adrian")
//  @Test
//  public void given_nonexisting_addresstype_when_delete_is_executed_then_return_not_found() {
//    // Given
//    AddressType unsavedHome = AddressType.builder()
//      .name("Home")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .createdBy("system")
//      .lastUpdated(now)
//      .build();
//
//    AddressType savedHome = AddressType.builder()
//      .id(1L)
//      .name("Home")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .createdBy("system")
//      .lastUpdated(now)
//      .build();
//
//    AddressType deletedHome = AddressType.builder()
//      .id(1L)
//      .name("Home")
//      .status(VersionStatus.Cancel)
//      .updatedBy("system")
//      .createdBy("system")
//      .lastUpdated(now)
//      .build();
//
//    when(mockedDao.save(unsavedHome)).thenReturn(savedHome);
//
//    when(mockedDao.findById(savedHome.getId())).thenReturn(Optional.empty());
//
//    // When
//    ResponseEntity<AddressType> responseEntity = controller.delete(savedHome.getId());
//
//    // Then
//    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
//    assertNull(responseEntity.getBody());
//  }

  @Test
  @WithMockUser(value = "adrian")
  public void when_update_is_executed_then_return_updated_addresstype() {
    // Given
//    AddressTypeDto unsavedHome = AddressTypeDto.builder()
//      .name("Home")
//      .status(VersionStatus.New.name())
//      .updatedBy("system")
//      .createdBy("system")
//      .lastUpdated(now)
//      .build();
//
//    AddressType savedHome = AddressType.builder()
//      .id(1L)
//      .name("Home")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .createdBy("system")
//      .lastUpdated(now)
//      .build();

    AddressTypeDto updatedHome = AddressTypeDto.builder()
      .id(1L)
      .name("Home UPDATE")
      .status(VersionStatus.Amend.name())
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

//    when(mockedDao.save(unsavedHome)).thenReturn(savedHome);
//
//    when(mockedDao.save(savedHome)).thenReturn(updatedHome);
//
//    when(mockedDao.save(updatedHome)).thenReturn(updatedHome);

    when(mockedAddressTypeService.update(updatedHome)).thenReturn(updatedHome);

    // When
//    ResponseEntity<AddressType> responseEntity = controller.add(unsavedHome);

    ResponseEntity<AddressTypeDto> responseUpdatedEntity = controller.update(updatedHome);

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
//    AddressType unsaved = AddressType.builder()
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Home")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    AddressType saved = AddressType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Home")
//      .status(VersionStatus.New)
//      .updatedBy("system")
//      .build();
//
//    AddressType updated = AddressType.builder()
//      .id(1L)
//      .createdBy("system")
//      .lastUpdated(now)
//      .name("Home UPDATE")
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
//    ResponseEntity<AddressType> responseEntity = controller.add(unsaved);
//
//    ResponseEntity<AddressType> responseUpdatedEntity = controller.update(updated);
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
