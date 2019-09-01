package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.util.UriComponentsBuilder;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
public class AddressTypeControllerIT {

//  @MockBean
//  private MockMvc mockMvc;
  TestRestTemplate restTemplate = new TestRestTemplate();

  @LocalServerPort
  private Integer port;

  @Autowired
  private AddressTypeDao addressTypeDao;

  private ObjectMapper objectMapper;

  @Before
  public void beforeEachTest() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    addressTypeDao.deleteAll();;
  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_valid_address_type_when_save_is_executed_then_save_data() {
    // Given
    LocalDateTime now = LocalDateTime.now();
    AddressType toBeSaved = AddressType.builder()
      .name("Home")
      .status(VersionStatus.New)
      .createdBy("system")
      .updatedBy("system")
      .lastUpdated(now)
      .build();

    // When
    ResponseEntity<AddressType> responseEntity = restTemplate.postForEntity(constructUri(), constructHttpEntity(toBeSaved), AddressType.class);

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertNotNull("saved id", responseEntity.getBody().getId());
    assertEquals("name", toBeSaved.getName(), responseEntity.getBody().getName());

  }

  @WithMockUser(value = "adrian")
  @Test
  public void given_valid_address_types_when_findAll_is_executed_then_return_data() throws Exception {
    // Given
    LocalDateTime now = LocalDateTime.now();

    AddressType newUnsaved = AddressType.builder()
      .name("Email")
      .status(VersionStatus.New)
      .createdBy("system")
      .lastUpdated(now)
      .updatedBy("system")
      .build();

    AddressType cancelledUnsaved = AddressType.builder()
      .name("Work")
      .status(VersionStatus.Cancel)
      .createdBy("system")
      .lastUpdated(now)
      .updatedBy("system")
      .build();

    AddressType amendedUnsaved2 = AddressType.builder()
      .name("Alternate office")
      .status(VersionStatus.Amend)
      .createdBy("system")
      .lastUpdated(now)
      .updatedBy("system")
      .build();

    // When
    ResponseEntity<AddressType> responseEntity1 = restTemplate.postForEntity(constructUri(), constructHttpEntity(newUnsaved), AddressType.class);
    ResponseEntity<AddressType> responseEntity2 = restTemplate.postForEntity(constructUri(), constructHttpEntity(cancelledUnsaved), AddressType.class);
    ResponseEntity<AddressType> responseEntity3 = restTemplate.postForEntity(constructUri(), constructHttpEntity(amendedUnsaved2), AddressType.class);

    ResponseEntity<List<AddressType>> responseListEntity = restTemplate.exchange(constructUri(), HttpMethod.GET, null, new ParameterizedTypeReference<List<AddressType>>(){});

    // Then
    assertEquals(HttpStatus.CREATED, responseEntity1.getStatusCode());
    assertNotNull(responseEntity1.getBody());
    assertNotNull(responseEntity1.getBody().getId());
    assertEquals(VersionStatus.New, responseEntity1.getBody().getStatus());
    //
    assertEquals(HttpStatus.CREATED, responseEntity2.getStatusCode());
    assertNotNull(responseEntity2.getBody());
    assertNotNull(responseEntity2.getBody().getId());
    assertEquals(VersionStatus.Cancel, responseEntity2.getBody().getStatus());
    //
    assertEquals(HttpStatus.CREATED, responseEntity3.getStatusCode());
    assertNotNull(responseEntity3.getBody());
    assertNotNull(responseEntity3.getBody().getId());
    assertEquals(VersionStatus.Amend, responseEntity3.getBody().getStatus());
    //
    assertEquals(HttpStatus.OK, responseListEntity.getStatusCode());
    assertNotNull(responseListEntity.getBody());
    assertEquals(2, responseListEntity.getBody().size()); // contains only live statuses
    assertEquals(VersionStatus.New, responseListEntity.getBody().get(0).getStatus());
    assertEquals(VersionStatus.Amend, responseListEntity.getBody().get(1).getStatus());
  }

  private String constructUri() {
    return UriComponentsBuilder
      .newInstance()
      .scheme("http")
      .host("localhost")
      .port(port)
      .path(RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL)
      .toUriString();
  }

  private HttpEntity<AddressType> constructHttpEntity(AddressType toBeSaved) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(toBeSaved, headers);
  }
}
