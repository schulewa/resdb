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

public class PlaceTestHelper extends AbstractTestHelper {

  public static PlaceDto constructNewPlaceDto(AltitudeDto altitude,
                                              LatitudeDto latitude,
                                              LongitudeDto longitude,
                                              String name,
                                              RiverDto river) {
    return PlaceDto.builder()
      .altitude(altitude)
      .createdBy(USER_NAME)
      .latitude(latitude)
      .longitude(longitude)
      .name(name)
      .river(river)
      .status(VersionStatus.New.name())
      .build();
  }

  public static Place constructNewPlace(Altitude altitude,
                                        Latitude latitude,
                                        Longitude longitude,
                                        String name,
                                        River river) {
    return Place.builder()
      .altitude(altitude)
      .createdBy(USER_NAME)
      .latitude(latitude)
      .longitude(longitude)
      .name(name)
      .river(river)
      .status(VersionStatus.New)

      .build();
  }

}
