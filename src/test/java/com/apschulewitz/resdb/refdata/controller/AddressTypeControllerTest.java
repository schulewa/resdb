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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AddressTypeControllerTest {

  private LocalDateTime now = LocalDateTime.now();

  private AddressTypeController addressTypeController;

  @MockBean
  private AddressTypeDao mockedAddressTypeDao;

  @Before
  public void beforeEachTest() {
    mockedAddressTypeDao.deleteAll();
    addressTypeController = new AddressTypeController(mockedAddressTypeDao);
  }

  @WithMockUser(value = "adrian")
  @Test
  public void givenNone_when_findAll_is_executed_then_return_list() {
    HttpServletRequest mockedRequest = mock(HttpServletRequest.class);

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
  public void givenAddress_whensave_is_executed_then_return_saved_entity() {
    HttpServletRequest mockedRequest = mock(HttpServletRequest.class);

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
}
