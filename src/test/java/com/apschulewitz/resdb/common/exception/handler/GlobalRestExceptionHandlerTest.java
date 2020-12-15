package com.apschulewitz.resdb.common.exception.handler;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.controller.AddressTypeController;
import com.apschulewitz.resdb.refdata.model.dao.AddressTypeDao;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.apschulewitz.resdb.refdata.service.AddressTypeService;
import io.swagger.models.Response;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.beans.PropertyChangeEvent;
import java.net.URI;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GlobalRestExceptionHandlerTest {

  private RestTemplate restTemplate;

  private AddressTypeController addressTypeController;

  private GlobalRestExceptionHandler globalRestExceptionHandler;

  @Before
  public void beforeEachTest() {
    restTemplate = new RestTemplate();
    AddressTypeService mockedAddressTypeService = mock(AddressTypeService.class);
    addressTypeController = new AddressTypeController(mockedAddressTypeService);
    globalRestExceptionHandler = new GlobalRestExceptionHandler();
  }

  @Test
  public void given_runtimeexception_when_handleRuntimeException_is_executed_then_handle_error() {
    // given
    RuntimeException exception = new RuntimeException("test");
    WebRequest mockedWebRequest = mock(WebRequest.class);

    // when
    ResponseEntity<Object> response = globalRestExceptionHandler.handleRuntimeException(exception, mockedWebRequest);

    // then
    assertNotNull(response);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertNotNull(response.getBody());
    Object responseBody = response.getBody();
    if (responseBody instanceof Map) {
      Map<String, Object> body = (Map) responseBody;
      assertTrue(body.containsKey("timestamp"));
      assertTrue(body.containsKey("error"));
    } else {
      fail("Response does not contain map");
    }
  }

  @Ignore
  @Test
  public void given_httprequestmethodnotsupported_when_handleHttpRequestMethodNotSupported_is_executed_then_handle_error() {
    // given
    HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("HEAD");
    HttpHeaders headers = new HttpHeaders();
    HttpStatus status = HttpStatus.BAD_REQUEST;
    WebRequest mockedWebRequest = mock(WebRequest.class);

    // when
    AddressType addressType = AddressType.builder()
      .createdBy("testuser")
      .name("test address")
      .status(VersionStatus.New)
      .build();
    HttpEntity<AddressType> request = new HttpEntity<>(addressType);
    UriComponents uriComponents = UriComponentsBuilder.newInstance()
      .scheme("http").host("localhost").path(RestUrlPaths.ADDRESS_TYPE_CONTROLLER_BASE_URL).build();

    ResponseEntity<AddressType> response = restTemplate.exchange(uriComponents.toUriString(), HttpMethod.GET, request, AddressType.class);
//    ResponseEntity<Object> responseEntity = globalRestExceptionHandler.handleHttpRequestMethodNotSupported(exception, headers, status, mockedWebRequest);

    // then
    assertNotNull(response);

//    assertNotNull(responseEntity);
//    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
//    assertNotNull(responseEntity.getBody());
  }

  @Test
  public void given_inconvertable_when_handleConversionNotSupported_is_executed_then_handle_error() {
    // given
    PropertyChangeEvent mockedPropertyChangeEvent = mock(PropertyChangeEvent.class);
    Class<?> requiredType = AddressType.class;
    ConversionNotSupportedException exception = new ConversionNotSupportedException(mockedPropertyChangeEvent, requiredType, null);
    HttpHeaders headers = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    WebRequest mockedWebRequest = mock(WebRequest.class);

    // when
    ResponseEntity<Object> responseEntity = globalRestExceptionHandler.handleConversionNotSupported(exception, headers, httpStatus, mockedWebRequest);

    // then
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }

  @Test
  public void given_x_when_handleHttpMessageNotReadable_is_executed_then_handle_error() {
    // given
    HttpInputMessage mockedHttpInputMessage = mock(HttpInputMessage.class);
    HttpMessageNotReadableException exception = new HttpMessageNotReadableException("test", mockedHttpInputMessage);
    HttpHeaders headers = new HttpHeaders();
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    WebRequest mockedWebRequest = mock(WebRequest.class);

    // when
    ResponseEntity<Object> responseEntity = globalRestExceptionHandler.handleHttpMessageNotReadable(exception, headers, httpStatus, mockedWebRequest);

    // then
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
  }
}
