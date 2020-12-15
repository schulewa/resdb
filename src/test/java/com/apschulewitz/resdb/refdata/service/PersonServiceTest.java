package com.apschulewitz.resdb.refdata.service;

import com.apschulewitz.resdb.common.model.TitleTestHelper;
import com.apschulewitz.resdb.common.model.dto.AltitudeDto;
import com.apschulewitz.resdb.common.model.dto.HistoricalDateDto;
import com.apschulewitz.resdb.common.model.dto.LatitudeDto;
import com.apschulewitz.resdb.common.model.dto.LongitudeDto;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.common.model.mapper.TitleMapper;
import com.apschulewitz.resdb.refdata.model.dao.PersonDao;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.dto.PersonTitleDto;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.refdata.model.mapper.PersonMapper;
import com.apschulewitz.resdb.refdata.model.mapper.PlaceMapper;
import com.apschulewitz.resdb.refdata.service.PersonService;
import com.apschulewitz.resdb.research.model.PersonTestHelper;
import com.apschulewitz.resdb.research.model.PlaceTestHelper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@Slf4j
public class PersonServiceTest {

  private PersonService personService;

  @Autowired
  private PersonDao personDao;

  private PersonMapper personMapper;

  private AltitudeDto altitude;
  private LatitudeDto latitude;
  private LongitudeDto longitude;

  @Before
  public void beforeEachTest() {
    personMapper = new PersonMapper(new PlaceMapper(), new TitleMapper());
    personService = new PersonService(personDao, personMapper);

    altitude = new AltitudeDto();
    altitude.setValue("altitude");

    latitude = LatitudeDto.builder().value("latitude").build();
    longitude = LongitudeDto.builder().value("longitude").build();
  }

  @Test
  public void given_valid_person_with_minimal_data_when_add_is_executed_then_return_saved_person() {
    // given
    PersonDto toBeSaved = constructNewPersonDto(null, null, null);

    // when
    PersonDto saved = personService.add(toBeSaved);

    // then
    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertNotNull(saved.getStatus());
  }

  @Test
  public void given_valid_person_with_prefixTitle_when_add_is_executed_then_return_saved_person() {
    // given
    TitleDto prefixTitle = TitleTestHelper.constructNewTitleDto("HRH", TitleType.Prefix, Gender.Any);
    PersonDto toBeSaved = constructNewPersonDto(prefixTitle, null, null);

    // when
      PersonDto saved = personService.add(toBeSaved);

    // then
    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertNotNull(saved.getStatus());
  }

  @Test
  public void given_valid_person_with_suffixTitle_when_add_is_executed_then_return_saved_person() {
    // given
    TitleDto suffixTitle = TitleTestHelper.constructNewTitleDto("FemaleSuffix1", TitleType.Prefix, Gender.Female);
    PersonDto toBeSaved = constructNewPersonDto(null, suffixTitle, null);

    // when
    PersonDto saved = personService.add(toBeSaved);

    // then
    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertNotNull(saved.getStatus());
  }

  @Test
  public void given_valid_person_with_titles_when_add_is_executed_then_return_saved_person() {
    // given
    TitleDto suffixTitle = TitleTestHelper.constructNewTitleDto("FemaleSuffix2", TitleType.Prefix, Gender.Female);
    PersonDto toBeSaved = null;
    PersonTitleDto personTitle1 = PersonTitleDto.builder().person(toBeSaved).position(1).title(suffixTitle).build();
    List<PersonTitleDto> titles = Arrays.asList(personTitle1);
      toBeSaved = constructNewPersonDto(null, null, titles);

    // when
    PersonDto saved = personService.add(toBeSaved);

    // then
    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertNotNull(saved.getStatus());
  }

  @Test
  public void given_valid_person_with_prefix_suffix_and_titles_when_add_is_executed_then_return_saved_person() {
    // given
    TitleDto prefixTitle = TitleTestHelper.constructNewTitleDto("FemalePrefix1", TitleType.Prefix, Gender.Female);
    TitleDto suffixTitle = TitleTestHelper.constructNewTitleDto("FemaleSuffix2", TitleType.Suffix, Gender.Female);
    PersonDto toBeSaved = null;
    PersonTitleDto personTitle1 = PersonTitleDto.builder().person(toBeSaved).position(1).title(suffixTitle).build();
    List<PersonTitleDto> titles = Arrays.asList(personTitle1);
    toBeSaved = constructNewPersonDto(prefixTitle, suffixTitle, titles);

    // when
    PersonDto saved = personService.add(toBeSaved);

    // then
    assertNotNull(saved);
    assertNotNull(saved.getId());
    assertNotNull(saved.getStatus());
  }

  private PersonDto constructNewPersonDto(TitleDto prefix, TitleDto suffix, List<PersonTitleDto> titles) {
    PlaceDto sennaBirthPlace = PlaceTestHelper.constructNewPlaceDto(altitude, latitude, longitude,"Sao Paulo", null);
    PlaceDto sennaDeathPlace = PlaceTestHelper.constructNewPlaceDto(altitude, latitude, longitude,"Bologna", null);

    HistoricalDateDto sennaDateOfBirth = HistoricalDateDto.builder()
      .day(21).month(Month.MARCH.getValue()).year(1960).build();

    HistoricalDateDto sennaDateOfDeath = HistoricalDateDto.builder()
      .day(1).month(Month.MAY.getValue()).year(1994).build();

    return PersonTestHelper.constructNewPersonDto(
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
      titles
    );
  }
}
