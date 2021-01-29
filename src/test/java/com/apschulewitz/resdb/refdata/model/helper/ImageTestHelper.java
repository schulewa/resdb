package com.apschulewitz.resdb.refdata.model.helper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.refdata.model.dto.ImageDto;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.Image;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import com.apschulewitz.resdb.research.model.AbstractTestHelper;
import org.springframework.stereotype.Component;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class ImageTestHelper extends AbstractTestHelper<Image, ImageDto> {

  @Override
  public Image constructUnsavedMinimalEntity() {
    return Image.builder()
      .filePath("/tmp")
      .fileName(Image.class.getSimpleName())
      .imageType(ImageType.builder().name("PNG").build())
      .build();
  }

  @Override
  public Image constructNewEntityWithAllValues() {
    return Image.builder()
      .createdBy(USER_NAME)
      .id(ID.getAndIncrement())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .filePath("/tmp")
      .fileName(Image.class.getSimpleName())
      .imageType(ImageType.builder().name("PNG").build())
      .status(VersionStatus.New)
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public ImageDto constructUnsavedMinimalDto() {
    return ImageDto.builder()
      .filePath("/tmp")
      .fileName(Image.class.getSimpleName())
      .imageType(ImageTypeDto.builder().name("PNG").build())
      .build();
  }

  @Override
  public ImageDto constructNewDtoWithAllValues() {
    return ImageDto.builder()
      .createdBy(USER_NAME)
      .filePath("/tmp")
      .fileName(Image.class.getSimpleName())
      .id(ID.getAndIncrement())
      .imageType(ImageTypeDto.builder().name("PNG").build())
      .lastUpdated(ZonedDateTime.now(ZoneOffset.UTC))
      .status(VersionStatus.New.name())
      .updatedBy(USER_NAME1)
      .versionNumber(VERSION_NUMBER.getAndIncrement())
      .build();
  }

  @Override
  public List<ImageDto> constructListOfUnsavedMinimalDto() {
    ImageDto image1 = constructUnsavedMinimalDto();
    ImageDto image2 = constructUnsavedMinimalDto();
    image2.setFileName(image2.getFileName()+"2");
    return Arrays.asList(image1, image2);
  }
}
