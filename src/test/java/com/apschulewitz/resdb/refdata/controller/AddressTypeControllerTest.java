package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
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

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private AddressTypeController addressTypeController;

  @MockBean
  private AddressTypeDao mockedAddressTypeDao;

  private HttpServletRequest mockedRequest = mock(HttpServletRequest.class);

  @Before
  public void beforeEachTest() {
    mockedAddressTypeDao.deleteAll();
    addressTypeController = new AddressTypeController(mockedAddressTypeDao);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void givenNone_when_findAll_is_executed_then_return_list() {
    // Given

    AddressType savedHome = AddressType.builder()
      .id(1L)
      .name("Home")
      .status(VersionStatus.New)
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    AddressType savedWork = AddressType.builder()
      .name("Work")
      .status(VersionStatus.New)
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    when(mockedAddressTypeDao.findByStatusIn(VersionStatus.getLiveStatuses())).thenReturn(Arrays.asList(savedHome, savedWork));

    // When
    ResponseEntity<List<AddressType>> responseEntityList = addressTypeController.findAll();

    // Then
    assertEquals(HttpStatus.OK, responseEntityList.getStatusCode());
    assertNotNull(responseEntityList.getBody());
    assertEquals(2, responseEntityList.getBody().size());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_addresstype_when_save_is_executed_then_return_saved_entity() {
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

    when(mockedAddressTypeDao.save(unsavedHome)).thenReturn(savedHome);

    // When
    ResponseEntity<AddressType> responseEntity = addressTypeController.add(mockedRequest, unsavedHome);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_existing_addresstype_when_delete_is_executed_then_return_mark_entity_as_cancelled() {
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

    AddressType deletedHome = AddressType.builder()
      .id(1L)
      .name("Home")
      .status(VersionStatus.Cancel)
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    when(mockedAddressTypeDao.save(unsavedHome)).thenReturn(savedHome);

    when(mockedAddressTypeDao.save(savedHome)).thenReturn(deletedHome);

    when(mockedAddressTypeDao.findById(savedHome.getId())).thenReturn(Optional.of(savedHome));

    // When
    ResponseEntity<AddressType> responseEntity = addressTypeController.delete(savedHome.getId());

    // Then
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    verify(mockedAddressTypeDao, times(1)).save(any(AddressType.class));

    assertEquals(deletedHome, responseEntity.getBody());
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_nonexisting_addresstype_when_delete_is_executed_then_return_not_found() {
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

    AddressType deletedHome = AddressType.builder()
      .id(1L)
      .name("Home")
      .status(VersionStatus.Cancel)
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    when(mockedAddressTypeDao.save(unsavedHome)).thenReturn(savedHome);

    when(mockedAddressTypeDao.findById(savedHome.getId())).thenReturn(Optional.empty());

    // When
    ResponseEntity<AddressType> responseEntity = addressTypeController.delete(savedHome.getId());

    // Then
    assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    assertNull(responseEntity.getBody());
  }

  @Test
  @WithMockUser(value = "adrian")
  public void given_new_addresstype_when_update_is_executed_then_return_updated_addresstype() {
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

    AddressType updatedHome = AddressType.builder()
      .id(1L)
      .name("Home UPDATE")
      .status(VersionStatus.Amend)
      .updatedBy("system")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    when(mockedAddressTypeDao.save(unsavedHome)).thenReturn(savedHome);

    when(mockedAddressTypeDao.save(savedHome)).thenReturn(updatedHome);

    when(mockedAddressTypeDao.save(updatedHome)).thenReturn(updatedHome);

    when(mockedAddressTypeDao.findById(savedHome.getId())).thenReturn(Optional.of(savedHome));

    // When
    ResponseEntity<AddressType> responseEntity = addressTypeController.add(mockedRequest, unsavedHome);

    ResponseEntity<AddressType> responseUpdatedEntity = addressTypeController.update(updatedHome);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull(responseEntity.getBody().getId());

    assertNotNull(responseUpdatedEntity);
    assertEquals(HttpStatus.OK, responseUpdatedEntity.getStatusCode());
  }
}
