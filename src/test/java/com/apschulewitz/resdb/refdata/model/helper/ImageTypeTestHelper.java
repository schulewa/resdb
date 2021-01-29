package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ImageTypeTestHelper extends AbstractTestHelper<ImageType, ImageTypeDto> {

  @Override
  public ImageType constructUnsavedMinimalEntity() {
    return ImageType.builder()
      .name(ImageType.class.getSimpleName())
      .build();
  }

  @Override
  public ImageType constructNewEntityWithAllValues() {
    return ImageType.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(ImageType.class.getSimpleName())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public ImageTypeDto constructUnsavedMinimalDto() {
    return ImageTypeDto.builder()
      .name(ImageTypeDto.class.getSimpleName())
      .build();
  }

  @Override
  public ImageTypeDto constructNewDtoWithAllValues() {
    return ImageTypeDto.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .name(ImageTypeDto.class.getSimpleName())
      .status(VersionStatus.New.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<ImageTypeDto> constructListOfUnsavedMinimalDto() {
    ImageTypeDto dto1 = constructUnsavedMinimalDto();
    ImageTypeDto dto2 = constructUnsavedMinimalDto();
    dto2.setName(dto2.getName()+"2");
    return Arrays.asList(dto1, dto2);
  }
}
