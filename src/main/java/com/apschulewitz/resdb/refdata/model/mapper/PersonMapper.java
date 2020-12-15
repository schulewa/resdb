package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.dto.HistoricalDateDto;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.TitleMapper;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper implements VersionableEntityDtoMapper<Person, PersonDto> {

  private PlaceMapper placeMapper;
  private TitleMapper titleMapper;

  public PersonMapper(PlaceMapper placeMapper, TitleMapper titleMapper) {
    this.placeMapper = placeMapper;
    this.titleMapper = titleMapper;
  }

  @Override
  public Person toEntity(PersonDto dto) {
    Gender gender = Gender.Unknown;
    if (dto.getGender() != null) {
      gender = Gender.getGenderFor(dto.getGender());
    }

    HistoricalDate dateOfBirth = null;
    if (dto.getDateOfBirth() != null) {
      dateOfBirth = HistoricalDate.builder()
        .day(dto.getDateOfBirth().getDay())
        .month(dto.getDateOfBirth().getMonth())
        .year(dto.getDateOfBirth().getYear())
        .build();
    }

    HistoricalDate dateOfDeath = null;
    if (dto.getDateOfDeath() != null) {
      dateOfDeath = HistoricalDate.builder()
        .day(dto.getDateOfDeath().getDay())
        .month(dto.getDateOfDeath().getMonth())
        .year(dto.getDateOfDeath().getYear())
        .build();
    }

    Place placeOfBirth = null;

    if (dto.getBirthPlace() != null) {
      placeOfBirth = placeMapper.toEntity(dto.getBirthPlace());
    }

    Place placeOfDeath = null;
    if (dto.getDeathPlace() != null) {
      placeOfDeath = placeMapper.toEntity(dto.getDeathPlace());
    }

    VersionStatus status = VersionStatus.New;
    if (dto.getStatus() != null) {
      status = VersionStatus.getInstance(dto.getStatus());
    }

    Title prefixTitle = null;
    if (dto.getPrefixTitle() != null) {
      prefixTitle = titleMapper.toEntity(dto.getPrefixTitle());
    }

    Title suffixTitle = null;
    if (dto.getSuffixTitle() != null) {
      suffixTitle = titleMapper.toEntity(dto.getSuffixTitle());
    }

    Person person = Person.builder()
      .birthPlace(placeOfBirth)
      .createdBy(dto.getCreatedBy())
      .dateOfBirth(dateOfBirth)
      .dateOfDeath(dateOfDeath)
      .deathPlace(placeOfDeath)
      .firstName(dto.getFirstName())
      .gender(gender)
      .middleName(dto.getMiddleName())
      .familyName(dto.getFamilyName())
      .prefixTitle(prefixTitle)
      .status(status)
      .suffixTitle(suffixTitle)
    .build();

    return person;
  }

  @Override
  public PersonDto toDto(Person person) {
    if (person == null) {
      throw new IllegalArgumentException("Null person cannot be mapped to dto");
    }

    PlaceDto birthPlace = null;
    if (person.getBirthPlace() != null) {
        birthPlace = PlaceDto.builder()
        .createdBy(person.getBirthPlace().getCreatedBy())
        .id(person.getBirthPlace().getId())
        .name(person.getBirthPlace().getName())
                    .status(person.getBirthPlace().getStatus().name())
        .build();
    }

    PlaceDto deathPlace = null;
    if (person.getDeathPlace() != null) {
      deathPlace = PlaceDto.builder()
        .createdBy(person.getDeathPlace().getCreatedBy())
        .id(person.getDeathPlace().getId())
        .name(person.getDeathPlace().getName())
        .status(person.getDeathPlace().getStatus().name())
        .build();
    }

    HistoricalDateDto birthDate = null;
    if (person.getDateOfBirth() != null) {
      birthDate = HistoricalDateDto.builder()
        .day(person.getDateOfBirth().getDay())
        .month(person.getDateOfBirth().getMonth())
        .year(person.getDateOfBirth().getYear())
        .build();
    }

    HistoricalDateDto deathDate = null;
    if (person.getDateOfDeath() != null) {
      deathDate = HistoricalDateDto.builder()
        .day(person.getDateOfDeath().getDay())
        .month(person.getDateOfDeath().getMonth())
        .year(person.getDateOfDeath().getYear())
        .build();
    }

    String gender = null;
    if (person.getGender() != null) {
      gender = person.getGender().name();
    }

    TitleDto prefixTitle = null;
    if (person.getPrefixTitle() != null) {
      prefixTitle = titleMapper.toDto(person.getPrefixTitle());
    }

    TitleDto suffixTitle = null;
    if (person.getSuffixTitle() != null) {
      suffixTitle = titleMapper.toDto(person.getSuffixTitle());
    }

    return PersonDto.builder()
      .birthPlace(birthPlace)
      .createdBy(person.getCreatedBy())
      .dateOfBirth(birthDate)
      .dateOfDeath(deathDate)
      .deathPlace(deathPlace)
      .familyName(person.getFamilyName())
      .firstName(person.getFirstName())
      .gender(gender)
      .id(person.getId())
      .middleName(person.getMiddleName())
      .prefixTitle(prefixTitle)
      .status(person.getStatus().name())
      .suffixTitle(suffixTitle)
      .updatedBy(person.getUpdatedBy())
      .build();
  }

  @Override
  public PersonDto toDto(Person person, boolean onlyActive) {
    if (VersionStatus.getLiveStatuses().contains(person.getStatus()) || !onlyActive) {
      return toDto(person);
    }
    return null;
  }

  @Override
  public Person toEntity(PersonDto dto, boolean onlyActive) {
    return null;
  }

//  @Override
//  public Person toDto(PersonDto entity) {
//    return null;
//  }
}
