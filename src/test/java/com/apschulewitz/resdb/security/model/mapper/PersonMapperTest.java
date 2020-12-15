package com.apschulewitz.resdb.security.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.TitleMapper;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.mapper.PersonMapper;
import com.apschulewitz.resdb.refdata.model.mapper.PlaceMapper;
import com.apschulewitz.resdb.research.model.PersonTestHelper;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;


@RunWith(SpringRunner.class)
public class PersonMapperTest {

  private PersonMapper personMapper;

  @Before
  public void beforeEachTest() {
    personMapper = new PersonMapper(new PlaceMapper(), new TitleMapper());
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

}
