package com.apschulewitz.resdb.research.controller;

import com.apschulewitz.resdb.common.model.TitleTestHelper;
import com.apschulewitz.resdb.common.model.dto.AltitudeDto;
import com.apschulewitz.resdb.common.model.dto.HistoricalDateDto;
import com.apschulewitz.resdb.common.model.dto.LatitudeDto;
import com.apschulewitz.resdb.common.model.dto.LongitudeDto;
import com.apschulewitz.resdb.common.model.dto.RiverDto;
import com.apschulewitz.resdb.common.model.entity.Altitude;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import com.apschulewitz.resdb.common.model.entity.Latitude;
import com.apschulewitz.resdb.common.model.entity.Longitude;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.utils.StringUtils;
import com.apschulewitz.resdb.config.RestUrlPaths;
import com.apschulewitz.resdb.refdata.model.dao.PersonDao;
import com.apschulewitz.resdb.refdata.model.dao.PlaceDao;
import com.apschulewitz.resdb.refdata.model.dao.RiverDao;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.dto.PersonTitleDto;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import com.apschulewitz.resdb.refdata.model.entity.River;
import com.apschulewitz.resdb.refdata.model.mapper.PersonMapper;
import com.apschulewitz.resdb.refdata.model.mapper.RiverMapper;
import com.apschulewitz.resdb.research.model.PersonTestHelper;
import com.apschulewitz.resdb.research.model.PlaceTestHelper;
import com.apschulewitz.resdb.research.model.dao.TitleDao;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.net.UnknownServiceException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
    altitudeDto = new AltitudeDto();
    altitudeDto.setValue("altitude");

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
    PlaceDto sennaBirthPlace = PlaceTestHelper.constructNewPlaceDto(altitudeDto, latitudeDto, longitudeDto,"Sao Paulo", null);
    PlaceDto sennaDeathPlace = PlaceTestHelper.constructNewPlaceDto(altitudeDto, latitudeDto, longitudeDto,"Bologna", null);

    HistoricalDateDto sennaDateOfBirth = HistoricalDateDto.builder()
      .day(21).month(Month.MARCH.getValue()).year(1960).build();

    HistoricalDateDto sennaDateOfDeath = HistoricalDateDto.builder()
      .day(1).month(Month.MAY.getValue()).year(1994).build();

    PersonDto unsavedSenna = PersonTestHelper.constructNewPersonDto(
      "Ayrton",
      null,
      "Senna da Silva",
      sennaDateOfBirth,
      sennaBirthPlace,
      sennaDateOfDeath,
      sennaDeathPlace,
      Gender.Male,
      null,
      null,
      null
    );

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
    PlaceDto sennaBirthPlace = PlaceTestHelper.constructNewPlaceDto(altitudeDto, latitudeDto, longitudeDto,"Sao Paulo", null);
    PlaceDto sennaDeathPlace = PlaceTestHelper.constructNewPlaceDto(altitudeDto, latitudeDto, longitudeDto,"Bologna", null);

    HistoricalDateDto sennaDateOfBirth = HistoricalDateDto.builder()
      .day(21).month(Month.MARCH.getValue()).year(1960).build();

    HistoricalDateDto sennaDateOfDeath = HistoricalDateDto.builder()
      .day(1).month(Month.MAY.getValue()).year(1994).build();

    TitleDto prefix = TitleTestHelper.constructNewTitleDto("Prefix1", TitleType.Prefix, Gender.Male);
    TitleDto suffix = TitleTestHelper.constructNewTitleDto("Suffix1", TitleType.Suffix, Gender.Male);

    List<PersonTitleDto> titles = new ArrayList<>();
    PersonDto unsavedSenna = PersonTestHelper.constructNewPersonDto(
      "Ayrton",
      null,
      "Senna da Silva",
      sennaDateOfBirth,
      sennaBirthPlace,
      sennaDateOfDeath,
      sennaDeathPlace,
      Gender.Male,
      prefix,
      suffix,
      null
    );
    // TODO adding 'titles' into the builder results in StackOverflow
    PersonTitleDto personTitleDto1 = PersonTitleDto.builder()
      .person(unsavedSenna)
      .title(prefix)
      .position(1)
      .build();
    PersonTitleDto personTitleDto2 = PersonTitleDto.builder()
      .person(unsavedSenna)
      .title(suffix)
      .position(1)
      .build();
    titles.add(personTitleDto1);
    titles.add(personTitleDto2);

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
    PlaceDto churchillBirthPlace = PlaceTestHelper.constructNewPlaceDto(altitudeDto, latitudeDto, longitudeDto,"Blenheim Palace", null);
    PlaceDto churchillDeathPlace = PlaceTestHelper.constructNewPlaceDto(altitudeDto, latitudeDto, longitudeDto,"Kensington", null);

    HistoricalDateDto churchillDateOfBirth = HistoricalDateDto.builder()
      .day(30).month(Month.NOVEMBER.getValue()).year(1870).build();

    HistoricalDateDto churchillDateOfDeath = HistoricalDateDto.builder()
      .day(24).month(Month.JANUARY.getValue()).year(1965).build();

    TitleDto prefix = TitleTestHelper.constructNewTitleDto("Prime Minister", TitleType.Prefix, Gender.Male);
    TitleDto suffix = TitleTestHelper.constructNewTitleDto("KG", TitleType.Suffix, Gender.Male);

    PersonDto unsavedChurchillDto = PersonTestHelper.constructNewPersonDto(
      "Winston",
      "Leonard",
      "Spencer-Churchill",
      churchillDateOfBirth,
      churchillBirthPlace,
      churchillDateOfDeath,
      churchillDeathPlace,
      Gender.Male,
      prefix,
      suffix,
      null
    );

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
