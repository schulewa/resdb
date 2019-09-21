package worldfirst;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class ResponseUsingGenericsTest {

  private static final ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void given_error_when_response_is_created_then_return_response_with_error_and_no_data() throws JsonProcessingException {
    log.info("START: given_error_when_response_is_created_then_return_response_with_error_and_no_data");

    // Given
    WfError error = new WfError("101", "Beginners error");
    WfResponse response = new WfResponseImpl(error);
    String json = objectMapper.writeValueAsString(error);
    log.info("json={}", json);

    // When
    ResponseEntity<WfResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

    // Then
    assertNotNull(responseEntity);
    assertNotNull(responseEntity.getBody());

    log.info("END: given_error_when_response_is_created_then_return_response_with_error_and_no_data");
  }

  @Test
  public void given_data_when_response_is_created_then_return_response_with_data_and_no_error() throws JsonProcessingException {
    log.info("START: given_data_when_response_is_created_then_return_response_with_data_and_no_error");

    // Given
    List<LocalDate> dates = Arrays.asList(LocalDate.of(2019, 9, 20), LocalDate.of(2019, 9, 23));
    WfData<List<LocalDate>> data = new WfData<>(dates);
    WfResponse response = new WfResponseImpl(data);
    String json = objectMapper.writeValueAsString(data);
    log.info("json={}", json);

    // When
    ResponseEntity<WfResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);

    // Then
    assertNotNull(responseEntity);
    assertNotNull(responseEntity.getBody());

    log.info("END: given_data_when_response_is_created_then_return_response_with_data_and_no_error");
  }

}
