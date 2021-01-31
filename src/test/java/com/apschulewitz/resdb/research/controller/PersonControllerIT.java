package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.model.TitleTestHelper;
import com.apschulewitz.resdb.common.model.dto.AltitudeDto;
import com.apschulewitz.resdb.common.model.dto.HistoricalDateDto;
import com.apschulewitz.resdb.common.model.dto.LatitudeDto;
import com.apschulewitz.resdb.common.model.dto.LongitudeDto;
import com.apschulewitz.resdb.common.model.dto.RiverDto;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.PersonDao;
import com.apschulewitz.resdb.refdata.model.dao.PlaceDao;
import com.apschulewitz.resdb.refdata.model.dao.RiverDao;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.entity.River;
import com.apschulewitz.resdb.refdata.model.mapper.PersonMapper;
import com.apschulewitz.resdb.refdata.model.mapper.RiverMapper;
import com.apschulewitz.resdb.research.model.helper.PersonTestHelper;
import com.apschulewitz.resdb.research.model.helper.PlaceTestHelper;
import com.apschulewitz.resdb.research.model.dao.TitleDao;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Slf4j
public class PersonControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private PersonTestHelper personTestHelper;

  @Autowired
  private PlaceTestHelper placeTestHelper;

  @Autowired
  private TitleTestHelper titleTestHelper;

  @Autowired
  private PersonDao personDao;

  @Autowired
  private PlaceDao placeDao;

  @Autowired
  private RiverDao riverDao;

  @Autowired
  private TitleDao titleDao;

  @Autowired
  private PersonMapper personMapper;

  @Autowired
  private RiverMapper riverMapper;

  private AltitudeDto altitudeDto;
  private LatitudeDto latitudeDto;
  private LongitudeDto longitudeDto;

  private River thamesRiver;
  private RiverDto thamesRiverDto;

  private Title sir;

  private Gson gson = new Gson();

  @Before
  public void beforeEachTest() {
    altitudeDto = AltitudeDto.builder().value("altitude").build();

    latitudeDto = LatitudeDto.builder().value("latitude").build();

    longitudeDto = LongitudeDto.builder().value("longitude").build();

    thamesRiver = River.builder().name("Thames River").build();
    thamesRiverDto = riverMapper.toDto(thamesRiver);

    sir = PersonTestHelper.constructTitle("Sir", TitleType.Prefix, Gender.Male);

    riverDao.save(thamesRiver);
    titleDao.save(sir);
  }

  @Test
  public void when_findAll_is_executed_then_return_ok() throws Exception {
    mockMvc.perform(
      get(RestUrlPaths.PERSON_CONTROLLER_BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().isOk());
  }

  @Test
  @Transactional
  public void given_valid_person_and_no_titles_when_save_is_executed_then_return_ok() throws Exception {
    // given
    PlaceDto sennaBirthPlace = placeTestHelper.constructUnsavedMinimalDto(); //(altitudeDto, latitudeDto, longitudeDto,"Sao Paulo", null);
    PlaceDto sennaDeathPlace = placeTestHelper.constructUnsavedMinimalDto(); //(altitudeDto, latitudeDto, longitudeDto,"Bologna", null);

    HistoricalDateDto sennaDateOfBirth = HistoricalDateDto.builder()
      .day(21).month(Month.MARCH.getValue()).year(1960).build();

    HistoricalDateDto sennaDateOfDeath = HistoricalDateDto.builder()
      .day(1).month(Month.MAY.getValue()).year(1994).build();

    PersonDto unsavedSenna = personTestHelper.constructUnsavedMinimalDto();

//      .constructNewDtoWithAllValues(
//      "Ayrton",
//      null,
//      "Senna da Silva",
//      sennaDateOfBirth,
//      sennaBirthPlace,
//      sennaDateOfDeath,
//      sennaDeathPlace,
//      Gender.Male,
//      null,
//      null,
//      null
//    );

    String json = gson.toJson(unsavedSenna);

    // when
    mockMvc.perform(
      post(RestUrlPaths.PERSON_CONTROLLER_BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().is2xxSuccessful());

    // then
  }

  @Test
  @Transactional
  public void given_valid_person_and_titles_when_save_is_executed_then_return_ok() throws Exception {
    // given
    PlaceDto sennaBirthPlace = placeTestHelper.constructUnsavedMinimalDto(); //(altitudeDto, latitudeDto, longitudeDto,"Sao Paulo", null);
    PlaceDto sennaDeathPlace = placeTestHelper.constructUnsavedMinimalDto(); //(altitudeDto, latitudeDto, longitudeDto,"Bologna", null);

    HistoricalDateDto sennaDateOfBirth = HistoricalDateDto.builder()
      .day(21).month(Month.MARCH.getValue()).year(1960).build();

    HistoricalDateDto sennaDateOfDeath = HistoricalDateDto.builder()
      .day(1).month(Month.MAY.getValue()).year(1994).build();

    TitleDto prefix = titleTestHelper.constructUnsavedMinimalDto();
    TitleDto suffix = titleTestHelper.constructUnsavedMinimalDto();
    suffix.setTitleType(TitleType.Suffix.name());
    suffix.setTitle("KG");

    PersonDto unsavedSenna = personTestHelper.constructUnsavedMinimalDto();
//    List<PersonTitleDto> titles = new ArrayList<>();
//    PersonDto unsavedSenna = PersonTestHelper.constructNewDtoWithAllValues(
//      "Ayrton",
//      null,
//      "Senna da Silva",
//      sennaDateOfBirth,
//      sennaBirthPlace,
//      sennaDateOfDeath,
//      sennaDeathPlace,
//      Gender.Male,
//      prefix,
//      suffix,
//      null
//    );
    // TODO adding 'titles' into the builder results in StackOverflow
//    PersonTitleDto personTitleDto1 = PersonTitleDto.builder()
//      .person(unsavedSenna)
//      .title(prefix)
//      .position(1)
//      .build();
//    PersonTitleDto personTitleDto2 = PersonTitleDto.builder()
//      .person(unsavedSenna)
//      .title(suffix)
//      .position(1)
//      .build();
//    titles.add(personTitleDto1);
//    titles.add(personTitleDto2);

    String json = gson.toJson(unsavedSenna);

    // when
    mockMvc.perform(
      post(RestUrlPaths.PERSON_CONTROLLER_BASE_URL)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().is2xxSuccessful());

    // then
  }

  @Test
  @Transactional
  public void given_valid_person_and_titles_when_delete_is_executed_then_return_ok() throws Exception {
    // given
    PlaceDto churchillBirthPlace = placeTestHelper.constructUnsavedMinimalDto(); //(altitudeDto, latitudeDto, longitudeDto,"Blenheim Palace", null);
    PlaceDto churchillDeathPlace = placeTestHelper.constructUnsavedMinimalDto(); //(altitudeDto, latitudeDto, longitudeDto,"Kensington", null);

    HistoricalDateDto churchillDateOfBirth = HistoricalDateDto.builder()
      .day(30).month(Month.NOVEMBER.getValue()).year(1870).build();

    HistoricalDateDto churchillDateOfDeath = HistoricalDateDto.builder()
      .day(24).month(Month.JANUARY.getValue()).year(1965).build();

    TitleDto prefix = titleTestHelper.constructUnsavedMinimalDto(); //constructNewTitleDto("Prime Minister", TitleType.Prefix, Gender.Male);
    TitleDto suffix = titleTestHelper.constructUnsavedMinimalDto(); //constructNewTitleDto("KG", TitleType.Suffix, Gender.Male);

//    PersonDto unsavedChurchillDto = PersonTestHelper.constructNewDtoWithAllValues(
//      "Winston",
//      "Leonard",
//      "Spencer-Churchill",
//      churchillDateOfBirth,
//      churchillBirthPlace,
//      churchillDateOfDeath,
//      churchillDeathPlace,
//      Gender.Male,
//      prefix,
//      suffix,
//      null
//    );

    PersonDto unsavedChurchillDto = personTestHelper.constructNewDtoWithAllValues();

    Person unsavedChurchill = personMapper.toEntity(unsavedChurchillDto);
    Person savedChurchill = personDao.save(unsavedChurchill);

    String json = gson.toJson(savedChurchill.getId());

    String url = String.format("%s/%d", RestUrlPaths.PERSON_CONTROLLER_BASE_URL, savedChurchill.getId());

    // when
    mockMvc.perform(
      delete(url)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json)
        .accept(MediaType.APPLICATION_JSON)
    ).andExpect(status().is2xxSuccessful());

    // then
  }

}
