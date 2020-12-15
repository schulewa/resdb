package com.apschulewitz.resdb.refdata.model.mapper;

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
  public PlaceDto toDto(Place entity, boolean onlyActive) {
    return null;
  }

  @Override
  public Place toEntity(PlaceDto dto, boolean onlyActive) {
    Place entity = null;

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
      // TODO set updatedBy to logged in user
      .build();
    return entity;
  }
}
