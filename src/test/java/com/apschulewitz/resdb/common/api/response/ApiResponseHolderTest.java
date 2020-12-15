package com.apschulewitz.resdb.common.api.response;

import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.AddressType;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@Slf4j
public class ApiResponseHolderTest {

  private Gson gson = new Gson();

  @Test
  public void given_datum_when_response_is_constructed_then_return_response_with_datum() {
    // Given
    AddressType datum = AddressType.builder()
      .createdBy("creator")
      .name("Test address")
      .status(VersionStatus.New)
      .updatedBy("tester")
      .lastUpdated(ZonedDateTime.now())
      .id(1L)
      .operation(DataOperation.CREATE)
      .build();

    // When
    ApiResult result = new ApiResponseData<>(datum);
    ApiResponseHolder apiResponseHolder = new ApiResponseHolder(result);
    String holderAsJson = gson.toJson(apiResponseHolder);
    String resultAsJson = gson.toJson(apiResponseHolder.getResult());
    log.info("Datum holderAsJson = {}", holderAsJson);
    log.info("Datum resultAsJson = {}", resultAsJson);

    // Then
    assertNotNull(apiResponseHolder.getResult());
    assertTrue(apiResponseHolder.getResult() instanceof ApiResponseData);
    assertFalse(apiResponseHolder.getResult() instanceof ApiResponseError);
    ApiResponseData data = (ApiResponseData) apiResponseHolder.getResult();
    assertNotNull(data);
    assertTrue(data.getData() instanceof AddressType);
    AddressType addressType = (AddressType) data.getData();
    assertEquals(datum, addressType);
  }

  @Test
  public void given_data_when_response_is_constructed_then_return_response_with_data() {
    // Given
    AddressType addressType1 = AddressType.builder()
      .createdBy("creator")
      .name("Test address1")
      .status(VersionStatus.New)
      .updatedBy("tester")
      .lastUpdated(ZonedDateTime.now())
      .id(1L)
      .operation(DataOperation.CREATE)
      .build();

    AddressType addressType2 = AddressType.builder()
      .createdBy("creator")
      .name("Test address2")
      .status(VersionStatus.New)
      .updatedBy("tester")
      .lastUpdated(ZonedDateTime.now())
      .id(1L)
      .operation(DataOperation.CREATE)
      .build();

    // When
    List<AddressType> data = Arrays.asList(addressType1, addressType2);
    ApiResult result = new ApiResponseData<>(data);
    ApiResponseHolder apiResponseHolder = new ApiResponseHolder(result);
    String holderAsJson = gson.toJson(apiResponseHolder);
    String resultAsJson = gson.toJson(apiResponseHolder.getResult());
    log.info("Data holderAsJson = {}", holderAsJson);
    log.info("Data resultAsJson = {}", resultAsJson);

    // Then
    assertNotNull(apiResponseHolder.getResult());
    assertTrue(apiResponseHolder.getResult() instanceof ApiResponseData);
    assertFalse(apiResponseHolder.getResult() instanceof ApiResponseError);
    ApiResponseData responseData = (ApiResponseData) apiResponseHolder.getResult();
    assertNotNull(responseData);
    assertTrue(responseData.getData() instanceof List);
    List<?> listEntries = (List<?>) responseData.getData();
    assertFalse(listEntries.isEmpty());
    assertTrue(listEntries.get(0) instanceof AddressType);
    AddressType addressType = (AddressType) ((List<?>) responseData.getData()).get(0);
    assertEquals(addressType1, addressType);
    addressType = (AddressType) ((List<?>) responseData.getData()).get(1);
    assertEquals(addressType2, addressType);
  }

  @Test
  public void given_error_when_response_is_constructed_then_return_response_with_error() {
    // Given
    ApiResult result = new ApiResponseError(ApiErrorCode.DATA_OPERATION_FAILED.name(), "Some error message");

    // When
    ApiResponseHolder apiResponseHolder = new ApiResponseHolder(result);
    String holderAsJson = gson.toJson(apiResponseHolder);
    String resultAsJson = gson.toJson(apiResponseHolder.getResult());
    log.info("Error holderAsJson = {}", holderAsJson);
    log.info("Error resultAsJson = {}", resultAsJson);

    // Then
    assertNotNull(apiResponseHolder.getResult());
    assertTrue(apiResponseHolder.getResult() instanceof ApiResponseError);
    assertFalse(apiResponseHolder.getResult() instanceof ApiResponseData);
    ApiResponseError error = (ApiResponseError) apiResponseHolder.getResult();
    assertNotNull(error);
    assertEquals(ApiErrorCode.DATA_OPERATION_FAILED.name(), error.getCode());
    assertEquals("Some error message", error.getMessage());
  }
}
