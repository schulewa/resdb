package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.model.dto.HistoricalDateDto;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.refdata.service.PersonService;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.research.model.PersonTestHelper;
import com.apschulewitz.resdb.research.model.PlaceTestHelper;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {

  private static final Gson GSON = new Gson();

  private PersonController personController;

  @MockBean
  private PersonService mockedPersonService;

  @Autowired
  private PersonTestHelper personTestHelper;

  @Autowired
  private PlaceTestHelper placeTestHelper;

  @Before
  public void beforeEachTest() {
    reset(mockedPersonService);
    personController = new PersonController(mockedPersonService);
  }

  @Test
  public void when_findAll_is_executed_and_onlyactive_is_false_then_return_list() {
    // given
    PlaceDto malborough = placeTestHelper.constructUnsavedMinimalDto(); //(null, null, null, "Malborough", null);
    PlaceDto kensington = placeTestHelper.constructUnsavedMinimalDto(); //constructNewPlaceDto(null, null, null, "Kensington", null);
    PersonDto churchillDto = personTestHelper.constructUnsavedMinimalDto();
    List<PersonDto> personDtos = Arrays.asList(churchillDto);
    when(mockedPersonService.findAll(false)).thenReturn(personDtos);

    // when
    ResponseEntity<List<PersonDto>> responseEntity = personController.findAll(false);

    // then
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(personDtos.size(), responseEntity.getBody().size());
  }

  @Test
  public void when_findAll_is_executed_and_onlyactive_is_true_then_return_empty_list() {
    // given
    PlaceDto malborough = placeTestHelper.constructUnsavedMinimalDto(); //(null, null, null, "Malborough", null);
    PlaceDto kensington = placeTestHelper.constructUnsavedMinimalDto(); //(null, null, null, "Kensington", null);
    List<PersonDto> personDtos = Arrays.asList(
      PersonTestHelper.constructCancelledPersonDto(
        "Winston",
        null,
        "Churchill",
        HistoricalDateDto.builder().day(1).month(2).year(3).build(),
        malborough,
        HistoricalDateDto.builder().day(2).month(3).year(4).build(),
        kensington,
        Gender.Male,
        null,
        null,
        null)
    );
    when(mockedPersonService.findAll(true)).thenReturn(personDtos);

    // when
    ResponseEntity<List<PersonDto>> responseEntity = personController.findAll(true);

    // then
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
    assertEquals(personDtos.size(), responseEntity.getBody().size());
  }

  @Test
  public void given_valid_minimal_person_when_add_is_called_then_return_saved_person() {
    // given
    PersonDto personDto = personTestHelper.constructUnsavedMinimalDto();
    String json = GSON.toJson(personDto);
    PersonDto savedDto = GSON.fromJson(GSON.toJson(personDto), PersonDto.class);
    savedDto.setId(1L);
    when(mockedPersonService.add(any())).thenReturn(savedDto);

    // when
    ResponseEntity<PersonDto> responseEntity = personController.add(personDto);

    // then
    assertNotNull(responseEntity);
    assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    assertNotNull(responseEntity.getBody());
  }
}
