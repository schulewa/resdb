package com.apschulewitz.resdb.research.model;

import com.apschulewitz.resdb.common.model.TitleTestHelper;
import com.apschulewitz.resdb.common.model.dto.AltitudeDto;
import com.apschulewitz.resdb.common.model.dto.HistoricalDateDto;
import com.apschulewitz.resdb.common.model.dto.LatitudeDto;
import com.apschulewitz.resdb.common.model.dto.LongitudeDto;
import com.apschulewitz.resdb.common.model.dto.RiverDto;
import com.apschulewitz.resdb.common.model.entity.Altitude;
import com.apschulewitz.resdb.common.model.entity.DataOperation;
import com.apschulewitz.resdb.common.model.entity.Gender;
import com.apschulewitz.resdb.common.model.entity.HistoricalDate;
import com.apschulewitz.resdb.common.model.entity.Latitude;
import com.apschulewitz.resdb.common.model.entity.Longitude;
import com.apschulewitz.resdb.common.model.entity.Title;
import com.apschulewitz.resdb.common.model.entity.TitleType;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.PersonDto;
import com.apschulewitz.resdb.refdata.model.dto.PersonTitleDto;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.dto.TitleDto;
import com.apschulewitz.resdb.refdata.model.entity.Person;
import com.apschulewitz.resdb.refdata.model.entity.PersonTitle;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import com.apschulewitz.resdb.refdata.model.entity.River;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class PersonTestHelper extends AbstractTestHelper {

  public static PersonDto constructNewPersonDto(String firstName,
                                                String middleName,
                                                String familyName,
                                                HistoricalDateDto dateOfBirth,
                                                PlaceDto placeOfBirth,
                                                HistoricalDateDto dateOfDeath,
                                                PlaceDto placeOfDeath,
                                                Gender gender,
                                                TitleDto prefixTitle,
                                                TitleDto suffixTitle,
                                                List<PersonTitleDto> titles) {
    return PersonDto.builder()
      .createdBy(USER_NAME)
      .familyName(familyName)
      .firstName(firstName)
      .middleName(middleName)
      .birthPlace(placeOfBirth)
      .dateOfBirth(dateOfBirth)
      .dateOfDeath(dateOfDeath)
      .deathPlace(placeOfDeath)
      .gender(gender.name())
      .prefixTitle(prefixTitle)
      .status(VersionStatus.New.name())
      .suffixTitle(suffixTitle)
      .titles(titles)
      .build();
  }

  public static PersonDto constructCancelledPersonDto(String firstName,
                                                String middleName,
                                                String familyName,
                                                HistoricalDateDto dateOfBirth,
                                                PlaceDto placeOfBirth,
                                                HistoricalDateDto dateOfDeath,
                                                PlaceDto placeOfDeath,
                                                Gender gender,
                                                TitleDto prefixTitle,
                                                TitleDto suffixTitle,
                                                List<PersonTitleDto> titles) {
    return PersonDto.builder()
      .createdBy(USER_NAME)
      .familyName(familyName)
      .firstName(firstName)
      .middleName(middleName)
      .birthPlace(placeOfBirth)
      .dateOfBirth(dateOfBirth)
      .dateOfDeath(dateOfDeath)
      .deathPlace(placeOfDeath)
      .gender(gender.name())
      .prefixTitle(prefixTitle)
      .status(VersionStatus.Cancel.name())
      .suffixTitle(suffixTitle)
      .titles(titles)
      .build();
  }

  public static PersonDto constructNewPersonDto() {
    LocalDateTime now = LocalDateTime.now();

    AltitudeDto altitude = new AltitudeDto();
    altitude.setValue("altitude");

    LatitudeDto latitude = LatitudeDto.builder().value("latitude").build();
    LongitudeDto longitude = LongitudeDto.builder().build().builder().value("longitude").build();

    RiverDto thames = RiverDto.builder()
      .id(2L)
      .name("River Thames")
      .build();

//    PlaceDto placeOfBirth = PlaceDto.builder()
//      .altitude(altitude)
//      .createdBy(USER_NAME)
//      .id(1L)
//      .lastUpdated(now.plus(1, ChronoUnit.MINUTES))
//      .latitude(latitude)
//      .longitude(longitude)
//      .name("London")
//      .operation(DataOperation.CREATE.name())
//      .river(thames)
//      .status(VersionStatus.New.name())
//      .build();

    PlaceDto placeOfBirth = PlaceTestHelper.constructNewPlaceDto(altitude, latitude, longitude, "London", thames);

    PlaceDto placeOfDeath = PlaceDto.builder()
      .altitude(altitude)
      .createdBy(USER_NAME)
      .latitude(latitude)
      .longitude(longitude)
      .name("Malborough")
      .status(VersionStatus.Amend.name())
      .build();

    HistoricalDateDto dateOfBirth = HistoricalDateDto.builder()
      .day(11).month(3).year(1940).build();

    HistoricalDateDto dateOfDeath = HistoricalDateDto.builder()
      .day(23).month(11).year(1960).build();

    PersonDto dto = PersonDto.builder()
      .birthPlace(placeOfBirth)
      .createdBy(USER_NAME)
      .dateOfBirth(dateOfBirth)
      .dateOfDeath(dateOfDeath)
      .deathPlace(placeOfDeath)
      .familyName("Churchill")
      .firstName("Winston")
      .gender(Gender.Male.name())
      .middleName("Spencer")
      .prefixTitle(constructTitleDto("Sir", TitleType.Prefix.name(), Gender.Male.name()))
      .suffixTitle(constructTitleDto("KG", TitleType.Suffix.name(), Gender.Any.name()))
      .status(VersionStatus.New.name())
      .build();

    return dto;
  }

  public static Person constructNewPerson(String firstName,
                                          String middleName,
                                          String familyName,
                                          Gender gender,
                                          HistoricalDate dateOfBirth,
                                          Place placeOfBirth,
                                          HistoricalDate dateOfDeath,
                                          Place placeOfDeath,
                                          Title prefixTitle,
                                          Title suffixTitle,
                                          List<PersonTitle> titles) {
    return Person.builder()
      .createdBy(USER_NAME)
      .birthPlace(placeOfBirth)
      .dateOfBirth(dateOfBirth)
      .dateOfDeath(dateOfDeath)
      .deathPlace(placeOfDeath)
      .firstName(firstName)
      .familyName(familyName)
      .gender(gender)
      .middleName(middleName)
      .prefixTitle(prefixTitle)
      .status(VersionStatus.New)
      .suffixTitle(suffixTitle)
      .titles(titles)
      .build();
  }

  public static Person constructPerson() {
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
      .createdBy(USER_NAME)
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
      .createdBy(USER_NAME)
      .dateOfBirth(dateOfBirth)
      .dateOfDeath(dateOfDeath)
      .deathPlace(placeOfDeath)
      .familyName("Churchill")
      .firstName("Winston")
      .gender(Gender.Male)
      .middleName("Spencer")
      .prefixTitle(TitleTestHelper.constructNewTitle("Sir", TitleType.Prefix, Gender.Male))
      .suffixTitle(TitleTestHelper.constructNewTitle("KG", TitleType.Suffix, Gender.Any))
      .status(VersionStatus.New)
      .build();

    return person;
  }

  public static Title constructTitle(String name, TitleType titleType, Gender appliesTo) {
    return Title.builder()
      .title(name)
      .titleType(titleType)
      .appliesTo(appliesTo)
      .createdBy(USER_NAME)
      .status(VersionStatus.New)
      .build();
  }

  public static TitleDto constructTitleDto(String name, String titleType, String appliesTo) {
    return TitleDto.builder()
      .title(name)
      .titleType(titleType)
      .appliesTo(appliesTo)
      .createdBy(USER_NAME)
      .status(VersionStatus.New.name())
      .build();
  }
}
