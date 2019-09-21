package worldfirst;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.Immutable;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Slf4j
public class ResponseTest {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void given_error_when_response_is_constructed_then_return_response_with_error_and_no_data() throws JsonProcessingException {
    log.info("START: given_error_when_response_is_constructed_then_return_response_with_error_and_no_data");

    // Given
    ResponseError error = new ResponseError("101", "Beginners error");

    // When
    ResponseWithDataOrException responseWithDataOrException = new ResponseWithDataOrException(error);
    String json = objectMapper.writeValueAsString(responseWithDataOrException);
    log.info("json={}", json);

    // Then
    assertNotNull(responseWithDataOrException);
    assertNotNull(responseWithDataOrException.getError());
    assertNull(responseWithDataOrException.getListOfData());
    assertNull(responseWithDataOrException.getSingleData());

    log.info("END: given_error_when_response_is_constructed_then_return_response_with_error_and_no_data");
  }

  @Test
  public void given_singledata_when_response_is_constructed_then_return_response_with_singledata_no_error_and_no_listofdata() throws JsonProcessingException {
    log.info("START: given_singledata_when_response_is_constructed_then_return_response_with_singledata_no_error_and_no_listofdata");

    // Given
    SingleData<LocalDate> singleData = new SingleData<>(LocalDate.of(2019, 9, 20));

    // When
    ResponseWithDataOrException<LocalDate> responseWithDataOrException = new ResponseWithDataOrException<>(singleData);
    String json = objectMapper.writeValueAsString(responseWithDataOrException);
    log.info("json={}", json);

    // Then
    assertNotNull(responseWithDataOrException);
    assertNull(responseWithDataOrException.getError());
    assertNull(responseWithDataOrException.getListOfData());
    assertNotNull(responseWithDataOrException.getSingleData());

    log.info("END: given_singledata_when_response_is_constructed_then_return_response_with_singledata_no_error_and_no_listofdata");
  }

  @Test
  public void given_listofdata_when_response_is_constructed_then_return_response_with_listofdata_no_singledata_and_no_error() throws JsonProcessingException {
    log.info("START: given_listofdata_when_response_is_constructed_then_return_response_with_listofdata_no_singledata_and_no_error");

    // Given
    List<LocalDate> dates = Arrays.asList(LocalDate.of(2019, 9, 20), LocalDate.of(2019, 9, 23));
    ListOfData<LocalDate> singleData = new ListOfData<>(dates);

    // When
    ResponseWithDataOrException<LocalDate> responseWithDataOrException = new ResponseWithDataOrException<>(singleData);
    String json = objectMapper.writeValueAsString(responseWithDataOrException);
    log.info("json={}", json);

    // Then
    assertNotNull(responseWithDataOrException);
    assertNull(responseWithDataOrException.getError());
    assertNotNull(responseWithDataOrException.getListOfData());
    assertEquals(2, responseWithDataOrException.getListOfData().getData().size());
    assertNull(responseWithDataOrException.getSingleData());

    log.info("END: given_listofdata_when_response_is_constructed_then_return_response_with_listofdata_no_singledata_and_no_error");
  }

  @Immutable
  @Getter
  @ToString
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public class ResponseWithDataOrException<T> {
    private final SingleData<T> singleData;
    private final ListOfData<T> listOfData;
    private final ResponseError error;

    public ResponseWithDataOrException(
      @JsonProperty SingleData<T> singleData) {
      this.singleData = singleData;
      this.listOfData = null;
      this.error = null;
    }

    public ResponseWithDataOrException(
      @JsonProperty ListOfData<T> data) {
      this.singleData = null;
      this.listOfData = data;
      this.error = null;
    }

    public ResponseWithDataOrException(
      @JsonProperty ResponseError error
    ) {
      this.singleData = null;
      this.listOfData = null;
      this.error = error;
    }
  }

  @Immutable
  @Getter
  @ToString
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public class SingleData<T> {
    private final T data;

    public SingleData(
      @JsonProperty T data) {
      this.data = data;
    }
  }

  @Immutable
  @Getter
  @ToString
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public class ListOfData<T> {
    private final List<T> data;

    public ListOfData(
      @JsonProperty List<T> data) {
      this.data = data;
    }
  }

  @Getter
  @ToString
  @JsonInclude(JsonInclude.Include.NON_NULL)
  public class ResponseError {
    private final String code;
    private final String message;

    public ResponseError(
      @JsonProperty String code,
      @JsonProperty String message) {
      this.code = code;
      this.message = message;
    }

  }
}
