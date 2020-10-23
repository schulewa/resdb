package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.common.model.entity.Altitude;
import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import com.apschulewitz.resdb.common.model.entity.Latitude;
import com.apschulewitz.resdb.common.model.entity.Longitude;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import com.apschulewitz.resdb.refdata.model.entity.River;
import com.apschulewitz.resdb.security.model.dto.PersonDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
public class PersonMapperTest {

  private PersonMapper personMapper = new PersonMapper();

  @Test
  public void given_null_person_when_toDto_is_executed_then_throw_exception() {
    assertThatThrownBy(() -> personMapper.toDto(null))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null person cannot be mapped to dto");
  }

  @Test
  public void given_inactive_person_and_onlyactive_when_toDto_is_executed_then_return_null() {
    // given
    Person person = constructPerson();
    person.setStatus(VersionStatus.Cancel);

    // when
    PersonDto personDto = personMapper.toDto(person, true);

    // then
    assertNull(personDto);
  }

  @Test
  public void given_inactive_person_and_not_onlyactive_when_toDto_is_executed_then_return_null() {
    // given
    Person person = constructPerson();
    person.setStatus(VersionStatus.Cancel);

    // when
    PersonDto personDto = personMapper.toDto(person, false);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_active_person_and_not_onlyactive_when_toDto_is_executed_then_return_null() {
    // given
    Person person = constructPerson();

    // when
    PersonDto personDto = personMapper.toDto(person, false);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_when_toDto_is_executed_then_return_dto() {
    // given
    Person person = constructPerson();

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_birthplace_then_return_dto() {
    // given
    Person person = constructPerson();
    person.setBirthPlace(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_deathplace_then_return_dto() {
    // given
    Person person = constructPerson();
    person.setDeathPlace(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_dateofbirth_then_return_dto() {
    // given
    Person person = constructPerson();
    person.setDateOfBirth(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_dateofdeath_then_return_dto() {
    // given
    Person person = constructPerson();
    person.setDateOfDeath(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_gender_then_return_dto() {
    // given
    Person person = constructPerson();
    person.setGender(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_prefixtitle_then_return_dto() {
    // given
    Person person = constructPerson();
    person.setPrefixTitle(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_suffixtitle_then_return_dto() {
    // given
    Person person = constructPerson();
    person.setSuffixTitle(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  private Person constructPerson() {
    LocalDateTime now = LocalDateTime.now();

    Altitude altitude = new Altitude();
    altitude.setValue("altitude");

    Latitude latitude = Latitude.builder().value("latitude").build();
    Longitude longitude = Longitude.builder().value("longitude").build();

    River thames = River.builder()
      .id(2L)
      .name("River Thames")
      .build();

    Place placeOfBirth = Place.builder()
      .altitude(altitude)
      .createdBy("testuser")
      .id(1L)
      .lastUpdated(now.plus(1, ChronoUnit.MINUTES))
      .latitude(latitude)
      .longitude(longitude)
      .name("London")
      .operation(DataOperation.CREATE)
      .river(thames)
      .status(VersionStatus.New)
      .build();

    HistoricalDate dateOfBirth = HistoricalDate.builder()
      .day(11).month(3).year(1940).build();

    HistoricalDate dateOfDeath = HistoricalDate.builder()
      .day(23).month(11).year(1960).build();

    Place placeOfDeath = Place.builder()
      .altitude(altitude)
      .createdBy("testuser")
      .latitude(latitude)
      .longitude(longitude)
      .name("Malborough")
      .status(VersionStatus.Amend)
      .build();

    Person person = Person.builder()
      .birthPlace(placeOfBirth)
      .createdBy("testuser")
      .dateOfBirth(dateOfBirth)
      .dateOfDeath(dateOfDeath)
      .deathPlace(placeOfDeath)
      .familyName("Churchill")
      .firstName("Winston")
      .gender(Gender.Male)
      .middleName("Spencer")
      .prefixTitle(constructTitle("Sir", TitleType.Prefix, Gender.Male))
      .suffixTitle(constructTitle("KG", TitleType.Suffix, Gender.Any))
      .status(VersionStatus.New)
      .build();

    return person;
  }

  private Title constructTitle(String name, TitleType titleType, Gender appliesTo) {
    return Title.builder()
      .title(name)
      .titleType(titleType)
      .appliesTo(appliesTo)
      .createdBy("testuser")
      .status(VersionStatus.New)
      .build();
  }
}
