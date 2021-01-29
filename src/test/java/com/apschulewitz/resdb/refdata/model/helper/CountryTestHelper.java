package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.ArtefactGroupDto;
import com.apschulewitz.resdb.refdata.model.dto.CountryDto;
import com.apschulewitz.resdb.refdata.model.dto.ImageDto;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.Country;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class CountryTestHelper extends AbstractTestHelper<Country, CountryDto> {

  @Override
  public Country constructUnsavedMinimalEntity() {
    return Country.builder()
      .code("UK")
      .name(Country.class.getSimpleName())
      .sovereignty("UK")
      .stateName("GB")
      .build();
  }

  @Override
  public Country constructNewEntityWithAllValues() {
    return Country.builder()
      .code("UK")
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(Country.class.getSimpleName())
      .sovereignty("UK")
      .stateName("GB")
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public CountryDto constructUnsavedMinimalDto() {
    return CountryDto.builder()
      .code("UK")
      .name(CountryDto.class.getSimpleName())
      .sovereignty("UK")
      .stateName("GB")
      .build();
  }

  @Override
  public CountryDto constructNewDtoWithAllValues() {
    ImageDto imageDto = ImageDto.builder()
      .createdBy(USER_NAME)
      .filePath("/filepath")
      .fileName("image.png")
      .id(ID.getAndIncrement())
      .imageType(ImageTypeDto.builder().name("PNG").build())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();

    return CountryDto.builder()
      .code("UK")
      .createdBy(USER_NAME)
      .flagImage(imageDto)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(CountryDto.class.getSimpleName())
      .sovereignty("UK")
      .stateName("GB")
      .status(VersionStatus.Amend.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();

  }

  @Override
  public CountryDto constructModifiedMinimalDto(CountryDto savedDto) {
    savedDto.setName(savedDto.getName()+"2");
    return savedDto;
  }

  @Override
  public List<CountryDto> constructListOfUnsavedMinimalDto() {
    CountryDto dto1 = constructUnsavedMinimalDto();
    CountryDto dto2 = constructUnsavedMinimalDto();
    dto2.setCode("U2");
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
