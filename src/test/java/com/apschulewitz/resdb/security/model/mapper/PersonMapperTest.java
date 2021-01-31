package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.common.model.TitleTestHelper;
import com.apschulewitz.resdb.common.model.dto.HistoricalDateDto;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.TitleMapper;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.mapper.PersonMapper;
import com.apschulewitz.resdb.refdata.model.mapper.PlaceMapper;
import com.apschulewitz.resdb.research.model.helper.PersonTestHelper;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.research.model.helper.PlaceTestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Month;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
public class PersonMapperTest {

  private PersonMapper personMapper;

  private PersonTestHelper personTestHelper;

  private PlaceTestHelper placeTestHelper;

  private TitleTestHelper titleTestHelper;

  @Before
  public void beforeEachTest() {
    personMapper = new PersonMapper(new PlaceMapper(), new TitleMapper());
    titleTestHelper = new TitleTestHelper();
    personTestHelper = new PersonTestHelper(placeTestHelper, titleTestHelper);
    placeTestHelper = new PlaceTestHelper();
  }

  @Test
  public void given_null_dto_when_toEntity_is_executed_then_throw_exception() {
    assertThatThrownBy(() -> personMapper.toEntity(null))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null person cannot be mapped to entity");
  }

  @Test
  public void given_null_person_when_toDto_is_executed_then_throw_exception() {
    assertThatThrownBy(() -> personMapper.toDto(null))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null person cannot be mapped to dto");
  }

  @Test
  public void given_inactive_person_and_onlyactive_when_toDto_is_executed_then_return_null() {
    // given
    Person person = PersonTestHelper.constructPerson();
    person.setStatus(VersionStatus.Cancel);

    // when
    PersonDto personDto = personMapper.toDto(person, true);

    // then
    assertNull(personDto);
  }

  @Test
  public void given_inactive_person_and_not_onlyactive_when_toDto_is_executed_then_return_null() {
    // given
    Person person = PersonTestHelper.constructPerson();
    person.setStatus(VersionStatus.Cancel);

    // when
    PersonDto personDto = personMapper.toDto(person, false);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_active_person_and_not_onlyactive_when_toDto_is_executed_then_return_null() {
    // given
    Person person = PersonTestHelper.constructPerson();

    // when
    PersonDto personDto = personMapper.toDto(person, false);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_when_toDto_is_executed_then_return_dto() {
    // given
    Person person = PersonTestHelper.constructPerson();

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_birthplace_then_return_dto() {
    // given
    Person person = PersonTestHelper.constructPerson();
    person.setBirthPlace(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_deathplace_then_return_dto() {
    // given
    Person person = PersonTestHelper.constructPerson();
    person.setDeathPlace(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_dateofbirth_then_return_dto() {
    // given
    Person person = PersonTestHelper.constructPerson();
    person.setDateOfBirth(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_dateofbirth_when_toEntity_is_executed_then_return_entity() {
    // given
    PersonDto personDto = personTestHelper.constructUnsavedMinimalDto();
    HistoricalDateDto dateOfBirth = HistoricalDateDto.builder()
      .year(1022)
      .build();
    personDto.setDateOfBirth(dateOfBirth);
    HistoricalDateDto dateOfDeath = HistoricalDateDto.builder()
      .year(1066)
      .month(Month.OCTOBER.getValue())
      .day(14)
      .build();
    personDto.setDateOfDeath(dateOfDeath);
    PlaceDto placeOfBirth = placeTestHelper.constructUnsavedMinimalDto();
    personDto.setBirthPlace(placeOfBirth);
    personDto.setDeathPlace(placeOfBirth);

    TitleDto title = titleTestHelper.constructUnsavedMinimalDto();
    personDto.setPrefixTitle(title);
    personDto.setSuffixTitle(title);

    // when
    Person person = personMapper.toEntity(personDto);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_dateofdeath_then_return_dto() {
    // given
    Person person = PersonTestHelper.constructPerson();
    person.setDateOfDeath(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_gender_then_return_dto() {
    // given
    Person person = PersonTestHelper.constructPerson();
    person.setGender(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_prefixtitle_then_return_dto() {
    // given
    Person person = PersonTestHelper.constructPerson();
    person.setPrefixTitle(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_valid_person_with_no_suffixtitle_then_return_dto() {
    // given
    Person person = PersonTestHelper.constructPerson();
    person.setSuffixTitle(null);

    // when
    PersonDto personDto = personMapper.toDto(person);

    // then
    assertNotNull(personDto);
  }

  @Test
  public void given_null_entity_and_onlyactive_when_toDto_is_executed_then_throw_exception() {
    assertThatThrownBy(() -> personMapper.toDto(null, false))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null person cannot be mapped to dto");
  }

  @Test
  public void given_null_entity_and_onlyactive_is_false_when_toEntity_is_executed_then_throw_exception() {
    assertThatThrownBy(() -> personMapper.toEntity(null, false))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessageContaining("Null person cannot be mapped to entity");
  }

  @Test
  public void given_entity_and_onlyactive_is_true_when_toEntity_is_executed_then_return_entity() {
    // given
    PersonDto dto = personTestHelper.constructUnsavedMinimalDto();

    // when
    Person entity = personMapper.toEntity(dto, true);

    // then
    assertNotNull(entity);
  }

  @Test
  public void given_entity_and_onlyactive_is_true_and_status_is_inactive_when_toEntity_is_executed_then_return_null() {
    // given
    PersonDto dto = personTestHelper.constructUnsavedMinimalDto();
    dto.setStatus(VersionStatus.Cancel.name());

    // when
    Person entity = personMapper.toEntity(dto, true);

    // then
    assertNull(entity);
  }
}
