package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.TitleTestHelper;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.dto.PersonTitleDto;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.entity.PersonTitle;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import com.apschulewitz.resdb.research.model.helper.PersonTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class PersonTitleTestHelper extends AbstractTestHelper<PersonTitle, PersonTitleDto> {

  private final PersonTestHelper personTestHelper;

  private final TitleTestHelper titleTestHelper;

  public PersonTitleTestHelper(PersonTestHelper personTestHelper,
                               TitleTestHelper titleTestHelper) {
    this.personTestHelper = personTestHelper;
    this.titleTestHelper = titleTestHelper;
  }

  @Override
  public PersonTitle constructUnsavedMinimalEntity() {
    Person person = personTestHelper.constructUnsavedMinimalEntity();
    Title title = titleTestHelper.constructUnsavedMinimalEntity();
    return PersonTitle.builder()
      .person(person)
      .position(1)
      .title(title)
      .build();
  }

  @Override
  public PersonTitle constructNewEntityWithAllValues() {
    Person person = personTestHelper.constructUnsavedMinimalEntity();
    Title title = titleTestHelper.constructUnsavedMinimalEntity();
    return PersonTitle.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .person(person)
      .status(VersionStatus.Amend)
      .title(title)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public PersonTitleDto constructUnsavedMinimalDto() {
    PersonDto person = personTestHelper.constructUnsavedMinimalDto();
    TitleDto title = titleTestHelper.constructUnsavedMinimalDto();
    return PersonTitleDto.builder()
      .person(person)
      .position(1)
      .title(title)
      .build();
  }

  @Override
  public PersonTitleDto constructNewDtoWithAllValues() {
    PersonDto person = personTestHelper.constructUnsavedMinimalDto();
    TitleDto title = titleTestHelper.constructUnsavedMinimalDto();
    return PersonTitleDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .person(person)
      .position(1)
      .status(VersionStatus.Amend.name())
      .title(title)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<PersonTitleDto> constructListOfUnsavedMinimalDto() {
    TitleDto title = titleTestHelper.constructUnsavedMinimalDto();
    title.setTitle(title.getTitle()+"2");
    PersonTitleDto dto1 = constructUnsavedMinimalDto();
    PersonTitleDto dto2 = constructUnsavedMinimalDto();
    dto2.setTitle(title);
    dto2.setPosition(2);
    return Arrays.asList(dto1, dto2);
  }
}
