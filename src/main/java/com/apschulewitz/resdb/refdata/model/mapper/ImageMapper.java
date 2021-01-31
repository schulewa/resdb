package com.apschulewitz.resdb.refdata.model.mapper;

import com.apschulewitz.resdb.common.model.entity.VersionStatus;
import com.apschulewitz.resdb.common.model.mapper.VersionableEntityDtoMapper;
import com.apschulewitz.resdb.refdata.model.dto.ImageDto;
import com.apschulewitz.resdb.refdata.model.dto.ImageTypeDto;
import com.apschulewitz.resdb.refdata.model.entity.Image;
import com.apschulewitz.resdb.refdata.model.entity.ImageType;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

@Component
//@NoArgsConstructor
public class ImageMapper implements VersionableEntityDtoMapper<Image, ImageDto> {

  private ImageTypeMapper imageTypeMapper;

  public ImageMapper(ImageTypeMapper imageTypeMapper) {
    this.imageTypeMapper = imageTypeMapper;
  }

  @Override
  public Image toEntity(ImageDto dto, boolean onlyActive) {
    if (dto == null) {
      throw new IllegalArgumentException("Null image cannot be mapped to entity");
    }

    VersionStatus status = null;
    if (!StringUtils.isEmpty(dto.getStatus())) {
      status = VersionStatus.getInstance(dto.getStatus());
    }

    if (VersionStatus.getLiveStatuses().contains(status) || !onlyActive) {
      return toEntity(dto);
    }

    return null;
  }

  @Override
  public Image toEntity(ImageDto dto) {

    if (dto == null) {
      throw new IllegalArgumentException("Null image cannot be mapped to entity");
    }

    ImageType imageType = null;
    if (dto.getImageType() != null) {
      imageType = imageTypeMapper.toEntity(dto.getImageType());
    }

    return Image.builder()
      .createdBy(dto.getCreatedBy())
      .filePath(dto.getFilePath())
      .fileName(dto.getFileName())
      .id(dto.getId())
      .imageType(imageType)
      .lastUpdated(dto.getLastUpdated())
      .status(VersionStatus.getInstance(dto.getStatus()))
      .updatedBy(dto.getUpdatedBy())
      .versionNumber(dto.getVersionNumber())
      .build();
  }

  @Override
  public ImageDto toDto(Image entity) {
    if (entity == null) {
      throw new IllegalArgumentException("Null image cannot be mapped to dto");
    }

    ImageTypeDto imageTypeDto = null;
    if (entity.getImageType() != null) {
      String status = null;
      if (entity.getImageType().getStatus() != null) {
        status = entity.getImageType().getStatus().name();
      }
      imageTypeDto = ImageTypeDto.builder()
        .createdBy(entity.getImageType().getCreatedBy())
        .id(entity.getImageType().getId())
        .lastUpdated(entity.getImageType().getLastUpdated())
        .name(entity.getImageType().getName())
        .status(status)
        .updatedBy(entity.getImageType().getUpdatedBy())
        .versionNumber(entity.getImageType().getVersionNumber())
        .build();
    }

    String entityStatus = null;
    if (entity.getStatus() != null) {
      entityStatus = entity.getStatus().name();
    }
    return ImageDto.builder()
      .createdBy(entity.getCreatedBy())
      .filePath(entity.getFilePath())
      .fileName(entity.getFileName())
      .id(entity.getId())
      .imageType(imageTypeDto)
      .lastUpdated(entity.getLastUpdated())
      .status(entityStatus)
      .updatedBy(entity.getUpdatedBy())
      .versionNumber(entity.getVersionNumber())
      .build();
  }

  @Override
  public ImageDto toDto(Image entity, boolean onlyActive) {

    if (entity == null) {
      throw new IllegalArgumentException("Null image cannot be mapped to dto");
    }

    if (VersionStatus.getLiveStatuses().contains(entity.getStatus()) || !onlyActive) {
      return toDto(entity);
    }
    return null;
  }

}
