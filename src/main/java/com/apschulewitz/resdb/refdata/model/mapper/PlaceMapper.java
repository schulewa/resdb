package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.dto.AltitudeDto;
import com.apschulewitz.resdb.common.model.dto.LatitudeDto;
import com.apschulewitz.resdb.common.model.dto.LongitudeDto;
import com.apschulewitz.resdb.common.model.entity.Altitude;
import com.apschulewitz.resdb.common.model.entity.Latitude;
import com.apschulewitz.resdb.common.model.entity.Longitude;
import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.PlaceDto;
import com.apschulewitz.resdb.refdata.model.entity.Place;
import com.apschulewitz.resdb.refdata.model.entity.River;
import org.springframework.stereotype.Component;

@Component
public class PlaceMapper implements VersionableEntityDtoMapper<Place, PlaceDto> {

  @Override
  public PlaceDto toDto(Place entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null place cannot be mapped to dto");
    }

    PlaceDto placeDto;

    AltitudeDto altitude = null;
    if (entity.getAltitude() != null) {
      altitude = AltitudeDto.builder()
        .value(entity.getAltitude().getValue())
        .build();
    }

    LatitudeDto latitude = null;
    if (entity.getLatitude() != null) {
      latitude = LatitudeDto.builder()
        .value(entity.getLatitude().getValue())
        .build();
    }

    LongitudeDto longitude = null;
    if (entity.getLongitude() != null) {
      longitude = LongitudeDto.builder()
        .value(entity.getLongitude().getValue())
        .build();
    }

    String status = null;
    if (entity.getStatus() != null) {
      status = entity.getStatus().name();
    }

    placeDto = PlaceDto.builder()
      .altitude(altitude)
      .createdBy(entity.getCreatedBy())
      .id(entity.getId())
      .lastUpdated(entity.getLastUpdated())
      .latitude(latitude)
      .longitude(longitude)
      .name(entity.getName())
      .status(status)
      .updatedBy(entity.getUpdatedBy())
      .versionNumber(entity.getVersionNumber())
      .build();

    return placeDto;
  }

  @Override
  public Place toEntity(PlaceDto dto) {
    if (dto == null) {
      throw new IllegalArgumentException("Null place cannot be mapped to entity");
    }

    Place entity;

    Altitude altitude = null;
    if (dto.getAltitude() != null) {
      altitude = new Altitude();
      altitude.setValue(dto.getAltitude().getValue());
    }

    Latitude latitude = null;
    if (dto.getLatitude() != null) {
      latitude = Latitude.builder().value(dto.getLatitude().getValue()).build();
    }

    Longitude longitude = null;
    if (dto.getLongitude() != null) {
      longitude = Longitude.builder().value(dto.getLongitude().getValue()).build();
    }

    River river = null;
    if (dto.getRiver() != null) {
      river = River.builder()
        .id(dto.getRiver().getId())
        .name(dto.getRiver().getName())
        .build();
    }

    VersionStatus status = VersionStatus.New;
    if (dto.getStatus() != null) {
      status = VersionStatus.getInstance(dto.getStatus());
    }

    entity = Place.builder()
      .altitude(altitude)
      .createdBy(dto.getCreatedBy())
      .id(dto.getId())
      .lastUpdated(dto.getLastUpdated())
      .latitude(latitude)
      .longitude(longitude)
      .name(dto.getName())
      .river(river)
      .status(status)
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
      .build();
    return entity;
  }

  @Override
  public PlaceDto toDto(Place entity, boolean onlyActive) {
    if (entity == null) {
      throw new IllegalArgumentException("Null place cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

  @Override
  public Place toEntity(PlaceDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null place cannot be mapped to entity");
    }

    if (VersionStatus.getLiveStatuses().contains(dto.getStatus()) || !onlyActive) {
      return toEntity(dto);
    }
    return null;
  }
}
