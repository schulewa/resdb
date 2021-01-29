package com.apschulewitz.resdb.research.model;

import com.apschulewitz.resdb.common.model.dto.AltitudeDto;
import com.apschulewitz.resdb.common.model.dto.LatitudeDto;
import com.apschulewitz.resdb.common.model.dto.LongitudeDto;
import com.apschulewitz.resdb.common.model.dto.RiverDto;
import com.apschulewitz.resdb.common.model.entity.Altitude;
import com.apschulewitz.resdb.common.model.entity.Latitude;
import com.apschulewitz.resdb.common.model.entity.Longitude;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import com.apschulewitz.resdb.refdata.model.entity.River;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;

@Component
public class PlaceTestHelper extends AbstractTestHelper<Place, PlaceDto> {

  @Override
  public Place constructUnsavedMinimalEntity() {
    return Place.builder()
      .name(Place.class.getSimpleName())
      .build();
  }

  @Override
  public Place constructNewEntityWithAllValues() {
    Altitude altitude = Altitude.builder().value("altitude").build();
    Latitude latitude = Latitude.builder().value("latitude").build();
    Longitude longitude = Longitude.builder().value("longitude").build();
    River river = River.builder().name(River.class.getSimpleName()).build();

    return Place.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .altitude(altitude)
      .id(ID.getAndIncrement())
      .latitude(latitude)
      .longitude(longitude)
      .name(Place.class.getSimpleName())
      .river(river)
      .status(VersionStatus.Amend)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public PlaceDto constructUnsavedMinimalDto() {
    return PlaceDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(PlaceDto.class.getSimpleName())
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public PlaceDto constructNewDtoWithAllValues() {
    AltitudeDto altitudeDto = new AltitudeDto();
    altitudeDto.setValue("altitude");
    LatitudeDto latitudeDto = LatitudeDto.builder().value("latitude").build();
    LongitudeDto longitudeDto = LongitudeDto.builder().value("longitude").build();
    RiverDto riverDto = RiverDto.builder().name(River.class.getSimpleName()).build();
    return PlaceDto.builder()
      .altitude(altitudeDto)
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .latitude(latitudeDto)
      .longitude(longitudeDto)
      .name(PlaceDto.class.getSimpleName())
      .river(riverDto)
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<PlaceDto> constructListOfUnsavedMinimalDto() {
    return null;
  }
}
