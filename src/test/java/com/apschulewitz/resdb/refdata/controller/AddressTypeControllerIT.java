package com.apschulewitz.resdb.refdata.controller;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.dto.AddressTypeDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddressTypeControllerIT {

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
    ZonedDateTime now = ZonedDateTime.now();
    AddressTypeDto toBeSaved = AddressTypeDto.builder()
      .name("Home")
      .createdBy("system")
      .lastUpdated(now)
      .build();

    // When
    ResponseEntity<AddressTypeDto> responseEntity = restTemplate.postForEntity(constructUri(), constructHttpEntity(toBeSaved), AddressTypeDto.class);

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
    AddressTypeDto newUnsaved = AddressTypeDto.builder()
      .name("Email")
      .createdBy("system")
      .build();

    AddressTypeDto cancelledUnsaved = AddressTypeDto.builder()
      .name("Work")
      .createdBy("system")
      .build();

    AddressTypeDto amendedUnsaved2 = AddressTypeDto.builder()
      .name("Alternate office")
      .createdBy("system")
      .build();

    // When
    ResponseEntity<AddressTypeDto> responseEntity1 = restTemplate.postForEntity(constructUri(), constructHttpEntity(newUnsaved), AddressTypeDto.class);
    ResponseEntity<AddressTypeDto> responseEntity2 = restTemplate.postForEntity(constructUri(), constructHttpEntity(cancelledUnsaved), AddressTypeDto.class);
    ResponseEntity<AddressTypeDto> responseEntity3 = restTemplate.postForEntity(constructUri(), constructHttpEntity(amendedUnsaved2), AddressTypeDto.class);

    ResponseEntity<List<AddressTypeDto>> responseListEntity = restTemplate.exchange(constructUri(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {
    });

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

  private HttpEntity<AddressTypeDto> constructHttpEntity(AddressTypeDto toBeSaved) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new HttpEntity<>(toBeSaved, headers);
  }
}
